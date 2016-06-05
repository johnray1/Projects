/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "buses_on_contracts")

@XmlRootElement
public class BusOnContract implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }  
    
    @EmbeddedId
    private BusOnContractPK BusOnContractPK;
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getBusOnContract() != null ? getBusOnContract().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusOnContract)) {
            return false;
        }
        BusOnContract other = (BusOnContract) object;
        if ((this.getBusOnContract() == null && other.getBusOnContract() != null) || (this.getBusOnContract() != null && !this.BusOnContractPK.equals(other.BusOnContractPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.BusOnContract[ BusOnContract=" + getBusOnContract() + " ]";
    }

    /**
     * @return the BusOnContractPK
     */
    public BusOnContractPK getBusOnContract() {
        return BusOnContractPK;
    }

    /**
     * @param BusOnContractPK the BusOnContractPK to set
     */
    public void setBusOnContract(BusOnContractPK BusOnContractPK) {
        this.BusOnContractPK = BusOnContractPK;
    }


    

    
}
