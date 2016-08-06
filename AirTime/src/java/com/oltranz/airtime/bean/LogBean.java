/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.Log;
import com.oltranz.airtime.model.LogList;
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
@ManagedBean(name="LogBean")
@SessionScoped
public class LogBean {
    
    private Log log;
    private LogList logList;
    
    private String a;
    private String date;
    
    
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public String logs(){
        getTemplateBean().setDashboardClassName("omenu");
        getTemplateBean().setUserClassName("omenu");
        getTemplateBean().setCustomerClassName("omenu");
        getTemplateBean().setTransactionClassName("omenu");
        getTemplateBean().setLogClassName("omenu_active");
        try{
            
            String url="http://localhost:8080/AirtimeRechargeSystem/customer/listcustomers";
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            logList=(LogList)mapper.readValue(jsonResponse, LogList.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return "log.xhtml?faces-redirect=true";
    }
    
    
    
    public void logForView(Log lo){
        log=lo;
    }
    
    
    
    
    
    
    public void filterLog(){
        
    }
    
    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }
    
    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
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
     * @return the a
     */
    public String getA() {
        return a;
    }
    
    /**
     * @param a the a to set
     */
    public void setA(String a) {
        this.a = a;
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
    
    /**
     * @return the log
     */
    public Log getLog() {
        return log;
    }
    
    /**
     * @param log the log to set
     */
    public void setLog(Log log) {
        this.log = log;
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
    
    
    
    
}
