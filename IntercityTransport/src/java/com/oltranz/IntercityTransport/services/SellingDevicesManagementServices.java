/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.entities.SellingDevice;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.beans.SellingDevicesManager;
import com.oltranz.IntercityTransport.models.SellingDeviceRegistrationModel;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author manzi
 */
@Stateless
@Path("sellingDevicesManagementServices")
public class SellingDevicesManagementServices {
    
    @EJB
            SellingDevicesManager SellingDevicesManagerEJB;
    
    @POST
    @Path("sellingDevice/new")
    @Consumes({"application/xml", "application/json"})
    public String createSellingDevice(SellingDevice sellingDevice) {
        ResultObject result= SellingDevicesManagerEJB.createSellingDevice(sellingDevice);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("registerDevice/")
    @Consumes({"application/xml", "application/json"})
    public String createSellingDevice(SellingDeviceRegistrationModel sellingDevice) {
        ResultObject result= SellingDevicesManagerEJB.registerSellingDevice(sellingDevice);
        return result.getJsonFormat();
    }
    
//     @POST
//    @Path("registerDevice/new")
//    @Consumes({"application/xml", "application/json"})
//    public String createSellingDevice(SellingDevice sellingDevice) {
//        ResultObject result= SellingDevicesManagerEJB.createSellingDevice(sellingDevice);
//        return result.getJsonFormat();
//    }
    
    @POST
    @Path("sellingDevice/edit")
    @Consumes({"application/xml", "application/json"})
    public String editSellingDevice(SellingDevice sellingDevice) {
        ResultObject result= SellingDevicesManagerEJB.updateSellingDevice(sellingDevice);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("sellingProfile2Device/{profileId}/{deviceId}")
    @Consumes({"application/xml", "application/json"})
    public String editSellingDevice(@PathParam("profileId") Integer profileId, @PathParam("deviceId") String deviceId) {
        ResultObject result= SellingDevicesManagerEJB.attachProfile2SellingDevice(profileId, deviceId);
        return result.getJsonFormat();
    }
    
    
    
    @GET
    @Path("sellingDevice/{id}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("id") String id) {
        ResultObject result= SellingDevicesManagerEJB.getSellingDevice(id);
        return result.getJsonFormat();
    }
            
     @GET
    @Path("sellingDeviceProfile/{id}")
    @Produces({"application/xml", "application/json"})
    public String getSellingDeviceProfile(@PathParam("id") String id) {
        ResultObject result= SellingDevicesManagerEJB.getSellingDeviceProfile(id);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("sellingDevices")
    @Produces({"application/xml", "application/json"})
    public String getAllSellingDevices() {
        ResultObject result= SellingDevicesManagerEJB.getAllSellingDevicesList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("transporterSellingDevices/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getTransporterSellingDevices(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= SellingDevicesManagerEJB.getTransporterSellingDevicesList(transporterId);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("sellingDeviceToBus/{id}/{numberPlate}")
    public String sellingDeviceToBus(@PathParam("id") String id,@PathParam("numberPlate") String numberPlate) {
        ResultObject result= SellingDevicesManagerEJB.attacheSellingDeviceToBus(id, numberPlate);
        return result.getJsonFormat();
    }
    
    @DELETE
    @Path("sellingDeviceFromBus/{id}")
    public String sellingDeviceFromCurrentBus(@PathParam("id") String id) {
        ResultObject result= SellingDevicesManagerEJB.detachSellingDeviceFromCurrentBus(id);
        return result.getJsonFormat();
    }
    
}
