/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.chartmodel;

/**
 *
 * @author John
 */
public class TankChartModel {
    
    private String name;
    private double max;
    private double current;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the max
     */
    public double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * @return the current
     */
    public double getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(double current) {
        this.current = current;
    }
    
    
    
    
}
