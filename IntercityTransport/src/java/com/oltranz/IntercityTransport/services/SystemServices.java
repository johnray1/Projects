/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.beans.InitialData;
import com.oltranz.IntercityTransport.library.Crypto;
import com.oltranz.IntercityTransport.models.AccessTokenWithMessage;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransporter.utils.CommonLibrary;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author manzi
 */
@Stateless
@Path("SystemServices")
public class SystemServices {
    
    @EJB
            InitialData InitialDataEJB;
    
    @POST
    @Path("initializeSystemData")
    public String initializeSystemData() {
        
        ResultObject result= InitialDataEJB.Initialise();
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("encrypt")
    @Consumes({"text/html", "application/json"})
    public String encrypt(String toEncrypt) {
        final String iv = "0123456789123456"; // This has to be 16 characters
        
        final String secretKey = "0123456789123456";
        
        Crypto crypto= new Crypto();
        String encrypted=crypto.encrypt(toEncrypt,iv,secretKey);
        
        return encrypted;
    }
    
    @POST
    @Path("decrypt")
    @Consumes({"application/xml", "application/json"})
    public String decrypt(String toDecrypt) {
        Crypto crypto= new Crypto();
        String decrypted=crypto.encrypt(toDecrypt,"test","test");
        
        return decrypted;
    }
    
    @POST
    @Path("test")
    @Consumes({"application/xml", "application/json"})
    public String decrypt() {
         try{
        ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String url="http://41.74.172.132:8080/IntercityTransport/MembershipServices/user/authenticate";
        String topost="{\n" +
                "\"password\":\"pass123\"  \n" +
                ",\"phoneNumber\":\"0788636644\"\n" +
                "}";
        Response response= CommonLibrary.sendRESTRequest(url, topost, MediaType.APPLICATION_JSON, "POST");
        
        String res= response.readEntity(String.class);
        
         AccessTokenWithMessage token= mapper.readValue(res,AccessTokenWithMessage.class);
            
        
        
        return "got it";
         }catch(Exception ex){
            return "";
        }
    }
}
