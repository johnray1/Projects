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
@Table(name = "nozzle", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nozzle.findAll", query = "SELECT n FROM Nozzle n"),
    @NamedQuery(name = "Nozzle.findByNozzleId", query = "SELECT n FROM Nozzle n WHERE n.nozzleId = :nozzleId"),
    @NamedQuery(name = "Nozzle.findByNozzleName", query = "SELECT n FROM Nozzle n WHERE n.nozzleName = :nozzleName"),
    @NamedQuery(name = "Nozzle.findByNozzleIndex", query = "SELECT n FROM Nozzle n WHERE n.nozzleIndex = :nozzleIndex"),
    @NamedQuery(name = "Nozzle.findByNozzleStatus", query = "SELECT n FROM Nozzle n WHERE n.nozzleStatus = :nozzleStatus"),
    @NamedQuery(name = "Nozzle.findByBranchId", query = "SELECT n FROM Nozzle n WHERE n.branchId = :branchId"),
    @NamedQuery(name = "Nozzle.findByTankId", query = "SELECT n FROM Nozzle n WHERE n.tankId = :tankId"),
    @NamedQuery(name = "Nozzle.findByPumpId", query = "SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId"),
    @NamedQuery(name = "Nozzle.findByProductId", query = "SELECT n FROM Nozzle n WHERE n.productId = :productId")})
public class Nozzle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nozzle_id", nullable = false)
    private Integer nozzleId;
    @Size(max = 255)
    @Column(name = "nozzle_name", length = 255)
    private String nozzleName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nozzle_index", precision = 22)
    private Double nozzleIndex=0.0;
    @Column(name = "nozzle_status")
    private Integer nozzleStatus=7;
    @Column(name = "branch_id")
    private Integer branchId;
    @Column(name = "tank_id")
    private Integer tankId;
    @Column(name = "pump_id")
    private Integer pumpId;
    @Column(name = "product_id")
    private Integer productId;

    public Nozzle() {
    }

    public Nozzle(Integer nozzleId) {
        this.nozzleId = nozzleId;
    }

    public Integer getNozzleId() {
        return nozzleId;
    }

    public void setNozzleId(Integer nozzleId) {
        this.nozzleId = nozzleId;
    }

    public String getNozzleName() {
        return nozzleName;
    }

    public void setNozzleName(String nozzleName) {
        this.nozzleName = nozzleName;
    }

    public Double getNozzleIndex() {
        return nozzleIndex;
    }

    public void setNozzleIndex(Double nozzleIndex) {
        this.nozzleIndex = nozzleIndex;
    }

    public Integer getNozzleStatus() {
        return nozzleStatus;
    }

    public void setNozzleStatus(Integer nozzleStatus) {
        this.nozzleStatus = nozzleStatus;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public Integer getPumpId() {
        return pumpId;
    }

    public void setPumpId(Integer pumpId) {
        this.pumpId = pumpId;
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
        hash += (nozzleId != null ? nozzleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nozzle)) {
            return false;
        }
        Nozzle other = (Nozzle) object;
        if ((this.nozzleId == null && other.nozzleId != null) || (this.nozzleId != null && !this.nozzleId.equals(other.nozzleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.Nozzle[ nozzleId=" + nozzleId + " ]";
    }
    
}
