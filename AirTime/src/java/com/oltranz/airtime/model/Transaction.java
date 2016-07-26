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
public class Transaction {
    
    @JsonProperty("txType")
    private TransactionType txType;
    
    @JsonProperty("walletTransaction")
    private WalletTransaction walletTransaction;

    /**
     * @return the txType
     */
    public TransactionType getTxType() {
        return txType;
    }

    /**
     * @param txType the txType to set
     */
    public void setTxType(TransactionType txType) {
        this.txType = txType;
    }

    /**
     * @return the walletTransaction
     */
    public WalletTransaction getWalletTransaction() {
        return walletTransaction;
    }

    /**
     * @param walletTransaction the walletTransaction to set
     */
    public void setWalletTransaction(WalletTransaction walletTransaction) {
        this.walletTransaction = walletTransaction;
    }
    
    
    
}
