/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

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
 * @author JohnRay
 */
@Entity
@Table(name = "diping", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diping.findAll", query = "SELECT d FROM Diping d"),
    @NamedQuery(name = "Diping.findById", query = "SELECT d FROM Diping d WHERE d.id = :id"),
    @NamedQuery(name = "Diping.findByUserId", query = "SELECT d FROM Diping d WHERE d.userId = :userId"),
    @NamedQuery(name = "Diping.findByTankId", query = "SELECT d FROM Diping d WHERE d.tankId = :tankId"),
    @NamedQuery(name = "Diping.findByMeasuredDip", query = "SELECT d FROM Diping d WHERE d.measuredDip = :measuredDip"),
    @NamedQuery(name = "Diping.findByCalculatedDip", query = "SELECT d FROM Diping d WHERE d.calculatedDip = :calculatedDip"),
    @NamedQuery(name = "Diping.findByDatetime", query = "SELECT d FROM Diping d WHERE d.datetime = :datetime"),
    @NamedQuery(name = "Diping.findByWaterValue", query = "SELECT d FROM Diping d WHERE d.waterValue = :waterValue")})
public class Diping implements Serializable {
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
    @Column(name = "measured_dip", precision = 22)
    private Double measuredDip;
    @Column(name = "calculated_dip", precision = 22)
    private Double calculatedDip;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "water_value", precision = 22)
    private Double waterValue;

    public Diping() {
    }

    public Diping(Integer id) {
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

    public Double getMeasuredDip() {
        return measuredDip;
    }

    public void setMeasuredDip(Double measuredDip) {
        this.measuredDip = measuredDip;
    }

    public Double getCalculatedDip() {
        return calculatedDip;
    }

    public void setCalculatedDip(Double calculatedDip) {
        this.calculatedDip = calculatedDip;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(Double waterValue) {
        this.waterValue = waterValue;
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
        if (!(object instanceof Diping)) {
            return false;
        }
        Diping other = (Diping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.Diping[ id=" + id + " ]";
    }
    
}
