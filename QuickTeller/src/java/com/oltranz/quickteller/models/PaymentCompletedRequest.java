/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.models;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author manzi
 */
@JsonRootName("PaymentCompletedRequest")
public class PaymentCompletedRequest {
    
    @JsonProperty("initUid")
    private String initUid;
    
    @JsonProperty("SPPaymentRef")
    private String SPPaymentRef;
    
    @JsonProperty("paymentSPId")
    private Integer paymentSPId;
    
    @JsonProperty("paymentTypeId")
    private Integer paymentTypeId;
    
    @JsonProperty("amount")
    private Double amount;
    
    @JsonProperty("msisdn")
    private String msisdn;
    

    /**
     * @return the initUid
     */
    public String getInitUid() {
        return initUid;
    }

    /**
     * @param initUid the initUid to set
     */
    public void setInitUid(String initUid) {
        this.initUid = initUid;
    }

    /**
     * @return the SPPaymentRef
     */
    public String getSPPaymentRef() {
        return SPPaymentRef;
    }

    /**
     * @param SPPaymentRef the SPPaymentRef to set
     */
    public void setSPPaymentRef(String SPPaymentRef) {
        this.SPPaymentRef = SPPaymentRef;
    }

    /**
     * @return the paymentSPId
     */
    public Integer getPaymentSPId() {
        return paymentSPId;
    }

    /**
     * @param paymentSPId the paymentSPId to set
     */
    public void setPaymentSPId(Integer paymentSPId) {
        this.paymentSPId = paymentSPId;
    }

    /**
     * @return the paymentTypeId
     */
    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    /**
     * @param paymentTypeId the paymentTypeId to set
     */
    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
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

    
    
    
}
