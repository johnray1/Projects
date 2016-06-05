/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author John
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

    public ActionOnObjectResultPK(int objectId, int actionId) {
        this.objectId = objectId;
        this.actionId = actionId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) objectId;
        hash += (int) actionId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionOnObjectResultPK)) {
            return false;
        }
        ActionOnObjectResultPK other = (ActionOnObjectResultPK) object;
        if (this.objectId != other.objectId) {
            return false;
        }
        if (this.actionId != other.actionId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.ActionOnObjectResultPK[ objectId=" + objectId + ", actionId=" + actionId + " ]";
    }
    
}
