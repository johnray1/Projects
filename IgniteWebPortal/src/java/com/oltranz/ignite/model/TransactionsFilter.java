/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.model;

/**
 *
 * @author manzi
 */
public class TransactionsFilter {
    
    private String periodFrom;
    private String periodTo;
    private Integer status;
    private String paymentSpId;
    private String merchantId;
    private String merchantContractId;
    private String paymentSpContractId;
    private String transactionType;

    /**
     * @return the periodFrom
     */
    public String getPeriodFrom() {
        return periodFrom;
    }

    /**
     * @param periodFrom the periodFrom to set
     */
    public void setPeriodFrom(String periodFrom) {
        this.periodFrom = periodFrom;
    }

    /**
     * @return the periodTo
     */
    public String getPeriodTo() {
        return periodTo;
    }

    /**
     * @param periodTo the periodTo to set
     */
    public void setPeriodTo(String periodTo) {
        this.periodTo = periodTo;
    }

    

    /**
     * @return the paymentSpId
     */
    public String getPaymentSpId() {
        return paymentSpId;
    }

    /**
     * @param paymentSpId the paymentSpId to set
     */
    public void setPaymentSpId(String paymentSpId) {
        this.paymentSpId = paymentSpId;
    }

    /**
     * @return the merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * @param merchantId the merchantId to set
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * @return the merchantContractId
     */
    public String getMerchantContractId() {
        return merchantContractId;
    }

    /**
     * @param merchantContractId the merchantContractId to set
     */
    public void setMerchantContractId(String merchantContractId) {
        this.merchantContractId = merchantContractId;
    }

    
    /**
     * @return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return the paymentSpContractId
     */
    public String getPaymentSpContractId() {
        return paymentSpContractId;
    }

    /**
     * @param paymentSpContractId the paymentSpContractId to set
     */
    public void setPaymentSpContractId(String paymentSpContractId) {
        this.paymentSpContractId = paymentSpContractId;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    
}
