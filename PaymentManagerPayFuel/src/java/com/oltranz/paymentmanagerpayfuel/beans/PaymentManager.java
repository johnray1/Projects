/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.paymentmanagerpayfuel.beans;

import com.oltranz.paymentmanagerpayfuel.entities.Payment;
import com.oltranz.paymentmanagerpayfuel.library.CommonLibrary;
import com.oltranz.paymentmanagerpayfuel.model.PaymentRequest;
import com.oltranz.paymentmanagerpayfuel.model.PaymentResponse;
import com.oltranz.paymentmanagerpayfuel.model.ServiceProvison;
import com.oltranz.paymentmanagerpayfuel.model.Voucher;
import com.oltranz.paymentmanagerpayfuel.model.VoucherSingle;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@Stateless
public class PaymentManager {
    
    @PersistenceContext
    private EntityManager em;
    
    
    //---------------------------------------------CashPayment----------------------------------------------------------------
    
    public PaymentResponse cashPayment(PaymentRequest paymentRequest){
        
        PaymentResponse paymentResponse=new PaymentResponse();
        Payment payment =new Payment();
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date dtt=dateFormat.parse(dateFormat.format(date));
            
            paymentResponse.setReqStatus(100);
            paymentResponse.setResStatus(100);
            paymentResponse.setDesc("Payment Successfully Completed");
            
            
            payment.setTransactionId(paymentRequest.getTransactionId());
            payment.setAmount(paymentRequest.getAmount());
            payment.setContact(paymentRequest.getTelephone());
            payment.setRequestDatetime(dtt);
            payment.setResponseDatetime(dtt);
            payment.setDescr(paymentRequest.getAmount()+"frw Of Cash Transaction Successful");
            em.persist(payment);
            
            return paymentResponse;
        }
        catch(ParseException pe){
            paymentResponse.setReqStatus(100);
            paymentResponse.setResStatus(500);
            paymentResponse.setDesc(pe.getMessage());
            return paymentResponse;
        }
    }
    
    
    
    //---------------------------------------------VoucherPayment----------------------------------------------------------------
    
    public PaymentResponse voucherPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=new PaymentResponse();
        Payment payment =new Payment();
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date dtt=dateFormat.parse(dateFormat.format(date));
            
            Voucher voucher=getVoucher(paymentRequest.getVoucherNumber());
            if(voucher==null){
                
                paymentResponse.setReqStatus(500);
                paymentResponse.setResStatus(100);
                paymentResponse.setDesc("No Voucher Found");
                
                payment.setTransactionId(paymentRequest.getTransactionId());
                payment.setAmount(paymentRequest.getAmount());
                payment.setContact(paymentRequest.getTelephone());
                payment.setRequestDatetime(dtt);
                payment.setResponseDatetime(dtt);
                payment.setDescr(paymentRequest.getAmount()+"frw Of Voucher Transaction UnSuccessful For No Voucher Found");
                em.persist(payment);
                
                return paymentResponse;
            }
            if(voucher.getRemainAmount()<=paymentRequest.getAmount()){
                
                paymentResponse.setReqStatus(500);
                paymentResponse.setResStatus(100);
                paymentResponse.setDesc("Insufficient Amount");
                
                payment.setTransactionId(paymentRequest.getTransactionId());
                payment.setAmount(paymentRequest.getAmount());
                payment.setContact(paymentRequest.getTelephone());
                payment.setRequestDatetime(dtt);
                payment.setResponseDatetime(dtt);
                payment.setDescr(paymentRequest.getAmount()+"frw Of Voucher Transaction UnSuccessful For Insufficient Amount");
                em.persist(payment);
                
                return paymentResponse;
            }
            
            Double remainAmount=voucher.getRemainAmount()-paymentRequest.getAmount();
            Voucher editVoucher=editVoucher(voucher.getVoucherId(),remainAmount);
            
            paymentResponse.setReqStatus(100);
            paymentResponse.setResStatus(100);
            paymentResponse.setDesc("Payment Successfully Completed");
            
            
            payment.setTransactionId(paymentRequest.getTransactionId());
            payment.setAmount(paymentRequest.getAmount());
            payment.setContact(paymentRequest.getTelephone());
            payment.setRequestDatetime(dtt);
            payment.setResponseDatetime(dtt);
            payment.setDescr(paymentRequest.getAmount()+"frw Of Voucher Transaction Successful");
            em.persist(payment);
            
            return paymentResponse;
        }
        catch(ParseException pe){
            paymentResponse.setReqStatus(500);
            paymentResponse.setResStatus(100);
            paymentResponse.setDesc(pe.getMessage());
            return paymentResponse;
        }
    }
    
    public Voucher getVoucher(String voucherNo){
        Voucher voucher;
        try{
            String getUrl="http://localhost:8080/PayFuel/VoucherManagementService/voucher/"+voucherNo;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            VoucherSingle voucherSingle=(VoucherSingle)mapper.readValue(jsonResponse, VoucherSingle.class);
            voucher=voucherSingle.getVoucher();
            
            return voucher;
        }
        catch(Exception ex){
            return null;
        }
        
    }
    
    public Voucher editVoucher(Long voucherId,Double remainAmount){
        Voucher voucher;
        try{
            String url="http://localhost:8080/PayFuel/VoucherManagementService/voucher/edit";
            String  jsonData = "{\n" +
                    "\"voucherId\":\""+voucherId+"\",\n" +
                    "\"remainAmount\":\""+remainAmount+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse=response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            VoucherSingle voucherSingle=(VoucherSingle)mapper.readValue(jsonResponse, VoucherSingle.class);
            voucher=voucherSingle.getVoucher();
            
            return voucher;
        }
        catch(Exception ex){
            return null;
        }
    }
    
    
    
    //---------------------------------------------MobileMOneyPayment----------------------------------------------------------------
    
    public PaymentResponse mobileMOneyPayment(PaymentRequest paymentRequest) {
        
        PaymentResponse paymentResponse=new PaymentResponse();
        Payment payment =new Payment();
        String ps="";
        
        try{
            if(paymentRequest.getPaymentModeId()==3){
                ps="2484";
            }
            else if(paymentRequest.getPaymentModeId()==4){
                ps="3382";
            }
            else if(paymentRequest.getPaymentModeId()==5){
                ps="5728";
            }
            //call Payment Function For Money
            paymentResponse=sendPaymentXML(ps,paymentRequest.getTransactionId(),paymentRequest.getTelephone(),paymentRequest.getAmount());
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date dtt=dateFormat.parse(dateFormat.format(date));
            
            payment.setTransactionId(paymentRequest.getTransactionId());
            payment.setAmount(paymentRequest.getAmount());
            payment.setContact(paymentRequest.getTelephone());
            payment.setRequestDatetime(dtt);
            payment.setResponseDatetime(dtt);
            
            if(paymentResponse.getReqStatus()==100){
                payment.setDescr(paymentRequest.getAmount()+"frw Of MOMO Transaction Successful");
            }
            else{
                if(paymentResponse.getReqStatus()==301){
                    payment.setDescr(paymentRequest.getAmount()+"frw Of MOMO Transaction Pending");
                }
                else{
                    payment.setDescr(paymentRequest.getAmount()+"frw Of MOMO Transaction Failure");
                }
            }
            em.persist(payment);
            
            return paymentResponse;
        }
        catch(ParseException pe){
            paymentResponse.setReqStatus(500);
            paymentResponse.setResStatus(100);
            paymentResponse.setDesc(pe.getMessage());
            return paymentResponse;
        }
    }
    
    public PaymentResponse sendPaymentXML(String ps,Long tId,String tel,Double amount){
        
        String url="http://10.171.1.53/PaymentGateway/payments/paymentRequestv2";
        String xmlData= "<COMMAND>"
                + "<CONTRACTID>441001</CONTRACTID>"
                + "<PAYINGACCOUNTIDATSP>"+tel+"</PAYINGACCOUNTIDATSP>"
                +"<PAYMENTSPID>"+ps+"</PAYMENTSPID>"
                +"<DESCR>"+amount+" PAYMENT FOR ENGEN"+"</DESCR>"
                +"<TRANSID>"+tId+"</TRANSID>"
                +"<AMOUNT>"+amount+"</AMOUNT>"
                + "</COMMAND>";
        out.print("ANDROID  PAYMENT:"+xmlData);
        
        Response response=CommonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        String xmldata=response.readEntity(String.class);
        out.print("ANDROID  PAYMENT: Payment response Header:"+response+" | BODY:"+xmldata);
        
        PaymentResponse paymentResponse=(PaymentResponse) CommonLibrary.unmarshalling(xmldata, PaymentResponse.class);
        
        return paymentResponse;
        
    }
    
    
    public void momoConfirmation(ServiceProvison serviceProvisonIp){
        
        String url="http://localhost:8080/PayFuel/AndroidWebService/pos/momoConfirmation";
        String xmlData="<COMMAND>"
                +"<TRANSID>"+serviceProvisonIp.getTRANSID()+"</TRANSID>"
                +"<CONTRACTID>441001</CONTRACTID>"
                +"<STATUSCODE>"+serviceProvisonIp.getSTATUSCODE()+"</STATUSCODE>"
                +"<SPTRANSID>"+serviceProvisonIp.getSPTRANSID()+"</SPTRANSID>"
                +"<STATUSDESC>"+serviceProvisonIp.getSTATUSDESC()+"</STATUSDESC>"
                +"</COMMAND>";
        out.print("ANDROID  MOMOConfirmation:"+xmlData);
        
        Response response=CommonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        String xmldata=response.readEntity(String.class);
        out.print("ANDROID  MOMOConfirmation: Payment MOMOConfirmation Header:"+response+" | BODY:"+xmldata);
        
        MultivaluedMap<String, Object> map=response.getHeaders();
        
    }
    
    public void momoAcknowledgement(ServiceProvison serviceProvisonIp){
        
        String url="http://10.171.1.53/PaymentGateway/payments/paymentResponseConfirmation";
        String xmlData= "<COMMAND>"
                +"<TRANSID>"+serviceProvisonIp.getTRANSID()+"</TRANSID>"
                +"<CONTRACTID>"+serviceProvisonIp.getCONTRACTID()+"</CONTRACTID>"
                +"<STATUSCODE>"+serviceProvisonIp.getSTATUSCODE()+"</STATUSCODE>"
                +"<DESCR>"+serviceProvisonIp.getSTATUSDESC()+"</DESCR>"
                +"</COMMAND>";
        out.print("ANDROID  PAYMENT:"+xmlData);
        
        Response response=CommonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        String xmldata=response.readEntity(String.class);
        out.print("ANDROID  Acknowledgement: Payment Acknowledgement Header:"+response+" | BODY:"+xmldata);
        
        MultivaluedMap<String, Object> map=response.getHeaders();
        
    }
    
    //---------------------------------------------VisaPayment----------------------------------------------------------------
    
    public PaymentResponse visaPayment(PaymentRequest paymentRequest) {
        return null;
    }
    
    
    //---------------------------------------------MasterCardPayment----------------------------------------------------------------
    
    public PaymentResponse masterCardPayment(PaymentRequest paymentRequest) {
        return null;
    }
    
    
    //---------------------------------------------DebtPayment----------------------------------------------------------------
    
    public PaymentResponse debtPayment(PaymentRequest paymentRequest) {
        return null;
    }
    
    
    //---------------------------------------------SpCardPayment----------------------------------------------------------------
    
    public PaymentResponse spCardPayment(PaymentRequest paymentRequest) {
        return null;
    }
    
    
}
