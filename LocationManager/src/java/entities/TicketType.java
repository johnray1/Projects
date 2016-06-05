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
@Table(name = "ticket_type", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketType.findAll", query = "SELECT t FROM TicketType t"),
    @NamedQuery(name = "TicketType.findByTypeId", query = "SELECT t FROM TicketType t WHERE t.typeId = :typeId"),
    @NamedQuery(name = "TicketType.findByTypeName", query = "SELECT t FROM TicketType t WHERE t.typeName = :typeName"),
    @NamedQuery(name = "TicketType.findByDescr", query = "SELECT t FROM TicketType t WHERE t.descr = :descr")})
public class TicketType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "type_id", nullable = false)
    private Integer typeId;
    @Size(max = 50)
    @Column(name = "type_name", length = 50)
    private String typeName;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketTypeId")
    private Collection<Ticket> ticketCollection;

    public TicketType() {
    }

    public TicketType(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketType)) {
            return false;
        }
        TicketType other = (TicketType) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TicketType[ typeId=" + typeId + " ]";
    }
    
}
