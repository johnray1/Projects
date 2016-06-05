/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "trip", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findByDepartLocId", query = "SELECT t FROM Trip t WHERE t.tripPK.departLocId = :departLocId"),
    @NamedQuery(name = "Trip.findByDestLocId", query = "SELECT t FROM Trip t WHERE t.tripPK.destLocId = :destLocId"),
    @NamedQuery(name = "Trip.findByRouteId", query = "SELECT t FROM Trip t WHERE t.tripPK.routeId = :routeId"),
    @NamedQuery(name = "Trip.findByDescription", query = "SELECT t FROM Trip t WHERE t.description = :description")})
public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TripPK tripPK;
    @Size(max = 60)
    @Column(name = "description", length = 60)
    private String description;
    @OneToMany(mappedBy = "trip")
    private Collection<Ticket> ticketCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private Collection<Schedule> scheduleCollection;
    @JoinColumn(name = "depart_loc_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Location location;
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Route route;
    @JoinColumn(name = "dest_loc_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Location location1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private Collection<TripTransporter> tripTransporterCollection;

    public Trip() {
    }

    public Trip(TripPK tripPK) {
        this.tripPK = tripPK;
    }

    public Trip(int departLocId, int destLocId, int routeId) {
        this.tripPK = new TripPK(departLocId, destLocId, routeId);
    }

    public TripPK getTripPK() {
        return tripPK;
    }

    public void setTripPK(TripPK tripPK) {
        this.tripPK = tripPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Location getLocation1() {
        return location1;
    }

    public void setLocation1(Location location1) {
        this.location1 = location1;
    }

    @XmlTransient
    public Collection<TripTransporter> getTripTransporterCollection() {
        return tripTransporterCollection;
    }

    public void setTripTransporterCollection(Collection<TripTransporter> tripTransporterCollection) {
        this.tripTransporterCollection = tripTransporterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripPK != null ? tripPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.tripPK == null && other.tripPK != null) || (this.tripPK != null && !this.tripPK.equals(other.tripPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trip[ tripPK=" + tripPK + " ]";
    }
    
}
