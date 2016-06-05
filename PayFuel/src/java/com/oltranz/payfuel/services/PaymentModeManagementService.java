/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.PaymentModeManager;
import com.oltranz.payfuel.entities.PaymentMode;
import com.oltranz.payfuel.library.CommonLibrary;
import com.oltranz.payfuel.models.ResultObject;
import java.io.InputStream;
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
@Path("PaymentModeManagementService")
@Stateless
public class PaymentModeManagementService {
    
    @EJB
    PaymentModeManager paymentModeManager;
    
    @POST
    @Path("paymentmode/create")
    @Consumes({"application/xml", "application/json"})
    public String createPaymentMode(PaymentMode newPaymentMode) {
        
        
        ResultObject result=paymentModeManager.createPaymentMode(newPaymentMode);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("paymentmode/edit")
    @Consumes({"application/xml", "application/json"})
    public String editPaymentMode(PaymentMode editPaymentMode) {
        
        
        ResultObject result=paymentModeManager.editPaymentMode(editPaymentMode);
        return result.getJsonFormat();
        
    }
    
    @GET
    @Path("paymentmodes")
    @Produces({"application/xml", "application/json"})
    public String getPaymentModeList() {
        
        ResultObject result= paymentModeManager.getPaymentModeList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("paymentmode/{id}")
    @Produces({"application/xml", "application/json"})
    public String getPaymentModeByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= paymentModeManager.getPaymentModeByItsId(id);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("paymentmodes/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getPaymentModeByUserId(@PathParam("userId") Integer userId) {
        
        ResultObject result= paymentModeManager.getPaymentModeByUserId(userId);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("paymentmode/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removePaymentMode(@PathParam("id") Integer id) {
        
        ResultObject result= paymentModeManager.removePaymentMode(id);
        return result.getJsonFormat();
    }
    
}
