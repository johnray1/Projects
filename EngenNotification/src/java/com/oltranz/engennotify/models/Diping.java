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

public class Diping{
    
    private Integer id;
    private Integer userId;
    private Integer tankId;
    private Double measuredDip;
    private Double calculatedDip;
    private Date datetime;
    private Double waterValue;

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
     * @return the tankId
     */
    public Integer getTankId() {
        return tankId;
    }

    /**
     * @param tankId the tankId to set
     */
    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    /**
     * @return the measuredDip
     */
    public Double getMeasuredDip() {
        return measuredDip;
    }

    /**
     * @param measuredDip the measuredDip to set
     */
    public void setMeasuredDip(Double measuredDip) {
        this.measuredDip = measuredDip;
    }

    /**
     * @return the calculatedDip
     */
    public Double getCalculatedDip() {
        return calculatedDip;
    }

    /**
     * @param calculatedDip the calculatedDip to set
     */
    public void setCalculatedDip(Double calculatedDip) {
        this.calculatedDip = calculatedDip;
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
     * @return the waterValue
     */
    public Double getWaterValue() {
        return waterValue;
    }

    /**
     * @param waterValue the waterValue to set
     */
    public void setWaterValue(Double waterValue) {
        this.waterValue = waterValue;
    }

    
    
    
}
