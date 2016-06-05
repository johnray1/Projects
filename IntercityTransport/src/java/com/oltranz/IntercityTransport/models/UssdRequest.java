/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.models;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class UssdRequest implements Serializable{
    
    @XmlElement(name="MSISDN")
    private String msisdn;
    @XmlElement(name="SESSIONID")
    private String sessionId;
    @XmlElement(name="NEWREQUEST")
    private Integer newRequest;
    @XmlElement(name="AGENTID")
    private String agentId;
    @XmlElement(name="INPUT")
    private String input;
    @XmlElement(name="SPID")
    private String spid;
    @XmlElement(name="LANGUAGE")
    private String language;
    @XmlElement(name="FROMMULTIUSSD")
    private Boolean fromMultiUSSD;
    @XmlElement(name="resume")
    private Boolean resume;
    
    
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
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }
    
    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    /**
     * @return the newRequest
     */
    public Integer getNewRequest() {
        return newRequest;
    }
    
    /**
     * @param newRequest the newRequest to set
     */
    public void setNewRequest(Integer newRequest) {
        this.newRequest = newRequest;
    }
    
    /**
     * @return the agentId
     */
    public String getAgentId() {
        return agentId;
    }
    
    /**
     * @param agentId the agentId to set
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
    
    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }
    
    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }
    
    /**
     * @return the spid
     */
    public String getSpid() {
        return spid;
    }
    
    /**
     * @param spid the spid to set
     */
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }
    
    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    
    /**
     * @return the fromMultiUSSD
     */
    public Boolean getFromMultiUSSD() {
        return fromMultiUSSD;
    }
    
    /**
     * @param fromMultiUSSD the fromMultiUSSD to set
     */
    public void setFromMultiUSSD(Boolean fromMultiUSSD) {
        this.fromMultiUSSD = fromMultiUSSD;
    }

    /**
     * @return the resume
     */
    public Boolean getResume() {
        return resume;
    }

    /**
     * @param resume the resume to set
     */
    public void setResume(Boolean resume) {
        this.resume = resume;
    }
    
    
}
