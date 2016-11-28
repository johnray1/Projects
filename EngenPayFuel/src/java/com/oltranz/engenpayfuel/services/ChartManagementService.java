/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.ChartManager;
import com.oltranz.engenpayfuel.models.ResultObject;
import java.text.SimpleDateFormat;
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
    @Path("productPie/{branchId}/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public String productPie(@PathParam("branchId") Integer branchId, @PathParam("from")String from, @PathParam("to")String to) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return chartManager.productPie(branchId,sdf.parse(from), sdf.parse(to));
        }
        catch(Exception e){
            return e.getMessage();
            
        }
    }
    
    
    @GET
    @Path("branchChart/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public String allBranchChart(@PathParam("from")String from, @PathParam("to")String to) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return chartManager.allBranchChart(sdf.parse(from), sdf.parse(to));
        }
        catch(Exception e){
            return e.getMessage();
            
        }
    }
    
    
    @GET
    @Path("paymentChart/{branchId}/{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public String allPaymentChart(@PathParam("branchId") Integer branchId,@PathParam("from")String from, @PathParam("to")String to) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return chartManager.allPaymentChart(branchId,sdf.parse(from), sdf.parse(to));
        }
        catch(Exception e){
            return e.getMessage();
            
        }
    }
    
    
    
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
    @Path("tankDashboard/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getTankDashboard(@PathParam("branchId") Integer branchId) {
        
        ResultObject result=chartManager.getTankDashboard(branchId);
        return result.getJsonFormat();
        
    }
    
    
    
    
//    @GET
//    @Path("tankDashboard/{tankId}")
//    @Produces({"application/xml", "application/json"})
//    public String getTankDashboard(@PathParam("tankId") Integer tankId) {
//
//
//        return chartManager.getTankDashboard(tankId);
//    }
    
    
    
    
//    
//    @GET
//    @Path("paymentChartByProduct")
//    @Produces({"application/xml", "application/json"})
//    public String paymentChartSaleProduct() {
//        
//        return chartManager.paymentChartSaleProduct();
//    }
//    
    
    
    
    
    
}
