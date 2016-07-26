/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class WalletTransaction {
    
    @JsonProperty("amount")
    private double amount;
    
    @JsonProperty("destRefId")
    private String  destRefId;
    
    @JsonProperty("destType")
    private int destType;
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("newBalance")
    private double newBalance;
    
    @JsonProperty("prevBalance")
    private double prevBalance;
    
    @JsonProperty("sourceType")
    private int sourceType;
    
    @JsonProperty("srcrefId")
    private String  srcrefId;
    
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("transTime")
    private String transTime;
    
    @JsonProperty("transTypeId")
    private int transTypeId;

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the destRefId
     */
    public String getDestRefId() {
        return destRefId;
    }

    /**
     * @param destRefId the destRefId to set
     */
    public void setDestRefId(String destRefId) {
        this.destRefId = destRefId;
    }

    /**
     * @return the destType
     */
    public int getDestType() {
        return destType;
    }

    /**
     * @param destType the destType to set
     */
    public void setDestType(int destType) {
        this.destType = destType;
    }

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
     * @return the newBalance
     */
    public double getNewBalance() {
        return newBalance;
    }

    /**
     * @param newBalance the newBalance to set
     */
    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    /**
     * @return the prevBalance
     */
    public double getPrevBalance() {
        return prevBalance;
    }

    /**
     * @param prevBalance the prevBalance to set
     */
    public void setPrevBalance(double prevBalance) {
        this.prevBalance = prevBalance;
    }

    /**
     * @return the sourceType
     */
    public int getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the sourceType to set
     */
    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return the srcrefId
     */
    public String getSrcrefId() {
        return srcrefId;
    }

    /**
     * @param srcrefId the srcrefId to set
     */
    public void setSrcrefId(String srcrefId) {
        this.srcrefId = srcrefId;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the transTime
     */
    public String getTransTime() {
        return transTime;
    }

    /**
     * @param transTime the transTime to set
     */
    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    /**
     * @return the transTypeId
     */
    public int getTransTypeId() {
        return transTypeId;
    }

    /**
     * @param transTypeId the transTypeId to set
     */
    public void setTransTypeId(int transTypeId) {
        this.transTypeId = transTypeId;
    }
    
    
    
    
    
}
