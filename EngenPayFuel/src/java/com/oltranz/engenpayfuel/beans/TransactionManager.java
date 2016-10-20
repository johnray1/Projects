/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Customer;
import com.oltranz.engenpayfuel.entities.Device;
import com.oltranz.engenpayfuel.entities.PaymentMode;
import com.oltranz.engenpayfuel.entities.Product;
import com.oltranz.engenpayfuel.entities.Transaction;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.models.DayTransaction;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.TransactionDetailsModel;
import com.oltranz.engenpayfuel.models.TransactionFilter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class TransactionManager {
    
    @PersistenceContext
    private  EntityManager em;
    
    @EJB
            UserManager userManager;
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    
    public ResultObject getTransactionList(){
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(TransactionDetailsModel.class);
        
        List<TransactionDetailsModel> transactionDetailsModelList=new ArrayList<>();
        Transaction transaction;
        List<Transaction> transactionList=(List<Transaction>)em.createNamedQuery("Transaction.findAll").getResultList();
        
        if(transactionList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Transaction in the system");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=transactionList.iterator();
            while(i.hasNext()){
                transaction=(Transaction) i.next();
                TransactionDetailsModel  transactionDetailsModel=new TransactionDetailsModel();
                
                transactionDetailsModel.setTransactionId(transaction.getTransactionId());
                transactionDetailsModel.setDeviceTransactionId(transaction.getDeviceTransactionId());
                transactionDetailsModel.setAmount(transaction.getAmount());
                Branch branch=commonFunctionEjb.getBranchName(transaction.getBranchId());//branch
                transactionDetailsModel.setBranchName(branch.getName());
                Customer customer=commonFunctionEjb.getCustomer(transaction.getCustomerId());//customer
                transactionDetailsModel.setCustomer(customer.getContactDetails());
                Device device=commonFunctionEjb.getDeviceName(transaction.getDeviceId());//device
                transactionDetailsModel.setDeviceName(device.getDeviceName());
                PaymentMode paymentMode=commonFunctionEjb.getPaymentModeName(transaction.getPaymentModeId());
                transactionDetailsModel.setPaymentMode(paymentMode.getName());
                transactionDetailsModel.setPaymentStatus(transaction.getPaymentStatus());
                Product product=commonFunctionEjb.getProductName(transaction.getProductId());
                transactionDetailsModel.setProductName(product.getName());
                transactionDetailsModel.setQuantity(transaction.getQuantity());
                transactionDetailsModel.setTime(transaction.getServerReqTime());
                User user=commonFunctionEjb.getUserName(transaction.getUserId());
                transactionDetailsModel.setUserName(user.getFname());
                
                
                transactionDetailsModelList.add(transactionDetailsModel);
                
            }
            resultObject.setObject(transactionDetailsModelList);
            resultObject.setMessage(transactionList.size()+" Transaction were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject getDayTransactionList(DayTransaction dayTransactionIp){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Transaction.class);
        try{
            String ipDate=dayTransactionIp.getDeviceTransactionDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate=df.parse(ipDate);
            
            List<TransactionDetailsModel> transactionDetailsModelList=new ArrayList<>();
            Transaction transaction;
            List<Transaction> transactionList=(List<Transaction>)em.createQuery("SELECT t FROM Transaction t WHERE t.userId = :userId and t.date = :date").setParameter("userId",dayTransactionIp.getUserId()).setParameter("date",startDate).getResultList();
            
            if(transactionList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Transaction in the system");
                resultObject.setStatusCode(500);
            }
            else{
                resultObject.setObject(transactionList);
                resultObject.setMessage(transactionList.size()+" Transaction were found");
                resultObject.setStatusCode(100);
            }
            
            return resultObject;
        }
        catch(ParseException e){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
    }
    
    public ResultObject getTransactionListByTraId(long transactionId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(TransactionDetailsModel.class);
        
        Transaction transaction=em.find(Transaction.class,transactionId);
        if(transaction!=null){
            
            TransactionDetailsModel  transactionDetailsModel=new TransactionDetailsModel();
            
            transactionDetailsModel.setTransactionId(transaction.getTransactionId());
            transactionDetailsModel.setDeviceTransactionId(transaction.getDeviceTransactionId());
            transactionDetailsModel.setAmount(transaction.getAmount());
            Branch branch=commonFunctionEjb.getBranchName(transaction.getBranchId());//branch
            transactionDetailsModel.setBranchName(branch.getName());
            Customer customer=commonFunctionEjb.getCustomer(transaction.getCustomerId());//customer
            transactionDetailsModel.setCustomer(customer.getContactDetails());
            Device device=commonFunctionEjb.getDeviceName(transaction.getDeviceId());//device
            transactionDetailsModel.setDeviceName(device.getDeviceName());
            PaymentMode paymentMode=commonFunctionEjb.getPaymentModeName(transaction.getPaymentModeId());
            transactionDetailsModel.setPaymentMode(paymentMode.getName());
            transactionDetailsModel.setPaymentStatus(transaction.getPaymentStatus());
            Product product=commonFunctionEjb.getProductName(transaction.getProductId());
            transactionDetailsModel.setProductName(product.getName());
            transactionDetailsModel.setQuantity(transaction.getQuantity());
            transactionDetailsModel.setTime(transaction.getServerReqTime());
            User user=commonFunctionEjb.getUserName(transaction.getUserId());
            transactionDetailsModel.setUserName(user.getFname());
            
            resultObject.setObject(transactionDetailsModel);
            resultObject.setMessage("Transaction Well found and returned!");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setMessage("Transaction with given Id not found!");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        
    }
    
    public ResultObject getTransactionListByBraId(int branchId){
        
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate=dateFormat.format(currentDate)+"  00:00:00";
        String endDate=dateFormat.format(currentDate)+"  23:59:59";
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = null;Date toDate = null;
        try {
            fromDate=dateFormat2.parse(startDate);
            toDate=dateFormat2.parse(endDate);
        } catch (ParseException ex) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(TransactionDetailsModel.class);
        
        List<TransactionDetailsModel> transactionDetailsModelList=new ArrayList<>();
        Transaction transaction;
        
        List<Transaction> transactionList;
        if(branchId==0){
            transactionList=(List<Transaction>)em.createQuery("SELECT t FROM Transaction t WHERE t.serverReqTime BETWEEN :fromDate AND :toDate").setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
        }
        else{
            transactionList=(List<Transaction>)em.createQuery("SELECT t FROM Transaction t WHERE t.branchId = :branchId and t.serverReqTime BETWEEN :fromDate AND :toDate").setParameter("branchId", branchId).setParameter("fromDate", fromDate).setParameter("toDate", toDate).getResultList();
        }
        
        
        if(transactionList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Transaction in the system");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=transactionList.iterator();
            while(i.hasNext()){
                transaction=(Transaction) i.next();
                TransactionDetailsModel  transactionDetailsModel=new TransactionDetailsModel();
                
                transactionDetailsModel.setTransactionId(transaction.getTransactionId());
                transactionDetailsModel.setDeviceTransactionId(transaction.getDeviceTransactionId());
                transactionDetailsModel.setAmount(transaction.getAmount());
                Branch branch=commonFunctionEjb.getBranchName(transaction.getBranchId());//branch
                transactionDetailsModel.setBranchName(branch.getName());
                Customer customer=commonFunctionEjb.getCustomer(transaction.getCustomerId());//customer
                transactionDetailsModel.setCustomer(customer.getContactDetails());
                Device device=commonFunctionEjb.getDeviceName(transaction.getDeviceId());//device
                transactionDetailsModel.setDeviceName(device.getDeviceName());
                PaymentMode paymentMode=commonFunctionEjb.getPaymentModeName(transaction.getPaymentModeId());
                transactionDetailsModel.setPaymentMode(paymentMode.getName());
                transactionDetailsModel.setPaymentStatus(transaction.getPaymentStatus());
                Product product=commonFunctionEjb.getProductName(transaction.getProductId());
                transactionDetailsModel.setProductName(product.getName());
                transactionDetailsModel.setQuantity(transaction.getQuantity());
                transactionDetailsModel.setTime(transaction.getServerReqTime());
                User user=commonFunctionEjb.getUserName(transaction.getUserId());
                transactionDetailsModel.setUserName(user.getFname());
                transactionDetailsModel.setTankName(commonFunctionEjb.getTank(transaction.getTankId()).getName());
                transactionDetailsModel.setPumpName(commonFunctionEjb.getPumpName(transaction.getPumpId()).getName());
                transactionDetailsModel.setNozzleName(commonFunctionEjb.getNozzle(transaction.getNozzleId()).getNozzleName());
                
                
                
                transactionDetailsModelList.add(transactionDetailsModel);
                
            }
            resultObject.setObject(transactionDetailsModelList);
            resultObject.setMessage(transactionList.size()+" Transaction were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    
    //-----------------------web------------------------------------------------------------------------------------------------------
    
    public ResultObject getBranchTransactionList(Integer branchId){
        ResultObject resultObject;
        resultObject=getTransactionListByBraId(branchId);
        return resultObject;
    }
    
    public ResultObject filterTransaction(TransactionFilter transactionFilter){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(TransactionDetailsModel.class);
        try{
            List<TransactionDetailsModel> transactionDetailsModelList=new ArrayList<>();
            Transaction transaction;
            
            String dateIp=transactionFilter.getDate();
            
            String[] output = dateIp.split("-");
            String from=output[0];
            String to=output[1];
            String dateFrom = from.replace('/', '-');
            String dateTo = to.replace('/', '-');
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            Date fromDate=dateFormat.parse(dateFrom);
            Date toDate=dateFormat.parse(dateTo);
            
            String nul="null";
            String sqlQuery="SELECT t FROM Transaction t WHERE";
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(transactionFilter.getBranchId()!=0){
                sqlQuery+=" t.branchId = :branchId";
            }
            
            if( (transactionFilter.getBranchId()!=0) && ( (transactionFilter.getUserId()!=0)||(transactionFilter.getDeviceId()!=0)||(transactionFilter.getProductId()!=0)||(transactionFilter.getPaymentModeId()!=0)||(!transactionFilter.getStatus().equalsIgnoreCase(nul))||(!transactionFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            if(transactionFilter.getUserId()!=0){
                sqlQuery+=" t.userId = :userId";
            }
            
            if( (transactionFilter.getUserId()!=0) && ( (transactionFilter.getDeviceId()!=0)||(transactionFilter.getProductId()!=0)||(transactionFilter.getPaymentModeId()!=0)||(!transactionFilter.getStatus().equalsIgnoreCase(nul))||(!transactionFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(transactionFilter.getDeviceId()!=0){
                sqlQuery+=" t.deviceId = :deviceId";
            }
            
            if( (transactionFilter.getDeviceId()!=0) && ( (transactionFilter.getProductId()!=0)||(transactionFilter.getPaymentModeId()!=0)||(!transactionFilter.getStatus().equalsIgnoreCase(nul))||(!transactionFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(transactionFilter.getProductId()!=0){
                sqlQuery+=" t.productId = :productId";
            }
            
            if( (transactionFilter.getProductId()!=0) && ( (transactionFilter.getPaymentModeId()!=0)||(!transactionFilter.getStatus().equalsIgnoreCase(nul))||(!transactionFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(transactionFilter.getPaymentModeId()!=0){
                sqlQuery+=" t.paymentModeId = :paymentModeId";
            }
            
            if( (transactionFilter.getPaymentModeId()!=0) && ( (!transactionFilter.getStatus().equalsIgnoreCase(nul))||(!transactionFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(!transactionFilter.getStatus().equalsIgnoreCase(nul)){
                sqlQuery+=" t.paymentStatus = :paymentStatus";
            }
            
            if( (!transactionFilter.getStatus().equalsIgnoreCase(nul)) && ( (!transactionFilter.getDate().equalsIgnoreCase(nul)) ) ){
                sqlQuery+=" and";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            if(!transactionFilter.getDate().equalsIgnoreCase(nul)){
                sqlQuery+=" t.serverReqTime BETWEEN :fromDate AND :toDate";
            }
            
            //-------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            Query query = em.createQuery(sqlQuery);
            
            if(transactionFilter.getBranchId()!=0){
                query.setParameter("branchId", transactionFilter.getBranchId());
            }
            
            if(transactionFilter.getUserId()!=0){
                query.setParameter("userId", transactionFilter.getUserId());
            }
            
            if(transactionFilter.getDeviceId()!=0){
                query.setParameter("deviceId", transactionFilter.getDeviceId());
            }
            
            if(transactionFilter.getProductId()!=0){
                query.setParameter("productId", transactionFilter.getProductId());
            }
            
            if(transactionFilter.getPaymentModeId()!=0){
                query.setParameter("paymentModeId", transactionFilter.getPaymentModeId());
            }
            
            if(!transactionFilter.getStatus().equalsIgnoreCase(nul)){
                query.setParameter("paymentStatus", transactionFilter.getStatus());
            }
            
            if(!transactionFilter.getDate().equalsIgnoreCase(nul)){
                query.setParameter("fromDate", fromDate);
                query.setParameter("toDate", toDate);
            }
            
            
            List<Transaction> transactionList=(List<Transaction>)query.getResultList();
            Iterator i=transactionList.iterator();
            while(i.hasNext()){
                transaction=(Transaction) i.next();
                TransactionDetailsModel  transactionDetailsModel=new TransactionDetailsModel();
                
                transactionDetailsModel.setAmount(transaction.getAmount());
                Branch branch=commonFunctionEjb.getBranchName(transaction.getBranchId());
                transactionDetailsModel.setBranchName(branch.getName());
                Customer customer=commonFunctionEjb.getCustomer(transaction.getCustomerId());
                transactionDetailsModel.setCustomer(customer.getContactDetails());
                Device device=commonFunctionEjb.getDeviceName(transaction.getDeviceId());
                transactionDetailsModel.setDeviceName(device.getDeviceName());
                PaymentMode paymentMode=commonFunctionEjb.getPaymentModeName(transaction.getPaymentModeId());
                transactionDetailsModel.setPaymentMode(paymentMode.getName());
                transactionDetailsModel.setPaymentStatus(transaction.getPaymentStatus());
                Product product=commonFunctionEjb.getProductName(transaction.getProductId());
                transactionDetailsModel.setProductName(product.getName());
                transactionDetailsModel.setQuantity(transaction.getQuantity());
                transactionDetailsModel.setTime(transaction.getServerReqTime());
                User us=commonFunctionEjb.getUserName(transaction.getUserId());
                transactionDetailsModel.setUserName(us.getFname());
                transactionDetailsModel.setTransactionId(transaction.getTransactionId());
                transactionDetailsModel.setDeviceTransactionId(transaction.getDeviceTransactionId());
                transactionDetailsModel.setTankName(commonFunctionEjb.getTank(transaction.getTankId()).getName());
                transactionDetailsModel.setPumpName(commonFunctionEjb.getPumpName(transaction.getPumpId()).getName());
                transactionDetailsModel.setNozzleName(commonFunctionEjb.getNozzle(transaction.getNozzleId()).getNozzleName());
                
                transactionDetailsModelList.add(transactionDetailsModel);
                
            }
            resultObject.setObject(transactionDetailsModelList);
            resultObject.setMessage(transactionList.size()+" Transaction were found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(ParseException pe){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
        
    }
    
    
}
