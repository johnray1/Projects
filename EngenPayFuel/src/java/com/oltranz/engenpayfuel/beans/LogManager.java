/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Log;
import com.oltranz.engenpayfuel.models.LogFilter;
import com.oltranz.engenpayfuel.models.ResultObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        try{
            String dateIp=logFilter.getDate();
            
            String[] output = dateIp.split("-");
            String from=output[0];
            String to=output[1];
            String dateFrom = from.replace('/', '-');
            String dateTo = to.replace('/', '-');
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            Date fromDate=dateFormat.parse(dateFrom);
            Date toDate=dateFormat.parse(dateTo);
            
            String nul="null";
            String sqlQuery="SELECT l FROM Log l WHERE";
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            if(logFilter.getUserId()!=0){
                sqlQuery+=" l.userId = :userId";
            }
            
            if( (logFilter.getUserId()!=0) && ((logFilter.getActionId()!=0)||(!logFilter.getSource().equalsIgnoreCase(nul))||(!logFilter.getIp().equalsIgnoreCase(nul))||(!logFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(logFilter.getActionId()!=0){
                sqlQuery+=" l.actionId = :actionId";
            }
            
            if( (logFilter.getActionId()!=0) && ((!logFilter.getSource().equalsIgnoreCase(nul))||(!logFilter.getIp().equalsIgnoreCase(nul))||(!logFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(!logFilter.getSource().equalsIgnoreCase(nul)){
                sqlQuery+=" l.source = :source";
            }
            
            if((!logFilter.getSource().equalsIgnoreCase(nul)) && ( (!logFilter.getIp().equalsIgnoreCase(nul))||(!logFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(!logFilter.getIp().equalsIgnoreCase(nul)){
                sqlQuery+=" l.ip = :ip";
            }
            
            if((!logFilter.getIp().equalsIgnoreCase(nul)) && ( (!logFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            if(!logFilter.getDate().equalsIgnoreCase(nul)){
                sqlQuery+=" l.datetime BETWEEN :fromDate AND :toDate";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
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
            
            if(!logFilter.getDate().equalsIgnoreCase(nul)){
                query.setParameter("fromDate", fromDate);
                query.setParameter("toDate", toDate);
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
        catch(ParseException pe){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
    }
    
}
