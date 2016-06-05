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
    private String devicesClassName;
    private String productsClassName;
    private String usersClassName;
    private String rolesClassName;
    private String transactionsClassName;
    private String logsClassName;

    
    
    
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
     * @return the devicesClassName
     */
    public String getDevicesClassName() {
        return devicesClassName;
    }

    /**
     * @param devicesClassName the devicesClassName to set
     */
    public void setDevicesClassName(String devicesClassName) {
        this.devicesClassName = devicesClassName;
    }

    /**
     * @return the productsClassName
     */
    public String getProductsClassName() {
        return productsClassName;
    }

    /**
     * @param productsClassName the productsClassName to set
     */
    public void setProductsClassName(String productsClassName) {
        this.productsClassName = productsClassName;
    }

    /**
     * @return the usersClassName
     */
    public String getUsersClassName() {
        return usersClassName;
    }

    /**
     * @param usersClassName the usersClassName to set
     */
    public void setUsersClassName(String usersClassName) {
        this.usersClassName = usersClassName;
    }

    /**
     * @return the rolesClassName
     */
    public String getRolesClassName() {
        return rolesClassName;
    }

    /**
     * @param rolesClassName the rolesClassName to set
     */
    public void setRolesClassName(String rolesClassName) {
        this.rolesClassName = rolesClassName;
    }

    /**
     * @return the transactionsClassName
     */
    public String getTransactionsClassName() {
        return transactionsClassName;
    }

    /**
     * @param transactionsClassName the transactionsClassName to set
     */
    public void setTransactionsClassName(String transactionsClassName) {
        this.transactionsClassName = transactionsClassName;
    }

    /**
     * @return the logsClassName
     */
    public String getLogsClassName() {
        return logsClassName;
    }

    /**
     * @param logsClassName the logsClassName to set
     */
    public void setLogsClassName(String logsClassName) {
        this.logsClassName = logsClassName;
    }
    
    
    
}
