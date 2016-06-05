/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.AndroidDataManager;
import com.oltranz.payfuel.library.CommonLibrary;
import com.oltranz.payfuel.models.AsyncTransaction;
import com.oltranz.payfuel.models.LoginIpModel;
import com.oltranz.payfuel.models.LogoutIpModel;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.SaleDetailsModel;
import com.oltranz.payfuel.models.ServiceProvison;
import com.oltranz.payfuel.models.SyncTransaction;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author John
 */
@Stateless
@Path("AndroidWebService")
public class AndroidWebService {
    
    @EJB
    private AndroidDataManager androidDataManager;
    
    
    
    
    @POST
    @Path("pos/login")
    @Consumes({"application/xml", "application/json"})
    public String login(LoginIpModel validateLoginIp) {
        
        ResultObject result= androidDataManager.login(validateLoginIp.getDeviceId(),validateLoginIp.getUserPin());
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    @POST
    @Path("pos/logout")
    @Consumes({"application/xml", "application/json"})
    public String logout(LogoutIpModel validateLogout) {
        
        ResultObject result= androidDataManager.logout(validateLogout.getDeviceId(),validateLogout.getUserId());
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("pos/sale")
    @Consumes({"application/xml", "application/json"})
    public String sale(SaleDetailsModel saleDetailsModel) {
        
        ResultObject result= androidDataManager.sale(saleDetailsModel);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
 
    
    //service provison conformation link, after  mobile money payment the payment gateway post payment status on this link
    @POST
    @Path("pos/spc")
    public void serviceProvisonConfirmation(InputStream is) throws Exception{
        
            ServiceProvison serviceProvisonIp=(ServiceProvison) CommonLibrary.unmarshalling(is, ServiceProvison.class);
            androidDataManager.serviceProvisonConfirmation(serviceProvisonIp);
    }
    
    //async for mobile money
    @POST
    @Path("pos/sale/async")
    @Consumes({"application/xml", "application/json"})
    public String asyncSaleTransaction(AsyncTransaction asyncTransactionIp) {
        
        ResultObject result= androidDataManager.asyncSaleTransaction(asyncTransactionIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    //sync for mobile money
    @POST
    @Path("pos/sale/sync")
    @Consumes({"application/xml", "application/json"})
    public String syncSaleTransaction(SyncTransaction syncTransactionIp) {
        
        ResultObject result= androidDataManager.syncSaleTransaction(syncTransactionIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @GET
    @Path("pos/transactions")
    @Consumes({"application/xml", "application/json"})
    public String getTransactionList() {
        
        ResultObject result= androidDataManager.getTransactionList();
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("pos/transaction/{transactionId}")
    @Consumes({"application/xml", "application/json"})
    public String getTransactionListByTraId(@PathParam("transactionId") Integer transactionId) {
        
        ResultObject result= androidDataManager.getTransactionListByTraId(transactionId);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @GET
    @Path("pos/transactions/{userId}")
    @Consumes({"application/xml", "application/json"})
    public String getTransactionList(@PathParam("userId") Integer userId) {
        
        ResultObject result= androidDataManager.getTransactionList(userId);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("logs")
    @Consumes({"application/xml", "application/json"})
    public String getLogList() {
        
        ResultObject result= androidDataManager.getLogList();
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("log/{logId}")
    @Consumes({"application/xml", "application/json"})
    public String getLog(@PathParam("logId") Integer logId) {
        
        ResultObject result= androidDataManager.getLog(logId);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
}
