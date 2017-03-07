/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.models;

import java.util.Date;

/**
 *
 * @author JohnRay
 */

public class Transaction{
    
    
    private Long transactionId;
    
    private Long deviceTransactionId;
    
    private Date deviceTransactionTime;
    
    private Integer branchId;
    
    private Integer userId;
    
    private Integer deviceId;
    
    private Integer tankId;
    
    private Integer pumpId;
    
    private Integer nozzleId;
    
    private Integer productId;
    
    private Long customerId;
   
    private Integer paymentModeId;
    
    private String paymentStatus;
    
    private Double amount;
    
    private Double quantity;
    
    private String platenumber;
    
    private Date serverReqTime;
    
    private Date serverResTime;
    
    private Date date;

    /**
     * @return the transactionId
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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
    public Date getDeviceTransactionTime() {
        return deviceTransactionTime;
    }

    /**
     * @param deviceTransactionTime the deviceTransactionTime to set
     */
    public void setDeviceTransactionTime(Date deviceTransactionTime) {
        this.deviceTransactionTime = deviceTransactionTime;
    }

    /**
     * @return the branchId
     */
    public Integer getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

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
     * @return the deviceId
     */
    public Integer getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the tankId
     */
    public Integer getTankId() {
        return tankId;
    }

    /**
     * @param tankId the tankId to set
     */
    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    /**
     * @return the pumpId
     */
    public Integer getPumpId() {
        return pumpId;
    }

    /**
     * @param pumpId the pumpId to set
     */
    public void setPumpId(Integer pumpId) {
        this.pumpId = pumpId;
    }

    /**
     * @return the nozzleId
     */
    public Integer getNozzleId() {
        return nozzleId;
    }

    /**
     * @param nozzleId the nozzleId to set
     */
    public void setNozzleId(Integer nozzleId) {
        this.nozzleId = nozzleId;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the customerId
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the paymentModeId
     */
    public Integer getPaymentModeId() {
        return paymentModeId;
    }

    /**
     * @param paymentModeId the paymentModeId to set
     */
    public void setPaymentModeId(Integer paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the platenumber
     */
    public String getPlatenumber() {
        return platenumber;
    }

    /**
     * @param platenumber the platenumber to set
     */
    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    /**
     * @return the serverReqTime
     */
    public Date getServerReqTime() {
        return serverReqTime;
    }

    /**
     * @param serverReqTime the serverReqTime to set
     */
    public void setServerReqTime(Date serverReqTime) {
        this.serverReqTime = serverReqTime;
    }

    /**
     * @return the serverResTime
     */
    public Date getServerResTime() {
        return serverResTime;
    }

    /**
     * @param serverResTime the serverResTime to set
     */
    public void setServerResTime(Date serverResTime) {
        this.serverResTime = serverResTime;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    
}
