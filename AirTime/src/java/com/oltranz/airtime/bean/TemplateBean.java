/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author John
 */
@ManagedBean(name="TemplateBean")
@SessionScoped
public class TemplateBean implements Serializable{
    
    
    //Menu variables
    private String dashboardClassName;
    private String userClassName;
    private String customerClassName;
    private String transactionClassName;
    private String logClassName;

    /**
     * @return the dashboardClassName
     */
    public String getDashboardClassName() {
        return dashboardClassName;
    }

    /**
     * @param dashboardClassName the dashboardClassName to set
     */
    public void setDashboardClassName(String dashboardClassName) {
        this.dashboardClassName = dashboardClassName;
    }

    /**
     * @return the userClassName
     */
    public String getUserClassName() {
        return userClassName;
    }

    /**
     * @param userClassName the userClassName to set
     */
    public void setUserClassName(String userClassName) {
        this.userClassName = userClassName;
    }

    /**
     * @return the customerClassName
     */
    public String getCustomerClassName() {
        return customerClassName;
    }

    /**
     * @param customerClassName the customerClassName to set
     */
    public void setCustomerClassName(String customerClassName) {
        this.customerClassName = customerClassName;
    }

    /**
     * @return the transactionClassName
     */
    public String getTransactionClassName() {
        return transactionClassName;
    }

    /**
     * @param transactionClassName the transactionClassName to set
     */
    public void setTransactionClassName(String transactionClassName) {
        this.transactionClassName = transactionClassName;
    }

    /**
     * @return the logClassName
     */
    public String getLogClassName() {
        return logClassName;
    }

    /**
     * @param logClassName the logClassName to set
     */
    public void setLogClassName(String logClassName) {
        this.logClassName = logClassName;
    }
    
    
    
    
}
