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
 * @author John
 */
@XmlRootElement(name="COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponse {
    
    @XmlElement(name="RESPONDERSTATUS")
    private int resStatus;
    
    @XmlElement(name="REQUESTSTATUS")
    private int reqStatus;
    
    @XmlElement(name="REQUESTSTATUSDESC")
    private String desc;

    /**
     * @return the resStatus
     */
    public int getResStatus() {
        return resStatus;
    }

    /**
     * @param resStatus the resStatus to set
     */
    public void setResStatus(int resStatus) {
        this.resStatus = resStatus;
    }

    /**
     * @return the reqStatus
     */
    public int getReqStatus() {
        return reqStatus;
    }

    /**
     * @param reqStatus the reqStatus to set
     */
    public void setReqStatus(int reqStatus) {
        this.reqStatus = reqStatus;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
