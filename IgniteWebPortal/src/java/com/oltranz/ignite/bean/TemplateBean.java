/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;

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
    private String transactionClassName;
    private String liquidClassName;
    private String successClassname="success";
    private String failureClassname="failed";
    
    private String today="col-xs-3 shortdate";
    private String yesterday="col-xs-3 shortdate";
    private String week="col-xs-3 shortdate";
    private String month="col-xs-3 shortdate";
    private String overall="col-xs-3 shortdate active";
    

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
     * @return the overall
     */
    public String getOverall() {
        return overall;
    }

    /**
     * @param overall the overall to set
     */
    public void setOverall(String overall) {
        this.overall = overall;
    }

    /**
     * @return the liquidClassName
     */
    public String getLiquidClassName() {
        return liquidClassName;
    }

    /**
     * @param liquidClassName the liquidClassName to set
     */
    public void setLiquidClassName(String liquidClassName) {
        this.liquidClassName = liquidClassName;
    }

    /**
     * @return the successClassname
     */
    public String getSuccessClassname() {
        return successClassname;
    }

    /**
     * @param successClassname the successClassname to set
     */
    public void setSuccessClassname(String successClassname) {
        this.successClassname = successClassname;
    }

    /**
     * @return the failureClassname
     */
    public String getFailureClassname() {
        return failureClassname;
    }

    /**
     * @param failureClassname the failureClassname to set
     */
    public void setFailureClassname(String failureClassname) {
        this.failureClassname = failureClassname;
    }

    
    
    
    
}
