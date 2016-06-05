/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author manzi
 */
@Entity
@SequenceGenerator(name="walletTransactionSeq", initialValue=1, allocationSize=1)
@Table(name = "wallets_transactions")
public class WalletTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="walletTransactionSeq")
    
    @Column(name = "id")
    private Long id;
    
    
    @Column(name = "src_wallet_Owner_id")
    private Integer srcWalletOwnerId;
    
    @Column(name = "src_wallet_type_id")
    private Integer srcWalletTypeId;
   
     @Column(name = "amount")
    private Integer amount;
    
    
    @Column(name = "src_wallet_prev_balance")
    private Double srcWalletPrevBalance;
    
    @Column(name = "src_wallet_new_balance")
    private Double srcWalletNewBalance;
    
    @Column(name = "dest_wallet_Owner_id")
    private Integer destWalletOwnerId;
    
    @Column(name = "dest_wallet_type_id")
    private Integer destWalletTypeId;
    
    @Column(name = "dest_wallet_prev_balance")
    private Double destWalletPrevBalance;
    
    @Column(name = "dest_wallet_new_balance")
    private Double destWalletNewBalance;
    
    @Column(name = "ref_trans_type_id")
    private Integer refTransTypeId;
    
    @Column(name = "ref_trans_id")
    private Long refTransId;
    
    @Column(name = "wallet_trans_id")
    private Integer walletTransId;
    
    @Column(name = "date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof WalletTransaction)) {
            return false;
        }
        WalletTransaction other = (WalletTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransport.Entities.walletTransaction[ id=" + id + " ]";
    }
    
    
    /**
     * @return the refTransTypeId
     */
    public Integer getRefTransTypeId() {
        return refTransTypeId;
    }
    
    /**
     * @param refTransTypeId the refTransTypeId to set
     */
    public void setRefTransTypeId(Integer refTransTypeId) {
        this.refTransTypeId = refTransTypeId;
    }
    
    /**
     * @return the refTransId
     */
    public Long getRefTransId() {
        return refTransId;
    }
    
    /**
     * @param refTransId the refTransId to set
     */
    public void setRefTransId(Long refTransId) {
        this.refTransId = refTransId;
    }
    
    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }
    
    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    /**
     * @return the srcWalletPrevBalance
     */
    public Double getSrcWalletPrevBalance() {
        return srcWalletPrevBalance;
    }
    
    /**
     * @param srcWalletPrevBalance the srcWalletPrevBalance to set
     */
    public void setSrcWalletPrevBalance(Double srcWalletPrevBalance) {
        this.srcWalletPrevBalance = srcWalletPrevBalance;
    }
    
    /**
     * @return the srcWalletNewBalance
     */
    public Double getSrcWalletNewBalance() {
        return srcWalletNewBalance;
    }
    
    /**
     * @param srcWalletNewBalance the srcWalletNewBalance to set
     */
    public void setSrcWalletNewBalance(Double srcWalletNewBalance) {
        this.srcWalletNewBalance = srcWalletNewBalance;
    }
    
    /**
     * @return the destWalletPrevBalance
     */
    public Double getDestWalletPrevBalance() {
        return destWalletPrevBalance;
    }
    
    /**
     * @param destWalletPrevBalance the destWalletPrevBalance to set
     */
    public void setDestWalletPrevBalance(Double destWalletPrevBalance) {
        this.destWalletPrevBalance = destWalletPrevBalance;
    }
    
    /**
     * @return the destWalletNewBalance
     */
    public Double getDestWalletNewBalance() {
        return destWalletNewBalance;
    }
    
    /**
     * @param destWalletNewBalance the destWalletNewBalance to set
     */
    public void setDestWalletNewBalance(Double destWalletNewBalance) {
        this.destWalletNewBalance = destWalletNewBalance;
    }
    
    /**
     * @return the srcWalletOwnerId
     */
    public Integer getSrcWalletOwnerId() {
        return srcWalletOwnerId;
    }
    
    /**
     * @param srcWalletOwnerId the srcWalletOwnerId to set
     */
    public void setSrcWalletOwnerId(Integer srcWalletOwnerId) {
        this.srcWalletOwnerId = srcWalletOwnerId;
    }
    
    /**
     * @return the srcWalletTypeId
     */
    public Integer getSrcWalletTypeId() {
        return srcWalletTypeId;
    }
    
    /**
     * @param srcWalletTypeId the srcWalletTypeId to set
     */
    public void setSrcWalletTypeId(Integer srcWalletTypeId) {
        this.srcWalletTypeId = srcWalletTypeId;
    }
    
    /**
     * @return the destWalletOwnerId
     */
    public Integer getDestWalletOwnerId() {
        return destWalletOwnerId;
    }
    
    /**
     * @param destWalletOwnerId the destWalletOwnerId to set
     */
    public void setDestWalletOwnerId(Integer destWalletOwnerId) {
        this.destWalletOwnerId = destWalletOwnerId;
    }
    
    /**
     * @return the destWalletTypeId
     */
    public Integer getDestWalletTypeId() {
        return destWalletTypeId;
    }
    
    /**
     * @param destWalletTypeId the destWalletTypeId to set
     */
    public void setDestWalletTypeId(Integer destWalletTypeId) {
        this.destWalletTypeId = destWalletTypeId;
    }
    
    /**
     * @return the walletTransId
     */
    public Integer getWalletTransId() {
        return walletTransId;
    }
    
    /**
     * @param walletTransId the walletTransId to set
     */
    public void setWalletTransId(Integer walletTransId) {
        this.walletTransId = walletTransId;
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    
}
