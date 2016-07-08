/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.TankManager;
import com.oltranz.payfuel.entities.Deeping;
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
    @Path("tanks/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getDeviceListByUserId(@PathParam("userId") Integer userId) {
        
        ResultObject result= tankManager.getTankList(userId);
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
    @Path("tanking/create")
    @Consumes({"application/xml", "application/json"})
    public String createTanking(Tanking newTanking) {
        
        ResultObject result=tankManager.createTanking(newTanking);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("deeping/create")
    @Consumes({"application/xml", "application/json"})
    public String createDeeping(Deeping newDeeping) {
        
        ResultObject result=tankManager.createDeeping(newDeeping);
        return result.getJsonFormat();
        
    }
}
