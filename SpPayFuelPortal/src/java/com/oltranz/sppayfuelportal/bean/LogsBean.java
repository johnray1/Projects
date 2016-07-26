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
    private int usId;
    private int actionId;
    private String source;
    private String ip;
    private String date;
    
    private String saveActionName="Save";
    private LogList logList;
    private LogSingle logSingle;
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public void init() {
        saveActionName="Save";
    }
    
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
            String getBranchUrl="http://localhost:8080/PayFuel/LogManagementService/logs";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            logList=(LogList)mapper.readValue(jsonResponse, LogList.class);
            
            this.usId=0;
            this.actionId=0;
            this.source=null;
            this.ip=null;
            this.date=null;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_logs.xhtml?faces-redirect=true";
        
    }
    
    public void logForView(long logId){
        logById(logId);
    }
    
    public void logById(long logId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/LogManagementService/log/"+logId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            logSingle=(LogSingle)mapper.readValue(jsonResponse, LogSingle.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String filteredLogs(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu_active");
        
        try{
            String url="http://localhost:8080/PayFuel/LogManagementService/logs/filter";
            String  jsonData = "{\n" +
                    "\"userId\":"+usId+",\n" +
                    "\"actionId\":"+actionId+",\n" +
                    "\"source\":\""+source+"\",\n" +
                    "\"ip\":\""+ip+"\",\n" +
                    "\"date\":\""+date+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            logList=(LogList)mapper.readValue(jsonResponse, LogList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_logs.xhtml?faces-redirect=true";
        
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
    
    /**
     * @return the saveActionName
     */
    public String getSaveActionName() {
        return saveActionName;
    }
    
    /**
     * @param saveActionName the saveActionName to set
     */
    public void setSaveActionName(String saveActionName) {
        this.saveActionName = saveActionName;
    }
    
    /**
     * @return the usId
     */
    public int getUsId() {
        return usId;
    }
    
    /**
     * @param usId the usId to set
     */
    public void setUsId(int usId) {
        this.usId = usId;
    }
    
    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }
    
    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }
    
    /**
     * @return the actionId
     */
    public int getActionId() {
        return actionId;
    }
    
    /**
     * @param actionId the actionId to set
     */
    public void setActionId(int actionId) {
        this.actionId = actionId;
    }
    
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
