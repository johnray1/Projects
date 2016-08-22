/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;



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
    private String username;
    private String pwd;
    private Boolean faceMessage=false;
    private String msg;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
   
    
    
    public void login(){
        
        templateBean.setWelcomeClassName("active");
        templateBean.setUserClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try {
            
            int statusCode=400;
            
            if (statusCode==400) {
                
                
                username="john";
                
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username",username);
                session.setMaxInactiveInterval(30*60);
                
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
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
    
    
    
}
