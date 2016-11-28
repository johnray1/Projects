/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "user_shift", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
public class UserShift implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    
    @Column(name = "cash", precision = 22)
    private Double cash;
    
    @Column(name = "voucher", precision = 22)
    private Double voucher;
    
    @Column(name = "mtn", precision = 22)
    private Double mtn;
    
    @Column(name = "tigo", precision = 22)
    private Double tigo;
    
    @Column(name = "airtel", precision = 22)
    private Double airtel;
    
    @Column(name = "visa", precision = 22)
    private Double visa;
    
    @Column(name = "master", precision = 22)
    private Double master;
    
    @Column(name = "debt", precision = 22)
    private Double debt;
    
    @Column(name = "engenCard", precision = 22)
    private Double engenCard;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return the cash
     */
    public Double getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(Double cash) {
        this.cash = cash;
    }

    /**
     * @return the voucher
     */
    public Double getVoucher() {
        return voucher;
    }

    /**
     * @param voucher the voucher to set
     */
    public void setVoucher(Double voucher) {
        this.voucher = voucher;
    }

    /**
     * @return the mtn
     */
    public Double getMtn() {
        return mtn;
    }

    /**
     * @param mtn the mtn to set
     */
    public void setMtn(Double mtn) {
        this.mtn = mtn;
    }

    /**
     * @return the tigo
     */
    public Double getTigo() {
        return tigo;
    }

    /**
     * @param tigo the tigo to set
     */
    public void setTigo(Double tigo) {
        this.tigo = tigo;
    }

    /**
     * @return the airtel
     */
    public Double getAirtel() {
        return airtel;
    }

    /**
     * @param airtel the airtel to set
     */
    public void setAirtel(Double airtel) {
        this.airtel = airtel;
    }

    /**
     * @return the visa
     */
    public Double getVisa() {
        return visa;
    }

    /**
     * @param visa the visa to set
     */
    public void setVisa(Double visa) {
        this.visa = visa;
    }

    /**
     * @return the master
     */
    public Double getMaster() {
        return master;
    }

    /**
     * @param master the master to set
     */
    public void setMaster(Double master) {
        this.master = master;
    }

    /**
     * @return the debt
     */
    public Double getDebt() {
        return debt;
    }

    /**
     * @param debt the debt to set
     */
    public void setDebt(Double debt) {
        this.debt = debt;
    }

    /**
     * @return the engenCard
     */
    public Double getEngenCard() {
        return engenCard;
    }

    /**
     * @param engenCard the engenCard to set
     */
    public void setEngenCard(Double engenCard) {
        this.engenCard = engenCard;
    }
    
    
    
}
