/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.models;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */

public class TankModel {
    
    @JsonProperty("tankId")
    private Integer tankId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("maxCapacity")
    private Double maxCapacity;
    
    @JsonProperty("currentCapacity")
    private Double currentCapacity;
    
    @JsonProperty("dippedCapacity")
    private Double dippedCapacity;
    
    @JsonProperty("diff")
    private Double diff;
    
    @JsonProperty("dippedTime")
    private Date dippedTime;
    
    @JsonProperty("preCalibrationDate")
    private Date preCalibrationDate;
    
    @JsonProperty("nextCalibrationDate")
    private Date nextCalibrationDate;
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("branchId")
    private Integer branchId;
    
   
    @JsonProperty("productId")
    private Integer productId;

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
     * @return the dippedCapacity
     */
    public Double getDippedCapacity() {
        return dippedCapacity;
    }

    /**
     * @param dippedCapacity the dippedCapacity to set
     */
    public void setDippedCapacity(Double dippedCapacity) {
        this.dippedCapacity = dippedCapacity;
    }

    /**
     * @return the diff
     */
    public Double getDiff() {
        return diff;
    }

    /**
     * @param diff the diff to set
     */
    public void setDiff(Double diff) {
        this.diff = diff;
    }

    /**
     * @return the dippedTime
     */
    public Date getDippedTime() {
        return dippedTime;
    }

    /**
     * @param dippedTime the dippedTime to set
     */
    public void setDippedTime(Date dippedTime) {
        this.dippedTime = dippedTime;
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
   

    

}