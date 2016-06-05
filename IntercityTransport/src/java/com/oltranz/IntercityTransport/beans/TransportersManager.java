/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.entities.UserRole;
import com.oltranz.IntercityTransport.entities.UserRoleForTransporter;
import com.oltranz.IntercityTransport.entities.UserRoleForTransporterPK;
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
public class TransportersManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB 
        WalletsManager walletsManagerEJB; 
    
    public ResultObject getTransporter(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        Transporter transporter=em.find(Transporter.class,transporterId);
        
        if(transporter!=null){
            resultObject.setMessage("Transporter Well found and returned!");
            resultObject.setObject(transporter);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }else{
            resultObject.setMessage("Transporter with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
    }
    
    public ResultObject getTransporterByUserRole(Integer userRoleId){
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
        Transporter transporter=null;
        try{
            Query query=em.createQuery("select t from UserRoleForTransporter ut join Transporter t on ut.userRoleForTransporterPK.transporterId=t.id where ut.userRoleForTransporterPK.roleId=:userRoleId ");
            query.setParameter("userRoleId", userRoleId);
            
            transporter=(Transporter)query.getSingleResult();
        }
        catch(NoResultException ex){
            resultObject.setMessage("Fatal error: Transporter for with [valid] user role for one transporter is missing!");
            resultObject.setObject(transporter);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        
        if(transporter!=null){
            resultObject.setMessage("Transporter for given user role found!");
            resultObject.setObject(transporter);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }else{
            resultObject.setMessage("Transporter with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
    }
    
    public ResultObject createTransporter(Transporter newTransporter){
        ResultObject resultObject= new ResultObject();
        if(newTransporter.getName()==null ){
            resultObject.setObject(null);
            resultObject.setMessage("Transporter name is missing!");
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        
        Query query=em.createQuery("select t from Transporter t where UPPER(t.name)=:name");
        query.setParameter("name", newTransporter.getName().toUpperCase());
        
        if(query.getResultList().size()>0){
            resultObject.setObject(null);
            resultObject.setMessage("A transporter with that same name already exists!");
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        
        Transporter transporter=new Transporter();
        transporter.setName(newTransporter.getName());
        transporter.setDescr(newTransporter.getDescr());
        em.persist(transporter);
        em.flush();
        //after creating the transporter, a role the represent this transorter
        //must be also created in userRoleForTransporter
        
        //So we create first an new user role
        UserRole userRole= new UserRole();
        userRole.setDescr("A role that Transporter '"+ transporter.getName()+"' Staff are considered to have");
        userRole.setName("Transporter '"+ transporter.getName()+"' Staff");
        userRole.setTypeId(3); //as per system initialized roleType 3 is for transporters staff
        
        em.persist(userRole);
        em.flush();
        
        UserRoleForTransporterPK userRoleForTransporterPK=new UserRoleForTransporterPK(userRole.getId(),transporter.getId());
        UserRoleForTransporter userRoleForTransporter=new UserRoleForTransporter(userRoleForTransporterPK);
        
        em.persist(userRoleForTransporter);
        
        //Create Transporter wallet
        walletsManagerEJB.createTransporterWallet(transporter.getId());        
        
        resultObject.setMessage("New transporter successfully created, staff role and wallet created");
        resultObject.setObject(transporter);
        resultObject.setObjectClass(Transporter.class);
        return resultObject;
    }
    
    public ResultObject updateTransporter(Transporter editTransporter){
        ResultObject resultObject=new ResultObject();
        Transporter transporter=em.find(Transporter.class, editTransporter.getId());
        
        if(transporter==null){
            resultObject.setObject(null);
            resultObject.setMessage("Transporter not found!");
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        if(editTransporter.getName()==null || editTransporter.getName().isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("Transporter name can't be set to empty nor left out !");
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        
        Query query=em.createQuery("select t from Transporter t where UPPER(t.name)=:name and t.id=:newId");
        query.setParameter("name", editTransporter.getName().toUpperCase());
        query.setParameter("newId", editTransporter.getId());
        
        if(query.getResultList().size()>0){
            resultObject.setObject(null);
            resultObject.setMessage("A transporter with that same name already exists!");
            resultObject.setObjectClass(Transporter.class);
            return resultObject;
        }
        
        
        transporter.setName(editTransporter.getName());
        transporter.setDescr(editTransporter.getDescr());
        em.merge(transporter);
        
        
        resultObject.setMessage("Transporter successfully updated");
        resultObject.setObject(transporter);
        resultObject.setObjectClass(Transporter.class);
        return resultObject;
    }
    
    public ResultObject getTransportersList(){
        ResultObject resultObject= new ResultObject();
        Query query;
        
        query=em.createQuery("select t from Transporter t  ");
        
        List<Transporter> transportersList=(List<Transporter>)query.getResultList();
        
        resultObject.setObject(transportersList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(transportersList.isEmpty()){
            resultObject.setMessage("There are no Transporter in the system");
        }
        else{
            resultObject.setMessage(transportersList.size()+" Transporters well found");
        }
        
        return resultObject;
        
    }
    
    public ResultObject deleteTransporter(Transporter transporter2Delete){
        ResultObject resultObject=new ResultObject();
        Transporter transporter=em.find(Transporter.class, transporter2Delete.getId());
        
        if(transporter==null){
            resultObject.setMessage("Transporter with given Id not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        transporter.setStatus(transporter.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        transporter.setName(transporter.getName()+ sdf.format(deletionTIme));
        em.merge(transporter);
        
        
        resultObject.setMessage("Transporter successfully sent to dustbin");
        resultObject.setObject(transporter);
        resultObject.setObjectClass(Transporter.class);
        return resultObject;
    }
    
    public ResultObject deleteTransporter(Integer transproterId){
        ResultObject resultObject=new ResultObject();
        Transporter transporter2Delete=em.find(Transporter.class, transproterId);
        if(transporter2Delete==null){
            resultObject.setMessage("Transporter with given Id not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        Transporter transporter=em.find(Transporter.class, transporter2Delete.getId());
        transporter.setStatus(transporter.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        transporter.setName(transporter.getName()+ sdf.format(deletionTIme));
        em.merge(transporter);
        
        
        resultObject.setMessage("Transporter successfully sent to dustbin");
        resultObject.setObject(transporter);
        resultObject.setObjectClass(Transporter.class);
        return resultObject;
    }
    
}
