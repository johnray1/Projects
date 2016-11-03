/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class TransactionType {
    
    @JsonProperty("id")
    private int id;
    
    @JsonProperty("sens")
    private String sens;
    
    @JsonProperty("transactionType")
    private String transactionType;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the sens
     */
    public String getSens() {
        return sens;
    }

    /**
     * @param sens the sens to set
     */
    public void setSens(String sens) {
        this.sens = sens;
    }

    /**
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    

    
}
