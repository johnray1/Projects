/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

/**
 *
 * @author JohnRay
 */
public class PaymentCompletedResponse {
    
    private SimpleStatus status;  //Expected to have the same status as the posted one 

    /**
     * @return the status
     */
    public SimpleStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(SimpleStatus status) {
        this.status = status;
    }
    
    
    
}
