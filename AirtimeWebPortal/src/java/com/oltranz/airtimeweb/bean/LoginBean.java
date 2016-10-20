/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import com.oltranz.airtimeweb.dao.LoginDAO;
import com.oltranz.airtimeweb.model.LoginModel;
import com.oltranz.airtimeweb.model.RegisterRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author John
 */
@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    private Date date=new Date();
    private String msisdn;
    private String pin;
    private Boolean faceMessage=false;
    private String msg;
    private LoginModel lm;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{WelcomeBean}")
    private WelcomeBean welcomeBean;
    
    @ManagedProperty(value="#{RechargeBean}")
    private RechargeBean rechargeBean;
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    @ManagedProperty(value="#{RegisterRequest}")
    private RegisterRequest registerRequest;
    
    
    public void login(){
        
        templateBean.setWelcomeClassName("active");
        templateBean.setAccountClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try {
            lm=LoginDAO.validate(msisdn, pin);
            int statusCode=lm.getResponseStatusSimpleBean().getStatusCode();
            
            if (statusCode==400) {
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username",lm.getCustomerDetails().getfName());
                session.setAttribute("msisdn",msisdn);
                session.setAttribute("email",lm.getCustomerDetails().getEmail());
                session.setAttribute("token",lm.getCustomerDetails().getToken());
                session.setMaxInactiveInterval(30*60);
                
                
                welcomeBean.customerWalletBalance();
                welcomeBean.latestTransaction();
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
            }
            else{
                faceMessage=true;
                msg="Your access code doesn't match, Please Try again!";
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            }
            
        }
        catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void logout(){
        
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void register(){
        
        templateBean.setWelcomeClassName("active");
        templateBean.setAccountClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try {
            if(!pin.equals(registerRequest.getPin())){
                faceMessage=true;
                msg="Pin Is Not Same,Registration Failure";
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            }
            else{
                lm=LoginDAO.validateRegister(registerRequest);
                int statusCode=lm.getResponseStatusSimpleBean().getStatusCode();
                
                if (statusCode==400) {
                    
                    HttpSession session = SessionBean.getSession();
                    session.setAttribute("username",lm.getCustomerDetails().getfName());
                    session.setAttribute("msisdn",msisdn);
                    session.setAttribute("email",lm.getCustomerDetails().getEmail());
                    session.setAttribute("token",lm.getCustomerDetails().getToken());
                    session.setMaxInactiveInterval(30*60);
                    
                    welcomeBean.customerWalletBalance();
                    welcomeBean.latestTransaction();
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
                }
                else{
                    faceMessage=true;
                    msg="Registration Failure";
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                }
            }
        }
        catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    /**
     * @return the faceMessage
     */
    public Boolean getFaceMessage() {
        return faceMessage;
    }
    
    /**
     * @param faceMessage the faceMessage to set
     */
    public void setFaceMessage(Boolean faceMessage) {
        this.faceMessage = faceMessage;
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
     * @return the pin
     */
    public String getPin() {
        return pin;
    }
    
    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
    
    
    
    /**
     * @return the welcomeBean
     */
    public WelcomeBean getWelcomeBean() {
        return welcomeBean;
    }
    
    /**
     * @param welcomeBean the welcomeBean to set
     */
    public void setWelcomeBean(WelcomeBean welcomeBean) {
        this.welcomeBean = welcomeBean;
    }
    
    /**
     * @return the registerRequest
     */
    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }
    
    /**
     * @param registerRequest the registerRequest to set
     */
    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }
    
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
    
    /**
     * @return the rechargeBean
     */
    public RechargeBean getRechargeBean() {
        return rechargeBean;
    }
    
    /**
     * @param rechargeBean the rechargeBean to set
     */
    public void setRechargeBean(RechargeBean rechargeBean) {
        this.rechargeBean = rechargeBean;
    }
    
    /**
     * @return the lm
     */
    public LoginModel getLm() {
        return lm;
    }
    
    /**
     * @param lm the lm to set
     */
    public void setLm(LoginModel lm) {
        this.lm = lm;
    }
    
    
    
    
}
