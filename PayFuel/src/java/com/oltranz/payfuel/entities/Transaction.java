/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "transaction", catalog = "PayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"device_transaction_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTransactionId", query = "SELECT t FROM Transaction t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transaction.findByDeviceTransactionId", query = "SELECT t FROM Transaction t WHERE t.deviceTransactionId = :deviceTransactionId"),
    @NamedQuery(name = "Transaction.findByDeviceTransactionTime", query = "SELECT t FROM Transaction t WHERE t.deviceTransactionTime = :deviceTransactionTime"),
    @NamedQuery(name = "Transaction.findByBranchId", query = "SELECT t FROM Transaction t WHERE t.branchId = :branchId"),
    @NamedQuery(name = "Transaction.findByUserId", query = "SELECT t FROM Transaction t WHERE t.userId = :userId"),
    @NamedQuery(name = "Transaction.findByDeviceId", query = "SELECT t FROM Transaction t WHERE t.deviceId = :deviceId"),
    @NamedQuery(name = "Transaction.findByPumpId", query = "SELECT t FROM Transaction t WHERE t.pumpId = :pumpId"),
    @NamedQuery(name = "Transaction.findByNozzleId", query = "SELECT t FROM Transaction t WHERE t.nozzleId = :nozzleId"),
    @NamedQuery(name = "Transaction.findByProductId", query = "SELECT t FROM Transaction t WHERE t.productId = :productId"),
    @NamedQuery(name = "Transaction.findByCustomerId", query = "SELECT t FROM Transaction t WHERE t.customerId = :customerId"),
    @NamedQuery(name = "Transaction.findByPaymentModeId", query = "SELECT t FROM Transaction t WHERE t.paymentModeId = :paymentModeId"),
    @NamedQuery(name = "Transaction.findByPaymentStatus", query = "SELECT t FROM Transaction t WHERE t.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Transaction.findByAmount", query = "SELECT t FROM Transaction t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transaction.findByQuantity", query = "SELECT t FROM Transaction t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "Transaction.findByPlatenumber", query = "SELECT t FROM Transaction t WHERE t.platenumber = :platenumber"),
    @NamedQuery(name = "Transaction.findByServerReqTime", query = "SELECT t FROM Transaction t WHERE t.serverReqTime = :serverReqTime"),
    @NamedQuery(name = "Transaction.findByServerResTime", query = "SELECT t FROM Transaction t WHERE t.serverResTime = :serverResTime"),
    @NamedQuery(name = "Transaction.findByDate", query = "SELECT t FROM Transaction t WHERE t.date = :date")})
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;
    @Column(name = "device_transaction_id")
    private Long deviceTransactionId;
    @Column(name = "device_transaction_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deviceTransactionTime;
    @Column(name = "branch_id")
    private Integer branchId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "device_id")
    private Integer deviceId;
    @Column(name = "pump_id")
    private Integer pumpId;
    @Column(name = "nozzle_id")
    private Integer nozzleId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "payment_mode_id")
    private Integer paymentModeId;
    @Size(max = 255)
    @Column(name = "payment_status", length = 255)
    private String paymentStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 22)
    private Double amount;
    @Column(name = "quantity", precision = 22)
    private Double quantity;
    @Size(max = 255)
    @Column(name = "platenumber", length = 255)
    private String platenumber;
    @Column(name = "server_req_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serverReqTime;
    @Column(name = "server_res_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serverResTime;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Transaction() {
    }

    public Transaction(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getDeviceTransactionId() {
        return deviceTransactionId;
    }

    public void setDeviceTransactionId(Long deviceTransactionId) {
        this.deviceTransactionId = deviceTransactionId;
    }

    public Date getDeviceTransactionTime() {
        return deviceTransactionTime;
    }

    public void setDeviceTransactionTime(Date deviceTransactionTime) {
        this.deviceTransactionTime = deviceTransactionTime;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getPumpId() {
        return pumpId;
    }

    public void setPumpId(Integer pumpId) {
        this.pumpId = pumpId;
    }

    public Integer getNozzleId() {
        return nozzleId;
    }

    public void setNozzleId(Integer nozzleId) {
        this.nozzleId = nozzleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(Integer paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    public Date getServerReqTime() {
        return serverReqTime;
    }

    public void setServerReqTime(Date serverReqTime) {
        this.serverReqTime = serverReqTime;
    }

    public Date getServerResTime() {
        return serverResTime;
    }

    public void setServerResTime(Date serverResTime) {
        this.serverResTime = serverResTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.Transaction[ transactionId=" + transactionId + " ]";
    }
    
}
