/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author John
 */
@ManagedBean(name="CustomerBean")
@SessionScoped
public class CustomerBean {
    
    private String oldpin,newpin,repin;
    
    private Boolean faceMessage=false;
    private String msg;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    
    public void customerAccount(){
        
        templateBean.setWelcomeClassName("deactive");
        templateBean.setAccountClassName("active");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try{
            faceMessage=false;
            FacesContext.getCurrentInstance().getExternalContext().redirect("account.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * @return the oldpin
     */
    public String getOldpin() {
        return oldpin;
    }

    /**
     * @param oldpin the oldpin to set
     */
    public void setOldpin(String oldpin) {
        this.oldpin = oldpin;
    }

    /**
     * @return the newpin
     */
    public String getNewpin() {
        return newpin;
    }

    /**
     * @param newpin the newpin to set
     */
    public void setNewpin(String newpin) {
        this.newpin = newpin;
    }

    /**
     * @return the repin
     */
    public String getRepin() {
        return repin;
    }

    /**
     * @param repin the repin to set
     */
    public void setRepin(String repin) {
        this.repin = repin;
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
