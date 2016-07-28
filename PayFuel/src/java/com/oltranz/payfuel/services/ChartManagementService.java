/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.ChartManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author John
 */
@Path("ChartManagementService")
@Stateless
public class ChartManagementService {
    
    @EJB
            ChartManager chartManager;
    
    
    @GET
    @Path("dailyProductChart")
    @Produces({"application/xml", "application/json"})
    public void getDailyProductSaleChart() {
        
        chartManager.getDailyProductSaleChart();
    }
    
    
    @GET
    @Path("monthlyProductChart")
    @Produces({"application/xml", "application/json"})
    public String getMonthlyProductSaleChart() {
        
        return chartManager.getMonthlyProductSaleChart();
    }
    
    
    @GET
    @Path("tankChart")
    @Produces({"application/xml", "application/json"})
    public String tankChart() {
        return chartManager.tankChart();
    }
    
    
    @GET
    @Path("tankDashboard/{tankId}")
    @Produces({"application/xml", "application/json"})
    public String getTankDashboard(@PathParam("tankId") Integer tankId) {
        
       
        return chartManager.getTankDashboard(tankId);
    }
    
    @GET
    @Path("getday")
    @Produces({"application/xml", "application/json"})
    public void test() {
        
       
        chartManager.test();
    }
    
    
    
}
