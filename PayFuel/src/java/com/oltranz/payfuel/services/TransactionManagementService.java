/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.TransactionManager;
import com.oltranz.payfuel.models.DayTransaction;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.TransactionFilter;
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
@Path("TransactionManagementService")
public class TransactionManagementService {
    
    @EJB
    private TransactionManager transactionManager;
    
    
    
    @GET
    @Path("transactions")
    @Consumes({"application/xml", "application/json"})
    public String getTransactionList() {
        
        ResultObject result= transactionManager.getTransactionList();
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("transaction/{transactionId}")
    @Consumes({"application/xml", "application/json"})
    public String getTransactionListByTraId(@PathParam("transactionId") long transactionId) {
        
        ResultObject result= transactionManager.getTransactionListByTraId(transactionId);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @GET
    @Path("transactions/{userId}")
    @Consumes({"application/xml", "application/json"})
    public String getTransactionList(@PathParam("userId") Integer userId) {
        
        ResultObject result= transactionManager.getTransactionList(userId);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("transactions/filter")
    @Consumes({"application/xml", "application/json"})
    public String filterTransaction(TransactionFilter transactionFilterIp) {
        
        ResultObject result=transactionManager.filterTransaction(transactionFilterIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("transactions/day")
    @Consumes({"application/xml", "application/json"})
    public String getDayTransactionList(DayTransaction dayTransactionIp) {
        
        ResultObject result=transactionManager.getDayTransactionList(dayTransactionIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
}
