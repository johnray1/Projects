/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.LogList;
import com.oltranz.sppayfuelportal.model.LogSingle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="LogsBean")
@SessionScoped
public class LogsBean {
    
    private LogList logList;
    private LogSingle logSingle;
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public String logs(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu_active");
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/AndroidWebService/logs";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            logList=(LogList)mapper.readValue(jsonResponse, LogList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_logs.xhtml";
        
    }
    
    
    public void logById(int logId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/AndroidWebService/log/"+logId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            logSingle=(LogSingle)mapper.readValue(jsonResponse, LogSingle.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @return the logList
     */
    public LogList getLogList() {
        return logList;
    }

    /**
     * @param logList the logList to set
     */
    public void setLogList(LogList logList) {
        this.logList = logList;
    }

    /**
     * @return the templateBean
     */
    public TemplateBean getTemplateBean() {
        return templateBean;
    }

    /**
     * @param templateBean the templateBean to set
     */
    public void setTemplateBean(TemplateBean templateBean) {
        this.templateBean = templateBean;
    }

    /**
     * @return the logSingle
     */
    public LogSingle getLogSingle() {
        return logSingle;
    }

    /**
     * @param logSingle the logSingle to set
     */
    public void setLogSingle(LogSingle logSingle) {
        this.logSingle = logSingle;
    }
    
    
    
}
