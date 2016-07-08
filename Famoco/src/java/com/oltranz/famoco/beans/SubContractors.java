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
public class SubContractors implements Serializable {
    @JsonProperty("arraylist")
    private List<SubContractor> subcontractors = new ArrayList<>();
    @JsonProperty("message")
    private String message;

    /**
     * @return the subcontractors
     */
    public List<SubContractor> getSubcontractors() {
        return subcontractors;
    }

    /**
     * @param subcontractors the subcontractors to set
     */
    public void setSubcontractors(List<SubContractor> subcontractors) {
        this.subcontractors = subcontractors;
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
