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
public class SellingDeviceRegistrationModel {
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("password")
    private String deviceName;
    
    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }
    
    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
}
