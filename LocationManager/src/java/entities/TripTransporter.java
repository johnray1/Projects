/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "trip_transporter", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TripTransporter.findAll", query = "SELECT t FROM TripTransporter t"),
    @NamedQuery(name = "TripTransporter.findByTransporterId", query = "SELECT t FROM TripTransporter t WHERE t.tripTransporterPK.transporterId = :transporterId"),
    @NamedQuery(name = "TripTransporter.findByPrice", query = "SELECT t FROM TripTransporter t WHERE t.price = :price"),
    @NamedQuery(name = "TripTransporter.findByTripdepartLocId", query = "SELECT t FROM TripTransporter t WHERE t.tripTransporterPK.tripdepartLocId = :tripdepartLocId"),
    @NamedQuery(name = "TripTransporter.findByTripdestLocId", query = "SELECT t FROM TripTransporter t WHERE t.tripTransporterPK.tripdestLocId = :tripdestLocId"),
    @NamedQuery(name = "TripTransporter.findByTriprouteId", query = "SELECT t FROM TripTransporter t WHERE t.tripTransporterPK.triprouteId = :triprouteId")})
public class TripTransporter implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TripTransporterPK tripTransporterPK;
    @Column(name = "price")
    private Integer price;
    @JoinColumn(name = "transporter_id", referencedColumnName = "transporter_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transporter transporter;
    @JoinColumns({
        @JoinColumn(name = "tripdepart_loc_id", referencedColumnName = "depart_loc_id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "tripdest_loc_id", referencedColumnName = "dest_loc_id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "triproute_id", referencedColumnName = "route_id", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Trip trip;

    public TripTransporter() {
    }

    public TripTransporter(TripTransporterPK tripTransporterPK) {
        this.tripTransporterPK = tripTransporterPK;
    }

    public TripTransporter(int transporterId, int tripdepartLocId, int tripdestLocId, int triprouteId) {
        this.tripTransporterPK = new TripTransporterPK(transporterId, tripdepartLocId, tripdestLocId, triprouteId);
    }

    public TripTransporterPK getTripTransporterPK() {
        return tripTransporterPK;
    }

    public void setTripTransporterPK(TripTransporterPK tripTransporterPK) {
        this.tripTransporterPK = tripTransporterPK;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripTransporterPK != null ? tripTransporterPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TripTransporter)) {
            return false;
        }
        TripTransporter other = (TripTransporter) object;
        if ((this.tripTransporterPK == null && other.tripTransporterPK != null) || (this.tripTransporterPK != null && !this.tripTransporterPK.equals(other.tripTransporterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TripTransporter[ tripTransporterPK=" + tripTransporterPK + " ]";
    }
    
}
