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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "index_tracking", catalog = "PayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"transaction_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IndexTracking.findAll", query = "SELECT i FROM IndexTracking i"),
    @NamedQuery(name = "IndexTracking.findById", query = "SELECT i FROM IndexTracking i WHERE i.id = :id"),
    @NamedQuery(name = "IndexTracking.findByTransactionId", query = "SELECT i FROM IndexTracking i WHERE i.transactionId = :transactionId"),
    @NamedQuery(name = "IndexTracking.findByTransactionTypeId", query = "SELECT i FROM IndexTracking i WHERE i.transactionTypeId = :transactionTypeId"),
    @NamedQuery(name = "IndexTracking.findByIndexbefore", query = "SELECT i FROM IndexTracking i WHERE i.indexbefore = :indexbefore"),
    @NamedQuery(name = "IndexTracking.findByIndexafter", query = "SELECT i FROM IndexTracking i WHERE i.indexafter = :indexafter"),
    @NamedQuery(name = "IndexTracking.findByDateTime", query = "SELECT i FROM IndexTracking i WHERE i.dateTime = :dateTime"),
    @NamedQuery(name = "IndexTracking.findByUserId", query = "SELECT i FROM IndexTracking i WHERE i.userId = :userId")})
public class IndexTracking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_type_id", nullable = false)
    private int transactionTypeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "indexbefore", precision = 22)
    private Double indexbefore;
    @Column(name = "indexafter", precision = 22)
    private Double indexafter;
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Column(name = "user_id")
    private Integer userId;

    public IndexTracking() {
    }

    public IndexTracking(Long id) {
        this.id = id;
    }

    public IndexTracking(Long id, long transactionId, int transactionTypeId) {
        this.id = id;
        this.transactionId = transactionId;
        this.transactionTypeId = transactionTypeId;
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

    public Double getIndexbefore() {
        return indexbefore;
    }

    public void setIndexbefore(Double indexbefore) {
        this.indexbefore = indexbefore;
    }

    public Double getIndexafter() {
        return indexafter;
    }

    public void setIndexafter(Double indexafter) {
        this.indexafter = indexafter;
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
        if (!(object instanceof IndexTracking)) {
            return false;
        }
        IndexTracking other = (IndexTracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.IndexTracking[ id=" + id + " ]";
    }
    
}
