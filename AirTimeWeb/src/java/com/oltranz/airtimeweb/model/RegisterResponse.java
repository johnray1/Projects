/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class RegisterResponse {
    
    @JsonProperty("customerDetails")
    private CustomerDetails customerDetails;
    
    @JsonProperty("status")
    private ResponseStatusSimpleBean responseStatusSimpleBean;

    /**
     * @return the customerDetails
     */
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    /**
     * @param customerDetails the customerDetails to set
     */
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    /**
     * @return the responseStatusSimpleBean
     */
    public ResponseStatusSimpleBean getResponseStatusSimpleBean() {
        return responseStatusSimpleBean;
    }

    /**
     * @param responseStatusSimpleBean the responseStatusSimpleBean to set
     */
    public void setResponseStatusSimpleBean(ResponseStatusSimpleBean responseStatusSimpleBean) {
        this.responseStatusSimpleBean = responseStatusSimpleBean;
    }
    
    
    
}
