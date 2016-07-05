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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "deeping", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deeping.findAll", query = "SELECT d FROM Deeping d"),
    @NamedQuery(name = "Deeping.findById", query = "SELECT d FROM Deeping d WHERE d.id = :id"),
    @NamedQuery(name = "Deeping.findByUserId", query = "SELECT d FROM Deeping d WHERE d.userId = :userId"),
    @NamedQuery(name = "Deeping.findByTankId", query = "SELECT d FROM Deeping d WHERE d.tankId = :tankId"),
    @NamedQuery(name = "Deeping.findByQuantityBefore", query = "SELECT d FROM Deeping d WHERE d.quantityBefore = :quantityBefore"),
    @NamedQuery(name = "Deeping.findByQuantityAfter", query = "SELECT d FROM Deeping d WHERE d.quantityAfter = :quantityAfter"),
    @NamedQuery(name = "Deeping.findByDatetime", query = "SELECT d FROM Deeping d WHERE d.datetime = :datetime")})
public class Deeping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "tank_id")
    private Integer tankId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity_before", precision = 22)
    private Double quantityBefore;
    @Column(name = "quantity_after", precision = 22)
    private Double quantityAfter;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    public Deeping() {
    }

    public Deeping(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public Double getQuantityBefore() {
        return quantityBefore;
    }

    public void setQuantityBefore(Double quantityBefore) {
        this.quantityBefore = quantityBefore;
    }

    public Double getQuantityAfter() {
        return quantityAfter;
    }

    public void setQuantityAfter(Double quantityAfter) {
        this.quantityAfter = quantityAfter;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
        if (!(object instanceof Deeping)) {
            return false;
        }
        Deeping other = (Deeping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.Deeping[ id=" + id + " ]";
    }
    
}
