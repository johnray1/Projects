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
public class BizzareTxnNotificationEditModel {
    
    private String id;
    
    private int branchId;
    
    private Double maxQuantity;
    
    private Double maxAmount;
    
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
     * @return the maxQuantity
     */
    public Double getMaxQuantity() {
        return maxQuantity;
    }

    /**
     * @param maxQuantity the maxQuantity to set
     */
    public void setMaxQuantity(Double maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    /**
     * @return the maxAmount
     */
    public Double getMaxAmount() {
        return maxAmount;
    }

    /**
     * @param maxAmount the maxAmount to set
     */
    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
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
