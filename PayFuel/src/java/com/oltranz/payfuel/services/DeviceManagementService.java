/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.DeviceManager;
import com.oltranz.payfuel.entities.Device;
import com.oltranz.payfuel.models.DeviceRegistrationModel;
import com.oltranz.payfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author John
 */
@Path("DeviceManagementService")
@Stateless
public class DeviceManagementService {
    
    
    @EJB
    DeviceManager deviceManager;
    
    @POST
    @Path("device/create")
    @Consumes({"application/xml", "application/json"})
    public String createDevice(Device newDevice) {
        
        ResultObject result=deviceManager.createDevice(newDevice);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("device/register")
    @Consumes({"application/xml", "application/json"})
    public String createSellingDevice(DeviceRegistrationModel regDeviceModel) {
        ResultObject result= deviceManager.registerSellingDevice(regDeviceModel);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("device/edit")
    @Consumes({"application/xml", "application/json"})
    public String editDevice(Device editDevice) {
        
        ResultObject result=deviceManager.editDevice(editDevice);
        return result.getJsonFormat();
        
    }
    
    @GET
    @Path("devices")
    @Produces({"application/xml", "application/json"})
    public String getDeviceList() {
        
        ResultObject result= deviceManager.getDeviceList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("devices/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getDeviceListByUserId(@PathParam("userId") Integer userId) {
        
        ResultObject result= deviceManager.getDeviceList(userId);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("device/{id}")
    @Produces({"application/xml", "application/json"})
    public String getDeviceByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= deviceManager.getDeviceByItsId(id);
        return result.getJsonFormat();
    }
    
    
    @POST
    @Path("device/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removeDevice(@PathParam("id") Integer id) {
        ResultObject result= deviceManager.deleteDevice(id);
        return result.getJsonFormat();
    }
    
}
