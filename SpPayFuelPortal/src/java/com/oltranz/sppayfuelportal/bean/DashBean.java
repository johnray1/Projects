/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author John
 */
@ManagedBean(name="DashBean")
@SessionScoped
public class DashBean {
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public String dashBoard(){
        
        templateBean.setDashboardClassName("omenu_active");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        
        return "dashboard?faces-redirect=true";
    }
    
    /**
     * @return the templateBean
     */
    public TemplateBean getTemplateBean() {
        return templateBean;
    }
    
    /**
     * @param templateBean the templateBean to set
     */
    public void setTemplateBean(TemplateBean templateBean) {
        this.templateBean = templateBean;
    }
    
}
