/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Customer;
import com.oltranz.engenpayfuel.entities.Voucher;
import com.oltranz.engenpayfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class VoucherManager {
    
    @PersistenceContext
    private EntityManager em;
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    public ResultObject createVoucher(Voucher newVoucher){
        
        //set the returntype resultset object
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Customer.class);
        
        //check if voucher name is available before
        long voucherIsAvailable=commonFunctionEjb.isVoucherAvailable(newVoucher.getVoucherNo());
        if(voucherIsAvailable!=0){
            resultObject.setObject(null);
            resultObject.setMessage("Voucher name already used by another");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Customer customer=em.find(Customer.class, newVoucher.getCustomerId());
        if(customer==null){
            resultObject.setObject(null);
            resultObject.setMessage("Customer Is Not Available");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        if(customer.getCustomerTypeId()==null){
            resultObject.setObject(null);
            resultObject.setMessage("This Customer Is Not Belong To Any Customer Type");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Voucher voucher=new Voucher();
        voucher.setVoucherNo(newVoucher.getVoucherNo());
        voucher.setInitialAmount(newVoucher.getInitialAmount());
        voucher.setRemainAmount(newVoucher.getInitialAmount());
        voucher.setNumberPlate(newVoucher.getNumberPlate());
        voucher.setProvisonDate(newVoucher.getProvisonDate());
        voucher.setExpiryDate(newVoucher.getExpiryDate());
        voucher.setCustomerId(newVoucher.getCustomerId());
        
        em.persist(voucher);
        em.flush();
        
        resultObject.setObject(voucher);
        resultObject.setMessage("New Voucher Successfully Alloted To Customer");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject editVoucher(Voucher editVoucher){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Voucher.class);
        
        Voucher voucher=em.find(Voucher.class, editVoucher.getVoucherId());
        
        if(voucher==null){
            resultObject.setMessage("No Voucher with id of the given one is found!");
            resultObject.setObject(null);
            return resultObject;
        }
        
        voucher.setRemainAmount(editVoucher.getRemainAmount());
        em.merge(voucher);
        
        
        resultObject.setMessage("Voucher successfully updated");
        resultObject.setObject(voucher);
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    public ResultObject getVoucher(String voucherNo){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Voucher.class);
        
        Voucher voucher =commonFunctionEjb.voucherDetails(voucherNo);
        if(voucher==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Voucher Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        resultObject.setObject(voucher);
        resultObject.setMessage("Voucher Found");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
}
