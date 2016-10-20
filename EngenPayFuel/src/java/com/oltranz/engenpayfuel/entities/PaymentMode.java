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
@Table(name = "paymentMode", catalog = "EngenPayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMode.findAll", query = "SELECT p FROM PaymentMode p"),
    @NamedQuery(name = "PaymentMode.findByPaymentModeId", query = "SELECT p FROM PaymentMode p WHERE p.paymentModeId = :paymentModeId"),
    @NamedQuery(name = "PaymentMode.findByName", query = "SELECT p FROM PaymentMode p WHERE p.name = :name"),
    @NamedQuery(name = "PaymentMode.findByDescr", query = "SELECT p FROM PaymentMode p WHERE p.descr = :descr"),
    @NamedQuery(name = "PaymentMode.findByStatus", query = "SELECT p FROM PaymentMode p WHERE p.status = :status")})
public class PaymentMode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_mode_id", nullable = false)
    private Integer paymentModeId;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;
    @Column(name = "status")
    private Integer status=7;

    public PaymentMode() {
    }

    public PaymentMode(Integer paymentModeId, String name, String descr) {
        this.paymentModeId = paymentModeId;
        this.name = name;
        this.descr = descr;
    }

    public PaymentMode(Integer paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public Integer getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(Integer paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentModeId != null ? paymentModeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMode)) {
            return false;
        }
        PaymentMode other = (PaymentMode) object;
        if ((this.paymentModeId == null && other.paymentModeId != null) || (this.paymentModeId != null && !this.paymentModeId.equals(other.paymentModeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.PaymentMode[ paymentModeId=" + paymentModeId + " ]";
    }
    
}
