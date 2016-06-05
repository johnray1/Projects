/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities; 

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "profiles_on_selling_devices")

@XmlRootElement
public class ProfileOnSellingDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }  
    
    @EmbeddedId
    private ProfileOnSellingDevicePK profileOnSellingDevicePK;
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getProfileOnSellingDevicePK() != null ? getProfileOnSellingDevicePK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileOnSellingDevicePK)) {
            return false;
        }
        ProfileOnSellingDevice other = (ProfileOnSellingDevice) object;
        if ((this.getProfileOnSellingDevicePK() == null && other.getProfileOnSellingDevicePK() != null) || (this.getProfileOnSellingDevicePK() != null && !this.profileOnSellingDevicePK.equals(other.profileOnSellingDevicePK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.ProfileOnSellingDevice[ ProfileOnSellingDevicePK=" + getProfileOnSellingDevicePK() + " ]";
    }

    /**
     * @return the profileOnSellingDevicePK
     */
    public ProfileOnSellingDevicePK getProfileOnSellingDevicePK() {
        return profileOnSellingDevicePK;
    }

    /**
     * @param profileOnSellingDevicePK the profileOnSellingDevicePK to set
     */
    public void setProfileOnSellingDevicePK(ProfileOnSellingDevicePK profileOnSellingDevicePK) {
        this.profileOnSellingDevicePK = profileOnSellingDevicePK;
    }


    

    
}
