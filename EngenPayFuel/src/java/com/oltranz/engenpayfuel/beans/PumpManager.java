/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Nozzle;
import com.oltranz.engenpayfuel.entities.Pump;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleForBranch;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.entities.UserPumpNozzle;
import com.oltranz.engenpayfuel.entities.UserPumpNozzlePK;
import com.oltranz.engenpayfuel.models.AssignedPumpModel;
import com.oltranz.engenpayfuel.models.AssignedPumpModelList;
import com.oltranz.engenpayfuel.models.AssignedPumpModelResponse;
import com.oltranz.engenpayfuel.models.DeviceRegistrationResponse;
import com.oltranz.engenpayfuel.models.NozzleDetailsModel;
import com.oltranz.engenpayfuel.models.PumpDetailsModel;
import com.oltranz.engenpayfuel.models.Pumps;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.UserDetailsModel;
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
        
        
        Branch branch=em.find(Branch.class, newPump.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("Branch is not created, to which we want to set our Pump");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Pump pump=new Pump();
        pump.setName(newPump.getName());
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
        
        
        Branch branch=em.find(Branch.class, editPump.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("BranchId is not created, to which we want to set our Pump");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        pump.setName(editPump.getName());
        pump.setBranchId(editPump.getBranchId());
        //pump.setStatus(editPump.getStatus());//default value of a device status is 7 even if i dont post status the 7 is coming in this object
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
    
    
    //----------------------------------------------------Android--------------------------------------------------------
    
    public ResultObject getPumpNozzleDetailsByUserId(Integer userId){
        
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
            List<PumpDetailsModel> pumpDetailsList=new ArrayList<>();
            List<Pump> pumpList=em.createQuery("SELECT p FROM Pump p WHERE p.branchId = :branchId").setParameter("branchId", branchId).getResultList();
            for(Pump p:pumpList){
                //call pumplistoftank function
                
                PumpDetailsModel pumpDetailsModel=new PumpDetailsModel();
                pumpDetailsModel.setPumpId(p.getPumpId());
                pumpDetailsModel.setPumpName(commonFunctionEjb.getPumpName(p.getPumpId()).getName());
                pumpDetailsModel.setBranchId(p.getBranchId());
                pumpDetailsModel.setStatus(p.getStatus());
                pumpDetailsModel.setNozzleList(nozzleListOfPump(p.getPumpId()));
                pumpDetailsList.add(pumpDetailsModel);
            }
            
            resultObject.setObject(pumpDetailsList);
            resultObject.setMessage(pumpDetailsList.size()+" "+"Pumps found");
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
    
    
    public List<NozzleDetailsModel> nozzleListOfPump(Integer pumpId){
        
        List<NozzleDetailsModel> nozzleDetailsList=new ArrayList<>();
        List<Nozzle> nozzleList=em.createQuery("SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId").setParameter("pumpId",pumpId).getResultList();
        if(nozzleList.isEmpty()){
            nozzleDetailsList.add(null);
        }
        
        for(Nozzle n:nozzleList){
            //call checkNozzleId function
            UserPumpNozzle userPumpNozzle=checkNozzleId(n.getNozzleId());
            NozzleDetailsModel nozzleDetails=new NozzleDetailsModel();
            nozzleDetails.setNozzleId(n.getNozzleId());
            nozzleDetails.setNozzleName(n.getNozzleName());
            nozzleDetails.setNozzleIndex(n.getNozzleIndex());
            nozzleDetails.setProductId(commonFunctionEjb.getTank(n.getTankId()).getProductId());
            nozzleDetails.setProductName(commonFunctionEjb.getProductName(commonFunctionEjb.getTank(n.getTankId()).getProductId()).getName());
            nozzleDetails.setUnitPrice(commonFunctionEjb.getProductPrice(n.getBranchId(), commonFunctionEjb.getTank(n.getTankId()).getProductId()));
            
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
    
    
    
    //----------------web-------------------------------------------------------------------------------------------------------------
    
    public ResultObject getPumpListByBranchId(int branchId){
        
        ResultObject resultObject = new ResultObject();
        resultObject.setObjectClass(Pump.class);
        
        List<Pump> pumpList;
        
        if(branchId==0){
            pumpList=(List<Pump>) em.createQuery("SELECT p FROM Pump p").getResultList();
            if(pumpList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Pump in the system");
                resultObject.setStatusCode(500);
            }
        }
        else{
            pumpList=(List<Pump>) em.createQuery("SELECT p FROM Pump p WHERE p.branchId = :branchId").setParameter("branchId", branchId).getResultList();
            if(pumpList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Pump in the system");
                resultObject.setStatusCode(500);
            }
        }
        
        List<Pumps> pumpsList=new ArrayList<>();
        for(Pump p : pumpList){
            Pumps pumps=new Pumps();
            
            pumps.setPumpId(p.getPumpId());
            pumps.setName(p.getName());
            pumps.setBranchId(p.getBranchId());
            pumps.setBranchName(commonFunctionEjb.getBranchName(p.getBranchId()).getName());
            pumps.setStatus(p.getStatus());
            if(p.getStatus()==7){
                pumps.setStatusName("ACTIVE");
            }
            else{
                pumps.setStatusName("DEACTIVE");
            }
            
            pumpsList.add(pumps);
        }
        
        resultObject.setObject(pumpsList);
        resultObject.setMessage(pumpsList.size()+" Pumps are found");
        resultObject.setStatusCode(100);
        return resultObject;
        
    }
    
}
