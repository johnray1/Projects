/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class TopUpResponse {
    
    @JsonProperty("destinations")
    private List<TopUpResponseBean> destinations;
    
    @JsonProperty("status")
    private ResponseStatusSimpleBean status;

    /**
     * @return the destinations
     */
    public List<TopUpResponseBean> getDestinations() {
        return destinations;
    }

    /**
     * @param destinations the destinations to set
     */
    public void setDestinations(List<TopUpResponseBean> destinations) {
        this.destinations = destinations;
    }

    /**
     * @return the status
     */
    public ResponseStatusSimpleBean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ResponseStatusSimpleBean status) {
        this.status = status;
    }
    

    
    
    
}
