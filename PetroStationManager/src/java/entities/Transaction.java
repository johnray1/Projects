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
@Table(name = "transaction", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTraId", query = "SELECT t FROM Transaction t WHERE t.traId = :traId"),
    @NamedQuery(name = "Transaction.findByQuantity", query = "SELECT t FROM Transaction t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "Transaction.findByAmount", query = "SELECT t FROM Transaction t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transaction.findByPaymentMode", query = "SELECT t FROM Transaction t WHERE t.paymentMode = :paymentMode"),
    @NamedQuery(name = "Transaction.findByPaymentStatus", query = "SELECT t FROM Transaction t WHERE t.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Transaction.findByReqDatetime", query = "SELECT t FROM Transaction t WHERE t.reqDatetime = :reqDatetime"),
    @NamedQuery(name = "Transaction.findByResDatetime", query = "SELECT t FROM Transaction t WHERE t.resDatetime = :resDatetime")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tra_id", nullable = false)
    private Integer traId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity", precision = 22)
    private Double quantity;
    @Column(name = "amount", precision = 22)
    private Double amount;
    @Size(max = 50)
    @Column(name = "payment_mode", length = 50)
    private String paymentMode;
    @Size(max = 50)
    @Column(name = "payment_status", length = 50)
    private String paymentStatus;
    @Column(name = "req_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reqDatetime;
    @Column(name = "res_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resDatetime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<SpPaymentRes> spPaymentResCollection;
    @JoinColumn(name = "d_id", referencedColumnName = "d_id", nullable = false)
    @ManyToOne(optional = false)
    private Device dId;
    @JoinColumn(name = "pu_id", referencedColumnName = "pu_id", nullable = false)
    @ManyToOne(optional = false)
    private Pump puId;
    @JoinColumn(name = "b_id", referencedColumnName = "b_id", nullable = false)
    @ManyToOne(optional = false)
    private Branch bId;
    @JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false)
    @ManyToOne(optional = false)
    private Customer custId;
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id", nullable = false)
    @ManyToOne(optional = false)
    private Product proId;
    @JoinColumn(name = "u_id", referencedColumnName = "u_id", nullable = false)
    @ManyToOne(optional = false)
    private User uId;

    public Transaction() {
    }

    public Transaction(Integer traId) {
        this.traId = traId;
    }

    public Integer getTraId() {
        return traId;
    }

    public void setTraId(Integer traId) {
        this.traId = traId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getReqDatetime() {
        return reqDatetime;
    }

    public void setReqDatetime(Date reqDatetime) {
        this.reqDatetime = reqDatetime;
    }

    public Date getResDatetime() {
        return resDatetime;
    }

    public void setResDatetime(Date resDatetime) {
        this.resDatetime = resDatetime;
    }

    @XmlTransient
    public Collection<SpPaymentRes> getSpPaymentResCollection() {
        return spPaymentResCollection;
    }

    public void setSpPaymentResCollection(Collection<SpPaymentRes> spPaymentResCollection) {
        this.spPaymentResCollection = spPaymentResCollection;
    }

    public Device getDId() {
        return dId;
    }

    public void setDId(Device dId) {
        this.dId = dId;
    }

    public Pump getPuId() {
        return puId;
    }

    public void setPuId(Pump puId) {
        this.puId = puId;
    }

    public Branch getBId() {
        return bId;
    }

    public void setBId(Branch bId) {
        this.bId = bId;
    }

    public Customer getCustId() {
        return custId;
    }

    public void setCustId(Customer custId) {
        this.custId = custId;
    }

    public Product getProId() {
        return proId;
    }

    public void setProId(Product proId) {
        this.proId = proId;
    }

    public User getUId() {
        return uId;
    }

    public void setUId(User uId) {
        this.uId = uId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traId != null ? traId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.traId == null && other.traId != null) || (this.traId != null && !this.traId.equals(other.traId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Transaction[ traId=" + traId + " ]";
    }
    
}
