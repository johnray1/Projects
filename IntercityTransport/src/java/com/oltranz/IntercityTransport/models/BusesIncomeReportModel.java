/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.models;
/**
 *
 * @author manzi
 */
public class BusesIncomeReportModel {
    
    private String numberPlate;
    private Integer ownerId;
    private String ownerName;
    private Integer yesterdayIncome;
    private Integer todayIncome;
    
    public BusesIncomeReportModel(){
        
    }

      public BusesIncomeReportModel(String numberPlate,Integer ownerId,String ownerName, Integer todayIncome) {
        this.numberPlate=numberPlate;
        this.ownerId=ownerId;
        this.ownerName=ownerName;
        this.todayIncome=todayIncome;
       
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
     * @return the yesterdayIncome
     */
    public Integer getYesterdayIncome() {
        return yesterdayIncome;
    }

    /**
     * @param yesterdayIncome the yesterdayIncome to set
     */
    public void setYesterdayIncome(Integer yesterdayIncome) {
        this.yesterdayIncome = yesterdayIncome;
    }

    /**
     * @return the todayIncome
     */
    public Integer getTodayIncome() {
        return todayIncome;
    }

    /**
     * @param todayIncome the todayIncome to set
     */
    public void setTodayIncome(Integer todayIncome) {
        this.todayIncome = todayIncome;
    }

   
    
}
