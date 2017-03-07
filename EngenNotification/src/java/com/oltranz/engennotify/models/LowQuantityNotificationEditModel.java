/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.models;

/**
 *
 * @author JohnRay
 */
public class LowQuantityNotificationEditModel {
    
    private String id;
    private int branchId;
    private int tankId;
    private Double minCapacity;
    private String createdBy;
    private int checkingPeriod;
    private int count;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
    
    /**
     * @return the minCapacity
     */
    public Double getMinCapacity() {
        return minCapacity;
    }
    
    /**
     * @param minCapacity the minCapacity to set
     */
    public void setMinCapacity(Double minCapacity) {
        this.minCapacity = minCapacity;
    }
    
    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the checkingPeriod
     */
    public int getCheckingPeriod() {
        return checkingPeriod;
    }

    /**
     * @param checkingPeriod the checkingPeriod to set
     */
    public void setCheckingPeriod(int checkingPeriod) {
        this.checkingPeriod = checkingPeriod;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    
    
    
    
    
}
