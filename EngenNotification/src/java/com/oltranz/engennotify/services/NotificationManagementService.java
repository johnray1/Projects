/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.services;

import com.oltranz.engennotify.beans.NotificationManager;
import com.oltranz.engennotify.entities.NotificationDestination;
import com.oltranz.engennotify.library.CommonLibrary;
import com.oltranz.engennotify.models.DippingNotificationEditModel;
import com.oltranz.engennotify.models.LowQuantityNotificationEditModel;
import com.oltranz.engennotify.models.ResultObject;
import com.oltranz.engennotify.models.BizzareTxnNotificationEditModel;
import com.oltranz.engennotify.models.IdlingNotificationEditModel;
import com.oltranz.engennotify.models.Transaction;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@Path("NotificationManagementService")
@Stateless
public class NotificationManagementService {
    
    @EJB
            NotificationManager notificationManager;
    
    
    
    @POST
    @Path("dippingnotification/create")
    @Consumes({"application/xml", "application/json"})
    public String dipingNotificationCreateAndEdit(DippingNotificationEditModel dippingNotificationEditModel) {
        ResultObject result;
        
        if(dippingNotificationEditModel.getId()==null){
            result= notificationManager.createDipingNotification(dippingNotificationEditModel);
        }
        else{
            result= notificationManager.editDipingNotification(dippingNotificationEditModel);
        }
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @POST
    @Path("lowquantitynotification/create")
    @Consumes({"application/xml", "application/json"})
    public String lowQuantityNotificationCreateAndEdit(LowQuantityNotificationEditModel lowQuantityNotificationEditModel) {
        ResultObject result;
        
        if(lowQuantityNotificationEditModel.getId()==null){
            result= notificationManager.createLowQuantityNotification(lowQuantityNotificationEditModel);
        }
        else{
            result= notificationManager.editLowQuantityNotification(lowQuantityNotificationEditModel);
        }
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("idlingnotification/create")
    @Consumes({"application/xml", "application/json"})
    public String idlingNotificationCreateAndEdit(IdlingNotificationEditModel idlingNotificationEditModel) {
        ResultObject result;
        
        if(idlingNotificationEditModel.getId()==null){
            result= notificationManager.createIdlingNotification(idlingNotificationEditModel);
        }
        else{
            result= notificationManager.editIdlingNotification(idlingNotificationEditModel);
        }
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    @POST
    @Path("bizzaretxnnotification/create")
    @Consumes({"application/xml", "application/json"})
    public String bizzareTxnNotificationCreateAndEdit(BizzareTxnNotificationEditModel btxn) {
        ResultObject result;
        
        if(btxn.getId()==null){
            result= notificationManager.createBizzareTransactionNotification(btxn);
        }
        else{
            result= notificationManager.editBizzareTransactionNotification(btxn);
        }
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    
    
    
    
    
    
    @POST
    @Path("notificationdest/create")
    @Consumes({"application/xml", "application/json"})
    public String createNotificationDest(NotificationDestination notificationDestination) {
        
        ResultObject result;
        result= notificationManager.createNotificationDest(notificationDestination);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
        
    }
    
    
    
    
    
    @GET
    @Path("notifications")
    @Produces({"application/xml", "application/json"})
    public void checkNotification() {
        
        Thread t1 = new Thread(new CheckDiping());
        t1.start();
        
        Thread t2 = new Thread(new CheckQuantity());
        t2.start();
        
        Thread t3 = new Thread(new CheckIdling());
        t3.start();
        
        Thread t4 = new Thread(new CheckBizzareTransaction());
        t4.start();
    }
    
    
    class CheckDiping implements Runnable{
        
        
        
        @Override
        public void run() {
            notificationManager.checkDiping();
        }
        
    }
    
    class CheckQuantity implements Runnable{
        
        
        
        @Override
        public void run() {
            notificationManager.checkLowQuantity();
        }
        
    }
    
    class CheckIdling implements Runnable{
        
        
        
        @Override
        public void run() {
            notificationManager.checkIdlingNotification();
        }
        
    }
    
    
    
    
    class CheckBizzareTransaction implements Runnable{
        
        
        
        @Override
        public void run() {
            notificationManager.checkBizzareTransaction();
        }
        
    }
    
    
    
    
    
    
    
    @POST
    @Path("bizzarenotifications")
    public void bizzarenotifications(InputStream is) {
        
        String jsonData = null;
        try {
            jsonData = CommonLibrary.inputStream2StringTest(is);
            out.print("Time ["+(new Date())+"] - NotificationManagementService/bizzarenotifications - InPutRequest: Received string ||"+jsonData+"\n");
        }
        catch (IOException ex) {
            out.print("Time ["+(new Date())+"] - NotificationManagementService/bizzarenotifications - InPutRequest: CATCH BLOCK ||"+ex.getMessage()+"\n");
            
        }
        
        ObjectMapper mapper=new ObjectMapper();
        Transaction txn = new Transaction();
        try {
            txn = (Transaction)mapper.readValue(jsonData, Transaction.class);
        }
        catch (IOException ex) {
            out.print("Time ["+(new Date())+"] - NotificationManagementService/bizzarenotifications - MAPPING TO A OBJECT: CATCH BLOCK ||"+ex.getMessage()+"\n");
            
        }
        
        Thread t = new Thread(new CreateBizzareTransaction(txn));
        t.start();
        
        
    }
    
    
    
    class CreateBizzareTransaction implements Runnable{
        
        Transaction txn;
        
        public CreateBizzareTransaction(Transaction transaction) {
            this.txn=transaction;
        }
        
        
        
        @Override
        public void run() {
            notificationManager.createBizzareTransaction(txn);
        }
        
    }
    
    
    
    @GET
    @Path("notificationsdest/{nId}/{ntId}")
    @Produces({"application/xml", "application/json"})
    public String setUserPermisson(@PathParam("nId") String noId,@PathParam("ntId") Integer notId) {
        
        ResultObject result= notificationManager.getnotificationDestList(noId,notId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("logs")
    @Produces({"application/xml", "application/json"})
    public String getLog() {
        
        ResultObject result;
        result=notificationManager.getLog();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("dipingNotifications/{braId}")
    @Produces({"application/xml", "application/json"})
    public String getDipingNotificationList(@PathParam("braId") Integer braId) {
        
        ResultObject result;
        result=notificationManager.getDipingNotificationList(braId);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("lowQuantityNotifications/{braId}")
    @Produces({"application/xml", "application/json"})
    public String getLowQuantityNotificationList(@PathParam("braId") Integer braId) {
        
        ResultObject result;
        result=notificationManager.getLowQuantityNotificationList(braId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("idlingNotifications/{braId}")
    @Produces({"application/xml", "application/json"})
    public String getIdlingNotificationList(@PathParam("braId") Integer braId) {
        
        ResultObject result;
        result=notificationManager.getIdlingNotificationList(braId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("bizzareNotifications/{braId}")
    @Produces({"application/xml", "application/json"})
    public String getBizzareNotificationList(@PathParam("braId") Integer braId) {
        
        ResultObject result;
        result=notificationManager.getBizzareNotificationList(braId);
        return result.getJsonFormat();
    }
    
    
}
