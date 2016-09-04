/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.Transaction;
import com.oltranz.airtime.model.TransactionList;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean(name="TransactionBean")
@SessionScoped
public class TransactionBean {
    
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
    
    private Transaction transaction;
    private TransactionList transactionList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    
    public String transactions(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("deactive");
        templateBean.setTransactionClassName("active");
        templateBean.setLogClassName("deactive");
        
        
        
        try{
            currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate=dateFormat.format(currentDate)+"  00:00";
            String endDate=dateFormat.format(currentDate)+"  23:59";
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/webtransactions";
            
            String  jsonData = "{\n" +
                    "\"sender\":\""+""+"\",\n" +
                    "\"receiver\":\""+""+"\",\n" +
                    "\"amount\":"+amount+",\n" +
                    "\"startDate\":\""+startDate+"\",\n" +
                    "\"endDate\":\""+endDate+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
            this.date=null;
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "transaction.xhtml?faces-redirect=true";
    }
    
    public void transactionForView(Transaction tra){
        transaction=tra;
    }
    
    public String filterTransaction(){
        
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
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/webtransactions";
            
            String  jsonData = "{\n" +
                    "\"sender\":\""+sender+"\",\n" +
                    "\"receiver\":\""+receiver+"\",\n" +
                    "\"amount\":"+amount+",\n" +
                    "\"startDate\":\""+startDate+"\",\n" +
                    "\"endDate\":\""+endDate+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "transaction.xhtml?faces-redirect=true";
    }
    
    public void dayTransactions(){
        
        try {
            
            currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate=dateFormat.format(currentDate)+"  00:00";
            String endDate=dateFormat.format(currentDate)+"  23:59";
            amount=0.0;
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/webtransactions";
            
            String  jsonData = "{\n" +
                    "\"sender\":\""+""+"\",\n" +
                    "\"receiver\":\""+""+"\",\n" +
                    "\"amount\":"+amount+",\n" +
                    "\"startDate\":\""+startDate+"\",\n" +
                    "\"endDate\":\""+endDate+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
            this.date=null;
            
        }
        catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void traPerToday(){
        try{
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/todaypurchases";
            
            Response response=CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            
            airtimeToday= response.readEntity(String.class);
            
            if(airtimeToday.equals("null")){
                at=0;
            }
            else{
                at=Double.parseDouble(airtimeToday);
            }
        }
        catch(Exception ex){
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void traPerWeek(){
        try{
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/weeklypurchases";
            
            Response response=CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            
            airtimeWeek= response.readEntity(String.class);
            
            if(airtimeWeek.equals("null")){
                aw=0;
            }
            else{
                aw=Double.parseDouble(airtimeWeek);
            }
        }
        catch(Exception ex){
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }
    
    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
    /**
     * @return the transactionList
     */
    public TransactionList getTransactionList() {
        return transactionList;
    }
    
    /**
     * @param transactionList the transactionList to set
     */
    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
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
    
    
    
    
}
