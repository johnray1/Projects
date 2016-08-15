/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

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
    private String loginClassName="omenu_active";
    private String howClassName="omenu";
    private String helpClassName="omenu";
    private String contactClassName="omenu";
    private String welcomeClassName;
    private String accountClassName;
    private String transactionClassName;
    private String notificationClassName;
    
    
    public String login(){
        
        loginClassName="omenu_active";
        howClassName="omenu";
        helpClassName="omenu";
        contactClassName="omenu";
        
        return "login.xhtml?faces-redirect=true";
    }
    public String how(){
        
        loginClassName="omenu";
        howClassName="omenu_active";
        helpClassName="omenu";
        contactClassName="omenu";
        
        return "how.xhtml?faces-redirect=true";
    }
    
    public String help(){
        
        loginClassName="omenu";
        howClassName="omenu";
        helpClassName="omenu_active";
        contactClassName="omenu";
        
        return "help.xhtml?faces-redirect=true";
    }
    
    public String contacts(){
        
        loginClassName="omenu";
        howClassName="omenu";
        helpClassName="omenu";
        contactClassName="omenu_active";
        
        return "contacts.xhtml?faces-redirect=true";
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
     * @return the howClassName
     */
    public String getHowClassName() {
        return howClassName;
    }
    
    /**
     * @param howClassName the howClassName to set
     */
    public void setHowClassName(String howClassName) {
        this.howClassName = howClassName;
    }
    
    /**
     * @return the helpClassName
     */
    public String getHelpClassName() {
        return helpClassName;
    }
    
    /**
     * @param helpClassName the helpClassName to set
     */
    public void setHelpClassName(String helpClassName) {
        this.helpClassName = helpClassName;
    }
    
    /**
     * @return the contactClassName
     */
    public String getContactClassName() {
        return contactClassName;
    }
    
    /**
     * @param contactClassName the contactClassName to set
     */
    public void setContactClassName(String contactClassName) {
        this.contactClassName = contactClassName;
    }
    
    /**
     * @return the welcomeClassName
     */
    public String getWelcomeClassName() {
        return welcomeClassName;
    }
    
    /**
     * @param welcomeClassName the welcomeClassName to set
     */
    public void setWelcomeClassName(String welcomeClassName) {
        this.welcomeClassName = welcomeClassName;
    }
    
    /**
     * @return the accountClassName
     */
    public String getAccountClassName() {
        return accountClassName;
    }
    
    /**
     * @param accountClassName the accountClassName to set
     */
    public void setAccountClassName(String accountClassName) {
        this.accountClassName = accountClassName;
    }
    
    /**
     * @return the notificationClassName
     */
    public String getNotificationClassName() {
        return notificationClassName;
    }
    
    /**
     * @param notificationClassName the notificationClassName to set
     */
    public void setNotificationClassName(String notificationClassName) {
        this.notificationClassName = notificationClassName;
    }
    
    /**
     * @return the loginClassName
     */
    public String getLoginClassName() {
        return loginClassName;
    }
    
    /**
     * @param loginClassName the loginClassName to set
     */
    public void setLoginClassName(String loginClassName) {
        this.loginClassName = loginClassName;
    }
    
    
    
    
}
