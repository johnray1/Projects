/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.ItemOnSellingProfile;
import com.oltranz.IntercityTransport.entities.SaleItem;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.entities.SellingProfile;
import com.oltranz.IntercityTransport.models.SellingProfileWithItemsModel;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.SaleItemForDisplay;
import com.oltranz.IntercityTransport.models.SellingProfileForDisplay;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class SellingProfilesManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            TransportersManager transportersManagerEJB;
    
    public ResultObject getSellingProfile(Integer profileId){
        ResultObject resultObject=new ResultObject();
        SellingProfile sellingProfile=em.find(SellingProfile.class,profileId);
        
        if(sellingProfile!=null){
            resultObject.setMessage("selling Profile Well found and returned!");
            resultObject.setObject(sellingProfile);
            resultObject.setObjectClass(SellingProfile.class);
            return resultObject;
        }else{
            resultObject.setMessage("selling Profile with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(SellingProfile.class);
            return resultObject;
        }
    }
    
    public ResultObject getSellingProfileForDisplay(Integer profileId){
        ResultObject resultObject=new ResultObject();
        SellingProfile sellingProfile=em.find(SellingProfile.class,profileId);
        String message="";
        
        if(sellingProfile!=null){
            SellingProfileForDisplay SellingProfileForDisplay=new SellingProfileForDisplay();
            SellingProfileForDisplay.setId(sellingProfile.getId());
            SellingProfileForDisplay.setName(sellingProfile.getName());
            SellingProfileForDisplay.setTransporterId(sellingProfile.getTransporterId());
            
            if(sellingProfile.getTransporterId()==null){
                message=" Warning: missing transporter id";
            }
            else{
                Transporter transporter=em.find(Transporter.class, sellingProfile.getTransporterId());
                if(transporter==null){
                    message=" Fatal error: transporter Id is of non existing transporter on the system";
                }else{
                    SellingProfileForDisplay.setTransporterName(transporter.getName());
                }
            }
            
            resultObject.setMessage("Selling Profile Well found."+message);
            resultObject.setObject(SellingProfileForDisplay);
            resultObject.setObjectClass(SaleItemForDisplay.class);
            return resultObject;
        }else{
            resultObject.setMessage("sellingProfile with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(SellingProfile.class);
            return resultObject;
        }
    }
    
    public ResultObject createSellingProfile(SellingProfile newProfile){
        ResultObject resultObject= new ResultObject();
        
        // confirm that transporter Id is given and is of an existing transporter
        if(newProfile.getTransporterId()==null ){
            resultObject.setObject(null);
            resultObject.setMessage("TransporterId is missing!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        if(newProfile.getName()==null ){
            resultObject.setObject(null);
            resultObject.setMessage("Transporter name is missing!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        if(em.find(Transporter.class, newProfile.getTransporterId())==null){
            resultObject.setObject(null);
            resultObject.setMessage("Transporter not found!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        Query query=em.createQuery("select sp from SellingProfile sp where sp.name=:itemName and sp.transporterId=:transporterId");
        query.setParameter("itemName", newProfile.getName());
        query.setParameter("transporterId", newProfile.getTransporterId());
        
        
        if(query.getResultList().size()>0){
            resultObject.setObject(null);
            resultObject.setMessage("Another Selling Profile for this transporter has the same name!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        SellingProfile profile= new SellingProfile();
        profile.setName(newProfile.getName());
        profile.setTransporterId(newProfile.getTransporterId());
        
        em.persist(profile);
        em.flush();
        
        return getSellingProfile(profile.getId());
    }
    
    public ResultObject getSellingProfileWithItems(Integer profileId){
        ResultObject resultObject= new ResultObject();
        
        SellingProfileWithItemsModel profileWithItems=null;
        SellingProfile profile=em.find(SellingProfile.class,profileId);
        
        if(profile==null){
            resultObject.setObject(null);
            resultObject.setMessage("No selling profile with given id can be been found");
            resultObject.setObjectClass(SellingProfileWithItemsModel.class);
            return resultObject;
        }
        profileWithItems=new SellingProfileWithItemsModel();
        profileWithItems.setId(profileId);
        profileWithItems.setName(profile.getName());
        profileWithItems.setTransporterId(profile.getTransporterId());
        
        //then get the items
        Query query=em.createQuery("select i from SaleItem i left join ItemOnSellingProfile ip on i.id=ip.itemId where ip.profileId=:profileId");
        query.setParameter("profileId",profileId);
        
        profileWithItems.setItems(query.getResultList());
        
        resultObject.setObject(profileWithItems);
        resultObject.setMessage("Selling profile with given id well found");
        resultObject.setObjectClass(SellingProfileWithItemsModel.class);
        return resultObject;
    }
    
    public ResultObject addItemToProfile(Integer itemId,Integer profileId ){
        ResultObject resultObject=new ResultObject();
        
        //confirm that both the item and profile exist and
        // are for the same transporter
        
        Query query= em.createQuery("select x from ItemOnSellingProfile x where x.itemId=:saleItemId and x.profileId=:sellingProfileId");
        query.setParameter("saleItemId", itemId);
        query.setParameter("sellingProfileId",profileId);
        
        if(!query.getResultList().isEmpty()){
            resultObject.setObject(null);
            resultObject.setObjectClass(ArrayList.class);
            resultObject.setMessage("Item already on the given selling profile");
            return resultObject;
        }
        
        SaleItem item=em.find(SaleItem.class, itemId);
        SellingProfile profile=em.find(SellingProfile.class, profileId);
        
        if(item ==null || profile==null){
            resultObject.setMessage("the item or profile does not exists");
            resultObject.setObject(null);
            resultObject.setObjectClass(ItemOnSellingProfile.class);
            return  resultObject;
        }
        
        
        if(!item.getTransporterId().equals(profile.getTransporterId())){
            resultObject.setMessage("the item and profile are not of the same transporter");
            resultObject.setObject(null);
            resultObject.setObjectClass(ItemOnSellingProfile.class);
            return  resultObject;
        }
        
        ItemOnSellingProfile itemOnSellingProfile= new ItemOnSellingProfile();
        itemOnSellingProfile.setItemId(itemId);
        itemOnSellingProfile.setProfileId(profileId);
        em.persist(itemOnSellingProfile);
        em.flush();
        
        
        resultObject.setMessage("Item successfully added to profile");
        resultObject.setObject(itemOnSellingProfile);
        resultObject.setObjectClass(ItemOnSellingProfile.class);
        return resultObject;
    }
    
  
    
    public ResultObject removeItemFromProfile(Integer itemId,Integer profileId ){
        ItemOnSellingProfile itemOnSellingProfile= new ItemOnSellingProfile();
        itemOnSellingProfile.setItemId(itemId);
        itemOnSellingProfile.setProfileId(profileId);
        em.remove(itemOnSellingProfile);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("Item successfully removed from profile");
        resultObject.setObject(itemOnSellingProfile);
        resultObject.setObjectClass(ItemOnSellingProfile.class);
        return resultObject;
    }
    
    public ResultObject updateSellingProfile(SellingProfile editProfile){
        ResultObject resultObject= new ResultObject();
        
        // confirm that transporter Id is given and is of an existing transporter
        if(editProfile.getTransporterId()==null){
            resultObject.setObject(null);
            resultObject.setMessage("TransporterId is missing!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        SellingProfile profile=em.find(SellingProfile.class, editProfile.getId());
        if(profile==null){
            resultObject.setObject(null);
            resultObject.setMessage("profile to be edited cannot be found in the system database!");
            resultObject.setObjectClass(SaleItem.class);
            return resultObject;
        }
        
        if(profile.getTransporterId()!=null){
            if(profile.getTransporterId().equals(editProfile.getTransporterId())){
                if(em.find(Transporter.class, editProfile.getTransporterId())==null){
                    resultObject.setObject(null);
                    resultObject.setMessage("Transporter not found!");
                    resultObject.setObjectClass(SaleItem.class);
                    return resultObject;
                }
            }
        }
        else
            if(em.find(Transporter.class, editProfile.getTransporterId())==null){
                resultObject.setObject(null);
                resultObject.setMessage("Transporter not found!");
                resultObject.setObjectClass(SaleItem.class);
                return resultObject;
            }
        
        if(profile.getName()!=null){
            if(profile.getName().equalsIgnoreCase(editProfile.getName())){
                Query query=em.createQuery("select sp from SellingProfile sp where sp.name=:itemName and sp.transporterId=:transporterId");
                query.setParameter("itemName", editProfile.getName());
                query.setParameter("transporterId", editProfile.getTransporterId());
                
                
                if(query.getResultList().size()>0){
                    resultObject.setObject(null);
                    resultObject.setMessage("Another selling Profile for this transporter has the same name!");
                    resultObject.setObjectClass(SaleItem.class);
                    return resultObject;
                }
            }
        }else{
            Query query=em.createQuery("select sp from SellingProfile sp where sp.name=:itemName and sp.transporterId=:transporterId");
            query.setParameter("itemName", editProfile.getName());
            query.setParameter("transporterId", editProfile.getTransporterId());
            
            
            if(query.getResultList().size()>0){
                resultObject.setObject(null);
                resultObject.setMessage("Another selling Profile for this transporter has the same name!");
                resultObject.setObjectClass(SaleItem.class);
                return resultObject;
            }
        }
        
        profile.setName(editProfile.getName());
        profile.setTransporterId(editProfile.getTransporterId());
        em.merge(profile);
        
        return getSellingProfile(profile.getId());
    }
    
    public ResultObject deleteSellingProfile(SellingProfile profile2Delete){
        SellingProfile sellingProfile=em.find(SellingProfile.class, profile2Delete.getId());
        
        //set the first bit to
        sellingProfile.setStatus(sellingProfile.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        sellingProfile.setName(sellingProfile.getName()+ sdf.format(deletionTIme));
        em.merge(sellingProfile);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("Selling Profile successfully sent to dustbin");
        resultObject.setObject(sellingProfile);
        resultObject.setObjectClass(Transporter.class);
        return resultObject;
    }
    
    public ResultObject getTransporterSellingProfilesList(Integer transporterId){
        ResultObject resultObject= new ResultObject();
        resultObject.setObject(transportersManagerEJB.getTransporter(transporterId));
        
        if(resultObject.getObject()==null)
            return resultObject;
        
        Query query;
        
        query=em.createQuery("select i from SellingProfile i where i.transporterId=:transporterId ");
        query.setParameter("transporterId", transporterId);
        
        List<SellingProfile> sellingProfileslist=(List<SellingProfile>)query.getResultList();
        List<SellingProfileForDisplay> SellingProfilesForDisplayList=new ArrayList();
        sellingProfileslist.forEach(x->SellingProfilesForDisplayList.add((SellingProfileForDisplay)getSellingProfileForDisplay(x.getId()).getObject()));
        
        resultObject.setObject(SellingProfilesForDisplayList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(sellingProfileslist.isEmpty()){
            resultObject.setMessage("There are no Selling profile for this transporter");
        }
        else{
            resultObject.setMessage(sellingProfileslist.size()+" Selling profiles for this transporter well found");
        }
        return resultObject;
    }
    
    public ResultObject getSellingProfilesList(){
        ResultObject resultObject= new ResultObject();
        
        Query query;
        
        query=em.createQuery("select i from SellingProfile i");
        
        List<SellingProfile> sellingProfileslist=(List<SellingProfile>)query.getResultList();
        List<SellingProfileForDisplay> SellingProfilesForDisplayList=new ArrayList();
        sellingProfileslist.forEach(x->SellingProfilesForDisplayList.add((SellingProfileForDisplay)getSellingProfileForDisplay(x.getId()).getObject()));
        
        resultObject.setObject(SellingProfilesForDisplayList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(sellingProfileslist.isEmpty()){
            resultObject.setMessage("There are no Selling profile ");
        }
        else{
            resultObject.setMessage(sellingProfileslist.size()+" Selling profiles are returned");
        }
        return resultObject;
    }
    
    
}
