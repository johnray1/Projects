/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;


import com.oltranz.payfuel.entities.Nozzle;
import com.oltranz.payfuel.entities.Product;
import com.oltranz.payfuel.entities.Pump;
import com.oltranz.payfuel.models.ResultObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class NozzleManager {
    @PersistenceContext
    private EntityManager em;
    
    public ResultObject createNozzle(Nozzle createNozzle) {
        
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Nozzle.class);
        try{
        Pump pump=em.find(Pump.class, createNozzle.getPumpId());
        if(pump==null){
            resultObject.setObject(null);
            resultObject.setMessage("PumpId is not created, to which we want to set our Nozzle");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Product product=em.find(Product.class, createNozzle.getProductId());
        if(product==null){
            resultObject.setObject(null);
            resultObject.setMessage("ProductId is not created, to which we want to set our Nozzle");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Nozzle nozzle=new Nozzle();
        
        nozzle.setNozzleName(createNozzle.getNozzleName());
        nozzle.setPumpId(createNozzle.getPumpId());
        nozzle.setProductId(createNozzle.getProductId());
        em.persist(nozzle);
        
        em.flush();
        
        resultObject.setObject(nozzle);
        resultObject.setMessage("Nozzle created Successfully");
        resultObject.setStatusCode(100);
        
        return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
        
    }
    
    
    public ResultObject editNozzle(Nozzle editNozzle) {
        
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Nozzle.class);
        try{
            Nozzle nozzle=em.find(Nozzle.class, editNozzle.getNozzleId());
            if(nozzle==null){
                resultObject.setObject(null);
                resultObject.setMessage("NozzleId is not created, to which we want to Update");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            nozzle.setNozzleName(editNozzle.getNozzleName());
            nozzle.setNozzleIndex(editNozzle.getNozzleIndex());
            em.merge(nozzle);
            
            resultObject.setObject(nozzle);
            resultObject.setMessage("Nozzle Succefully Updated");
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
        
    }
    
    
    public ResultObject getNozzleList(){
        
        List<Nozzle> nozzleList=(List<Nozzle>)em.createNamedQuery("Nozzle.findAll").getResultList();
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Nozzle.class);
        resultObject.setObject(nozzleList);
        
        
        if(nozzleList.isEmpty()){
            resultObject.setMessage("There are no Nozzle in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(nozzleList.size()+" Nozzle are found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
  
}
