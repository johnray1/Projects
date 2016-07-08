/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;

/**
 *
 * @author ismaelnzamutuma
 */
public class FilteringParameter implements Serializable{
    private int subcontractorId;
    private String bus;
    private String tripId;
    private String deviceId;

    /**
     * @return the subcontractorId
     */
    public int getSubcontractorId() {
        return subcontractorId;
    }

    /**
     * @param subcontractorId the subcontractorId to set
     */
    public void setSubcontractorId(int subcontractorId) {
        this.subcontractorId = subcontractorId;
    }

    /**
     * @return the bus
     */
    public String getBus() {
        return bus;
    }

    /**
     * @param bus the bus to set
     */
    public void setBus(String bus) {
        this.bus = bus;
    }

    /**
     * @return the tripId
     */
    public String getTripId() {
        return tripId;
    }

    /**
     * @param tripId the tripId to set
     */
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
}
