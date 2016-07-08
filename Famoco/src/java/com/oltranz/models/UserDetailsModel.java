/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.models;


import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */

public class UserDetailsModel {
    
    @JsonProperty("userId")
    private Integer userId;
    
    @JsonProperty("fname")
    private String fname;
    
    @JsonProperty("otherNames")
    private String otherNames;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    
    @JsonProperty("details")
    private String details;
    
    @JsonProperty("permission")
    private String permissions;
    
    @JsonProperty("roles")
    private List<UserRole> roles;
    
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
     * @return the roles
     */
    public List<UserRole> getRoles() {
        return roles;
    }
    
    /**
     * @param roles the roles to set
     */
    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
    
    
    
    
    
    
}
