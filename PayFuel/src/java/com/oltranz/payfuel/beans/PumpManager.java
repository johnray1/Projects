/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.Nozzle;
import com.oltranz.payfuel.entities.Product;
import com.oltranz.payfuel.entities.Pump;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleForBranch;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.entities.UserPumpNozzle;
import com.oltranz.payfuel.entities.UserPumpNozzlePK;
import com.oltranz.payfuel.models.AssignedPumpModel;
import com.oltranz.payfuel.models.AssignedPumpModelList;
import com.oltranz.payfuel.models.AssignedPumpModelResponse;
import com.oltranz.payfuel.models.DeviceRegistrationResponse;
import com.oltranz.payfuel.models.NozzleDetailsModel;
import com.oltranz.payfuel.models.PumpDetailsModel;
import com.oltranz.payfuel.models.PumpNozzleProductModel;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.TankDetailsModel;
import com.oltranz.payfuel.models.UserDetailsModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class PumpManager {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
            UserManager userManager;
    @EJB
            ProductManager productManager;
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    public ResultObject createPump(Pump newPump) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Pump.class);
        
        Tank tank=em.find(Tank.class, newPump.getTankId());
        if(tank==null){
            resultObject.setObject(null);
            resultObject.setMessage("Tank is not created, to which we want to set our Pump");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        Branch branch=em.find(Branch.class, tank.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("Branch is not created, to which we want to set our Pump");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Pump pump=new Pump();
        pump.setName(newPump.getName());
        pump.setTankId(newPump.getTankId());
        pump.setBranchId(branch.getBranchId());
        em.persist(pump);
        em.flush();
        
        resultObject.setObject(pump);
        resultObject.setMessage("New pump successfully created ");
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    public ResultObject editPump(Pump editPump){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Pump.class);
        
        Pump pump=em.find(Pump.class, editPump.getPumpId());
        if(pump==null){
            resultObject.setMessage("No Pump with id of the given one is found!");
            resultObject.setObject(null);
            return resultObject;
        }
        
        Tank tank=em.find(Tank.class, editPump.getTankId());
        if(tank==null){
            resultObject.setObject(null);
            resultObject.setMessage("Tank is not created, to which we want to set our Pump");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Branch branch=em.find(Branch.class, tank.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("BranchId is not created, to which we want to set our Pump");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        pump.setName(editPump.getName());
        pump.setTankId(editPump.getTankId());
        pump.setBranchId(branch.getBranchId());
        pump.setStatus(editPump.getStatus());//default value of a device status is 7 even if i dont post status the 7 is coming in this object
        em.merge(pump);
        
        
        resultObject.setMessage("Pump successfully updated");
        resultObject.setObject(pump);
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    public ResultObject getPumpList(){
        
        List<Pump> pumpList=(List<Pump>)em.createNamedQuery("Pump.findAll").getResultList();
        
        ResultObject resultObject= new ResultObject();
        
        resultObject.setObject(pumpList);
        resultObject.setObjectClass(Pump.class);
        if(pumpList.isEmpty()){
            resultObject.setMessage("There are no Pump in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(pumpList.size()+" Pumps are found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject getPumpByItsId(Integer pumpId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Pump.class);
        
        Pump pump=em.find(Pump.class,pumpId);
        
        if(pump!=null){
            resultObject.setMessage("Pump Well found and returned!");
            resultObject.setObject(pump);
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setMessage("Pump with given Id not found!");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public ResultObject getPumpByTankId(Integer tankId){
        
        List<Pump> pumpList=(List<Pump>)em.createQuery("SELECT p FROM Pump p WHERE p.tankId = :tankId").setParameter("tankId", tankId).getResultList();
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Pump.class);
        resultObject.setObject(pumpList);
        
        if(pumpList.isEmpty()){
            resultObject.setMessage("There are no Pump in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(pumpList.size()+" Pumps are found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject deletePump(Integer pumpId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Pump.class);
        
        Pump pump2Delete=em.find(Pump.class,pumpId);
        
        if(pump2Delete==null){
            resultObject.setMessage("Pump with given Id not found!");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Pump pump=em.find(Pump.class, pump2Delete.getPumpId());
        pump.setStatus(pump.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("--yyyy-MM-dd/HH:mm");
        Date deletionTIme=new Date();
        pump.setName(pump.getName()+ sdf.format(deletionTIme));
        em.merge(pump);
        
        
        resultObject.setMessage("Pump successfully sent to dustbin");
        resultObject.setObject(pump);
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getPumpNozzleProductList(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(PumpNozzleProductModel.class);
        
        List<PumpNozzleProductModel> pumpNozzleProductModelList=new ArrayList<>();
        Nozzle nozzle;
        List<Nozzle> nozzleList=(List<Nozzle>)em.createQuery("SELECT n FROM Nozzle n").getResultList();
        if(nozzleList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("No Pumps Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        else{
            Iterator i=nozzleList.iterator();
            while(i.hasNext()){
                nozzle=(Nozzle) i.next();
                
                PumpNozzleProductModel pumpNozzleProductModel=new PumpNozzleProductModel();
                
                pumpNozzleProductModel.setPumpId(nozzle.getPumpId());
                Pump pump=commonFunctionEjb.getPumpName(nozzle.getPumpId());
                pumpNozzleProductModel.setPumpName(pump.getName());
                pumpNozzleProductModel.setPreCalibrationDate(pump.getPreCalibrationDate());
                pumpNozzleProductModel.setNextCalibrationDate(pump.getNextCalibrationDate());
                pumpNozzleProductModel.setNozzleNo(commonFunctionEjb.getNozzleNo(pump.getPumpId()));
                pumpNozzleProductModel.setNozzleId(nozzle.getNozzleId());
                pumpNozzleProductModel.setNozzleName(nozzle.getNozzleName());
                pumpNozzleProductModel.setIndex(nozzle.getNozzleIndex());
                pumpNozzleProductModel.setProductId(nozzle.getProductId());
                Product product=commonFunctionEjb.getProductName(nozzle.getProductId());
                pumpNozzleProductModel.setProductName(product.getName());
                Tank tank=commonFunctionEjb.getTank(pump.getTankId());
                pumpNozzleProductModel.setBranchId(tank.getBranchId());
                Branch branch=commonFunctionEjb.getBranchName(tank.getBranchId());
                pumpNozzleProductModel.setBranchName(branch.getName());
                pumpNozzleProductModel.setStatus(pump.getStatus());
                pumpNozzleProductModel.setTankId(pump.getTankId());
                pumpNozzleProductModel.setTankName(tank.getName());
                
                pumpNozzleProductModelList.add(pumpNozzleProductModel);
            }
            resultObject.setObject(pumpNozzleProductModelList);
            resultObject.setMessage(pumpNozzleProductModelList.size()+" "+"Data found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
    }
    
    public ResultObject getPumpNozzleProductListByBranchId(Integer branchId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(PumpNozzleProductModel.class);
        
        List<PumpNozzleProductModel> pumpNozzleProductModelList=new ArrayList<>();
        Nozzle nozzle;
        List<Nozzle> nozzleList=(List<Nozzle>)em.createQuery("SELECT n FROM Nozzle n WHERE n.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        if(nozzleList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("No Pumps Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        else{
            Iterator i=nozzleList.iterator();
            while(i.hasNext()){
                nozzle=(Nozzle) i.next();
                
                PumpNozzleProductModel pumpNozzleProductModel=new PumpNozzleProductModel();
                
                pumpNozzleProductModel.setPumpId(nozzle.getPumpId());
                Pump pump=commonFunctionEjb.getPumpName(nozzle.getPumpId());
                pumpNozzleProductModel.setPumpName(pump.getName());
                pumpNozzleProductModel.setPreCalibrationDate(pump.getPreCalibrationDate());
                pumpNozzleProductModel.setNextCalibrationDate(pump.getNextCalibrationDate());
                pumpNozzleProductModel.setNozzleNo(commonFunctionEjb.getNozzleNo(pump.getPumpId()));
                pumpNozzleProductModel.setNozzleId(nozzle.getNozzleId());
                pumpNozzleProductModel.setNozzleName(nozzle.getNozzleName());
                pumpNozzleProductModel.setIndex(nozzle.getNozzleIndex());
                pumpNozzleProductModel.setProductId(nozzle.getProductId());
                Product product=commonFunctionEjb.getProductName(nozzle.getProductId());
                pumpNozzleProductModel.setProductName(product.getName());
                Tank tank=commonFunctionEjb.getTank(pump.getTankId());
                pumpNozzleProductModel.setBranchId(tank.getBranchId());
                Branch branch=commonFunctionEjb.getBranchName(tank.getBranchId());
                pumpNozzleProductModel.setBranchName(branch.getName());
                pumpNozzleProductModel.setStatus(pump.getStatus());
                pumpNozzleProductModel.setTankId(pump.getTankId());
                pumpNozzleProductModel.setTankName(tank.getName());
                
                pumpNozzleProductModelList.add(pumpNozzleProductModel);
            }
            resultObject.setObject(pumpNozzleProductModelList);
            resultObject.setMessage(pumpNozzleProductModelList.size()+" "+"Data found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
    }
    
    public ResultObject getPumpNozzleProduct(int nozzleId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(PumpNozzleProductModel.class);
        
        Nozzle nozzle=em.find(Nozzle.class,nozzleId);
        
        if(nozzle==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Pumps Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        else{
            
            PumpNozzleProductModel pumpNozzleProductModel=new PumpNozzleProductModel();
            
            pumpNozzleProductModel.setPumpId(nozzle.getPumpId());
            Pump pump=commonFunctionEjb.getPumpName(nozzle.getPumpId());
            pumpNozzleProductModel.setPumpName(pump.getName());
            pumpNozzleProductModel.setPreCalibrationDate(pump.getPreCalibrationDate());
            pumpNozzleProductModel.setNextCalibrationDate(pump.getNextCalibrationDate());
            pumpNozzleProductModel.setNozzleNo(commonFunctionEjb.getNozzleNo(pump.getPumpId()));
            pumpNozzleProductModel.setNozzleId(nozzle.getNozzleId());
            pumpNozzleProductModel.setNozzleName(nozzle.getNozzleName());
            pumpNozzleProductModel.setIndex(nozzle.getNozzleIndex());
            pumpNozzleProductModel.setProductId(nozzle.getProductId());
            Product product=commonFunctionEjb.getProductName(nozzle.getProductId());
            pumpNozzleProductModel.setProductName(product.getName());
            Tank tank=commonFunctionEjb.getTank(pump.getTankId());
            pumpNozzleProductModel.setBranchId(tank.getBranchId());
            Branch branch=commonFunctionEjb.getBranchName(tank.getBranchId());
            pumpNozzleProductModel.setBranchName(branch.getName());
            pumpNozzleProductModel.setStatus(pump.getStatus());
            pumpNozzleProductModel.setTankId(pump.getTankId());
            pumpNozzleProductModel.setTankName(tank.getName());
            
            resultObject.setObject(pumpNozzleProductModel);
            resultObject.setMessage("Pump Data found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
    }
    
    
//----------------------------------------Android UserData To AssignPump-------------------------------------------------------------------------------------------
    
    public ResultObject assignUserToPumpNozzle(AssignedPumpModelList assignedPumpModelListIp){
        
        ResultObject resultObject=new ResultObject();
        
        List<AssignedPumpModelResponse> assignedPumpModelList=new ArrayList<>();
        for (AssignedPumpModel assignedPumpModel : assignedPumpModelListIp.getAssignPumpModel()){
            
            resultObject = persistAssignedPumpModel(assignedPumpModel);
            
            //assignedPumpModelList.add((AssignedPumpModel) resultObject.getObject());
            assignedPumpModelList.add((AssignedPumpModelResponse) resultObject.getObject());
            
            resultObject.setObject(assignedPumpModelList);
            resultObject.setMessage("Successfull Response ");
            resultObject.setStatusCode(100);
        }
        
        
        return resultObject;
        
    }
    
    public ResultObject persistAssignedPumpModel(AssignedPumpModel assignedPumpModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(AssignedPumpModel.class);
        
        AssignedPumpModelResponse assignedPumpModelResponse=new AssignedPumpModelResponse();
        
        //first we check if its duplicate entry
        UserPumpNozzlePK userPumpNozzlePK=new UserPumpNozzlePK();
        userPumpNozzlePK.setUserId(assignedPumpModel.getUserId());
        userPumpNozzlePK.setPumpId(assignedPumpModel.getPumpId());
        userPumpNozzlePK.setNozzleId(assignedPumpModel.getNozzleId());
        UserPumpNozzle userPumpNozzle=em.find(UserPumpNozzle.class, userPumpNozzlePK);
        if(userPumpNozzle!=null){
            assignedPumpModelResponse.setUserId(assignedPumpModel.getUserId());
            assignedPumpModelResponse.setPumpId(assignedPumpModel.getPumpId());
            assignedPumpModelResponse.setNozzleId(assignedPumpModel.getNozzleId());
            assignedPumpModelResponse.setMessage("Duplicate Entry");
            assignedPumpModelResponse.setStatus(500);
            resultObject.setObject(assignedPumpModelResponse);
            return resultObject;
        }
        
        //check if the nozzle id is already assigned
        
        
        //persisting the details
        UserPumpNozzlePK createUserPumpNozzlePK=new UserPumpNozzlePK();
        createUserPumpNozzlePK.setUserId(assignedPumpModel.getUserId());
        createUserPumpNozzlePK.setPumpId(assignedPumpModel.getPumpId());
        createUserPumpNozzlePK.setNozzleId(assignedPumpModel.getNozzleId());
        
        UserPumpNozzle createUserPumpNozzle=new UserPumpNozzle();
        createUserPumpNozzle.setUserPumpNozzlePK(createUserPumpNozzlePK);
        em.persist(createUserPumpNozzle);
        em.flush();
        
        assignedPumpModelResponse.setUserId(createUserPumpNozzle.getUserPumpNozzlePK().getUserId());
        assignedPumpModelResponse.setPumpId(createUserPumpNozzle.getUserPumpNozzlePK().getPumpId());
        assignedPumpModelResponse.setNozzleId(createUserPumpNozzle.getUserPumpNozzlePK().getNozzleId());
        assignedPumpModelResponse.setMessage("Successfully persist");
        assignedPumpModelResponse.setStatus(100);
        
        resultObject.setObject(assignedPumpModelResponse);
        return resultObject;
    }
    
    
    
//-----------------------------------------Android UserData To Get Pump Details------------------------------------------------------------------------------------
    
    public ResultObject getTankPumpNozzleDetailsByUserId(Integer userId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(PumpDetailsModel.class);
        try{
            User user=em.find(User.class,userId);
            if(user==null){
                resultObject.setMessage("User is not Created To Access The Pumps");
                resultObject.setObject(null);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            
            //get the user details
            UserDetailsModel userDetails= (UserDetailsModel) userManager.getUserDetails(user.getUserId()).getObject();
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
                resultObject.setMessage("You are not a staff of any branch");
                resultObject.setObjectClass(DeviceRegistrationResponse.class);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            
            //check pumps according to branch id
            List<TankDetailsModel> tankDetailsList=new ArrayList<>();
            List<Tank> tankList=em.createQuery("SELECT t FROM Tank t WHERE t.branchId = :branchId").setParameter("branchId", branchId).getResultList();
            for(Tank t:tankList){
                
                //call pumplistoftank function
                List<PumpDetailsModel> pumpDetailsList=pumpListOfTank(t.getTankId());
                TankDetailsModel tankDetailsModel=new TankDetailsModel();
                tankDetailsModel.setTankName(t.getName());
                tankDetailsModel.setBranchName(commonFunctionEjb.getBranchName(t.getBranchId()).getName());
                tankDetailsModel.setPumpList(pumpDetailsList);
                
                tankDetailsList.add(tankDetailsModel);
            }
            
            resultObject.setObject(tankDetailsList);
            resultObject.setMessage(tankDetailsList.size()+" "+"Tanks found");
            resultObject.setStatusCode(100);
            return resultObject;
            
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public List<PumpDetailsModel> pumpListOfTank(Integer tankId){
        
        List<PumpDetailsModel> pumpDetailsList=new ArrayList<>();
        Pump p;
        List<Pump> pumpList=em.createQuery("SELECT p FROM Pump p WHERE p.tankId = :tankId").setParameter("tankId", tankId).getResultList();
        
        if(pumpList.isEmpty()){
            pumpDetailsList.add(null);
        }
        
        Iterator i=pumpList.iterator();
        while(i.hasNext()){
            p=(Pump) i.next();
            PumpDetailsModel pumpDetailsModel=new PumpDetailsModel();
            pumpDetailsModel.setPumpId(p.getPumpId());
            pumpDetailsModel.setPumpName(p.getName());
            Tank tank=commonFunctionEjb.getTank(p.getTankId());
            //call nozzleListOfPump function
            pumpDetailsModel.setNozzleList(nozzleListOfPump(p.getPumpId(),tank.getBranchId()));
            
            pumpDetailsList.add(pumpDetailsModel);
        }
        return pumpDetailsList;
    }
    
    public List<NozzleDetailsModel> nozzleListOfPump(Integer pumpId,Integer branchId){
        
        List<NozzleDetailsModel> nozzleDetailsList=new ArrayList<>();
        Nozzle n;
        List<Nozzle> nozzleList=em.createQuery("SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId").setParameter("pumpId", pumpId).getResultList();
        if(nozzleList.isEmpty()){
            nozzleDetailsList.add(null);
        }
        else{
            Iterator i=nozzleList.iterator();
            while(i.hasNext()){
                
                n=(Nozzle) i.next();
                //call checkNozzleId function
                UserPumpNozzle userPumpNozzle=checkNozzleId(n.getNozzleId());
                //int nozzleId=userPumpNozzle.getUserPumpNozzlePK().getNozzleId();
                
                NozzleDetailsModel nozzleDetails=new NozzleDetailsModel();
                nozzleDetails.setNozzleId(n.getNozzleId());
                nozzleDetails.setNozzleName(n.getNozzleName());
                nozzleDetails.setNozzleIndex(n.getNozzleIndex());
                nozzleDetails.setProductId(n.getProductId());
                Product product=commonFunctionEjb.getProductName(n.getProductId());
                nozzleDetails.setProductName(product.getName());
                nozzleDetails.setUnitPrice(commonFunctionEjb.getProductPrice(branchId,n.getProductId()));
                if(userPumpNozzle==null){
                    nozzleDetails.setStatus(n.getNozzleStatus());
                    nozzleDetails.setUserName(null);
                }
                else{
                    nozzleDetails.setStatus(8);
                    User user=commonFunctionEjb.getUserName(userPumpNozzle.getUserPumpNozzlePK().getUserId());
                    nozzleDetails.setUserName(user.getFname());
                }
                nozzleDetailsList.add(nozzleDetails);
            }
            
        }
        return nozzleDetailsList;
        
    }
    
    public UserPumpNozzle checkNozzleId(int nozzleId){
        
        UserPumpNozzle userPumpNozzle=new UserPumpNozzle();
        List<UserPumpNozzle> checkNozzle=(List<UserPumpNozzle>)em.createQuery("SELECT u FROM UserPumpNozzle u WHERE u.userPumpNozzlePK.nozzleId = :nozzleId").setParameter("nozzleId", nozzleId).getResultList();
        if(checkNozzle.isEmpty()){
            return null;
        }
        else{
            Iterator i=checkNozzle.iterator();
            while(i.hasNext()){
                userPumpNozzle=(UserPumpNozzle) i.next();
            }
            return userPumpNozzle;
        }
        
    }
    
    
//---------------------------------------------------------------------web--------------------------------------------------------------------------------------------
    
    public ResultObject getPumpNozzleProductListForWeb(int branchId){
        
        ResultObject resultObject;
        if(branchId==0){
            resultObject=getPumpNozzleProductList();
        }
        else{
            resultObject=getPumpNozzleProductListByBranchId(branchId);
        }
        return resultObject;
    }
}
