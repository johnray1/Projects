/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.TransactionDetailsModel;
import com.oltranz.engen.model.TransactionList;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
    
    private TransactionDetailsModel transactionDetailsModel;
    private TransactionList transactionList;
    
    
    HttpSession session = SessionBean.getSession();
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    
    public void transactions(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu_active");
        templateBean.setSettingClassName("omenu");
        
        templateBean.branchList();
        templateBean.userList();
        templateBean.deviceList();
        templateBean.productList();
        templateBean.paymentModes();
        try{
            
            int braId=(int) session.getAttribute("branchId");
            String getUrl="http://localhost:8080/EngenPayFuel/TransactionManagementService/transactions/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_transactions.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void filteredTransactions(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu_active");
        templateBean.setSettingClassName("omenu");
        
        try{
            transactionList(date,branchId);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_transactions.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void transactionList(String dateIp,int bIdIp) throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/TransactionManagementService/transactions/filter";
        
        String  jsonData = "{\n" +
                "\"branchId\":"+bIdIp+",\n" +
                "\"userId\":"+usId+",\n" +
                "\"deviceId\":"+deviceId+",\n" +
                "\"productId\":"+productId+",\n" +
                "\"paymentModeId\":"+paymentModeId+",\n" +
                "\"status\":\""+status+"\",\n" +
                "\"date\":\""+dateIp+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        ObjectMapper mapper=new ObjectMapper();
        transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
    }
    
    
    
    public void transactionForView(TransactionDetailsModel tdm){
        
        transactionDetailsModel=tdm;
        
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
     * @return the transactionDetailsModel
     */
    public TransactionDetailsModel getTransactionDetailsModel() {
        return transactionDetailsModel;
    }

    /**
     * @param transactionDetailsModel the transactionDetailsModel to set
     */
    public void setTransactionDetailsModel(TransactionDetailsModel transactionDetailsModel) {
        this.transactionDetailsModel = transactionDetailsModel;
    }
    
    
    
}
