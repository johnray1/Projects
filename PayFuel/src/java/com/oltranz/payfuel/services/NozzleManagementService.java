/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.NozzleManager;
import com.oltranz.payfuel.entities.Nozzle;
import com.oltranz.payfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author John
 */
@Path("NozzleManagementService")
@Stateless
public class NozzleManagementService {
    
    @EJB
            NozzleManager nozzleManager;
    
    
    
    
    @POST
    @Path("nozzle/create")
    @Consumes({"application/xml", "application/json"})
    public String createNozzle(Nozzle createNozzle) {
        
        ResultObject result=nozzleManager.createNozzle(createNozzle);
        return result.getJsonFormat();
        
    }
    
    @GET
    @Path("nozzles")
    @Consumes({"application/xml", "application/json"})
    public String getNozzleList() {
        
        ResultObject result=nozzleManager.getNozzleList();
        return result.getJsonFormat();
        
    }
    
    
    
}
