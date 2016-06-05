/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */
public class SellingDeviceRegistrationResponse {
       
    @JsonProperty("transporterName")
    private String transporterName;
    
    @JsonProperty("transporterId")
    private Integer transporterId;    
 
    @JsonProperty("deviceName")
    private String deviceName;

    /**
     * @return the transporterName
     */
    public String getTransporterName() {
        return transporterName;
    }

    /**
     * @param transporterName the transporterName to set
     */
    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    /**
     * @return the transporterId
     */
    public Integer getTransporterId() {
        return transporterId;
    }

    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
}
