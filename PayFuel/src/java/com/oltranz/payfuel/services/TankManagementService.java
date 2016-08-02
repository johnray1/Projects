/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.TankManager;
import com.oltranz.payfuel.entities.Diping;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.entities.Tanking;
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
@Path("TankManagementService")
@Stateless
public class TankManagementService {
    @EJB
    TankManager tankManager;
    
    @POST
    @Path("tank/create")
    @Consumes({"application/xml", "application/json"})
    public String createTank(Tank newTank) {
        
        ResultObject result=tankManager.createTank(newTank);
        return result.getJsonFormat();
        
    }
    
    
    
    @POST
    @Path("tank/edit")
    @Consumes({"application/xml", "application/json"})
    public String editTank(Tank editTank) {
        
        ResultObject result=tankManager.editTank(editTank);
        return result.getJsonFormat();
        
    }
    
    @GET
    @Path("tanks")
    @Produces({"application/xml", "application/json"})
    public String getTankList() {
        
        ResultObject result= tankManager.getTankList();
        return result.getJsonFormat();
    }
    
    
    
    @GET
    @Path("tank/{tankId}")
    @Produces({"application/xml", "application/json"})
    public String getTankByItsId(@PathParam("tankId") Integer tankId) {
        
        ResultObject result= tankManager.getTankByItsId(tankId);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("tanking")
    @Consumes({"application/xml", "application/json"})
    public String createTanking(Tanking tanking) {
        
        ResultObject result=tankManager.tanking(tanking);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("diping")
    @Consumes({"application/xml", "application/json"})
    public String createDeeping(Diping diping) {
        
        ResultObject result=tankManager.diping(diping);
        return result.getJsonFormat();
        
    }
    
    //---------------------web----------------------------------------------
    
    @GET
    @Path("tanks/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getDeviceListByUserId(@PathParam("branchId") Integer branchId) {
        
        ResultObject result= tankManager.getTankListById(branchId);
        return result.getJsonFormat();
    }
}
