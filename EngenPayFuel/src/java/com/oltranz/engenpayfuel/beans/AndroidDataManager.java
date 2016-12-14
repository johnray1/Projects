/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Customer;
import com.oltranz.engenpayfuel.entities.Device;
import com.oltranz.engenpayfuel.entities.ErroneousTransaction;
import com.oltranz.engenpayfuel.entities.IndexTracking;
import com.oltranz.engenpayfuel.entities.Log;
import com.oltranz.engenpayfuel.entities.Nozzle;
import com.oltranz.engenpayfuel.entities.Pump;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleForBranch;
import com.oltranz.engenpayfuel.entities.SpPaymentRes;
import com.oltranz.engenpayfuel.entities.Tank;
import com.oltranz.engenpayfuel.entities.TankTracking;
import com.oltranz.engenpayfuel.entities.Transaction;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.entities.UserPumpNozzle;
import com.oltranz.engenpayfuel.library.CommonLibrary;
import com.oltranz.engenpayfuel.library.PaymentLibrary;
import com.oltranz.engenpayfuel.models.AsyncTransaction;
import com.oltranz.engenpayfuel.models.AuthenticationModel;
import com.oltranz.engenpayfuel.models.LoginOpModel;
import com.oltranz.engenpayfuel.models.LogoutOpModel;
import com.oltranz.engenpayfuel.models.PaymentResponse;
import com.oltranz.engenpayfuel.models.ReportModel;
import com.oltranz.engenpayfuel.models.ReportShift;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.SaleDetailsModel;
import com.oltranz.engenpayfuel.models.SaleDetailsModelList;
import com.oltranz.engenpayfuel.models.ServiceProvison;
import com.oltranz.engenpayfuel.models.SyncTransaction;
import com.oltranz.engenpayfuel.models.SaleCancelModel;
import com.oltranz.engenpayfuel.models.SaleEditModel;
import com.oltranz.engenpayfuel.models.UserDetailsModel;
import java.io.IOException;
import static java.lang.System.out;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

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
//-------------------------------------------------------Login/Logout----------------------------------------------------------------------------------------------
    
    public  ResultObject login(String deviceName, String pin){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LoginOpModel.class);
        try{
            int deviceBranchId=commonFunctionEjb.getDeviceNoBranchId(deviceName);//device branch id
            int userId=commonFunctionEjb.isUserIdAvailable(pin);
            
//            //check user status
//            User user=em.find(User.class, userId);
//            if(user==null){
//                resultObject.setObject(null);
//                resultObject.setMessage("User Not Found");
//                resultObject.setStatusCode(500);
//                return resultObject;
//            }
//
//            if(user.getStatus()==8){
//                resultObject.setObject(null);
//                resultObject.setMessage("You are Logged in an Another Device");
//                resultObject.setStatusCode(500);
//                return resultObject;
//            }
//            user.setStatus(8);
//            em.merge(user);
//            em.flush();
//            //Endcheck user status
            
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
            
            
            /*
            ReportModel rm=new ReportModel();
            rm.setUserId(userDetails.getUserId());
            rm.setBranchId(userDetails.getBranchId());
            rm.setType(2);
            postCreateReportModule(rm);
            */
            
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
            
//            //check user status
//            User user=em.find(User.class, userId);
//            if(user==null){
//                resultObject.setObject(null);
//                resultObject.setMessage("User Not Found");
//                resultObject.setStatusCode(500);
//                return resultObject;
//            }
//            user.setStatus(7);
//            em.merge(user);
//            em.flush();
//            //End check user status
            
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
            
            
            /*
            ReportModel rm=new ReportModel();
            rm.setUserId(userDetails.getUserId());
            rm.setBranchId(userDetails.getBranchId());
            rm.setType(2);
            postEndReportModule(rm);
            */
            
            
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
    
    
//-------------------------------------------------------Sale-------------------------------------------------------------------------------------------------------
    
    
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
            
            
            //first check transaction
            Transaction checkTransaction=em.find(Transaction.class,saleDetailsModel.getDeviceTransactionId());
            if(checkTransaction!=null){
                
                resultObject.setObject(saleDetailsModel);
                resultObject.setMessage(saleDetailsModel.getDeviceTransactionId()+" Is Already Available and PAYMENT Status Is :"+checkTransaction.getPaymentStatus());
                
                if(checkTransaction.getPaymentStatus().equalsIgnoreCase("SUCCESS")){
                    resultObject.setStatusCode(100);
                }
                if(checkTransaction.getPaymentStatus().equalsIgnoreCase("PENDING")){
                    resultObject.setStatusCode(301);
                }
                if(checkTransaction.getPaymentStatus().equalsIgnoreCase("FAILURE")){
                    resultObject.setStatusCode(500);
                }
                
                return resultObject;
            }
            
            
            //set the transaction data
            Transaction transaction=new Transaction();
            transaction.setTransactionId(saleDetailsModel.getDeviceTransactionId());
            transaction.setDeviceId(device.getDeviceId());
            transaction.setDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
            transaction.setDeviceTransactionTime(deviceDate);
            transaction.setBranchId(saleDetailsModel.getBranchId());
            transaction.setUserId(saleDetailsModel.getUserId());
            transaction.setTankId(commonFunctionEjb.getNozzle(saleDetailsModel.getNozzleId()).getTankId());
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
            if(saleDetailsModel.getPaymentModeId()==2){
                customer.setCustomerTypeId(1);
            }
            else{
                customer.setCustomerTypeId(2);
            }
            em.persist(customer);
            em.flush();
            
            transaction.setCustomerId(customer.getCustomerId());
            
            //now we get nozzle index and tank quantity
            Nozzle nozzle=em.find(Nozzle.class, saleDetailsModel.getNozzleId());
            Pump pump=commonFunctionEjb.getPumpName(saleDetailsModel.getPumpId());
            Tank tank=commonFunctionEjb.getTank(nozzle.getTankId());
            
            //set Index Tracking
            IndexTracking indexTracking=new IndexTracking();
            indexTracking.setTransactionId(saleDetailsModel.getDeviceTransactionId());
            indexTracking.setTransactionTypeId(1);
            indexTracking.setUserId(saleDetailsModel.getUserId());
            indexTracking.setDateTime(dtt);
            
            //set Tank Tracking
            TankTracking tankTracking=new TankTracking();
            tankTracking.setTankId(tank.getTankId());
            tankTracking.setTransactionId(saleDetailsModel.getDeviceTransactionId());
            tankTracking.setTransactionTypeId(1);
            tankTracking.setUserId(saleDetailsModel.getUserId());
            tankTracking.setDateTime(dtt);
            
            //----------------------------------------Make Payment in Payment Gate Way------------------------------------------------------------------------------
            
            PaymentResponse paymentResponse=PaymentLibrary.sendTestPaymentXML(saleDetailsModel.getDeviceTransactionId(),saleDetailsModel.getPaymentModeId(),saleDetailsModel.getAmount(),saleDetailsModel.getTelephone(),saleDetailsModel.getVoucherNumber());
            
            //------------------------------------------------------------------------------------------------------------------------------------------------------
            
            
            if(paymentResponse.getReqStatus()==100){
                
                //set Index Tracking indexbefore and after
                indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                nozzle.setNozzleIndex(nozzle.getNozzleIndex()+saleDetailsModel.getQuantity());
                em.merge(nozzle);
                em.flush();
                indexTracking.setIndexafter(nozzle.getNozzleIndex());
                em.persist(indexTracking);
                
                //set Tank Tracking Quantity before and after
                tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                tank.setCurrentCapacity(tank.getCurrentCapacity()-saleDetailsModel.getQuantity());
                em.merge(tank);
                em.flush();
                tankTracking.setQuantityafter(tank.getCurrentCapacity());
                em.persist(tankTracking);
                
                
                
                transaction.setPaymentStatus("SUCCESS");
            }
            else
            {
                //set Index Tracking indexbefore and after
                indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                indexTracking.setIndexafter(nozzle.getNozzleIndex());
                em.persist(indexTracking);
                
                //set Tank Tracking Quantity before and after
                tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                tankTracking.setQuantityafter(tank.getCurrentCapacity());
                em.persist(tankTracking);
                
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
            em.flush();
            
            resultObject.setObject(saleDetailsModel);
            resultObject.setMessage("Sale Details Persist And Payment Status"+" : "+transaction.getPaymentStatus().toUpperCase());
            if(transaction.getPaymentStatus().equalsIgnoreCase("SUCCESS")){
                resultObject.setStatusCode(100);
            }
            if(transaction.getPaymentStatus().equalsIgnoreCase("PENDING")){
                resultObject.setStatusCode(301);
            }
            if(transaction.getPaymentStatus().equalsIgnoreCase("FAILURE")){
                resultObject.setStatusCode(500);
            }
            
            return resultObject;
        }
        catch(Exception ex){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Error Rise On Exception"+ex.getMessage());
            return resultObject;
        }
    }
    
    
    
    public void momoConfirmation(ServiceProvison serviceProvisonIp){
        
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            
            //first check sppaymentresponse table
            SpPaymentRes spPaymentRes=em.find(SpPaymentRes.class, serviceProvisonIp.getSPTRANSID());
            if(spPaymentRes==null){
                //create new SpPaymentRes
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
                //update existing SpPaymentRes
                spPaymentRes.setStatusCode(serviceProvisonIp.getSTATUSCODE());
                spPaymentRes.setStatusDesc(serviceProvisonIp.getSTATUSDESC());
                em.merge(spPaymentRes);
                em.flush();
            }
            
            //After Persist We Update Our Transaction Table
            Transaction transaction=em.find(Transaction.class, serviceProvisonIp.getTRANSID());
            
            //now we get nozzle index and tank quantity
            Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
            Pump pump=commonFunctionEjb.getPumpName(transaction.getPumpId());
            Tank tank=commonFunctionEjb.getTank(nozzle.getTankId());
            
            //After Persist We Update Our IndexTracking Table
            IndexTracking indexTracking=commonFunctionEjb.getIndexTracking(transaction.getTransactionId());
            
            //After Persist We Update Our TankTracking Table
            TankTracking tankTracking=commonFunctionEjb.getTankTracking(transaction.getTransactionId());
            
            if(serviceProvisonIp.getSTATUSCODE()==100){
                
                //set Index Tracking indexbefore and after
                indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                nozzle.setNozzleIndex(nozzle.getNozzleIndex()+transaction.getQuantity());
                em.merge(nozzle);
                em.flush();
                indexTracking.setIndexafter(nozzle.getNozzleIndex());
                indexTracking.setDateTime(dtt);
                em.merge(indexTracking);
                
                //set Tank Tracking Quantity before and after
                tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                tank.setCurrentCapacity(tank.getCurrentCapacity()-transaction.getQuantity());
                em.merge(tank);
                em.flush();
                tankTracking.setQuantityafter(tank.getCurrentCapacity());
                tankTracking.setDateTime(dtt);
                em.merge(tankTracking);
                
                transaction.setServerResTime(dtt);
                transaction.setPaymentStatus("SUCCESS");
                
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
            
            //--------------------------------------------------MoMO Acknowledgement--------------------------------------------------------------------------------
            
            PaymentLibrary.momoAcknowledgement(serviceProvisonIp);
            
            //------------------------------------------------------------------------------------------------------------------------------------------------------
        }
        catch(ParseException e){
            out.print(e.getMessage());
        }
    }
    
    
//-------------------------------------------------------Sync/Async SaleTransaction---------------------------------------------------------------------------------
    
    
    public ResultObject asyncSaleTransaction(AsyncTransaction asyncTransactionIp){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(AsyncTransaction.class);
        try{
            Device dev=commonFunctionEjb.getDeviceNoId(asyncTransactionIp.getDeviceId());
            
            //get the transaction
            Transaction transaction=(Transaction) em.createQuery("SELECT t FROM Transaction t WHERE t.deviceTransactionId = :deviceTransactionId and t.userId = :userId and t.deviceId = :deviceId and t.branchId = :branchId").setParameter("deviceTransactionId", asyncTransactionIp.getDeviceTransactionId()).setParameter("userId", asyncTransactionIp.getUserId()).setParameter("deviceId", dev.getDeviceId()).setParameter("branchId", asyncTransactionIp.getBranchId()).getSingleResult();
            
            //set the transaction data to AsyncTransaction model
            AsyncTransaction transactionAsync=new AsyncTransaction();
            transactionAsync.setUserId(transaction.getUserId());
            Device device=commonFunctionEjb.getDeviceName(transaction.getDeviceId());
            transactionAsync.setDeviceId(device.getDeviceName());
            transactionAsync.setBranchId(transaction.getBranchId());
            transactionAsync.setDeviceTransactionId(transaction.getDeviceTransactionId());
            
            //set the resultobject
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
            
            if(transaction==null){
                //create transaction
                Device device=commonFunctionEjb.getDeviceNoId(saleDetailsModel.getDeviceId());
                String transactionId=device.getDeviceId().toString()+saleDetailsModel.getDeviceTransactionId();
                long transId=Long.parseLong(transactionId);
                
                //set the transaction data
                Transaction createTransaction=new Transaction();
                createTransaction.setTransactionId(transId);
                createTransaction.setDeviceTransactionId(saleDetailsModel.getDeviceTransactionId());
                createTransaction.setDeviceTransactionTime(deviceDate);
                createTransaction.setBranchId(saleDetailsModel.getBranchId());
                createTransaction.setUserId(saleDetailsModel.getUserId());
                createTransaction.setDeviceId(device.getDeviceId());
                createTransaction.setTankId(commonFunctionEjb.getNozzle(saleDetailsModel.getNozzleId()).getTankId());
                createTransaction.setPumpId(saleDetailsModel.getPumpId());
                createTransaction.setNozzleId(saleDetailsModel.getNozzleId());
                createTransaction.setProductId(saleDetailsModel.getProductId());
                createTransaction.setPaymentModeId(saleDetailsModel.getPaymentModeId());
                createTransaction.setAmount(saleDetailsModel.getAmount());
                createTransaction.setQuantity(saleDetailsModel.getQuantity());
                createTransaction.setPlatenumber(saleDetailsModel.getPlateNumber());
                createTransaction.setServerReqTime(dtt);
                createTransaction.setServerResTime(dtt);
                createTransaction.setDate(dtt);
                Customer customer=new Customer();
                customer.setName(saleDetailsModel.getName());
                customer.setContactDetails(saleDetailsModel.getTelephone());
                customer.setTin(saleDetailsModel.getTin());
                em.persist(customer);
                em.flush();
                createTransaction.setCustomerId(customer.getCustomerId());
                
                //now we get nozzle index and tank quantity
                Nozzle nozzle=em.find(Nozzle.class, saleDetailsModel.getNozzleId());
                Pump pump=commonFunctionEjb.getPumpName(saleDetailsModel.getPumpId());
                Tank tank=commonFunctionEjb.getTank(nozzle.getTankId());
                
                
                
                //set Index Tracking
                IndexTracking indexTracking=new IndexTracking();
                indexTracking.setTransactionId(transId);
                indexTracking.setTransactionTypeId(1);
                indexTracking.setUserId(saleDetailsModel.getUserId());
                indexTracking.setDateTime(dtt);
                
                //set Tank Tracking
                TankTracking tankTracking=new TankTracking();
                tankTracking.setTankId(tank.getTankId());
                tankTracking.setTransactionId(transId);
                tankTracking.setTransactionTypeId(1);
                tankTracking.setUserId(saleDetailsModel.getUserId());
                tankTracking.setDateTime(dtt);
                
                //----------------------------------------Make Payment in Payment Gate Way------------------------------------------------------------------------------
                
                PaymentResponse paymentResponse=PaymentLibrary.sendTestPaymentXML(transId,saleDetailsModel.getPaymentModeId(),saleDetailsModel.getAmount(),saleDetailsModel.getTelephone(),saleDetailsModel.getVoucherNumber());
                
                //------------------------------------------------------------------------------------------------------------------------------------------------------
                
                
                if(paymentResponse.getReqStatus()==100){
                    
                    //set Index Tracking indexbefore and after
                    indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                    nozzle.setNozzleIndex(nozzle.getNozzleIndex()+saleDetailsModel.getQuantity());
                    em.merge(nozzle);
                    em.flush();
                    indexTracking.setIndexafter(nozzle.getNozzleIndex());
                    em.persist(indexTracking);
                    
                    //set Tank Tracking Quantity before and after
                    tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                    tank.setCurrentCapacity(tank.getCurrentCapacity()-saleDetailsModel.getQuantity());
                    em.merge(tank);
                    em.flush();
                    tankTracking.setQuantityafter(tank.getCurrentCapacity());
                    em.persist(tankTracking);
                    
                    createTransaction.setPaymentStatus("SUCCESS");
                }
                else{
                    
                    //set Index Tracking indexbefore and after
                    indexTracking.setIndexbefore(nozzle.getNozzleIndex());
                    indexTracking.setIndexafter(nozzle.getNozzleIndex());
                    em.persist(indexTracking);
                    
                    //set Tank Tracking Quantity before and after
                    tankTracking.setQuantitybefore(tank.getCurrentCapacity());
                    tankTracking.setQuantityafter(tank.getCurrentCapacity());
                    em.persist(tankTracking);
                    
                    if(paymentResponse.getReqStatus()==301){
                        createTransaction.setPaymentStatus("PENDING");
                    }
                    else{
                        createTransaction.setAmount(0.0);
                        createTransaction.setQuantity(0.0);
                        createTransaction.setPaymentStatus("FAILURE");
                    }
                    
                }
                
                em.persist(createTransaction);
                
                resultObject.setObject(saleDetailsModel);
                resultObject.setMessage("Persist SuccessFully");
                resultObject.setStatusCode(100);
                return resultObject;
            }
            else{
                //update transaction
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
    
    
//-------------------------------------------------------Erroneous Transaction--------------------------------------------------------------------------------------
    
    
    public  ResultObject adminLogin(AuthenticationModel  authenticationModel){
        
        ResultObject resultObject=new ResultObject();
        
        try{
            //Authenticating email and password
            resultObject=userManager.authenticateWebUser(authenticationModel.getEmail(), authenticationModel.getPassword());
            if(resultObject.getObject()==null){
                resultObject.setMessage("Unsuccessful Authentication");
                resultObject.setObjectClass(LoginOpModel.class);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            //get the user details
            UserDetailsModel userDetails= (UserDetailsModel) resultObject.getObject();
            
            LoginOpModel loginOpModel=new LoginOpModel();
            loginOpModel.setUserId(userDetails.getUserId());
            loginOpModel.setName(userDetails.getFname());
            loginOpModel.setBranchId(userDetails.getBranchId());
            loginOpModel.setBranchName(userDetails.getBranchName());
            
            //set logs
            Log log=new Log();
            log.setActionId(7);
            log.setActionName("Login As Authorised User");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setObjectId(userDetails.getUserId()); //user object id =1
            log.setObjectName("Authorized User");
            log.setUserId(userDetails.getUserId());
            User actionByUser=em.find(User.class,userDetails.getUserId());
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            log.setSource("POS ADMIN LOGIN");
            InetAddress IP=InetAddress.getLocalHost();
            log.setIp(IP.toString());
            em.persist(log);
            
            resultObject.setObjectClass(LoginOpModel.class);
            resultObject.setObject(loginOpModel);
            resultObject.setMessage("Successful Authentication");
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
    
    public ResultObject saleEdit(SaleEditModel saleEditModel){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(SaleEditModel.class);
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            Date deviceDate=dateFormat.parse(saleEditModel.getDeviceTransactionTime());
            
            Transaction transaction=commonFunctionEjb.getDeviceTransactionId(saleEditModel.getDeviceTransactionId());
            if(transaction==null){
                resultObject.setObject(null);
                resultObject.setMessage("No Transaction Available With This Device TransactionId");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            //now in this function we set nozzleIndex(by subtracting quantity) and tankQuantity to correct data(by adding quantity)
            ErroneousTransaction createErroneousTransaction=commonFunctionEjb.createErroneousTransaction(transaction);
            
            //After We set Edited Transaction
            transaction.setAmount(saleEditModel.getAmount());
            transaction.setQuantity(saleEditModel.getQuantity());
            transaction.setDeviceTransactionTime(deviceDate);
            transaction.setServerReqTime(dtt);
            transaction.setServerResTime(dtt);
            transaction.setDate(dtt);
            em.merge(transaction);
            em.flush();
            
            //now we get corrected nozzle index and tank quantity
            Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
            Pump pump=commonFunctionEjb.getPumpName(transaction.getPumpId());
            Tank tank=commonFunctionEjb.getTank(nozzle.getTankId());
            
            //now we set index tracking
            IndexTracking indexTracking=commonFunctionEjb.getIndexTracking(transaction.getTransactionId());
            indexTracking.setIndexbefore(nozzle.getNozzleIndex());
            nozzle.setNozzleIndex(nozzle.getNozzleIndex()+transaction.getQuantity());
            em.merge(nozzle);
            //need to check
            em.flush();
            indexTracking.setIndexafter(nozzle.getNozzleIndex());
            indexTracking.setTransactionTypeId(2);
            indexTracking.setDateTime(dtt);
            em.merge(indexTracking);
            
            //now we set tank tracking
            TankTracking tankTracking=commonFunctionEjb.getTankTracking(transaction.getTransactionId());
            tankTracking.setTransactionTypeId(2);
            tankTracking.setTankId(tank.getTankId());
            tankTracking.setDateTime(dtt);
            tankTracking.setQuantitybefore(tank.getCurrentCapacity());
            tank.setCurrentCapacity(tank.getCurrentCapacity()-transaction.getQuantity());
            em.merge(tank);
            em.flush();
            tankTracking.setQuantityafter(tank.getCurrentCapacity());
            em.merge(tankTracking);
            
            
            
            resultObject.setObject(saleEditModel);
            resultObject.setMessage("Sale Edits Successful And Payment Status Of The Sale is"+" : "+transaction.getPaymentStatus().toUpperCase());
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(ParseException e){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
    }
    
    
    
    
    public ResultObject saleCancel(SaleCancelModel saleCancelModel){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(SaleCancelModel.class);
        try{
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            Date deviceDate=dateFormat.parse(saleCancelModel.getDeviceTransactionTime());
            
            Transaction transaction=commonFunctionEjb.getDeviceTransactionId(saleCancelModel.getDeviceTransactionId());
            if(transaction==null){
                resultObject.setObject(null);
                resultObject.setMessage("No Transaction Available With This Device TransactionId");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            //now in this function we set nozzleIndex(by subtracting quantity) and tankQuantity to correct data(by adding quantity)
            ErroneousTransaction createErroneousTransaction=commonFunctionEjb.createErroneousTransaction(transaction);
            
            //now we get corrected nozzle index and tank quantity
            Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
            Pump pump=commonFunctionEjb.getPumpName(transaction.getPumpId());
            Tank tank=commonFunctionEjb.getTank(nozzle.getTankId());
            
            //now we set index tracking
            IndexTracking indexTracking=commonFunctionEjb.getIndexTracking(transaction.getTransactionId());
            indexTracking.setIndexbefore(nozzle.getNozzleIndex());
            indexTracking.setIndexafter(nozzle.getNozzleIndex());
            indexTracking.setTransactionTypeId(3);
            indexTracking.setDateTime(dtt);
            em.merge(indexTracking);
            em.flush();
            
            //now we set tank tracking
            TankTracking tankTracking=commonFunctionEjb.getTankTracking(transaction.getTransactionId());
            tankTracking.setQuantitybefore(tank.getCurrentCapacity());
            tankTracking.setQuantityafter(tank.getCurrentCapacity());
            tankTracking.setTransactionTypeId(3);
            tankTracking.setDateTime(dtt);
            em.merge(tankTracking);
            em.flush();
            
            //now we update transaction
            transaction.setQuantity(0.0);
            transaction.setAmount(0.0);
            transaction.setDeviceTransactionTime(deviceDate);
            transaction.setServerReqTime(dtt);
            transaction.setServerResTime(dtt);
            transaction.setDate(dtt);
            transaction.setPaymentStatus("CANCEL");
            em.merge(transaction);
            em.flush();
            
            resultObject.setObject(saleCancelModel);
            resultObject.setMessage("Sale Cancel Successful And Payment Status Of The Sale is"+" : "+transaction.getPaymentStatus().toUpperCase());
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(ParseException e){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("Unparseable or Wrong Date Format");
            return resultObject;
        }
        
    }
    
    
    
    public void postCreateReportModule(ReportModel rm){
        try {
            ObjectMapper mapper=new ObjectMapper();
            String url="http://41.74.172.132:8080/ReportEngen/ReportWebService/report/create";
            String jsonData = mapper.writeValueAsString(rm);
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            ReportShift reportShift=(ReportShift)mapper.readValue(jsonResponse, ReportShift.class);
        }
        catch (IOException ex) {
            Logger.getLogger(AndroidDataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void postEndReportModule(ReportModel rm){
        try {
            ObjectMapper mapper=new ObjectMapper();
            String url="http://41.74.172.132:8080/ReportEngen/ReportWebService/report/end";
            String jsonData = mapper.writeValueAsString(rm);
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            ReportShift reportShift=(ReportShift)mapper.readValue(jsonResponse, ReportShift.class);
        }
        catch (IOException ex) {
            Logger.getLogger(AndroidDataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}


































