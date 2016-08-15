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
    
    
    
    
}
