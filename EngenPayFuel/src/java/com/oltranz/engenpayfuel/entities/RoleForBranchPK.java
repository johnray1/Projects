/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JohnRay
 */
@Embeddable
public class RoleForBranchPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "branch_id", nullable = false)
    private int branchId;

    public RoleForBranchPK() {
    }

    public RoleForBranchPK(int roleId, int branchId) {
        this.roleId = roleId;
        this.branchId = branchId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) branchId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleForBranchPK)) {
            return false;
        }
        RoleForBranchPK other = (RoleForBranchPK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.branchId != other.branchId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.RoleForBranchPK[ roleId=" + roleId + ", branchId=" + branchId + " ]";
    }
    
}
