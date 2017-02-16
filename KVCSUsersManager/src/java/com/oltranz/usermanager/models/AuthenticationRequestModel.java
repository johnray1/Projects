/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */

public class AuthenticationRequestModel {
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("pin")
    private String pin;
    
    @JsonProperty("userName")
    private String userName;
    
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @JsonProperty("password")
    private String password;
    
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }
    
    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }
    
    
}
