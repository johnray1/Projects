/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities; 

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
public class UserRoleForTransporterPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id", nullable = false)
    private int roleId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "transporter_id", nullable = false)
    private int transporterId;
    
    
    
    public UserRoleForTransporterPK() {
    }
    
    public UserRoleForTransporterPK(Integer roleId, Integer transporterId) {
        this.roleId = roleId;
        this.transporterId = transporterId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getRoleId();
        hash += (int) getTransporterId();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoleForTransporterPK)) {
            return false;
        }
        UserRoleForTransporterPK other = (UserRoleForTransporterPK) object;
        if (this.getTransporterId() != other.getTransporterId()) {
            return false;
        }
        if (this.getRoleId() != other.getRoleId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Passenger[ RoleId=" + getRoleId() + ", transporterId=" + getTransporterId() + " ]";
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
     * @return the transporterId
     */
    public int getTransporterId() {
        return transporterId;
    }

    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

       
    
}
