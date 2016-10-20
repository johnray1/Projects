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
@Table(name = "role_for_branch", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleForBranch.findAll", query = "SELECT r FROM RoleForBranch r"),
    @NamedQuery(name = "RoleForBranch.findByRoleId", query = "SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId"),
    @NamedQuery(name = "RoleForBranch.findByBranchId", query = "SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.branchId = :branchId")})
public class RoleForBranch implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoleForBranchPK roleForBranchPK;

    public RoleForBranch() {
    }

    public RoleForBranch(RoleForBranchPK roleForBranchPK) {
        this.roleForBranchPK = roleForBranchPK;
    }

    public RoleForBranch(int roleId, int branchId) {
        this.roleForBranchPK = new RoleForBranchPK(roleId, branchId);
    }

    public RoleForBranchPK getRoleForBranchPK() {
        return roleForBranchPK;
    }

    public void setRoleForBranchPK(RoleForBranchPK roleForBranchPK) {
        this.roleForBranchPK = roleForBranchPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleForBranchPK != null ? roleForBranchPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleForBranch)) {
            return false;
        }
        RoleForBranch other = (RoleForBranch) object;
        if ((this.roleForBranchPK == null && other.roleForBranchPK != null) || (this.roleForBranchPK != null && !this.roleForBranchPK.equals(other.roleForBranchPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.RoleForBranch[ roleForBranchPK=" + roleForBranchPK + " ]";
    }
    
}
