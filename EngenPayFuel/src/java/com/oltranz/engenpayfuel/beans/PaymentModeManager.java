/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.PaymentMode;
import com.oltranz.engenpayfuel.entities.Transaction;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.models.ReportShift;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.UserPaymentModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@Stateless
public class PaymentModeManager {
    
    @PersistenceContext
    private EntityManager em;
    
    public ResultObject createPaymentMode(PaymentMode newPaymentMode) {
        
        //we dont set status and unity in a product object bcz it have default value 7 and "LITER"
        PaymentMode paymentMode=new PaymentMode();
        paymentMode.setName(newPaymentMode.getName());
        paymentMode.setDescr(newPaymentMode.getDescr());
        em.persist(paymentMode);
        em.flush();
        //set the returntype resultset object
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("New PaymentMode successfully created ");
        resultObject.setObject(paymentMode);
        resultObject.setObjectClass(PaymentMode.class);
        
        return resultObject;
        
    }
    
    public ResultObject editPaymentMode(PaymentMode editPaymentMode){
        
        ResultObject resultObject=new ResultObject();
        PaymentMode paymentMode=em.find(PaymentMode.class, editPaymentMode.getPaymentModeId());
        
        
        if(paymentMode==null){
            resultObject.setMessage("No PaymentMode with id of the given one is found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        
        paymentMode.setName(editPaymentMode.getName());
        paymentMode.setDescr(editPaymentMode.getDescr());
        paymentMode.setStatus(editPaymentMode.getStatus());
        //default value of a Product status is 7 even if i dont post status the 7 is coming in this object
        em.merge(paymentMode);
        
        
        resultObject.setMessage("PaymentMode successfully updated");
        resultObject.setObject(paymentMode);
        resultObject.setObjectClass(PaymentMode.class);
        
        return resultObject;
        
    }
    
    public ResultObject getPaymentModeList(){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(PaymentMode.class);
        
        List<PaymentMode> paymentModeList=(List<PaymentMode>)em.createQuery("SELECT p FROM PaymentMode p").getResultList();
        resultObject.setObject(paymentModeList);
        if(paymentModeList.isEmpty()){
            resultObject.setMessage("There are no PaymentMode in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(paymentModeList.size()+" PaymentMode were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    
    public ResultObject getPaymentModeByItsId(Integer paymentModeId){
        
        ResultObject resultObject=new ResultObject();
        PaymentMode paymentMode=em.find(PaymentMode.class,paymentModeId);
        
        if(paymentMode!=null){
            resultObject.setMessage("PaymentMode Well found and returned!");
            resultObject.setObject(paymentMode);
            resultObject.setObjectClass(PaymentMode.class);
            return resultObject;
        }else{
            resultObject.setMessage("PaymentMode with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(PaymentMode.class);
            return resultObject;
        }
    }
    
    public ResultObject removePaymentMode(Integer paymentModeId){
        
        ResultObject resultObject=new ResultObject();
        PaymentMode paymentMode2Delete=em.find(PaymentMode.class, paymentModeId);
        
        if(paymentMode2Delete==null){
            resultObject.setMessage("PaymentMode with given Id not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        PaymentMode paymentMode=em.find(PaymentMode.class, paymentMode2Delete.getPaymentModeId());
        paymentMode.setStatus(paymentMode.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("--yyyy-MM-dd/HH:mm");
        Date deletionTIme=new Date();
        paymentMode.setName(paymentMode.getName()+ sdf.format(deletionTIme));
        em.merge(paymentMode);
        
        
        resultObject.setMessage("PaymentMode successfully sent to dustbin");
        resultObject.setObject(paymentMode);
        resultObject.setObjectClass(PaymentMode.class);
        return resultObject;
    }
    
    
    //android need function
    public ResultObject getPaymentModeListByUserId(int userId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(PaymentMode.class);
        
        User user=em.find(User.class, userId);
        if(user==null){
            resultObject.setObject(null);
            resultObject.setMessage("There are no PaymentMode available for this user");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        List<PaymentMode> paymentModeList=(List<PaymentMode>)em.createQuery("SELECT p FROM PaymentMode p").getResultList();
        resultObject.setObject(paymentModeList);
        if(paymentModeList.isEmpty()){
            resultObject.setMessage("There are no PaymentMode in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(paymentModeList.size()+" PaymentMode were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    
    public ResultObject getPaymentReportList(String inRs){
        
        try {
            ResultObject resultObject= new ResultObject();
            resultObject.setObjectClass(UserPaymentModel.class);
            
            ReportShift rs = new ObjectMapper().readValue(inRs, ReportShift.class);
            List<UserPaymentModel> userPaymentModelList=new ArrayList<>();
            List<Integer> paymentModeList=em.createQuery("SELECT DISTINCT t.paymentModeId FROM Transaction t WHERE  t.serverReqTime BETWEEN :fromDate AND :toDate").setParameter("fromDate",rs.getStartTime(), TemporalType.TIMESTAMP).setParameter("toDate", rs.getEndTime(), TemporalType.TIMESTAMP).getResultList();
            for(int pmId:paymentModeList){
                double amount=reportAmount(rs.getPumpistId(),pmId,rs.getStartTime(),rs.getEndTime());
                UserPaymentModel upm=new UserPaymentModel();
                upm.setPaymentModeId(pmId);
                upm.setAmount(amount);
                
                 if(pmId==1){
                    upm.setPaymentMode("cash");
                }
                
                if(pmId==2){
                    upm.setPaymentMode("voucher");
                }
                
                if(pmId==3){
                    upm.setPaymentMode("mtn");
                }
                
                if(pmId==4){
                    upm.setPaymentMode("tigo");
                }
                
                if(pmId==5){
                    upm.setPaymentMode("airtel");
                }
                
                if(pmId==6){
                    upm.setPaymentMode("visa");
                }
                
                if(pmId==7){
                    upm.setPaymentMode("master");
                }
                
                if(pmId==8){
                    upm.setPaymentMode("debt");
                }
                
                if(pmId==9){
                    upm.setPaymentMode("engenCard");
                }
                
                userPaymentModelList.add(upm);
            }
            
            
            resultObject.setObject(userPaymentModelList);
            resultObject.setMessage(userPaymentModelList.size()+" PaymentMode were found");
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch (Exception ex) {
            Logger.getLogger(PaymentModeManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public double reportAmount(int userId,int paymentModeId,Date from, Date to){
        
        Query query;
        query=em.createQuery("SELECT t FROM Transaction t WHERE t.userId = :userId and t.paymentModeId = :paymentModeId and t.paymentStatus = :paymentStatus and t.serverReqTime BETWEEN :fromDate AND :toDate");
        query.setParameter("userId", userId);
        query.setParameter("paymentModeId", paymentModeId);
        query.setParameter("paymentStatus", "SUCCESS");
        query.setParameter("fromDate", from);
        query.setParameter("toDate", to);
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        
        double amount=0;
        if(transactionList.isEmpty()){
            return amount;
        }
        else{
            for(Transaction transaction : transactionList){
                amount+=transaction.getAmount();
            }
            return amount;
        }
        
    }
    
    
}
