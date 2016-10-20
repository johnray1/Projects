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
public class SaleCancelModel {
    
    @JsonProperty("adminId")
    private int adminId;
    
    @JsonProperty("deviceTransactionId")
    private Long deviceTransactionId;
    
    @JsonProperty("deviceTransactionTime")
    private String deviceTransactionTime;
    
    @JsonProperty("userId")
    private int userId;

    /**
     * @return the adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * @param adminId the adminId to set
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    /**
     * @return the deviceTransactionTime
     */
    public String getDeviceTransactionTime() {
        return deviceTransactionTime;
    }

    /**
     * @param deviceTransactionTime the deviceTransactionTime to set
     */
    public void setDeviceTransactionTime(String deviceTransactionTime) {
        this.deviceTransactionTime = deviceTransactionTime;
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
    

    
    
    
}
