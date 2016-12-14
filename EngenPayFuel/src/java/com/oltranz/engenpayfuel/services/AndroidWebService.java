/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.AndroidDataManager;
import com.oltranz.engenpayfuel.library.CommonLibrary;
import com.oltranz.engenpayfuel.models.AsyncTransaction;
import com.oltranz.engenpayfuel.models.AuthenticationModel;
import com.oltranz.engenpayfuel.models.LoginIpModel;
import com.oltranz.engenpayfuel.models.LogoutIpModel;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.SaleDetailsModel;
import com.oltranz.engenpayfuel.models.ServiceProvison;
import com.oltranz.engenpayfuel.models.SyncTransaction;
import com.oltranz.engenpayfuel.models.SaleCancelModel;
import com.oltranz.engenpayfuel.models.SaleEditModel;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@Stateless
@Path("AndroidWebService")
public class AndroidWebService {
    
    @EJB
    private AndroidDataManager androidDataManager;
    
    
    
    
    @POST
    @Path("pos/login")
    @Consumes({"application/xml", "application/json"})
    public String login(LoginIpModel validateLoginIp) {
        
        ResultObject result= androidDataManager.login(validateLoginIp.getDeviceId(),validateLoginIp.getUserPin());
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    
    
    @POST
    @Path("pos/logout")
    @Consumes({"application/xml", "application/json"})
    public String logout(LogoutIpModel validateLogout) {
        
        ResultObject result= androidDataManager.logout(validateLogout.getDeviceId(),validateLogout.getUserId());
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    

    
    @POST
    @Path("pos/sale")
    public String sale(InputStream is) {
        ResultObject result=new ResultObject();
        String jsonData = null;
        try {
            jsonData = CommonLibrary.inputStream2StringTest(is);
            out.print("Time ["+(new Date())+"] - AndroidWebService/pos/sale - InPutRequest: Received string ||"+jsonData+"\n");
        } catch (IOException ex) {
            out.print("Time ["+(new Date())+"] - AndroidWebService/pos/sale - InPutRequest: CATCH BLOCK ||"+ex.getMessage()+"\n");
            ex.printStackTrace();
        }
        
        
        ObjectMapper mapper=new ObjectMapper();
        SaleDetailsModel sdm = new SaleDetailsModel();
        try {
            sdm = (SaleDetailsModel)mapper.readValue(jsonData, SaleDetailsModel.class);
        } catch (IOException ex) {
            out.print("Time ["+(new Date())+"] - AndroidWebService/pos/sale - MAPPING TO A OBJECT: CATCH BLOCK ||"+ex.getMessage()+"\n");
            ex.printStackTrace();
        }
        
        try{
            Long.parseLong(sdm.getDeviceTransactionId()+"");
        }
        catch(Exception ex){
            result.setObject(null);
            result.setMessage(sdm.getDeviceTransactionId()+"Invalid Transaction Id ||"+ex.getMessage()+"\n");
            result.setStatusCode(500);
            return result.getJsonFormat();
        }
        
        out.print("Time ["+(new Date())+"] - AndroidWebService/pos/sale - InPutData ||"
                
                +"DEVICE TRANSACTION ID:"+sdm.getDeviceTransactionId()
                +"| DEVICE TRANSACTION TIME:"+sdm.getDeviceTransactionTime()
                +"| BRANCH ID:"+sdm.getBranchId()
                +"| USER ID:"+sdm.getUserId()
                +"| DEVICE ID:"+sdm.getDeviceId()
                +"| PUMP ID:"+sdm.getPumpId()
                +"| NOZZLE ID:"+sdm.getNozzleId()
                +"| PRODUCT ID:"+sdm.getProductId()
                +"| PAYMENTMODE ID:"+sdm.getPaymentModeId()
                +"| AMOUNT:"+sdm.getAmount()
                +"| QUANTITY:"+sdm.getQuantity()
                +"| PLATE NO:"+sdm.getPlateNumber()
                +"| NAME:"+sdm.getName()
                +"| TELEPHONE:"+sdm.getTelephone()
                +"| TIN:"+sdm.getTin()
                +"| VOUCHER NO:"+sdm.getVoucherNumber()
                +"| AUTHANTICATION CODE:"+sdm.getAuthenticationCode()
                +"| AUTHORISATION CODE:"+sdm.getAuthorisationCode()
                +"| STATUS:"+sdm.getStatus());
        
        result= androidDataManager.sale(sdm);
        String jsonResult=result.getJsonFormat();
        
        out.print("Time ["+(new Date())+"] - AndroidWebService/pos/sale - InPutResponse: Received string ||"+jsonResult+"\n");
        
        return jsonResult;
    }
    
    
    
    
    //service provison conformation link, after  mobile money payment the payment gateway post payment status on this link
    @POST
    @Path("pos/momoConfirmation")
    public void momoConfirmation(InputStream is){
        
        ServiceProvison serviceProvisonIp=(ServiceProvison) CommonLibrary.unmarshalling(is, ServiceProvison.class);
        
        androidDataManager.momoConfirmation(serviceProvisonIp);
    }
    
    
    
    //async for mobile money
    @POST
    @Path("pos/sale/async")
    @Consumes({"application/xml", "application/json"})
    public String asyncSaleTransaction(AsyncTransaction asyncTransactionIp) {
        
        ResultObject result= androidDataManager.asyncSaleTransaction(asyncTransactionIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    //sync for mobile money
    @POST
    @Path("pos/sale/sync")
    @Consumes({"application/xml", "application/json"})
    public String syncSaleTransaction(SyncTransaction syncTransactionIp) {
        
        ResultObject result= androidDataManager.syncSaleTransaction(syncTransactionIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @POST
    @Path("pos/adminLogin")
    @Consumes({"application/xml", "application/json"})
    public String adminLogin(AuthenticationModel  authenticationModelIp) {
        
        ResultObject result= androidDataManager.adminLogin(authenticationModelIp);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @POST
    @Path("pos/saleEdit")
    @Consumes({"application/xml", "application/json"})
    public String saleEdit(SaleEditModel saleEditModel) {
        
        ResultObject result= androidDataManager.saleEdit(saleEditModel);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("pos/saleCancel")
    @Consumes({"application/xml", "application/json"})
    public String saleCancel(SaleCancelModel saleCancelModel) {
        
        ResultObject result= androidDataManager.saleCancel(saleCancelModel);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
}
