/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;




import com.oltranz.ignite.dao.LoginDAO;
import com.oltranz.ignite.model.UserDetails;
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
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{DashBean}")
    private DashBean dashBean;
    
    @ManagedProperty(value="#{LiquidBean}")
    private LiquidBean liquidBean;
    
    private UserDetails ud;
    
    private Date date=new Date();
    
    private String user;
    private String pwd;
    private Boolean faceMessage=false;
    private String msg;
    
    private String userName;
    
    
    
    public void login(){
        
        templateBean.setDashboardClassName("active");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLiquidClassName("deactive");
        try {
            
            ud = LoginDAO.validate(user, pwd);
            
            if ((user.equals("admin@oltranz.com"))&&(pwd.equals("admin"))) {
                
                userName=ud.getUserDetailsModel().getUsername();
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username",userName);
                session.setAttribute("orgTypeId",ud.getUserDetailsModel().getOrganizationTypeId());
                session.setAttribute("orgId",ud.getUserDetailsModel().getOrganizationId());
                session.setMaxInactiveInterval(30*60);
                
                dashBean.dashFilter(5);
                liquidBean.liquidList();
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("dash.xhtml");
            }
            else{
                
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return the ud
     */
    public UserDetails getUd() {
        return ud;
    }
    
    /**
     * @param ud the ud to set
     */
    public void setUd(UserDetails ud) {
        this.ud = ud;
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

    /**
     * @return the liquidBean
     */
    public LiquidBean getLiquidBean() {
        return liquidBean;
    }

    /**
     * @param liquidBean the liquidBean to set
     */
    public void setLiquidBean(LiquidBean liquidBean) {
        this.liquidBean = liquidBean;
    }
    
    
    
    
}
