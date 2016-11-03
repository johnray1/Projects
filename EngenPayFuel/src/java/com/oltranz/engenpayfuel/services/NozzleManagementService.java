/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.NozzleManager;
import com.oltranz.engenpayfuel.entities.Nozzle;
import com.oltranz.engenpayfuel.models.NozzleAdjust;
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
    
    
    @POST
    @Path("nozzle/edit")
    @Consumes({"application/xml", "application/json"})
    public String editNozzle(Nozzle editNozzle) {
        
        ResultObject result=nozzleManager.editNozzle(editNozzle);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("nozzle/adjust")
    @Consumes({"application/xml", "application/json"})
    public String adjustNozzle(NozzleAdjust nozzleAdjust) {
        
        ResultObject result=nozzleManager.adjustNozzle(nozzleAdjust);
        return result.getJsonFormat();
        
    }
    
    @GET
    @Path("nozzles")
    @Consumes({"application/xml", "application/json"})
    public String getNozzleList() {
        
        ResultObject result=nozzleManager.getNozzleList();
        return result.getJsonFormat();
        
    }
    
    //---------------------web----------------------------------------------
    
    @GET
    @Path("nozzles/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getPumpListByUserId(@PathParam("branchId") Integer branchId) {
        
        ResultObject result= nozzleManager.getNozzleListByBranchId(branchId);
        return result.getJsonFormat();
    }
    
}
