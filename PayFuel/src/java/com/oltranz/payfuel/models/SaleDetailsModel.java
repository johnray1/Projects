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
public class SaleDetailsModel {
    
    @JsonProperty("deviceTransactionId")
    private Long deviceTransactionId;
    
    @JsonProperty("deviceTransactionTime")
    private String deviceTransactionTime;
    
    @JsonProperty("branchId")
    private int branchId;
    
    @JsonProperty("userId")
    private int userId;
    
    @JsonProperty("deviceId")
    private String deviceId;
    
    @JsonProperty("pumpId")
    private int pumpId;
    
    @JsonProperty("nozzleId")
    private int nozzleId;
    
    @JsonProperty("productId")
    private int productId;
    
    @JsonProperty("paymentModeId")
    private int paymentModeId;
    
    @JsonProperty("amount")
    private Double amount;
    
    @JsonProperty("quantity")
    private Double quantity;
    
    @JsonProperty("plateNumber")
    private String plateNumber;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("telephone")
    private String telephone;
    
    @JsonProperty("tin")
    private String tin;
    
    @JsonProperty("voucherNumber")
    private String voucherNumber;
    
    @JsonProperty("authenticationCode")
    private int authenticationCode;
    
    @JsonProperty("authorisationCode")
    private String authorisationCode;
    
    @JsonProperty("status")
    private int status;

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
     * @return the pumpId
     */
    public int getPumpId() {
        return pumpId;
    }

    /**
     * @param pumpId the pumpId to set
     */
    public void setPumpId(int pumpId) {
        this.pumpId = pumpId;
    }

    /**
     * @return the nozzleId
     */
    public int getNozzleId() {
        return nozzleId;
    }

    /**
     * @param nozzleId the nozzleId to set
     */
    public void setNozzleId(int nozzleId) {
        this.nozzleId = nozzleId;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the paymentModeId
     */
    public int getPaymentModeId() {
        return paymentModeId;
    }

    /**
     * @param paymentModeId the paymentModeId to set
     */
    public void setPaymentModeId(int paymentModeId) {
        this.paymentModeId = paymentModeId;
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
     * @return the plateNumber
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * @param plateNumber the plateNumber to set
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the tin
     */
    public String getTin() {
        return tin;
    }

    /**
     * @param tin the tin to set
     */
    public void setTin(String tin) {
        this.tin = tin;
    }

    /**
     * @return the voucherNumber
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }

    /**
     * @param voucherNumber the voucherNumber to set
     */
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    /**
     * @return the authenticationCode
     */
    public int getAuthenticationCode() {
        return authenticationCode;
    }

    /**
     * @param authenticationCode the authenticationCode to set
     */
    public void setAuthenticationCode(int authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    /**
     * @return the authorisationCode
     */
    public String getAuthorisationCode() {
        return authorisationCode;
    }

    /**
     * @param authorisationCode the authorisationCode to set
     */
    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    
}
