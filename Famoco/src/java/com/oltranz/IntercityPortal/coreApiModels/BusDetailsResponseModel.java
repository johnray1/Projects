/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityPortal.coreApiModels;

import com.oltranz.IntercityPortal.models.BusDetailsModel;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */
public class BusDetailsResponseModel {
    @JsonProperty("busdetailsmodel")
    private BusDetailsModel busdetailsmodel;
    
    @JsonProperty("message")
    private String message;

    /**
     * @return the BusDetailsModel
     */
    public BusDetailsModel getBusdetailsmodel() {
        return busdetailsmodel;
    }

    /**
     * @param busdetailsmodel the BusDetailsModel to set
     */
    public void setBusdetailsmodel(BusDetailsModel busdetailsmodel) {
        this.busdetailsmodel = busdetailsmodel;
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
