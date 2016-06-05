/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "balanceHistory", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BalanceHistory.findAll", query = "SELECT b FROM BalanceHistory b"),
    @NamedQuery(name = "BalanceHistory.findByBalance", query = "SELECT b FROM BalanceHistory b WHERE b.balance = :balance"),
    @NamedQuery(name = "BalanceHistory.findByRecordId", query = "SELECT b FROM BalanceHistory b WHERE b.balanceHistoryPK.recordId = :recordId"),
    @NamedQuery(name = "BalanceHistory.findByBusinessDate", query = "SELECT b FROM BalanceHistory b WHERE b.balanceHistoryPK.businessDate = :businessDate")})
public class BalanceHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BalanceHistoryPK balanceHistoryPK;
    @Column(name = "balance")
    private Integer balance;
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    @ManyToOne(optional = false)
    private Account accountId;
    @JoinColumn(name = "record_id", referencedColumnName = "record_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AccountMvmnt accountMvmnt;

    public BalanceHistory() {
    }

    public BalanceHistory(BalanceHistoryPK balanceHistoryPK) {
        this.balanceHistoryPK = balanceHistoryPK;
    }

    public BalanceHistory(double recordId, Date businessDate) {
        this.balanceHistoryPK = new BalanceHistoryPK(recordId, businessDate);
    }

    public BalanceHistoryPK getBalanceHistoryPK() {
        return balanceHistoryPK;
    }

    public void setBalanceHistoryPK(BalanceHistoryPK balanceHistoryPK) {
        this.balanceHistoryPK = balanceHistoryPK;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public AccountMvmnt getAccountMvmnt() {
        return accountMvmnt;
    }

    public void setAccountMvmnt(AccountMvmnt accountMvmnt) {
        this.accountMvmnt = accountMvmnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (balanceHistoryPK != null ? balanceHistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BalanceHistory)) {
            return false;
        }
        BalanceHistory other = (BalanceHistory) object;
        if ((this.balanceHistoryPK == null && other.balanceHistoryPK != null) || (this.balanceHistoryPK != null && !this.balanceHistoryPK.equals(other.balanceHistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BalanceHistory[ balanceHistoryPK=" + balanceHistoryPK + " ]";
    }
    
}
