/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.dao.LoginDAO;
import com.oltranz.sppayfuelportal.model.UserDetails;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
    
    private Integer userId;
    private Integer bId;
    private String branchName;
    
    private Date date;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{BranchBean}")
    private BranchBean branchBean;
    
    @ManagedProperty(value="#{DeviceBean}")
    private DeviceBean deviceBean;
    
    @ManagedProperty(value="#{PumpBean}")
    private PumpBean pumpBean;
    
    @ManagedProperty(value="#{TransactionBean}")
    private TransactionBean transactionBean;
    
    @ManagedProperty(value="#{TankBean}")
    private TankBean tankBean;
    
    
    
    //validate login
    public String validateUsernamePassword() {
        date=new Date();
        
        templateBean.setDashboardClassName("active");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("deactive");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setSettingClassName("deactive");
        
        try{
            UserDetails ud = LoginDAO.validate(user, pwd);
            
            if (ud.getStatusCode()==100) {
                
                userId=ud.getUserDetailsModel().getUserId();
                bId=ud.getUserDetailsModel().getBranchId();
                user=ud.getUserDetailsModel().getFname();
                branchName=ud.getUserDetailsModel().getBranchName();
                
                //set branch Id For Beans
                
                branchBean.setbId(bId);
                deviceBean.setbId(bId);
                tankBean.setbId(bId);
                pumpBean.setbId(bId);
                transactionBean.setbId(bId);
                
                tankBean.tank1Dashboard();
                tankBean.tank2Dashboard();
                tankBean.tank3Dashboard();
                
                
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username",user );
                session.setAttribute("permissions", ud.getUserDetailsModel().getPermissions());
                session.setMaxInactiveInterval(30*60);
                
                if(userId!=1){
                    
                    templateBean.setHideClassName("hide");
                    templateBean.setHideDashHq("hide");
                    return "dashboard?faces-redirect=true";
                }
                else{
                    templateBean.setHideDash("hide");
                    return "dashboard_hq?faces-redirect=true";
                }
                
            }
            else {
                
                return "login.xhtml?faces-redirect=true";
            }
        }
        catch(Exception ex){
            return "login.xhtml?faces-redirect=true";
        }
    }
    
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login.xhtml?faces-redirect=true";
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
    
    /**
     * @return the tankBean
     */
    public TankBean getTankBean() {
        return tankBean;
    }
    
    /**
     * @param tankBean the tankBean to set
     */
    public void setTankBean(TankBean tankBean) {
        this.tankBean = tankBean;
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
     * @return the bId
     */
    public Integer getbId() {
        return bId;
    }
    
    /**
     * @param bId the bId to set
     */
    public void setbId(Integer bId) {
        this.bId = bId;
    }
    
    
    
    /**
     * @return the deviceBean
     */
    public DeviceBean getDeviceBean() {
        return deviceBean;
    }
    
    /**
     * @param deviceBean the deviceBean to set
     */
    public void setDeviceBean(DeviceBean deviceBean) {
        this.deviceBean = deviceBean;
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
     * @return the pumpBean
     */
    public PumpBean getPumpBean() {
        return pumpBean;
    }
    
    /**
     * @param pumpBean the pumpBean to set
     */
    public void setPumpBean(PumpBean pumpBean) {
        this.pumpBean = pumpBean;
    }
    
    /**
     * @return the branchBean
     */
    public BranchBean getBranchBean() {
        return branchBean;
    }
    
    /**
     * @param branchBean the branchBean to set
     */
    public void setBranchBean(BranchBean branchBean) {
        this.branchBean = branchBean;
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
    
    
    
    
    
    
}
