package com.oltranz.airtime.model;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

public class ItemTotals {
    private Long txCount;
    private Double Sum;

    /**
     * @return the txCount
     */
    public Long getTxCount() {
        return txCount;
    }

    /**
     * @param txCount the txCount to set
     */
    public void setTxCount(Long txCount) {
        this.txCount = txCount;
    }

    /**
     * @return the Sum
     */
    public Double getSum() {
        return Sum;
    }

    /**
     * @param Sum the Sum to set
     */
    public void setSum(Double Sum) {
        this.Sum = Sum;
    }
}
