/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ismaelnzamutuma
 */
@Embeddable
public class UserInRolePK implements Serializable {
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id", nullable = false)
    private int roleId;
    
    
    public UserInRolePK() {
    }
    
    public UserInRolePK( Integer userId,Integer roleId) {
        this.roleId = roleId;
        this.userId = userId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getRoleId();
        hash += (int) getUserId();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInRolePK)) {
            return false;
        }
        UserInRolePK other = (UserInRolePK) object;
        if (this.getRoleId() != other.getRoleId()) {
            return false;
        }
        if (this.getUserId() != other.getUserId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "userRole.entities.AAA.oltranz.com[ roleId=" + getRoleId() + ", userId=" + getUserId() + " ]";
    }
    
    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }
    
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
