/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.beans;

import com.oltranz.engennotify.entities.Log;
import com.oltranz.engennotify.entities.NotificationDestination;
import com.oltranz.engennotify.library.Common;
import com.oltranz.engennotify.library.CommonLibrary;
import com.oltranz.engennotify.models.SmsModel;
import java.util.Date;
import java.util.List;
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
public class SmsEmailManager {
    
    @PersistenceContext(unitName = "EngenNotificationPU")
    private EntityManager em;
    
    public void sendSms(String msg,String dest,String notificationType){
        
        SmsModel smsModel=new SmsModel();
        smsModel.setSrc("engNotif");
        smsModel.setDest(dest);
        smsModel.setMessage(msg);
        smsModel.setWait(0);
        smsModel.setContractId("54383453223");
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://10.171.1.50:8080/SMSServiceProvider/sendSMS";
        //String url="http://41.74.172.132:8080/SMSServiceProvider/sendSMS";
        
        try {
            
            String jsonData = mapper.writeValueAsString(smsModel);
            Response response = CommonLibrary.sendRESTRequest(url, jsonData,MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
        }
        catch (Exception ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Log log=new Log();
        log.setId(Common.shared.generateId());
        log.setNotification(msg);
        log.setNotificationType(notificationType);
        log.setDestination(dest);
        log.setDestinationType("SMS");
        log.setNotifyTime(new Date());
        em.persist(log);
        
    }
    
    
    //Find Notification Destination
    public List<NotificationDestination> findNotificationDestinationList(String notificationId,int notifyTypeId){
        
        List<NotificationDestination>   notificationDestinationList=(List<NotificationDestination>)em.createQuery("SELECT n FROM NotificationDestination n where n.notificationId=:notificationId and n.notifyTypeId=:notifyTypeId").setParameter("notificationId", notificationId).setParameter("notifyTypeId", notifyTypeId).getResultList();
        
        return notificationDestinationList;
        
    }
    
}
