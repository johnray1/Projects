/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.models;

import com.oltranz.IntercityTransport.entities.SaleItem;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author manzi
 */

public class SellingProfileWithItemsModel {
    
    @JsonProperty("id")
    private Integer id; 
    
     @JsonProperty("name")
    private String name;
    
     
    @JsonProperty("transporterId")
    private Integer transporterId;
    
    @JsonProperty("items")
    private List<SaleItem> items;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the transporterId
     */
    public Integer getTransporterId() {
        return transporterId;
    }

    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }

    /**
     * @return the item
     */
    public List<SaleItem> getItems() {
        return items;
    }

    /**
     * @param items the item to set
     */
    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

   
}
