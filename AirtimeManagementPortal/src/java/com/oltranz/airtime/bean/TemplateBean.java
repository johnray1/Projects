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
    
    private String today="col-xs-3 shortdate active";
    private String yesterday="col-xs-3 shortdate";
    private String week="col-xs-3 shortdate";
    private String month="col-xs-3 shortdate";
    
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

    /**
     * @return the today
     */
    public String getToday() {
        return today;
    }

    /**
     * @param today the today to set
     */
    public void setToday(String today) {
        this.today = today;
    }

    /**
     * @return the yesterday
     */
    public String getYesterday() {
        return yesterday;
    }

    /**
     * @param yesterday the yesterday to set
     */
    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(String week) {
        this.week = week;
    }
    
    
    
    
}
