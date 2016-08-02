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
public class DailyChartProductModel {
    
    private String[] day;
    private double[] superList;
    private double[] gasoilList;

    /**
     * @return the day
     */
    public String[] getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String[] day) {
        this.day = day;
    }

    /**
     * @return the superList
     */
    public double[] getSuperList() {
        return superList;
    }

    /**
     * @param superList the superList to set
     */
    public void setSuperList(double[] superList) {
        this.superList = superList;
    }

    /**
     * @return the gasoilList
     */
    public double[] getGasoilList() {
        return gasoilList;
    }

    /**
     * @param gasoilList the gasoilList to set
     */
    public void setGasoilList(double[] gasoilList) {
        this.gasoilList = gasoilList;
    }

    
    
}
