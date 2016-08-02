/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.TransactionList;
import com.oltranz.sppayfuelportal.model.TransactionSingle;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TransactionBean implements Serializable{
    
    private int branchId;
    private int usId;
    private int deviceId;
    private int productId;
    private int paymentModeId;
    private String status;
    private String date;
    
    private String saveActionName="Save";
    private TransactionList transactionList;
    private TransactionSingle transactionSingle;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{PaymentModeBean}")
    private PaymentModeBean paymentModeBean;
    
    
    
    
    
    public void init() {
        saveActionName="Save";
    }
    

    
    public String transactions(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("deactive");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("active");
        templateBean.setSettingClassName("deactive");
        
        paymentModeBean.paymentModes();
        
        
        try{
            
            
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String startDate=dateFormat.format(currentDate)+"  00:00";
            String endDate=dateFormat.format(currentDate)+"  23:59";
            date=startDate+" - "+endDate;
            
            if(loginBean.getbId()!=0){
                branchId=loginBean.getbId();
            }
            
            String url="http://localhost:8080/PayFuel/TransactionManagementService/transactions/filter";
            
            String  jsonData = "{\n" +
                    "\"branchId\":"+branchId+",\n" +
                    "\"userId\":"+usId+",\n" +
                    "\"deviceId\":"+deviceId+",\n" +
                    "\"productId\":"+productId+",\n" +
                    "\"paymentModeId\":"+paymentModeId+",\n" +
                    "\"status\":\""+status+"\",\n" +
                    "\"date\":\""+date+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_transactions.xhtml?faces-redirect=true";
        
    }
    
    
    
    public void transactionForView(long transactionId){
        transactionById(transactionId);
    }
    
    public void transactionById(long transactionId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/TransactionManagementService/transaction/"+transactionId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionSingle=(TransactionSingle)mapper.readValue(jsonResponse, TransactionSingle.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public String filteredTransactions(){
        
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("deactive");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("active");
        templateBean.setSettingClassName("deactive");
        
        paymentModeBean.paymentModes();
        
        try{
            String url="http://localhost:8080/PayFuel/TransactionManagementService/transactions/filter";
            
            String  jsonData = "{\n" +
                    "\"branchId\":"+branchId+",\n" +
                    "\"userId\":"+usId+",\n" +
                    "\"deviceId\":"+deviceId+",\n" +
                    "\"productId\":"+productId+",\n" +
                    "\"paymentModeId\":"+paymentModeId+",\n" +
                    "\"status\":\""+status+"\",\n" +
                    "\"date\":\""+date+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_transactions.xhtml?faces-redirect=true";
        
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
     * @return the transactionSingle
     */
    public TransactionSingle getTransactionSingle() {
        return transactionSingle;
    }
    
    /**
     * @param transactionSingle the transactionSingle to set
     */
    public void setTransactionSingle(TransactionSingle transactionSingle) {
        this.transactionSingle = transactionSingle;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }
    
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
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
     * @return the deviceId
     */
    public int getDeviceId() {
        return deviceId;
    }
    
    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    
    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }
    
    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    /**
     * @return the paymentModeId
     */
    public int getPaymentModeId() {
        return paymentModeId;
    }
    
    /**
     * @param paymentModeId the paymentModeId to set
     */
    public void setPaymentModeId(int paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    /**
     * @return the paymentModeBean
     */
    public PaymentModeBean getPaymentModeBean() {
        return paymentModeBean;
    }

    /**
     * @param paymentModeBean the paymentModeBean to set
     */
    public void setPaymentModeBean(PaymentModeBean paymentModeBean) {
        this.paymentModeBean = paymentModeBean;
    }

    
    
    
    
}
