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
@Table(name = "log", catalog = "EngenNotificationDB")
public class Log implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "notification")
    private String notification;
     
    @Column(name = "notification_type")
    private String notificationType;
    
    @Column(name = "destination")
    private String destination;
     
    @Column(name = "destination_type")
    private String destinationType;
      
    @Column(name = "notify_time")
    @Temporal(TemporalType.TIME)
    private Date notifyTime;

    

    /**
     * @return the notification
     */
    public String getNotification() {
        return notification;
    }

    /**
     * @param notification the notification to set
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }

    /**
     * @return the notificationType
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * @param notificationType the notificationType to set
     */
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the destinationType
     */
    public String getDestinationType() {
        return destinationType;
    }

    /**
     * @param destinationType the destinationType to set
     */
    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
