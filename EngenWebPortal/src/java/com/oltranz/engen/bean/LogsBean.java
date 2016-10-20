/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.LogList;
import com.oltranz.engen.model.LogSingle;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="LogsBean")
@SessionScoped
public class LogsBean implements Serializable{
    
    private int usId;
    private int actionId;
    private String source;
    private String ip;
    private String date;
    
    
    private LogList logList;
    private LogSingle logSingle;
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    
    
    public void logs(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu_active");
        
        templateBean.actions();
        templateBean.userList();
        try{
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String startDate=dateFormat.format(currentDate)+"  00:00";
            String endDate=dateFormat.format(currentDate)+"  23:59";
            date=startDate+" - "+endDate;
            
            logList(date);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_logs.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(LogsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void filterLogs(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu_active");
        
        try{
            logList(date);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_logs.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(LogsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void logList(String dateIp) throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/LogManagementService/logs/filter";
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
    
    
    
    public void logForView(long logId){
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/LogManagementService/log/"+logId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
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
