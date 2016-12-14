/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.chartmodel;

import java.util.Date;

/**
 *
 * @author John
 */
public class NozzleDash {
    private int nozzleId;
    private String nozzleName;
    private double index;
    private Date nozzleLastUsed;

    /**
     * @return the nozzleName
     */
    public String getNozzleName() {
        return nozzleName;
    }

    /**
     * @param nozzleName the nozzleName to set
     */
    public void setNozzleName(String nozzleName) {
        this.nozzleName = nozzleName;
    }

    /**
     * @return the index
     */
    public double getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(double index) {
        this.index = index;
    }

    /**
     * @return the nozzleId
     */
    public int getNozzleId() {
        return nozzleId;
    }

    /**
     * @param nozzleId the nozzleId to set
     */
    public void setNozzleId(int nozzleId) {
        this.nozzleId = nozzleId;
    }

    /**
     * @return the nozzleLastUsed
     */
    public Date getNozzleLastUsed() {
        return nozzleLastUsed;
    }

    /**
     * @param nozzleLastUsed the nozzleLastUsed to set
     */
    public void setNozzleLastUsed(Date nozzleLastUsed) {
        this.nozzleLastUsed = nozzleLastUsed;
    }

    
    
    
}
