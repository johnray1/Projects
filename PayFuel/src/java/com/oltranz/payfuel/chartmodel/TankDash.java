/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.chartmodel;

import java.util.List;

/**
 *
 * @author John
 */
public class TankDash {
    
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
    
    
    
}
