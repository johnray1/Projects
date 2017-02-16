/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engennotify.models;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class NotifyModel {
    
    @JsonProperty("dipNotificationMsg")
    private List<String> dipNotificationMsg;
    
    @JsonProperty("lowQuantityNotificationMsg")
    private List<String> lowQuantityNotificationMsg;
    
    @JsonProperty("idlingNotificationMsg")
    private List<String> idlingNotificationMsg;

    @JsonProperty("bizzareTxnNotificationMsg")
    private List<String> bizzareTxnNotificationMsg;

    /**
     * @return the dipNotificationMsg
     */
    public List<String> getDipNotificationMsg() {
        return dipNotificationMsg;
    }

    /**
     * @param dipNotificationMsg the dipNotificationMsg to set
     */
    public void setDipNotificationMsg(List<String> dipNotificationMsg) {
        this.dipNotificationMsg = dipNotificationMsg;
    }

    /**
     * @return the lowQuantityNotificationMsg
     */
    public List<String> getLowQuantityNotificationMsg() {
        return lowQuantityNotificationMsg;
    }

    /**
     * @param lowQuantityNotificationMsg the lowQuantityNotificationMsg to set
     */
    public void setLowQuantityNotificationMsg(List<String> lowQuantityNotificationMsg) {
        this.lowQuantityNotificationMsg = lowQuantityNotificationMsg;
    }

    /**
     * @return the idlingNotificationMsg
     */
    public List<String> getIdlingNotificationMsg() {
        return idlingNotificationMsg;
    }

    /**
     * @param idlingNotificationMsg the idlingNotificationMsg to set
     */
    public void setIdlingNotificationMsg(List<String> idlingNotificationMsg) {
        this.idlingNotificationMsg = idlingNotificationMsg;
    }

    /**
     * @return the bizzareTxnNotificationMsg
     */
    public List<String> getBizzareTxnNotificationMsg() {
        return bizzareTxnNotificationMsg;
    }

    /**
     * @param bizzareTxnNotificationMsg the bizzareTxnNotificationMsg to set
     */
    public void setBizzareTxnNotificationMsg(List<String> bizzareTxnNotificationMsg) {
        this.bizzareTxnNotificationMsg = bizzareTxnNotificationMsg;
    }
    
    
    
    
    
}
