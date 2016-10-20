/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.models;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class SyncTransaction {
    
    @JsonProperty("saleDetailsModelList")
    private List<SaleDetailsModel>  saleDetailsModelList; 

    /**
     * @return the saleDetailsModelList
     */
    public List<SaleDetailsModel> getSaleDetailsModelList() {
        return saleDetailsModelList;
    }

    /**
     * @param saleDetailsModelList the saleDetailsModelList to set
     */
    public void setSaleDetailsModelList(List<SaleDetailsModel> saleDetailsModelList) {
        this.saleDetailsModelList = saleDetailsModelList;
    }
    

    
    
    
}
