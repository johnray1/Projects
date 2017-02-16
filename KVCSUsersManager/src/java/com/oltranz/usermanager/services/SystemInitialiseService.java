/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.services;

import com.oltranz.usermanager.bizLogic.InitialData;
import com.oltranz.usermanager.library.Common;
import com.oltranz.usermanager.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author manzi
 */
@Stateless
@Path("SystemInitialiseService")
public class SystemInitialiseService {
    
    @EJB
    InitialData InitialDataEJB;
    
    @POST
    @Path("initialiseSystemData")
    @Consumes({"application/xml", "application/json"})
    public String initialise() {
        
        ResultObject result= InitialDataEJB.Initialise();
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
     @POST
    @Path("test")
    @Consumes({"application/xml", "application/json"})
    public String test() {
        
      Common.shared.hexStringToByteArray("");
        
        return "jsonResult";
    }
    
    
}
