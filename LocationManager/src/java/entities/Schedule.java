/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "schedule", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByTransporterId", query = "SELECT s FROM Schedule s WHERE s.schedulePK.transporterId = :transporterId"),
    @NamedQuery(name = "Schedule.findByDepartDatetime", query = "SELECT s FROM Schedule s WHERE s.schedulePK.departDatetime = :departDatetime"),
    @NamedQuery(name = "Schedule.findByDescription", query = "SELECT s FROM Schedule s WHERE s.description = :description"),
    @NamedQuery(name = "Schedule.findByReintegratorscheduleid", query = "SELECT s FROM Schedule s WHERE s.schedulePK.reintegratorscheduleid = :reintegratorscheduleid"),
    @NamedQuery(name = "Schedule.findByTripdepartLocId", query = "SELECT s FROM Schedule s WHERE s.schedulePK.tripdepartLocId = :tripdepartLocId"),
    @NamedQuery(name = "Schedule.findByTripdestLocId", query = "SELECT s FROM Schedule s WHERE s.schedulePK.tripdestLocId = :tripdestLocId"),
    @NamedQuery(name = "Schedule.findByTriprouteId", query = "SELECT s FROM Schedule s WHERE s.schedulePK.triprouteId = :triprouteId")})
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SchedulePK schedulePK;
    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;
    @JoinColumn(name = "transporter_id", referencedColumnName = "transporter_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transporter transporter;
    @JoinColumns({
        @JoinColumn(name = "tripdepart_loc_id", referencedColumnName = "depart_loc_id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "tripdest_loc_id", referencedColumnName = "dest_loc_id", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "triproute_id", referencedColumnName = "route_id", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Trip trip;

    public Schedule() {
    }

    public Schedule(SchedulePK schedulePK) {
        this.schedulePK = schedulePK;
    }

    public Schedule(int transporterId, Date departDatetime, int reintegratorscheduleid, int tripdepartLocId, int tripdestLocId, int triprouteId) {
        this.schedulePK = new SchedulePK(transporterId, departDatetime, reintegratorscheduleid, tripdepartLocId, tripdestLocId, triprouteId);
    }

    public SchedulePK getSchedulePK() {
        return schedulePK;
    }

    public void setSchedulePK(SchedulePK schedulePK) {
        this.schedulePK = schedulePK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (schedulePK != null ? schedulePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.schedulePK == null && other.schedulePK != null) || (this.schedulePK != null && !this.schedulePK.equals(other.schedulePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Schedule[ schedulePK=" + schedulePK + " ]";
    }
    
}
