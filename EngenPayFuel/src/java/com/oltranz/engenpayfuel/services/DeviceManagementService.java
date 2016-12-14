/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.DeviceManager;
import com.oltranz.engenpayfuel.entities.Device;
import com.oltranz.engenpayfuel.library.CommonLibrary;
import com.oltranz.engenpayfuel.models.DeviceRegistrationModel;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.SaleDetailsModel;
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
@Path("DeviceManagementService")
@Stateless
public class DeviceManagementService {
    
    
    @EJB
            DeviceManager deviceManager;
    
    
    @POST
    @Path("device/register")
    public String createSellingDevice(InputStream is) {
        
        String jsonData = null;
        try {
            jsonData = CommonLibrary.inputStream2StringTest(is);
            out.print("Time ["+(new Date())+"] - DeviceManagementService/device/register - InPutRequest: Received string\n"+jsonData+"\n\n");
        } catch (IOException ex) {
            out.print("Time ["+(new Date())+"] - DeviceManagementService/device/register - InPutRequest: CATCH BLOCK:\n"+ex.getMessage()+"\n\n");
            ex.printStackTrace();
        }
        
        
        ObjectMapper mapper=new ObjectMapper();
        DeviceRegistrationModel regDeviceModel = new DeviceRegistrationModel();
        try {
            regDeviceModel = (DeviceRegistrationModel)mapper.readValue(jsonData, DeviceRegistrationModel.class);
        } catch (IOException ex) {
            out.print("Time ["+(new Date())+"] - DeviceManagementService/device/register - MAPPING TO A OBJECT: CATCH BLOCK:\n"+ex.getMessage()+"\n\n");
            ex.printStackTrace();
        }
        
        out.print("Time ["+(new Date())+"] - DeviceManagementService/pos/saleTest - InPutData||\n"
                +"EMAIL:"+regDeviceModel.getEmail()
                +"| PASSWORD:"+regDeviceModel.getPassword()
                +"| DEVICE NAME/ID:"+regDeviceModel.getDeviceId()
                +"| SERIAL NUMBER:"+regDeviceModel.getSerialNumber()
                +"\n\n"
        );
        
        
        ResultObject result= deviceManager.registerSellingDevice(regDeviceModel);
        String jsonResult=result.getJsonFormat();
        
        out.print("Time ["+(new Date())+"] - DeviceManagementService/device/register - InPutResponse: Received string\n"+jsonResult+"\n");
        
        return jsonResult;
    }
    
    
    @POST
    @Path("device/create")
    @Consumes({"application/xml", "application/json"})
    public String createDevice(Device newDevice) {
        
        ResultObject result=deviceManager.createDevice(newDevice);
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
    
    
    //-----------------web
    
    @GET
    @Path("devices/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getDeviceListByUserId(@PathParam("branchId") Integer branchId) {
        
        ResultObject result= deviceManager.getDeviceList(branchId);
        return result.getJsonFormat();
    }
    
}
