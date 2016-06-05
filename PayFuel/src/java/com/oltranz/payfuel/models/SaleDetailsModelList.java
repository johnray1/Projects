/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.models;

/**
 *
 * @author John
 */
public class SaleDetailsModelList {
    
    private SaleDetailsModel saleDetailsModel;
    private int status;
    private String message;

   

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
     * @return the saleDetailsModel
     */
    public SaleDetailsModel getSaleDetailsModel() {
        return saleDetailsModel;
    }

    /**
     * @param saleDetailsModel the saleDetailsModel to set
     */
    public void setSaleDetailsModel(SaleDetailsModel saleDetailsModel) {
        this.saleDetailsModel = saleDetailsModel;
    }
    
    
   
}
