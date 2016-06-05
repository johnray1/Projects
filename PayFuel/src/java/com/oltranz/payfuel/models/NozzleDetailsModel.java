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
public class NozzleDetailsModel {
    
    @JsonProperty("nozzleId")
    private Integer nozzleId;
    
    @JsonProperty("nozzleName")
    private String nozzleName;
    
    @JsonProperty("nozzleIndex")
    private double nozzleIndex;
    
    @JsonProperty("status")
    private int status;
    
    @JsonProperty("productId")
    private int productId;
    
    @JsonProperty("productName")
    private String productName;
    
    @JsonProperty("unitPrice")
    private double unitPrice;
    
    @JsonProperty("userName")
    private String userName;

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
     * @return the nozzleName
     */
    public String getNozzleName() {
        return nozzleName;
    }

    /**
     * @param nozzleName the nozzleName to set
     */
    public void setNozzleName(String nozzleName) {
        this.nozzleName = nozzleName;
    }

    /**
     * @return the nozzleIndex
     */
    public double getNozzleIndex() {
        return nozzleIndex;
    }

    /**
     * @param nozzleIndex the nozzleIndex to set
     */
    public void setNozzleIndex(double nozzleIndex) {
        this.nozzleIndex = nozzleIndex;
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
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
