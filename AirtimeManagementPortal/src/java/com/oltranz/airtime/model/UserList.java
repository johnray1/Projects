/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtime.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class UserList {
    
    
    @JsonProperty("users")
    private List<User> userList;

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    
    
}
