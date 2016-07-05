/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.Customer;
import com.oltranz.payfuel.entities.Device;
import com.oltranz.payfuel.entities.PaymentMode;
import com.oltranz.payfuel.entities.Product;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleForBranch;
import com.oltranz.payfuel.entities.Transaction;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.models.DayTransaction;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.TransactionDetailsModel;
import com.oltranz.payfuel.models.TransactionFilter;
import com.oltranz.payfuel.models.UserDetailsModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
    
    public ResultObject getTransactionList(int userId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(TransactionDetailsModel.class);
        
        //check if user is available
        User user=em.find(User.class,userId);
        if(user==null){
            resultObject.setMessage("User is not Created To Access The Device");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        List<TransactionDetailsModel> transactionDetailsModelList=new ArrayList<>();
        Transaction transaction;
        List<Transaction> transactionList=(List<Transaction>)em.createNamedQuery("Transaction.findAll").getResultList();
        
        //if user id 1 bring all transaction
        if(user.getUserId()==1){
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
                
                transactionDetailsModelList.add(transactionDetailsModel);
                
            }
            resultObject.setObject(transactionDetailsModelList);
            resultObject.setMessage(transactionList.size()+" Transaction were found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        //get the user details,roles and its branch
        UserDetailsModel userDetails= (UserDetailsModel) userManager.getUserDetails(user.getUserId()).getObject();
        List<Role> roles=userDetails.getRoles();
        Integer branchId=-1;
        for(Role r: roles){
            
            if(r.getTypeId()==1){
                
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
                    
                    transactionDetailsModelList.add(transactionDetailsModel);
                    
                }
                resultObject.setObject(transactionDetailsModelList);
                resultObject.setMessage(transactionList.size()+" Transaction were found");
                resultObject.setStatusCode(100);
                return resultObject;
            }
            
            if(r.getTypeId()==2){
                
                List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                if(list.size()>0)
                    
                    branchId=list.get(0).getRoleForBranchPK().getBranchId();
            }
        }
        
        if(branchId==-1){
            resultObject.setObject(null);
            resultObject.setMessage("You are not a staff of any branch to access the Transaction");
            resultObject.setObjectClass(Branch.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //get the branch from userbranchid
        transactionList=(List<Transaction>)em.createQuery("SELECT t FROM Transaction t WHERE t.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        if(transactionList.size()>0){
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
                
                transactionDetailsModelList.add(transactionDetailsModel);
                
            }
            resultObject.setObject(transactionDetailsModelList);
            resultObject.setMessage(transactionList.size()+" Transaction were found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("There are no Transaction in the system");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    public ResultObject filterTransaction(TransactionFilter transactionFilter){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(TransactionDetailsModel.class);
       
        List<TransactionDetailsModel> transactionDetailsModelList=new ArrayList<>();
        Transaction transaction;
        
        String nul="null";
        String and="and";
        String sqlQuery="SELECT t FROM Transaction t WHERE";
        
        if(transactionFilter.getBranchId()!=0){
            sqlQuery+=" t.branchId = :branchId";
        }
        
        if(transactionFilter.getUserId()!=0){
            sqlQuery+=" and t.userId = :userId";
        }
        
        if(transactionFilter.getDeviceId()!=0){
            sqlQuery+=" and t.deviceId = :deviceId";
        }
        
        if(transactionFilter.getProductId()!=0){
            sqlQuery+=" and t.productId = :productId";
        }
        
        if(transactionFilter.getPaymentModeId()!=0){
            sqlQuery+=" and t.paymentModeId = :paymentModeId";
        }
        
        if(!transactionFilter.getStatus().equalsIgnoreCase(nul)){
            sqlQuery+=" and t.paymentStatus = :paymentStatus";
        }
        
       
        
        
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
            
            transactionDetailsModelList.add(transactionDetailsModel);
            
        }
        resultObject.setObject(transactionDetailsModelList);
        resultObject.setMessage(transactionList.size()+" Transaction were found");
        resultObject.setStatusCode(100);
        return resultObject;
        
    }
    
    
    
    
    
    
}
