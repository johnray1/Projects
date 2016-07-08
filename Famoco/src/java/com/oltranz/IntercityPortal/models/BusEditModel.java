/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityPortal.models;

import java.util.List;
import utils.CommonWebUI.selectListItemInteger;

/**
 *
 * @author manzi
 */
public class BusEditModel  {
    private String numberPlate;
    private Integer ownerId;
    private Boolean isTransporterOwned;
    private String descr;
    private List<selectListItemInteger> ServiceProviders;

    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * @return the ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * @return the ServiceProviders
     */
    public List<selectListItemInteger> getServiceProviders() {
        return ServiceProviders;
    }

    /**
     * @param ServiceProviders the ServiceProviders to set
     */
    public void setServiceProviders(List<selectListItemInteger> ServiceProviders) {
        this.ServiceProviders = ServiceProviders;
    }

    /**
     * @return the isTransporterOwned
     */
    public Boolean getIsTransporterOwned() {
        return isTransporterOwned;
    }

    /**
     * @param isTransporterOwned the isTransporterOwned to set
     */
    public void setIsTransporterOwned(Boolean isTransporterOwned) {
        this.isTransporterOwned = isTransporterOwned;
    }
}
