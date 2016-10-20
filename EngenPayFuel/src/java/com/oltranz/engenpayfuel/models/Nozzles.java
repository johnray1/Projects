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
 * @author JohnRay
 */
public class Nozzles {
    
    @JsonProperty("branchId")
    private Integer branchId;

    @JsonProperty("branchName")
    private String branchName;
    
    @JsonProperty("tankId")
    private Integer tankId;

    @JsonProperty("tankName")
    private String tankName;

    @JsonProperty("pumpId")
    private Integer pumpId;

    @JsonProperty("pumpName")
    private String pumpName;
    
    @JsonProperty("nozzleId")
    private Integer nozzleId;

    @JsonProperty("nozzleName")
    private String nozzleName;
    
    @JsonProperty("index")
    private double index;
    
    @JsonProperty("preCalibrationDate")
    private Date preCalibrationDate;
    
    @JsonProperty("nextCalibrationDate")
    private Date nextCalibrationDate;

    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("statusName")
    private String statusName;

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
     * @return the tankName
     */
    public String getTankName() {
        return tankName;
    }

    /**
     * @param tankName the tankName to set
     */
    public void setTankName(String tankName) {
        this.tankName = tankName;
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
     * @return the pumpName
     */
    public String getPumpName() {
        return pumpName;
    }

    /**
     * @param pumpName the pumpName to set
     */
    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
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
     * @return the index
     */
    public double getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(double index) {
        this.index = index;
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
    
    

    

    

}
