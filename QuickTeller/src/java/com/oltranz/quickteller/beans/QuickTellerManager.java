/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.quickteller.beans;

import com.oltranz.quickteller.entities.Quickteller;
import com.oltranz.quickteller.library.PaymentLibrary;
import com.oltranz.quickteller.models.PaymentCompletedRequest;
import com.oltranz.quickteller.models.PaymentCompletedResponse;
import com.oltranz.quickteller.models.QuickPaymentRes;
import com.oltranz.quickteller.models.QuicktellerPayment;
import com.oltranz.quickteller.models.SimpleStatus;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author JohnRay
 */
@Stateless
public class QuickTellerManager {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public String quickPayment(QuicktellerPayment qp) {
        
        try {
            PaymentCompletedResponse pres=new PaymentCompletedResponse();
            
            //Persist
            Quickteller newQuickteller=new Quickteller();
            newQuickteller.setMsisdn(qp.getMsisdn());
            newQuickteller.setRequestReference(qp.getRequestReference());
            
            newQuickteller.setPaymentReference(qp.getPaymentReference());
            newQuickteller.setAmount(qp.getAmount());
            newQuickteller.setRechargePin(qp.getRechargePin());
            newQuickteller.setSignature(qp.getSignature());
            newQuickteller.setResponseCode(qp.getResponseCode());
            newQuickteller.setResponseDescription(qp.getResponseDescription());
            
            em.persist(newQuickteller);
            em.flush();
            
            //Getting the transaction confirmation response from quickteller by passing payment reference
            QuickPaymentRes qr=PaymentLibrary.getpaymentConfirmFromQuickteller(newQuickteller.getPaymentReference());
            
            
            //Posting To PaymentModule Function
            if(qr.getResponseCode().equals("00")){
                PaymentCompletedRequest preq=new PaymentCompletedRequest();
                preq.setInitUid(qp.getRequestReference());
                preq.setSPPaymentRef(qp.getPaymentReference());
                preq.setAmount(Double.valueOf(qr.getAmount())/100);
                preq.setPaymentSPId(3847);
                preq.setPaymentTypeId(4);
                
                pres=PaymentLibrary.postPaymentModule(preq,qp.getToken());
            }
            else{
                SimpleStatus ss=new SimpleStatus();
                ss.setStatusCode(qp.getResponseCode());
                ss.setStatusDesc(qp.getResponseDescription());
                pres.setStatus(ss);
            }
            
            ObjectMapper mapper=new ObjectMapper();
            String jsonData=mapper.writeValueAsString(pres);
            return jsonData;
        }
        catch (Exception ex) {
            Logger.getLogger(QuickTellerManager.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage()+ex.getLocalizedMessage();
        }
    }
    
    
    
}
