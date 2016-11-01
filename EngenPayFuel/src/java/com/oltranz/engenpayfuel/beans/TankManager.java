/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Diping;
import com.oltranz.engenpayfuel.entities.Product;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleForBranch;
import com.oltranz.engenpayfuel.entities.Tank;
import com.oltranz.engenpayfuel.entities.Tanking;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.models.LoginOpModel;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.Tanks;
import com.oltranz.engenpayfuel.models.UserDetailsModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        
        Product product=em.find(Product.class, newTank.getProductId());
        if(product==null){
            resultObject.setObject(null);
            resultObject.setMessage("ProductId is not created, to which we want to set our Tank");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Tank tank=new Tank();
        tank.setName(newTank.getName());
        tank.setMaxCapacity(newTank.getMaxCapacity());
        tank.setCurrentCapacity(newTank.getCurrentCapacity());
        tank.setDippedCapacity(newTank.getCurrentCapacity());
        tank.setPreCalibrationDate(newTank.getPreCalibrationDate());
        tank.setNextCalibrationDate(newTank.getNextCalibrationDate());
        tank.setBranchId(newTank.getBranchId());
        tank.setProductId(newTank.getProductId());
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
        
        Product product=em.find(Product.class, editTank.getProductId());
        if(product==null){
            resultObject.setObject(null);
            resultObject.setMessage("ProductId is not created, to which we want to set our Tank");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        tank.setName(editTank.getName());
        tank.setMaxCapacity(editTank.getMaxCapacity());
        tank.setCurrentCapacity(editTank.getCurrentCapacity());
        tank.setDippedCapacity(editTank.getCurrentCapacity());
        tank.setPreCalibrationDate(editTank.getPreCalibrationDate());
        tank.setNextCalibrationDate(editTank.getNextCalibrationDate());
        tank.setBranchId(editTank.getBranchId());
        tank.setProductId(editTank.getProductId());
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
    
    public ResultObject getTankListByBranchId(int branchId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        List<Tank> tankList=(List<Tank>)em.createQuery("SELECT t FROM Tank t WHERE t.branchId = :branchId").setParameter("branchId", branchId).getResultList();
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
    
    public ResultObject tanking(Tanking newTanking) {
        
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
        tanking.setDeliveredBy(newTanking.getDeliveredBy());
        tanking.setPlateNumber(newTanking.getPlateNumber());
        tanking.setTheoriticalTanked(newTanking.getTheoriticalTanked());
        tanking.setWaterValue(newTanking.getWaterValue());
        tanking.setDatetime(commonFunctionEjb.getDate());
        
        tanking.setPreTankingMeasuredDip(newTanking.getPreTankingCalculatedDip());
        tanking.setPreTankingCalculatedDip(newTanking.getPreTankingCalculatedDip());
        
        tanking.setPostTankingMeasuredDip(newTanking.getPostTankingCalculatedDip());
        tanking.setPostTankingCalculatedDip(newTanking.getPostTankingCalculatedDip());
        
        em.persist(tanking);
        em.flush();
        
        
        tank.setCurrentCapacity(tanking.getPostTankingCalculatedDip());
        em.merge(tank);
        em.flush();
        
        resultObject.setObject(tanking);
        resultObject.setMessage("Tanking Successfully Happened ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    
    public ResultObject diping(Diping newDeeping) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Diping.class);
        
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
        
        
        Diping diping=new Diping();
        diping.setUserId(newDeeping.getUserId());
        diping.setTankId(newDeeping.getTankId());
        diping.setMeasuredDip(newDeeping.getCalculatedDip());
        diping.setCalculatedDip(newDeeping.getCalculatedDip());
        diping.setWaterValue(newDeeping.getWaterValue());
        diping.setDatetime(commonFunctionEjb.getDate());
        em.persist(diping);
        em.flush();
        
        Date date=new Date();
        tank.setDippedCapacity(diping.getCalculatedDip());
        tank.setDippedTime(date);
        em.merge(tank);
        em.flush();
        
        resultObject.setObject(diping);
        resultObject.setMessage("Diping Successfully Happened ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
//-----------------------------------------------------Android-------------------------------------------------------------------
    
    public ResultObject login(String pin){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(LoginOpModel.class);
        try{
            
            int userId=commonFunctionEjb.isUserIdAvailable(pin);
            //get the user details
            UserDetailsModel userDetails=(UserDetailsModel) userManager.getUserDetails(userId).getObject();
            List<Role> roles=userDetails.getRoles();
            
            Integer branchId=-1;
            
            for(Role r: roles){
                if(r.getTypeId()==2){
                    
                    List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                    if(list.size()>0)
                        
                        branchId=list.get(0).getRoleForBranchPK().getBranchId();
                }
            }
            
            if(branchId==-1){
                resultObject.setObject(null);
                resultObject.setMessage("You are not a staff of any branch authorized to Login");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            
            LoginOpModel loginOpModel=new LoginOpModel();
            loginOpModel.setUserId(userDetails.getUserId());
            loginOpModel.setName(userDetails.getFname());
            loginOpModel.setPermission(userDetails.getPermissions());
            loginOpModel.setBranchId(branchId);
            Branch branch=commonFunctionEjb.getBranchName(branchId);
            loginOpModel.setBranchName(branch.getName());
            
            
            resultObject.setObject(loginOpModel);
            resultObject.setMessage("Successfull Login");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
    }
    
//----------------web-------------------------------------------------------------------------------------------------------------
    
    public ResultObject getTankListById(int branchId){
        
        ResultObject resultObject = new ResultObject();
        resultObject.setObjectClass(Tank.class);
        List<Tanks> tanksList=new ArrayList<>();
    
        Query query;
        if(branchId==0){
            query=em.createQuery("SELECT t FROM Tank t");
        }
        else{
            query=em.createQuery("SELECT t FROM Tank t WHERE t.branchId = :branchId");
            query.setParameter("branchId", branchId);
        }
        
        List<Tank> tankList=query.getResultList();
        
        for(Tank tank : tankList){
            Tanks tanks=new Tanks();
            
            tanks.setTankId(tank.getTankId());
            tanks.setName(tank.getName());
            tanks.setMaxCapacity(tank.getMaxCapacity());
            tanks.setCurrentCapacity(tank.getCurrentCapacity());
            tanks.setDippedCapacity(tank.getDippedCapacity());
            tanks.setPreCalibrationDate(tank.getPreCalibrationDate());
            tanks.setNextCalibrationDate(tank.getNextCalibrationDate());
            tanks.setBranchId(tank.getBranchId());
            tanks.setBranchName(commonFunctionEjb.getBranchName(tank.getBranchId()).getName());
            tanks.setProductId(tank.getProductId());
            tanks.setProductName(commonFunctionEjb.getProductName(tank.getProductId()).getName());
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
    
    
    
}