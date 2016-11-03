package com.oltranz.airtime.model;
import java.util.List;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


/**
 *
 * @author manzi
 */
public class VtuSalesReport {
    
    private List<VtuAgentSIMSalesReport> agentSIMSalesReport;
    private Double totalSales;

    /**
     * @return the agentSIMSalesReport
     */
    public List<VtuAgentSIMSalesReport> getAgentSIMSalesReport() {
        return agentSIMSalesReport;
    }

    /**
     * @param agentSIMSalesReport the agentSIMSalesReport to set
     */
    public void setAgentSIMSalesReport(List<VtuAgentSIMSalesReport> agentSIMSalesReport) {
        this.agentSIMSalesReport = agentSIMSalesReport;
    }

    /**
     * @return the totalSales
     */
    public Double getTotalSales() {
        return totalSales;
    }

    /**
     * @param totalSales the totalSales to set
     */
    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
  
   
}
