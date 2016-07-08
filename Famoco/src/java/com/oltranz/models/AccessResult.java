/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author ismaelnzamutuma
 */
public class AccessResult {
   @JsonProperty("accesstoken")
    private AccessToken accessTocken;
   @JsonProperty("message")
    private String message;

   
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
     * @return the accessTocken
     */
    public AccessToken getAccessTocken() {
        return accessTocken;
    }

    /**
     * @param accessTocken the accessTocken to set
     */
    public void setAccessTocken(AccessToken accessTocken) {
        this.accessTocken = accessTocken;
    }
}
