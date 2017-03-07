/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.model;

import java.util.List;

/**
 *
 * @author manzi
 */
public class PaymentsTransactionsList {
    
    private List<PaymentTransaction> txList;

    /**
     * @return the txList
     */
    public List<PaymentTransaction> getTxList() {
        return txList;
    }

    /**
     * @param txList the txList to set
     */
    public void setTxList(List<PaymentTransaction> txList) {
        this.txList = txList;
    }
}
