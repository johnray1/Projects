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
@Table(name = "ticket_status", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketStatus.findAll", query = "SELECT t FROM TicketStatus t"),
    @NamedQuery(name = "TicketStatus.findByStatusCode", query = "SELECT t FROM TicketStatus t WHERE t.statusCode = :statusCode"),
    @NamedQuery(name = "TicketStatus.findByStatusDescr", query = "SELECT t FROM TicketStatus t WHERE t.statusDescr = :statusDescr")})
public class TicketStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_code", nullable = false)
    private Integer statusCode;
    @Size(max = 255)
    @Column(name = "status_descr", length = 255)
    private String statusDescr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketStatusCode")
    private Collection<Ticket> ticketCollection;

    public TicketStatus() {
    }

    public TicketStatus(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusCode != null ? statusCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketStatus)) {
            return false;
        }
        TicketStatus other = (TicketStatus) object;
        if ((this.statusCode == null && other.statusCode != null) || (this.statusCode != null && !this.statusCode.equals(other.statusCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TicketStatus[ statusCode=" + statusCode + " ]";
    }
    
}
