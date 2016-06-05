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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JOHN
 */
@Embeddable
public class BalanceHistoryPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "record_id", nullable = false)
    private double recordId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "businessDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date businessDate;

    public BalanceHistoryPK() {
    }

    public BalanceHistoryPK(double recordId, Date businessDate) {
        this.recordId = recordId;
        this.businessDate = businessDate;
    }

    public double getRecordId() {
        return recordId;
    }

    public void setRecordId(double recordId) {
        this.recordId = recordId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) recordId;
        hash += (businessDate != null ? businessDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BalanceHistoryPK)) {
            return false;
        }
        BalanceHistoryPK other = (BalanceHistoryPK) object;
        if (this.recordId != other.recordId) {
            return false;
        }
        if ((this.businessDate == null && other.businessDate != null) || (this.businessDate != null && !this.businessDate.equals(other.businessDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BalanceHistoryPK[ recordId=" + recordId + ", businessDate=" + businessDate + " ]";
    }
    
}
