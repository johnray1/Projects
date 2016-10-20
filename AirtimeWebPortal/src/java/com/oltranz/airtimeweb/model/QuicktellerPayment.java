/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.model;

/**
 *
 * @author John
 * 
 */
public class QuicktellerPayment {
    
    private String token;
    
    private String msisdn;
    
    private double amount;
    
    private String requestReference;
    
    private String paymentReference;
    
    private String rechargePin;
    
    private String signature;
    
    private String responseCode;
    
    private String responseDescription;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the requestReference
     */
    public String getRequestReference() {
        return requestReference;
    }

    /**
     * @param requestReference the requestReference to set
     */
    public void setRequestReference(String requestReference) {
        this.requestReference = requestReference;
    }

    /**
     * @return the paymentReference
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * @param paymentReference the paymentReference to set
     */
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    /**
     * @return the rechargePin
     */
    public String getRechargePin() {
        return rechargePin;
    }

    /**
     * @param rechargePin the rechargePin to set
     */
    public void setRechargePin(String rechargePin) {
        this.rechargePin = rechargePin;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the responseDescription
     */
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * @param responseDescription the responseDescription to set
     */
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    
   
    
}
