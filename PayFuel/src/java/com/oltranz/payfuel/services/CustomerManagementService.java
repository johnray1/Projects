/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.CustomerManager;
import com.oltranz.payfuel.entities.Customer;
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
@Path("CustomerManagementService")
@Stateless
public class CustomerManagementService {
    
    @EJB
    private CustomerManager customerManager;
    
    @POST
    @Path("customer/create")
    @Consumes({"application/xml", "application/json"})
    public String createCompany(Customer newCustomer) {
        
        ResultObject result=customerManager.createCustomer(newCustomer);
        return result.getJsonFormat();
    }
   
    
}
