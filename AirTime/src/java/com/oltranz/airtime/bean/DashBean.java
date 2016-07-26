/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

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
    
    @ManagedProperty(value="#{CustomerBean}")
    private CustomerBean customerBean;
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    public String dashBoard(){
        
        templateBean.setDashboardClassName("omenu_active");
        templateBean.setUserClassName("omenu");
        templateBean.setCustomerClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setLogClassName("omenu");
        
        customerBean.totalCustomers();
        transactionBean.dayTransactions();
        transactionBean.traPerToday();
        transactionBean.traPerWeek();
        
        
        return "dashboard.xhtml?faces-redirect=true";
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
