/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.chartmodel;

import java.util.List;

/**
 *
 * @author John
 */
public class TankDash {
    private int tankId;
    private String tankName;
    private double maxCapacity;
    private double currentCapacity;
    private List<PumpDash> pumpDash;

    
    
    
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
     * @return the maxCapacity
     */
    public double getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the currentCapacity
     */
    public double getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * @param currentCapacity the currentCapacity to set
     */
    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * @return the pumpDash
     */
    public List<PumpDash> getPumpDash() {
        return pumpDash;
    }

    /**
     * @param pumpDash the pumpDash to set
     */
    public void setPumpDash(List<PumpDash> pumpDash) {
        this.pumpDash = pumpDash;
    }

    /**
     * @return the tankId
     */
    public int getTankId() {
        return tankId;
    }

    /**
     * @param tankId the tankId to set
     */
    public void setTankId(int tankId) {
        this.tankId = tankId;
    }

    
    
    
}
