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
public class CustomerSingle {
    
    @JsonProperty("customer")
    private Customer customer;
    
    @JsonProperty("nowBalance")
    private double nowBalance;
    
    @JsonProperty("status")
    private Status status;
    
    @JsonProperty("yesterdayBalance")
    private double yesterdayBalance;

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the nowBalance
     */
    public double getNowBalance() {
        return nowBalance;
    }

    /**
     * @param nowBalance the nowBalance to set
     */
    public void setNowBalance(double nowBalance) {
        this.nowBalance = nowBalance;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the yesterdayBalance
     */
    public double getYesterdayBalance() {
        return yesterdayBalance;
    }

    /**
     * @param yesterdayBalance the yesterdayBalance to set
     */
    public void setYesterdayBalance(double yesterdayBalance) {
        this.yesterdayBalance = yesterdayBalance;
    }

    
    
}
