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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "tank", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tank.findAll", query = "SELECT t FROM Tank t"),
    @NamedQuery(name = "Tank.findByTankId", query = "SELECT t FROM Tank t WHERE t.tankId = :tankId"),
    @NamedQuery(name = "Tank.findByName", query = "SELECT t FROM Tank t WHERE t.name = :name"),
    @NamedQuery(name = "Tank.findByMaxCapacity", query = "SELECT t FROM Tank t WHERE t.maxCapacity = :maxCapacity"),
    @NamedQuery(name = "Tank.findByCurrentCapacity", query = "SELECT t FROM Tank t WHERE t.currentCapacity = :currentCapacity"),
    @NamedQuery(name = "Tank.findByPreCalibrationDate", query = "SELECT t FROM Tank t WHERE t.preCalibrationDate = :preCalibrationDate"),
    @NamedQuery(name = "Tank.findByNextCalibrationDate", query = "SELECT t FROM Tank t WHERE t.nextCalibrationDate = :nextCalibrationDate"),
    @NamedQuery(name = "Tank.findByStatus", query = "SELECT t FROM Tank t WHERE t.status = :status"),
    @NamedQuery(name = "Tank.findByBranchId", query = "SELECT t FROM Tank t WHERE t.branchId = :branchId"),
    @NamedQuery(name = "Tank.findByProductId", query = "SELECT t FROM Tank t WHERE t.productId = :productId")})
public class Tank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tank_id", nullable = false)
    private Integer tankId;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "max_capacity", precision = 22)
    private Double maxCapacity;
    @Column(name = "current_capacity", precision = 22)
    private Double currentCapacity;
    @Column(name = "pre_calibration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date preCalibrationDate;
    @Column(name = "next_calibration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextCalibrationDate;
    @Column(name = "status")
    private Integer status=7;
    @Column(name = "branch_id")
    private Integer branchId;
    @Column(name = "product_id")
    private Integer productId;

    public Tank() {
    }

    public Tank(Integer tankId) {
        this.tankId = tankId;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Date getPreCalibrationDate() {
        return preCalibrationDate;
    }

    public void setPreCalibrationDate(Date preCalibrationDate) {
        this.preCalibrationDate = preCalibrationDate;
    }

    public Date getNextCalibrationDate() {
        return nextCalibrationDate;
    }

    public void setNextCalibrationDate(Date nextCalibrationDate) {
        this.nextCalibrationDate = nextCalibrationDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tankId != null ? tankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tank)) {
            return false;
        }
        Tank other = (Tank) object;
        if ((this.tankId == null && other.tankId != null) || (this.tankId != null && !this.tankId.equals(other.tankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.Tank[ tankId=" + tankId + " ]";
    }
    
}
