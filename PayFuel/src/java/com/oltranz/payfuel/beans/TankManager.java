/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.Deeping;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleForBranch;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.entities.Tanking;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.Tanks;
import com.oltranz.payfuel.models.UserDetailsModel;
import java.util.ArrayList;
import java.util.Iterator;
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
public class TankManager {
    @PersistenceContext
    private EntityManager em;
    
    @EJB
            UserManager userManager;
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    public ResultObject createTank(Tank newTank) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        Branch branch=em.find(Branch.class, newTank.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("BranchId is not created, to which we want to set our Tank");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Tank tank=new Tank();
        tank.setName(newTank.getName());
        tank.setMaxCapacity(newTank.getMaxCapacity());
        tank.setCurrentCapacity(newTank.getCurrentCapacity());
        tank.setPreCalibrationDate(newTank.getPreCalibrationDate());
        tank.setNextCalibrationDate(newTank.getNextCalibrationDate());
        tank.setBranchId(newTank.getBranchId());
        em.persist(tank);
        em.flush();
        
        resultObject.setObject(tank);
        resultObject.setMessage("New Tank successfully created ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject editTank(Tank editTank) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        Tank tank=em.find(Tank.class, editTank.getTankId());
        if(tank==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Tank with id of the given one is found!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        tank.setName(editTank.getName());
        tank.setMaxCapacity(editTank.getMaxCapacity());
        tank.setCurrentCapacity(editTank.getCurrentCapacity());
        tank.setPreCalibrationDate(editTank.getPreCalibrationDate());
        tank.setNextCalibrationDate(editTank.getNextCalibrationDate());
        tank.setBranchId(editTank.getBranchId());
        em.merge(tank);
        em.flush();
        
        resultObject.setObject(tank);
        resultObject.setMessage("New Tank successfully Updated ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getTankList(){
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        List<Tank> tankList=(List<Tank>)em.createQuery("SELECT t FROM Tank t").getResultList();
        if(tankList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Tank in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setObject(tankList);
            resultObject.setMessage(tankList.size()+" Tanks are found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject getTankList(int userId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        //check if user is available
        User user=em.find(User.class,userId);
        if(user==null){
            resultObject.setMessage("User is not Created To Access The Device");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        List<Tanks> tanksList=new ArrayList<>();
        Tank tank;
        List<Tank> tankList=(List<Tank>)em.createQuery("SELECT t FROM Tank t").getResultList();
        //if user id 1 bring all branch
        if(user.getUserId()==1){
            Iterator i=tankList.iterator();
            while (i.hasNext()){
                tank=(Tank) i.next();
                
                Tanks tanks=new Tanks();
                tanks.setTankId(tank.getTankId());
                tanks.setName(tank.getName());
                tanks.setMaxCapacity(tank.getMaxCapacity());
                tanks.setCurrentCapacity(tank.getCurrentCapacity());
                tanks.setPreCalibrationDate(tank.getPreCalibrationDate());
                tanks.setNextCalibrationDate(tank.getNextCalibrationDate());
                tanks.setBranchId(tank.getBranchId());
                tanks.setBranchName(commonFunctionEjb.getBranchName(tank.getBranchId()).getName());
                
                tanks.setStatus(tank.getStatus());
                if(tank.getStatus()==7){
                    tanks.setStatusName("ACTIVE");
                }
                else{
                    tanks.setStatusName("DEACTIVE");
                }
                
                tanksList.add(tanks);
            }
            resultObject.setObject(tanksList);
            resultObject.setMessage(tanksList.size()+" Tanks are found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        //get the user details,roles and its branch
        UserDetailsModel userDetails= (UserDetailsModel) userManager.getUserDetails(user.getUserId()).getObject();
        List<Role> roles=userDetails.getRoles();
        Integer branchId=-1;
        for(Role r: roles){
            
            if(r.getTypeId()==1){
                
                Iterator i=tankList.iterator();
                while (i.hasNext()){
                    tank=(Tank) i.next();
                    
                    Tanks tanks=new Tanks();
                    tanks.setTankId(tank.getTankId());
                    tanks.setName(tank.getName());
                    tanks.setMaxCapacity(tank.getMaxCapacity());
                    tanks.setCurrentCapacity(tank.getCurrentCapacity());
                    tanks.setPreCalibrationDate(tank.getPreCalibrationDate());
                    tanks.setNextCalibrationDate(tank.getNextCalibrationDate());
                    tanks.setBranchId(tank.getBranchId());
                    tanks.setBranchName(commonFunctionEjb.getBranchName(tank.getBranchId()).getName());
                    
                    tanks.setStatus(tank.getStatus());
                    if(tank.getStatus()==7){
                        tanks.setStatusName("ACTIVE");
                    }
                    else{
                        tanks.setStatusName("DEACTIVE");
                    }
                    
                    tanksList.add(tanks);
                }
                resultObject.setObject(tanksList);
                resultObject.setMessage(tanksList.size()+" Tanks are found");
                resultObject.setStatusCode(100);
                return resultObject;
            }
            
            if(r.getTypeId()==2){
                
                List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                if(list.size()>0)
                    
                    branchId=list.get(0).getRoleForBranchPK().getBranchId();
            }
        }
        
        if(branchId==-1){
            resultObject.setObject(null);
            resultObject.setMessage("You are not a staff of any branch to access the Tank");
            resultObject.setObjectClass(Branch.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        //get the branch from userbranchid
        tankList=(List<Tank>)em.createQuery("SELECT t FROM Tank t WHERE t.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        if(tankList.size()>0){
            
            Iterator i=tankList.iterator();
            while (i.hasNext()){
                tank=(Tank) i.next();
                
                Tanks tanks=new Tanks();
                tanks.setTankId(tank.getTankId());
                tanks.setName(tank.getName());
                tanks.setMaxCapacity(tank.getMaxCapacity());
                tanks.setCurrentCapacity(tank.getCurrentCapacity());
                tanks.setPreCalibrationDate(tank.getPreCalibrationDate());
                tanks.setNextCalibrationDate(tank.getNextCalibrationDate());
                tanks.setBranchId(tank.getBranchId());
                tanks.setBranchName(commonFunctionEjb.getBranchName(tank.getBranchId()).getName());
                
                tanks.setStatus(tank.getStatus());
                if(tank.getStatus()==7){
                    tanks.setStatusName("ACTIVE");
                }
                else{
                    tanks.setStatusName("DEACTIVE");
                }
                
                tanksList.add(tanks);
            }
            resultObject.setObject(tanksList);
            resultObject.setMessage(tanksList.size()+" Tanks are found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        resultObject.setObject(null);
        resultObject.setMessage("There are no Tanks in the system");
        resultObject.setStatusCode(500);
        return resultObject;
    }
    
    
    public ResultObject getTankByItsId(Integer tankId){
        
        ResultObject resultObject=new ResultObject();
        Tank tank=em.find(Tank.class,tankId);
        
        if(tank!=null){
            
            Tanks tanks=new Tanks();
            tanks.setTankId(tank.getTankId());
            tanks.setName(tank.getName());
            tanks.setMaxCapacity(tank.getMaxCapacity());
            tanks.setCurrentCapacity(tank.getCurrentCapacity());
            tanks.setPreCalibrationDate(tank.getPreCalibrationDate());
            tanks.setNextCalibrationDate(tank.getNextCalibrationDate());
            tanks.setBranchId(tank.getBranchId());
            tanks.setBranchName(commonFunctionEjb.getBranchName(tank.getBranchId()).getName());
            
            tanks.setStatus(tank.getStatus());
            if(tank.getStatus()==7){
                tanks.setStatusName("ACTIVE");
            }
            else{
                tanks.setStatusName("DEACTIVE");
            }
            
            resultObject.setMessage("Tank Well found and returned!");
            resultObject.setObject(tanks);
            resultObject.setObjectClass(Tank.class);
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setMessage("Tank with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Tank.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    
    //-----------------------------------------------------Tanking---------------------------------------------------------
    
    public ResultObject createTanking(Tanking newTanking) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Tanking.class);
        
        User user=em.find(User.class, newTanking.getUserId());
        if(user==null){
            resultObject.setObject(null);
            resultObject.setMessage("UserId is not created,Who Can Do Tanking");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Tank tank=em.find(Tank.class, newTanking.getTankId());
        if(tank==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Tank with id of the given one is found For Tanking!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Tanking tanking=new Tanking();
        tanking.setUserId(newTanking.getUserId());
        tanking.setTankId(newTanking.getTankId());
        tanking.setQuantity(newTanking.getQuantity());
        tanking.setDatetime(commonFunctionEjb.getDate());
        em.persist(tanking);
        em.flush();
        
        double currentCapacity=tank.getCurrentCapacity()+tanking.getQuantity();
        tank.setCurrentCapacity(currentCapacity);
        em.merge(tank);
        em.flush();
        
        resultObject.setObject(tanking);
        resultObject.setMessage("Tanking Successfully Happened ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    //-----------------------------------------------------Deeping---------------------------------------------------------
    
    public ResultObject createDeeping(Deeping newDeeping) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Deeping.class);
        
        User user=em.find(User.class, newDeeping.getUserId());
        if(user==null){
            resultObject.setObject(null);
            resultObject.setMessage("UserId is not created,Who Can Do Deeping");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Tank tank=em.find(Tank.class, newDeeping.getTankId());
        if(tank==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Tank with id of the given one is found For Deeping!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        
        Deeping deeping=new Deeping();
        deeping.setUserId(newDeeping.getUserId());
        deeping.setTankId(newDeeping.getTankId());
        deeping.setQuantityBefore(tank.getCurrentCapacity());
        deeping.setQuantityAfter(newDeeping.getQuantityAfter());
        deeping.setDatetime(commonFunctionEjb.getDate());
        em.persist(deeping);
        em.flush();
        
        tank.setCurrentCapacity(deeping.getQuantityAfter());
        em.merge(tank);
        em.flush();
        
        resultObject.setObject(deeping);
        resultObject.setMessage("Deeping Successfully Happened ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
}
