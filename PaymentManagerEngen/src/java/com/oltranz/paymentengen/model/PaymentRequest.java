/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.paymentengen.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@XmlRootElement(name="COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentRequest {
    
    @XmlElement(name="TRANSACTIONID")
    private Long transactionId;
    
    @XmlElement(name="PAYMENTMODEID")
    private int paymentModeId;
    
    @XmlElement(name="AMOUNT")
    private Double amount;
    
    @XmlElement(name="TELEPHONE")
    private String telephone;
    
    @XmlElement(name="VOUCHER")
    private String voucherNumber;
    
    /**
     * @return the transactionId
     */
    public Long getTransactionId() {
        return transactionId;
    }
    
    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    
    /**
     * @return the paymentModeId
     */
    public int getPaymentModeId() {
        return paymentModeId;
    }
    
    /**
     * @param paymentModeId the paymentModeId to set
     */
    public void setPaymentModeId(int paymentModeId) {
        this.paymentModeId = paymentModeId;
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
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }
    
    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    /**
     * @return the voucherNumber
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }
    
    /**
     * @param voucherNumber the voucherNumber to set
     */
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
    
    
    
}
