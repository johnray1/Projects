/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
@ManagedBean(name="TopUpRequest")
@SessionScoped
public class TopUpRequest {
    
    @JsonProperty("sourcemsisdn")
    private String sourceMsisdn;
    
    @JsonProperty("destinations")
    private List<TopUpBean> destinations;
    
    @JsonProperty("token")
    private String token;

    /**
     * @return the sourceMsisdn
     */
    public String getSourceMsisdn() {
        return sourceMsisdn;
    }

    /**
     * @param sourceMsisdn the sourceMsisdn to set
     */
    public void setSourceMsisdn(String sourceMsisdn) {
        this.sourceMsisdn = sourceMsisdn;
    }

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

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    
}
