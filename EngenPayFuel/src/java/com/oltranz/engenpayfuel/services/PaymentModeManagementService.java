/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.PaymentModeManager;
import com.oltranz.engenpayfuel.entities.PaymentMode;
import com.oltranz.engenpayfuel.models.ReportShift;
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
    @Path("paymentmodes/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getPaymentModeListByUserId(@PathParam("userId") Integer userId) {
        
        ResultObject result= paymentModeManager.getPaymentModeListByUserId(userId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("paymentmode/{id}")
    @Produces({"application/xml", "application/json"})
    public String getPaymentModeByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= paymentModeManager.getPaymentModeByItsId(id);
        return result.getJsonFormat();
    }
    
    
    
    @POST
    @Path("paymentmode/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removePaymentMode(@PathParam("id") Integer id) {
        
        ResultObject result= paymentModeManager.removePaymentMode(id);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("paymentmodeReport")
    @Consumes({"application/xml", "application/json"})
    public String getPaymentReportList(String rs) {
        
        ResultObject result=paymentModeManager.getPaymentReportList(rs);
        
        return result.getJsonFormat();
        
    }
    
}
