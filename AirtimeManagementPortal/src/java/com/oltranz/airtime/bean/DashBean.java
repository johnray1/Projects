/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

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
public class DashBean {
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{CustomerBean}")
    private CustomerBean customerBean;
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    public void dashBoard(){
        
        templateBean.setDashboardClassName("active");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLogClassName("deactive");
        
        customerBean.totalCustomers();
        transactionBean.dayTransactions();
        transactionBean.traPerToday();
        transactionBean.traPerWeek();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
        }
        catch (Exception ex) {
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
     * @return the customerBean
     */
    public CustomerBean getCustomerBean() {
        return customerBean;
    }
    
    /**
     * @param customerBean the customerBean to set
     */
    public void setCustomerBean(CustomerBean customerBean) {
        this.customerBean = customerBean;
    }
    
    /**
     * @return the transactionBean
     */
    public TransactionBean getTransactionBean() {
        return transactionBean;
    }
    
    /**
     * @param transactionBean the transactionBean to set
     */
    public void setTransactionBean(TransactionBean transactionBean) {
        this.transactionBean = transactionBean;
    }
    
    
    
}
