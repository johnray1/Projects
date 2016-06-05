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
public class PumpNozzleProductModelList {
    
    @JsonProperty("PumpNozzleProductModel")
    private List<PumpNozzleProductModel> pumpNozzleProductModelList;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("statusCode")
    private Integer statusCode;

    /**
     * @return the pumpNozzleProductModelList
     */
    public List<PumpNozzleProductModel> getPumpNozzleProductModelList() {
        return pumpNozzleProductModelList;
    }

    /**
     * @param pumpNozzleProductModelList the pumpNozzleProductModelList to set
     */
    public void setPumpNozzleProductModelList(List<PumpNozzleProductModel> pumpNozzleProductModelList) {
        this.pumpNozzleProductModelList = pumpNozzleProductModelList;
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
