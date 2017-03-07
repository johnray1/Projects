/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.dao;



import com.oltranz.ignite.library.CommonLibrary;
import com.oltranz.ignite.model.UserDetails;
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
        
        String url="http://localhost:8080/UserManager/UserManagementService/user/authenticate";
        String  jsonData = "{\n";
        jsonData+="\"email\":\""+email+"\",\n" ;
        jsonData+="\"password\":\""+password+"\"\n" ;
        jsonData+="}";
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,null, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        UserDetails userDetails=(UserDetails)mapper.readValue(jsonResponse, UserDetails.class);
        
        return userDetails;
    }
}
