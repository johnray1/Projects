/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author JohnRay
 */

public class ReportShift implements Serializable {
    
    
    private Long id;
    
    private Date startTime;
   
    private Date endTime;
    
    private Integer pumpistId;
    
    private Integer branchId;
    
    private String status;
    
    private Long pumpistReportId;
    
    private Long managerReportId;

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
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the pumpistId
     */
    public Integer getPumpistId() {
        return pumpistId;
    }

    /**
     * @param pumpistId the pumpistId to set
     */
    public void setPumpistId(Integer pumpistId) {
        this.pumpistId = pumpistId;
    }

    /**
     * @return the branchId
     */
    public Integer getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the pumpistReportId
     */
    public Long getPumpistReportId() {
        return pumpistReportId;
    }

    /**
     * @param pumpistReportId the pumpistReportId to set
     */
    public void setPumpistReportId(Long pumpistReportId) {
        this.pumpistReportId = pumpistReportId;
    }

    /**
     * @return the managerReportId
     */
    public Long getManagerReportId() {
        return managerReportId;
    }

    /**
     * @param managerReportId the managerReportId to set
     */
    public void setManagerReportId(Long managerReportId) {
        this.managerReportId = managerReportId;
    }

    
    
}
