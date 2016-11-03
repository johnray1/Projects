/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtime.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class TopUpListFilteredResponseItem {
    
    @JsonProperty("source")
    private String source;
    
    @JsonProperty("destination")
    private String destination;
    
    @JsonProperty("txTime")
    private String txTime;
    
    @JsonProperty("amount")
    private String amount;
    
    @JsonProperty("status")
    private Integer status;

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
     * @return the txTime
     */
    public String getTxTime() {
        return txTime;
    }

    /**
     * @param txTime the txTime to set
     */
    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
