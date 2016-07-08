/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author ismaelnzamutuma
 */
@JsonRootName("filters")
public class TransactionFilter implements Serializable{
    private String subcontractorId;
    private String platenumber;
    private String tripId;
    private String deviceId;

    public String getSubcontractorId() {
        return subcontractorId;
    }

    public void setSubcontractorId(String subcontractorId) {
        this.subcontractorId = subcontractorId;
    }

    /**
     * @return the platenumber
     */
    public String getPlatenumber() {
        return platenumber;
    }

    /**
     * @param platenumber the platenumber to set
     */
    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
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
