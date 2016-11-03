/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.Customer;
import com.oltranz.airtime.model.CustomerList;
import com.oltranz.airtime.model.CustomerSingle;
import java.io.Serializable;
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
@ManagedBean(name="CustomerBean")
@SessionScoped
public class CustomerBean implements Serializable{
    
    private String customerNo;
    private Customer customer;
    
    private CustomerSingle customerSingle;
    private CustomerList customerList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public String customers(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("active");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLogClassName("deactive");
        
        try{
            
            String url="http://localhost:8080/AirtimeRechargeSystemCore/customer/listcustomers";
            
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            
            customerList=(CustomerList)mapper.readValue(jsonResponse, CustomerList.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return "customer.xhtml?faces-redirect=true";
    }
    
    
    public void customerForView(String msisdn){
        customerById(msisdn);
    }
    
    
    public void customerById(String msisdn){
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
    
    public String activeDeactive(Customer customer){
        
        boolean status=true;
        
        if(customer.isStatus()){
            status=false;
        }
        
        String url="http://41.74.172.132:8080/AirtimeRechargeSystem/customer/editcustomer";
        String  jsonData ="{\n" +
                "\"OSversion\":\""+customer.getOSversion()+"\",\n" +
                "\"creationTime\":\""+customer.getCreationTime()+"\",\n" +
                "\"email\":\""+customer.getEmail()+"\",\n" +
                "\"fName\":\""+customer.getfName()+"\",\n" +
                "\"imei\":\""+customer.getImei()+"\",\n" +
                "\"lastUpdateTime\":\""+customer.getLastUpdateTime()+"\",\n" +
                "\"msisdn\":\""+customer.getMsisdn()+"\",\n" +
                "\"otherNames\":\""+customer.getOtherNames()+"\",\n" +
                "\"phoneSerialNumber\":\""+customer.getPhoneSerialNumber()+"\",\n" +
                "\"pin\":\""+customer.getPin()+"\",\n" +
                "\"status\":"+status+"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        return customers();
        
    }
    
    public void totalCustomers(){
        try{
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/customer/counting";
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            customerNo=response.readEntity(String.class);
        }
        catch(Exception ex){
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the customerList
     */
    public CustomerList getCustomerList() {
        return customerList;
    }
    
    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(CustomerList customerList) {
        this.customerList = customerList;
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
     * @return the customerNo
     */
    public String getCustomerNo() {
        return customerNo;
    }
    
    /**
     * @param customerNo the customerNo to set
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    
    
    
}
