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
@Table(name = "tank", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tank.findAll", query = "SELECT t FROM Tank t"),
    @NamedQuery(name = "Tank.findByTankId", query = "SELECT t FROM Tank t WHERE t.tankId = :tankId"),
    @NamedQuery(name = "Tank.findByName", query = "SELECT t FROM Tank t WHERE t.name = :name"),
    @NamedQuery(name = "Tank.findByBranchId", query = "SELECT t FROM Tank t WHERE t.branchId = :branchId")})
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
    @Column(name = "branch_id")
    private Integer branchId;

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

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
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
        return "com.oltranz.payfuel.entities.Tank[ tankId=" + tankId + " ]";
    }
    
}
