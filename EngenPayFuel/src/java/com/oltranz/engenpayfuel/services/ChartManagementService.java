/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.ChartManager;
import com.oltranz.engenpayfuel.models.ResultObject;
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
    @Path("dailyProductChart/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getDailyProductSaleChart(@PathParam("branchId") Integer branchId) {
        
        return chartManager.getDailyProductSaleChart(branchId);
    }
    
    
    @GET
    @Path("monthlyProductChart/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getMonthlyProductSaleChart(@PathParam("branchId") Integer branchId) {
        
        return chartManager.getMonthlyProductSaleChart(branchId);
    }
    
    @GET
    @Path("totalQuantityAllTankChart")
    @Produces({"application/xml", "application/json"})
    public String allTankQuantityChart() {
        
        return chartManager.allTankQuantityCommulativeChart();
    }
    
    
    @GET
    @Path("tankChart/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String tankChart(@PathParam("branchId") Integer branchId) {
        return chartManager.tankChart(branchId);
    }
    
    
    @GET
    @Path("paymentChart/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String allPaymentChart(@PathParam("branchId") Integer branchId) {
        
        return chartManager.allPaymentChart(branchId);
    }
    
    
    
    
//    @GET
//    @Path("tankDashboard/{tankId}")
//    @Produces({"application/xml", "application/json"})
//    public String getTankDashboard(@PathParam("tankId") Integer tankId) {
//        
//       
//        return chartManager.getTankDashboard(tankId);
//    }
    
    
    @GET
    @Path("tankDashboard/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getTankDashboard(@PathParam("branchId") Integer branchId) {
        
         ResultObject result=chartManager.getTankDashboard(branchId);
         return result.getJsonFormat();
         
    }
    
    
    @GET
    @Path("paymentChartByProduct")
    @Produces({"application/xml", "application/json"})
    public String paymentChartSaleProduct() {
        
        return chartManager.paymentChartSaleProduct();
    }
    
    @GET
    @Path("productPie")
    @Produces({"application/xml", "application/json"})
    public String productPie() {
        
        return chartManager.productPie();
    }
    
    @GET
    @Path("branchChart")
    @Produces({"application/xml", "application/json"})
    public String allBranchChart() {
        
        return chartManager.allBranchChart();
    }
    
    
}
