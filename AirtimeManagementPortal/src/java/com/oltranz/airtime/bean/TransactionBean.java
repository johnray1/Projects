/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.TopUpListFilteredRequest;
import com.oltranz.airtime.model.TopUpListFilteredResponse;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@ManagedBean(name="TransactionBean")
@SessionScoped
public class TransactionBean implements Serializable{
    
    private String id;
    private String sender;
    private String receiver;
    private double amount;
    private String date;
    private Date currentDate;
    
    private String airtimeToday;
    private String airtimeWeek;
    
    private double at;
    private double aw;
    
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    private TopUpListFilteredResponse topUpListFilteredResponse;
    
    
    public void transactions(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("deactive");
        templateBean.setTransactionClassName("active");
        templateBean.setLogClassName("deactive");
        
        
        
        try{
            currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate=dateFormat.format(currentDate)+"  00:00:00";
            String endDate=dateFormat.format(currentDate)+"  23:59:59";
            
            TopUpListFilteredRequest tfr=new TopUpListFilteredRequest();
            tfr.setStartDate(startDate);
            tfr.setEndDate(endDate);
            
            getAirtimeTransaction(tfr);
            
            this.date=null;this.sender=null;this.receiver=null;
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("transaction.xhtml");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
     public void filterTransaction(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("deactive");
        templateBean.setTransactionClassName("active");
        templateBean.setLogClassName("deactive");
        
        
        try{
            String dateIp=date;
            String[] output = dateIp.split("-");
            String start=output[0];
            String end=output[1];
            String startDate = start.replace('/', '-');
            String endDate = end.replace('/', '-');
            
            TopUpListFilteredRequest tfr=new TopUpListFilteredRequest();
            tfr.setStartDate(startDate);
            tfr.setEndDate(endDate);
            tfr.setSource(sender);
            tfr.setDestination(receiver);
            
            getAirtimeTransaction(tfr);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("transaction.xhtml");
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public void getAirtimeTransaction(TopUpListFilteredRequest tfr) throws IOException{
        
        String url="http://localhost:8080/AirtimeRechargeSystemCore/airtimetopup/getAirtimeTransactions";
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tfr);
        
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        topUpListFilteredResponse=(TopUpListFilteredResponse)mapper.readValue(jsonResponse, TopUpListFilteredResponse.class);
        
    }
    
    
    
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }
    
    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
    
    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }
    
    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
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
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }
    
    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    /**
     * @return the airtimeToday
     */
    public String getAirtimeToday() {
        return airtimeToday;
    }
    
    /**
     * @param airtimeToday the airtimeToday to set
     */
    public void setAirtimeToday(String airtimeToday) {
        this.airtimeToday = airtimeToday;
    }
    
    /**
     * @return the airtimeWeek
     */
    public String getAirtimeWeek() {
        return airtimeWeek;
    }
    
    /**
     * @param airtimeWeek the airtimeWeek to set
     */
    public void setAirtimeWeek(String airtimeWeek) {
        this.airtimeWeek = airtimeWeek;
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
     * @return the at
     */
    public double getAt() {
        return at;
    }
    
    /**
     * @param at the at to set
     */
    public void setAt(double at) {
        this.at = at;
    }
    
    /**
     * @return the aw
     */
    public double getAw() {
        return aw;
    }
    
    /**
     * @param aw the aw to set
     */
    public void setAw(double aw) {
        this.aw = aw;
    }
    
    /**
     * @return the topUpListFilteredResponse
     */
    public TopUpListFilteredResponse getTopUpListFilteredResponse() {
        return topUpListFilteredResponse;
    }
    
    /**
     * @param topUpListFilteredResponse the topUpListFilteredResponse to set
     */
    public void setTopUpListFilteredResponse(TopUpListFilteredResponse topUpListFilteredResponse) {
        this.topUpListFilteredResponse = topUpListFilteredResponse;
    }
    
    
    
    
}
