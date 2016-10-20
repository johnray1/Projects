/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class AsyncTransaction {
    
    @JsonProperty("deviceTransactionId")
    private Long deviceTransactionId;
    
    @JsonProperty("branchId")
    private int branchId; 
    
    @JsonProperty("userId")
    private int userId;
    
    @JsonProperty("deviceId")
    private String deviceId; 

    

    /**
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

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
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the deviceTransactionId
     */
    public Long getDeviceTransactionId() {
        return deviceTransactionId;
    }

    /**
     * @param deviceTransactionId the deviceTransactionId to set
     */
    public void setDeviceTransactionId(Long deviceTransactionId) {
        this.deviceTransactionId = deviceTransactionId;
    }

    

    

    

    
   
    
}
