/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtimeweb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class Customer {
    
    @JsonProperty("OSversion")
    private String OSversion;
    
    @JsonProperty("creationTime")
    private String creationTime;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("fName")
    private String fName;
    
    @JsonProperty("imei")
    private String imei;
    
    @JsonProperty("lastUpdateTime")
    private String lastUpdateTime;
    
    @JsonProperty("msisdn")
    private String msisdn;
    
    @JsonProperty("otherNames")
    private String otherNames;
    
    @JsonProperty("phoneSerialNumber")
    private String phoneSerialNumber;
    
    @JsonProperty("pin")
    private String pin;
    
    @JsonProperty("status")
    private String status;

    /**
     * @return the creationTime
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime the creationTime to set
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the lastUpdateTime
     */
    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime the lastUpdateTime to set
     */
    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the otherNames
     */
    public String getOtherNames() {
        return otherNames;
    }

    /**
     * @param otherNames the otherNames to set
     */
    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    /**
     * @return the phoneSerialNumber
     */
    public String getPhoneSerialNumber() {
        return phoneSerialNumber;
    }

    /**
     * @param phoneSerialNumber the phoneSerialNumber to set
     */
    public void setPhoneSerialNumber(String phoneSerialNumber) {
        this.phoneSerialNumber = phoneSerialNumber;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the OSversion
     */
    public String getOSversion() {
        return OSversion;
    }

    /**
     * @param OSversion the OSversion to set
     */
    public void setOSversion(String OSversion) {
        this.OSversion = OSversion;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
