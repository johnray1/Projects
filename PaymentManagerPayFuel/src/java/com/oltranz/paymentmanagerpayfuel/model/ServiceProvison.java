/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.paymentmanagerpayfuel.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@XmlRootElement(name = "COMMAND")
@XmlAccessorType (XmlAccessType.FIELD)
public class ServiceProvison {
    
    @XmlElement(name="TRANSID")
    private Long TRANSID;
    
    @XmlElement(name="CONTRACTID")
    private Integer CONTRACTID;
    
    @XmlElement(name="STATUSCODE")
    private Integer STATUSCODE;
    
    @XmlElement(name="SPTRANSID")
    private Long SPTRANSID;
    
    @XmlElement(name="STATUSDESC")
    private String STATUSDESC;

    public ServiceProvison() {
    }

    public ServiceProvison(Long TRANSID, Integer CONTRACTID, Integer STATUSCODE, Long SPTRANSID, String STATUSDESC) {
        this.TRANSID = TRANSID;
        this.CONTRACTID = CONTRACTID;
        this.STATUSCODE = STATUSCODE;
        this.SPTRANSID = SPTRANSID;
        this.STATUSDESC = STATUSDESC;
    }

    /**
     * @return the TRANSID
     */
    public Long getTRANSID() {
        return TRANSID;
    }

    /**
     * @param TRANSID the TRANSID to set
     */
    public void setTRANSID(Long TRANSID) {
        this.TRANSID = TRANSID;
    }

    /**
     * @return the CONTRACTID
     */
    public Integer getCONTRACTID() {
        return CONTRACTID;
    }

    /**
     * @param CONTRACTID the CONTRACTID to set
     */
    public void setCONTRACTID(Integer CONTRACTID) {
        this.CONTRACTID = CONTRACTID;
    }

    /**
     * @return the STATUSCODE
     */
    public Integer getSTATUSCODE() {
        return STATUSCODE;
    }

    /**
     * @param STATUSCODE the STATUSCODE to set
     */
    public void setSTATUSCODE(Integer STATUSCODE) {
        this.STATUSCODE = STATUSCODE;
    }

    /**
     * @return the SPTRANSID
     */
    public Long getSPTRANSID() {
        return SPTRANSID;
    }

    /**
     * @param SPTRANSID the SPTRANSID to set
     */
    public void setSPTRANSID(Long SPTRANSID) {
        this.SPTRANSID = SPTRANSID;
    }

    /**
     * @return the STATUSDESC
     */
    public String getSTATUSDESC() {
        return STATUSDESC;
    }

    /**
     * @param STATUSDESC the STATUSDESC to set
     */
    public void setSTATUSDESC(String STATUSDESC) {
        this.STATUSDESC = STATUSDESC;
    }

    
    
}
