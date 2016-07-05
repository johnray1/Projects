/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Action;
import com.oltranz.payfuel.models.ResultObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class ActionManager {
    
    @PersistenceContext
    private  EntityManager em;
    
    public ResultObject getActionList(){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Action.class);
        
        List<Action> actionList=(List<Action>)em.createQuery("SELECT a FROM Action a").getResultList();
        if(actionList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Action in the system");
            resultObject.setStatusCode(500);
        }
        resultObject.setObject(actionList);
        resultObject.setMessage(actionList.size()+" Action were found");
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
}
