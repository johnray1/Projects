/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "erroneous_transaction", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ErroneousTransaction.findAll", query = "SELECT e FROM ErroneousTransaction e"),
    @NamedQuery(name = "ErroneousTransaction.findById", query = "SELECT e FROM ErroneousTransaction e WHERE e.id = :id"),
    @NamedQuery(name = "ErroneousTransaction.findByTransactionId", query = "SELECT e FROM ErroneousTransaction e WHERE e.transactionId = :transactionId"),
    @NamedQuery(name = "ErroneousTransaction.findByChecksum", query = "SELECT e FROM ErroneousTransaction e WHERE e.checksum = :checksum"),
    @NamedQuery(name = "ErroneousTransaction.findByDeviceTransactionId", query = "SELECT e FROM ErroneousTransaction e WHERE e.deviceTransactionId = :deviceTransactionId"),
    @NamedQuery(name = "ErroneousTransaction.findByDeviceTransactionTime", query = "SELECT e FROM ErroneousTransaction e WHERE e.deviceTransactionTime = :deviceTransactionTime"),
    @NamedQuery(name = "ErroneousTransaction.findByBranchId", query = "SELECT e FROM ErroneousTransaction e WHERE e.branchId = :branchId"),
    @NamedQuery(name = "ErroneousTransaction.findByUserId", query = "SELECT e FROM ErroneousTransaction e WHERE e.userId = :userId"),
    @NamedQuery(name = "ErroneousTransaction.findByDeviceId", query = "SELECT e FROM ErroneousTransaction e WHERE e.deviceId = :deviceId"),
    @NamedQuery(name = "ErroneousTransaction.findByTankId", query = "SELECT e FROM ErroneousTransaction e WHERE e.tankId = :tankId"),
    @NamedQuery(name = "ErroneousTransaction.findByPumpId", query = "SELECT e FROM ErroneousTransaction e WHERE e.pumpId = :pumpId"),
    @NamedQuery(name = "ErroneousTransaction.findByNozzleId", query = "SELECT e FROM ErroneousTransaction e WHERE e.nozzleId = :nozzleId"),
    @NamedQuery(name = "ErroneousTransaction.findByProductId", query = "SELECT e FROM ErroneousTransaction e WHERE e.productId = :productId"),
    @NamedQuery(name = "ErroneousTransaction.findByCustomerId", query = "SELECT e FROM ErroneousTransaction e WHERE e.customerId = :customerId"),
    @NamedQuery(name = "ErroneousTransaction.findByPaymentModeId", query = "SELECT e FROM ErroneousTransaction e WHERE e.paymentModeId = :paymentModeId"),
    @NamedQuery(name = "ErroneousTransaction.findByPaymentStatus", query = "SELECT e FROM ErroneousTransaction e WHERE e.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "ErroneousTransaction.findByAmount", query = "SELECT e FROM ErroneousTransaction e WHERE e.amount = :amount"),
    @NamedQuery(name = "ErroneousTransaction.findByQuantity", query = "SELECT e FROM ErroneousTransaction e WHERE e.quantity = :quantity"),
    @NamedQuery(name = "ErroneousTransaction.findByPlatenumber", query = "SELECT e FROM ErroneousTransaction e WHERE e.platenumber = :platenumber"),
    @NamedQuery(name = "ErroneousTransaction.findByServerReqTime", query = "SELECT e FROM ErroneousTransaction e WHERE e.serverReqTime = :serverReqTime"),
    @NamedQuery(name = "ErroneousTransaction.findByServerResTime", query = "SELECT e FROM ErroneousTransaction e WHERE e.serverResTime = :serverResTime"),
    @NamedQuery(name = "ErroneousTransaction.findByDate", query = "SELECT e FROM ErroneousTransaction e WHERE e.date = :date")})
public class ErroneousTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private long transactionId;
    @Column(name = "checksum")
    private Integer checksum;
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
    @Column(name = "tank_id")
    private Integer tankId;
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

    public ErroneousTransaction() {
    }

    public ErroneousTransaction(Long id) {
        this.id = id;
    }

    public ErroneousTransaction(Long id, long transactionId) {
        this.id = id;
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
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

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErroneousTransaction)) {
            return false;
        }
        ErroneousTransaction other = (ErroneousTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.ErroneousTransaction[ id=" + id + " ]";
    }
    
}
