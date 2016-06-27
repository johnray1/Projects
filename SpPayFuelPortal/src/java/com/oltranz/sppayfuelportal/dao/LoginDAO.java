/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.dao;


import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.UserDetails;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;



/**
 *
 * @author John
 */
public class LoginDAO {
    
    public static UserDetails validate(String email, String password)throws IOException{
        
        String loginUrl="http://localhost:8080/PayFuel/UserManagementService/user/authenticate";
        String  jsonData = "{\n";
        jsonData+="\"email\":\""+email+"\",\n" ;
        jsonData+="\"password\":\""+password+"\"\n" ;
        jsonData+="}";
        
        Response response = CommonLibrary.sendRESTRequest(loginUrl, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        UserDetails userDetails=(UserDetails)mapper.readValue(jsonResponse, UserDetails.class);
        
        return userDetails;
    }
}
