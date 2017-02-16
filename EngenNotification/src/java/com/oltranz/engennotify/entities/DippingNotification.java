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
@Table(name = "dipping_notification", catalog = "EngenNotificationDB")
public class DippingNotification implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private String id;
    
    
    @Column(name = "branch_id")
    private int branchId;
    
    
    @Column(name = "diping_time")
    @Temporal(TemporalType.TIME)
    private Date dipingTime;
    
    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "checking_period")
    private String checkingPeriod;
    
    @Column(name = "count")
    private String count;
    
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
    public Date getDipingTime() {
        return dipingTime;
    }
    
    /**
     * @param dipingTime the dipingTime to set
     */
    public void setDipingTime(Date dipingTime) {
        this.dipingTime = dipingTime;
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
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the checkingPeriod
     */
    public String getCheckingPeriod() {
        return checkingPeriod;
    }

    /**
     * @param checkingPeriod the checkingPeriod to set
     */
    public void setCheckingPeriod(String checkingPeriod) {
        this.checkingPeriod = checkingPeriod;
    }
    
    
    
    
}
