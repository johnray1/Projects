/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.model;

import java.util.List;

/**
 *
 * @author John
 */
public class PumpDash {
    
    private String pumpName;
    private List<NozzleDash> nozzleDash;

    /**
     * @return the pumpName
     */
    public String getPumpName() {
        return pumpName;
    }

    /**
     * @param pumpName the pumpName to set
     */
    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
    }

    /**
     * @return the nozzleDash
     */
    public List<NozzleDash> getNozzleDash() {
        return nozzleDash;
    }

    /**
     * @param nozzleDash the nozzleDash to set
     */
    public void setNozzleDash(List<NozzleDash> nozzleDash) {
        this.nozzleDash = nozzleDash;
    }
    
    
}
