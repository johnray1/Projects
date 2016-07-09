/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "branch_product_price", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchProductPrice.findAll", query = "SELECT b FROM BranchProductPrice b"),
    @NamedQuery(name = "BranchProductPrice.findByBranchId", query = "SELECT b FROM BranchProductPrice b WHERE b.branchProductPricePK.branchId = :branchId"),
    @NamedQuery(name = "BranchProductPrice.findByProductId", query = "SELECT b FROM BranchProductPrice b WHERE b.branchProductPricePK.productId = :productId"),
    @NamedQuery(name = "BranchProductPrice.findByBPrice", query = "SELECT b FROM BranchProductPrice b WHERE b.bPrice = :bPrice"),
    @NamedQuery(name = "BranchProductPrice.findByBpStatus", query = "SELECT b FROM BranchProductPrice b WHERE b.bpStatus = :bpStatus")})
public class BranchProductPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BranchProductPricePK branchProductPricePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "b_price", precision = 22)
    private Double bPrice;
    @Column(name = "bp_status")
    private Integer bpStatus;

    public BranchProductPrice() {
    }

    public BranchProductPrice(BranchProductPricePK branchProductPricePK) {
        this.branchProductPricePK = branchProductPricePK;
    }

    public BranchProductPrice(int branchId, int productId) {
        this.branchProductPricePK = new BranchProductPricePK(branchId, productId);
    }

    public BranchProductPricePK getBranchProductPricePK() {
        return branchProductPricePK;
    }

    public void setBranchProductPricePK(BranchProductPricePK branchProductPricePK) {
        this.branchProductPricePK = branchProductPricePK;
    }

    public Double getBPrice() {
        return bPrice;
    }

    public void setBPrice(Double bPrice) {
        this.bPrice = bPrice;
    }

    public Integer getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(Integer bpStatus) {
        this.bpStatus = bpStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (branchProductPricePK != null ? branchProductPricePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchProductPrice)) {
            return false;
        }
        BranchProductPrice other = (BranchProductPrice) object;
        if ((this.branchProductPricePK == null && other.branchProductPricePK != null) || (this.branchProductPricePK != null && !this.branchProductPricePK.equals(other.branchProductPricePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.BranchProductPrice[ branchProductPricePK=" + branchProductPricePK + " ]";
    }
    
}
