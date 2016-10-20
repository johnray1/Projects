/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import com.oltranz.airtimeweb.library.CommonLibrary;
import com.oltranz.airtimeweb.model.TopUpBean;
import com.oltranz.airtimeweb.model.TopUpRequest;
import com.oltranz.airtimeweb.model.TopUpResponse;
import com.oltranz.airtimeweb.model.TransactionList;
import com.oltranz.airtimeweb.model.WalletBalance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author John
 */
@ManagedBean(name="WelcomeBean")
@SessionScoped
public class WelcomeBean {
    
    private String msisdn,email,username;
    
    private String phone,phone1,phone2,phone3;
    private Double amount,amount1,amount2,amount3;
    
    private Date date;
    
    private WalletBalance walletBalance;
    private TransactionList latestTransactionList;
    
    private TopUpResponse topUpResponse;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    private HttpSession session = SessionBean.getSession();
    
    public void welcomes(){
        try {
            templateBean.setWelcomeClassName("active");
            templateBean.setAccountClassName("deactive");
            templateBean.setTransactionClassName("deactive");
            templateBean.setNotificationClassName("deactive");
            
            customerWalletBalance();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
        }
        catch (Exception ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void customerWalletBalance(){
        String token=(String) session.getAttribute("token");
        date=new Date();
        
        try{
            String url="http://localhost:8080/AirtimeRechargeSystemCore/wallets/CustomerWalletBalance";
            Response response=CommonLibrary.sendAirRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET", token );
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            walletBalance=(WalletBalance)mapper.readValue(jsonResponse, WalletBalance.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    
//Recharge Box Data
    public void confirm(){
        
    }
    
    public void cancel(){
        
        this.phone=null;this.amount=null;
        this.phone1=null; this.amount1=null;
        this.phone2=null; this.amount2=null;
        this.phone3=null; this.amount3=null;
    }
    
    public void approveOne(){
        try {
            String token=(String) session.getAttribute("token");
            TopUpBean tb=new TopUpBean(); tb.setMsisdn(phone); tb.setAmount(amount);
            
            List<TopUpBean> tbl=new ArrayList<>(); tbl.add(tb);
            
            TopUpRequest tr=new TopUpRequest();
            tr.setDestinations(tbl);
            
            ObjectMapper mapper=new ObjectMapper();
            String url="http://localhost:8080/AirtimeRechargeSystemCore/airtimetopup/topup";
            String jsonData=mapper.writeValueAsString(tr);
            
            Response response=CommonLibrary.sendAirRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST", token);
            String jsonResponse = response.readEntity(String.class);
            topUpResponse=(TopUpResponse)mapper.readValue(jsonResponse, TopUpResponse.class);
            
            
            
        }
        catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void approveMany(){
        try {
            String token=(String) session.getAttribute("token");
            TopUpBean tb=new TopUpBean(); tb.setMsisdn(phone); tb.setAmount(amount);
            TopUpBean tb1=new TopUpBean(); tb1.setMsisdn(phone1); tb1.setAmount(amount1);
            TopUpBean tb2=new TopUpBean(); tb2.setMsisdn(phone2); tb2.setAmount(amount2);
            TopUpBean tb3=new TopUpBean();  tb3.setMsisdn(phone3); tb3.setAmount(amount3);
            
            List<TopUpBean> tbl=new ArrayList<>();
            if((!phone1.equals(""))&&(amount1!=null)){
                tbl.add(tb1);
            }
            if((!phone2.equals(""))&&(amount2!=null)){
                tbl.add(tb2);
            }
            if((!phone3.equals(""))&&(amount3!=null)){
                tbl.add(tb3);
            }
            
            TopUpRequest tr=new TopUpRequest(); tr.setDestinations(tbl);
            
            ObjectMapper mapper=new ObjectMapper();
            String url="http://localhost:8080/AirtimeRechargeSystemCore/airtimetopup/topup";
            String jsonData=mapper.writeValueAsString(tr);
            
            Response response=CommonLibrary.sendAirRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST", token);
            String jsonResponse = response.readEntity(String.class);
            
            topUpResponse=(TopUpResponse)mapper.readValue(jsonResponse, TopUpResponse.class);
            
            
        }
        catch (IOException ex) {
            Logger.getLogger(WelcomeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void approveFav(){
        TopUpBean tb=new TopUpBean();
        List<TopUpBean> tbl=new ArrayList<>();
        TopUpRequest tr=new TopUpRequest();
    }
    
    
//Latest 10 Transaction Box Data
    public void latestTransaction(){
        String token=(String) session.getAttribute("token");
        date=new Date();
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            
            String url="http://localhost:8080/AirtimeRechargeSystemCore/airtimetopup/getlatest/10";
            Response response=CommonLibrary.sendAirRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET", token);
            String jsonResponse = response.readEntity(String.class);
            
            topUpResponse=(TopUpResponse)mapper.readValue(jsonResponse, TopUpResponse.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * @return the phone1
     */
    public String getPhone1() {
        return phone1;
    }
    
    /**
     * @param phone1 the phone1 to set
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    
    /**
     * @return the phone2
     */
    public String getPhone2() {
        return phone2;
    }
    
    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
    
    /**
     * @return the phone3
     */
    public String getPhone3() {
        return phone3;
    }
    
    /**
     * @param phone3 the phone3 to set
     */
    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }
    
    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    /**
     * @return the amount1
     */
    public Double getAmount1() {
        return amount1;
    }
    
    /**
     * @param amount1 the amount1 to set
     */
    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }
    
    /**
     * @return the amount2
     */
    public Double getAmount2() {
        return amount2;
    }
    
    /**
     * @param amount2 the amount2 to set
     */
    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }
    
    /**
     * @return the amount3
     */
    public Double getAmount3() {
        return amount3;
    }
    
    /**
     * @param amount3 the amount3 to set
     */
    public void setAmount3(Double amount3) {
        this.amount3 = amount3;
    }
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
     * @return the latestTransactionList
     */
    public TransactionList getLatestTransactionList() {
        return latestTransactionList;
    }
    
    /**
     * @param latestTransactionList the latestTransactionList to set
     */
    public void setLatestTransactionList(TransactionList latestTransactionList) {
        this.latestTransactionList = latestTransactionList;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }
    
    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }
    
    /**
     * @return the walletBalance
     */
    public WalletBalance getWalletBalance() {
        return walletBalance;
    }
    
    /**
     * @param walletBalance the walletBalance to set
     */
    public void setWalletBalance(WalletBalance walletBalance) {
        this.walletBalance = walletBalance;
    }

    /**
     * @return the topUpResponse
     */
    public TopUpResponse getTopUpResponse() {
        return topUpResponse;
    }

    /**
     * @param topUpResponse the topUpResponse to set
     */
    public void setTopUpResponse(TopUpResponse topUpResponse) {
        this.topUpResponse = topUpResponse;
    }
    
    
    
}
