/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities; 

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ismaelnzamutuma
 */
@Embeddable
public class BusOnContractPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_plate", nullable = false)
    private String numberPlate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "contract_Id", nullable = false)
    private Integer contractId;
    
    
    
    public BusOnContractPK() {
    }
    
    public BusOnContractPK(String numberPlate, Integer contractId) {
        this.numberPlate = numberPlate;
        this.contractId = contractId;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getNumberPlate().hashCode();
        hash += (int) getcontractId().hashCode();
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PassengerPK)) {
            return false;
        }
        BusOnContractPK other = (BusOnContractPK) object;
        if (!this.getcontractId().equals(other.getcontractId())) {
            return false;
        }
        if (!this.getNumberPlate().equals(other.getNumberPlate())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.BusOnContract[ UserId=" + getNumberPlate() + ", transporterId=" + getcontractId() + " ]";
    }

    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * @return the contractId
     */
    public Integer getcontractId() {
        return contractId;
    }

    /**
     * @param contractId the contractId to set
     */
    public void setcontractId(Integer contractId) {
        this.contractId = contractId;
    }

    
    
}
