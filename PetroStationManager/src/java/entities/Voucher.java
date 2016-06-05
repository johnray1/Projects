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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "voucher", catalog = "PetroStationManagerDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"v_no"})})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByVId", query = "SELECT v FROM Voucher v WHERE v.vId = :vId"),
    @NamedQuery(name = "Voucher.findByVNo", query = "SELECT v FROM Voucher v WHERE v.vNo = :vNo"),
    @NamedQuery(name = "Voucher.findByVFormat", query = "SELECT v FROM Voucher v WHERE v.vFormat = :vFormat"),
    @NamedQuery(name = "Voucher.findByVAmount", query = "SELECT v FROM Voucher v WHERE v.vAmount = :vAmount")})
public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "v_id", nullable = false)
    private Integer vId;
    @Size(max = 255)
    @Column(name = "v_no", length = 255)
    private String vNo;
    @Size(max = 255)
    @Column(name = "v_format", length = 255)
    private String vFormat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "v_amount", precision = 22)
    private Double vAmount;

    public Voucher() {
    }

    public Voucher(Integer vId) {
        this.vId = vId;
    }

    public Integer getVId() {
        return vId;
    }

    public void setVId(Integer vId) {
        this.vId = vId;
    }

    public String getVNo() {
        return vNo;
    }

    public void setVNo(String vNo) {
        this.vNo = vNo;
    }

    public String getVFormat() {
        return vFormat;
    }

    public void setVFormat(String vFormat) {
        this.vFormat = vFormat;
    }

    public Double getVAmount() {
        return vAmount;
    }

    public void setVAmount(Double vAmount) {
        this.vAmount = vAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vId != null ? vId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.vId == null && other.vId != null) || (this.vId != null && !this.vId.equals(other.vId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Voucher[ vId=" + vId + " ]";
    }
    
}
