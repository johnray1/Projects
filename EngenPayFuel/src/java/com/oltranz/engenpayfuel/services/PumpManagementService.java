/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.PumpManager;
import com.oltranz.engenpayfuel.entities.Pump;
import com.oltranz.engenpayfuel.models.AssignedPumpModelList;
import com.oltranz.engenpayfuel.models.ResultObject;
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
    @Path("pump/{pumpId}")
    @Produces({"application/xml", "application/json"})
    public String getPumpByItsId(@PathParam("pumpId") Integer pumpId) {
        
        ResultObject result= pumpManager.getPumpByItsId(pumpId);
        return result.getJsonFormat();
    }
    
    
    
    @POST
    @Path("pump/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removePump(@PathParam("id") Integer id) {
        ResultObject result= pumpManager.deletePump(id);
        return result.getJsonFormat();
    }
    
  //------------------------------------------------Android---------------------------------------------------------
    
    @GET
    @Path("pumpsbyuser/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getTankPumpNozzleDetailsByUserId(@PathParam("userId") Integer userId) {
        
        ResultObject result= pumpManager.getPumpNozzleDetailsByUserId(userId);
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
    
    
     //---------------------web----------------------------------------------
    
    @GET
    @Path("pumps/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getPumpListByUserId(@PathParam("branchId") Integer branchId) {
        
        ResultObject result= pumpManager.getPumpListByBranchId(branchId);
        return result.getJsonFormat();
    }
}
