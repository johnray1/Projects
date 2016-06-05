/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "location_aliase", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationAliase.findAll", query = "SELECT l FROM LocationAliase l"),
    @NamedQuery(name = "LocationAliase.findByLocAliasId", query = "SELECT l FROM LocationAliase l WHERE l.locAliasId = :locAliasId"),
    @NamedQuery(name = "LocationAliase.findByLocAliasName", query = "SELECT l FROM LocationAliase l WHERE l.locAliasName = :locAliasName")})
public class LocationAliase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loc_alias_id", nullable = false)
    private Integer locAliasId;
    @Size(max = 50)
    @Column(name = "loc_alias_name", length = 50)
    private String locAliasName;
    @JoinColumn(name = "loc_id", referencedColumnName = "id")
    @ManyToOne
    private Location locId;

    public LocationAliase() {
    }

    public LocationAliase(Integer locAliasId) {
        this.locAliasId = locAliasId;
    }

    public Integer getLocAliasId() {
        return locAliasId;
    }

    public void setLocAliasId(Integer locAliasId) {
        this.locAliasId = locAliasId;
    }

    public String getLocAliasName() {
        return locAliasName;
    }

    public void setLocAliasName(String locAliasName) {
        this.locAliasName = locAliasName;
    }

    public Location getLocId() {
        return locId;
    }

    public void setLocId(Location locId) {
        this.locId = locId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locAliasId != null ? locAliasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationAliase)) {
            return false;
        }
        LocationAliase other = (LocationAliase) object;
        if ((this.locAliasId == null && other.locAliasId != null) || (this.locAliasId != null && !this.locAliasId.equals(other.locAliasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LocationAliase[ locAliasId=" + locAliasId + " ]";
    }
    
}
