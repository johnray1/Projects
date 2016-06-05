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

public class UserEditModel {
    
    @JsonProperty("actionUserId")
    private Integer actionUserId; 
//    
//    @JsonProperty("actionUserPassword")
//    private String actionUserPassword; 
    
     @JsonProperty("userId")
    private Integer userId;
    
     
    @JsonProperty("fname")
    private String fname;
    
    @JsonProperty("otherNames")
    private String otherNames;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("repeatPassword=")
    private String repeatPassword;  
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @JsonProperty("details")
    private String details;
    
      public UserEditModel(){
        
    }
    public UserEditModel(String fname,String otherNames,String email,String password,String repeatPassword,String PhoneNumber,String gender,String details){
        this.fname=fname;
        this.otherNames=otherNames;
        this.email=email;
        this.password=password;
        this.repeatPassword=repeatPassword;
        this.phoneNumber=PhoneNumber;
        this.gender=gender;
        this.details=details;        
    }
      public UserEditModel(String fname,String otherNames,String email,String password,String repeatPassword,String PhoneNumber,String gender,String permissions,String details){
        this.fname=fname;
        this.otherNames=otherNames;
        this.email=email;
        this.password=password;
        this.repeatPassword=repeatPassword;
        this.phoneNumber=PhoneNumber;
        this.gender=gender;
        this.details=details;  
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
     * @return the Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.password = Password;
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
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

  
    /**
     * @return the userId
     */
   
}
