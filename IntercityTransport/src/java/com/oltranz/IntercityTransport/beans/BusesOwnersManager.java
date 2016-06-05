/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.BusOwner;
import com.oltranz.IntercityTransport.models.ResultObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import org.eclipse.persistence.config.HintValues;
//import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class BusesOwnersManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em; 
    
    public ResultObject createBusOwner(BusOwner newBusOwner){
        BusOwner busOwner=new BusOwner();
        busOwner.setName(newBusOwner.getName());
        busOwner.setDescr(newBusOwner.getDescr());
        em.persist(busOwner);
         em.flush();
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("New busOwner successfully create");
        resultObject.setObject(busOwner);
        resultObject.setObjectClass(BusOwner.class);
        return resultObject;
    }
    
    public ResultObject updateBusOwner(BusOwner editBusOwner){
        BusOwner busOwner=em.find(BusOwner.class, editBusOwner.getId());
        busOwner.setName(editBusOwner.getName());
        busOwner.setDescr(editBusOwner.getDescr());
        em.merge(busOwner);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("BusOwner successfully updated");
        resultObject.setObject(busOwner);
        resultObject.setObjectClass(BusOwner.class);
        return resultObject;
    }
       
    public List<BusOwner> getBusOwnersList(){
        Query query;
     
            query=em.createQuery("select t from BusOwner t ");
        
        return (List<BusOwner>)query.getResultList();
    }
    
    public ResultObject deleteBusOwner(BusOwner busOwner2Delete){
        BusOwner busOwner=em.find(BusOwner.class, busOwner2Delete.getId());
        busOwner.setStatus(busOwner.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        busOwner.setName(busOwner.getName()+ sdf.format(deletionTIme));
        em.merge(busOwner);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("Bus Owner successfully sent to dustbin");
        resultObject.setObject(busOwner);
        resultObject.setObjectClass(BusOwner.class);
        return resultObject;
    }    
        
}
