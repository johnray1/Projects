/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.SaleItem;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.SaleItemForDisplay;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.List;
import javax.ejb.EJB;
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
public class SaleItemsManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            TransportersManager transportersManagerEJB;
    @EJB
            SellingProfilesManager sellingProfilesManagerEJB;
    
    public ResultObject getSaleItem(Integer itemId){
        ResultObject resultObject=new ResultObject();
        SaleItem saleItem=em.find(SaleItem.class,itemId);
        
        if(saleItem!=null){
            resultObject.setMessage("Sale Item Well found and returned!");
            resultObject.setObject(saleItem);
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }else{
            resultObject.setMessage("sellingProfile with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
    }
    
    public ResultObject getSaleItemForDisplay(Integer itemId){
        ResultObject resultObject=new ResultObject();
        SaleItem saleItem=em.find(SaleItem.class,itemId);
        String message="";
        
        if(saleItem!=null){
            SaleItemForDisplay saleItemForDisplay=new SaleItemForDisplay();
            saleItemForDisplay.setId(saleItem.getId());
            saleItemForDisplay.setName(saleItem.getName());
            saleItemForDisplay.setPrice(saleItem.getPrice());
            saleItemForDisplay.setTransporterId(saleItem.getTransporterId());
            
            if(saleItem.getTransporterId()==null){
                message=" Warning: missing transporter id";
            }
            else{
                Transporter transporter=em.find(Transporter.class, saleItem.getTransporterId());
                if(transporter==null){
                    message=" Fatal error: transporter Id is of non existing transporter on the system";
                }else{
                    saleItemForDisplay.setTransporterName(transporter.getName());
                }
            }
            
            resultObject.setMessage("Sale Item Well found."+message);
            resultObject.setObject(saleItemForDisplay);
            resultObject.setObjectClass(SaleItemForDisplay.class);
            return resultObject;
        }else{
            resultObject.setMessage("sellingProfile with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(SaleItemForDisplay.class);
            return resultObject;
        }
    }
    
    public ResultObject createSaleItem(SaleItem newItem){
        ResultObject resultObject= new ResultObject();
        
        // confirm that transporter Id is given and is of an existing transporter
        if(newItem.getTransporterId()==null){
            resultObject.setObject(null);
            resultObject.setMessage("TransporterId is missing!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        if(em.find(Transporter.class, newItem.getTransporterId())==null){
            resultObject.setObject(null);
            resultObject.setMessage("Transporter not found!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        Query query=em.createQuery("select i from SaleItem i where i.name=:itemName and i.transporterId=:transporterId");
        query.setParameter("itemName", newItem.getName());
        query.setParameter("transporterId", newItem.getTransporterId());
        
        
        if(query.getResultList().size()>0){
            resultObject.setObject(null);
            resultObject.setMessage("Another Item for this transporter has the same name!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        SaleItem item= new SaleItem();
        item.setName(newItem.getName());
        item.setPrice(newItem.getPrice());
        item.setTransporterId(newItem.getTransporterId());
        
        em.persist(item);
        em.flush();
        
        resultObject.setMessage("New Sale item successfully created");
        resultObject.setObject(getSaleItemForDisplay(item.getId()).getObject());
        resultObject.setObjectClass(SaleItem.class);
        return resultObject;
    }
    
    public ResultObject updateSaleItem(SaleItem editItem){
        ResultObject resultObject= new ResultObject();
        
        // confirm that transporter Id is given and is of an existing transporter
        if(editItem.getTransporterId()==null){
            resultObject.setObject(null);
            resultObject.setMessage("TransporterId is missing!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        SaleItem item = em.find(SaleItem.class,editItem.getId());
        if(item==null){
            resultObject.setObject(null);
            resultObject.setMessage("Item to be edited cannot be found in the system database!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        if(item.getTransporterId().equals(editItem.getTransporterId()))
            if(em.find(Transporter.class, editItem.getTransporterId())==null){
                resultObject.setObject(null);
                resultObject.setMessage("Transporter not found!");
                resultObject.setObjectClass(SaleItem.class);
                return resultObject;
            }
        
        if(item.getName().equalsIgnoreCase(editItem.getName())){
            Query query=em.createQuery("select i from SaleItem i where i.name=:itemName and i.transporterId=:transporterId");
            query.setParameter("itemName", editItem.getName());
            query.setParameter("transporterId", editItem.getTransporterId());
            
            
            if(query.getResultList().size()>0){
                resultObject.setObject(null);
                resultObject.setMessage("Another Item for this transporter has the same name!");
                resultObject.setObjectClass(SaleItem.class);
                return resultObject;
            }
        }
        
        
        item.setName(editItem.getName());
        item.setPrice(editItem.getPrice());
        item.setTransporterId(editItem.getTransporterId());
        
        em.merge(item);
        
        
        resultObject.setMessage("Sale item successfully Edited and saved");
        resultObject.setObject(getSaleItemForDisplay(item.getId()));
        resultObject.setObjectClass(SaleItem.class);
        return resultObject;
    }
    
    public  ResultObject getAllSaleItemsList(){
        ResultObject resultObject= new ResultObject();
        Query query;
        
        query=em.createQuery("select i from SaleItem i ");
        List<SaleItem> itemsList=(List<SaleItem>)query.getResultList();
        List<SaleItemForDisplay> itemsForDisplayList=new ArrayList();
        itemsList.forEach(x->itemsForDisplayList.add((SaleItemForDisplay)getSaleItemForDisplay(x.getId()).getObject()));
        
        resultObject.setObject(itemsForDisplayList);
        
        if(itemsForDisplayList.isEmpty()){
            resultObject.setMessage("There are no Sale Items in this system");
        }
        else{
            resultObject.setMessage(itemsForDisplayList.size()+" sale items returned");
        }
        resultObject.setObjectClass(ArrayList.class);
        
        return resultObject;
    }
    
    public ResultObject addItemToProfile(Integer itemId,Integer profileId ){
        return sellingProfilesManagerEJB.addItemToProfile(itemId, profileId);
    }
    
    public ResultObject removeItemFromProfile(Integer itemId,Integer profileId ){
        return sellingProfilesManagerEJB.removeItemFromProfile(itemId, profileId);
    }
    
    public ResultObject getTransporterSaleItemsList(Integer transporterId){
        ResultObject resultObject= new ResultObject();
        resultObject.setObject(transportersManagerEJB.getTransporter(transporterId));
        
        if(resultObject.getObject()==null)
            return resultObject;
        
        Query query;
        
        query=em.createQuery("select i from SaleItem i where i.transporterId=:transporterId ");
        query.setParameter("transporterId", transporterId);
        
        List<SaleItem> saleItemslist=(List<SaleItem>)query.getResultList();
        List<SaleItemForDisplay> itemsForDisplayList=new ArrayList();
        saleItemslist.forEach(x->itemsForDisplayList.add((SaleItemForDisplay)getSaleItemForDisplay(x.getId()).getObject()));
        
        resultObject.setObject(itemsForDisplayList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(saleItemslist.isEmpty()){
            resultObject.setMessage("There are no Sale Items for this transporter");
        }
        else{
            resultObject.setMessage(saleItemslist.size()+"All Sale Items for this transporter are returned");
        }
        
        
        return resultObject;
    }
    
    
}
