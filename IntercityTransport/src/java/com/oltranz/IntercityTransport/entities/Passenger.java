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
@Table(name = "passengers")

@XmlRootElement
public class Passenger implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }  
    
    @EmbeddedId
    private PassengerPK passengerPK;
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getPassengerPK() != null ? getPassengerPK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassengerPK)) {
            return false;
        }
        Passenger other = (Passenger) object;
        if ((this.getPassengerPK() == null && other.getPassengerPK() != null) || (this.getPassengerPK() != null && !this.passengerPK.equals(other.passengerPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Passenger[ PassengerPK=" + getPassengerPK() + " ]";
    }

    /**
     * @return the passengerPK
     */
    public PassengerPK getPassengerPK() {
        return passengerPK;
    }

    /**
     * @param passengerPK the passengerPK to set
     */
    public void setPassengerPK(PassengerPK passengerPK) {
        this.passengerPK = passengerPK;
    }


    

    
}
