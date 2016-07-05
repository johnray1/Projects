/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "tank_tracking", catalog = "PayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"transaction_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TankTracking.findAll", query = "SELECT t FROM TankTracking t"),
    @NamedQuery(name = "TankTracking.findById", query = "SELECT t FROM TankTracking t WHERE t.id = :id"),
    @NamedQuery(name = "TankTracking.findByTransactionId", query = "SELECT t FROM TankTracking t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "TankTracking.findByTransactionTypeId", query = "SELECT t FROM TankTracking t WHERE t.transactionTypeId = :transactionTypeId"),
    @NamedQuery(name = "TankTracking.findByTankId", query = "SELECT t FROM TankTracking t WHERE t.tankId = :tankId"),
    @NamedQuery(name = "TankTracking.findByQuantitybefore", query = "SELECT t FROM TankTracking t WHERE t.quantitybefore = :quantitybefore"),
    @NamedQuery(name = "TankTracking.findByQuantityafter", query = "SELECT t FROM TankTracking t WHERE t.quantityafter = :quantityafter"),
    @NamedQuery(name = "TankTracking.findByDateTime", query = "SELECT t FROM TankTracking t WHERE t.dateTime = :dateTime"),
    @NamedQuery(name = "TankTracking.findByUserId", query = "SELECT t FROM TankTracking t WHERE t.userId = :userId")})
public class TankTracking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "transaction_id")
    private Long transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_type_id", nullable = false)
    private int transactionTypeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tank_id", nullable = false)
    private int tankId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantitybefore", precision = 22)
    private Double quantitybefore;
    @Column(name = "quantityafter", precision = 22)
    private Double quantityafter;
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Column(name = "user_id")
    private Integer userId;

    public TankTracking() {
    }

    public TankTracking(Long id) {
        this.id = id;
    }

    public TankTracking(Long id, int transactionTypeId, int tankId) {
        this.id = id;
        this.transactionTypeId = transactionTypeId;
        this.tankId = tankId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public int getTankId() {
        return tankId;
    }

    public void setTankId(int tankId) {
        this.tankId = tankId;
    }

    public Double getQuantitybefore() {
        return quantitybefore;
    }

    public void setQuantitybefore(Double quantitybefore) {
        this.quantitybefore = quantitybefore;
    }

    public Double getQuantityafter() {
        return quantityafter;
    }

    public void setQuantityafter(Double quantityafter) {
        this.quantityafter = quantityafter;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TankTracking)) {
            return false;
        }
        TankTracking other = (TankTracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.TankTracking[ id=" + id + " ]";
    }
    
}
