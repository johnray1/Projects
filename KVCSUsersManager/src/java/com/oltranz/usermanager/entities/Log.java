/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.usermanager.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "logs")

public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 255)
    @Column(name = "user_name", length = 255)
    private String userName;
    @Column(name = "object_id")
    private String objectId;
    @Size(max = 255)
    @Column(name = "object_name", length = 255)
    private String objectName;
    @Column(name = "action_id")
    private Integer actionId;
    @Size(max = 255)
    @Column(name = "action_name", length = 255)
    private String actionName;
    @Column(name = "action_result")
    private Integer actionResult;
    @Size(max = 255)
    @Column(name = "source", length = 255)
    private String source;
    @Size(max = 255)
    @Column(name = "ip", length = 255)
    private String ip;

    public Log() {
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

    /**
     * @return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the objectName
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * @param objectName the objectName to set
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * @return the actionId
     */
    public Integer getActionId() {
        return actionId;
    }

    /**
     * @param actionId the actionId to set
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    /**
     * @return the actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * @return the actionResult
     */
    public Integer getActionResult() {
        return actionResult;
    }

    /**
     * @param actionResult the actionResult to set
     */
    public void setActionResult(Integer actionResult) {
        this.actionResult = actionResult;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

   
}
