/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JOHN
 */
@Embeddable
public class SchedulePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "transporter_id", nullable = false)
    private int transporterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "depart_datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date departDatetime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reintegratorscheduleid", nullable = false)
    private int reintegratorscheduleid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tripdepart_loc_id", nullable = false)
    private int tripdepartLocId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tripdest_loc_id", nullable = false)
    private int tripdestLocId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "triproute_id", nullable = false)
    private int triprouteId;

    public SchedulePK() {
    }

    public SchedulePK(int transporterId, Date departDatetime, int reintegratorscheduleid, int tripdepartLocId, int tripdestLocId, int triprouteId) {
        this.transporterId = transporterId;
        this.departDatetime = departDatetime;
        this.reintegratorscheduleid = reintegratorscheduleid;
        this.tripdepartLocId = tripdepartLocId;
        this.tripdestLocId = tripdestLocId;
        this.triprouteId = triprouteId;
    }

    public int getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    public Date getDepartDatetime() {
        return departDatetime;
    }

    public void setDepartDatetime(Date departDatetime) {
        this.departDatetime = departDatetime;
    }

    public int getReintegratorscheduleid() {
        return reintegratorscheduleid;
    }

    public void setReintegratorscheduleid(int reintegratorscheduleid) {
        this.reintegratorscheduleid = reintegratorscheduleid;
    }

    public int getTripdepartLocId() {
        return tripdepartLocId;
    }

    public void setTripdepartLocId(int tripdepartLocId) {
        this.tripdepartLocId = tripdepartLocId;
    }

    public int getTripdestLocId() {
        return tripdestLocId;
    }

    public void setTripdestLocId(int tripdestLocId) {
        this.tripdestLocId = tripdestLocId;
    }

    public int getTriprouteId() {
        return triprouteId;
    }

    public void setTriprouteId(int triprouteId) {
        this.triprouteId = triprouteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) transporterId;
        hash += (departDatetime != null ? departDatetime.hashCode() : 0);
        hash += (int) reintegratorscheduleid;
        hash += (int) tripdepartLocId;
        hash += (int) tripdestLocId;
        hash += (int) triprouteId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SchedulePK)) {
            return false;
        }
        SchedulePK other = (SchedulePK) object;
        if (this.transporterId != other.transporterId) {
            return false;
        }
        if ((this.departDatetime == null && other.departDatetime != null) || (this.departDatetime != null && !this.departDatetime.equals(other.departDatetime))) {
            return false;
        }
        if (this.reintegratorscheduleid != other.reintegratorscheduleid) {
            return false;
        }
        if (this.tripdepartLocId != other.tripdepartLocId) {
            return false;
        }
        if (this.tripdestLocId != other.tripdestLocId) {
            return false;
        }
        if (this.triprouteId != other.triprouteId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SchedulePK[ transporterId=" + transporterId + ", departDatetime=" + departDatetime + ", reintegratorscheduleid=" + reintegratorscheduleid + ", tripdepartLocId=" + tripdepartLocId + ", tripdestLocId=" + tripdestLocId + ", triprouteId=" + triprouteId + " ]";
    }
    
}
