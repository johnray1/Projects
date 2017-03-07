/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author manzi
 *
 * "statusCodeAtMerchant": "400",
 * "statusCodeAtPaymentSP": "24844545580884117",
 * "statusDesc": "Success",
 * "statusMessageAtMerchant": "Thank you for your payment!",
 * "statusMessageAtPaymentSp": "-",
 * "statusUpdateTime": "2017-01-31 05:34",
 * "transactionId": "24844545580884117",
 * "transactionType": "SP Initiated"
 */
public class PaymentTransaction {
    
    private Double amount;
    private String customerAccountIdAtMerchant;
    private String customerAccountIdAtPaymentSp;
    private String initiationTime;
    private String merchantContractId;
    private String merchantId;
    private String merchantIdAtPaymentSp;
    private String merchantName;
    public String merchantTransactionId;
    private String paymentSpContractId;
    private String paymentSpId;
    private String paymentSpIdAtmerchant;
    private String paymentSpName;
    private String paymentSpTransactionId;
    private String status;
    private String statusCodeAtMerchant;
    private String statusCodeAtPaymentSP;
    private String statusDesc;
    private String statusMessageAtMerchant;
    private String statusMessageAtPaymentSp;
    private String statusUpdateTime;
    private String transactionId;
    private String transactionType; // Merchant Initiated or payment SP id

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
     * @return the customerAccountIdAtMerchant
     */
    public String getCustomerAccountIdAtMerchant() {
        return customerAccountIdAtMerchant;
    }

    /**
     * @param customerAccountIdAtMerchant the customerAccountIdAtMerchant to set
     */
    public void setCustomerAccountIdAtMerchant(String customerAccountIdAtMerchant) {
        this.customerAccountIdAtMerchant = customerAccountIdAtMerchant;
    }

    /**
     * @return the customerAccountIdAtPaymentSp
     */
    public String getCustomerAccountIdAtPaymentSp() {
        return customerAccountIdAtPaymentSp;
    }

    /**
     * @param customerAccountIdAtPaymentSp the customerAccountIdAtPaymentSp to set
     */
    public void setCustomerAccountIdAtPaymentSp(String customerAccountIdAtPaymentSp) {
        this.customerAccountIdAtPaymentSp = customerAccountIdAtPaymentSp;
    }

    /**
     * @return the initiationTime
     */
    public String getInitiationTime() {
        return initiationTime;
    }

    /**
     * @param initiationTime the initiationTime to set
     */
    public void setInitiationTime(String initiationTime) {
        this.initiationTime = initiationTime;
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
     * @return the merchantIdAtPaymentSp
     */
    public String getMerchantIdAtPaymentSp() {
        return merchantIdAtPaymentSp;
    }

    /**
     * @param merchantIdAtPaymentSp the merchantIdAtPaymentSp to set
     */
    public void setMerchantIdAtPaymentSp(String merchantIdAtPaymentSp) {
        this.merchantIdAtPaymentSp = merchantIdAtPaymentSp;
    }

    /**
     * @return the merchantName
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * @param merchantName the merchantName to set
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * @return the merchantTransactionId
     */
    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    /**
     * @param merchantTransactionId the merchantTransactionId to set
     */
    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
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
     * @return the paymentSpIdAtmerchant
     */
    public String getPaymentSpIdAtmerchant() {
        return paymentSpIdAtmerchant;
    }

    /**
     * @param paymentSpIdAtmerchant the paymentSpIdAtmerchant to set
     */
    public void setPaymentSpIdAtmerchant(String paymentSpIdAtmerchant) {
        this.paymentSpIdAtmerchant = paymentSpIdAtmerchant;
    }

    /**
     * @return the paymentSpName
     */
    public String getPaymentSpName() {
        return paymentSpName;
    }

    /**
     * @param paymentSpName the paymentSpName to set
     */
    public void setPaymentSpName(String paymentSpName) {
        this.paymentSpName = paymentSpName;
    }

    /**
     * @return the paymentSpTransactionId
     */
    public String getPaymentSpTransactionId() {
        return paymentSpTransactionId;
    }

    /**
     * @param paymentSpTransactionId the paymentSpTransactionId to set
     */
    public void setPaymentSpTransactionId(String paymentSpTransactionId) {
        this.paymentSpTransactionId = paymentSpTransactionId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the statusCodeAtMerchant
     */
    public String getStatusCodeAtMerchant() {
        return statusCodeAtMerchant;
    }

    /**
     * @param statusCodeAtMerchant the statusCodeAtMerchant to set
     */
    public void setStatusCodeAtMerchant(String statusCodeAtMerchant) {
        this.statusCodeAtMerchant = statusCodeAtMerchant;
    }

    /**
     * @return the statusCodeAtPaymentSP
     */
    public String getStatusCodeAtPaymentSP() {
        return statusCodeAtPaymentSP;
    }

    /**
     * @param statusCodeAtPaymentSP the statusCodeAtPaymentSP to set
     */
    public void setStatusCodeAtPaymentSP(String statusCodeAtPaymentSP) {
        this.statusCodeAtPaymentSP = statusCodeAtPaymentSP;
    }

    /**
     * @return the statusDesc
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * @return the statusMessageAtMerchant
     */
    public String getStatusMessageAtMerchant() {
        return statusMessageAtMerchant;
    }

    /**
     * @param statusMessageAtMerchant the statusMessageAtMerchant to set
     */
    public void setStatusMessageAtMerchant(String statusMessageAtMerchant) {
        this.statusMessageAtMerchant = statusMessageAtMerchant;
    }

    /**
     * @return the statusMessageAtPaymentSp
     */
    public String getStatusMessageAtPaymentSp() {
        return statusMessageAtPaymentSp;
    }

    /**
     * @param statusMessageAtPaymentSp the statusMessageAtPaymentSp to set
     */
    public void setStatusMessageAtPaymentSp(String statusMessageAtPaymentSp) {
        this.statusMessageAtPaymentSp = statusMessageAtPaymentSp;
    }

    /**
     * @return the statusUpdateTime
     */
    public String getStatusUpdateTime() {
        return statusUpdateTime;
    }

    /**
     * @param statusUpdateTime the statusUpdateTime to set
     */
    public void setStatusUpdateTime(String statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
    
    
    
    
    
    
    
    
}
