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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "role_type", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleType.findAll", query = "SELECT r FROM RoleType r"),
    @NamedQuery(name = "RoleType.findById", query = "SELECT r FROM RoleType r WHERE r.id = :id"),
    @NamedQuery(name = "RoleType.findByName", query = "SELECT r FROM RoleType r WHERE r.name = :name"),
    @NamedQuery(name = "RoleType.findByDescr", query = "SELECT r FROM RoleType r WHERE r.descr = :descr")})
public class RoleType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;

    public RoleType() {
    }

    public RoleType(Integer id, String name, String descr) {
        this.id = id;
        this.name = name;
        this.descr = descr;
    }

    public RoleType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleType)) {
            return false;
        }
        RoleType other = (RoleType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.RoleType[ id=" + id + " ]";
    }
    
}
