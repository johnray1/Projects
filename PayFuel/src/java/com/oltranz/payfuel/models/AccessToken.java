/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.models;


import java.util.Date;

/**
 *
 * @author manzi
 */
public class AccessToken {
    
    private UserDetailsModel userDetails;
    private Date creationTime;
    private Date ExpirationTime;

      

    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
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
    public Date getExpirationTime() {
        return ExpirationTime;
    }

    /**
     * @param ExpirationTime the ExpirationTime to set
     */
    public void setExpirationTime(Date ExpirationTime) {
        this.ExpirationTime = ExpirationTime;
    }

   
}
