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
public class DashboardBus implements Serializable{
    private String numberPlate;
    private String ownerName;
    private int ownerId;
    private int yesterdayIncome;
    private int todayIncome;

    /**
     * @return the plateNumber
     */
   
    /**
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the ownerId
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return the yesterdayIncome
     */
    public int getYesterdayIncome() {
        return yesterdayIncome;
    }

    /**
     * @param yesterdayIncome the yesterdayIncome to set
     */
    public void setYesterdayIncome(int yesterdayIncome) {
        this.yesterdayIncome = yesterdayIncome;
    }

    /**
     * @return the todayIncome
     */
    public int getTodayIncome() {
        return todayIncome;
    }

    /**
     * @param todayIncome the todayIncome to set
     */
    public void setTodayIncome(int todayIncome) {
        this.todayIncome = todayIncome;
    }

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
    
}
