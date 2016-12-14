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

/**
 *
 * @author John
 */
@ManagedBean(name="TransactionBean")
@SessionScoped
public class TransactionBean implements Serializable{
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public void transactions(){
        
        getTemplateBean().setDashboardClassName("deactive");
        getTemplateBean().setTransactionClassName("active");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("tra.xhtml");
        }
        catch (Exception ex) {
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
