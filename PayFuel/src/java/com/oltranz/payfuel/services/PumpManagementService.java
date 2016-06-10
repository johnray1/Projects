/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.PumpManager;
import com.oltranz.payfuel.entities.Pump;
import com.oltranz.payfuel.models.AssignedPumpModel;
import com.oltranz.payfuel.models.AssignedPumpModelList;
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
@Path("PumpManagementService")
@Stateless
public class PumpManagementService {
    
    @EJB
    PumpManager pumpManager;

    @POST
    @Path("pump/create")
    @Consumes({"application/xml", "application/json"})
    public String createPump(Pump newPump) {
        
        ResultObject result=pumpManager.createPump(newPump);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("pump/edit")
    @Consumes({"application/xml", "application/json"})
    public String editPump(Pump editPump) {
        
        ResultObject result=pumpManager.editPump(editPump);
        return result.getJsonFormat();
        
    }
    
    @GET
    @Path("pumps")
    @Produces({"application/xml", "application/json"})
    public String getPumpList() {
        
        ResultObject result= pumpManager.getPumpList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("getPumpNozzleProductList")
    @Produces({"application/xml", "application/json"})
    public String getPumpNozzleProductList() {
        
        ResultObject result= pumpManager.getPumpNozzleProductList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("getPumpNozzleProductList/{nozzleId}")
    @Produces({"application/xml", "application/json"})
    public String getPumpNozzleProductList(@PathParam("nozzleId") Integer nozzleId) {
        
        ResultObject result= pumpManager.getPumpNozzleProductList(nozzleId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("pump/{id}")
    @Produces({"application/xml", "application/json"})
    public String getPumpByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= pumpManager.getPumpByItsId(id);
        return result.getJsonFormat();
    }
    
    
    
    @GET
    @Path("pumpsbyuser/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getPumpDetailsByUserId(@PathParam("userId") Integer userId) {
        
        ResultObject result= pumpManager.getPumpDetailsByUserId(userId);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("assignUserToPumpNozzle")
    @Produces({"application/xml", "application/json"})
    public String assignUserToPumpNozzle(AssignedPumpModelList assignedPumpModelList) {
        
        ResultObject result= pumpManager.assignUserToPumpNozzle(assignedPumpModelList);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("pumps/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getPumpByBranchId(@PathParam("branchId") Integer branchId) {
        
        ResultObject result= pumpManager.getPumpByBranchId(branchId);
        return result.getJsonFormat();
    }
    
    
    @POST
    @Path("pump/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removePump(@PathParam("id") Integer id) {
        ResultObject result= pumpManager.deletePump(id);
        return result.getJsonFormat();
    }
    
}
