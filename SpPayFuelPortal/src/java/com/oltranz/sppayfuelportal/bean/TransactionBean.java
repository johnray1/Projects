/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.TransactionList;
import com.oltranz.sppayfuelportal.model.TransactionSingle;
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
    
    private TransactionList transactionList;
    
    private TransactionSingle transactionSingle;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    public String transactions(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu_active");
        templateBean.setLogsClassName("omenu");
        
        int userId=loginBean.getUserId();
        System.out.println(loginBean.getUserId());
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/AndroidWebService/pos/transactions/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionList=(TransactionList)mapper.readValue(jsonResponse, TransactionList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_transactions.xhtml";
        
    }
    
    public void transactionById(int transactionId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/AndroidWebService/pos/transaction/"+transactionId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            transactionSingle=(TransactionSingle)mapper.readValue(jsonResponse, TransactionSingle.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
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
    
    
}
