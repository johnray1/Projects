/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.models;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class Tanks {
    
    @JsonProperty("tankId")
    private Integer tankId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("maxCapacity")
    private Double maxCapacity;
    
    @JsonProperty("currentCapacity")
    private Double currentCapacity;
    
    @JsonProperty("dippedCapacity")
    private double dippedCapacity;
    
    @JsonProperty("preCalibrationDate")
    private Date preCalibrationDate;
    
    @JsonProperty("nextCalibrationDate")
    private Date nextCalibrationDate;
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("statusName")
    private String statusName;
    
    @JsonProperty("branchId")
    private Integer branchId;
    
    @JsonProperty("branchName")
    private String branchName;
    
    @JsonProperty("productId")
    private Integer productId;
    
    @JsonProperty("productName")
    private String productName;

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
     * @return the maxCapacity
     */
    public Double getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the currentCapacity
     */
    public Double getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * @param currentCapacity the currentCapacity to set
     */
    public void setCurrentCapacity(Double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * @return the preCalibrationDate
     */
    public Date getPreCalibrationDate() {
        return preCalibrationDate;
    }

    /**
     * @param preCalibrationDate the preCalibrationDate to set
     */
    public void setPreCalibrationDate(Date preCalibrationDate) {
        this.preCalibrationDate = preCalibrationDate;
    }

    /**
     * @return the nextCalibrationDate
     */
    public Date getNextCalibrationDate() {
        return nextCalibrationDate;
    }

    /**
     * @param nextCalibrationDate the nextCalibrationDate to set
     */
    public void setNextCalibrationDate(Date nextCalibrationDate) {
        this.nextCalibrationDate = nextCalibrationDate;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the statusName
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName the statusName to set
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
     * @return the dippedCapacity
     */
    public double getDippedCapacity() {
        return dippedCapacity;
    }

    /**
     * @param dippedCapacity the dippedCapacity to set
     */
    public void setDippedCapacity(double dippedCapacity) {
        this.dippedCapacity = dippedCapacity;
    }
    
    
}
