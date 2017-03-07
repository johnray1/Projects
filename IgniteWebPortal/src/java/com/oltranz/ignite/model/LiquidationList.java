/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class LiquidationList {
    
    @JsonProperty("Liquidation")
    private List<Liquidation> liquidationList;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("statusCode")
    private Integer statusCode;

    /**
     * @return the liquidationList
     */
    public List<Liquidation> getLiquidationList() {
        return liquidationList;
    }

    /**
     * @param liquidationList the liquidationList to set
     */
    public void setLiquidationList(List<Liquidation> liquidationList) {
        this.liquidationList = liquidationList;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    
    
}
