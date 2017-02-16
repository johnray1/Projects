/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.services;

import com.oltranz.engennotify.beans.NotificationManager;
import com.oltranz.engennotify.models.DippingNotificationEditModel;
import com.oltranz.engennotify.models.LowQuantityNotificationEditModel;
import com.oltranz.engennotify.models.ResultObject;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    
    
    @GET
    @Path("notifications")
    @Produces({"application/xml", "application/json"})
    public void checkNotification() {
        notificationManager.checkNotification();
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
    @Path("dipingNotifications")
    @Produces({"application/xml", "application/json"})
    public String getDipingNotificationList() {
        
        ResultObject result;
        result=notificationManager.getDipingNotificationList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("lowQuantityNotifications")
    @Produces({"application/xml", "application/json"})
    public String getLowQuantityNotificationList() {
        
        ResultObject result;
        result=notificationManager.getLowQuantityNotificationList();
        return result.getJsonFormat();
    }
}
