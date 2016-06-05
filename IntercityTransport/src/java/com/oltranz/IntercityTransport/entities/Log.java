/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author manzi
 */
@Entity
@SequenceGenerator(name="logSeq", initialValue=1, allocationSize=1)
@Table(name = "logs")
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="logSeq")
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datetime;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "object_id")
    private Integer objectId;
    
    @Column(name = "object_name")
    private String objectName;
    
    @Column(name = "action_id")
    private Integer actionId;
    
    @Column(name = "action_name")
    private String actionName;
    
    @Column(name = "action_result")
    private Integer actionResult;
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.entities.Log[ id=" + getId() + " ]";
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
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
    public Integer getObjectId() {
        return objectId;
    }
    
    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
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
    
    
    
}
