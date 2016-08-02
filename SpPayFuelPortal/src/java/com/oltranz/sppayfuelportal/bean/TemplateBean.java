/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

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
    private String branchClassName;
    private String productClassName;
    private String goalClassName;
    private String transactionClassName;
    private String settingClassName;
    private String hideClassName;

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
     * @return the branchClassName
     */
    public String getBranchClassName() {
        return branchClassName;
    }

    /**
     * @param branchClassName the branchClassName to set
     */
    public void setBranchClassName(String branchClassName) {
        this.branchClassName = branchClassName;
    }

    /**
     * @return the productClassName
     */
    public String getProductClassName() {
        return productClassName;
    }

    /**
     * @param productClassName the productClassName to set
     */
    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }

    /**
     * @return the goalClassName
     */
    public String getGoalClassName() {
        return goalClassName;
    }

    /**
     * @param goalClassName the goalClassName to set
     */
    public void setGoalClassName(String goalClassName) {
        this.goalClassName = goalClassName;
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
     * @return the settingClassName
     */
    public String getSettingClassName() {
        return settingClassName;
    }

    /**
     * @param settingClassName the settingClassName to set
     */
    public void setSettingClassName(String settingClassName) {
        this.settingClassName = settingClassName;
    }

    /**
     * @return the hideClassName
     */
    public String getHideClassName() {
        return hideClassName;
    }

    /**
     * @param hideClassName the hideClassName to set
     */
    public void setHideClassName(String hideClassName) {
        this.hideClassName = hideClassName;
    }
    
    
    
}