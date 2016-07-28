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
public class TransactionList {
    
    @JsonProperty("status")
    private ResponseStatusSimpleBean responseStatusSimpleBean;
    
    @JsonProperty("walletHistory")
    private List<Transaction> transactionList;

    /**
     * @return the transactionList
     */
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * @param transactionList the transactionList to set
     */
    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
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
