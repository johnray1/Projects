/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.LogManager;
import com.oltranz.engenpayfuel.models.LogFilter;
import com.oltranz.engenpayfuel.models.ResultObject;
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
@Path("LogManagementService")
public class LogManagementService {
    
    @EJB
    private LogManager logManager;
    
    @GET
    @Path("logs")
    @Consumes({"application/xml", "application/json"})
    public String getLogList() {
        
        ResultObject result= logManager.getLogList();
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("log/{logId}")
    @Consumes({"application/xml", "application/json"})
    public String getLog(@PathParam("logId") long logId) {
        
        ResultObject result= logManager.getLog(logId);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("logs/filter")
    @Consumes({"application/xml", "application/json"})
    public String filterLogs(LogFilter logFilterIp) {
        
        ResultObject result=logManager.filterLogs(logFilterIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
}
