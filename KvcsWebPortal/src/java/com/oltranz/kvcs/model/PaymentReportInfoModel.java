/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.kvcs.model;

/**
 *
 * @author JohnRay
 */
public class PaymentReportInfoModel {
    
    private String method;
    private String paymentFrom;
    private String paymentFor;
    private Double amount;
    private String paymentsCount;
    private String time;

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the paymentFrom
     */
    public String getPaymentFrom() {
        return paymentFrom;
    }

    /**
     * @param paymentFrom the paymentFrom to set
     */
    public void setPaymentFrom(String paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    /**
     * @return the paymentFor
     */
    public String getPaymentFor() {
        return paymentFor;
    }

    /**
     * @param paymentFor the paymentFor to set
     */
    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
    }

    

    /**
     * @return the paymentsCount
     */
    public String getPaymentsCount() {
        return paymentsCount;
    }

    /**
     * @param paymentsCount the paymentsCount to set
     */
    public void setPaymentsCount(String paymentsCount) {
        this.paymentsCount = paymentsCount;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
}
