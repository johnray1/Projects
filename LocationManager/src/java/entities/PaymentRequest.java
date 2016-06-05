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
@Table(name = "payment_request", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentRequest.findAll", query = "SELECT p FROM PaymentRequest p"),
    @NamedQuery(name = "PaymentRequest.findByRequestId", query = "SELECT p FROM PaymentRequest p WHERE p.requestId = :requestId"),
    @NamedQuery(name = "PaymentRequest.findByRequestDatetime", query = "SELECT p FROM PaymentRequest p WHERE p.requestDatetime = :requestDatetime"),
    @NamedQuery(name = "PaymentRequest.findByResponseDatetime", query = "SELECT p FROM PaymentRequest p WHERE p.responseDatetime = :responseDatetime"),
    @NamedQuery(name = "PaymentRequest.findBySpTransactionId", query = "SELECT p FROM PaymentRequest p WHERE p.spTransactionId = :spTransactionId"),
    @NamedQuery(name = "PaymentRequest.findByAmount", query = "SELECT p FROM PaymentRequest p WHERE p.amount = :amount"),
    @NamedQuery(name = "PaymentRequest.findByRequestStatusCode", query = "SELECT p FROM PaymentRequest p WHERE p.requestStatusCode = :requestStatusCode"),
    @NamedQuery(name = "PaymentRequest.findBySpTransactionStatusCode", query = "SELECT p FROM PaymentRequest p WHERE p.spTransactionStatusCode = :spTransactionStatusCode"),
    @NamedQuery(name = "PaymentRequest.findByPayingAccountCode", query = "SELECT p FROM PaymentRequest p WHERE p.payingAccountCode = :payingAccountCode"),
    @NamedQuery(name = "PaymentRequest.findByRequestDescr", query = "SELECT p FROM PaymentRequest p WHERE p.requestDescr = :requestDescr")})
public class PaymentRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "request_id", nullable = false, length = 50)
    private String requestId;
    @Column(name = "request_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDatetime;
    @Column(name = "response_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDatetime;
    @Size(max = 50)
    @Column(name = "sp_transaction_id", length = 50)
    private String spTransactionId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 22)
    private Double amount;
    @Size(max = 10)
    @Column(name = "request_status_code", length = 10)
    private String requestStatusCode;
    @Size(max = 10)
    @Column(name = "sp_transaction_status_code", length = 10)
    private String spTransactionStatusCode;
    @Size(max = 50)
    @Column(name = "paying_account_code", length = 50)
    private String payingAccountCode;
    @Size(max = 200)
    @Column(name = "request_descr", length = 200)
    private String requestDescr;
    @OneToMany(mappedBy = "paymentRequestId")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "paying_institution_code", referencedColumnName = "code", nullable = false)
    @ManyToOne(optional = false)
    private PayingInstitution payingInstitutionCode;

    public PaymentRequest() {
    }

    public PaymentRequest(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(Date requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public Date getResponseDatetime() {
        return responseDatetime;
    }

    public void setResponseDatetime(Date responseDatetime) {
        this.responseDatetime = responseDatetime;
    }

    public String getSpTransactionId() {
        return spTransactionId;
    }

    public void setSpTransactionId(String spTransactionId) {
        this.spTransactionId = spTransactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRequestStatusCode() {
        return requestStatusCode;
    }

    public void setRequestStatusCode(String requestStatusCode) {
        this.requestStatusCode = requestStatusCode;
    }

    public String getSpTransactionStatusCode() {
        return spTransactionStatusCode;
    }

    public void setSpTransactionStatusCode(String spTransactionStatusCode) {
        this.spTransactionStatusCode = spTransactionStatusCode;
    }

    public String getPayingAccountCode() {
        return payingAccountCode;
    }

    public void setPayingAccountCode(String payingAccountCode) {
        this.payingAccountCode = payingAccountCode;
    }

    public String getRequestDescr() {
        return requestDescr;
    }

    public void setRequestDescr(String requestDescr) {
        this.requestDescr = requestDescr;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public PayingInstitution getPayingInstitutionCode() {
        return payingInstitutionCode;
    }

    public void setPayingInstitutionCode(PayingInstitution payingInstitutionCode) {
        this.payingInstitutionCode = payingInstitutionCode;
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
        if (!(object instanceof PaymentRequest)) {
            return false;
        }
        PaymentRequest other = (PaymentRequest) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PaymentRequest[ requestId=" + requestId + " ]";
    }
    
}
