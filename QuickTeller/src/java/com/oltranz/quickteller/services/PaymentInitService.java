/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.services;

import com.oltranz.quickteller.beans.PaymentInitManager;
import com.oltranz.quickteller.models.PaymentInitRequest;
import com.oltranz.quickteller.models.PaymentInitResponse;
import com.oltranz.quickteller.models.ResultObject;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JohnRay
 */
@Path("PaymentInitService")
@Stateless
public class PaymentInitService {
    
    @EJB
            PaymentInitManager paymentInitManager;
    
    @Path("init")
    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Response PaymentInit(@Context HttpHeaders headers,PaymentInitRequest request) {
        PaymentInitResponse pIResponse;
        String token=null;
        ResultObject resultObject=new ResultObject();
        List<String> tokens=headers.getRequestHeader("Token");
        
        if(tokens!=null)
            token= tokens.get(0);
//        int status=paymentInitManager.getCustomerSession(token);
//        
//        if(status!=200){
//            return Response.status(Response.Status.FORBIDDEN).entity("Invalid Token").build();
//        }
        
        pIResponse=paymentInitManager.initializePayment(request);
        resultObject.setObject(pIResponse.getInitUid());
        resultObject.setObjectClass(String.class);
        resultObject.setStatusCode(pIResponse.getStatus().getStatusCode());
        resultObject.setMessage(pIResponse.getStatus().getStatusDesc());
        
        return Response.ok(pIResponse, MediaType.APPLICATION_JSON).build();
        
    }
    
}
