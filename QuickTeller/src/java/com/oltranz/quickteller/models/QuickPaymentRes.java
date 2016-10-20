/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class QuickPaymentRes {
    
    @JsonProperty("PaymentReference")
    private String PaymentReference;
    
    @JsonProperty("Amount")
    private String Amount;
    
    @JsonProperty("TransactionDate")
    private String TransactionDate;
    
    @JsonProperty("CustomerId")
    private String CustomerId;
    
    @JsonProperty("ResponseCode")
    private String ResponseCode;
    
    @JsonProperty("ResponseDescription")
    private String ResponseDescription;
    
    
    /**
     * @return the PaymentReference
     */
    public String getPaymentReference() {
        return PaymentReference;
    }
    
    /**
     * @param PaymentReference the PaymentReference to set
     */
    public void setPaymentReference(String PaymentReference) {
        this.PaymentReference = PaymentReference;
    }
    
    
    
    
    /**
     * @return the CustomerId
     */
    public String getCustomerId() {
        return CustomerId;
    }
    
    /**
     * @param CustomerId the CustomerId to set
     */
    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }
    
    /**
     * @return the ResponseCode
     */
    public String getResponseCode() {
        return ResponseCode;
    }
    
    /**
     * @param ResponseCode the ResponseCode to set
     */
    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }
    
    /**
     * @return the ResponseDescription
     */
    public String getResponseDescription() {
        return ResponseDescription;
    }
    
    /**
     * @param ResponseDescription the ResponseDescription to set
     */
    public void setResponseDescription(String ResponseDescription) {
        this.ResponseDescription = ResponseDescription;
    }

    /**
     * @return the Amount
     */
    public String getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return the TransactionDate
     */
    public String getTransactionDate() {
        return TransactionDate;
    }

    /**
     * @param TransactionDate the TransactionDate to set
     */
    public void setTransactionDate(String TransactionDate) {
        this.TransactionDate = TransactionDate;
    }
    
    

    
}
