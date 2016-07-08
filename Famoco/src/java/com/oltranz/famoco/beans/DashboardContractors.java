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
public class DashboardContractors implements Serializable{
    @JsonProperty("arraylist")
    private List<DashboardContractor> dashContractors = new ArrayList<>();
    
    @JsonProperty("message")
    private String message;

    /**
     * @return the dashContractors
     */
    public List<DashboardContractor> getDashContractors() {
        return dashContractors;
    }

    /**
     * @param dashContractors the dashContractors to set
     */
    public void setDashContractors(List<DashboardContractor> dashContractors) {
        this.dashContractors = dashContractors;
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
