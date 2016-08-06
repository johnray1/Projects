/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import java.io.IOException;
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
@ManagedBean(name="WelcomeBean")
@SessionScoped
public class WelcomeBean {
    
    private String msisdn;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{CustomerBean}")
    private CustomerBean customerBean;
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    public void welcomes(){
        
        templateBean.setWelcomeClassName("omenu_active");
        templateBean.setAccountClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setNotificationClassName("omenu");
        
        customerBean.customer(msisdn);
        transactionBean.transactions(msisdn);
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
        }
        catch (Exception ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }
    
    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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
