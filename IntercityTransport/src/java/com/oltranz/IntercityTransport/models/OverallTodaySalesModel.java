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
public class OverallTodaySalesModel {
    private Integer up2YesterdayRevenue;
    private Integer up2YesterdayBusesCount;
    private Integer todayRevenue;
    private Integer todayPassengersCount;
    private Integer up2YesterdayPassengersCount;
    private Integer todayBusesCount;

    /**
     * @return the up2YesterdayRevenue
     */
    public Integer getUp2YesterdayRevenue() {
        return up2YesterdayRevenue;
    }

    /**
     * @param up2YesterdayRevenue the up2YesterdayRevenue to set
     */
    public void setUp2YesterdayRevenue(Integer up2YesterdayRevenue) {
        this.up2YesterdayRevenue = up2YesterdayRevenue;
    }

    /**
     * @return the up2YesterdayBusesCount
     */
    public Integer getUp2YesterdayBusesCount() {
        return up2YesterdayBusesCount;
    }

    /**
     * @param up2YesterdayBusesCount the up2YesterdayBusesCount to set
     */
    public void setUp2YesterdayBusesCount(Integer up2YesterdayBusesCount) {
        this.up2YesterdayBusesCount = up2YesterdayBusesCount;
    }

    /**
     * @return the todayRevenue
     */
    public Integer getTodayRevenue() {
        return todayRevenue;
    }

    /**
     * @param todayRevenue the todayRevenue to set
     */
    public void setTodayRevenue(Integer todayRevenue) {
        this.todayRevenue = todayRevenue;
    }

    /**
     * @return the todayPassengersCount
     */
    public Integer getTodayPassengersCount() {
        return todayPassengersCount;
    }

    /**
     * @param todayPassengersCount the todayPassengersCount to set
     */
    public void setTodayPassengersCount(Integer todayPassengersCount) {
        this.todayPassengersCount = todayPassengersCount;
    }

    /**
     * @return the up2YesterdayPassengersCount
     */
    public Integer getUp2YesterdayPassengersCount() {
        return up2YesterdayPassengersCount;
    }

    /**
     * @param up2YesterdayPassengersCount the up2YesterdayPassengersCount to set
     */
    public void setUp2YesterdayPassengersCount(Integer up2YesterdayPassengersCount) {
        this.up2YesterdayPassengersCount = up2YesterdayPassengersCount;
    }

    /**
     * @return the todayBusesCount
     */
    public Integer getTodayBusesCount() {
        return todayBusesCount;
    }

    /**
     * @param todayBusesCount the todayBusesCount to set
     */
    public void setTodayBusesCount(Integer todayBusesCount) {
        this.todayBusesCount = todayBusesCount;
    }
}
