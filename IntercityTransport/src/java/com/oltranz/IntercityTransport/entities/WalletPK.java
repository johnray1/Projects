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
public class WalletPK implements Serializable {
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "type_id", nullable = false)
    private Integer typeId;
    
    
    public WalletPK() {
    }
    
    public WalletPK( Integer ownerId,Integer typeId) {
        this.typeId = typeId;
        this.ownerId = ownerId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash +=  getTypeId();
        hash += getOwnerId();
        return hash;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WalletPK)) {
            return false;
        }
        WalletPK other = (WalletPK) object;
        if (this.getTypeId() != other.getTypeId()) {
            return false;
        }
        if (this.getOwnerId() != other.getOwnerId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "userRole.entities.AAA.oltranz.com[ typeId=" + getTypeId() + ", ownerId=" + getOwnerId() + " ]";
    }
    
    /**
     * @return the typeId
     */
    public Integer getTypeId() {
        return typeId;
    }
    
    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    
    /**
     * @return the ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }
    
    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    
    
}
