/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;

/**
 *
 * @author ismaelnzamutuma
 */
public class DashboardContractor implements Serializable{
    private int busOwnerId;
    private int busesCount;
    private int todaySales;
    private int todayDue;
    private String busOwnerName;

    /**
     * @return the busOwnerId
     */
    public int getBusOwnerId() {
        return busOwnerId;
    }

    /**
     * @param busOwnerId the busOwnerId to set
     */
    public void setBusOwnerId(int busOwnerId) {
        this.busOwnerId = busOwnerId;
    }

    /**
     * @return the busesCount
     */
    public int getBusesCount() {
        return busesCount;
    }

    /**
     * @param busesCount the busesCount to set
     */
    public void setBusesCount(int busesCount) {
        this.busesCount = busesCount;
    }

    /**
     * @return the todaySales
     */
    public int getTodaySales() {
        return todaySales;
    }

    /**
     * @param todaySales the todaySales to set
     */
    public void setTodaySales(int todaySales) {
        this.todaySales = todaySales;
    }

    /**
     * @return the todayDue
     */
    public int getTodayDue() {
        return todayDue;
    }

    /**
     * @param todayDue the todayDue to set
     */
    public void setTodayDue(int todayDue) {
        this.todayDue = todayDue;
    }

    /**
     * @return the busOwnerName
     */
    public String getBusOwnerName() {
        return busOwnerName;
    }

    /**
     * @param busOwnerName the busOwnerName to set
     */
    public void setBusOwnerName(String busOwnerName) {
        this.busOwnerName = busOwnerName;
    }
    
    
}
