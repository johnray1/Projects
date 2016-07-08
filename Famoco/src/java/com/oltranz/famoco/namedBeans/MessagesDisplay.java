/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.namedBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ismaelnzamutuma
 */
@ManagedBean(name="requestMessages")
@ViewScoped
public class MessagesDisplay implements Serializable {
   private String loginFailure;

    /**
     * @return the loginFailure
     */
    public String getLoginFailure() {
        return loginFailure;
    }

    /**
     * @param loginFailure the loginFailure to set
     */
    public void setLoginFailure(String loginFailure) {
        this.loginFailure = loginFailure;
        
        
        
    }
   
}
