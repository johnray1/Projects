/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.models;



/**
 *
 * @author manzi
 */
public class AccessToken {
    
    private UserDetailsModel userDetails;
    private String creationTime;
    private String ExpirationTime;

      

    /**
     * @return the creationTime
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return the userDetails
     */
    public UserDetailsModel getUserDetails() {
        return userDetails;
    }

    /**
     * @param userDetails the userDetails to set
     */
    public void setUserDetails(UserDetailsModel userDetails) {
        this.userDetails = userDetails;
    }

    /**
     * @return the ExpirationTime
     */
    public String getExpirationTime() {
        return ExpirationTime;
    }

    /**
     * @param ExpirationTime the ExpirationTime to set
     */
    public void setExpirationTime(String ExpirationTime) {
        this.ExpirationTime = ExpirationTime;
    }

   
}
