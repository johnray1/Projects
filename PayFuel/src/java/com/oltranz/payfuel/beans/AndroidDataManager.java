/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.Customer;
import com.oltranz.payfuel.entities.Device;
import com.oltranz.payfuel.entities.ErroneousTransaction;
import com.oltranz.payfuel.entities.IndexTracking;
import com.oltranz.payfuel.entities.Log;
import com.oltranz.payfuel.entities.Nozzle;
import com.oltranz.payfuel.entities.Pump;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleForBranch;
import com.oltranz.payfuel.entities.SpPaymentRes;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.entities.TankTracking;
import com.oltranz.payfuel.entities.Transaction;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.entities.UserPumpNozzle;
import com.oltranz.payfuel.library.PaymentLibrary;
import com.oltranz.payfuel.models.AsyncTransaction;
import com.oltranz.payfuel.models.LoginOpModel;
import com.oltranz.payfuel.models.LogoutOpModel;
import com.oltranz.payfuel.models.PaymentResponse;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.SaleDetailsModel;
import com.oltranz.payfuel.models.SaleDetailsModelList;
import com.oltranz.payfuel.models.ServiceProvison;
import com.oltranz.payfuel.models.SyncTransaction;
import com.oltranz.payfuel.models.UserDetailsModel;
import static java.lang.System.out;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class AndroidDataManager {
    
    @PersistenceContext
    private  EntityManager em;
    
    @EJB
            UserManager userManager;
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
//-------------------------------------------------------Login/Logout----------------------------------------------------------
    
    public  ResultObject login(String deviceName, String pin){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LoginOpModel.class);
        try{
            int deviceBranchId=commonFunctionEjb.getDeviceNoBranchId(deviceName);//device branch id
            int userId=commonFunctionEjb.isUserIdAvailable(pin);
            
            //get the user details
            UserDetailsModel userDetails=(UserDetailsModel) userManager.getUserDetails(userId).getObject();
            List<Role> roles=userDetails.getRoles();
            
            Integer branchId=-1;
            
            for(Role r: roles){
                if(r.getTypeId()==2){
                    
                    List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                    if(list.size()>0)
                        
                        branchId=list.get(0).getRoleForBranchPK().getBranchId();
                }
            }
            
            if(branchId==-1){
                resultObject.setObject(null);
                resultObject.setMessage("You are not a staff of any branch authorized to Login");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            if(deviceBranchId!=branchId){
                resultObject.setObject(null);
                resultObject.setMessage("Device and User are not from same Branch");
                resultObject.setStatusCode(500);
                return resultObject;
                
            }
            
            LoginOpModel loginOpModel=new LoginOpModel();
            loginOpModel.setUserId(userDetails.getUserId());
            loginOpModel.setName(userDetails.getFname());
            loginOpModel.setPermission(userDetails.getPermissions());
            loginOpModel.setBranchId(branchId);
            Branch branch=commonFunctionEjb.getBranchName(branchId);
            loginOpModel.setBranchName(branch.getName());
            
            //set logs
            Log log=new Log();
            log.setActionId(7);
            log.setActionName("Login As User");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setObjectId(userDetails.getUserId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(userDetails.getUserId());
            User actionByUser=em.find(User.class,userDetails.getUserId());
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            log.setSource("POS : "+deviceName);
            InetAddress IP=InetAddress.getLocalHost();
            log.setIp(IP.toString());
            em.persist(log);
            
            resultObject.setObject(loginOpModel);
            resultObject.setMessage("Successfull Login");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public  ResultObject logout(String deviceName,Integer userId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LogoutOpModel.class);
        try{
            int deviceBranchId=commonFunctionEjb.getDeviceNoBranchId(deviceName);//device branch id
            
            //get the user details
            UserDetailsModel userDetails=(UserDetailsModel) userManager.getUserDetails(userId).getObject();
            List<Role> roles=userDetails.getRoles();
            
            Integer branchId=-1;
            
            for(Role r: roles){
                if(r.getTypeId()==2){
                    
                    List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                    if(list.size()>0)
                        
                        branchId=list.get(0).getRoleForBranchPK().getBranchId();
                }
            }
            
            if(branchId==-1){
                resultObject.setObject(null);
                resultObject.setMessage("You are not a staff of any branch authorized to Logout");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            if(deviceBranchId!=branchId){
                resultObject.setObject(null);
                resultObject.setMessage("Device and User are not from same Branch");
                resultObject.setStatusCode(500);
                return resultObject;
                
            }
            
            List<UserPumpNozzle> userPumpNozzleList=(List<UserPumpNozzle>) em.createQuery("SELECT u FROM UserPumpNozzle u WHERE u.userPumpNozzlePK.userId = :userId").setParameter("userId", userId).getResultList();
            for(UserPumpNozzle upn:userPumpNozzleList){
                
                em.remove(upn);
            }
            
            LogoutOpModel  logoutOpModel=new LogoutOpModel();
            logoutOpModel.setUserId(userDetails.getUserId());
            logoutOpModel.setName(userDetails.getFname());
            logoutOpModel.setPermission(userDetails.getPermissions());
            logoutOpModel.setBranchId(branchId);
            Branch branch=commonFunctionEjb.getBranchName(branchId);
            logoutOpModel.setBranchName(branch.getName());
            
            //set logs
            Log log=new Log();
            log.setActionId(8);
            log.setActionName("Logout As User");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setObjectId(userDetails.getUserId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(userDetails.getUserId());
            User actionByUser=em.find(User.class,userDetails.getUserId());
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            log.setSource("POS : "+deviceName);
            InetAddress IP=InetAddress.getLocalHost();
            log.setIp(IP.toString());
            em.persist(log);
            
            resultObject.setObject(logoutOpModel);
            resultObject.setMessage("Successfull  Logout");
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setMessage("Unsuccessful Logout"+message);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    
//-------------------------------------------------------Sale-----------------------------------------------------------
    
    
    
    public ResultObject sale(SaleDetailsModel saleDetailsModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(SaleDetailsModel.class);
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            
            Date dtt=dateFormat.parse(dt);
            Date deviceDate=dateFormat.parse(saleDetailsModel.getDeviceTransactionTime());
            
            Device device=commonFunctionEjb.getDeviceNoId(saleDetailsModel.getDeviceId());
            String transactionId=device.getDeviceId().toString()+saleDetailsModel.getDeviceTransactionId();
            long traId=Long.parseLong(transactionId);
            
            //----------------------------------------Make Payment in Payment Gate Way---------------------------------------
            
            PaymentResponse paymentResponse=PaymentLibrary.sendTestPaymentXML(traId,saleDetailsModel.getPaymentModeId(),saleDetailsModel.getAmount(),saleDetailsModel.getTelephone(),saleDetailsModel.getVoucherNumber());
            
            //---------------------------------------------------------------------------------------------------------------
            
            Transaction transaction=new Transaction();
            transaction.setTransactionId(traId);
            transaction.setDeviceId(device.getDeviceId());
            transaction.setDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
            transaction.setDeviceTransactionTime(deviceDate);
            transaction.setBranchId(saleDetailsModel.getBranchId());
            transaction.setUserId(saleDetailsModel.getUserId());
            transaction.setPumpId(saleDetailsModel.getPumpId());
            transaction.setProductId(saleDetailsModel.getProductId());
            transaction.setPlatenumber(saleDetailsModel.getPlateNumber());
            transaction.setPaymentModeId(saleDetailsModel.getPaymentModeId());
            transaction.setNozzleId(saleDetailsModel.getNozzleId());
            transaction.setAmount(saleDetailsModel.getAmount());
            transaction.setQuantity(saleDetailsModel.getQuantity());
            transaction.setServerReqTime(dtt);
            transaction.setServerResTime(dtt);
            transaction.setDate(dtt);
            Customer customer=new Customer();
            customer.setName(saleDetailsModel.getName());
            customer.setContactDetails(saleDetailsModel.getTelephone());
            customer.setTin(saleDetailsModel.getTin());
            em.persist(customer);
            em.flush();
            transaction.setCustomerId(customer.getCustomerId());
            
            
            
            Nozzle nozzle=em.find(Nozzle.class, saleDetailsModel.getNozzleId());
            Pump pump=commonFunctionEjb.getPumpName(saleDetailsModel.getPumpId());
            Tank tank=commonFunctionEjb.getTank(pump.getTankId());
            
            //set Tank Tracking
            TankTracking tankTracking=new TankTracking();
            tankTracking.setTransactionId(traId);
            tankTracking.setTransactionTypeId(1);
            tankTracking.setTankId(tank.getTankId());
            tankTracking.setDateTime(dtt);
            
            //set Index Tracking
            IndexTracking indexTracking=new IndexTracking();
            indexTracking.setTransactionId(traId);
            indexTracking.setTransactionTypeId(1);
            indexTracking.setDateTime(dtt);
            
            if(paymentResponse.getReqStatus()==100){
                
                //set Tank Tracking Quantity before and after
                tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                tank.setCurrentCapacity(tank.getCurrentCapacity()-saleDetailsModel.getQuantity());
                em.merge(tank);
                em.flush();
                tankTracking.setQuantityafter(tank.getCurrentCapacity());
                em.persist(tankTracking);
                
                //set Index Tracking indexbefore and after
                indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                nozzle.setNozzleIndex(nozzle.getNozzleIndex()+saleDetailsModel.getQuantity());
                em.merge(nozzle);
                em.flush();
                indexTracking.setIndexafter(nozzle.getNozzleIndex());
                em.persist(indexTracking);
                
                transaction.setPaymentStatus("SUCCESS");
            }
            else
            {
                
                tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                tankTracking.setQuantityafter(tank.getCurrentCapacity());
                em.persist(tankTracking);
                
                indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                indexTracking.setIndexafter(nozzle.getNozzleIndex());
                em.persist(indexTracking);
                
                if(paymentResponse.getReqStatus()==301){
                    transaction.setPaymentStatus("PENDING");
                }
                else{
                    transaction.setAmount(0.0);
                    transaction.setQuantity(0.0);
                    transaction.setPaymentStatus("FAILURE");
                }
            }
            em.persist(transaction);
            
            resultObject.setObject(saleDetailsModel);
            resultObject.setMessage("Sale Details Persist And Payment Status"+" : "+transaction.getPaymentStatus().toUpperCase());
            resultObject.setStatusCode(paymentResponse.getReqStatus());
            return resultObject;
        }
        catch(ParseException e){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
    }
    
    //MoMO Conformation Post a XML ServiceProvison
    public void momoConfirmation(ServiceProvison serviceProvisonIp){
        
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            
            //first check sppaymentresponse table
            SpPaymentRes spPaymentRes=em.find(SpPaymentRes.class, serviceProvisonIp.getSPTRANSID());
            if(spPaymentRes==null){
                SpPaymentRes createspPaymentRes=new SpPaymentRes();
                createspPaymentRes.setSpTransactionId(serviceProvisonIp.getSPTRANSID());
                createspPaymentRes.setTransactionId(serviceProvisonIp.getTRANSID());
                createspPaymentRes.setContractId(serviceProvisonIp.getCONTRACTID());
                createspPaymentRes.setStatusCode(serviceProvisonIp.getSTATUSCODE());
                createspPaymentRes.setStatusDesc(serviceProvisonIp.getSTATUSDESC());
                em.persist(createspPaymentRes);
                em.flush();
            }
            else{
                spPaymentRes.setStatusCode(serviceProvisonIp.getSTATUSCODE());
                spPaymentRes.setStatusDesc(serviceProvisonIp.getSTATUSDESC());
                em.merge(spPaymentRes);
                em.flush();
            }
            
            //After Persist We Update Our Transaction Table
            Transaction transaction=em.find(Transaction.class, serviceProvisonIp.getTRANSID());
            Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
            Pump pump=commonFunctionEjb.getPumpName(transaction.getPumpId());
            Tank tank=commonFunctionEjb.getTank(pump.getTankId());
            
            //After Persist We Update Our IndexTracking Table
            IndexTracking indexTracking=commonFunctionEjb.getIndexTracking(transaction.getTransactionId());
            
            //After Persist We Update Our TankTracking Table
            TankTracking tankTracking=commonFunctionEjb.getTankTracking(transaction.getTransactionId());
            
            if(serviceProvisonIp.getSTATUSCODE()==100){
                
                //set Tank Tracking Quantity before and after
                tankTracking.setDateTime(dtt);
                tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                tank.setCurrentCapacity(tank.getCurrentCapacity()-transaction.getQuantity());
                em.merge(tank);
                em.flush();
                tankTracking.setQuantityafter(tank.getCurrentCapacity());
                em.merge(tankTracking);
                
                //set Index Tracking indexbefore and after
                indexTracking.setDateTime(dtt);
                indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                nozzle.setNozzleIndex(nozzle.getNozzleIndex()+transaction.getQuantity());
                em.merge(nozzle);
                em.flush();
                indexTracking.setIndexafter(nozzle.getNozzleIndex());
                em.merge(indexTracking);
                
                transaction.setPaymentStatus("SUCCESS");
                transaction.setServerResTime(dtt);
            }
            else{
                transaction.setServerResTime(dtt);
                if(serviceProvisonIp.getSTATUSCODE()==301){
                    transaction.setPaymentStatus("PENDING");
                }
                else{
                    transaction.setAmount(0.0);
                    transaction.setQuantity(0.0);
                    transaction.setServerResTime(dtt);
                    transaction.setPaymentStatus("FAILURE");
                }
            }
            em.merge(transaction);
            
            //MoMO Acknowledgement and this function it returning  a header 200
            PaymentLibrary.momoAcknowledgement(serviceProvisonIp);
        }
        catch(ParseException e){
            out.print(e.getMessage());
        }
    }
    
    
//-------------------------------------------------------Sync/Async SaleTransaction------------------------------------
    
    
    public ResultObject asyncSaleTransaction(AsyncTransaction asyncTransactionIp){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(AsyncTransaction.class);
        try{
            Device dev=commonFunctionEjb.getDeviceNoId(asyncTransactionIp.getDeviceId());
            Transaction transaction=(Transaction) em.createQuery("SELECT t FROM Transaction t WHERE t.deviceTransactionId = :deviceTransactionId and t.userId = :userId and t.deviceId = :deviceId and t.branchId = :branchId").setParameter("deviceTransactionId", asyncTransactionIp.getDeviceTransactionId()).setParameter("userId", asyncTransactionIp.getUserId()).setParameter("deviceId", dev.getDeviceId()).setParameter("branchId", asyncTransactionIp.getBranchId()).getSingleResult();
            
            AsyncTransaction transactionAsync=new AsyncTransaction();
            transactionAsync.setUserId(transaction.getUserId());
            Device device=commonFunctionEjb.getDeviceName(transaction.getDeviceId());
            transactionAsync.setDeviceId(device.getDeviceName());
            transactionAsync.setBranchId(transaction.getBranchId());
            transactionAsync.setDeviceTransactionId(transaction.getDeviceTransactionId());
            
            
            resultObject.setObject(transactionAsync);
            resultObject.setMessage("Payment Status"+" "+transaction.getPaymentStatus().toUpperCase());
            
            if(transaction.getPaymentStatus().equalsIgnoreCase("SUCCESS")){
                resultObject.setStatusCode(100);
            }
            else if(transaction.getPaymentStatus().equalsIgnoreCase("PENDING")){
                resultObject.setStatusCode(301);
            }
            else if(transaction.getPaymentStatus().equalsIgnoreCase("CANCEL")){
                resultObject.setStatusCode(500);
            }
            else if(transaction.getPaymentStatus().equalsIgnoreCase("FAILURE")){
                resultObject.setStatusCode(500);
            }
            
            return resultObject;
        }
        catch(NoResultException nre){
            resultObject.setObject(null);
            resultObject.setMessage("No Result Found / Wrong Async Transaction Data");
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public ResultObject syncSaleTransaction(SyncTransaction syncTransactionIp){
        
        ResultObject resultObject=new ResultObject();
        
        List<SaleDetailsModelList> saleDetailsModelList=new ArrayList<>();
        
        try{
            for (SaleDetailsModel sdm : syncTransactionIp.getSaleDetailsModelList()){
                
                resultObject=persistSyncSaleTransaction(sdm);
                SaleDetailsModelList saleDetailsModel=new SaleDetailsModelList();
                saleDetailsModel.setSaleDetailsModel((SaleDetailsModel) resultObject.getObject());
                saleDetailsModel.setMessage(resultObject.getMessage());
                saleDetailsModel.setStatus(resultObject.getStatusCode());
                saleDetailsModelList.add(saleDetailsModel);
            }
            resultObject.setObject(saleDetailsModelList);
            resultObject.setMessage("Sales Successfully Sync");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
    }
    
    public ResultObject persistSyncSaleTransaction(SaleDetailsModel saleDetailsModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(SaleDetailsModelList.class);
        
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            Date deviceDate=dateFormat.parse(saleDetailsModel.getDeviceTransactionTime());
            
            //we check if deviceTransacTionId Is Available
            Transaction transaction=commonFunctionEjb.getDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
            
//create transaction
            if(transaction==null){
                
                Customer customer=new Customer();
                customer.setName(saleDetailsModel.getName());
                customer.setContactDetails(saleDetailsModel.getTelephone());
                customer.setTin(saleDetailsModel.getTin());
                em.persist(customer);
                em.flush();
                
                Device device=commonFunctionEjb.getDeviceNoId(saleDetailsModel.getDeviceId());
                String transactionId=device.getDeviceId().toString()+saleDetailsModel.getDeviceTransactionId();
                long transId=Long.parseLong(transactionId);
                
                Transaction createTransaction=new Transaction();
                createTransaction.setTransactionId(transId);
                createTransaction.setDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
                createTransaction.setDeviceTransactionTime(deviceDate);
                createTransaction.setBranchId(saleDetailsModel.getBranchId());
                createTransaction.setUserId(saleDetailsModel.getUserId());
                createTransaction.setDeviceId(device.getDeviceId());
                createTransaction.setPumpId(saleDetailsModel.getPumpId());
                createTransaction.setNozzleId(saleDetailsModel.getNozzleId());
                createTransaction.setProductId(saleDetailsModel.getProductId());
                createTransaction.setCustomerId(customer.getCustomerId());
                createTransaction.setPaymentModeId(saleDetailsModel.getPaymentModeId());
                createTransaction.setAmount(saleDetailsModel.getAmount());
                createTransaction.setQuantity(saleDetailsModel.getQuantity());
                createTransaction.setPlatenumber(saleDetailsModel.getPlateNumber());
                createTransaction.setServerReqTime(dtt);
                createTransaction.setServerResTime(dtt);
                createTransaction.setDate(dtt);
                
                Nozzle nozzle=em.find(Nozzle.class, saleDetailsModel.getNozzleId());
                Pump pump=commonFunctionEjb.getPumpName(saleDetailsModel.getPumpId());
                Tank tank=commonFunctionEjb.getTank(pump.getTankId());
                
                //set Tank Tracking
                TankTracking tankTracking=new TankTracking();
                tankTracking.setTransactionId(transId);
                tankTracking.setTransactionTypeId(1);
                tankTracking.setTankId(tank.getTankId());
                tankTracking.setDateTime(dtt);
                
                //set Index Tracking
                IndexTracking indexTracking=new IndexTracking();
                indexTracking.setTransactionId(transId);
                indexTracking.setTransactionTypeId(1);
                indexTracking.setDateTime(dtt);
                
                if(saleDetailsModel.getStatus()==100){
                    
                    tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                    tank.setCurrentCapacity(tank.getCurrentCapacity()-saleDetailsModel.getQuantity());
                    em.merge(tank);
                    em.flush();
                    tankTracking.setQuantityafter(tank.getCurrentCapacity());
                    em.persist(tankTracking);
                    
                    indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                    nozzle.setNozzleIndex(nozzle.getNozzleIndex()+saleDetailsModel.getQuantity());
                    em.merge(nozzle);
                    em.flush();
                    indexTracking.setIndexafter(nozzle.getNozzleIndex());
                    em.persist(indexTracking);
                    
                    createTransaction.setPaymentStatus("SUCCESS");
                }
                else{
                    
                    tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                    tankTracking.setQuantityafter(tank.getCurrentCapacity());
                    em.persist(tankTracking);
                    
                    indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                    indexTracking.setIndexafter(nozzle.getNozzleIndex());
                    em.persist(indexTracking);
                    
                    createTransaction.setAmount(0.0);
                    createTransaction.setQuantity(0.0);
                    createTransaction.setPaymentStatus("FAILURE");
                    
                }
                em.persist(createTransaction);
                
                resultObject.setObject(saleDetailsModel);
                resultObject.setMessage("Persist SuccessFully");
                resultObject.setStatusCode(100);
                return resultObject;
            }
//update transaction
            else{
                
                resultObject.setObject(saleDetailsModel);
                resultObject.setMessage("Update SuccessFully");
                resultObject.setStatusCode(100);
                return resultObject;
            }
            
        }
        catch(ParseException e){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
    }
    
    
//-------------------------------------------------------Erroneous Transaction------------------------------------
    
    public ResultObject cancelSale(long deviceTransactionId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Transaction.class);
        
        Transaction transaction=commonFunctionEjb.getDeviceTransactionId(deviceTransactionId);
        if(transaction==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Transaction Available With This Device TransactionId");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //check the transaction list
        List<Transaction> transactionList=(List<Transaction>)em.createQuery("SELECT t FROM Transaction t WHERE t.deviceTransactionId > :deviceTransactionId").setParameter("deviceTransactionId", deviceTransactionId).getResultList();
        if(transactionList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("No Transaction Available");
            resultObject.setStatusCode(500);
            return resultObject;
        }
//        for(Transaction t:transactionList){
//
//            double indexBefore=t.getIndexbefore()-transaction.getQuantity();
//            double indexAfter=t.getIndexafter()-transaction.getQuantity();
//            t.setIndexbefore(indexBefore);
//            t.setIndexafter(indexAfter);
//            em.merge(t);
//        }
        
        //set nozzle right index
        Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
        double index=nozzle.getNozzleIndex()-transaction.getQuantity();
        nozzle.setNozzleIndex(index);
        em.merge(nozzle);
        em.flush();
        
//        //set transaction status
//        transaction.setIndexbefore(0.0);
//        transaction.setIndexafter(0.0);
//        transaction.setAmount(0.0);
//        transaction.setQuantity(0.0);
//        transaction.setPaymentStatus("CANCEL");
//        em.merge(transaction);
//        em.flush();
        
        em.remove(transaction);
        
        resultObject.setObject(null);
        resultObject.setMessage("Transaction Deleted");
        resultObject.setStatusCode(100);
        return resultObject;
        
    }
    
    public ResultObject saleCancel(SaleDetailsModel saleDetailsModel){
        
        ResultObject resultObject= new ResultObject();
        
        Transaction transaction=commonFunctionEjb.getDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
        
        if(transaction==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Transaction Available With This Device TransactionId");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //check the list size for set check sum
        List<ErroneousTransaction> erroneousTransactionList=(List<ErroneousTransaction>)em.createQuery("SELECT e FROM ErroneousTransaction e WHERE e.deviceTransactionId = :deviceTransactionId").setParameter("deviceTransactionId", transaction.getDeviceTransactionId()).getResultList();
        Integer checksum = erroneousTransactionList.size()+1;
        long checkSum = checksum.longValue();
        
        //set erroneous transaction
        ErroneousTransaction erroneousTransaction=new ErroneousTransaction();
        erroneousTransaction.setChecksum(checkSum);
        erroneousTransaction.setTransactionId(transaction.getTransactionId());
        erroneousTransaction.setDeviceTransactionId(transaction.getDeviceTransactionId());
        erroneousTransaction.setDeviceTransactionTime(transaction.getDeviceTransactionTime());
        erroneousTransaction.setBranchId(transaction.getBranchId());
        erroneousTransaction.setUserId(transaction.getUserId());
        erroneousTransaction.setDeviceId(transaction.getDeviceId());
        erroneousTransaction.setPumpId(transaction.getPumpId());
        erroneousTransaction.setNozzleId(transaction.getNozzleId());
        erroneousTransaction.setProductId(transaction.getProductId());
        erroneousTransaction.setCustomerId(transaction.getCustomerId());
        erroneousTransaction.setPaymentModeId(transaction.getPaymentModeId());
        erroneousTransaction.setPaymentStatus(transaction.getPaymentStatus());
        erroneousTransaction.setAmount(transaction.getAmount());
        erroneousTransaction.setQuantity(transaction.getQuantity());
        erroneousTransaction.setPlatenumber(transaction.getPlatenumber());
        erroneousTransaction.setServerReqTime(transaction.getServerReqTime());
        erroneousTransaction.setServerResTime(transaction.getServerResTime());
        erroneousTransaction.setDate(transaction.getDate());
        em.persist(erroneousTransaction);
        em.flush();
        
        //set correct nozzle
        Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
        double correctedIndex=nozzle.getNozzleIndex()-transaction.getQuantity();
        nozzle.setNozzleIndex(correctedIndex);
        em.merge(nozzle);
        em.flush();
        
        //set transaction
        
        
        return null;
    }
    
}


































//    public ResultObject sale(SaleDetailsModel saleDetailsModel){
//
//        ResultObject resultObject=new ResultObject();
//        resultObject.setObjectClass(SaleDetailsModel.class);
//
//        try{
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date();
//            String dt=dateFormat.format(date);
//            Date dtt=dateFormat.parse(dt);
//            Date deviceDate=dateFormat.parse(saleDetailsModel.getDeviceTransactionTime());
//
//            Device device=commonFunctionEjb.getDeviceNoId(saleDetailsModel.getDeviceId());
//            String transactionId=device.getDeviceId().toString()+saleDetailsModel.getDeviceTransactionId();
//            long traId=Long.parseLong(transactionId);
//
//            Transaction transaction=new Transaction();
//            transaction.setTransactionId(traId);
//            transaction.setDeviceId(device.getDeviceId());
//            transaction.setDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
//            transaction.setDeviceTransactionTime(deviceDate);
//            transaction.setBranchId(saleDetailsModel.getBranchId());
//            transaction.setUserId(saleDetailsModel.getUserId());
//            transaction.setPumpId(saleDetailsModel.getPumpId());
//            transaction.setProductId(saleDetailsModel.getProductId());
//            transaction.setPlatenumber(saleDetailsModel.getPlateNumber());
//            transaction.setPaymentModeId(saleDetailsModel.getPaymentModeId());
//            transaction.setNozzleId(saleDetailsModel.getNozzleId());
//            transaction.setAmount(saleDetailsModel.getAmount());
//            transaction.setQuantity(saleDetailsModel.getQuantity());
//            transaction.setServerReqTime(dtt);
//            transaction.setServerResTime(dtt);
//
//            //******************************************* PROCEED CASH PAYMENT ****************************************************************
//
//            if((saleDetailsModel.getPaymentModeId()==1)||(saleDetailsModel.getPaymentModeId()==6)||(saleDetailsModel.getPaymentModeId()==7)||(saleDetailsModel.getPaymentModeId()==8)||(saleDetailsModel.getPaymentModeId()==9)){
//
//                Customer customer=new Customer();
//                customer.setName(saleDetailsModel.getName());
//                customer.setContactDetails(saleDetailsModel.getTelephone());
//                customer.setTin(saleDetailsModel.getTin());
//                em.persist(customer);
//                em.flush();
//
//                Nozzle nozzle=em.find(Nozzle.class, saleDetailsModel.getNozzleId());
//                transaction.setIndexbefore(nozzle.getNozzleIndex());
//                nozzle.setNozzleIndex(nozzle.getNozzleIndex()+saleDetailsModel.getQuantity());
//                em.merge(nozzle);
//                em.flush();
//
//                transaction.setIndexafter(nozzle.getNozzleIndex());
//                transaction.setCustomerId(customer.getCustomerId());
//                transaction.setPaymentStatus("SUCCESS");
//                em.persist(transaction);
//            }
//
//
//            //****************************************** PROCEED VOUCHER PAYMENT ***********************************************************
//
//            else if(saleDetailsModel.getPaymentModeId()==2){
//
//                Voucher voucher =commonFunctionEjb.voucherDetails(saleDetailsModel.getVoucherNumber());
//                if(voucher==null){
//                    resultObject.setObject(null);
//                    resultObject.setMessage("No Voucher Found");
//                    resultObject.setStatusCode(500);
//                    return resultObject;
//                }
//
//                if(voucher.getRemainAmount()<=saleDetailsModel.getAmount()){
//                    resultObject.setObject(null);
//                    resultObject.setMessage("Amount Insufficient In Voucher Found");
//                    resultObject.setStatusCode(500);
//                    return resultObject;
//                }
//
//                voucher.setRemainAmount(voucher.getRemainAmount()-saleDetailsModel.getAmount());
//                em.merge(voucher);
//                em.flush();
//
//                Nozzle nozzle=em.find(Nozzle.class, saleDetailsModel.getNozzleId());
//                transaction.setIndexbefore(nozzle.getNozzleIndex());
//                nozzle.setNozzleIndex(nozzle.getNozzleIndex()+saleDetailsModel.getQuantity());
//                em.merge(nozzle);
//                em.flush();
//
//                transaction.setIndexafter(nozzle.getNozzleIndex());
//                transaction.setCustomerId(voucher.getCustomerId());
//                transaction.setPaymentStatus("SUCCESS");
//                em.persist(transaction);
//            }
//
//
//            //************************************* PROCEED MOBILE MONEY PAYMENT ***********************************************************************
//
//            else if( (saleDetailsModel.getPaymentModeId()==3)||(saleDetailsModel.getPaymentModeId()==4)||(saleDetailsModel.getPaymentModeId()==5) ){
//
//                Customer customer=new Customer();
//                customer.setName(saleDetailsModel.getName());
//                customer.setContactDetails(saleDetailsModel.getTelephone());
//                customer.setTin(saleDetailsModel.getTin());
//                em.persist(customer);
//                em.flush();
//
//                transaction.setCustomerId(customer.getCustomerId());
//                transaction.setPaymentStatus("PENDING");
//                em.persist(transaction);
//
//                String ps="";
//
//                if(saleDetailsModel.getPaymentModeId()==3){
//                    ps="2484";
//                }
//                else if(saleDetailsModel.getPaymentModeId()==4){
//                    ps="3382";
//                }
//                else if(saleDetailsModel.getPaymentModeId()==5){
//                    ps="5728";
//                }
//
//
//                //this function is for posting data for mobile money request
//                String payXmlReturnData=sendPaymentXML(ps,traId,saleDetailsModel.getTelephone(),saleDetailsModel.getAmount());
//
//            }
//
//            //********************************************* Set ResultObject *****************************************
//
//            resultObject.setObject(saleDetailsModel);
//            resultObject.setMessage("Sale Details Persist And Payment Status"+" : "+transaction.getPaymentStatus().toUpperCase());
//
//            if(transaction.getPaymentStatus().equalsIgnoreCase("SUCCESS")){
//                resultObject.setStatusCode(100);
//            }
//            else if(transaction.getPaymentStatus().equalsIgnoreCase("PENDING")){
//                resultObject.setStatusCode(301);
//            }
//            else if(transaction.getPaymentStatus().equalsIgnoreCase("CANCEL")){
//                resultObject.setStatusCode(500);
//            }
//            else if(transaction.getPaymentStatus().equalsIgnoreCase("FAILURE")){
//                resultObject.setStatusCode(500);
//            }
//
//            return resultObject;
//
//        }
//        catch(Exception e){
//            resultObject.setObject(null);
//            resultObject.setStatusCode(500);
//            resultObject.setMessage(e.getMessage());
//            return resultObject;
//        }
//    }