/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import com.oltranz.airtimeweb.library.CommonLibrary;
import com.oltranz.airtimeweb.model.PaymentInitResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author manzi
 */
@ManagedBean(name="RechargeBean")
@SessionScoped
public class RechargeBean {
    
    private String txInitId;
    private String customer;
    private String email;
    private Integer amount;
    private Integer qamount;
    private Boolean addAmountVisible=true;
    private Boolean checkoutVisible;
    
    private HttpSession session = SessionBean.getSession();
    private String tokenname;
    
    public void cancel(){
        this.checkoutVisible=false;
        this.addAmountVisible=true;
    }
    
    public String createReqRef(String msisdn,double amt){
        try{
            String token=(String) getSession().getAttribute("token");
            String url="http://localhost:8080/QuickTeller/PaymentInitService/init";
            String  jsonData = "{\n" +
                    "\"paymentSPId\":\""+3847+"\",\n" +
                    "\"paymentTypeId\":\""+4+"\",\n" +
                    "\"amount\":\""+amt+"\",\n" +
                    "\"msisdn\":\""+msisdn+"\"\n" +
                    "}";
            
            Response response = CommonLibrary.sendAirRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST", token);
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            PaymentInitResponse paymentInitResponse=(PaymentInitResponse)mapper.readValue(jsonResponse, PaymentInitResponse.class);
            
            return paymentInitResponse.getInitUid();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return ex.getLocalizedMessage();
        }
    }
    
    
    /**
     * @return the txInitId
     */
    public String getTxInitId() {
        return txInitId;
    }
    
    /**
     * @param txInitId the txInitId to set
     */
    public void setTxInitId(String txInitId) {
        this.txInitId = txInitId;
    }
    
    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
        this.qamount=amount*100;
        this.email=(String) getSession().getAttribute("email");
        this.customer=(String) getSession().getAttribute("msisdn");
        this.txInitId=createReqRef(customer,amount);
        this.tokenname=(String) getSession().getAttribute("token");
        this.checkoutVisible=true;
        this.addAmountVisible=false;
    }
    
    /**
     * @return the addAmountVisible
     */
    public Boolean getAddAmountVisible() {
        return addAmountVisible;
    }
    
    /**
     * @param addAmountVisible the addAmountVisible to set
     */
    public void setAddAmountVisible(Boolean addAmountVisible) {
        this.addAmountVisible = addAmountVisible;
    }
    
    /**
     * @return the checkoutVisible
     */
    public Boolean getCheckoutVisible() {
        return checkoutVisible;
    }
    
    /**
     * @param checkoutVisible the checkoutVisible to set
     */
    public void setCheckoutVisible(Boolean checkoutVisible) {
        this.checkoutVisible = checkoutVisible;
    }
    
    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }
    
    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
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
     * @return the qamount
     */
    public Integer getQamount() {
        return qamount;
    }
    
    /**
     * @param qamount the qamount to set
     */
    public void setQamount(Integer qamount) {
        this.qamount = qamount;
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
     * @return the tokenname
     */
    public String getTokenname() {
        return tokenname;
    }

    /**
     * @param tokenname the tokenname to set
     */
    public void setTokenname(String tokenname) {
        this.tokenname = tokenname;
    }
    
    
    
    
}
