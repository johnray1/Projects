/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;


import com.oltranz.engenpayfuel.entities.Nozzle;
import com.oltranz.engenpayfuel.entities.Pump;
import com.oltranz.engenpayfuel.entities.Tank;
import com.oltranz.engenpayfuel.models.Nozzles;
import com.oltranz.engenpayfuel.models.ResultObject;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
     @EJB
            CommonFunctionEjb commonFunctionEjb;
    public ResultObject createNozzle(Nozzle createNozzle) {
        
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Nozzle.class);
        try{
            
            Tank tank=em.find(Tank.class, createNozzle.getTankId());
            if(tank==null){
                resultObject.setObject(null);
                resultObject.setMessage("TankId is not created, to which Our Pump Is connect");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            Pump pump=em.find(Pump.class, createNozzle.getPumpId());
            if(pump==null){
                resultObject.setObject(null);
                resultObject.setMessage("PumpId is not created, to which we want to set our Nozzle");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            
            Nozzle nozzle=new Nozzle();
            nozzle.setNozzleName(createNozzle.getNozzleName());
            nozzle.setBranchId(tank.getBranchId());
            nozzle.setTankId(tank.getTankId());
            nozzle.setPumpId(pump.getPumpId());
            nozzle.setPreCalibrationDate(createNozzle.getPreCalibrationDate());
            nozzle.setNextCalibrationDate(createNozzle.getNextCalibrationDate());
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
            
            Tank tank=em.find(Tank.class, editNozzle.getTankId());
            if(tank==null){
                resultObject.setObject(null);
                resultObject.setMessage("TankId is not created, to which Our Pump Is connect");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            Pump pump=em.find(Pump.class, editNozzle.getPumpId());
            if(pump==null){
                resultObject.setObject(null);
                resultObject.setMessage("PumpId is not created, to which we want to set our Nozzle");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            nozzle.setNozzleName(editNozzle.getNozzleName());
            nozzle.setNozzleIndex(editNozzle.getNozzleIndex());
            nozzle.setBranchId(tank.getBranchId());
            nozzle.setTankId(tank.getTankId());
            nozzle.setPumpId(pump.getPumpId());
            nozzle.setPreCalibrationDate(editNozzle.getPreCalibrationDate());
            nozzle.setNextCalibrationDate(editNozzle.getNextCalibrationDate());
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
    
    
    //---------------------------------------------------------------------web--------------------------------------------------------------------------------------------
    
    public ResultObject getNozzleListByBranchId(int branchId){
        ResultObject resultObject = new ResultObject();
        resultObject.setObjectClass(Nozzle.class);
        
        List<Nozzle> nozzleList;
        
        if(branchId==0){
            nozzleList=(List<Nozzle>) em.createQuery("SELECT n FROM Nozzle n").getResultList();
            if(nozzleList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Nozzle in the system");
                resultObject.setStatusCode(500);
            }
        }
        else{
            nozzleList=(List<Nozzle>) em.createQuery("SELECT n FROM Nozzle n WHERE n.branchId = :branchId").setParameter("branchId", branchId).getResultList();
            if(nozzleList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Nozzle in the system");
                resultObject.setStatusCode(500);
            }
        }
        
        List<Nozzles> nozzlesList=new ArrayList<>();
        for(Nozzle n : nozzleList){
            Nozzles nozzles=new Nozzles();
            nozzles.setBranchId(n.getBranchId());
            nozzles.setBranchName(commonFunctionEjb.getBranchName(n.getBranchId()).getName());
            nozzles.setTankId(n.getTankId());
            nozzles.setTankName(commonFunctionEjb.getTank(n.getTankId()).getName());
            nozzles.setPumpId(n.getPumpId());
            nozzles.setPumpName(commonFunctionEjb.getPumpName(n.getPumpId()).getName());
            nozzles.setNozzleId(n.getNozzleId());
            nozzles.setNozzleName(n.getNozzleName());
            nozzles.setIndex(n.getNozzleIndex());
            nozzles.setPreCalibrationDate(n.getPreCalibrationDate());
            nozzles.setNextCalibrationDate(n.getNextCalibrationDate());
            nozzles.setStatus(n.getNozzleStatus());
            if(n.getNozzleStatus()==7){
                nozzles.setStatusName("ACTIVE");
            }
            else{
                nozzles.setStatusName("DEACTIVE");
            }
            
            nozzlesList.add(nozzles);
            
        }
        
        resultObject.setObject(nozzlesList);
        resultObject.setMessage(nozzlesList.size()+" Nozzles are found");
        resultObject.setStatusCode(100);
        return resultObject;
       
    }
    
    
    
}
