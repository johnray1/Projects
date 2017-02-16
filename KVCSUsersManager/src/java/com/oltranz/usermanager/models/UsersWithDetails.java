/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.usermanager.models;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */
public class UsersWithDetails {
     @JsonProperty("userdetails")
   private List<UserDetailsModel> usersList;

    /**
     * @return the usersList
     */
    public List<UserDetailsModel> getUsersList() {
        return usersList;
    }

    /**
     * @param usersList the usersList to set
     */
    public void setUsersList(List<UserDetailsModel> usersList) {
        this.usersList = usersList;
    }

    
}
