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
public class TankDetailsModel {
    
    @JsonProperty("branchName")
    private String branchName;
    
    @JsonProperty("tankName")
    private String tankName;
    
    @JsonProperty("pumpList")
    private List<PumpDetailsModel> pumpList;

    

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
     * @return the pumpList
     */
    public List<PumpDetailsModel> getPumpList() {
        return pumpList;
    }

    /**
     * @param pumpList the pumpList to set
     */
    public void setPumpList(List<PumpDetailsModel> pumpList) {
        this.pumpList = pumpList;
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
    
}
