/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.models;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class AssignedPumpModelList {
    
    @JsonProperty("assignPumpModel")
    private List<AssignedPumpModel>  assignPumpModel; 

    /**
     * @return the assignPumpModel
     */
    public List<AssignedPumpModel> getAssignPumpModel() {
        return assignPumpModel;
    }

    /**
     * @param assignPumpModel the assignPumpModel to set
     */
    public void setAssignPumpModel(List<AssignedPumpModel> assignPumpModel) {
        this.assignPumpModel = assignPumpModel;
    }

    
    
   
   
    
    
}
