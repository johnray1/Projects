/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "account", catalog = "TransportManagerDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"account_number"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId"),
    @NamedQuery(name = "Account.findByAccountName", query = "SELECT a FROM Account a WHERE a.accountName = :accountName"),
    @NamedQuery(name = "Account.findByLastUpdateDatetime", query = "SELECT a FROM Account a WHERE a.lastUpdateDatetime = :lastUpdateDatetime"),
    @NamedQuery(name = "Account.findByAccountNumber", query = "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "account_id", nullable = false)
    private Integer accountId;
    @Size(max = 50)
    @Column(name = "account_name", length = 50)
    private String accountName;
    @Column(name = "last_update_datetime")
    @Temporal(TemporalType.DATE)
    private Date lastUpdateDatetime;
    @Column(name = "account_number")
    private Integer accountNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<BalanceHistory> balanceHistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "debitedAccountId")
    private Collection<AccountMvmnt> accountMvmntCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditedAccountId")
    private Collection<AccountMvmnt> accountMvmntCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<Transporter> transporterCollection;
    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id", nullable = false)
    @ManyToOne(optional = false)
    private Accounttype accountTypeId;

    public Account() {
    }

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Date getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(Date lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @XmlTransient
    public Collection<BalanceHistory> getBalanceHistoryCollection() {
        return balanceHistoryCollection;
    }

    public void setBalanceHistoryCollection(Collection<BalanceHistory> balanceHistoryCollection) {
        this.balanceHistoryCollection = balanceHistoryCollection;
    }

    @XmlTransient
    public Collection<AccountMvmnt> getAccountMvmntCollection() {
        return accountMvmntCollection;
    }

    public void setAccountMvmntCollection(Collection<AccountMvmnt> accountMvmntCollection) {
        this.accountMvmntCollection = accountMvmntCollection;
    }

    @XmlTransient
    public Collection<AccountMvmnt> getAccountMvmntCollection1() {
        return accountMvmntCollection1;
    }

    public void setAccountMvmntCollection1(Collection<AccountMvmnt> accountMvmntCollection1) {
        this.accountMvmntCollection1 = accountMvmntCollection1;
    }

    @XmlTransient
    public Collection<Transporter> getTransporterCollection() {
        return transporterCollection;
    }

    public void setTransporterCollection(Collection<Transporter> transporterCollection) {
        this.transporterCollection = transporterCollection;
    }

    public Accounttype getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Accounttype accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Account[ accountId=" + accountId + " ]";
    }
    
}
