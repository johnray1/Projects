/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

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
 * @author JohnRay
 */
@Entity
@Table(name = "customer_type", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerType.findAll", query = "SELECT c FROM CustomerType c"),
    @NamedQuery(name = "CustomerType.findByCustomerTypeId", query = "SELECT c FROM CustomerType c WHERE c.customerTypeId = :customerTypeId"),
    @NamedQuery(name = "CustomerType.findByName", query = "SELECT c FROM CustomerType c WHERE c.name = :name"),
    @NamedQuery(name = "CustomerType.findByDescr", query = "SELECT c FROM CustomerType c WHERE c.descr = :descr")})
public class CustomerType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_type_id", nullable = false)
    private Integer customerTypeId;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;

    public CustomerType() {
    }

    public CustomerType(Integer customerTypeId, String name, String descr) {
        this.customerTypeId = customerTypeId;
        this.name = name;
        this.descr = descr;
    }

    public CustomerType(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
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
        hash += (customerTypeId != null ? customerTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerType)) {
            return false;
        }
        CustomerType other = (CustomerType) object;
        if ((this.customerTypeId == null && other.customerTypeId != null) || (this.customerTypeId != null && !this.customerTypeId.equals(other.customerTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.CustomerType[ customerTypeId=" + customerTypeId + " ]";
    }
    
}
