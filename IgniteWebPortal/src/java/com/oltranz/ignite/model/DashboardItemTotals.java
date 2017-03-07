/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.model;

/**
 *
 * @author JohnRay
 */
public class DashboardItemTotals {
    
    private String  itemName;
   
    private Long count;
    
    private Double sum;
    
    private String resultCode;
    
    private String resultDescr;
    
    

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * @return the sum
     */
    public Double getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultDescr
     */
    public String getResultDescr() {
        return resultDescr;
    }

    /**
     * @param resultDescr the resultDescr to set
     */
    public void setResultDescr(String resultDescr) {
        this.resultDescr = resultDescr;
    }
    
    
    
}
