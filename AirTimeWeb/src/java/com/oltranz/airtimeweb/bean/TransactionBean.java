/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import com.oltranz.airtimeweb.library.CommonLibrary;
import com.oltranz.airtimeweb.model.Transaction;
import com.oltranz.airtimeweb.model.TransactionList;
import java.io.IOException;
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
@ManagedBean(name="TransactionBean")
@SessionScoped
public class TransactionBean {
    
    private String msisdn;
    private String date;
    private Date currentDate;
    
    private Transaction transaction;
    private TransactionList airtimeTransactionList;
    private TransactionList walletTransactionList;
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    

//------------------------------------------Customer Transaction------------------------------------------------------------- 
    public void customerTransactions(){
        
        templateBean.setWelcomeClassName("omenu");
        templateBean.setAccountClassName("omenu");
        templateBean.setTransactionClassName("omenu_active");
        templateBean.setNotificationClassName("omenu");
        
        try {
            customerCreditTransactions();
            customerDebitTransactions();
            FacesContext.getCurrentInstance().getExternalContext().redirect("transaction.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void customerCreditTransactions() throws IOException{
        
       
            currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String from=dateFormat.format(currentDate);
            String to=dateFormat.format(currentDate)+"  23:59";
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/walletcredits";
            String  jsonData = "{\n" +
                    "\"from\":\""+from+"\",\n" +
                    "\"to\":\""+to+"\",\n" +
                    "\"msisdn\":\""+msisdn+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            walletTransactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
       
    }
    
    public void customerDebitTransactions() throws IOException{
        
       
            currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String from=dateFormat.format(currentDate);
            String to=dateFormat.format(currentDate)+"  23:59";
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/walletdebits";
            String  jsonData = "{\n" +
                    "\"from\":\""+from+"\",\n" +
                    "\"to\":\""+to+"\",\n" +
                    "\"msisdn\":\""+msisdn+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            airtimeTransactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
       
    }
    
    public void transactionForView(Transaction tra){
        transaction=tra;
    }
    
    
//-----------------------------------------------filter------------------------------------------------------------------------
    public void filterTransactions(){
        
        templateBean.setWelcomeClassName("omenu");
        templateBean.setAccountClassName("omenu");
        templateBean.setTransactionClassName("omenu_active");
        templateBean.setNotificationClassName("omenu");
        
        try {
            filterCreditTransactions();
            filterDebitTransactions();
            FacesContext.getCurrentInstance().getExternalContext().redirect("transaction.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void filterCreditTransactions(){
        
        
        try {
            String dateIp=date;
            String[] output = dateIp.split("-");
            String start=output[0];
            String end=output[1];
            String from = start.replace('/', '-');
            String to = end.replace('/', '-');
            
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/walletcredits";
            String  jsonData = "{\n" +
                    "\"from\":\""+from+"\",\n" +
                    "\"to\":\""+to+"\",\n" +
                    "\"msisdn\":\""+msisdn+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            
            walletTransactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
        }
        catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void filterDebitTransactions(){
        
        
        try {
            String dateIp=date;
            String[] output = dateIp.split("-");
            String start=output[0];
            String end=output[1];
            String from = start.replace('/', '-');
            String to = end.replace('/', '-');
            
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/walletdebits";
            String  jsonData = "{\n" +
                    "\"from\":\""+from+"\",\n" +
                    "\"to\":\""+to+"\",\n" +
                    "\"msisdn\":\""+msisdn+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            
            airtimeTransactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
            
        }
        catch (IOException ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }
    
    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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
     * @return the airtimeTransactionList
     */
    public TransactionList getAirtimeTransactionList() {
        return airtimeTransactionList;
    }
    
    /**
     * @param airtimeTransactionList the airtimeTransactionList to set
     */
    public void setAirtimeTransactionList(TransactionList airtimeTransactionList) {
        this.airtimeTransactionList = airtimeTransactionList;
    }
    
    /**
     * @return the walletTransactionList
     */
    public TransactionList getWalletTransactionList() {
        return walletTransactionList;
    }
    
    /**
     * @param walletTransactionList the walletTransactionList to set
     */
    public void setWalletTransactionList(TransactionList walletTransactionList) {
        this.walletTransactionList = walletTransactionList;
    }
    
    
    
}
