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

public class AuthenticationResponseModel {
    
    @JsonProperty("permissions")
    private String permissions;
    
    @JsonProperty("userId")
    private String userId;
    
    @JsonProperty("userName")
    private String userName;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("phoneMumber")
    private String phoneMumber;
    
    @JsonProperty("fname")
    private String fname;
    
    @JsonProperty("otherNames")
    private String otherNames;
    
    @JsonProperty("status")
    private SimpleStatus status;
    
    /**
     * @return the permissions
     */
    public String getPermissions() {
        return permissions;
    }
    
    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
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
     * @return the status
     */
    public SimpleStatus getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(SimpleStatus status) {
        this.status = status;
    }
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return the phoneMumber
     */
    public String getPhoneMumber() {
        return phoneMumber;
    }
    
    /**
     * @param phoneMumber the phoneMumber to set
     */
    public void setPhoneMumber(String phoneMumber) {
        this.phoneMumber = phoneMumber;
    }
    
    
    
}
