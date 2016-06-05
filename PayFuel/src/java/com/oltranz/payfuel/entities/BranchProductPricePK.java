/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author John
 */
@Embeddable
public class BranchProductPricePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "branch_id", nullable = false)
    private int branchId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id", nullable = false)
    private int productId;

    public BranchProductPricePK() {
    }

    public BranchProductPricePK(int branchId, int productId) {
        this.branchId = branchId;
        this.productId = productId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) branchId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchProductPricePK)) {
            return false;
        }
        BranchProductPricePK other = (BranchProductPricePK) object;
        if (this.branchId != other.branchId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.BranchProductPricePK[ branchId=" + branchId + ", productId=" + productId + " ]";
    }
    
}
