/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.usermanager.models;


import java.util.Date;

/**
 *
 * @author manzi
 */
public class UserSession {
    private String sessionId;
    private String userId;
    private String currentPermission;
    private Date creationTime;
    private Date lastAccessTime;
    private Integer lifetime;

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the currentPermission
     */
    public String getCurrentPermission() {
        return currentPermission;
    }

    /**
     * @param currentPermission the currentPermission to set
     */
    public void setCurrentPermission(String currentPermission) {
        this.currentPermission = currentPermission;
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
     * @return the lastAccessTime
     */
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    /**
     * @param lastAccessTime the lastAccessTime to set
     */
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * @return the lifetime
     */
    public Integer getLifetime() {
        return lifetime;
    }

    /**
     * @param lifetime the lifetime to set
     */
    public void setLifetime(Integer lifetime) {
        this.lifetime = lifetime;
    }

  
}
