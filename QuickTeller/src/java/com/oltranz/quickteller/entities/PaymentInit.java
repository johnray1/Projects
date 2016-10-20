/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.quickteller.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "payment_init", catalog = "QuicktellerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentInit.findAll", query = "SELECT p FROM PaymentInit p"),
    @NamedQuery(name = "PaymentInit.findByInitUid", query = "SELECT p FROM PaymentInit p WHERE p.initUid = :initUid"),
    @NamedQuery(name = "PaymentInit.findByPaymentSpId", query = "SELECT p FROM PaymentInit p WHERE p.paymentSpId = :paymentSpId"),
    @NamedQuery(name = "PaymentInit.findByPaymentTypeId", query = "SELECT p FROM PaymentInit p WHERE p.paymentTypeId = :paymentTypeId"),
    @NamedQuery(name = "PaymentInit.findByAmount", query = "SELECT p FROM PaymentInit p WHERE p.amount = :amount"),
    @NamedQuery(name = "PaymentInit.findByMsisdn", query = "SELECT p FROM PaymentInit p WHERE p.msisdn = :msisdn")})
public class PaymentInit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "init_uid", nullable = false, length = 255)
    private String initUid;
    @Column(name = "payment_sp_id")
    private Integer paymentSpId;
    @Column(name = "payment_type_id")
    private Integer paymentTypeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 22)
    private Double amount;
    @Size(max = 255)
    @Column(name = "msisdn", length = 255)
    private String msisdn;

    public PaymentInit() {
    }

    public PaymentInit(String initUid) {
        this.initUid = initUid;
    }

    public String getInitUid() {
        return initUid;
    }

    public void setInitUid(String initUid) {
        this.initUid = initUid;
    }

    public Integer getPaymentSpId() {
        return paymentSpId;
    }

    public void setPaymentSpId(Integer paymentSpId) {
        this.paymentSpId = paymentSpId;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (initUid != null ? initUid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentInit)) {
            return false;
        }
        PaymentInit other = (PaymentInit) object;
        if ((this.initUid == null && other.initUid != null) || (this.initUid != null && !this.initUid.equals(other.initUid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.quickteller.entities.PaymentInit[ initUid=" + initUid + " ]";
    }
    
}
