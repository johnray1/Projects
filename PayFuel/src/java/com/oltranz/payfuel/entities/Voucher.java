/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "voucher", catalog = "PayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"voucher_no"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByVoucherId", query = "SELECT v FROM Voucher v WHERE v.voucherId = :voucherId"),
    @NamedQuery(name = "Voucher.findByVoucherNo", query = "SELECT v FROM Voucher v WHERE v.voucherNo = :voucherNo"),
    @NamedQuery(name = "Voucher.findByInitialAmount", query = "SELECT v FROM Voucher v WHERE v.initialAmount = :initialAmount"),
    @NamedQuery(name = "Voucher.findByRemainAmount", query = "SELECT v FROM Voucher v WHERE v.remainAmount = :remainAmount"),
    @NamedQuery(name = "Voucher.findByNumberPlate", query = "SELECT v FROM Voucher v WHERE v.numberPlate = :numberPlate"),
    @NamedQuery(name = "Voucher.findByProvisonDate", query = "SELECT v FROM Voucher v WHERE v.provisonDate = :provisonDate"),
    @NamedQuery(name = "Voucher.findByExpiryDate", query = "SELECT v FROM Voucher v WHERE v.expiryDate = :expiryDate"),
    @NamedQuery(name = "Voucher.findByStatus", query = "SELECT v FROM Voucher v WHERE v.status = :status"),
    @NamedQuery(name = "Voucher.findByCustomerId", query = "SELECT v FROM Voucher v WHERE v.customerId = :customerId")})
public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "voucher_id", nullable = false)
    private Long voucherId;
    @Size(max = 255)
    @Column(name = "voucher_no", length = 255)
    private String voucherNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "initial_amount", precision = 22)
    private Double initialAmount;
    @Column(name = "remain_amount", precision = 22)
    private Double remainAmount;
    @Size(max = 255)
    @Column(name = "number_plate", length = 255)
    private String numberPlate;
    @Column(name = "provison_date")
    @Temporal(TemporalType.DATE)
    private Date provisonDate;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Column(name = "status")
    private Integer status=7;
    @Column(name = "customer_id")
    private Long customerId;

    public Voucher() {
    }

    public Voucher(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Double getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Double remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Date getProvisonDate() {
        return provisonDate;
    }

    public void setProvisonDate(Date provisonDate) {
        this.provisonDate = provisonDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voucherId != null ? voucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.voucherId == null && other.voucherId != null) || (this.voucherId != null && !this.voucherId.equals(other.voucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.Voucher[ voucherId=" + voucherId + " ]";
    }
    
}
