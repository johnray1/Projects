/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author manzi
 */
@JsonRootName("PaymentInitResponse")
public class PaymentInitResponse {
    @JsonProperty("initUid")
    private String initUid;
    
    @JsonProperty("status")
    private SimpleStatus status;
    
   
    
    /**
     * @return the status
     */
    public SimpleStatus getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(SimpleStatus status) {
        this.status = status;
    }

    /**
     * @return the initUid
     */
    public String getInitUid() {
        return initUid;
    }

    /**
     * @param initUid the initUid to set
     */
    public void setInitUid(String initUid) {
        this.initUid = initUid;
    }
}
