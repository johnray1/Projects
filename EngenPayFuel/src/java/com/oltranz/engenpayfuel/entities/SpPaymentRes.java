/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "spPaymentRes", catalog = "EngenPayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"transaction_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpPaymentRes.findAll", query = "SELECT s FROM SpPaymentRes s"),
    @NamedQuery(name = "SpPaymentRes.findBySpTransactionId", query = "SELECT s FROM SpPaymentRes s WHERE s.spTransactionId = :spTransactionId"),
    @NamedQuery(name = "SpPaymentRes.findByTransactionId", query = "SELECT s FROM SpPaymentRes s WHERE s.transactionId = :transactionId"),
    @NamedQuery(name = "SpPaymentRes.findByContractId", query = "SELECT s FROM SpPaymentRes s WHERE s.contractId = :contractId"),
    @NamedQuery(name = "SpPaymentRes.findByStatusCode", query = "SELECT s FROM SpPaymentRes s WHERE s.statusCode = :statusCode"),
    @NamedQuery(name = "SpPaymentRes.findByStatusDesc", query = "SELECT s FROM SpPaymentRes s WHERE s.statusDesc = :statusDesc")})
public class SpPaymentRes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sp_transaction_id", nullable = false)
    private Long spTransactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private long transactionId;
    @Column(name = "contract_id")
    private Integer contractId;
    @Column(name = "status_code")
    private Integer statusCode;
    @Size(max = 255)
    @Column(name = "status_desc", length = 255)
    private String statusDesc;

    public SpPaymentRes() {
    }

    public SpPaymentRes(Long spTransactionId) {
        this.spTransactionId = spTransactionId;
    }

    public SpPaymentRes(Long spTransactionId, long transactionId) {
        this.spTransactionId = spTransactionId;
        this.transactionId = transactionId;
    }

    public Long getSpTransactionId() {
        return spTransactionId;
    }

    public void setSpTransactionId(Long spTransactionId) {
        this.spTransactionId = spTransactionId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spTransactionId != null ? spTransactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpPaymentRes)) {
            return false;
        }
        SpPaymentRes other = (SpPaymentRes) object;
        if ((this.spTransactionId == null && other.spTransactionId != null) || (this.spTransactionId != null && !this.spTransactionId.equals(other.spTransactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.SpPaymentRes[ spTransactionId=" + spTransactionId + " ]";
    }
    
}
