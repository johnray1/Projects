/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.services;


import com.oltranz.ignite.bean.LiquidationManager;
import com.oltranz.ignite.model.LiquidationEditModel;
import com.oltranz.ignite.model.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author John
 */
@Path("LiquidationManagementService")
@Stateless
public class LiquidationManagementService {
    
    @EJB
            LiquidationManager liquidationManager;
    
    
    
    @POST
    @Path("liquidation/create")
    @Consumes({"application/xml", "application/json"})
    public String liquidationCreateAndEdit(LiquidationEditModel lqe) {
        ResultObject result;
        
        if(lqe.getId()==null){
            result= liquidationManager.createLiquidation(lqe);
        }
        else{
            result= liquidationManager.editLiquidation(lqe);
        }
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    @GET
    @Path("liquidations")
    @Produces({"application/xml", "application/json"})
    public String checkNotification() {
        
        ResultObject result;
        result= liquidationManager.getLiquidation();
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
}
