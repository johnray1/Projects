/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.PaymentModeList;
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
@ManagedBean(name="PaymentModeBean")
@SessionScoped
public class PaymentModeBean {
    
    private PaymentModeList paymentModeList;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    public void paymentModes(){
        
        int userId=loginBean.getUserId();
        System.out.println(loginBean.getUserId());
        try{
            String url="http://localhost:8080/PayFuel/PaymentModeManagementService/paymentmodes/"+userId;
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            paymentModeList=(PaymentModeList)mapper.readValue(jsonResponse, PaymentModeList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @return the paymentModeList
     */
    public PaymentModeList getPaymentModeList() {
        return paymentModeList;
    }

    /**
     * @param paymentModeList the paymentModeList to set
     */
    public void setPaymentModeList(PaymentModeList paymentModeList) {
        this.paymentModeList = paymentModeList;
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
    
    
    
}
