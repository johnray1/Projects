/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "wallets")
public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @EmbeddedId
    private WalletPK walletPK;
    
       
    @Column(name = "balance")
    private double balance=0;
    
    public Wallet(){
        
    }
    
    public Wallet(Integer ownerId, Integer typeId){
        walletPK=new WalletPK();
        this.walletPK.setTypeId(typeId);
        this.walletPK.setOwnerId(ownerId);
    }
      public Wallet(WalletPK walletPK){
        this.walletPK=walletPK;
    }
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getWalletPK() != null ? getWalletPK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WalletPK)) {
            return false;
        }
        Wallet other = (Wallet) object;
        if ((this.getWalletPK() == null && other.getWalletPK() != null) || (this.getWalletPK() != null && !this.walletPK.equals(other.walletPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransport.Entities.Wallet[ walletPK=" + getWalletPK() + " ]";
    }

    /**
     * @return the walletPK
     */
    public WalletPK getWalletPK() {
        return walletPK;
    }

    /**
     * @param walletPK the walletPK to set
     */
    public void setWalletPK(WalletPK walletPK) {
        this.walletPK = walletPK;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

   
}
