/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.quickteller.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "quickteller", catalog = "QuicktellerDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"payment_reference"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quickteller.findAll", query = "SELECT q FROM Quickteller q"),
    @NamedQuery(name = "Quickteller.findByRequestReference", query = "SELECT q FROM Quickteller q WHERE q.requestReference = :requestReference"),
    @NamedQuery(name = "Quickteller.findByPaymentReference", query = "SELECT q FROM Quickteller q WHERE q.paymentReference = :paymentReference"),
    @NamedQuery(name = "Quickteller.findByAmount", query = "SELECT q FROM Quickteller q WHERE q.amount = :amount"),
    @NamedQuery(name = "Quickteller.findByCustomerId", query = "SELECT q FROM Quickteller q WHERE q.customerId = :customerId"),
    @NamedQuery(name = "Quickteller.findByTransactionDate", query = "SELECT q FROM Quickteller q WHERE q.transactionDate = :transactionDate"),
    @NamedQuery(name = "Quickteller.findByRechargePin", query = "SELECT q FROM Quickteller q WHERE q.rechargePin = :rechargePin"),
    @NamedQuery(name = "Quickteller.findBySignature", query = "SELECT q FROM Quickteller q WHERE q.signature = :signature"),
    @NamedQuery(name = "Quickteller.findByResponseCode", query = "SELECT q FROM Quickteller q WHERE q.responseCode = :responseCode"),
    @NamedQuery(name = "Quickteller.findByResponseDescription", query = "SELECT q FROM Quickteller q WHERE q.responseDescription = :responseDescription"),
    @NamedQuery(name = "Quickteller.findByMsisdn", query = "SELECT q FROM Quickteller q WHERE q.msisdn = :msisdn")})
public class Quickteller implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "request_reference", nullable = false, length = 255)
    private String requestReference;
    @Size(max = 255)
    @Column(name = "payment_reference", length = 255)
    private String paymentReference;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 22)
    private Double amount;
    @Size(max = 255)
    @Column(name = "customer_id", length = 255)
    private String customerId;
    @Size(max = 255)
    @Column(name = "transaction_date", length = 255)
    private String transactionDate;
    @Size(max = 255)
    @Column(name = "recharge_pin", length = 255)
    private String rechargePin;
    @Size(max = 255)
    @Column(name = "signature", length = 255)
    private String signature;
    @Size(max = 255)
    @Column(name = "response_code", length = 255)
    private String responseCode;
    @Size(max = 255)
    @Column(name = "response_description", length = 255)
    private String responseDescription;
    @Size(max = 255)
    @Column(name = "msisdn", length = 255)
    private String msisdn;
    @Column(name = "server_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serverTime;

    public Quickteller() {
    }

    public Quickteller(String requestReference) {
        this.requestReference = requestReference;
    }

    public String getRequestReference() {
        return requestReference;
    }

    public void setRequestReference(String requestReference) {
        this.requestReference = requestReference;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRechargePin() {
        return rechargePin;
    }

    public void setRechargePin(String rechargePin) {
        this.rechargePin = rechargePin;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
    /**
     * @return the serverTime
     */
    public Date getServerTime() {
        return serverTime;
    }

    /**
     * @param serverTime the serverTime to set
     */
    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestReference != null ? requestReference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quickteller)) {
            return false;
        }
        Quickteller other = (Quickteller) object;
        if ((this.requestReference == null && other.requestReference != null) || (this.requestReference != null && !this.requestReference.equals(other.requestReference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.quickteller.entities.Quickteller[ requestReference=" + requestReference + " ]";
    }
    
}
