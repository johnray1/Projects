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
@Table(name = "selling_devices")
public class SellingDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "transporter_id")
    private Integer transporterId;
    
    @Column(name = "current_bus")
    private String currentBusNumberPlate;
    
    @Column(name = "type_id")
    private Integer typeId;
    
    @Column(name = "descr", length = 255)
    private String decr;
    
    @Column(name = "status")
    private int status=7; //undeleted, enabled, unhidden
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SellingDevice)) {
            return false;
        }
        SellingDevice other = (SellingDevice) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.SellingDevice[ id=" + getId() + " ]";
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the transporterId
     */
    public Integer getTransporterId() {
        return transporterId;
    }
    
    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
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
     * @return the decr
     */
    public String getDecr() {
        return decr;
    }
    
    /**
     * @param decr the decr to set
     */
    public void setDecr(String decr) {
        this.decr = decr;
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
     * @return the currentBusNumberPlate
     */
    public String getCurrentBusNumberPlate() {
        return currentBusNumberPlate;
    }

    /**
     * @param currentBusNumberPlate the currentBusNumberPlate to set
     */
    public void setCurrentBusNumberPlate(String currentBusNumberPlate) {
        this.currentBusNumberPlate = currentBusNumberPlate;
    }
    
    
    
}
