/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.paymentengen.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class VoucherSingle {
    
    @JsonProperty("Voucher")
    private Voucher voucher;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("statusCode")
    private Integer statusCode;

    /**
     * @return the voucher
     */
    public Voucher getVoucher() {
        return voucher;
    }

    /**
     * @param voucher the voucher to set
     */
    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    
}
