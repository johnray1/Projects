/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "pumpTransaction", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "PumpTransaction.findAll", query = "SELECT p FROM PumpTransaction p"),
    @NamedQuery(name = "PumpTransaction.findByPuTraId", query = "SELECT p FROM PumpTransaction p WHERE p.puTraId = :puTraId"),
    @NamedQuery(name = "PumpTransaction.findByTraId", query = "SELECT p FROM PumpTransaction p WHERE p.traId = :traId"),
    @NamedQuery(name = "PumpTransaction.findByUName", query = "SELECT p FROM PumpTransaction p WHERE p.uName = :uName"),
    @NamedQuery(name = "PumpTransaction.findByProName", query = "SELECT p FROM PumpTransaction p WHERE p.proName = :proName"),
    @NamedQuery(name = "PumpTransaction.findByPuName", query = "SELECT p FROM PumpTransaction p WHERE p.puName = :puName"),
    @NamedQuery(name = "PumpTransaction.findByIndexbefore", query = "SELECT p FROM PumpTransaction p WHERE p.indexbefore = :indexbefore"),
    @NamedQuery(name = "PumpTransaction.findByIndexafter", query = "SELECT p FROM PumpTransaction p WHERE p.indexafter = :indexafter"),
    @NamedQuery(name = "PumpTransaction.findByDatetime", query = "SELECT p FROM PumpTransaction p WHERE p.datetime = :datetime")})
public class PumpTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pu_tra_id", nullable = false)
    private Integer puTraId;
    @Column(name = "tra_id")
    private Integer traId;
    @Size(max = 50)
    @Column(name = "u_name", length = 50)
    private String uName;
    @Size(max = 50)
    @Column(name = "pro_name", length = 50)
    private String proName;
    @Size(max = 255)
    @Column(name = "pu_name", length = 255)
    private String puName;
    @Size(max = 55)
    @Column(name = "indexbefore", length = 55)
    private String indexbefore;
    @Size(max = 55)
    @Column(name = "indexafter", length = 55)
    private String indexafter;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    public PumpTransaction() {
    }

    public PumpTransaction(Integer puTraId) {
        this.puTraId = puTraId;
    }

    public Integer getPuTraId() {
        return puTraId;
    }

    public void setPuTraId(Integer puTraId) {
        this.puTraId = puTraId;
    }

    public Integer getTraId() {
        return traId;
    }

    public void setTraId(Integer traId) {
        this.traId = traId;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

    public String getIndexbefore() {
        return indexbefore;
    }

    public void setIndexbefore(String indexbefore) {
        this.indexbefore = indexbefore;
    }

    public String getIndexafter() {
        return indexafter;
    }

    public void setIndexafter(String indexafter) {
        this.indexafter = indexafter;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (puTraId != null ? puTraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PumpTransaction)) {
            return false;
        }
        PumpTransaction other = (PumpTransaction) object;
        if ((this.puTraId == null && other.puTraId != null) || (this.puTraId != null && !this.puTraId.equals(other.puTraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PumpTransaction[ puTraId=" + puTraId + " ]";
    }
    
}
