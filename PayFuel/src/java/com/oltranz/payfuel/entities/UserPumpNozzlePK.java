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
public class UserPumpNozzlePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pump_id", nullable = false)
    private int pumpId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nozzle_id", nullable = false)
    private int nozzleId;

    public UserPumpNozzlePK() {
    }

    public UserPumpNozzlePK(int userId, int pumpId, int nozzleId) {
        this.userId = userId;
        this.pumpId = pumpId;
        this.nozzleId = nozzleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPumpId() {
        return pumpId;
    }

    public void setPumpId(int pumpId) {
        this.pumpId = pumpId;
    }

    public int getNozzleId() {
        return nozzleId;
    }

    public void setNozzleId(int nozzleId) {
        this.nozzleId = nozzleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) pumpId;
        hash += (int) nozzleId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPumpNozzlePK)) {
            return false;
        }
        UserPumpNozzlePK other = (UserPumpNozzlePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.pumpId != other.pumpId) {
            return false;
        }
        if (this.nozzleId != other.nozzleId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.UserPumpNozzlePK[ userId=" + userId + ", pumpId=" + pumpId + ", nozzleId=" + nozzleId + " ]";
    }
    
}
