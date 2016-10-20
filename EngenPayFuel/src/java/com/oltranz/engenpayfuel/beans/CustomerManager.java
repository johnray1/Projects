/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Customer;
import com.oltranz.engenpayfuel.entities.CustomerType;
import com.oltranz.engenpayfuel.models.ResultObject;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class CustomerManager {
    
    @PersistenceContext
    private EntityManager em;
    
    public ResultObject createCustomer(Customer newCustomer){
        
        //set the returntype resultset object
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Customer.class);
        
        CustomerType customerType=em.find(CustomerType.class, newCustomer.getCustomerTypeId());
        if(customerType==null){
            resultObject.setObject(null);
            resultObject.setMessage("CustomerType Is Not Available");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Customer customer=new Customer();
        customer.setContactDetails(newCustomer.getContactDetails());
        customer.setCustomerTypeId(newCustomer.getCustomerTypeId());
        customer.setName(newCustomer.getName());
        customer.setTin(newCustomer.getTin());
        em.persist(customer);
        em.flush();
        
        resultObject.setObject(customer);
        resultObject.setMessage("New Customer successfully created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
}
