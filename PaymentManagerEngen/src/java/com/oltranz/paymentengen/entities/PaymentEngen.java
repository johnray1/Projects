/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.paymentengen.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "payment_engen", catalog = "PaymentEngenDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentEngen.findAll", query = "SELECT p FROM PaymentEngen p"),
    @NamedQuery(name = "PaymentEngen.findByRequestId", query = "SELECT p FROM PaymentEngen p WHERE p.requestId = :requestId"),
    @NamedQuery(name = "PaymentEngen.findByRequestDatetime", query = "SELECT p FROM PaymentEngen p WHERE p.requestDatetime = :requestDatetime"),
    @NamedQuery(name = "PaymentEngen.findByResponseDatetime", query = "SELECT p FROM PaymentEngen p WHERE p.responseDatetime = :responseDatetime"),
    @NamedQuery(name = "PaymentEngen.findByTransactionId", query = "SELECT p FROM PaymentEngen p WHERE p.transactionId = :transactionId"),
    @NamedQuery(name = "PaymentEngen.findByAmount", query = "SELECT p FROM PaymentEngen p WHERE p.amount = :amount"),
    @NamedQuery(name = "PaymentEngen.findByContact", query = "SELECT p FROM PaymentEngen p WHERE p.contact = :contact"),
    @NamedQuery(name = "PaymentEngen.findByDescr", query = "SELECT p FROM PaymentEngen p WHERE p.descr = :descr")})
public class PaymentEngen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "request_id", nullable = false)
    private Long requestId;
    @Column(name = "request_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDatetime;
    @Column(name = "response_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDatetime;
    @Column(name = "transaction_id")
    private Long transactionId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 22)
    private Double amount;
    @Size(max = 255)
    @Column(name = "contact", length = 255)
    private String contact;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;

    public PaymentEngen() {
    }

    public PaymentEngen(Long requestId) {
        this.requestId = requestId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Date getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(Date requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public Date getResponseDatetime() {
        return responseDatetime;
    }

    public void setResponseDatetime(Date responseDatetime) {
        this.responseDatetime = responseDatetime;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentEngen)) {
            return false;
        }
        PaymentEngen other = (PaymentEngen) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.paymentengen.entities.PaymentEngen[ requestId=" + requestId + " ]";
    }
    
}
