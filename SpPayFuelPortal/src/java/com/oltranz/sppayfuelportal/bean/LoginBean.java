/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.dao.LoginDAO;
import com.oltranz.sppayfuelportal.model.UserDetails;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
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
    
    
    
    private String pwd;
    private String msg;
    private String user;
    private Integer userId;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    //validate login
    public String validateUsernamePassword() {
        
        templateBean.setDashboardClassName("omenu_active");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        
        try{
            UserDetails ud = LoginDAO.validate(user, pwd);
            
            if (ud.getStatusCode()==100) {
                
                userId=ud.getUserDetailsModel().getUserId();
                user=ud.getUserDetailsModel().getFname();
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username",user );
                session.setAttribute("permissions", ud.getUserDetailsModel().getPermissions());
                
                
                //setting session to expiry in 8 mins
                session.setMaxInactiveInterval(30*60);
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("dashboard.xhtml");
                
                return "dashboard?faces-redirect=true";
            }
            else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Passowrd",
                                "Please enter correct username and Password"
                        )
                        
                );
                return "login.xhtml";
            }
        }
        catch(Exception ex){
            System.out.println("Login error -->" + ex.getMessage());
            return "login";
        }
    }
    
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
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
     * @return the user
     */
    public String getUser() {
        return user;
    }
    
    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    
    
}
