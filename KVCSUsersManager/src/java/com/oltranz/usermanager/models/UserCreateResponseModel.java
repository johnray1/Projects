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

public class UserCreateResponseModel {
    @JsonProperty("userDetails")
    private UserDetailsModel userDetails;
    
    @JsonProperty("status")
    private SimpleStatus status;
    
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
    
}
