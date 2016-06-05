/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "contact", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findByContactId", query = "SELECT c FROM Contact c WHERE c.contactId = :contactId"),
    @NamedQuery(name = "Contact.findByContactName", query = "SELECT c FROM Contact c WHERE c.contactName = :contactName"),
    @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
    @NamedQuery(name = "Contact.findByPhone1", query = "SELECT c FROM Contact c WHERE c.phone1 = :phone1"),
    @NamedQuery(name = "Contact.findByPhone2", query = "SELECT c FROM Contact c WHERE c.phone2 = :phone2"),
    @NamedQuery(name = "Contact.findByPhone3", query = "SELECT c FROM Contact c WHERE c.phone3 = :phone3"),
    @NamedQuery(name = "Contact.findByPosition", query = "SELECT c FROM Contact c WHERE c.position = :position")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_id", nullable = false)
    private Integer contactId;
    @Size(max = 100)
    @Column(name = "contact_name", length = 100)
    private String contactName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;
    @Size(max = 50)
    @Column(name = "phone1", length = 50)
    private String phone1;
    @Size(max = 50)
    @Column(name = "phone2", length = 50)
    private String phone2;
    @Size(max = 50)
    @Column(name = "phone3", length = 50)
    private String phone3;
    @Size(max = 50)
    @Column(name = "position", length = 50)
    private String position;
    @ManyToMany(mappedBy = "contactCollection")
    private Collection<Transporter> transporterCollection;
    @JoinTable(name = "ticketing_sp_contact", joinColumns = {
        @JoinColumn(name = "contact_id", referencedColumnName = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "sp_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<TicketingServiceProvider> ticketingServiceProviderCollection;

    public Contact() {
    }

    public Contact(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @XmlTransient
    public Collection<Transporter> getTransporterCollection() {
        return transporterCollection;
    }

    public void setTransporterCollection(Collection<Transporter> transporterCollection) {
        this.transporterCollection = transporterCollection;
    }

    @XmlTransient
    public Collection<TicketingServiceProvider> getTicketingServiceProviderCollection() {
        return ticketingServiceProviderCollection;
    }

    public void setTicketingServiceProviderCollection(Collection<TicketingServiceProvider> ticketingServiceProviderCollection) {
        this.ticketingServiceProviderCollection = ticketingServiceProviderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contact[ contactId=" + contactId + " ]";
    }
    
}
