/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcsvignettes.models;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */
public class DashboardItemTotals { 
    
    @JsonProperty("itemName")
    private String  itemName;
     
    @JsonProperty("count")
    private Long count;
    
    @JsonProperty("value")
    private Double sum;

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
    
    

   
}
