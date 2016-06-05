/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "pump", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pump.findAll", query = "SELECT p FROM Pump p"),
    @NamedQuery(name = "Pump.findByPumpId", query = "SELECT p FROM Pump p WHERE p.pumpId = :pumpId"),
    @NamedQuery(name = "Pump.findByName", query = "SELECT p FROM Pump p WHERE p.name = :name"),
    @NamedQuery(name = "Pump.findByStatus", query = "SELECT p FROM Pump p WHERE p.status = :status"),
    @NamedQuery(name = "Pump.findByBranchId", query = "SELECT p FROM Pump p WHERE p.branchId = :branchId")})
public class Pump implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pump_id", nullable = false)
    private Integer pumpId;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "status")
    private Integer status=7;
    @Column(name = "branch_id")
    private Integer branchId;

    public Pump() {
    }

    public Pump(Integer pumpId) {
        this.pumpId = pumpId;
    }

    public Integer getPumpId() {
        return pumpId;
    }

    public void setPumpId(Integer pumpId) {
        this.pumpId = pumpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pumpId != null ? pumpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pump)) {
            return false;
        }
        Pump other = (Pump) object;
        if ((this.pumpId == null && other.pumpId != null) || (this.pumpId != null && !this.pumpId.equals(other.pumpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.Pump[ pumpId=" + pumpId + " ]";
    }
    
}
