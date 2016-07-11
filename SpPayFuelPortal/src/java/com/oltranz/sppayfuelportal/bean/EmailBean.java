/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author John
 */
@ManagedBean(name="EmailBean")
@SessionScoped
public class EmailBean {
    
    private String email;
    
    public String email(){
        
        String url="http://localhost:8080/PetroStationManager/portalmanager/sendmail/"+email;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.TEXT_PLAIN, "GET");
        String jsonResponse = response.readEntity(String.class);
        this.setEmail(null);
        
        return "forgotpswd.xhtml";
        
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
