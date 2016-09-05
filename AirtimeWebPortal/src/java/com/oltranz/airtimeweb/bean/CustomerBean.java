/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import com.oltranz.airtimeweb.library.CommonLibrary;
import com.oltranz.airtimeweb.model.Customer;
import com.oltranz.airtimeweb.model.CustomerSingle;
import java.io.IOException;
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
@ManagedBean(name="CustomerBean")
@SessionScoped
public class CustomerBean {
    
    private String oldpin;
    private String newpin;
    private String repin;
    
    private Boolean faceMessage=false;
    private String msg;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    private Date date;
    
    private Customer customer;
    private CustomerSingle customerSingle;
    
    
    public void customer(String msisdn){
        
        date=new Date();
        
        try{
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/wallettransactions/webwalletbalance/"+msisdn;
            Response response=CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            customerSingle=(CustomerSingle)mapper.readValue(jsonResponse, CustomerSingle.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void customerAccount(){
        
        templateBean.setWelcomeClassName("deactive");
        templateBean.setAccountClassName("active");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try{
            faceMessage=false;
            FacesContext.getCurrentInstance().getExternalContext().redirect("account.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePin(){
        
        templateBean.setWelcomeClassName("deactive");
        templateBean.setAccountClassName("active");
        templateBean.setTransactionClassName("deactive");
        templateBean.setNotificationClassName("deactive");
        
        try{
            
            if(!oldpin.equals(customerSingle.getCustomer().getPin())){
                faceMessage=true;
                msg="wrong current pin";
            }
            
            if(!newpin.equals(repin)){
                faceMessage=true;
                msg="password not match";
            }
            
            if(oldpin.equals(customerSingle.getCustomer().getPin()) && newpin.equals(repin)){
                
                ObjectMapper mapper=new ObjectMapper();
                String url=" http://41.74.172.132:8080/AirtimeRechargeSystem/customer/editCustomer";
                String  jsonData ="{\n" +
                        "\"OSversion\":\""+customerSingle.getCustomer().getOSversion()+"\",\n" +
                        "\"creationTime\":\""+customerSingle.getCustomer().getCreationTime()+"\",\n" +
                        "\"email\":\""+customerSingle.getCustomer().getEmail()+"\",\n" +
                        "\"fName\":\""+customerSingle.getCustomer().getfName()+"\",\n" +
                        "\"imei\":\""+customerSingle.getCustomer().getImei()+"\",\n" +
                        "\"lastUpdateTime\":\""+customerSingle.getCustomer().getLastUpdateTime()+"\",\n" +
                        "\"msisdn\":\""+customerSingle.getCustomer().getMsisdn()+"\",\n" +
                        "\"otherNames\":\""+customerSingle.getCustomer().getOtherNames()+"\",\n" +
                        "\"phoneSerialNumber\":\""+customerSingle.getCustomer().getPhoneSerialNumber()+"\",\n" +
                        "\"pin\":\""+newpin+"\",\n" +
                        "\"status\":\""+customerSingle.getCustomer().isStatus()+"\"\n" +
                        "}";
                
                Response response = CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
                String jsonResponse = response.readEntity(String.class);
                
                faceMessage=true;
                msg="successfully updated";
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("account.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * @return the customerSingle
     */
    public CustomerSingle getCustomerSingle() {
        return customerSingle;
    }
    
    /**
     * @param customerSingle the customerSingle to set
     */
    public void setCustomerSingle(CustomerSingle customerSingle) {
        this.customerSingle = customerSingle;
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
     * @return the oldpin
     */
    public String getOldpin() {
        return oldpin;
    }
    
    /**
     * @param oldpin the oldpin to set
     */
    public void setOldpin(String oldpin) {
        this.oldpin = oldpin;
    }
    
    /**
     * @return the newpin
     */
    public String getNewpin() {
        return newpin;
    }
    
    /**
     * @param newpin the newpin to set
     */
    public void setNewpin(String newpin) {
        this.newpin = newpin;
    }
    
    /**
     * @return the repin
     */
    public String getRepin() {
        return repin;
    }
    
    /**
     * @param repin the repin to set
     */
    public void setRepin(String repin) {
        this.repin = repin;
    }
    
    /**
     * @return the faceMessage
     */
    public Boolean getFaceMessage() {
        return faceMessage;
    }
    
    /**
     * @param faceMessage the faceMessage to set
     */
    public void setFaceMessage(Boolean faceMessage) {
        this.faceMessage = faceMessage;
    }
    
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
    
}
