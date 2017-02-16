/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.models;

/**
 *
 * @author JohnRay
 */
public class SmsModel {
    
    
    private String src;
    
    private String dest;
    
    private String message;
    
    private int wait;
    
    private int contractId;

    /**
     * @return the src
     */
    public String getSrc() {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * @return the dest
     */
    public String getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(String dest) {
        this.dest = dest;
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
     * @return the wait
     */
    public int getWait() {
        return wait;
    }

    /**
     * @param wait the wait to set
     */
    public void setWait(int wait) {
        this.wait = wait;
    }

    /**
     * @return the contractId
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * @param contractId the contractId to set
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
    
    
}
