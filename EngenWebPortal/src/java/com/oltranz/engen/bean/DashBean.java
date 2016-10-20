/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author John
 */
@ManagedBean(name="DashBean")
@SessionScoped
public class DashBean implements Serializable{
    
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{TpnBean}")
    private TpnBean tpnBean;
    
    
    
    public void dashBoard(){
        try{
            templateBean.setDashboardClassName("omenu_active");
            templateBean.setBranchClassName("omenu");
            templateBean.setProductsClassName("omenu");
            templateBean.setGoalClassName("omenu");
            templateBean.setTransactionClassName("omenu");
            templateBean.setSettingClassName("omenu");
            
            templateBean.paymentModeChart();
            tpnBean.tankDashboard();
            
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dashBoardHq(){
        try{
            templateBean.setDashboardClassName("omenu_active");
            templateBean.setBranchClassName("omenu");
            templateBean.setProductsClassName("omenu");
            templateBean.setGoalClassName("omenu");
            templateBean.setTransactionClassName("omenu");
            templateBean.setSettingClassName("omenu");
            
            templateBean.paymentModeChart();
            tpnBean.tankDashboard();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard_hq.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    /**
     * @return the tpnBean
     */
    public TpnBean getTpnBean() {
        return tpnBean;
    }
    
    /**
     * @param tpnBean the tpnBean to set
     */
    public void setTpnBean(TpnBean tpnBean) {
        this.tpnBean = tpnBean;
    }
    
    
    
    
}
