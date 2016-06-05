/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "actions_on_objects_results")

@XmlRootElement
public class ActionOnObjectResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    @EmbeddedId
    private ActionOnObjectResultPK actionOnObjectResultPK;
    
    @Column(name = "descr")
    private String descr;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getActionOnObjectResultPK() != null ? getActionOnObjectResultPK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionOnObjectResultPK)) {
            return false;
        }
        ActionOnObjectResult other = (ActionOnObjectResult) object;
        if ((this.getActionOnObjectResultPK() == null && other.getActionOnObjectResultPK() != null) || (this.getActionOnObjectResultPK() != null && !this.actionOnObjectResultPK.equals(other.actionOnObjectResultPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.ActionOnObjectResult[ ActionOnObjectResultPK=" + getActionOnObjectResultPK() + " ]";
    }
    
    /**
     * @return the actionOnObjectResultPK
     */
    public ActionOnObjectResultPK getActionOnObjectResultPK() {
        return actionOnObjectResultPK;
    }
    
    /**
     * @param actionOnObjectResultPK the actionOnObjectResultPK to set
     */
    public void setActionOnObjectResultPK(ActionOnObjectResultPK actionOnObjectResultPK) {
        this.actionOnObjectResultPK = actionOnObjectResultPK;
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
    
    
    
    
    
}
