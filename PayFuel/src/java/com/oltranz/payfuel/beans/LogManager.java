/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Log;
import com.oltranz.payfuel.models.LogFilter;
import com.oltranz.payfuel.models.ResultObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    
    public ResultObject filterLogs(LogFilter logFilter){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Log.class);
        
        String nul="null";
        String and="and";
        String sqlQuery="SELECT l FROM Log l WHERE";
        
        if(logFilter.getUserId()!=0){
            sqlQuery+=" l.userId = :userId";
        }
        
        if(logFilter.getActionId()!=0){
            sqlQuery+=" and l.actionId = :actionId";
        }
        
        if(!logFilter.getSource().equalsIgnoreCase(nul)){
            sqlQuery+=" and l.source = :source";
        }
        
        if(!logFilter.getIp().equalsIgnoreCase(nul)){
            sqlQuery+=" and l.ip = :ip";
        }
        
        Query query = em.createQuery(sqlQuery);
        
        if(logFilter.getUserId()!=0){
            query.setParameter("userId", logFilter.getUserId());
        }
        
        if(logFilter.getActionId()!=0){
            query.setParameter("actionId", logFilter.getActionId());
        }
        
        if(!logFilter.getSource().equalsIgnoreCase(nul)){
            query.setParameter("source", logFilter.getSource());
        }
        
        if(!logFilter.getIp().equalsIgnoreCase(nul)){
            query.setParameter("ip", logFilter.getIp());
        }
        
        List<Log> logList=(List<Log>)query.getResultList();
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
    
}
