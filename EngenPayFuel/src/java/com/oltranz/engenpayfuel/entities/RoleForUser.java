/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "role_for_user", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleForUser.findAll", query = "SELECT r FROM RoleForUser r"),
    @NamedQuery(name = "RoleForUser.findByRoleId", query = "SELECT r FROM RoleForUser r WHERE r.roleForUserPK.roleId = :roleId"),
    @NamedQuery(name = "RoleForUser.findByUserId", query = "SELECT r FROM RoleForUser r WHERE r.roleForUserPK.userId = :userId")})
public class RoleForUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleForUserPK roleForUserPK;

    public RoleForUser() {
    }

    public RoleForUser(RoleForUserPK roleForUserPK) {
        this.roleForUserPK = roleForUserPK;
    }

    public RoleForUser(int roleId, int userId) {
        this.roleForUserPK = new RoleForUserPK(roleId, userId);
    }

    public RoleForUserPK getRoleForUserPK() {
        return roleForUserPK;
    }

    public void setRoleForUserPK(RoleForUserPK roleForUserPK) {
        this.roleForUserPK = roleForUserPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleForUserPK != null ? roleForUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleForUser)) {
            return false;
        }
        RoleForUser other = (RoleForUser) object;
        if ((this.roleForUserPK == null && other.roleForUserPK != null) || (this.roleForUserPK != null && !this.roleForUserPK.equals(other.roleForUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.RoleForUser[ roleForUserPK=" + roleForUserPK + " ]";
    }
    
}
