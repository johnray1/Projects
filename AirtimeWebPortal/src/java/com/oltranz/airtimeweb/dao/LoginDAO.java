/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.dao;

import com.oltranz.airtimeweb.library.CommonLibrary;
import com.oltranz.airtimeweb.model.LoginModel;
import com.oltranz.airtimeweb.model.RegisterRequest;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
public class LoginDAO {
    
    public static LoginModel validate(String msisdn, String pin)throws Exception{
        
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=dateFormat.format(currentDate);
        String nul=null;
        InetAddress IP=InetAddress.getLocalHost();
        
        String loginUrl="http://localhost:8080/AirtimeRechargeSystemCore/customer/login";
        String  jsonData = "{\n" +
                "\"pin\":\""+pin+"\",\n" +
                "\"msisdn\":\""+msisdn+"\",\n" +
                "\"currentTime\":\""+date+"\",\n" +
                "\"serialNumber\":\""+nul+"\",\n" +
                "\"imei\":\""+nul+"\",\n" +
                "\"osVersion\":\""+IP.toString()+"\"\n" +
                "}";
        
        Response response = CommonLibrary.sendRESTRequest(loginUrl, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        ObjectMapper mapper=new ObjectMapper();
        LoginModel loginModel=(LoginModel)mapper.readValue(jsonResponse, LoginModel.class);
        
        return loginModel;
        
    }
    
    public static LoginModel validateRegister(RegisterRequest registerRequest)throws Exception{
        
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=dateFormat.format(currentDate);
        
        InetAddress IP=InetAddress.getLocalHost();
        ObjectMapper mapper=new ObjectMapper();
        registerRequest.setOtherNames(registerRequest.getfName());
        registerRequest.setOSversion(IP.toString());
        registerRequest.setCurrentTime(date);
        
        String url="http://localhost:8080/AirtimeRechargeSystemCore/customer/register";
        String  jsonData = mapper.writeValueAsString(registerRequest);
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        LoginModel loginModel=(LoginModel)mapper.readValue(jsonResponse, LoginModel.class);
        
        return loginModel;
        
    }
    
}
