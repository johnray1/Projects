/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "transporter", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporter.findAll", query = "SELECT t FROM Transporter t"),
    @NamedQuery(name = "Transporter.findByTransporterId", query = "SELECT t FROM Transporter t WHERE t.transporterId = :transporterId"),
    @NamedQuery(name = "Transporter.findByTransporterName", query = "SELECT t FROM Transporter t WHERE t.transporterName = :transporterName"),
    @NamedQuery(name = "Transporter.findByTransporterDetails", query = "SELECT t FROM Transporter t WHERE t.transporterDetails = :transporterDetails"),
    @NamedQuery(name = "Transporter.findByRouteid", query = "SELECT t FROM Transporter t WHERE t.routeid = :routeid")})
public class Transporter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transporter_id", nullable = false)
    private Integer transporterId;
    @Size(max = 50)
    @Column(name = "transporter_name", length = 50)
    private String transporterName;
    @Size(max = 255)
    @Column(name = "transporter_details", length = 255)
    private String transporterDetails;
    @Basic(optional = false)
    @NotNull
    @Column(name = "routeid", nullable = false)
    private int routeid;
    @JoinTable(name = "transporter_contact", joinColumns = {
        @JoinColumn(name = "transporter_id", referencedColumnName = "transporter_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "contact_id", referencedColumnName = "contact_id", nullable = false)})
    @ManyToMany
    private Collection<Contact> contactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transporter")
    private Collection<Ticket> ticketCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transporter")
    private Collection<Schedule> scheduleCollection;
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    @ManyToOne(optional = false)
    private Account accountId;
    @JoinColumn(name = "ticketing_service_provider_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TicketingServiceProvider ticketingServiceProviderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transporter")
    private Collection<TripTransporter> tripTransporterCollection;

    public Transporter() {
    }

    public Transporter(Integer transporterId) {
        this.transporterId = transporterId;
    }

    public Transporter(Integer transporterId, int routeid) {
        this.transporterId = transporterId;
        this.routeid = routeid;
    }

    public Integer getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public String getTransporterDetails() {
        return transporterDetails;
    }

    public void setTransporterDetails(String transporterDetails) {
        this.transporterDetails = transporterDetails;
    }

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
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

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public TicketingServiceProvider getTicketingServiceProviderId() {
        return ticketingServiceProviderId;
    }

    public void setTicketingServiceProviderId(TicketingServiceProvider ticketingServiceProviderId) {
        this.ticketingServiceProviderId = ticketingServiceProviderId;
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
        hash += (transporterId != null ? transporterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporter)) {
            return false;
        }
        Transporter other = (Transporter) object;
        if ((this.transporterId == null && other.transporterId != null) || (this.transporterId != null && !this.transporterId.equals(other.transporterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transporter[ transporterId=" + transporterId + " ]";
    }
    
}
