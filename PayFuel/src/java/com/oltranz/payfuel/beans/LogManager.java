/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Log;
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
public class LogManager {
    
    @PersistenceContext
    private  EntityManager em;
    
    public ResultObject getLogList(){
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Log.class);
        
        List<Log> logList=(List<Log>)em.createQuery("SELECT l FROM Log l").getResultList();
        if(logList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Log in the system");
            resultObject.setStatusCode(500);
        }
        resultObject.setObject(logList);
        resultObject.setMessage(logList.size()+" Log were found");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getLog(long logId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Log.class);
        
        Log log=em.find(Log.class, logId);
        if(log!=null){
            resultObject.setObject(log);
            resultObject.setMessage("Logs Found Successfully");
            resultObject.setStatusCode(100);
        }
        else{
            resultObject.setObject(null);
            resultObject.setMessage("There are no Log in the system");
            resultObject.setStatusCode(500);
        }
        return resultObject;
    }
    
    
}
