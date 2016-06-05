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
public class ProfileOnSellingDevicePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "profile_id", nullable = false)
    private int profileId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "device_id", nullable = false)
    private String deviceId;
    
    
    
    public ProfileOnSellingDevicePK() {
    }
    
    public ProfileOnSellingDevicePK(Integer profileId, String deviceId) {
        this.profileId = profileId;
        this.deviceId = deviceId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getProfileId();
        hash += getDeviceId().hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileOnSellingDevicePK)) {
            return false;
        }
        ProfileOnSellingDevicePK other = (ProfileOnSellingDevicePK) object;
        if (this.getDeviceId() != other.getDeviceId()) {
            return false;
        }
        if (this.getProfileId() != other.getProfileId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Passenger[ ProfileId=" + getProfileId() + ", deviceId=" + getDeviceId() + " ]";
    }

    /**
     * @return the profileId
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

       
    
}
