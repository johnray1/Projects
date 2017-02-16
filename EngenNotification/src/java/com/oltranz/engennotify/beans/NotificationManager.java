/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.beans;

import com.oltranz.engennotify.models.Diping;
import com.oltranz.engennotify.entities.DippingNotification;
import com.oltranz.engennotify.entities.Log;
import com.oltranz.engennotify.entities.LowQuantityNotification;
import com.oltranz.engennotify.entities.Notification;
import com.oltranz.engennotify.entities.NotificationDestination;
import com.oltranz.engennotify.library.Common;
import com.oltranz.engennotify.models.Tank;
import com.oltranz.engennotify.library.CommonLibrary;
import com.oltranz.engennotify.models.Branch;
import com.oltranz.engennotify.models.DipingList;
import com.oltranz.engennotify.models.DippingNotificationEditModel;
import com.oltranz.engennotify.models.DippingNotificationModel;
import com.oltranz.engennotify.models.LowQuantityNotificationEditModel;
import com.oltranz.engennotify.models.LowQuantityNotificationModel;
import com.oltranz.engennotify.models.ResultObject;
import com.oltranz.engennotify.models.SmsModel;
import com.oltranz.engennotify.models.TankList;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@Stateless
public class NotificationManager {
    
    
    @PersistenceContext(unitName = "EngenNotificationPU")
    private EntityManager em;
    
    
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
    
    
    
    public void checkNotification(){
        
        checkDiping();
        checkLowQuantity();
        
    }
    
    
    //--------------------------------------------------------------CHECK DIPPING--------------------------------------------------------------------------------
    public void checkDiping(){
        
        List<DippingNotification> dippingNotificationList=(List<DippingNotification>)em.createQuery("SELECT d FROM DippingNotification d").getResultList();
        
        for(DippingNotification dippingNotification:dippingNotificationList){
            
            boolean period=checkDipingPeriodically(dippingNotification);
            if(period){
                branchDipingStatus(dippingNotification);
            }
            
        }
        
    }
    
    public boolean checkDipingPeriodically(DippingNotification dippingNotification){
        
        List<Object> notificationList=(List<Object>)em.createQuery("SELECT MAX(n.notifyTime) FROM Notification n where n.notificationDefId=:notificationDefId").setParameter("notificationDefId", dippingNotification.getId()).getResultList();
        
        Date maxNotifyTime=(Date)notificationList.get(0);
        if(maxNotifyTime==null){
            return true;
        }
        
        long duration  = new Date().getTime() - maxNotifyTime.getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
        
        if(diffInSeconds>=3600){
            return true;
        }
        return false;
    }
    
    public void branchDipingStatus(DippingNotification dippingNotification){
        
        Notification notification=new Notification();
        notification.setId(Long.toString(Common.shared.generateId()));
        notification.setNotificationDefId(dippingNotification.getId());
        notification.setNotificationTypeId("1");
        notification.setNotifyTime(new Date());
        em.persist(notification);
        
        List<Tank> tankList=tankList(dippingNotification.getBranchId());
        
        for(Tank t:tankList){
            
            String notify=tankDipingStatus(t,dippingNotification.getDipingTime());
            NotificationDestination notificationDestination=findNotificationDestination(dippingNotification.getId(),1);
            if(notificationDestination!=null){
                
                sendSms(notify+"Diping is not done",notificationDestination.getDest(),"DIPING");
                
            }
            
        }
        
        
    }
    
    public String tankDipingStatus(Tank t,Date dipingTime){
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        Calendar cal = Calendar.getInstance();
        String dipingDateTime=dateFormat.format(cal.getTime())+" "+timeFormat.format(dipingTime);
        
        List<Diping> dipingList=dipingList(t.getTankId(),dipingDateTime);
        
        if(dipingList.isEmpty()){
            String notify=t.getName()+"("+timeFormat.format(dipingTime)+" hr)";
            
            return notify;
        }
        
        return null;
        
    }
    
    
    
    
    
//--------------------------------------------------------------------CHECK LOW QUANTITY------------------------------------------------------------
    
    public void checkLowQuantity(){
        
        
        List<LowQuantityNotification> lowQuantityNotificationList=(List<LowQuantityNotification>)em.createQuery("SELECT l FROM LowQuantityNotification l").getResultList();
        
        for(LowQuantityNotification lowQuantityNotification:lowQuantityNotificationList){
            
            branchLowTankQuantityStatus(lowQuantityNotification);
            
        }
        
        
    }
    
    public void branchLowTankQuantityStatus(LowQuantityNotification lowQuantityNotification){
        
      
        Tank tank=tank(lowQuantityNotification.getTankId());
        String notify=tank.getName()+" Have Low Quantity "+tank.getCurrentCapacity();
        
        if(tank.getCurrentCapacity()<=lowQuantityNotification.getMinCapacity()){
            
            NotificationDestination notificationDestination=findNotificationDestination(lowQuantityNotification.getId(),2);
            if(notificationDestination!=null){
                sendSms(notify,notificationDestination.getDest(),"LOW QUANTITY");
            }
            
        }
        
       
    }
    
    
    
    //-------------------------------------------------------SEND SMS--------------------------------------------------------------------------------------
    public void sendSms(String msg,String dest,String notificationType){
        
        SmsModel smsModel=new SmsModel();
        smsModel.setSrc("engNotif");
        smsModel.setDest(dest);
        smsModel.setMessage(msg);
        smsModel.setWait(0);
        smsModel.setContractId(234224532);
        
        ObjectMapper mapper=new ObjectMapper();
        //String url="http://10.171.1.50:8080/SMSServiceProvider/sendSMS";
        
        String url="http://41.74.172.132:8080/SMSServiceProvider/sendSMS";
        
        String jsonData = null;
        
        try {
            jsonData = mapper.writeValueAsString(smsModel);
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,MediaType.APPLICATION_JSON, "POST");
        
        String jsonResponse = response.readEntity(String.class);
        
        Log log=new Log();
        log.setId(Common.shared.generateId());
        log.setNotification(msg);
        log.setNotificationType(notificationType);
        log.setDestination(dest);
        log.setDestinationType("SMS");
        log.setNotifyTime(new Date());
        em.persist(log);
        
        
        
    }
    
    
    //FIND DESTINATION
    public NotificationDestination findNotificationDestination(String notificationId,int notifyTypeId){
        
        NotificationDestination notificationDestination;
        
        try{
            notificationDestination=(NotificationDestination)em.createQuery("SELECT n FROM NotificationDestination n where n.notificationId=:notificationId and n.notifyTypeId=:notifyTypeId").setParameter("notificationId", notificationId).setParameter("notifyTypeId", notifyTypeId).getSingleResult();
        }
        catch(Exception e){
            notificationDestination=null;
        }
        return notificationDestination;
        
    }
    
    
    
    
    
    //----------------------------------------------------------------------COMMON FUNCTION-----------------------------------------
    
    
    
    
    public List<Tank> tankList(int branchId){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/tanks/"+branchId;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        List<Tank> tankList = null;
        try {
            TankList  tList=(TankList)mapper.readValue(jsonResponse, TankList.class);
            tankList=tList.getTankList();
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tankList;
    }
    
    public Tank tank(int tankId){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/getTank/"+tankId;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        Tank tank=new Tank();
        try {
            tank=(Tank)mapper.readValue(jsonResponse, Tank.class);
            
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tank;
    }
    
    public List<Diping> dipingList(int tankId,String dipingTime){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/diping/notify/"+tankId+"/"+dipingTime;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        List<Diping> dipingList = null;
        try {
            DipingList  dList=(DipingList)mapper.readValue(jsonResponse, DipingList.class);
            dipingList=dList.getDipingList();
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dipingList;
    }
    
    public Branch branch(int braId){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/BranchManagementService/branch/"+braId;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        Branch  branch = null;
        try {
            branch = (Branch)mapper.readValue(jsonResponse, Branch.class);
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return branch;
        
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ResultObject createDipingNotification(DippingNotificationEditModel dippingNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        
        
        DippingNotification dippingNotification=new DippingNotification();
        dippingNotification.setId(Long.toString(Common.shared.generateId()));
        dippingNotification.setBranchId(dippingNotificationEditModel.getBranchId());
        dippingNotification.setCreationTime(new Date());
        dippingNotification.setCreatedBy(dippingNotificationEditModel.getCreatedBy());
        try{
            dippingNotification.setDipingTime(sdf.parse(dippingNotificationEditModel.getDipingTime()));
        }
        catch(Exception ex){
            dippingNotification.setDipingTime(new Date());
        }
        em.persist(dippingNotification);
        
        resultObject.setObject(dippingNotification);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editDipingNotification(DippingNotificationEditModel dippingNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        
        DippingNotification dippingNotification=em.find(DippingNotification.class, dippingNotificationEditModel.getId());
        if(dippingNotification==null){
            resultObject.setObject(null);
            resultObject.setMessage("Id Not Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        dippingNotification.setBranchId(dippingNotificationEditModel.getBranchId());
        dippingNotification.setCreationTime(new Date());
        dippingNotification.setCreatedBy(dippingNotificationEditModel.getCreatedBy());
        try{
            dippingNotification.setDipingTime(sdf.parse(dippingNotificationEditModel.getDipingTime()));
        }
        catch(Exception ex){
            dippingNotification.setDipingTime(new Date());
        }
        
        em.merge(dippingNotification);
        
        resultObject.setObject(dippingNotification);
        resultObject.setMessage("Successfully Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject createLowQuantityNotification(LowQuantityNotificationEditModel lowQuantityNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        
        LowQuantityNotification lowQuantityNotification=new LowQuantityNotification();
        lowQuantityNotification.setId(Long.toString(Common.shared.generateId()));
        lowQuantityNotification.setBranchId(lowQuantityNotificationEditModel.getBranchId());
        lowQuantityNotification.setTankId(lowQuantityNotificationEditModel.getTankId());
        lowQuantityNotification.setMinCapacity(lowQuantityNotificationEditModel.getMinCapacity());
        lowQuantityNotification.setCreatedBy(lowQuantityNotificationEditModel.getCreatedBy());
        lowQuantityNotification.setCreationTime(new Date());
        em.persist(lowQuantityNotification);
        em.flush();
        
        resultObject.setObject(lowQuantityNotification);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editLowQuantityNotification(LowQuantityNotificationEditModel lowQuantityNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        
        LowQuantityNotification lowQuantityNotification=em.find(LowQuantityNotification.class, lowQuantityNotificationEditModel.getId());
        if(lowQuantityNotification==null){
            resultObject.setObject(null);
            resultObject.setMessage("Id Not Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        lowQuantityNotification.setBranchId(lowQuantityNotificationEditModel.getBranchId());
        lowQuantityNotification.setTankId(lowQuantityNotificationEditModel.getTankId());
        lowQuantityNotification.setMinCapacity(lowQuantityNotificationEditModel.getMinCapacity());
        lowQuantityNotification.setCreatedBy(lowQuantityNotificationEditModel.getCreatedBy());
        lowQuantityNotification.setCreationTime(new Date());
        em.merge(lowQuantityNotification);
        
        resultObject.setObject(lowQuantityNotification);
        resultObject.setMessage("Successfully Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
     public ResultObject getLog(){
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Log.class);
        
        List<Log> logList=(List<Log>)em.createQuery("SELECT l FROM Log l").getResultList();
        if(logList.size()>0){
            resultObject.setObject(logList);
            resultObject.setMessage(logList.size()+" logs found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("No logs found");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    public ResultObject getDipingNotificationList(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        List<DippingNotificationModel> dippingNotificationModelList=new ArrayList<>();
        
        List<DippingNotification> dippingNotificationList=(List<DippingNotification>)em.createQuery("SELECT d FROM DippingNotification d").getResultList();
        if(dippingNotificationList.size()>0){
            
            for(DippingNotification dippingNotification:dippingNotificationList){
                DippingNotificationModel dippingNotificationModel=new DippingNotificationModel();
                dippingNotificationModel.setId(dippingNotification.getId());
                dippingNotificationModel.setBranchName(branch(dippingNotification.getBranchId()).getBranchModel().getName());
                dippingNotificationModel.setDipingTime(dippingNotification.getDipingTime());
                dippingNotificationModel.setCreatedBy(dippingNotification.getCreatedBy());
                dippingNotificationModel.setCreationTime(dippingNotification.getCreationTime());
                dippingNotificationModel.setCheckingPeriod(dippingNotification.getCheckingPeriod());
                dippingNotificationModel.setCount(dippingNotification.getCount());
                dippingNotificationModelList.add(dippingNotificationModel);
            }
            
            resultObject.setObject(dippingNotificationModelList);
            resultObject.setMessage(dippingNotificationModelList.size()+" Dipping Notification found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("No Dipping Notification found");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    public ResultObject getLowQuantityNotificationList(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LowQuantityNotification.class);
        List<LowQuantityNotificationModel> lowQuantityNotificationModelList=new ArrayList<>();
        
        List<LowQuantityNotification> lowQuantityNotificationList=(List<LowQuantityNotification>)em.createQuery("SELECT l FROM LowQuantityNotification l").getResultList();
        if(lowQuantityNotificationList.size()>0){
            
            for(LowQuantityNotification lowQuantityNotification:lowQuantityNotificationList){
                
                LowQuantityNotificationModel lowQuantityNotificationModel=new LowQuantityNotificationModel();
                lowQuantityNotificationModel.setId(lowQuantityNotification.getId());
                lowQuantityNotificationModel.setBranchName(branch(lowQuantityNotification.getBranchId()).getBranchModel().getName());
                lowQuantityNotificationModel.setTankName(tank(lowQuantityNotification.getTankId()).getName());
                lowQuantityNotificationModel.setMinCapacity(lowQuantityNotification.getMinCapacity());
                lowQuantityNotificationModel.setCreatedBy(lowQuantityNotification.getCreatedBy());
                lowQuantityNotificationModel.setCreationTime(lowQuantityNotification.getCreationTime());
                lowQuantityNotificationModel.setCheckingPeriod(lowQuantityNotification.getCheckingPeriod());
                lowQuantityNotificationModel.setCount(lowQuantityNotification.getCount());
                lowQuantityNotificationModelList.add(lowQuantityNotificationModel);
            }
            
            resultObject.setObject(lowQuantityNotificationModelList);
            resultObject.setMessage(lowQuantityNotificationList.size()+"  LowQuantity Notification found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("No  LowQuantity Notification found");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
}
