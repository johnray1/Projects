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
public class SaleTransactionsSearchModel {
    @JsonProperty("transporterId")
    private Integer transporterId;
    
    @JsonProperty("from")
    private String from;
    
    @JsonProperty("to")
    private String to;
    
    @JsonProperty("busNumberPlate")
    private String busNumberPlate;
    
    @JsonProperty("busOwnerId")
    private Integer busOwnerId;
    
    @JsonProperty("busOwnerTypeId")
    private Integer busOwnerTypeId;
    
    
    
    /**
     * @return the busOwnerId
     */
    public Integer getBusOwnerId() {
        return busOwnerId;
    }
    
    /**
     * @param busOwnerId the busOwnerId to set
     */
    public void setBusOwnerId(Integer busOwnerId) {
        this.busOwnerId = busOwnerId;
    }
    
    /**
     * @return the busOwnerTypeId
     */
    public Integer getBusOwnerTypeId() {
        return busOwnerTypeId;
    }
    
    /**
     * @param busOwnerTypeId the busOwnerTypeId to set
     */
    public void setBusOwnerTypeId(Integer busOwnerTypeId) {
        this.busOwnerTypeId = busOwnerTypeId;
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
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the busNumberPlate
     */
    public String getBusNumberPlate() {
        return busNumberPlate;
    }

    /**
     * @param busNumberPlate the busNumberPlate to set
     */
    public void setBusNumberPlate(String busNumberPlate) {
        this.busNumberPlate = busNumberPlate;
    }
}
