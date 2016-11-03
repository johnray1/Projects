/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.dao.LoginDAO;
import com.oltranz.airtime.model.LoginModel;
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
    
    private String username;
    private String password;
    private String message;
    private Boolean faceMessage=false;
    private Date date=new Date();
    
    @ManagedProperty(value="#{DashBean}")
    private DashBean dashBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{CustomerBean}")
    private CustomerBean customerBean;
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    public void login(){
        
        templateBean.setDashboardClassName("active");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLogClassName("deactive");
        
        try{
            LoginModel lm=LoginDAO.validate(username, password);
            int statusCode=lm.getStatus().getStatusCode();
            
            if (statusCode==200) {
                
                username=lm.getUser().getfName();
                dashBean.traPerFilter(1);
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username",username );
                session.setMaxInactiveInterval(30*60);
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
            }
            else{
                faceMessage=true;
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            }
        }
        catch(Exception ex){
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
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return the dashBean
     */
    public DashBean getDashBean() {
        return dashBean;
    }

    /**
     * @param dashBean the dashBean to set
     */
    public void setDashBean(DashBean dashBean) {
        this.dashBean = dashBean;
    }
    
    
    
    
}
