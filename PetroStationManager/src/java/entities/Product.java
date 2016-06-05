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
@Table(name = "product", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProId", query = "SELECT p FROM Product p WHERE p.proId = :proId"),
    @NamedQuery(name = "Product.findByProName", query = "SELECT p FROM Product p WHERE p.proName = :proName"),
    @NamedQuery(name = "Product.findByProUnityPrice", query = "SELECT p FROM Product p WHERE p.proUnityPrice = :proUnityPrice"),
    @NamedQuery(name = "Product.findByProMeasureUnity", query = "SELECT p FROM Product p WHERE p.proMeasureUnity = :proMeasureUnity"),
    @NamedQuery(name = "Product.findByPStatus", query = "SELECT p FROM Product p WHERE p.pStatus = :pStatus")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pro_id", nullable = false)
    private Integer proId;
    @Size(max = 50)
    @Column(name = "pro_name", length = 50)
    private String proName;
    @Size(max = 50)
    @Column(name = "pro_unity_price", length = 50)
    private String proUnityPrice;
    @Size(max = 50)
    @Column(name = "pro_measure_unity", length = 50)
    private String proMeasureUnity;
    @Size(max = 55)
    @Column(name = "p_status", length = 55)
    private String pStatus;
    @JoinColumn(name = "b_id", referencedColumnName = "b_id", nullable = false)
    @ManyToOne(optional = false)
    private Branch bId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proId")
    private Collection<Transaction> transactionCollection;

    public Product() {
    }

    public Product(Integer proId) {
        this.proId = proId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProUnityPrice() {
        return proUnityPrice;
    }

    public void setProUnityPrice(String proUnityPrice) {
        this.proUnityPrice = proUnityPrice;
    }

    public String getProMeasureUnity() {
        return proMeasureUnity;
    }

    public void setProMeasureUnity(String proMeasureUnity) {
        this.proMeasureUnity = proMeasureUnity;
    }

    public String getPStatus() {
        return pStatus;
    }

    public void setPStatus(String pStatus) {
        this.pStatus = pStatus;
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
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Product[ proId=" + proId + " ]";
    }
    
}
