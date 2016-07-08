/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismaelnzamutuma
 */
public class FilterSubcontractors implements Serializable {
    private List<FilterSubcontractor> filterSubs = new ArrayList<>();

    /**
     * @return the filterSubs
     */
    public List<FilterSubcontractor> getFilterSubs() {
        return filterSubs;
    }

    /**
     * @param filterSubs the filterSubs to set
     */
    public void setFilterSubs(List<FilterSubcontractor> filterSubs) {
        this.filterSubs = filterSubs;
    }
    
    
}
