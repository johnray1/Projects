/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtime.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class TopUpListFilteredResponse {
    
    @JsonProperty("tx")
    private List<TopUpListFilteredResponseItem> tx;
    
    @JsonProperty("status")
    private ResponseStatusSimpleBean status;

    /**
     * @return the tx
     */
    public List<TopUpListFilteredResponseItem> getTx() {
        return tx;
    }

    /**
     * @param tx the tx to set
     */
    public void setTx(List<TopUpListFilteredResponseItem> tx) {
        this.tx = tx;
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
