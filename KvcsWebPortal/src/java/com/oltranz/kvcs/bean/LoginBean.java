/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcs.bean;



import java.io.Serializable;
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
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{DashBean}")
    private DashBean dashBean;
    
    private String username;
    private String pwd;
    
    
    
    public void login(){
        
        templateBean.setDashboardClassName("active");
        templateBean.setTransactionClassName("deactive");
        
        try {
            dashBean.dashFilter(1);
            
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username",username);
            session.setMaxInactiveInterval(30*60);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("dash.xhtml");
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
