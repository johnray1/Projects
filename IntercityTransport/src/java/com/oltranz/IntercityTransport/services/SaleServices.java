/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.beans.SalesManager;
import com.oltranz.IntercityTransport.models.CardPaymentOnPOSRequestModel;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.SaleTransactionsSearchModel;
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
 * @author manzi
 */
@Stateless
@Path("salesServices")
public class SaleServices {
    
    @EJB
            SalesManager salesManagerEJB;
    
    
    @POST
    @Path("cardPaymentOnPOSRequest")
    @Consumes({"application/xml", "application/json"})
    public String cardPaymentOnPOSRequest(CardPaymentOnPOSRequestModel payment) {
        
        ResultObject result = salesManagerEJB.processCardPaymentOnPOSRequest(payment);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("sales")
    @Produces({"application/xml", "application/json"})
    public String getSalesTransactions(SaleTransactionsSearchModel saleTransactionsSearchModel ) {
        ResultObject result= salesManagerEJB.getSalesTransactions(saleTransactionsSearchModel);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("sales/getBusesSalesReport")
    @Produces({"application/xml", "application/json"})
    public String getBusesByTransporter() {
        ResultObject result= salesManagerEJB.getTodayBusesSalesReport();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("sales/getTodayBusesSalesReportPerTransporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getTodayBusesByTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= salesManagerEJB.getTodayBusesSalesReportPerTransporter(transporterId);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("sales/getBusesOnwersSalesReport")
    @Produces({"application/xml", "application/json"})
    public String getBusesOwnersSalesReport() {
        ResultObject result= salesManagerEJB.getTodayBusOwnersSalesReport();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("sales/getBusesOwnersSalesReportPerTransporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getBusesOwnersSalesReportPerTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= salesManagerEJB.getTodayBusOwnersSalesReportPerTransporter(transporterId);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("sales/transporterTotalRevenues/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String gettransporterTotalRevenues(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= salesManagerEJB.getTransporterTotalRevenues(transporterId);
        return result.getJsonFormat();
    }
}
