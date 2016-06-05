/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.Bus;
import com.oltranz.IntercityTransport.entities.BusOnContract;
import com.oltranz.IntercityTransport.entities.BusOnContractPK;
import com.oltranz.IntercityTransport.entities.Contract;
import com.oltranz.IntercityTransport.entities.ServiceProvider;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.models.BusDetailsModel;
import com.oltranz.IntercityTransport.models.ResultObject;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.List;
import javax.ejb.EJB;
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
public class BusesManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB 
      TransportersManager transporterManagerEJB;
    
    public ResultObject getBus(String numberPlate){
        ResultObject resultObject=new ResultObject();
        Bus bus=em.find(Bus.class,numberPlate);
        
        if(bus!=null){
            BusDetailsModel busDetails= new BusDetailsModel();
            busDetails.setDescr(bus.getDescr());
            busDetails.setNumberPlate(bus.getNumberPlate());
            busDetails.setOwnerId(bus.getOwnerId());
            
            busDetails.setTransporterOwned(bus.isTransporterOwned());
            if(bus.isTransporterOwned())                
                busDetails.setOwnerName(em.find(Transporter.class, bus.getOwnerId()).getName());
            else
                busDetails.setOwnerName(em.find(ServiceProvider.class, bus.getOwnerId()).getName());
            
            resultObject.setMessage("Bus With given Number Plate well found and returned!");
            resultObject.setObject(busDetails);
            resultObject.setObjectClass(BusDetailsModel.class);
            return resultObject;
        }else{
            resultObject.setMessage("Bus with given number plate not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(BusDetailsModel.class);
            return resultObject;
        }
    }
    
    public ResultObject createBus(Bus newBus){
        ResultObject resultObject=new ResultObject();
        Bus bus=new Bus();
        
        //if the bus is transporterOwned then the owner should be a valid transporter with id=ownerId
        
        if(newBus.isTransporterOwned()){
            if(em.find(Transporter.class, newBus.getOwnerId())==null){
                resultObject.setMessage("Transporter with given bus owner id not found!");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;
            }
        }
        else{
            if(em.find(ServiceProvider.class, newBus.getOwnerId())==null){
                resultObject.setMessage("Service Provider with given bus owner id not found!");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;
            }
        }
        
        //if it is transporter owned then we should have a valid Transporter Id as the owner
        // otherwise we should have a valid Service Provider Id as owner
        if(newBus.isTransporterOwned()){
            Transporter transporter= em.find(Transporter.class, newBus.getOwnerId());
            if(transporter==null){
                resultObject.setMessage("Provided owner Id is not a valid Transporter Id [transporter owned]!");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;
            }
        }else{
            ServiceProvider serviceProvider= em.find(ServiceProvider.class, newBus.getOwnerId());
            if(serviceProvider==null){
                resultObject.setMessage("Provided owner Id is not a valid Service Provider Id [bus not transporter owned], please create the owner of the bus in the system first!");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;
            }
        }
        if(!(em.find(Bus.class, newBus.getNumberPlate())==null)){
           resultObject.setMessage("Bus with given number plate already in the system");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;   
        }
        
        bus.setDescr(newBus.getDescr());
        bus.setTransporterOwned(newBus.isTransporterOwned());
        bus.setNumberPlate(newBus.getNumberPlate());
        bus.setOwnerId(newBus.getOwnerId());
        em.persist(bus);
        
        
        resultObject.setMessage("New bus successfully created");
        resultObject.setObject(bus);
        resultObject.setObjectClass(Bus.class);
        return resultObject;
    }
    
    public ResultObject updateBus(Bus editBus){
        ResultObject resultObject=new ResultObject();
        //if it is transporter owned then we should have a valid Transporter Id as the owner
        // otherwise we should have a valid Service Provider Id as owner
        if(editBus.isTransporterOwned()){
            Transporter transporter= em.find(Transporter.class, editBus.getOwnerId());
            if(transporter==null){
                resultObject.setMessage("Provided owner Id is not a valid Transporter Id [transporter owned]!");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;
            }
        }else{
            ServiceProvider serviceProvider= em.find(ServiceProvider.class, editBus.getOwnerId());
            if(serviceProvider==null){
                resultObject.setMessage("Provided owner Id is not a valid Service Provider Id [bus not transporter owned], please  create as service provider the owner  of the bus in the system first!");
                resultObject.setObject(null);
                resultObject.setObjectClass(Bus.class);
                return resultObject;
            }
        }
        
        Bus bus=em.find(Bus.class, editBus.getNumberPlate());
        bus.setDescr(editBus.getDescr());
        bus.setTransporterOwned(editBus.isTransporterOwned());
        bus.setNumberPlate(editBus.getNumberPlate());
        bus.setOwnerId(editBus.getOwnerId());
        em.merge(bus);
        
        resultObject.setMessage("Bus successfully updated");
        resultObject.setObject(bus);
        resultObject.setObjectClass(Bus.class);
        return resultObject;
    }
    
    public ResultObject getBusesList(){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select b from Bus b");
        
        
        List<Bus> spList=(List<Bus>)query.getResultList();
        if(spList.isEmpty())
            resultObject.setMessage("No single Bus is found in the system ");
        else
            resultObject.setMessage("All Buses are found and returned");
        resultObject.setObject(spList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject getBusesListUsedByTransporter(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select b from Bus b where b.transporterOwned=true and b.ownerId=:transporterId");
        query.setParameter("transporterId", transporterId);
        List<Bus> busesList=(List<Bus>)query.getResultList();
        
        query=em.createQuery("select b from Bus  b join BusOnContract bc on b.numberPlate=bc.BusOnContractPK.numberPlate join Contract c on bc.BusOnContractPK.contractId=c.id ");
        //query.setParameter("transporterId", transporterId);
        ((List<Bus>)query.getResultList()).forEach(x->busesList.add(x));
        if(busesList.isEmpty())
            resultObject.setMessage("No single Bus is found in the system ");
        else
            resultObject.setMessage("All Buses are found and returned");
        resultObject.setObject(busesList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
      public ResultObject getBusesListOwnedByTransporter(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select b from Bus b where b.transporterOwned=true and b.ownerId=:transporterId");
        query.setParameter("transporterId", transporterId);
        List<Bus> busesList=(List<Bus>)query.getResultList();
     
        if(busesList.isEmpty())
            resultObject.setMessage("No single Bus is found in the system ");
        else
            resultObject.setMessage("All Buses are found and returned");
        resultObject.setObject(busesList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject getBusesListByTransporterByServiceProvider(Integer transporterId, Integer serviceProviderId){
        ResultObject resultObject=new ResultObject();
        Query query;
//        
//        query=em.createQuery("select b from Bus b where b.transporterOwned=true and b.ownerId=:transporterId");
//        query.setParameter("transporterId", transporterId);
//        List<Bus> busesList=(List<Bus>)query.getResultList();
//        
        query=em.createQuery("select b from BusOnContract bc join Bus b on bc.BusOnContractPK.numberPlate=b.numberPlate join Contract c on bc.BusOnContractPK.contractId=c.id where c.serviceProviderId=:serviceProviderId and c.transporterId=:transporterId");
         query.setParameter("transporterId", transporterId);
        query.setParameter("serviceProviderId", serviceProviderId);
        List<Bus> busesList=(List<Bus>)query.getResultList(); 
//query.setParameter("transporterId", transporterId);
//        ((List<Bus>)query.getResultList()).forEach(x->busesList.add(x));
        if(busesList.isEmpty())
            resultObject.setMessage("No single Bus is found in the system ");
        else
            resultObject.setMessage("All Buses are found and returned");
        resultObject.setObject(busesList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    
    public ResultObject getBusesListByServiceProvider(Integer serviceProviderId){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select b from Bus b where b.transporterOwned=false and b.ownerId=:serviceProviderId");
        query.setParameter("serviceProviderId", serviceProviderId);
        
        List<Bus> busesList=(List<Bus>)query.getResultList();
        
        if(busesList.isEmpty())
            resultObject.setMessage("No single Bus of this service provider is found in the system ");
        else
            resultObject.setMessage("All Buses of this service Provider are found and returned");
        resultObject.setObject(busesList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject deleteBus(Bus bus2Delete){
        ResultObject resultObject=new ResultObject();
        Bus bus=em.find(Bus.class, bus2Delete.getNumberPlate());
        
        if(bus==null){
            resultObject.setMessage("Bus with given number plate not found in system");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        bus.setStatus(bus.getStatus()&6);
        
        em.merge(bus);
        
        resultObject.setMessage("Bus successfully sent to dustbin");
        resultObject.setObject(bus);
        resultObject.setObjectClass(Bus.class);
        return resultObject;
    }
    
    public ResultObject deleteBus(String numberPlate){
        ResultObject resultObject=new ResultObject();
        Bus bus=em.find(Bus.class, numberPlate);
        if(bus==null){
            resultObject.setMessage("Bus with given number plate not found in system");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        bus.setStatus(bus.getStatus()&6);
        
        em.merge(bus);
        
        
        resultObject.setMessage("Bus successfully sent to dustbin");
        resultObject.setObject(bus);
        resultObject.setObjectClass(Bus.class);
        return resultObject;
    }
    
    public ResultObject getBusContracts(String numberPlate){
        ResultObject resultObject=getBus(numberPlate);
        if(resultObject.getObject()==null)
            return resultObject;
        
        Query query=em.createQuery("select c from Contract c join BusOnContract bc on c.id=bc.BusOnContractPK.contractId where bc.BusOnContractPK.numberPlate=:numberPlate");
        query.setParameter("numberPlate", numberPlate);
        
        List<Contract> busContractsList=(List<Contract>)query.getResultList();
        resultObject.setObject(busContractsList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(busContractsList.isEmpty())
            resultObject.setMessage("This bus is not attached to any Contract (past or current)");
        else
            resultObject.setMessage(busContractsList.size() +" Contracts are found and returned");
        
        return resultObject;
    }
    
    public ResultObject getBusCurrentContract(String numberPlate){
        ResultObject resultObject=getBus(numberPlate);
        if(resultObject.getObject()==null)
            return resultObject;
        
        Contract contract=null;
        try{
            Query query=em.createQuery("select c from Contract c join BusOnContract bc on c.id=bc.BusOnContractPK.contractId where bc.BusOnContractPK.numberPlate=:numberPlate and c.endDate>:todayDate");
            query.setParameter("numberPlate", numberPlate);
            query.setParameter("todayDate", new Date());
            
            contract =(Contract)query.getSingleResult();
        }catch(NoResultException ex){
          
        }
        resultObject.setObject(contract);
        resultObject.setObjectClass(Contract.class);
        if(contract==null)
            resultObject.setMessage("This Bus is not attached to any current Contract");
        else
            resultObject.setMessage("Bus Current Contract well found!");
        
        return resultObject;
    }
    
    public ResultObject addbusTocontract(String numberPlate,Integer contractId){
        ResultObject resultObject=new ResultObject();
        
        //confirm that contractor is owner of the bus being added
        Bus bus=em.find(Bus.class,numberPlate);
        Contract contract=em.find(Contract.class,contractId);
        
        //if the bus is transporter owned, it can not go to any contract
        if(contract ==null || bus==null){
            resultObject.setMessage("Contract or/and Bus not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        Date today=new Date();
        if(contract.getStartDate().before(today) && contract.getEndDate().before(today) ){
            resultObject.setMessage("This Contract has already expired");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        if(bus.isTransporterOwned()){
            resultObject.setMessage("This bus is owned by a transporter, therefore can not be put on this contract");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        ServiceProvider serviceProvider=em.find(ServiceProvider.class, contract.getServiceProviderId());
        
        if(serviceProvider==null ){
            resultObject.setMessage("Service prover (bus owner) not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        if(bus.isTransporterOwned()){
            resultObject.setMessage("This Bus is owned by the transporter, therefore cannot be added to any contract!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            
            return resultObject;
        }
        
        if(!bus.getOwnerId().equals(contract.getServiceProviderId())){
            resultObject.setMessage("This Bus is not owned by the contracted service Provider!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
        //check to see if this bus is not attached to any current contract
        if(getBusCurrentContract(numberPlate).getObject()!=null){
            resultObject.setMessage("This Bus is currently attached to another current (not yet expired) contract!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Bus.class);
            return resultObject;
        }
        
              
        BusOnContract BusOnContract= new BusOnContract();
        BusOnContractPK BusOnContractPK=new BusOnContractPK(numberPlate,contractId);
        
        BusOnContract.setBusOnContract(BusOnContractPK);
        
        em.persist(BusOnContract);
        em.flush();
        
        resultObject.setMessage("bus successfully added to contract");
        resultObject.setObject(BusOnContract);
        resultObject.setObjectClass(BusOnContract.class);
        return resultObject;
    }
    
    public ResultObject removeBusfromcontract(String numberPlate,Integer contractId ){
        ResultObject resultObject=new ResultObject();
        
        Bus bus=em.find(Bus.class,numberPlate);
        Contract contract=em.find(Contract.class,contractId);
        
        if(contract ==null || bus==null){
            resultObject.setMessage("Given Contract[id] or/and Bus [Number plate] not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        BusOnContractPK BusOnContractPK=new BusOnContractPK(numberPlate,contractId);
        BusOnContract BusOnContract=em.find(BusOnContract.class, BusOnContractPK);
        
        if(BusOnContract!=null){
            em.remove(BusOnContract);
            resultObject.setMessage("bus successfully removed from Contract");
            resultObject.setObject("SUCCESS");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }else{
            resultObject.setMessage("Given bus and contract not found to be linked in any way");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
    }
    
    
}
