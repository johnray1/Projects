/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.models;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aimable
 */
@XmlRootElement(name = "COMMAND")
@XmlAccessorType(XmlAccessType.FIELD)
public class UssdResponse {

    @XmlElement(name = "MSISDN")
    private String msisdn;

    @XmlElement(name = "SESSIONID")
    private String sessionId;

    @XmlElement(name = "SPID")
    private String spid;

    @XmlElement(name = "INPUT")
    private String input;
    
    @XmlElement(name ="SESSIONIDsLIST")
    private  List<String>  sessionIdsList;

    @XmlElement(name = "FREEFLOW")
    private String freeFlow;

    @XmlElement(name = "MESSAGE")
    private String messageTitle;

    @XmlElement(name = "NEWREQUEST")
    private int newRequest;

    @XmlElementWrapper(name = "MENUS")
    @XmlElement(name = "MENU")
    private List<String> menus;
    
    @XmlTransient
    private String clientState;
    
    @XmlTransient
    private String messageType;

    public UssdResponse() {
        sessionIdsList=new ArrayList<>();
    }
    
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    
    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getFreeFlow() {
        return freeFlow;
    }

    public void setFreeFlow(String freeFlow) {
        this.freeFlow = freeFlow;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public int getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(int newRequest) {
        this.newRequest = newRequest;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the sessionIds
     */
    public List<String> getSessionIdsList() {
        return sessionIdsList;
    }

    /**
     * @param sessionIdsList the sessionIds to set
     */
    public void setSessionIds(List<String> sessionIdsList) {
        this.sessionIdsList = sessionIdsList;
    }
    
    public void addSessionId2SessionsIds(String sessionId){
        this.sessionIdsList.add(sessionId);
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
    
    
    
}
