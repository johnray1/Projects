/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.paymentengen.model;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class Voucher {
    
    @JsonProperty("voucherId")
    private Long voucherId;
    
    @JsonProperty("voucherNo")
    private String voucherNo;
    
    @JsonProperty("initialAmount")
    private Double initialAmount;
    
    @JsonProperty("remainAmount")
    private Double remainAmount;
    
    @JsonProperty("numberPlate")
    private String numberPlate;
    
    @JsonProperty("provisonDate")
    private Date provisonDate;
    
    @JsonProperty("expiryDate")
    private Date expiryDate;
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("customerId")
    private Long customerId;

    /**
     * @return the voucherId
     */
    public Long getVoucherId() {
        return voucherId;
    }

    /**
     * @param voucherId the voucherId to set
     */
    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    /**
     * @return the voucherNo
     */
    public String getVoucherNo() {
        return voucherNo;
    }

    /**
     * @param voucherNo the voucherNo to set
     */
    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    /**
     * @return the initialAmount
     */
    public Double getInitialAmount() {
        return initialAmount;
    }

    /**
     * @param initialAmount the initialAmount to set
     */
    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    /**
     * @return the remainAmount
     */
    public Double getRemainAmount() {
        return remainAmount;
    }

    /**
     * @param remainAmount the remainAmount to set
     */
    public void setRemainAmount(Double remainAmount) {
        this.remainAmount = remainAmount;
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
     * @return the provisonDate
     */
    public Date getProvisonDate() {
        return provisonDate;
    }

    /**
     * @param provisonDate the provisonDate to set
     */
    public void setProvisonDate(Date provisonDate) {
        this.provisonDate = provisonDate;
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the customerId
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    
    
}
