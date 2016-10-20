/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.servlet;

import com.oltranz.airtimeweb.bean.RechargeBean;
import com.oltranz.airtimeweb.html.Confirmation;
import com.oltranz.airtimeweb.library.CommonLibrary;
import com.oltranz.airtimeweb.model.PaymentCompletedResponse;
import com.oltranz.airtimeweb.model.QuicktellerPayment;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@WebServlet("/paymentServlet")
public class PaymentServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        // Get the bean from the session
        RechargeBean rechargeBean = (RechargeBean)session.getAttribute("RechargeBean");
        
        int amt=rechargeBean.getAmount();
        String token = (String) session.getAttribute("token");
        String msisdn = (String) session.getAttribute("msisdn");
        String req_Ref = request.getParameter("req_Ref");
        
        String tx_ref = request.getParameter("tx_ref");
        String amount = request.getParameter("amount");
        String recharge_pin = request.getParameter("recharge_pin");
        String signature = request.getParameter("signature");
        String resp_code = request.getParameter("resp_code");
        String resp_desc = request.getParameter("resp_desc");
        
        //post QuickTellerPayment To PaymentManager
        QuicktellerPayment quickteller=new QuicktellerPayment();
        quickteller.setToken(token);
        quickteller.setMsisdn(msisdn);
        quickteller.setRequestReference(request.getParameter("req_Ref"));
        
        quickteller.setPaymentReference(request.getParameter("tx_ref"));
        quickteller.setRechargePin(request.getParameter("recharge_pin"));
        quickteller.setSignature(request.getParameter("signature"));
        quickteller.setResponseCode(request.getParameter("resp_code"));
        quickteller.setResponseDescription(request.getParameter("resp_desc"));
        if(amount==null){
            quickteller.setAmount((double)amt);
        }
        else{
            quickteller.setAmount(Double.valueOf(amount)/100);
        }
        
        PaymentCompletedResponse pres=postQuickTellerPayment(quickteller);
        
        //show html page and go to java package called com.oltranz.airtimeweb.html package to find the Confirmation function
        PrintWriter writer = response.getWriter();
        writer.println(Confirmation.confirmationPage(pres.getStatus().getStatusCode(),amt));
        
        
    }
    
    
    public PaymentCompletedResponse postQuickTellerPayment(QuicktellerPayment quickteller) throws IOException{
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/QuickTeller/QuickTellerWebService/webPayment";
        out.print("PaymentServlet: url:"+url);
        String jsonData=mapper.writeValueAsString(quickteller);
        Response res=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = res.readEntity(String.class);
        out.print("PaymentServlet: response:"+jsonResponse);
        PaymentCompletedResponse pres=(PaymentCompletedResponse)mapper.readValue(jsonResponse, PaymentCompletedResponse.class);
        
        return pres;
    }
    
    
    
}
