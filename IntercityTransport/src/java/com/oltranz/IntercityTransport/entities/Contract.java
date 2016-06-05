/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@SequenceGenerator(name="contractSeq", initialValue=1, allocationSize=1)
@Table(name = "contracts")
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "id")
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="contractSeq")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "transporter_id")  //always Mainn contractor
    private Integer transporterId;
    
    
    @Column(name = "serviceProvider_id")
    private Integer serviceProviderId;
    
    @Column(name = "payment_type_id")
    private Integer paymentTypeId;
    
    @Column(name = "service_id")
    private Integer serviceId;
    
    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name = "descr", length = 255)
    private String descr;
    
    @Column(name = "status")
    private int status=7; //undeleted, enabled, unhidden
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.Contract[ id=" + getId() + " ]";
    }
    
    
    
    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @param startDate the startDate to set
     * @param format the startDate to set
     */
    public void setStartDate(String startDate, String format) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            this.startDate = sdf.parse(startDate);
        }
        catch(Exception e){
            
        }
        
    }
    
    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public void setEndDate(String endDate, String format) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            this.endDate = sdf.parse(endDate);
        }
        catch(Exception e){
            
        }
        
    }
    
    
    /**
     * @return the transporterId
     */
    public Integer getTransporterId() {
        return transporterId;
    }
    
    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }
    
    /**
     * @return the serviceProviderId
     */
    public Integer getServiceProviderId() {
        return serviceProviderId;
    }
    
    /**
     * @param serviceProviderId the serviceProviderId to set
     */
    public void setServiceProviderId(Integer serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the paymentTypeId
     */
    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }
    
    /**
     * @param paymentTypeId the paymentTypeId to set
     */
    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    /**
     * @return the serviceId
     */
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
    
}
