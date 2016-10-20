/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Device;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleForBranch;

import com.oltranz.engenpayfuel.models.DeviceRegistrationModel;
import com.oltranz.engenpayfuel.models.DeviceRegistrationResponse;
import com.oltranz.engenpayfuel.models.Devices;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.UserDetailsModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class DeviceManager {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
            UserManager userManager;
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    
    
    public ResultObject createDevice(Device newDevice) {
        
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DeviceRegistrationResponse.class);
        
        Branch branch=em.find(Branch.class, newDevice.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("BranchId is not created, to which we want to set our Device");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        //check if device name is available before
        int deviceIsAvailable=commonFunctionEjb.isDeviceAvailable(newDevice.getDeviceName());
        if(deviceIsAvailable!=0){
            resultObject.setObjectClass(DeviceRegistrationResponse.class);
            resultObject.setObject(null);
            resultObject.setMessage("Device name already used by another");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Device device=new Device();
        device.setDeviceName(newDevice.getDeviceName());
        device.setSerialNo(newDevice.getSerialNo());
        device.setBranchId(newDevice.getBranchId());
        em.persist(device);
        em.flush();
        
        DeviceRegistrationResponse deviceRegistrationResponse=new DeviceRegistrationResponse();
        deviceRegistrationResponse.setDeviceId(device.getDeviceId());
        deviceRegistrationResponse.setDeviceName(device.getDeviceName());
        deviceRegistrationResponse.setStatus(device.getStatus());
        deviceRegistrationResponse.setBranchId(device.getBranchId());
        deviceRegistrationResponse.setBranchName(em.find(Branch.class,device.getBranchId()).getName());
        
        resultObject.setObject(deviceRegistrationResponse);
        resultObject.setMessage("New Device successfully created ");
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    public ResultObject editDevice(Device editDevice){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Device.class);
        Device device=em.find(Device.class, editDevice.getDeviceId());
        
        
        if(device==null){
            resultObject.setMessage("No Device with id of the given one is found!");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //check if device is available before
        int deviceIsAvailable=commonFunctionEjb.isDeviceAvailable(editDevice.getDeviceName());
        if(deviceIsAvailable!=0){
            resultObject.setObject(null);
            resultObject.setMessage("Device name already used by another");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        device.setDeviceName(editDevice.getDeviceName());
        device.setBranchId(editDevice.getBranchId());
        em.merge(device);
        
        
        resultObject.setMessage("Device successfully updated");
        resultObject.setObject(device);
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    public ResultObject getDeviceList(){
        
        List<Device> deviceList=(List<Device>)em.createNamedQuery("Device.findAll").getResultList();
        
        ResultObject resultObject= new ResultObject();
        
        resultObject.setObject(deviceList);
        resultObject.setObjectClass(Device.class);
        if(deviceList.isEmpty()){
            resultObject.setMessage("There are no Device in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(deviceList.size()+" Device were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject getDeviceByItsId(Integer deviceId){
        
        ResultObject resultObject=new ResultObject();
        Device device=em.find(Device.class,deviceId);
        
        if(device!=null){
            
            Devices devices=new Devices();
            devices.setDeviceId(device.getDeviceId());
            devices.setDeviceName(device.getDeviceName());
            devices.setBranchId(device.getBranchId());
            Branch branch=commonFunctionEjb.getBranchName(device.getBranchId());
            devices.setBranchName(branch.getName());
            devices.setStatus(device.getStatus());
            if(device.getStatus()==7){
                devices.setStatusName("ACTIVE");
            }
            else{
                devices.setStatusName("DEACTIVE");
            }
            
            resultObject.setMessage("Device Well found and returned!");
            resultObject.setObject(devices);
            resultObject.setObjectClass(Device.class);
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setMessage("Device with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Device.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public ResultObject getDeviceByBranchId(Integer branchId){
        
        ResultObject resultObject=new ResultObject();
        Device device=commonFunctionEjb.getDevice(branchId);
        
        if(device!=null){
            
            Devices devices=new Devices();
            devices.setDeviceId(device.getDeviceId());
            devices.setDeviceName(device.getDeviceName());
            devices.setBranchId(device.getBranchId());
            Branch branch=commonFunctionEjb.getBranchName(device.getBranchId());
            devices.setBranchName(branch.getName());
            devices.setStatus(device.getStatus());
            if(device.getStatus()==7){
                devices.setStatusName("ACTIVE");
            }
            else{
                devices.setStatusName("DEACTIVE");
            }
            
            resultObject.setMessage("Device Well found and returned!");
            resultObject.setObject(devices);
            resultObject.setObjectClass(Device.class);
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setMessage("Device with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Device.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public ResultObject deleteDevice(Integer deviceId){
        
        ResultObject resultObject=new ResultObject();
        Device device2Delete=em.find(Device.class, deviceId);
        
        if(device2Delete==null){
            resultObject.setMessage("Device with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Device.class);
            return resultObject;
        }
        
        Device device=em.find(Device.class, device2Delete.getDeviceId());
        device.setStatus(device.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("--yyyy-MM-dd/HH:mm");
        Date deletionTIme=new Date();
        device.setDeviceName(device.getDeviceName()+ sdf.format(deletionTIme));
        em.merge(device);
        
        
        resultObject.setMessage("Device successfully sent to dustbin");
        resultObject.setObject(device);
        resultObject.setObjectClass(Device.class);
        return resultObject;
    }
  
    
    
//------------------------------------------android-------------------------------------------------------------------
    
    public ResultObject registerSellingDevice(DeviceRegistrationModel regDeviceModel){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DeviceRegistrationResponse.class);
        
        //Authenticating email and password
        resultObject=userManager.authenticateWebUser(regDeviceModel.getEmail(), regDeviceModel.getPassword());
        try{
            if(resultObject.getObject()==null){
                resultObject.setMessage("Authentication Failed");
                resultObject.setObjectClass(DeviceRegistrationResponse.class);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            //get the user details
            UserDetailsModel userDetails= (UserDetailsModel) resultObject.getObject();
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
                resultObject.setMessage("You are not a staff of any branch authorized to register selling devices");
                resultObject.setObjectClass(DeviceRegistrationResponse.class);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            //check device if same serial is available
            Device device=commonFunctionEjb.getSerialNo(regDeviceModel.getSerialNumber());
            if(device==null){
                
                Device newDevice=new Device();
                newDevice.setDeviceName(regDeviceModel.getDeviceId());
                newDevice.setSerialNo(regDeviceModel.getSerialNumber());
                newDevice.setBranchId(branchId);
                return createDevice(newDevice);
            }
            else{
                device.setDeviceName(regDeviceModel.getDeviceId());
                device.setBranchId(branchId);
                em.merge(device);
                em.flush();
                
                DeviceRegistrationResponse deviceRegistrationResponse=new DeviceRegistrationResponse();
                deviceRegistrationResponse.setDeviceId(device.getDeviceId());
                deviceRegistrationResponse.setDeviceName(device.getDeviceName());
                deviceRegistrationResponse.setStatus(device.getStatus());
                deviceRegistrationResponse.setBranchId(device.getBranchId());
                deviceRegistrationResponse.setBranchName(em.find(Branch.class,device.getBranchId()).getName());
                
                resultObject.setObjectClass(DeviceRegistrationResponse.class);
                resultObject.setObject(deviceRegistrationResponse);
                resultObject.setMessage("New Device successfully created ");
                resultObject.setStatusCode(100);
                
                return resultObject;
            }
            
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
        
    }
    
//------------------------------------------web-------------------------------------------------------------------
    
    
    public ResultObject getDeviceList(int branchId){
        
        ResultObject resultObject = new ResultObject();
        resultObject.setObjectClass(Device.class);
        
        
        List<Device> deviceList;
        
        if(branchId==0){
            deviceList=(List<Device>) em.createQuery("SELECT d FROM Device d ").getResultList();
            if(deviceList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Device in the system");
                resultObject.setStatusCode(500);
            }
        }
        else{
            deviceList=(List<Device>) em.createQuery("SELECT d FROM Device d WHERE d.branchId = :branchId").setParameter("branchId", branchId).getResultList();
            if(deviceList.isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("There are no Device in the system");
                resultObject.setStatusCode(500);
            }
        }
        
        List<Devices> devicesList=new ArrayList<>();
        for(Device device : deviceList){
            Devices devices=new Devices();
            devices.setDeviceId(device.getDeviceId());
            devices.setDeviceName(device.getDeviceName());
            devices.setSerialNo(device.getSerialNo());
            devices.setBranchId(device.getBranchId());
            Branch branch=commonFunctionEjb.getBranchName(device.getBranchId());
            devices.setBranchName(branch.getName());
            devices.setStatus(device.getStatus());
            if(device.getStatus()==7){
                devices.setStatusName("ACTIVE");
            }
            else{
                devices.setStatusName("DEACTIVE");
            }
            devicesList.add(devices);
        }
        
        resultObject.setObject(devicesList);
        resultObject.setMessage(devicesList.size()+" Devices are found");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    
    
    
}
