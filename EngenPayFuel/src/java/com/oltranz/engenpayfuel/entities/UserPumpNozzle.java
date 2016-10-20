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
@Table(name = "user_pump_nozzle", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPumpNozzle.findAll", query = "SELECT u FROM UserPumpNozzle u"),
    @NamedQuery(name = "UserPumpNozzle.findByUserId", query = "SELECT u FROM UserPumpNozzle u WHERE u.userPumpNozzlePK.userId = :userId"),
    @NamedQuery(name = "UserPumpNozzle.findByPumpId", query = "SELECT u FROM UserPumpNozzle u WHERE u.userPumpNozzlePK.pumpId = :pumpId"),
    @NamedQuery(name = "UserPumpNozzle.findByNozzleId", query = "SELECT u FROM UserPumpNozzle u WHERE u.userPumpNozzlePK.nozzleId = :nozzleId")})
public class UserPumpNozzle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserPumpNozzlePK userPumpNozzlePK;

    public UserPumpNozzle() {
    }

    public UserPumpNozzle(UserPumpNozzlePK userPumpNozzlePK) {
        this.userPumpNozzlePK = userPumpNozzlePK;
    }

    public UserPumpNozzle(int userId, int pumpId, int nozzleId) {
        this.userPumpNozzlePK = new UserPumpNozzlePK(userId, pumpId, nozzleId);
    }

    public UserPumpNozzlePK getUserPumpNozzlePK() {
        return userPumpNozzlePK;
    }

    public void setUserPumpNozzlePK(UserPumpNozzlePK userPumpNozzlePK) {
        this.userPumpNozzlePK = userPumpNozzlePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userPumpNozzlePK != null ? userPumpNozzlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPumpNozzle)) {
            return false;
        }
        UserPumpNozzle other = (UserPumpNozzle) object;
        if ((this.userPumpNozzlePK == null && other.userPumpNozzlePK != null) || (this.userPumpNozzlePK != null && !this.userPumpNozzlePK.equals(other.userPumpNozzlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.UserPumpNozzle[ userPumpNozzlePK=" + userPumpNozzlePK + " ]";
    }
    
}
