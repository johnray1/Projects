/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.model;

import java.util.Date;

/**
 *
 * @author JohnRay
 */

public class Liquidation{
    
    
    private String id;
    
    
    private String telcoName;
    
    
    private Double amount;
    
    private Date liquidationTime;
    
    private Date creationTime;
    
    private String createdBy;
    
    
    private String reference;
    
    
    private String referenceType;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the telcoName
     */
    public String getTelcoName() {
        return telcoName;
    }
    
    /**
     * @param telcoName the telcoName to set
     */
    public void setTelcoName(String telcoName) {
        this.telcoName = telcoName;
    }
    
    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    /**
     * @return the creationTime
     */
    public Date getCreationTime() {
        return creationTime;
    }
    
    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }
    
    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * @return the referenceType
     */
    public String getReferenceType() {
        return referenceType;
    }
    
    /**
     * @param referenceType the referenceType to set
     */
    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }
    
    /**
     * @return the liquidationTime
     */
    public Date getLiquidationTime() {
        return liquidationTime;
    }
    
    /**
     * @param liquidationTime the liquidationTime to set
     */
    public void setLiquidationTime(Date liquidationTime) {
        this.liquidationTime = liquidationTime;
    }
    
    
    
}
