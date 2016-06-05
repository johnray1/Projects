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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "location", catalog = "TransportManagerDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findById", query = "SELECT l FROM Location l WHERE l.id = :id"),
    @NamedQuery(name = "Location.findByName", query = "SELECT l FROM Location l WHERE l.name = :name"),
    @NamedQuery(name = "Location.findByDescr", query = "SELECT l FROM Location l WHERE l.descr = :descr")})
public class Location implements Serializable {
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
    @Column(name = "descr", length = 255)
    private String descr;
    @OneToMany(mappedBy = "locId")
    private Collection<LocationAliase> locationAliaseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private Collection<Trip> tripCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location1")
    private Collection<Trip> tripCollection1;

    public Location() {
    }

    public Location(Integer id) {
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @XmlTransient
    public Collection<LocationAliase> getLocationAliaseCollection() {
        return locationAliaseCollection;
    }

    public void setLocationAliaseCollection(Collection<LocationAliase> locationAliaseCollection) {
        this.locationAliaseCollection = locationAliaseCollection;
    }

    @XmlTransient
    public Collection<Trip> getTripCollection() {
        return tripCollection;
    }

    public void setTripCollection(Collection<Trip> tripCollection) {
        this.tripCollection = tripCollection;
    }

    @XmlTransient
    public Collection<Trip> getTripCollection1() {
        return tripCollection1;
    }

    public void setTripCollection1(Collection<Trip> tripCollection1) {
        this.tripCollection1 = tripCollection1;
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
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Location[ id=" + id + " ]";
    }
    
}
