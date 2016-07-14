/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.ActionList;
import com.oltranz.sppayfuelportal.model.BranchList;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="ActionBean")
@SessionScoped
public class ActionBean implements Serializable{
    
    private ActionList actionList;
    
    
    public void actions(){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/ActionManagementService/actions";
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            actionList=(ActionList)mapper.readValue(jsonResponse, ActionList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @return the actionList
     */
    public ActionList getActionList() {
        return actionList;
    }

    /**
     * @param actionList the actionList to set
     */
    public void setActionList(ActionList actionList) {
        this.actionList = actionList;
    }
    
}
