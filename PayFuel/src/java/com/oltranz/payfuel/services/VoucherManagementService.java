/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.VoucherManager;
import com.oltranz.payfuel.entities.Voucher;
import com.oltranz.payfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author John
 */
@Path("VoucherManagementService")
@Stateless
public class VoucherManagementService {
    
    @EJB
    private VoucherManager voucherManager;
    
    @POST
    @Path("voucher/create")
    @Consumes({"application/xml", "application/json"})
    public String createCompany(Voucher newVoucher) {
        
        ResultObject result=voucherManager.createVoucher(newVoucher);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("voucher/edit")
    @Consumes({"application/xml", "application/json"})
    public String editVoucher(Voucher editVoucher) {
        
        ResultObject result=voucherManager.editVoucher(editVoucher);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("voucher/{voucherNo}")
    @Produces({"application/xml", "application/json"})
    public String getVoucher(@PathParam("voucherNo") String voucherNo) {
        
        ResultObject result= voucherManager.getVoucher(voucherNo);
        return result.getJsonFormat();
    }
}
