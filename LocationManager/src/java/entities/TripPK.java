/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JOHN
 */
@Embeddable
public class TripPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "depart_loc_id", nullable = false)
    private int departLocId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dest_loc_id", nullable = false)
    private int destLocId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "route_id", nullable = false)
    private int routeId;

    public TripPK() {
    }

    public TripPK(int departLocId, int destLocId, int routeId) {
        this.departLocId = departLocId;
        this.destLocId = destLocId;
        this.routeId = routeId;
    }

    public int getDepartLocId() {
        return departLocId;
    }

    public void setDepartLocId(int departLocId) {
        this.departLocId = departLocId;
    }

    public int getDestLocId() {
        return destLocId;
    }

    public void setDestLocId(int destLocId) {
        this.destLocId = destLocId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) departLocId;
        hash += (int) destLocId;
        hash += (int) routeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TripPK)) {
            return false;
        }
        TripPK other = (TripPK) object;
        if (this.departLocId != other.departLocId) {
            return false;
        }
        if (this.destLocId != other.destLocId) {
            return false;
        }
        if (this.routeId != other.routeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TripPK[ departLocId=" + departLocId + ", destLocId=" + destLocId + ", routeId=" + routeId + " ]";
    }
    
}
