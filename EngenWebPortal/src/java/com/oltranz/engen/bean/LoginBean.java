/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.dao.LoginDAO;
import com.oltranz.engen.model.UserDetails;
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
    
    private String pwd;
    private String msg;
    private String user;
    private Boolean faceMessage=false;
    private UserDetails ud;
    
    
    private String branchName;
    private String userName;
    
    private Date date;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    @ManagedProperty(value="#{TpnBean}")
    private TpnBean tpnBean;
    
    
    
    
    
    //validate login
    public void validateUsernamePassword() {
        
        date=new Date();
        
        templateBean.setDashboardClassName("omenu_active");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu");
        
        try{
            ud = LoginDAO.validate(user, pwd);
            
            if (ud.getStatusCode()==100) {
                
                branchName=ud.getUserDetailsModel().getBranchName();
                userName=ud.getUserDetailsModel().getFname();
                
                
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("userId",ud.getUserDetailsModel().getUserId());
                session.setAttribute("username",userName);
                session.setAttribute("permissions", ud.getUserDetailsModel().getPermissions());
                session.setAttribute("branchId", ud.getUserDetailsModel().getBranchId());
                session.setMaxInactiveInterval(30*60);
                
                
                templateBean.paymentModeChart();
                tpnBean.tankDashboard();
                
                if(ud.getUserDetailsModel().getUserId()!=1){
                    
                    templateBean.setHideClassName("hide");
                    templateBean.setHideDashHq("hide");
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
                    
                }
                else{
                    templateBean.setHideDash("hide");
                    
                    FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard_hq.xhtml");
                    
                }
                
            }
            else {
                faceMessage=true;
                msg="Your access code doesn't match, Please Try again!";
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                
            }
        }
        catch(Exception ex){
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //logout event, invalidate session
    public void logout() {
        
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }
    
    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
    
//    /**
//     * @return the tpnBean
//     */
//    public TpnBean getTpnBean() {
//        return tpnBean;
//    }
//
//    /**
//     * @param tpnBean the tpnBean to set
//     */
//    public void setTpnBean(TpnBean tpnBean) {
//        this.tpnBean = tpnBean;
//    }
    
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
     * @return the tpnBean
     */
    public TpnBean getTpnBean() {
        return tpnBean;
    }

    /**
     * @param tpnBean the tpnBean to set
     */
    public void setTpnBean(TpnBean tpnBean) {
        this.tpnBean = tpnBean;
    }
    
    
    
}
