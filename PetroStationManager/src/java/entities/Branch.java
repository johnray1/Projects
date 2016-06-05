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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John
 */
@Entity
@Table(name = "branch", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b"),
    @NamedQuery(name = "Branch.findByBId", query = "SELECT b FROM Branch b WHERE b.bId = :bId"),
    @NamedQuery(name = "Branch.findByBName", query = "SELECT b FROM Branch b WHERE b.bName = :bName"),
    @NamedQuery(name = "Branch.findByBLongitude", query = "SELECT b FROM Branch b WHERE b.bLongitude = :bLongitude"),
    @NamedQuery(name = "Branch.findByBLatitude", query = "SELECT b FROM Branch b WHERE b.bLatitude = :bLatitude"),
    @NamedQuery(name = "Branch.findByBDescr", query = "SELECT b FROM Branch b WHERE b.bDescr = :bDescr"),
    @NamedQuery(name = "Branch.findByBStatus", query = "SELECT b FROM Branch b WHERE b.bStatus = :bStatus")})
public class Branch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "b_id", nullable = false)
    private Integer bId;
    @Size(max = 50)
    @Column(name = "b_name", length = 50)
    private String bName;
    @Size(max = 255)
    @Column(name = "b_longitude", length = 255)
    private String bLongitude;
    @Size(max = 255)
    @Column(name = "b_latitude", length = 255)
    private String bLatitude;
    @Size(max = 255)
    @Column(name = "b_descr", length = 255)
    private String bDescr;
    @Size(max = 55)
    @Column(name = "b_status", length = 55)
    private String bStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bId")
    private Collection<Product> productCollection;
    @JoinColumn(name = "c_id", referencedColumnName = "c_id", nullable = false)
    @ManyToOne(optional = false)
    private Company cId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bId")
    private Collection<Pump> pumpCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bId")
    private Collection<Device> deviceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bId")
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bId")
    private Collection<Transaction> transactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bId")
    private Collection<Customer> customerCollection;

    public Branch() {
    }

    public Branch(Integer bId) {
        this.bId = bId;
    }

    public Integer getBId() {
        return bId;
    }

    public void setBId(Integer bId) {
        this.bId = bId;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBLongitude() {
        return bLongitude;
    }

    public void setBLongitude(String bLongitude) {
        this.bLongitude = bLongitude;
    }

    public String getBLatitude() {
        return bLatitude;
    }

    public void setBLatitude(String bLatitude) {
        this.bLatitude = bLatitude;
    }

    public String getBDescr() {
        return bDescr;
    }

    public void setBDescr(String bDescr) {
        this.bDescr = bDescr;
    }

    public String getBStatus() {
        return bStatus;
    }

    public void setBStatus(String bStatus) {
        this.bStatus = bStatus;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public Company getCId() {
        return cId;
    }

    public void setCId(Company cId) {
        this.cId = cId;
    }

    @XmlTransient
    public Collection<Pump> getPumpCollection() {
        return pumpCollection;
    }

    public void setPumpCollection(Collection<Pump> pumpCollection) {
        this.pumpCollection = pumpCollection;
    }

    @XmlTransient
    public Collection<Device> getDeviceCollection() {
        return deviceCollection;
    }

    public void setDeviceCollection(Collection<Device> deviceCollection) {
        this.deviceCollection = deviceCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bId != null ? bId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Branch)) {
            return false;
        }
        Branch other = (Branch) object;
        if ((this.bId == null && other.bId != null) || (this.bId != null && !this.bId.equals(other.bId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Branch[ bId=" + bId + " ]";
    }
    
}
