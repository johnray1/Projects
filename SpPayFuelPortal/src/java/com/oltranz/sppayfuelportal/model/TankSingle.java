/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class TankSingle {
    
    @JsonProperty("Tank")
    private Tank tank;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("statusCode")
    private Integer statusCode;

    /**
     * @return the tank
     */
    public Tank getTank() {
        return tank;
    }

    /**
     * @param tank the tank to set
     */
    public void setTank(Tank tank) {
        this.tank = tank;
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
