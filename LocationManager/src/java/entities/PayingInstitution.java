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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "paying_institution", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PayingInstitution.findAll", query = "SELECT p FROM PayingInstitution p"),
    @NamedQuery(name = "PayingInstitution.findByCode", query = "SELECT p FROM PayingInstitution p WHERE p.code = :code"),
    @NamedQuery(name = "PayingInstitution.findByDescr", query = "SELECT p FROM PayingInstitution p WHERE p.descr = :descr")})
public class PayingInstitution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code", nullable = false, length = 50)
    private String code;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payingInstitutionCode")
    private Collection<PaymentRequest> paymentRequestCollection;

    public PayingInstitution() {
    }

    public PayingInstitution(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @XmlTransient
    public Collection<PaymentRequest> getPaymentRequestCollection() {
        return paymentRequestCollection;
    }

    public void setPaymentRequestCollection(Collection<PaymentRequest> paymentRequestCollection) {
        this.paymentRequestCollection = paymentRequestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayingInstitution)) {
            return false;
        }
        PayingInstitution other = (PayingInstitution) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PayingInstitution[ code=" + code + " ]";
    }
    
}
