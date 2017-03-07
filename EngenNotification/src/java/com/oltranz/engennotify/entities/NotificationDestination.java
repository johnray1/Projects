/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "notification_dest", catalog = "EngenNotificationDB")
public class NotificationDestination implements Serializable {
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "notification_id", nullable = false)
    private String notificationId;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "notify_type_id", nullable = false)
    private int notifyTypeId;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dest", nullable = false)
    private String dest;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dest_type", nullable = false)
    private int destType;

    

    /**
     * @return the notifyTypeId
     */
    public int getNotifyTypeId() {
        return notifyTypeId;
    }

    /**
     * @param notifyTypeId the notifyTypeId to set
     */
    public void setNotifyTypeId(int notifyTypeId) {
        this.notifyTypeId = notifyTypeId;
    }

    /**
     * @return the dest
     */
    public String getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

    /**
     * @return the destType
     */
    public int getDestType() {
        return destType;
    }

    /**
     * @param destType the destType to set
     */
    public void setDestType(int destType) {
        this.destType = destType;
    }

    /**
     * @return the notificationId
     */
    public String getNotificationId() {
        return notificationId;
    }

    /**
     * @param notificationId the notificationId to set
     */
    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
}
