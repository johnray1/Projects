/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.airtime.dao;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.LoginModel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
public class LoginDAO {
    
    public static LoginModel validate(String email, String password)throws Exception{
        
        String loginUrl="http://41.74.172.132:8080/AirtimeRechargeSystem/users/login";
        String  jsonData = "{\n";
        jsonData+="\"email\":\""+email+"\",\n" ;
        jsonData+="\"password\":\""+password+"\"\n" ;
        jsonData+="}";
        
        Response response = CommonLibrary.sendRESTRequest(loginUrl, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        LoginModel loginModel=(LoginModel)mapper.readValue(jsonResponse, LoginModel.class);
        
        return loginModel;
    }
    
}
