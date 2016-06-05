/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "account_mvmnt", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountMvmnt.findAll", query = "SELECT a FROM AccountMvmnt a"),
    @NamedQuery(name = "AccountMvmnt.findByRecordId", query = "SELECT a FROM AccountMvmnt a WHERE a.recordId = :recordId"),
    @NamedQuery(name = "AccountMvmnt.findByMvmntTransctionDetails", query = "SELECT a FROM AccountMvmnt a WHERE a.mvmntTransctionDetails = :mvmntTransctionDetails"),
    @NamedQuery(name = "AccountMvmnt.findByAmountNumeric", query = "SELECT a FROM AccountMvmnt a WHERE a.amountNumeric = :amountNumeric"),
    @NamedQuery(name = "AccountMvmnt.findByMvmntDatetime", query = "SELECT a FROM AccountMvmnt a WHERE a.mvmntDatetime = :mvmntDatetime")})
public class AccountMvmnt implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "record_id", nullable = false, precision = 22)
    private Double recordId;
    @Size(max = 255)
    @Column(name = "mvmnt_transction_details", length = 255)
    private String mvmntTransctionDetails;
    @Column(name = "amount numeric", precision = 19, scale = 2)
    private BigDecimal amountNumeric;
    @Column(name = "mvmnt_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mvmntDatetime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountMvmnt")
    private Collection<BalanceHistory> balanceHistoryCollection;
    @JoinColumn(name = "debited_account_id", referencedColumnName = "account_id", nullable = false)
    @ManyToOne(optional = false)
    private Account debitedAccountId;
    @JoinColumn(name = "mvmnt_reason_code", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false)
    private AccountMvmntReason mvmntReasonCode;
    @JoinColumn(name = "credited_account_id", referencedColumnName = "account_id", nullable = false)
    @ManyToOne(optional = false)
    private Account creditedAccountId;

    public AccountMvmnt() {
    }

    public AccountMvmnt(Double recordId) {
        this.recordId = recordId;
    }

    public Double getRecordId() {
        return recordId;
    }

    public void setRecordId(Double recordId) {
        this.recordId = recordId;
    }

    public String getMvmntTransctionDetails() {
        return mvmntTransctionDetails;
    }

    public void setMvmntTransctionDetails(String mvmntTransctionDetails) {
        this.mvmntTransctionDetails = mvmntTransctionDetails;
    }

    public BigDecimal getAmountNumeric() {
        return amountNumeric;
    }

    public void setAmountNumeric(BigDecimal amountNumeric) {
        this.amountNumeric = amountNumeric;
    }

    public Date getMvmntDatetime() {
        return mvmntDatetime;
    }

    public void setMvmntDatetime(Date mvmntDatetime) {
        this.mvmntDatetime = mvmntDatetime;
    }

    @XmlTransient
    public Collection<BalanceHistory> getBalanceHistoryCollection() {
        return balanceHistoryCollection;
    }

    public void setBalanceHistoryCollection(Collection<BalanceHistory> balanceHistoryCollection) {
        this.balanceHistoryCollection = balanceHistoryCollection;
    }

    public Account getDebitedAccountId() {
        return debitedAccountId;
    }

    public void setDebitedAccountId(Account debitedAccountId) {
        this.debitedAccountId = debitedAccountId;
    }

    public AccountMvmntReason getMvmntReasonCode() {
        return mvmntReasonCode;
    }

    public void setMvmntReasonCode(AccountMvmntReason mvmntReasonCode) {
        this.mvmntReasonCode = mvmntReasonCode;
    }

    public Account getCreditedAccountId() {
        return creditedAccountId;
    }

    public void setCreditedAccountId(Account creditedAccountId) {
        this.creditedAccountId = creditedAccountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountMvmnt)) {
            return false;
        }
        AccountMvmnt other = (AccountMvmnt) object;
        if ((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AccountMvmnt[ recordId=" + recordId + " ]";
    }
    
}
