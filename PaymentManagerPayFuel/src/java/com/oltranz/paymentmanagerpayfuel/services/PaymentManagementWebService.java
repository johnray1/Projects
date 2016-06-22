/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.paymentmanagerpayfuel.services;

import com.oltranz.paymentmanagerpayfuel.beans.PaymentManager;
import com.oltranz.paymentmanagerpayfuel.library.CommonLibrary;
import com.oltranz.paymentmanagerpayfuel.model.PaymentRequest;
import com.oltranz.paymentmanagerpayfuel.model.PaymentResponse;
import com.oltranz.paymentmanagerpayfuel.model.ServiceProvison;
import com.oltranz.paymentmanagerpayfuel.model.Voucher;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author John
 */
@Path("PaymentManagementWebService")
@Stateless
public class PaymentManagementWebService {
    
    @EJB
    private PaymentManager paymentManager;
    
    
    @POST
    @Path("payment/cash")
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse cashPayment(InputStream is) {
        
        PaymentRequest paymentRequest=(PaymentRequest) CommonLibrary.unmarshalling(is, PaymentRequest.class);
        PaymentResponse paymentResponse=paymentManager.cashPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    @POST
    @Path("payment/voucher")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse voucherPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=paymentManager.voucherPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    @POST
    @Path("payment/momo")
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse mobileMOneyPayment(InputStream is) {
        
        PaymentRequest paymentRequest=(PaymentRequest) CommonLibrary.unmarshalling(is, PaymentRequest.class);
        PaymentResponse paymentResponse=paymentManager.mobileMOneyPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    @POST
    @Path("payment/visa")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse visaPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=paymentManager.visaPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    @POST
    @Path("payment/master")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse masterCardPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=paymentManager.masterCardPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    @POST
    @Path("payment/debt")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse debtPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=paymentManager.debtPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    @POST
    @Path("payment/spCard")
    @Consumes({"application/xml", "application/json"})
    @Produces(MediaType.APPLICATION_XML)
    public PaymentResponse spCardPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=paymentManager.spCardPayment(paymentRequest);
        
        return paymentResponse;
        
    }
    
    //service provison conformation link, after  mobile money payment the payment gateway post payment status on this link
    @POST
    @Path("payment/momoConfirmation")
    public void momoConfirmation(InputStream is) throws Exception{
        
        ServiceProvison serviceProvisonIp=(ServiceProvison) CommonLibrary.unmarshalling(is, ServiceProvison.class);
        paymentManager.momoConfirmation(serviceProvisonIp);
    }
    
    //service provison conformation link, after  mobile money payment the payment gateway post payment status on this link
    @POST
    @Path("payment/momoAcknowledgement")
    public void momoAcknowledgement(InputStream is) throws Exception{
        
        ServiceProvison serviceProvisonIp=(ServiceProvison) CommonLibrary.unmarshalling(is, ServiceProvison.class);
        paymentManager.momoAcknowledgement(serviceProvisonIp);
    }
    
    @GET
    @Path("payment/{voucherNo}")
    @Produces({"application/xml", "application/json"})
    public Voucher getVoucher(@PathParam("voucherNo") String voucherNo) {
        
        Voucher voucher=paymentManager.getVoucher(voucherNo);
        
        return voucher;
    }
    
}
