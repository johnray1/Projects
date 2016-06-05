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
public class BusOwnersTodayIncomeModel {
    private Integer busOwnerId;
    private String busOwnerName;
    private Long busesCount;
    private Long todaySales;
    private Long todayDue;

    /**
     * @return the busOwnerId
     */
    public Integer getBusOwnerId() {
        return busOwnerId;
    }

    /**
     * @param busOwnerId the busOwnerId to set
     */
    public void setBusOwnerId(Integer busOwnerId) {
        this.busOwnerId = busOwnerId;
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

    /**
     * @return the todaySales
     */
    public Long getTodaySales() {
        return todaySales;
    }

    /**
     * @param todaySales the todaySales to set
     */
    public void setTodaySales(Long todaySales) {
        this.todaySales = todaySales;
    }

    /**
     * @return the todayDue
     */
    public Long getTodayDue() {
        return todayDue;
    }

    /**
     * @param todayDue the todayDue to set
     */
    public void setTodayDue(Long todayDue) {
        this.todayDue = todayDue;
    }

    /**
     * @return the busesCount
     */
    public Long getBusesCount() {
        return busesCount;
    }

    /**
     * @param busesCount the busesCount to set
     */
    public void setBusesCount(Long busesCount) {
        this.busesCount = busesCount;
    }

       
}
