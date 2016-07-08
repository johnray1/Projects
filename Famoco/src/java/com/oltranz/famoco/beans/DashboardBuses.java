/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author ismaelnzamutuma
 */
public class DashboardBuses implements Serializable {
    @JsonProperty("arraylist")
    private List<DashboardBus> dashbuses = new ArrayList<>();
    @JsonProperty("message")
    private String message;

    /**
     * @return the dashbuses
     */
    public List<DashboardBus> getDashbuses() {
        return dashbuses;
    }

    /**
     * @param dashbuses the dashbuses to set
     */
    public void setDashbuses(List<DashboardBus> dashbuses) {
        this.dashbuses = dashbuses;
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

    
    
}
