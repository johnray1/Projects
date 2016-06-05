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
import javax.persistence.UniqueConstraint;
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
@Table(name = "device", catalog = "PetroStationManagerDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"imei_no"})})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d"),
    @NamedQuery(name = "Device.findByDId", query = "SELECT d FROM Device d WHERE d.dId = :dId"),
    @NamedQuery(name = "Device.findByImeiNo", query = "SELECT d FROM Device d WHERE d.imeiNo = :imeiNo"),
    @NamedQuery(name = "Device.findByDStatus", query = "SELECT d FROM Device d WHERE d.dStatus = :dStatus")})
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "d_id", nullable = false)
    private Integer dId;
    @Size(max = 100)
    @Column(name = "imei_no", length = 100)
    private String imeiNo;
    @Size(max = 55)
    @Column(name = "d_status", length = 55)
    private String dStatus;
    @JoinColumn(name = "b_id", referencedColumnName = "b_id", nullable = false)
    @ManyToOne(optional = false)
    private Branch bId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dId")
    private Collection<Transaction> transactionCollection;

    public Device() {
    }

    public Device(Integer dId) {
        this.dId = dId;
    }

    public Integer getDId() {
        return dId;
    }

    public void setDId(Integer dId) {
        this.dId = dId;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getDStatus() {
        return dStatus;
    }

    public void setDStatus(String dStatus) {
        this.dStatus = dStatus;
    }

    public Branch getBId() {
        return bId;
    }

    public void setBId(Branch bId) {
        this.bId = bId;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dId != null ? dId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.dId == null && other.dId != null) || (this.dId != null && !this.dId.equals(other.dId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Device[ dId=" + dId + " ]";
    }
    
}
