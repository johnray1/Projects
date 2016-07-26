/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
@ManagedBean(name="TopUpBean")
@SessionScoped
public class TopUpBean {
    
    @JsonProperty("msisdn")
    private String msisdn;
    
    @JsonProperty("amount")
    private Double amount;

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    
    
}
