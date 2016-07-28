/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author John
 */
@ManagedBean(name="DashBean")
@SessionScoped
public class DashBean implements Serializable{
    
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{TankBean}")
    private TankBean tankBean;
    
    public String dashBoard(){
        
        templateBean.setDashboardClassName("omenu_active");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        tankBean.tank1Dashboard();
        tankBean.tank2Dashboard();
        tankBean.tank3Dashboard();
        
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

    /**
     * @return the tankBean
     */
    public TankBean getTankBean() {
        return tankBean;
    }

    /**
     * @param tankBean the tankBean to set
     */
    public void setTankBean(TankBean tankBean) {
        this.tankBean = tankBean;
    }
    
    
    
}
