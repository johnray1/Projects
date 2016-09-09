/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author John
 */
@ManagedBean(name="TemplateBean")
@SessionScoped
public class TemplateBean implements Serializable{
    
    
    //Menu variables
    private String loginClassName="omenu_active";
    private String downloadClassName="omenu";
    private String howClassName="omenu";
    private String contactClassName="omenu";
    private String logtabClassName="active";
    private String regtabClassName;
    
    private String welcomeClassName;
    private String accountClassName;
    private String transactionClassName;
    private String notificationClassName;
    
    
    public void login(){
        
        loginClassName="omenu_active";
        howClassName="omenu";
        downloadClassName="omenu";
        contactClassName="omenu";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void how(){
        
        loginClassName="omenu";
        howClassName="omenu_active";
        downloadClassName="omenu";
        contactClassName="omenu";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("how.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void download(){
        
        loginClassName="omenu";
        howClassName="omenu";
        downloadClassName="omenu_active";
        contactClassName="omenu";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("download.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void contacts(){
        
        loginClassName="omenu";
        howClassName="omenu";
        downloadClassName="omenu";
        contactClassName="omenu_active";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contacts.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void term(){
        
        loginClassName="omenu";
        howClassName="omenu_active";
        downloadClassName="omenu";
        contactClassName="omenu";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("term.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void privacy(){
        
        loginClassName="omenu";
        howClassName="omenu_active";
        downloadClassName="omenu";
        contactClassName="omenu";
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("privacy.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * @return the downloadClassName
     */
    public String getDownloadClassName() {
        return downloadClassName;
    }

    /**
     * @param downloadClassName the downloadClassName to set
     */
    public void setDownloadClassName(String downloadClassName) {
        this.downloadClassName = downloadClassName;
    }

    /**
     * @return the logtabClassName
     */
    public String getLogtabClassName() {
        return logtabClassName;
    }

    /**
     * @param logtabClassName the logtabClassName to set
     */
    public void setLogtabClassName(String logtabClassName) {
        this.logtabClassName = logtabClassName;
    }

    /**
     * @return the regtabClassName
     */
    public String getRegtabClassName() {
        return regtabClassName;
    }

    /**
     * @param regtabClassName the regtabClassName to set
     */
    public void setRegtabClassName(String regtabClassName) {
        this.regtabClassName = regtabClassName;
    }
    
    
    
    
}
