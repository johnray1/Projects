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
public class DippingNotificationEditModel {
    
    private String id;
    
    private int branchId;
    
    private String dipingTime;
    
    private String createdBy;

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
     * @return the dipingTime
     */
    public String getDipingTime() {
        return dipingTime;
    }

    /**
     * @param dipingTime the dipingTime to set
     */
    public void setDipingTime(String dipingTime) {
        this.dipingTime = dipingTime;
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

    
    
}
