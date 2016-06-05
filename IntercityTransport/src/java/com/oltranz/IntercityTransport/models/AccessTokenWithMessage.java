/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.models;

import org.codehaus.jackson.annotate.JsonProperty;



/**
 *
 * @author manzi
 */
public class AccessTokenWithMessage {
    @JsonProperty("accesstoken")
    private AccessToken accesstoken;
    
    @JsonProperty("message")
    private String message;

    /**
     * @return the accesstoken
     */
    public AccessToken getAccesstoken() {
        return accesstoken;
    }

    /**
     * @param accesstoken the accesstoken to set
     */
    public void setAccesstoken(AccessToken accesstoken) {
        this.accesstoken = accesstoken;
    }

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

      

}
