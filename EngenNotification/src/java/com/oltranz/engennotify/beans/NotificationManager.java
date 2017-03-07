/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.beans;

import com.oltranz.engennotify.entities.BizzareTransactionNotification;
import com.oltranz.engennotify.models.Diping;
import com.oltranz.engennotify.entities.DippingNotification;
import com.oltranz.engennotify.entities.Log;
import com.oltranz.engennotify.entities.LowQuantityNotification;
import com.oltranz.engennotify.entities.Notification;
import com.oltranz.engennotify.entities.NotificationDestination;
import com.oltranz.engennotify.entities.BizzareTransaction;
import com.oltranz.engennotify.entities.IdlingNotification;
import com.oltranz.engennotify.library.Common;
import com.oltranz.engennotify.models.BizzareTxnNotificationEditModel;
import com.oltranz.engennotify.models.BizzareTxnNotificationModel;
import com.oltranz.engennotify.models.TankModel;
import com.oltranz.engennotify.models.DippingNotificationEditModel;
import com.oltranz.engennotify.models.DippingNotificationModel;
import com.oltranz.engennotify.models.IdlingNotificationEditModel;
import com.oltranz.engennotify.models.IdlingNotificationModel;
import com.oltranz.engennotify.models.LowQuantityNotificationEditModel;
import com.oltranz.engennotify.models.LowQuantityNotificationModel;
import com.oltranz.engennotify.models.ResultObject;
import com.oltranz.engennotify.models.Transaction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author John
 */
@Stateless
public class NotificationManager {
    
    
    @PersistenceContext(unitName = "EngenNotificationPU")
    private EntityManager em;
    
    @EJB
            EngenPayFuelCommunicateManager epc;
    
    @EJB
            SmsEmailManager sem;
    
    SimpleDateFormat ymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
    
    SimpleDateFormat hm=new SimpleDateFormat("HH:mm");
    
    
//--------------------------------------------------Diping Notification----------------------------------------------------------
    
    public ResultObject createDipingNotification(DippingNotificationEditModel dippingNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        
        
        DippingNotification dippingNotification=new DippingNotification();
        dippingNotification.setId(Long.toString(Common.shared.generateId()));
        dippingNotification.setBranchId(dippingNotificationEditModel.getBranchId());
        dippingNotification.setCreationTime(new Date());
        dippingNotification.setCreatedBy(dippingNotificationEditModel.getCreatedBy());
        dippingNotification.setNotificationTypeId(1);
        try{
            dippingNotification.setDipingTime(hm.parse(dippingNotificationEditModel.getDipingTime()));
        }
        catch(Exception ex){
            dippingNotification.setDipingTime(new Date());
        }
        
        dippingNotification.setCheckingPeriod(dippingNotificationEditModel.getCheckingPeriod());
        dippingNotification.setCount(dippingNotificationEditModel.getCount());
        dippingNotification.setCheckCount(dippingNotificationEditModel.getCount());
        dippingNotification.setLastNotifyTime(new Date());
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
            dippingNotification.setDipingTime(hm.parse(dippingNotificationEditModel.getDipingTime()));
        }
        catch(Exception ex){
            dippingNotification.setDipingTime(new Date());
        }
        dippingNotification.setCheckingPeriod(dippingNotificationEditModel.getCheckingPeriod());
        dippingNotification.setCount(dippingNotificationEditModel.getCount());
        dippingNotification.setCheckCount(dippingNotificationEditModel.getCount());
        em.merge(dippingNotification);
        
        resultObject.setObject(dippingNotification);
        resultObject.setMessage("Successfully Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject getDipingNotificationList(int branchId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DippingNotification.class);
        List<DippingNotificationModel> dippingNotificationModelList=new ArrayList<>();
        List<DippingNotification> dippingNotificationList;
        if(branchId==0){
            dippingNotificationList=(List<DippingNotification>)em.createQuery("SELECT d FROM DippingNotification d").getResultList();
        }
        else{
            dippingNotificationList=(List<DippingNotification>)em.createQuery("SELECT d FROM DippingNotification d where d.branchId=:branchId").setParameter("branchId", branchId).getResultList();
        }
        if(dippingNotificationList.size()>0){
            
            for(DippingNotification dippingNotification:dippingNotificationList){
                DippingNotificationModel dippingNotificationModel=new DippingNotificationModel();
                dippingNotificationModel.setId(dippingNotification.getId());
                dippingNotificationModel.setBranchId(dippingNotification.getBranchId());
                dippingNotificationModel.setBranchName(epc.branch(dippingNotification.getBranchId()).getBranchModel().getName());
                dippingNotificationModel.setDipingTime(dippingNotification.getDipingTime());
                dippingNotificationModel.setCreatedBy(dippingNotification.getCreatedBy());
                dippingNotificationModel.setCreationTime(dippingNotification.getCreationTime());
                dippingNotificationModel.setCheckingPeriod(dippingNotification.getCheckingPeriod());
                dippingNotificationModel.setCount(dippingNotification.getCount());
                dippingNotificationModel.setNotificationTypeId(dippingNotification.getNotificationTypeId());
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
    
    
    
    
//--------------------------------------------------Low Quantity Notification----------------------------------------------------------
    
    public ResultObject createLowQuantityNotification(LowQuantityNotificationEditModel lowQuantityNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LowQuantityNotification.class);
        
        LowQuantityNotification lowQuantityNotification=new LowQuantityNotification();
        lowQuantityNotification.setId(Long.toString(Common.shared.generateId()));
        lowQuantityNotification.setBranchId(lowQuantityNotificationEditModel.getBranchId());
        lowQuantityNotification.setTankId(lowQuantityNotificationEditModel.getTankId());
        lowQuantityNotification.setMinCapacity(lowQuantityNotificationEditModel.getMinCapacity());
        lowQuantityNotification.setCreatedBy(lowQuantityNotificationEditModel.getCreatedBy());
        lowQuantityNotification.setCreationTime(new Date());
        lowQuantityNotification.setCheckingPeriod(lowQuantityNotificationEditModel.getCheckingPeriod());
        lowQuantityNotification.setCount(lowQuantityNotificationEditModel.getCount());
        lowQuantityNotification.setCheckCount(lowQuantityNotificationEditModel.getCount());
        lowQuantityNotification.setNotificationTypeId(2);
        lowQuantityNotification.setLastNotifyTime(new Date());
        em.persist(lowQuantityNotification);
        em.flush();
        
        resultObject.setObject(lowQuantityNotification);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editLowQuantityNotification(LowQuantityNotificationEditModel lowQuantityNotificationEditModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LowQuantityNotification.class);
        
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
        lowQuantityNotification.setCheckingPeriod(lowQuantityNotificationEditModel.getCheckingPeriod());
        lowQuantityNotification.setCount(lowQuantityNotificationEditModel.getCount());
        lowQuantityNotification.setCheckCount(lowQuantityNotificationEditModel.getCount());
        em.merge(lowQuantityNotification);
        
        resultObject.setObject(lowQuantityNotification);
        resultObject.setMessage("Successfully Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject getLowQuantityNotificationList(int branchId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LowQuantityNotification.class);
        List<LowQuantityNotificationModel> lowQuantityNotificationModelList=new ArrayList<>();
        List<LowQuantityNotification> lowQuantityNotificationList;
        if(branchId==0){
            lowQuantityNotificationList=(List<LowQuantityNotification>)em.createQuery("SELECT l FROM LowQuantityNotification l ").getResultList();
        }
        else{
            lowQuantityNotificationList=(List<LowQuantityNotification>)em.createQuery("SELECT l FROM LowQuantityNotification l where l.branchId=:branchId").setParameter("branchId", branchId).getResultList();
            
        }
        
        if(lowQuantityNotificationList.size()>0){
            
            for(LowQuantityNotification lowQuantityNotification:lowQuantityNotificationList){
                
                LowQuantityNotificationModel lowQuantityNotificationModel=new LowQuantityNotificationModel();
                lowQuantityNotificationModel.setId(lowQuantityNotification.getId());
                lowQuantityNotificationModel.setBranchId(lowQuantityNotification.getBranchId());
                lowQuantityNotificationModel.setBranchName(epc.branch(lowQuantityNotification.getBranchId()).getBranchModel().getName());
                lowQuantityNotificationModel.setTankId(lowQuantityNotification.getTankId());
                lowQuantityNotificationModel.setTankName(epc.tank(lowQuantityNotification.getTankId()).getName());
                lowQuantityNotificationModel.setMinCapacity(lowQuantityNotification.getMinCapacity());
                lowQuantityNotificationModel.setCreatedBy(lowQuantityNotification.getCreatedBy());
                lowQuantityNotificationModel.setCreationTime(lowQuantityNotification.getCreationTime());
                lowQuantityNotificationModel.setCheckingPeriod(lowQuantityNotification.getCheckingPeriod());
                lowQuantityNotificationModel.setCount(lowQuantityNotification.getCount());
                lowQuantityNotificationModel.setNotificationTypeId(lowQuantityNotification.getNotificationTypeId());
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
    
    
    
    
    
    //-------------------------------------------------- Idling Notification----------------------------------------------------------
    
    
    public ResultObject createIdlingNotification(IdlingNotificationEditModel inm){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(IdlingNotification.class);
        
        IdlingNotification in=new IdlingNotification();
        in.setId(Long.toString(Common.shared.generateId()));
        in.setBranchId(inm.getBranchId());
        in.setNozzleId(inm.getNozzleId());
        in.setMaxIdlingTime(inm.getMaxIdlingTime());
        in.setCreationTime(new Date());
        in.setCreatedBy(inm.getCreatedBy());
        in.setNotificationTypeId(3);
        in.setCheckingPeriod(inm.getCheckingPeriod());
        in.setCount(inm.getCount());
        in.setCheckCount(inm.getCount());
        in.setLastNotifyTime(new Date());
        em.persist(in);
        em.flush();
        
        
        resultObject.setObject(in);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editIdlingNotification(IdlingNotificationEditModel inm){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LowQuantityNotification.class);
        
        IdlingNotification in=em.find(IdlingNotification.class, inm.getId());
        if(in==null){
            resultObject.setObject(null);
            resultObject.setMessage("Id Not Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        in.setBranchId(inm.getBranchId());
        in.setNozzleId(inm.getNozzleId());
        in.setMaxIdlingTime(inm.getMaxIdlingTime());
        in.setCreationTime(new Date());
        in.setCreatedBy(inm.getCreatedBy());
        in.setNotificationTypeId(3);
        in.setCheckingPeriod(inm.getCheckingPeriod());
        in.setCount(inm.getCount());
        in.setCheckCount(inm.getCount());
        
        resultObject.setObject(in);
        resultObject.setMessage("Successfully Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getIdlingNotificationList(int branchId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(IdlingNotification.class);
        List<IdlingNotificationModel> idlingNotificationModelList=new ArrayList<>();
        List<IdlingNotification> idlingNotificationList;
        if(branchId==0){
            idlingNotificationList=(List<IdlingNotification>)em.createQuery("SELECT i FROM IdlingNotification i").getResultList();
        }
        else{
            idlingNotificationList=(List<IdlingNotification>)em.createQuery("SELECT i FROM IdlingNotification i where i.branchId=:branchId").setParameter("branchId", branchId).getResultList();
        }
        if(idlingNotificationList.size()>0){
            
            for(IdlingNotification idlingNotification:idlingNotificationList){
                
                IdlingNotificationModel idlingNotificationModel=new IdlingNotificationModel();
                idlingNotificationModel.setId(idlingNotification.getId());
                idlingNotificationModel.setBranchId(idlingNotification.getBranchId());
                idlingNotificationModel.setBranchName(epc.branch(idlingNotification.getBranchId()).getBranchModel().getName());
                idlingNotificationModel.setNozzleId(idlingNotification.getNozzleId());
                idlingNotificationModel.setNozzlename("Nozzle");
                idlingNotificationModel.setMaxIdlingTime(idlingNotification.getMaxIdlingTime());
                idlingNotificationModel.setLastUsedTime(idlingNotification.getLastUsedTime());
                idlingNotificationModel.setCreatedBy(idlingNotification.getCreatedBy());
                idlingNotificationModel.setCreationTime(idlingNotification.getCreationTime());
                idlingNotificationModel.setCheckingPeriod(idlingNotification.getCheckingPeriod());
                idlingNotificationModel.setCount(idlingNotification.getCount());
                idlingNotificationModel.setNotificationTypeId(idlingNotification.getNotificationTypeId());
                idlingNotificationModelList.add(idlingNotificationModel);
            }
            
            resultObject.setObject(idlingNotificationModelList);
            resultObject.setMessage(idlingNotificationModelList.size()+"  Idling Notification found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("No  Idling Notification found");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    
    
    //-------------------------------------------------- Bizzare Transaction Notification----------------------------------------------------------
    
    public ResultObject createBizzareTransactionNotification(BizzareTxnNotificationEditModel btxn){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BizzareTransactionNotification.class);
        
        BizzareTransactionNotification btn=new BizzareTransactionNotification();
        btn.setId(Long.toString(Common.shared.generateId()));
        btn.setBranchId(btxn.getBranchId());
        btn.setMaxAmount(btxn.getMaxAmount());
        btn.setMaxQuantity(btxn.getMaxQuantity());
        btn.setCreationTime(new Date());
        btn.setCreatedBy(btxn.getCreatedBy());
        btn.setNotificationTypeId(4);
        btn.setCheckingPeriod(btxn.getCheckingPeriod());
        btn.setCount(1);
        btn.setCheckCount(1);
        
        em.persist(btn);
        em.flush();
        
        
        resultObject.setObject(btn);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editBizzareTransactionNotification(BizzareTxnNotificationEditModel btxn){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BizzareTransactionNotification.class);
        
        BizzareTransactionNotification btn=em.find(BizzareTransactionNotification.class, btxn.getId());
        if(btn==null){
            resultObject.setObject(null);
            resultObject.setMessage("Id Not Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        btn.setBranchId(btxn.getBranchId());
        btn.setMaxAmount(btxn.getMaxAmount());
        btn.setMaxQuantity(btxn.getMaxQuantity());
        btn.setCreationTime(new Date());
        btn.setCreatedBy(btxn.getCreatedBy());
        btn.setNotificationTypeId(4);
        btn.setCheckingPeriod(btxn.getCheckingPeriod());
        btn.setCount(1);
        btn.setCheckCount(1);
        em.merge(btn);
        
        resultObject.setObject(btn);
        resultObject.setMessage("Successfully Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getBizzareNotificationList(int branchId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BizzareTransactionNotification.class);
        
        List<BizzareTxnNotificationModel> bizzareTxnNotificationModelList=new ArrayList<>();
        List<BizzareTransactionNotification> bizzareTransactionNotificationList;
        if(branchId==0){
            bizzareTransactionNotificationList=(List<BizzareTransactionNotification>)em.createQuery("SELECT b FROM BizzareTransactionNotification b").getResultList();
        }
        else{
            bizzareTransactionNotificationList=(List<BizzareTransactionNotification>)em.createQuery("SELECT b FROM BizzareTransactionNotification b where b.branchId=:branchId").setParameter("branchId", branchId).getResultList();
        }
        
        if(bizzareTransactionNotificationList.size()>0){
            
            for(BizzareTransactionNotification bizzareTransactionNotification:bizzareTransactionNotificationList){
                
                BizzareTxnNotificationModel bizzareTxnNotificationModel=new BizzareTxnNotificationModel();
                bizzareTxnNotificationModel.setId(bizzareTransactionNotification.getId());
                bizzareTxnNotificationModel.setBranchId(bizzareTransactionNotification.getBranchId());
                bizzareTxnNotificationModel.setBranchName(epc.branch(bizzareTransactionNotification.getBranchId()).getBranchModel().getName());
                bizzareTxnNotificationModel.setMaxAmount(bizzareTransactionNotification.getMaxAmount());
                bizzareTxnNotificationModel.setMaxQuantity(bizzareTransactionNotification.getMaxQuantity());
                bizzareTxnNotificationModel.setCreatedBy(bizzareTransactionNotification.getCreatedBy());
                bizzareTxnNotificationModel.setCreationTime(bizzareTransactionNotification.getCreationTime());
                bizzareTxnNotificationModel.setCheckingPeriod(bizzareTransactionNotification.getCheckingPeriod());
                bizzareTxnNotificationModel.setCount(bizzareTransactionNotification.getCount());
                bizzareTxnNotificationModel.setNotificationTypeId(bizzareTransactionNotification.getNotificationTypeId());
                bizzareTxnNotificationModelList.add(bizzareTxnNotificationModel);
            }
            
            resultObject.setObject(bizzareTxnNotificationModelList);
            resultObject.setMessage(bizzareTxnNotificationModelList.size()+"  Bizzare Notification found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("No  Idling Notification found");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    
    
    
    
    //---------------------------------------------------------Logs----------------------------------------------------------------------
    
    
    
    
    
    
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
    
    
    
    
    
    
    public ResultObject getnotificationDestList(String notificationId,int notifyTypeId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(NotificationDestination.class);
        
        List<NotificationDestination> notificationDestinationList=(List<NotificationDestination>)em.createQuery("SELECT n FROM NotificationDestination n where n.notificationId=:notificationId and n.notifyTypeId=:notifyTypeId").setParameter("notificationId", notificationId).setParameter("notifyTypeId", notifyTypeId).getResultList();
        if(notificationDestinationList.size()>0){
            resultObject.setObject(notificationDestinationList);
            resultObject.setMessage(notificationDestinationList.size()+" Notification Destination found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("No Dipping Notification found");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    
    
    
    //-------------------------------------------------------------Keep Bizzare Txn And Set Last Time of Nozzle----------------------------------------------------
    public ResultObject createBizzareTransaction(Transaction tx){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BizzareTransaction.class);
        
        
        List<BizzareTransactionNotification> bizzareTransactionNotificationList=(List<BizzareTransactionNotification>)em.createQuery("select b from BizzareTransactionNotification b where b.branchId=:branchId").setParameter("branchId", tx.getBranchId()).getResultList();
        BizzareTransaction bt=new BizzareTransaction();
        if(bizzareTransactionNotificationList.size()>0){
            if( (tx.getAmount()>=bizzareTransactionNotificationList.get(0).getMaxAmount()) || (tx.getQuantity()>=bizzareTransactionNotificationList.get(0).getMaxQuantity()) ){
                bt.setAmount(tx.getAmount());
                bt.setBranchId(tx.getBranchId());
                bt.setCount(0);
                bt.setCustomerId(tx.getCustomerId());
                bt.setDate(tx.getDate());
                bt.setDeviceId(tx.getDeviceId());
                bt.setDeviceTransactionId(tx.getDeviceTransactionId());
                bt.setDeviceTransactionTime(tx.getDeviceTransactionTime());
                bt.setNozzleId(tx.getNozzleId());
                bt.setPaymentModeId(tx.getPaymentModeId());
                bt.setPaymentStatus(tx.getPaymentStatus());
                bt.setPlatenumber(tx.getPlatenumber());
                bt.setProductId(tx.getProductId());
                bt.setPumpId(tx.getPumpId());
                bt.setQuantity(tx.getQuantity());
                bt.setServerReqTime(tx.getServerReqTime());
                bt.setServerResTime(tx.getServerResTime());
                bt.setTankId(tx.getTankId());
                bt.setTransactionId(tx.getTransactionId());
                bt.setUserId(tx.getUserId());
                em.persist(bt);
            }
        }
        createNozzleLatUsed(tx);
        
        resultObject.setObject(bt);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject createNozzleLatUsed(Transaction tx){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(IdlingNotification.class);
        
        List<IdlingNotification> idlingNotificationList=(List<IdlingNotification>)em.createQuery("select i from IdlingNotification i where i.nozzleId=:nozzleId").setParameter("nozzleId", tx.getNozzleId()).getResultList();
        IdlingNotification idlingNotification=new IdlingNotification();
        if(idlingNotificationList.size()>0){
            idlingNotification=idlingNotificationList.get(0);
            idlingNotification.setLastUsedTime(new Date());
            em.merge(idlingNotification);
        }
        
        resultObject.setObject(idlingNotification);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    
    
//-------------------------------------------------------Notification Dest----------------------------------------------------
    
    public ResultObject createNotificationDest(NotificationDestination notificationDestination){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(NotificationDestination.class);
        
        
        em.persist(notificationDestination);
        em.flush();
        
        resultObject.setObject(notificationDestination);
        resultObject.setMessage("Successfully Created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    
    
    
//--------------------------------------------------Notification Start----------------------------------------------------------
    
    
    
//--------------------------------------------------------------CHECK DIPPING--------------------------------------------------------------------------------
    public void checkDiping(){
        
        List<DippingNotification> dippingNotificationList=(List<DippingNotification>)em.createQuery("SELECT d FROM DippingNotification d where d.dipingTime<=:dipingTime").setParameter("dipingTime", new Date(),TemporalType.TIME).getResultList();
        
        if(dippingNotificationList.size()>0){
            
            for(DippingNotification dippingNotification:dippingNotificationList){
                
                boolean period=checkDipingPeriodically(dippingNotification);
                
                if(period){
                    branchDipingStatus(dippingNotification);
                }
                
            }
            
        }
        
    }
    
    public boolean checkDipingPeriodically(DippingNotification dippingNotification){
        
        String lastNotifyDate=ymd.format(dippingNotification.getLastNotifyTime());
        String currentDate=ymd.format(new Date());
        
        if(!lastNotifyDate.equals(currentDate)){
            
            dippingNotification.setCheckCount(dippingNotification.getCount());
            em.merge(dippingNotification);
            
        }
        
        //check count
        if(dippingNotification.getCheckCount()==0){
            return false;
        }
        
        //check period
        List<Object> notificationList=(List<Object>)em.createQuery("SELECT MAX(n.notifyTime) FROM Notification n where n.notificationDefId=:notificationDefId and n.notificationTypeId=:notificationTypeId").setParameter("notificationDefId", dippingNotification.getId()).setParameter("notificationTypeId", dippingNotification.getNotificationTypeId()).getResultList();
        
        Date maxNotifyTime=(Date)notificationList.get(0);
        if(maxNotifyTime==null){
            return true;
        }
        
        long duration  = new Date().getTime() - maxNotifyTime.getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        
        if(diffInSeconds>=dippingNotification.getCheckingPeriod()){
            return true;
        }
        
        
        return false;
    }
    
    public void branchDipingStatus(DippingNotification dippingNotification){
        
        //Get Tanllist According To branchId
        List<TankModel> tankList=epc.tankList(dippingNotification.getBranchId());
        if(tankList.size()>0){
            
            for(TankModel t:tankList){
                
                String notify=branchTankDipingStatus(t,dippingNotification);
                
                List<NotificationDestination>   notificationDestinationList=sem.findNotificationDestinationList(dippingNotification.getId(),dippingNotification.getNotificationTypeId());
                if(notificationDestinationList.size()>0){
                    
                    for(NotificationDestination notificationDestination:notificationDestinationList){
                        
                        sem.sendSms(notify,notificationDestination.getDest(),"DIPING");
                        
                    }
                }
                
            }
        }
        
        
        
        
        
    }
    
    public String branchTankDipingStatus(TankModel t,DippingNotification dippingNotification){
        
        Date dipingTime=dippingNotification.getDipingTime();
        
        
        
        
        Calendar cal = Calendar.getInstance();
        String dipingDateTime=ymd.format(cal.getTime())+" "+hm.format(dipingTime);
        
        List<Diping> dipingList=epc.dipingList(t.getTankId(),dipingDateTime);
        
        if(dipingList.isEmpty()){
            
            //Set Notification
            Notification notification=new Notification();
            notification.setId(Long.toString(Common.shared.generateId()));
            notification.setNotificationDefId(dippingNotification.getId());
            notification.setNotificationTypeId(dippingNotification.getNotificationTypeId());
            notification.setNotifyTime(new Date());
            em.persist(notification);
            em.flush();
            
            
            
            //Update Diping Notification and update checkcount and notifytime
            int preCheckCount=dippingNotification.getCheckCount();
            if(preCheckCount>0){
                dippingNotification.setCheckCount(preCheckCount-1);
                dippingNotification.setLastNotifyTime(notification.getNotifyTime());
                em.merge(dippingNotification);
            }
            
            String notify="Late Dip\n\nDear "+epc.branch(dippingNotification.getBranchId()).getBranchModel().getName()+"dealer,\n to let you know that dip hasn't been performed for "+t.getName();
            
            return notify;
        }
        
        return null;
        
    }
    
    
    
//--------------------------------------------------------------CHECK LOW QUANTITY--------------------------------------------------------------------------------
    
    
    public void checkLowQuantity(){
        
        
        List<LowQuantityNotification> lowQuantityNotificationList=(List<LowQuantityNotification>)em.createQuery("SELECT l FROM LowQuantityNotification l").getResultList();
        
        if(lowQuantityNotificationList.size()>0){
            for(LowQuantityNotification lowQuantityNotification:lowQuantityNotificationList){
                
                boolean period=checkLowQuantityPeriodically(lowQuantityNotification);
                
                if(period){
                    
                    lowTankQuantityStatus(lowQuantityNotification);
                }
                
            }
        }
        
    }
    
    public boolean checkLowQuantityPeriodically(LowQuantityNotification lowQuantityNotification){
        
        String lastNotifyDate=ymd.format(lowQuantityNotification.getLastNotifyTime());
        String currentDate=ymd.format(new Date());
        
        if( lowQuantityNotification.getCheckCount()==0 && (!lastNotifyDate.equals(currentDate)) ){
            
            lowQuantityNotification.setCheckCount(lowQuantityNotification.getCount());
            em.merge(lowQuantityNotification);
            
        }
        
        //check count
        if(lowQuantityNotification.getCheckCount()==0){
            return false;
        }
        
        //check period
        List<Object> notificationList=(List<Object>)em.createQuery("SELECT MAX(n.notifyTime) FROM Notification n where n.notificationDefId=:notificationDefId and n.notificationTypeId=:notificationTypeId").setParameter("notificationDefId", lowQuantityNotification.getId()).setParameter("notificationTypeId", lowQuantityNotification.getNotificationTypeId()).getResultList();
        
        Date maxNotifyTime=(Date)notificationList.get(0);
        if(maxNotifyTime==null){
            return true;
        }
        
        long duration  = new Date().getTime() - maxNotifyTime.getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        
        if(diffInSeconds>=lowQuantityNotification.getCheckingPeriod()){
            return true;
        }
        
        
        return false;
    }
    
    public void lowTankQuantityStatus(LowQuantityNotification lowQuantityNotification){
        
        
        
        TankModel tank=epc.tank(lowQuantityNotification.getTankId());
        
        if(  (tank!=null) && (tank.getCurrentCapacity()<lowQuantityNotification.getMinCapacity())  ){
            
            //Set Notification
            Notification notification=new Notification();
            notification.setId(Long.toString(Common.shared.generateId()));
            notification.setNotificationDefId(lowQuantityNotification.getId());
            notification.setNotificationTypeId(lowQuantityNotification.getNotificationTypeId());
            notification.setNotifyTime(new Date());
            em.persist(notification);
            em.flush();
            
            
            
            //Update Low Quantity Notification and update checkcount and notifytime
            int preCheckCount=lowQuantityNotification.getCheckCount();
            if(preCheckCount>0){
                lowQuantityNotification.setCheckCount(preCheckCount-1);
                lowQuantityNotification.setLastNotifyTime(notification.getNotifyTime());
                em.merge(lowQuantityNotification);
            }
            String productName = null;
            if(tank.getProductId()==1){
                productName="SUPER";
            }
            
            if(tank.getProductId()==2){
                productName="GASOIL";
            }
            
            if(tank.getProductId()==3){
                productName="KEROSENE";
            }
            
            String notify;
            if(tank.getCurrentCapacity()<0){
                
                notify="Low tank quantity\n\nDear "+epc.branch(lowQuantityNotification.getBranchId()).getBranchModel().getName()+" dealer,\n to let you know that it is been long time without recording dipping or tanking on the"+ tank.getName()+". Thank you!";
            }
            else{
                notify="Low tank quantity\n\nDear "+epc.branch(lowQuantityNotification.getBranchId()).getBranchModel().getName()+" dealer,\n to let you know that the "+tank.getName()+" is nearly under "+lowQuantityNotification.getMinCapacity()+".\n Please plan to place a new order. Thank you!";
            }
            
            List<NotificationDestination>   notificationDestinationList=sem.findNotificationDestinationList(lowQuantityNotification.getId(),lowQuantityNotification.getNotificationTypeId());
            
            if(notificationDestinationList.size()>0){
                
                for(NotificationDestination notificationDestination:notificationDestinationList){
                    
                    sem.sendSms(notify,notificationDestination.getDest(),"LOW QUANTITY");
                    
                }
            }
        }
    }
    
    
    //--------------------------------------------------------------CHECK Bizzare BizzareTransaction--------------------------------------------------------------------------------
    
    
    public void checkBizzareTransaction(){
        
        
        List<BizzareTransactionNotification> bizzareTransactionNotificationList=(List<BizzareTransactionNotification>)em.createQuery("SELECT b FROM BizzareTransactionNotification b").getResultList();
        
        if(bizzareTransactionNotificationList.size()>0){
            for(BizzareTransactionNotification bizzareTransactionNotification:bizzareTransactionNotificationList){
                
                boolean period=checkBizzareTransactionPeriodically(bizzareTransactionNotification);
                
                if(period){
                    
                    bizzareTransactionStatus(bizzareTransactionNotification);
                }
                
            }
        }
        
    }
    
    public boolean checkBizzareTransactionPeriodically(BizzareTransactionNotification bizzareTransactionNotification){
        
        //check count
        if(bizzareTransactionNotification.getCheckCount()>0){
            return true;
        }
        
        return false;
    }
    
    public void bizzareTransactionStatus(BizzareTransactionNotification btn){
        
        //Set Notification
        Notification notification=new Notification();
        notification.setId(Long.toString(Common.shared.generateId()));
        notification.setNotificationDefId(btn.getId());
        notification.setNotificationTypeId(btn.getNotificationTypeId());
        notification.setNotifyTime(new Date());
        em.persist(notification);
        em.flush();
        
        //Update Diping Notification and update checkcount and notifytime
        int preCheckCount=btn.getCheckCount();
        if(preCheckCount>0){
            btn.setCheckCount(preCheckCount-1);
            btn.setLastNotifyTime(notification.getNotifyTime());
            em.merge(btn);
        }
        
        
        List<BizzareTransaction> bizzareTransactionList=(List<BizzareTransaction>)em.createQuery("SELECT t FROM BizzareTransaction t where t.branchId=:branchId and t.count=:count and (t.quantity>=:quantity or t.amount>=:amount)").setParameter("branchId", btn.getBranchId()).setParameter("count", 0).setParameter("quantity", btn.getMaxQuantity()).setParameter("amount", btn.getMaxAmount()).getResultList();
        
        if(bizzareTransactionList.size()>0){
            
            for(BizzareTransaction txn:bizzareTransactionList){
                txn.setCount(1);
                em.merge(txn);
                
                String notify="This is to notify you that there is a transaction of ["+txn.getAmount()+"/"+txn.getQuantity()+"]it may be a mistake.\n Please Follow up. Thank you!";
                
                List<NotificationDestination>   notificationDestinationList=sem.findNotificationDestinationList(btn.getId(),btn.getNotificationTypeId());
                
                if(notificationDestinationList.size()>0){
                    
                    for(NotificationDestination notificationDestination:notificationDestinationList){
                        
                        sem.sendSms(notify,notificationDestination.getDest(),"Bizzare Transaction");
                        
                    }
                }
                
            }
        }
    }
    
    
    
    //--------------------------------------------------------------CHECK IdlingNotification--------------------------------------------------------------------------------
    
    
    
    public void checkIdlingNotification(){
        
        
        List<IdlingNotification> idlingNotificationList=(List<IdlingNotification>)em.createQuery("SELECT i FROM IdlingNotification i").getResultList();
        
        if(idlingNotificationList.size()>0){
            for(IdlingNotification idlingNotification:idlingNotificationList){
                
                boolean period=checkIdlingPeriodically(idlingNotification);
                
                if(period){
                    idlingNozzleStatus(idlingNotification);
                    
                }
                
            }
        }
        
    }
    
    
    
    public boolean checkIdlingPeriodically(IdlingNotification idlingNotification){
        
        String lastNotifyDate=ymd.format(idlingNotification.getLastNotifyTime());
        String currentDate=ymd.format(new Date());
        
        if( idlingNotification.getCheckCount()==0 && (!lastNotifyDate.equals(currentDate)) ){
            
            idlingNotification.setCheckCount(idlingNotification.getCount());
            em.merge(idlingNotification);
            
        }
        
        //check count
        if(idlingNotification.getCheckCount()==0){
            return false;
        }
        
        //check period
        List<Object> notificationList=(List<Object>)em.createQuery("SELECT MAX(n.notifyTime) FROM Notification n where n.notificationDefId=:notificationDefId and n.notificationTypeId=:notificationTypeId").setParameter("notificationDefId", idlingNotification.getId()).setParameter("notificationTypeId", idlingNotification.getNotificationTypeId()).getResultList();
        
        Date maxNotifyTime=(Date)notificationList.get(0);
        if(maxNotifyTime==null){
            return true;
        }
        
        long duration  = new Date().getTime() - maxNotifyTime.getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        
        if(diffInSeconds>=idlingNotification.getCheckingPeriod()){
            return true;
        }
        
        
        return false;
    }
    
    
    
    
    
    public void idlingNozzleStatus(IdlingNotification idlingNotification){
        
        //Set Notification
        Notification notification=new Notification();
        notification.setId(Long.toString(Common.shared.generateId()));
        notification.setNotificationDefId(idlingNotification.getId());
        notification.setNotificationTypeId(idlingNotification.getNotificationTypeId());
        notification.setNotifyTime(new Date());
        em.persist(notification);
        em.flush();
        
        
        
        //Update Low Quantity Notification and update checkcount and notifytime
        int preCheckCount=idlingNotification.getCheckCount();
        if(preCheckCount>0){
            idlingNotification.setCheckCount(preCheckCount-1);
            idlingNotification.setLastNotifyTime(notification.getNotifyTime());
            em.merge(idlingNotification);
        }
        
        long duration  = new Date().getTime() - idlingNotification.getLastUsedTime().getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        
        if(diffInSeconds>=idlingNotification.getMaxIdlingTime()){
            String notify="This is to notify you that the  [Nozzle "+idlingNotification.getNozzleId()+"]with no activity.\n Please Follow up. Thank you!";
            
            List<NotificationDestination>   notificationDestinationList=sem.findNotificationDestinationList(idlingNotification.getId(),idlingNotification.getNotificationTypeId());
            
            if(notificationDestinationList.size()>0){
                
                for(NotificationDestination notificationDestination:notificationDestinationList){
                    
                    sem.sendSms(notify,notificationDestination.getDest(),"Idling Nozzle");
                    
                }
            }
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
