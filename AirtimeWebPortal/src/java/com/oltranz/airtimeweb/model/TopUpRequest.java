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

public class TopUpRequest {
    
    @JsonProperty("destinations")
    private List<TopUpBean> destinations;

    

    /**
     * @return the destinations
     */
    public List<TopUpBean> getDestinations() {
        return destinations;
    }

    /**
     * @param destinations the destinations to set
     */
    public void setDestinations(List<TopUpBean> destinations) {
        this.destinations = destinations;
    }

    
    
}
