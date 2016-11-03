package com.oltranz.airtime.model;

import org.codehaus.jackson.annotate.JsonProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author manzi
 */
public class VtuAgentSIMSalesReport {
    @JsonProperty("MSISDN")
    private String MSISDN;
    private double balance;
    private String totalTx;
    private String totalSuccessfulTx;
    private String totalFailuresfulTx;
    private double totalSoldAmount;
    private String lastSuccessfulTxTime;
    private double lastSuccessfulTxAmount;
    private String lastSuccessfulTxDestMSISDN;

    /**
     * @return the MSISDN
     */
    public String getMSISDN() {
        return MSISDN;
    }

    /**
     * @param MSISDN the MSISDN to set
     */
    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the totalTx
     */
    public String getTotalTx() {
        return totalTx;
    }

    /**
     * @param totalTx the totalTx to set
     */
    public void setTotalTx(String totalTx) {
        this.totalTx = totalTx;
    }

    /**
     * @return the totalSuccessfulTx
     */
    public String getTotalSuccessfulTx() {
        return totalSuccessfulTx;
    }

    /**
     * @param totalSuccessfulTx the totalSuccessfulTx to set
     */
    public void setTotalSuccessfulTx(String totalSuccessfulTx) {
        this.totalSuccessfulTx = totalSuccessfulTx;
    }

    /**
     * @return the totalFailuresfulTx
     */
    public String getTotalFailuresfulTx() {
        return totalFailuresfulTx;
    }

    /**
     * @param totalFailuresfulTx the totalFailuresfulTx to set
     */
    public void setTotalFailuresfulTx(String totalFailuresfulTx) {
        this.totalFailuresfulTx = totalFailuresfulTx;
    }

    /**
     * @return the totalSoldAmount
     */
    public double getTotalSoldAmount() {
        return totalSoldAmount;
    }

    /**
     * @param totalSoldAmount the totalSoldAmount to set
     */
    public void setTotalSoldAmount(double totalSoldAmount) {
        this.totalSoldAmount = totalSoldAmount;
    }

    /**
     * @return the lastSuccessfulTxTime
     */
    public String getLastSuccessfulTxTime() {
        return lastSuccessfulTxTime;
    }

    /**
     * @param lastSuccessfulTxTime the lastSuccessfulTxTime to set
     */
    public void setLastSuccessfulTxTime(String lastSuccessfulTxTime) {
        this.lastSuccessfulTxTime = lastSuccessfulTxTime;
    }

    /**
     * @return the lastSuccessfulTxAmount
     */
    public double getLastSuccessfulTxAmount() {
        return lastSuccessfulTxAmount;
    }

    /**
     * @param lastSuccessfulTxAmount the lastSuccessfulTxAmount to set
     */
    public void setLastSuccessfulTxAmount(double lastSuccessfulTxAmount) {
        this.lastSuccessfulTxAmount = lastSuccessfulTxAmount;
    }

    /**
     * @return the lastSuccessfulTxDestMSISDN
     */
    public String getLastSuccessfulTxDestMSISDN() {
        return lastSuccessfulTxDestMSISDN;
    }

    /**
     * @param lastSuccessfulTxDestMSISDN the lastSuccessfulTxDestMSISDN to set
     */
    public void setLastSuccessfulTxDestMSISDN(String lastSuccessfulTxDestMSISDN) {
        this.lastSuccessfulTxDestMSISDN = lastSuccessfulTxDestMSISDN;
    }

    
    
}
