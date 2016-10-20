/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.beans;

import com.oltranz.quickteller.entities.PaymentInit;
import com.oltranz.quickteller.models.PaymentInitRequest;
import com.oltranz.quickteller.models.PaymentInitResponse;
import com.oltranz.quickteller.models.SimpleStatus;
import static java.lang.System.out;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JohnRay
 */
@Stateless
public class PaymentInitManager {
    
    @PersistenceContext
    private EntityManager em;
    
    public PaymentInitResponse initializePayment(PaymentInitRequest request){
        PaymentInitResponse paymentInitResponse=new PaymentInitResponse();
        SimpleStatus status=new SimpleStatus();
        
        try{
            PaymentInit payment=new PaymentInit();
            payment.setInitUid(UUID.randomUUID().toString().replace("-",""));
            payment.setPaymentSpId(request.getPaymentSPId());
            payment.setPaymentTypeId(request.getPaymentTypeId());
            payment.setAmount(request.getAmount());
            payment.setMsisdn(request.getMsisdn());
            em.persist(payment);
            em.flush();
            
            status.setStatusCode("401");
            status.setStatusDesc("successFully Initiated");
            
            paymentInitResponse=new PaymentInitResponse();
            paymentInitResponse.setInitUid(payment.getInitUid());
            paymentInitResponse.setStatus(status);
            return paymentInitResponse;
        }
        catch(Exception ex){
            status.setStatusCode("500");
            status.setStatusDesc("An error occured, try again and please contact administrator about the same");
            paymentInitResponse.setInitUid(null);
            
            ex.printStackTrace(out);
            
            return paymentInitResponse;
        }
    }
    
    
    
}






//
//    public int getCustomerSession(String token){
//
//        ObjectMapper mapper=new ObjectMapper();
//        String url="http://localhost:8080/AirtimeRechargeSystemCore/customer/checkTokenValidity";
//        Response response = sendTokenRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET", token);
//        return response.getStatus();
//    }
//
//    public static Response sendTokenRequest(String url,String requestStr, String MediaType, String method, String token){
//        try
//        {
//            Response response=null;
//            Client client =ClientBuilder.newClient();
//            WebTarget target =client.target(url);
//            switch (method){
//                case "POST":
//                    response = target.request().header("Content-type", "text/xml").header("Signature", "43AD232FD45FF").header("Token", token).post(Entity.entity(requestStr, MediaType));
//
//                    break;
//
//                case "GET":
//                    response = target.request().header("Token", token).get();
//                    break;
//
//            }
//
//            return response;
//        }
//        catch(Exception e)
//        {
//            System.out.println("Error send request: "+e.getMessage());
//            return null;
//        }
//    }
//


