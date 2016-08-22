/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;


import java.io.IOException;
import java.io.Serializable;
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
@ManagedBean(name="UserBean")
@SessionScoped
public class UserBean implements Serializable{
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
   
    
    public void users(){
        templateBean.setWelcomeClassName("deactive");
        templateBean.setUserClassName("active");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("user.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    
}
