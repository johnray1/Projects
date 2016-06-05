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
public class ActionOnObjectResultPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "object_id", nullable = false)
    private int objectId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "action_id", nullable = false)
    private int actionId;
    
    
    
    public ActionOnObjectResultPK() {
    }
    
    public ActionOnObjectResultPK(Integer productId, Integer actionId) {
        this.objectId = productId;
        this.actionId = actionId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getObjectId();
        hash += (int) getActionId();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionOnObjectResultPK)) {
            return false;
        }
        ActionOnObjectResultPK other = (ActionOnObjectResultPK) object;
        if (this.getActionId() != other.getActionId()) {
            return false;
        }
        if (this.getObjectId() != other.getObjectId()) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Passenger[ ObjectId=" + getObjectId() + ", actionId=" + getActionId() + " ]";
    }

    /**
     * @return the objectId
     */
    public int getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the actionId
     */
    public int getActionId() {
        return actionId;
    }

    /**
     * @param actionId the actionId to set
     */
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

       
    
}
