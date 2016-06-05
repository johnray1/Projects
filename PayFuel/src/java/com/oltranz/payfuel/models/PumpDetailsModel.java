/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.models;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class PumpDetailsModel {
    
    @JsonProperty("pumpId")
    private Integer pumpId;
    
    @JsonProperty("pumpName")
    private String pumpName;
    
    @JsonProperty("branchId")
    private int branchId;
    
    @JsonProperty("status")
    private int status;
    
    @JsonProperty("nozzleList")
    private List<NozzleDetailsModel> nozzleList;

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
     * @return the nozzleList
     */
    public List<NozzleDetailsModel> getNozzleList() {
        return nozzleList;
    }

    /**
     * @param nozzleList the nozzleList to set
     */
    public void setNozzleList(List<NozzleDetailsModel> nozzleList) {
        this.nozzleList = nozzleList;
    }

    
    
    
}
