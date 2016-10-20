/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.ActionManager;
import com.oltranz.engenpayfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author John
 */
@Stateless
@Path("ActionManagementService")
public class ActionManagementService {
    
     @EJB
    private ActionManager actionManager;
    
    @GET
    @Path("actions")
    @Consumes({"application/xml", "application/json"})
    public String getActionList() {
        
        ResultObject result= actionManager.getActionList();
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
}
