/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.library;

import com.oltranz.quickteller.beans.QuickTellerManager;
import com.oltranz.quickteller.models.PaymentCompletedRequest;
import com.oltranz.quickteller.models.PaymentCompletedResponse;
import com.oltranz.quickteller.models.QuickPaymentRes;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.transport.https.CertificateHostnameVerifier;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author JohnRay
 */
public class PaymentLibrary {
    
    
    
    public static PaymentCompletedResponse postPaymentModule(PaymentCompletedRequest pcr,String token){
        
        PaymentCompletedResponse pcres=new PaymentCompletedResponse();
        try {
            ObjectMapper mapper=new ObjectMapper();
            String jsonData=mapper.writeValueAsString(pcr);
            String url="http://localhost:8080/AirtimeRechargeSystemCore/payments/completedPayment";
            Response response=sendPaymentModuleRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST", token);
            String jsonResponse = response.readEntity(String.class);
            pcres=(PaymentCompletedResponse)mapper.readValue(jsonResponse, PaymentCompletedResponse.class);
        }
        catch (IOException ex) {
            Logger.getLogger(QuickTellerManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return pcres;
    }
    
    
    
    public static Response sendPaymentModuleRequest(String url,String requestStr, String MediaType, String method, String token){
        try
        {
            Response response=null;
            Client client =ClientBuilder.newClient();
            WebTarget target =client.target(url);
            switch (method){
                case "POST":
                    response = target.request().header("Content-type", "text/xml").header("Signature", "43AD232FD45FF").header("Token", token).post(Entity.entity(requestStr, MediaType));
                    
                    break;
                    
                case "GET":
                    response = target.request().get();
                    break;
                    
            }
            
            return response;
        }
        catch(Exception e)
        {
            System.out.println("Error send request: "+e.getMessage());
            return null;
        }
    }
    
    
//-----------------------------------------------------Quickteller------------------------------------------------------------    
    
    
    //String testkey="E9300DJLXKJLQJ2993N1190023";// String testUrl="https://pwq.sandbox.interswitchng.com/api/v2/transaction/"+paymentReference+"?isRequestRef=false";
    public static QuickPaymentRes getpaymentConfirmFromQuickteller(String paymentReference){
        
        QuickPaymentRes quickPaymentRes = new QuickPaymentRes();
        try {
            ObjectMapper mapper=new ObjectMapper();
            
            String clientId="airtimeweb.oltranz.com";
            String key="ERTYUIOOHHGF87654323344HGFT";
            String hash=get_SHA_512ForQuickTeller(paymentReference+key);
            
            String liveUrl="https://paywith.quickteller.com/api/v2/transaction/"+paymentReference+"?isRequestRef=false";
            out.print("QUICKTELLER: url:"+liveUrl);
            Response response =sendQuicktellerRequest(liveUrl, clientId, hash);
            String qtjsonRes=response.readEntity(String.class);
            out.print("QUICKTELLER: response:"+qtjsonRes);
            quickPaymentRes=(QuickPaymentRes)mapper.readValue(qtjsonRes, QuickPaymentRes.class);
        }
        catch (IOException ex) {
            Logger.getLogger(QuickTellerManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return quickPaymentRes;
        
    }
    
    public  static String get_SHA_512ForQuickTeller(String passwordToHash){
        String generatedPassword = null;
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(passwordToHash.getBytes());
            
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    public static Response sendQuicktellerRequest(String url, String clientId, String hash){
        try
        {
            //Enable this for debugging on local machine/netbean
            //Client client =ClientBuilder.newClient();
            
            //Enable this before deploying to server
            Client client = buildClient();
            
            WebTarget target =client.target(url);
            Response response = target.request().header("clientid", clientId).header("Hash", hash).get();
            
            return response;
        }
        catch(Exception e)
        {
            System.out.println("Error send request: "+e.getMessage());
            return null;
        }
        
        
    }
    
    private static Client buildClient(){
        try{
            ClientBuilder builder = ClientBuilder.newBuilder();
            
            KeyStore trustStore = loadStore("/home/glassfish/glassfish/domains/domain1/config/quicktellerTruststore.jks","quickteller");
            
            builder.trustStore(trustStore);
            builder.hostnameVerifier(CertificateHostnameVerifier.ALLOW_ALL);
            
            KeyStore keyStore = loadStore("/home/glassfish/glassfish/domains/domain1/config/quicktellerTruststore.jks","quickteller");
            builder.keyStore(keyStore,"quickteller");
            
            Client client = builder.build();
            return client;
        }catch(Exception Ex){
            return null;
        }
    }
    
    private static KeyStore loadStore(String trustStoreFile, String password) throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        store.load(new FileInputStream(trustStoreFile), password.toCharArray());
        return store;
    }
    
    
    
}
