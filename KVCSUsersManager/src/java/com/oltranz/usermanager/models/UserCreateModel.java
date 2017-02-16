/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.usermanager.models;

/**
 *
 * @author manzi
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class UserCreateModel {
    
    @JsonProperty("actionUserId")
    private Integer actionUserId; 
    
    @JsonProperty("fname")
    private String fname;
    
    @JsonProperty("otherNames")
    private String otherNames;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("repeatPassword")
    private String repeatPassword;  
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @JsonProperty("details")
    private String details;
    
    
    /**
     * @return the actionUserId
     */
    public Integer getActionUserId() {
        return actionUserId;
    }

    /**
     * @param actionUserId the actionUserId to set
     */
    public void setActionUserId(Integer actionUserId) {
        this.actionUserId = actionUserId;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the otherNames
     */
    public String getOtherNames() {
        return otherNames;
    }

    /**
     * @param otherNames the otherNames to set
     */
    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
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
     * @return the repeatPassword
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * @param repeatPassword the repeatPassword to set
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
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

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    
    
    
}

