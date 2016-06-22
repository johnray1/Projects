/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.TankManager;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
}
