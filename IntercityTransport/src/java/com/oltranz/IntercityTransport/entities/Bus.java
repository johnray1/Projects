/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "buses")
public class Bus implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "number_plate")
    private String numberPlate;
    
    @Column(name = "transporter_owned")
    private Boolean transporterOwned;
    
    @Column(name = "ownerId")
    private Integer ownerId;
    
    @Column(name = "descr", length = 255)
    private String descr;
    
    @Column(name = "status")
    private int status=7; //undeleted, enabled, unhidden
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getNumberPlate() != null ? getNumberPlate().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.getNumberPlate() == null && other.getNumberPlate() != null) || (this.getNumberPlate() != null && !this.numberPlate.equals(other.numberPlate))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Bus[ id=" + getNumberPlate() + " ]";
    }
    
    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }
    
    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
    
  
    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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

    /**
     * @return the transporterOwned
     */
    public Boolean isTransporterOwned() {
        return transporterOwned;
    }

    /**
     * @param transporterOwned the transporterOwned to set
     */
    public void setTransporterOwned(Boolean transporterOwned) {
        this.transporterOwned = transporterOwned;
    }
    
    
    
}
