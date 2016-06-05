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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "account_mvmnt_reason", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountMvmntReason.findAll", query = "SELECT a FROM AccountMvmntReason a"),
    @NamedQuery(name = "AccountMvmntReason.findByCode", query = "SELECT a FROM AccountMvmntReason a WHERE a.code = :code"),
    @NamedQuery(name = "AccountMvmntReason.findByDescr", query = "SELECT a FROM AccountMvmntReason a WHERE a.descr = :descr")})
public class AccountMvmntReason implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code", nullable = false)
    private Integer code;
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mvmntReasonCode")
    private Collection<AccountMvmnt> accountMvmntCollection;

    public AccountMvmntReason() {
    }

    public AccountMvmntReason(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @XmlTransient
    public Collection<AccountMvmnt> getAccountMvmntCollection() {
        return accountMvmntCollection;
    }

    public void setAccountMvmntCollection(Collection<AccountMvmnt> accountMvmntCollection) {
        this.accountMvmntCollection = accountMvmntCollection;
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
        if (!(object instanceof AccountMvmntReason)) {
            return false;
        }
        AccountMvmntReason other = (AccountMvmntReason) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AccountMvmntReason[ code=" + code + " ]";
    }
    
}
