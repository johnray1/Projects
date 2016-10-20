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
 * @author JohnRay
 */
@Entity
@Table(name = "role", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId"),
    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name"),
    @NamedQuery(name = "Role.findByPermissions", query = "SELECT r FROM Role r WHERE r.permissions = :permissions"),
    @NamedQuery(name = "Role.findByStatus", query = "SELECT r FROM Role r WHERE r.status = :status"),
    @NamedQuery(name = "Role.findByTypeId", query = "SELECT r FROM Role r WHERE r.typeId = :typeId"),
    @NamedQuery(name = "Role.findByDescr", query = "SELECT r FROM Role r WHERE r.descr = :descr")})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "permissions", length = 255)
    private String permissions="00000000000000000000000000000000";
    @Column(name = "status")
    private Integer status=7;
    @Column(name = "type_id")
    private Integer typeId;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;

    public Role() {
    }

    public Role(String name, Integer typeId, String descr) {
        this.name = name;
        this.typeId = typeId;
        this.descr = descr;
    }

    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.Role[ roleId=" + roleId + " ]";
    }
    
}
