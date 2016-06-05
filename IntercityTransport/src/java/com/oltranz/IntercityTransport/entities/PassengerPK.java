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
public class PassengerPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "Transporter_id", nullable = false)
    private int transporterId;
    
    
    
    public PassengerPK() {
    }
    
    public PassengerPK(Integer userId, Integer transporterId) {
        this.userId = userId;
        this.transporterId = transporterId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getUserId();
        hash += (int) getTransporterId();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassengerPK)) {
            return false;
        }
        PassengerPK other = (PassengerPK) object;
        if (this.getTransporterId() != other.getTransporterId()) {
            return false;
        }
        if (this.getUserId() != other.getUserId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Passenger[ UserId=" + getUserId() + ", transporterId=" + getTransporterId() + " ]";
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
