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
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
}
