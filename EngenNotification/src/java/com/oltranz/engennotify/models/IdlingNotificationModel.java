/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.models;

import java.util.Date;

/**
 *
 * @author JohnRay
 */
public class IdlingNotificationModel {
    
    private String id;
    
    private int branchId;
    
    private String branchName;
    
    private int nozzleId;
    
    private String nozzlename;
    
    private int maxIdlingTime;
    
    private Date lastUsedTime;
    
    private String createdBy;
    
    private Date creationTime;
    
    private int checkingPeriod;
    
    private int count;
    
    private int notificationTypeId;

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
     * @return the nozzleId
     */
    public int getNozzleId() {
        return nozzleId;
    }

    /**
     * @param nozzleId the nozzleId to set
     */
    public void setNozzleId(int nozzleId) {
        this.nozzleId = nozzleId;
    }

    /**
     * @return the nozzlename
     */
    public String getNozzlename() {
        return nozzlename;
    }

    /**
     * @param nozzlename the nozzlename to set
     */
    public void setNozzlename(String nozzlename) {
        this.nozzlename = nozzlename;
    }

   

    /**
     * @return the lastUsedTime
     */
    public Date getLastUsedTime() {
        return lastUsedTime;
    }

    /**
     * @param lastUsedTime the lastUsedTime to set
     */
    public void setLastUsedTime(Date lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
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
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
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

    /**
     * @return the notificationTypeId
     */
    public int getNotificationTypeId() {
        return notificationTypeId;
    }

    /**
     * @param notificationTypeId the notificationTypeId to set
     */
    public void setNotificationTypeId(int notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    /**
     * @return the maxIdlingTime
     */
    public int getMaxIdlingTime() {
        return maxIdlingTime;
    }

    /**
     * @param maxIdlingTime the maxIdlingTime to set
     */
    public void setMaxIdlingTime(int maxIdlingTime) {
        this.maxIdlingTime = maxIdlingTime;
    }
    
    
    
    
}
