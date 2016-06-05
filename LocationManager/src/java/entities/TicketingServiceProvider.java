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
import javax.persistence.ManyToMany;
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
@Table(name = "ticketing_service_provider", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketingServiceProvider.findAll", query = "SELECT t FROM TicketingServiceProvider t"),
    @NamedQuery(name = "TicketingServiceProvider.findById", query = "SELECT t FROM TicketingServiceProvider t WHERE t.id = :id"),
    @NamedQuery(name = "TicketingServiceProvider.findByName", query = "SELECT t FROM TicketingServiceProvider t WHERE t.name = :name"),
    @NamedQuery(name = "TicketingServiceProvider.findByDetails", query = "SELECT t FROM TicketingServiceProvider t WHERE t.details = :details"),
    @NamedQuery(name = "TicketingServiceProvider.findByApiBaseUrl", query = "SELECT t FROM TicketingServiceProvider t WHERE t.apiBaseUrl = :apiBaseUrl")})
public class TicketingServiceProvider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;
    @Size(max = 255)
    @Column(name = "details", length = 255)
    private String details;
    @Size(max = 255)
    @Column(name = "api_base_url", length = 255)
    private String apiBaseUrl;
    @ManyToMany(mappedBy = "ticketingServiceProviderCollection")
    private Collection<Contact> contactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketingServiceProviderId")
    private Collection<Transporter> transporterCollection;

    public TicketingServiceProvider() {
    }

    public TicketingServiceProvider(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }

    public void setApiBaseUrl(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
    }

    @XmlTransient
    public Collection<Transporter> getTransporterCollection() {
        return transporterCollection;
    }

    public void setTransporterCollection(Collection<Transporter> transporterCollection) {
        this.transporterCollection = transporterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketingServiceProvider)) {
            return false;
        }
        TicketingServiceProvider other = (TicketingServiceProvider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TicketingServiceProvider[ id=" + id + " ]";
    }
    
}
