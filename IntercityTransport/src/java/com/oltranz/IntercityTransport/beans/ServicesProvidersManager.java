/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.ServiceProvider;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.entities.UserRole;
import com.oltranz.IntercityTransport.entities.UserRoleForServiceProvider;
import com.oltranz.IntercityTransport.entities.UserRoleForServiceProviderPK;
import com.oltranz.IntercityTransport.models.ResultObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class ServicesProvidersManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            WalletsManager walletsManagerEJB;
    
    public ResultObject getServiceProvider(Integer serviceProviderId){
        ResultObject resultObject=new ResultObject();
        ServiceProvider serviceProvider=em.find(ServiceProvider.class,serviceProviderId);
        
        if(serviceProvider!=null){
            resultObject.setMessage("ServiceProvider Well found and returned!");
            resultObject.setObject(serviceProvider);
            resultObject.setObjectClass(ServiceProvider.class);
            return resultObject;
        }else{
            resultObject.setMessage("ServiceProvider with given Id not found!");
            resultObject.setObject("NOT FOUND");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
    }
    
    public ResultObject getServiceProviderByUserRole(Integer userRoleId){
        ResultObject resultObject=new ResultObject();
        UserRole userRole=em.find(UserRole.class,userRoleId);
        
        if(userRole==null){
            resultObject.setMessage("User Role Not Found!");
            resultObject.setObject(userRole);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        //now check to see if this userRole is of transporter type (which is 3 according to the initialized data)
        
        if(userRole.getTypeId()!=3){
            resultObject.setMessage("This user Role is not for transporters staff!");
            resultObject.setObject(userRole);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        ServiceProvider serviceProvider=null;
        try{
            Query query=em.createQuery("select sp from UserRoleForServiceProvider us join ServiceProvider sp on us.userRoleForServiceProviderPK.serviceProviderId=sp.id where us.userRoleForServiceProviderPK.roleId=:userRoleId ");
            query.setParameter("userRoleId", userRoleId);
            
            serviceProvider=(ServiceProvider)query.getSingleResult();
        }
        catch(NoResultException ex){
            resultObject.setMessage("Fatal error occured :"+ ex.getLocalizedMessage());
            resultObject.setObject(serviceProvider);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        
        if(serviceProvider!=null){
            resultObject.setMessage("Service provider for given user role found!");
            resultObject.setObject(serviceProvider);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }else{
            resultObject.setMessage("Service Provider with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
    }
    
    
    public ResultObject createServiceProvider(ServiceProvider newServiceProvider){
        ResultObject resultObject=new ResultObject();
        ServiceProvider serviceProvider=new ServiceProvider();
        //confirm that is the new service is different from the existing, the new service does exists
        
        if( newServiceProvider.getName()==null || newServiceProvider.getName().isEmpty()){
            resultObject.setMessage("Service Provider's name can not be left empty!");
            resultObject.setObject(null);
            resultObject.setObjectClass(ServiceProvider.class);
            return resultObject;
        }
        Query query=em.createQuery("select sp from ServiceProvider sp where sp.id!=:currentName and sp.name=:name");
        query.setParameter("currentName", serviceProvider.getName());
        query.setParameter("name", newServiceProvider.getName());
        List<ServiceProvider> spList=(List<ServiceProvider>) query.getResultList();
        
        if(!spList.isEmpty()){
            resultObject.setMessage("Another Service Provider holds the same name as the new one provided!");
            resultObject.setObject(null);
            resultObject.setObjectClass(ServiceProvider.class);
            return resultObject;
        }
        
        serviceProvider.setName(newServiceProvider.getName());
        serviceProvider.setDescr(newServiceProvider.getDescr());
        em.persist(serviceProvider);
        em.flush();
        //after creating the transporter, a role the represent this transorter
        //must be also created in userRoleForServiceProvider
        
        //So we create first an new user role
        UserRole userRole= new UserRole();
        userRole.setDescr("A role that Service Provider '"+ serviceProvider.getName()+"' Staff are considered to have");
        userRole.setName("Service Provider '"+ serviceProvider.getName()+"' Staff");
        userRole.setTypeId(3); //as per system initialized roleType 3 is for transporters staff
        
        em.persist(userRole);
        em.flush();
        
        UserRoleForServiceProviderPK userRoleForServiceProviderPK=new UserRoleForServiceProviderPK(userRole.getId(),serviceProvider.getId());
        UserRoleForServiceProvider userRoleForServiceProvider=new UserRoleForServiceProvider(userRoleForServiceProviderPK);
        
        em.persist(userRoleForServiceProvider);
        
        //Create Service Provider wallet
        walletsManagerEJB.createServiceProviderWallet(serviceProvider.getId());
        
        resultObject.setMessage("New serviceProvider successfully create");
        resultObject.setObject(serviceProvider);
        resultObject.setObjectClass(ServiceProvider.class);
        return resultObject;
    }
    
    public ResultObject updateServiceProvider(ServiceProvider editServiceProvider){
        ResultObject resultObject=new ResultObject();
        ServiceProvider serviceProvider=em.find(ServiceProvider.class, editServiceProvider.getId());
        
        //confirm that is the new service is different from the existing, the new service does exists
        if( editServiceProvider.getName()==null || editServiceProvider.getName().isEmpty()){
            resultObject.setMessage("ServiceId can not be null!");
            resultObject.setObject(null);
            resultObject.setObjectClass(ServiceProvider.class);
            return resultObject;
        }
        
        
        if(serviceProvider.getName().equals(editServiceProvider.getName())){
            if( editServiceProvider.getName()==null){
                resultObject.setMessage("Service Provider's name can not be left empty!");
                resultObject.setObject(null);
                resultObject.setObjectClass(ServiceProvider.class);
                return resultObject;
            }
            Query query=em.createQuery("select sp from ServiceProvider sp where sp.id!=:currentName and sp.name=:name");
            query.setParameter("currentName", serviceProvider.getName());
            query.setParameter("name", editServiceProvider.getName());
            List<ServiceProvider> spList=(List<ServiceProvider>) query.getResultList();
            
            if(!spList.isEmpty()){
                resultObject.setMessage("Another Service Provider holds the same name as the new one provided!");
                resultObject.setObject(null);
                resultObject.setObjectClass(ServiceProvider.class);
                return resultObject;
            }
        }
        
        serviceProvider.setName(editServiceProvider.getName());
        serviceProvider.setDescr(editServiceProvider.getDescr());
        em.merge(serviceProvider);
        
        resultObject.setMessage("ServiceProvider successfully updated");
        resultObject.setObject(serviceProvider);
        resultObject.setObjectClass(ServiceProvider.class);
        return resultObject;
    }
    
    public ResultObject getServicesProvidersList(){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select sp from ServiceProvider sp");
        
        
        List<ServiceProvider> spList=(List<ServiceProvider>)query.getResultList();
        if(spList.isEmpty())
            resultObject.setMessage("No Service provider ");
        else
            resultObject.setMessage("Service Providers List well returned");
        resultObject.setObject(spList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject getServicesProvidersList(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select sp from ServiceProvider sp join Contract c on sp.id=c.serviceProviderId where c.transporterId=:transporterId ");
        query.setParameter("transporterId", transporterId);
        
        List<ServiceProvider> spList=(List<ServiceProvider>)query.getResultList();
        if(spList.isEmpty())
            resultObject.setMessage("No Service provider for this transporter");
        else
            resultObject.setMessage("Service Providers List well returned");
        resultObject.setObject(spList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject getServicesProvidersList(Integer transporterId,Integer serviceId){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select sp from ServiceProvider sp join Contract c on sp.id=c.serviceProviderId where c.transporterId=:transporterId and c.serviceId=:serviceId ");
        query.setParameter("transporterId", transporterId);
        query.setParameter("serviceId", serviceId);
        
        List<ServiceProvider> spList=(List<ServiceProvider>)query.getResultList();
        if(spList.isEmpty())
            resultObject.setMessage("No Service provider for this transporter");
        else
            resultObject.setMessage("Service Providers List well returned");
        resultObject.setObject(spList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject deleteServiceProvider(Integer serviceProvider2DeleteId){
        ResultObject resultObject=new ResultObject();
        ServiceProvider serviceProvider=em.find(ServiceProvider.class, serviceProvider2DeleteId);
        
        if(serviceProvider==null){
            resultObject.setMessage("No service provider with provided id is found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        serviceProvider.setStatus(serviceProvider.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        serviceProvider.setName(serviceProvider.getName()+ sdf.format(deletionTIme));
        em.merge(serviceProvider);
        
        
        resultObject.setMessage("ServiceProvider successfully sent to dustbin");
        resultObject.setObject(serviceProvider);
        resultObject.setObjectClass(ServiceProvider.class);
        return resultObject;
    }
    
    public ResultObject deleteServiceProvider(ServiceProvider serviceProvider2Delete){
        ServiceProvider serviceProvider=em.find(ServiceProvider.class, serviceProvider2Delete.getId());
        ResultObject resultObject=new ResultObject();
        if(serviceProvider==null){
            resultObject.setMessage("No service provider with provided id is found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        serviceProvider.setStatus(serviceProvider.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        serviceProvider.setName(serviceProvider.getName()+ sdf.format(deletionTIme));
        em.merge(serviceProvider);
        
        
        resultObject.setMessage("ServiceProvider successfully sent to dustbin");
        resultObject.setObject(serviceProvider);
        resultObject.setObjectClass(ServiceProvider.class);
        return resultObject;
    }
    
}
