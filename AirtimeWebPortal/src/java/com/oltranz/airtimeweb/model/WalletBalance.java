/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class WalletBalance {
    
    @JsonProperty("balance")
    private Double  balance;
    
    @JsonProperty("lastTxTime")
    private String lastTxTime;
    
    @JsonProperty("status")
    private ResponseStatusSimpleBean status;

    

    /**
     * @return the lastTxTime
     */
    public String getLastTxTime() {
        return lastTxTime;
    }

    /**
     * @param lastTxTime the lastTxTime to set
     */
    public void setLastTxTime(String lastTxTime) {
        this.lastTxTime = lastTxTime;
    }

    /**
     * @return the status
     */
    public ResponseStatusSimpleBean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ResponseStatusSimpleBean status) {
        this.status = status;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    
    
}
