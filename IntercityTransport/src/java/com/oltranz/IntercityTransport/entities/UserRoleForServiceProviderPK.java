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
public class UserRoleForServiceProviderPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id", nullable = false)
    private int roleId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ServiceProvider_id", nullable = false)
    private int serviceProviderId;
    
    
    
    public UserRoleForServiceProviderPK() {
    }
    
    public UserRoleForServiceProviderPK(Integer roleId, Integer serviceProviderId) {
        this.roleId = roleId;
        this.serviceProviderId = serviceProviderId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getRoleId();
        hash += (int) getServiceProviderId();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoleForServiceProviderPK)) {
            return false;
        }
        UserRoleForServiceProviderPK other = (UserRoleForServiceProviderPK) object;
        if (this.getServiceProviderId() != other.getServiceProviderId()) {
            return false;
        }
        if (this.getRoleId() != other.getRoleId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityServiceProvider.Entities.Passenger[ RoleId=" + getRoleId() + ", serviceProviderId=" + getServiceProviderId() + " ]";
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
     * @return the serviceProviderId
     */
    public int getServiceProviderId() {
        return serviceProviderId;
    }

    /**
     * @param serviceProviderId the serviceProviderId to set
     */
    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

       
    
}
