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
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
    @Consumes({"application/xml", "application/json"})
    public String sale(SaleDetailsModel saleDetailsModel) {
        
        ResultObject result= androidDataManager.sale(saleDetailsModel);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
 
    
    //service provison conformation link, after  mobile money payment the payment gateway post payment status on this link
    @POST
    @Path("pos/momoConfirmation")
    public void momoConfirmation(InputStream is) throws Exception{
        
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
