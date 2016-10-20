/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "action_on_object_result", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionOnObjectResult.findAll", query = "SELECT a FROM ActionOnObjectResult a"),
    @NamedQuery(name = "ActionOnObjectResult.findByObjectId", query = "SELECT a FROM ActionOnObjectResult a WHERE a.actionOnObjectResultPK.objectId = :objectId"),
    @NamedQuery(name = "ActionOnObjectResult.findByActionId", query = "SELECT a FROM ActionOnObjectResult a WHERE a.actionOnObjectResultPK.actionId = :actionId")})
public class ActionOnObjectResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActionOnObjectResultPK actionOnObjectResultPK;

    public ActionOnObjectResult() {
    }

    public ActionOnObjectResult(ActionOnObjectResultPK actionOnObjectResultPK) {
        this.actionOnObjectResultPK = actionOnObjectResultPK;
    }

    public ActionOnObjectResult(int objectId, int actionId) {
        this.actionOnObjectResultPK = new ActionOnObjectResultPK(objectId, actionId);
    }

    public ActionOnObjectResultPK getActionOnObjectResultPK() {
        return actionOnObjectResultPK;
    }

    public void setActionOnObjectResultPK(ActionOnObjectResultPK actionOnObjectResultPK) {
        this.actionOnObjectResultPK = actionOnObjectResultPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionOnObjectResultPK != null ? actionOnObjectResultPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionOnObjectResult)) {
            return false;
        }
        ActionOnObjectResult other = (ActionOnObjectResult) object;
        if ((this.actionOnObjectResultPK == null && other.actionOnObjectResultPK != null) || (this.actionOnObjectResultPK != null && !this.actionOnObjectResultPK.equals(other.actionOnObjectResultPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.ActionOnObjectResult[ actionOnObjectResultPK=" + actionOnObjectResultPK + " ]";
    }
    
}
