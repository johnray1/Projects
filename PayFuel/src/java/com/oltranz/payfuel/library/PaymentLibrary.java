/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.library;


import com.oltranz.payfuel.models.PaymentResponse;
import com.oltranz.payfuel.models.ServiceProvison;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author John
 */
public class PaymentLibrary {
    
    public static PaymentResponse sendTestPaymentXML(Long traId,int pmId,Double amount,String tel,String voucher){
        
        String url="";
        if((pmId==1)||(pmId==6)||(pmId==7)||(pmId==8)||(pmId==9)){
            url="http://localhost:8080/PaymentManagerPayFuel/PaymentManagementWebService/payment/cash";
        }
        else if(pmId==2){
            url="http://localhost:8080/PaymentManagerPayFuel/PaymentManagementWebService/payment/voucher";
        }
        else if((pmId==3)||(pmId==4)||(pmId==5)){
            url="http://localhost:8080/PaymentManagerPayFuel/PaymentManagementWebService/payment/momo";
        }
        
        String xmlData= "<COMMAND>"
                + "<TRANSACTIONID>"+traId+"</TRANSACTIONID>"
                + "<PAYMENTMODEID>"+pmId+"</PAYMENTMODEID>"
                +"<AMOUNT>"+amount+"</AMOUNT>"
                +"<TELEPHONE>"+tel+"</TELEPHONE>"
                +"<VOUCHER>"+voucher+"</VOUCHER>"
                + "</COMMAND>";
        Response response=CommonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        PaymentResponse paymentResponse=(PaymentResponse) CommonLibrary.unmarshalling(response.readEntity(String.class),PaymentResponse.class);
        
        return paymentResponse;
        
    }
    
    //On This function MyApp will Post the MOMO Payment Status Successfully Updated To Payment GateWay
    public static void momoAcknowledgement(ServiceProvison serviceProvisonIp){
        
        String url="http://localhost:8080/PaymentManagerPayFuel/PaymentManagementWebService/payment/momoAcknowledgement";
        String xmlData= "<COMMAND>"
                +"<TRANSID>"+serviceProvisonIp.getTRANSID()+"</TRANSID>"
                +"<CONTRACTID>"+serviceProvisonIp.getCONTRACTID()+"</CONTRACTID>"
                +"<STATUSCODE>"+serviceProvisonIp.getSTATUSCODE()+"</STATUSCODE>"
                +"<SPTRANSID>"+serviceProvisonIp.getSPTRANSID()+"</SPTRANSID>"
                +"<STATUSDESC>"+serviceProvisonIp.getSTATUSDESC()+"</STATUSDESC>"
                +"</COMMAND>";
        Response response=CommonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        
    }
    
}
