/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.kvcs.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class DashBoardNumbersRequest {
    
    @JsonProperty("startDateTime")
    private String startDateTime;
    
    @JsonProperty("endDateTime")
    private String endDateTime;

    /**
     * @return the startDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime the startDateTime to set
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the endDateTime
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime the endDateTime to set
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
    
    
}
