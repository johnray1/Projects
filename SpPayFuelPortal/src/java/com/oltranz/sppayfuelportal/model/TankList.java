/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class TankList {
    
    @JsonProperty("Tank")
    private List<Tank> tankList;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("statusCode")
    private Integer statusCode;

    /**
     * @return the tankList
     */
    public List<Tank> getTankList() {
        return tankList;
    }

    /**
     * @param tankList the tankList to set
     */
    public void setTankList(List<Tank> tankList) {
        this.tankList = tankList;
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
