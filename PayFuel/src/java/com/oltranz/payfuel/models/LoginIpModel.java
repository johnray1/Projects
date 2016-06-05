/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */

public class LoginIpModel {
    
    @JsonProperty("deviceId")
    private String deviceId; 
    
    @JsonProperty("userPin")
    private String userPin;

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the userPin
     */
    public String getUserPin() {
        return userPin;
    }

    /**
     * @param userPin the userPin to set
     */
    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    
    
    
    
}
