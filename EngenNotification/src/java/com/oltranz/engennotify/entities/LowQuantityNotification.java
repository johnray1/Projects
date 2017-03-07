/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "low_quantity_notification", catalog = "EngenNotificationDB")
public class LowQuantityNotification implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private String id;
    
    
    @Column(name = "branch_id")
    private int branchId;
    
    @Column(name = "tank_id")
    private int tankId;
    
    
    @Column(name = "min_capacity")
    private Double minCapacity;
    
    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "checking_period")
    private int checkingPeriod;
    
    @Column(name = "count")
    private int count;
    
    @Column(name = "check_count")
    private int checkCount;
    
    @Column(name = "last_notify_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastNotifyTime;
    
    @Column(name = "notification_type_id")
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

    /**
     * @return the checkCount
     */
    public int getCheckCount() {
        return checkCount;
    }

    /**
     * @param checkCount the checkCount to set
     */
    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    /**
     * @return the lastNotifyTime
     */
    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    /**
     * @param lastNotifyTime the lastNotifyTime to set
     */
    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
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

    
    

    
    
}
