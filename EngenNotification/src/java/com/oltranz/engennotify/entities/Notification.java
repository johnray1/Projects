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
@Table(name = "notification", catalog = "EngenNotificationDB")
public class Notification implements Serializable{
   
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private String id;
    
    @Column(name = "notification_def_id")
    private String notificationDefId;
    
    
    @Column(name = "notification_type_id")
    private String notificationTypeId;
    
    
    @Column(name = "notify_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notifyTime;

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
     * @return the notificationDefId
     */
    public String getNotificationDefId() {
        return notificationDefId;
    }

    /**
     * @param notificationDefId the notificationDefId to set
     */
    public void setNotificationDefId(String notificationDefId) {
        this.notificationDefId = notificationDefId;
    }

    /**
     * @return the notificationTypeId
     */
    public String getNotificationTypeId() {
        return notificationTypeId;
    }

    /**
     * @param notificationTypeId the notificationTypeId to set
     */
    public void setNotificationTypeId(String notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    /**
     * @return the notifyTime
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * @param notifyTime the notifyTime to set
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

   

    
    
}
