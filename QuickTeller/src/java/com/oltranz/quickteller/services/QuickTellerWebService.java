/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.services;

import com.oltranz.quickteller.beans.QuickTellerManager;
import com.oltranz.quickteller.models.QuicktellerPayment;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author JohnRay
 */
@Path("QuickTellerWebService")
@Stateless
public class QuickTellerWebService {
    
    @EJB
            QuickTellerManager quickTellerManager;
    
    @POST
    @Path("webPayment")
    @Consumes({"application/xml", "application/json"})
    public String quickPayment(QuicktellerPayment newQuickteller) {
        
        return quickTellerManager.quickPayment(newQuickteller);
        
    }
    

    
}
