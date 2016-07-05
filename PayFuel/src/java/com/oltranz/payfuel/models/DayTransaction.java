/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class DayTransaction {
    
    @JsonProperty("userId")
    private int userId;
    
    @JsonProperty("deviceTransactionDate")
    private String deviceTransactionDate;

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the deviceTransactionDate
     */
    public String getDeviceTransactionDate() {
        return deviceTransactionDate;
    }

    /**
     * @param deviceTransactionDate the deviceTransactionDate to set
     */
    public void setDeviceTransactionDate(String deviceTransactionDate) {
        this.deviceTransactionDate = deviceTransactionDate;
    }

    
    
    
    
}
