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
@Table(name = "pump", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Pump.findAll", query = "SELECT p FROM Pump p"),
    @NamedQuery(name = "Pump.findByPuId", query = "SELECT p FROM Pump p WHERE p.puId = :puId"),
    @NamedQuery(name = "Pump.findByPuName", query = "SELECT p FROM Pump p WHERE p.puName = :puName"),
    @NamedQuery(name = "Pump.findByPuIndex", query = "SELECT p FROM Pump p WHERE p.puIndex = :puIndex")})
public class Pump implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pu_id", nullable = false)
    private Integer puId;
    @Size(max = 255)
    @Column(name = "pu_name", length = 255)
    private String puName;
    @Size(max = 255)
    @Column(name = "pu_index", length = 255)
    private String puIndex;
    @JoinColumn(name = "b_id", referencedColumnName = "b_id", nullable = false)
    @ManyToOne(optional = false)
    private Branch bId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puId")
    private Collection<Transaction> transactionCollection;

    public Pump() {
    }

    public Pump(Integer puId) {
        this.puId = puId;
    }

    public Integer getPuId() {
        return puId;
    }

    public void setPuId(Integer puId) {
        this.puId = puId;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

    public String getPuIndex() {
        return puIndex;
    }

    public void setPuIndex(String puIndex) {
        this.puIndex = puIndex;
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
        hash += (puId != null ? puId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pump)) {
            return false;
        }
        Pump other = (Pump) object;
        if ((this.puId == null && other.puId != null) || (this.puId != null && !this.puId.equals(other.puId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pump[ puId=" + puId + " ]";
    }
    
}
