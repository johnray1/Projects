/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author ismaelnzamutuma
 */
@JsonRootName("device")
public class Device implements Serializable{
    private int transporterId;
    private int typeId;
    private int status;
    private String id;
    private String currentBusNumberPlate;
    private String decr;

    /**
     * @return the transporterId
     */
    public int getTransporterId() {
        return transporterId;
    }

    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    /**
     * @return the typId
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * @param typId the typId to set
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the currentBusNumberPlate
     */
    public String getCurrentBusNumberPlate() {
        return currentBusNumberPlate;
    }

    /**
     * @param currentBusNumberPlate the currentBusNumberPlate to set
     */
    public void setCurrentBusNumberPlate(String currentBusNumberPlate) {
        this.currentBusNumberPlate = currentBusNumberPlate;
    }

    /**
     * @return the descr
     */
    public String getDecr() {
        return decr;
    }

 
    public void setDecr(String decr) {
        this.decr = decr;
    }
    
}
