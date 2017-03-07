/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class UserDetails {
    
    @JsonProperty("UserDetailsModel")
    private UserDetailsModel userDetailsModel;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("statusCode")
    private Integer statusCode;

    

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the userDetailsModel
     */
    public UserDetailsModel getUserDetailsModel() {
        return userDetailsModel;
    }

    /**
     * @param userDetailsModel the userDetailsModel to set
     */
    public void setUserDetailsModel(UserDetailsModel userDetailsModel) {
        this.userDetailsModel = userDetailsModel;
    }

    
    
    
    
}
