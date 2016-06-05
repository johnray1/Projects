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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "SpPaymentRes", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "SpPaymentRes.findAll", query = "SELECT s FROM SpPaymentRes s"),
    @NamedQuery(name = "SpPaymentRes.findByRequestId", query = "SELECT s FROM SpPaymentRes s WHERE s.requestId = :requestId"),
    @NamedQuery(name = "SpPaymentRes.findByContractId", query = "SELECT s FROM SpPaymentRes s WHERE s.contractId = :contractId"),
    @NamedQuery(name = "SpPaymentRes.findBySpTransactionId", query = "SELECT s FROM SpPaymentRes s WHERE s.spTransactionId = :spTransactionId"),
    @NamedQuery(name = "SpPaymentRes.findByStatusCode", query = "SELECT s FROM SpPaymentRes s WHERE s.statusCode = :statusCode"),
    @NamedQuery(name = "SpPaymentRes.findByStatusDesc", query = "SELECT s FROM SpPaymentRes s WHERE s.statusDesc = :statusDesc")})
public class SpPaymentRes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "request_id", nullable = false)
    private Integer requestId;
    @Size(max = 10)
    @Column(name = "contract_id", length = 10)
    private String contractId;
    @Size(max = 255)
    @Column(name = "sp_transaction_id", length = 255)
    private String spTransactionId;
    @Size(max = 10)
    @Column(name = "status_code", length = 10)
    private String statusCode;
    @Size(max = 255)
    @Column(name = "status_desc", length = 255)
    private String statusDesc;
    @JoinColumn(name = "transaction_id", referencedColumnName = "tra_id", nullable = false)
    @ManyToOne(optional = false)
    private Transaction transactionId;

    public SpPaymentRes() {
    }

    public SpPaymentRes(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getSpTransactionId() {
        return spTransactionId;
    }

    public void setSpTransactionId(String spTransactionId) {
        this.spTransactionId = spTransactionId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpPaymentRes)) {
            return false;
        }
        SpPaymentRes other = (SpPaymentRes) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SpPaymentRes[ requestId=" + requestId + " ]";
    }
    
}
