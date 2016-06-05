/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.Bus;
import com.oltranz.IntercityTransport.entities.Contract;
import com.oltranz.IntercityTransport.entities.ProfileOnSellingDevice;
import com.oltranz.IntercityTransport.entities.ProfileOnSellingDevicePK;
import com.oltranz.IntercityTransport.entities.SellingDevice;
import com.oltranz.IntercityTransport.entities.SellingProfile;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.entities.UserRole;
import com.oltranz.IntercityTransport.entities.UserRoleForTransporter;
import com.oltranz.IntercityTransport.models.AccessToken;
import com.oltranz.IntercityTransport.models.BusDetailsModel;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.SellingDeviceRegistrationModel;
import com.oltranz.IntercityTransport.models.SellingDeviceRegistrationResponse;
import com.oltranz.IntercityTransport.models.SellingProfileWithItemsModel;
import com.oltranz.IntercityTransport.models.UserDetailsModel;
import java.util.ArrayList;
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
public class SellingDevicesManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            BusesManager BusesManagerEJB;
    @EJB
            SellingProfilesManager sellingProfilesManagerEJB;
    
    @EJB
            TransportersManager transportersManagerEJB;
    
    @EJB
            MembershipManager MembershipManagerEJB;
    
    public ResultObject getSellingDevice(String id){
        ResultObject resultObject=new ResultObject();
        SellingDevice SellingDevice=em.find(SellingDevice.class,id);
        
        if(SellingDevice!=null){
            resultObject.setMessage("SellingDevice Well found and returned!");
            resultObject.setObject(SellingDevice);
            resultObject.setObjectClass(SellingDevice.class);
            return resultObject;
        }else{
            resultObject.setMessage("SellingDevice with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(SellingDevice.class);
            return resultObject;
        }
    }
    
    public ResultObject getSellingDeviceProfile(String deviceId){
        ResultObject resultObject= new ResultObject();
        SellingProfileWithItemsModel profileWithItems=null;
        Query query=em.createQuery("select p from ProfileOnSellingDevice p where p.profileOnSellingDevicePK.deviceId=:deviceId");
        query.setParameter("deviceId", deviceId);
        List<ProfileOnSellingDevice> profileOnSellingDevicesList=(List<ProfileOnSellingDevice>)query.getResultList();
        
        if(profileOnSellingDevicesList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("No selling profile is currently assinged to this selling device");
            resultObject.setObjectClass(ProfileOnSellingDevice.class);
            return resultObject;
        }
        
        profileWithItems=(SellingProfileWithItemsModel) sellingProfilesManagerEJB.getSellingProfileWithItems(profileOnSellingDevicesList.get(0).getProfileOnSellingDevicePK().getProfileId()).getObject();
        resultObject.setObject(profileWithItems);
        resultObject.setMessage("Profile Well found and returned");
        resultObject.setObjectClass(ProfileOnSellingDevice.class);
        return resultObject;
        
    }
    
    public ResultObject registerSellingDevice(SellingDeviceRegistrationModel regDeviceModel){
        ResultObject resultObject=new ResultObject();
        resultObject=MembershipManagerEJB.authenticateUser(regDeviceModel.getPhoneNumber(), regDeviceModel.getPassword());
        
        if(resultObject.getObject()==null){
            resultObject.setMessage("Authentication Failed");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        UserDetailsModel userDetails=((AccessToken)resultObject.getObject()).getUserDetails();
        List<UserRole> userRoles=userDetails.getRoles();
        
        Integer transporterId=-1;
        for(UserRole u: userRoles){
            if(u.getTypeId()==3){
                
                Query query =em.createQuery("select ur from UserRoleForTransporter ur where ur.userRoleForTransporterPK.roleId=:roleId");
                query.setParameter("roleId", u.getId());
                List<UserRoleForTransporter> list = (List<UserRoleForTransporter>)query.getResultList();
                
                if(list.size()>0)
                    transporterId=list.get(0).getUserRoleForTransporterPK().getTransporterId();
                
            }
        }
        if(transporterId==-1){
            resultObject.setObject(null);
            resultObject.setMessage("You are not a staff of any transporter authorized to register selling devices");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        SellingDevice newSellingDevice=new SellingDevice();
        newSellingDevice.setId(regDeviceModel.getDeviceName());
        newSellingDevice.setTransporterId(transporterId);
        
        return createSellingDevice(newSellingDevice);
        
    }
    
    public ResultObject createSellingDevice(SellingDevice newSellingDevice){
        ResultObject resultObject=new ResultObject();
        if(em.find(SellingDevice.class, newSellingDevice.getId())!=null){
            
            resultObject.setMessage("Device name already used by another");
            resultObject.setObject(null);
            resultObject.setObjectClass(SellingDeviceRegistrationResponse.class);
            
            return resultObject;
        }
        SellingDevice sellingDevice=new SellingDevice();
        sellingDevice.setCurrentBusNumberPlate(newSellingDevice.getCurrentBusNumberPlate());
        sellingDevice.setDecr(newSellingDevice.getDecr());
        sellingDevice.setId(newSellingDevice.getId());
        sellingDevice.setTransporterId(newSellingDevice.getTransporterId());
        sellingDevice.setTypeId(newSellingDevice.getTypeId());
        em.persist(sellingDevice);
        em.flush();
        
        sellingDevice =(SellingDevice) getSellingDevice(sellingDevice.getId()).getObject();
        SellingDeviceRegistrationResponse response = new SellingDeviceRegistrationResponse();
        response.setDeviceName(sellingDevice.getId());
        response.setTransporterId(sellingDevice.getTransporterId());
        response.setTransporterName(em.find(Transporter.class, sellingDevice.getTransporterId()).getName());
        
        
        resultObject.setMessage("New sellingDevice successfully registered");
        resultObject.setObject(response);
        resultObject.setObjectClass(SellingDeviceRegistrationResponse.class);
        return resultObject;
    }
    
    public ResultObject updateSellingDevice(SellingDevice editSellingDevice){
        SellingDevice sellingDevice=em.find(SellingDevice.class, editSellingDevice.getId());
        sellingDevice.setCurrentBusNumberPlate(editSellingDevice.getCurrentBusNumberPlate());
        sellingDevice.setDecr(editSellingDevice.getDecr());
        sellingDevice.setId(editSellingDevice.getId());
        sellingDevice.setTransporterId(editSellingDevice.getTransporterId());
        sellingDevice.setTypeId(editSellingDevice.getTypeId());
        em.merge(sellingDevice);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("SellingDevice successfully updated");
        resultObject.setObject(sellingDevice);
        resultObject.setObjectClass(SellingDevice.class);
        return resultObject;
    }
    
    public ResultObject attacheSellingDeviceToBus(String id, String numberPlate){
        
        BusDetailsModel bus;
        SellingDevice sellingDevice;
        ResultObject resultObject=new ResultObject();
        try{
            resultObject=getSellingDevice(id);
            if(resultObject.getObject()==null)
                
                return resultObject;
            else
                sellingDevice=(SellingDevice)resultObject.getObject();
            
            resultObject=BusesManagerEJB.getBus(numberPlate);
            if(resultObject.getObject()==null)
                return resultObject;
            else
                bus=(BusDetailsModel)resultObject.getObject();
            
            
            //A device belongs to transporter
            //A Bus belong or is attached to a contract
            //A device can only be used/added to a bus that belongs to the same transporter
            //or to a bus already attached to a contract of the same transporter
            
            if(bus.isTransporterOwned()){
                if(!bus.getOwnerId().equals(sellingDevice.getTransporterId())){
                    resultObject.setObject(null);
                    resultObject.setMessage("A selling device can not be used in a bus bellonging to a different Transporter");
                    return resultObject;
                }
            }else{
                // get current contract to which this bus is attachd on
                
                Contract contract=(Contract)BusesManagerEJB.getBusCurrentContract(numberPlate).getObject();
                if(contract==null){
                    resultObject.setObject(null);
                    resultObject.setMessage("This bus does belong neither is it attached to any current contract with to the owner of this device");
                    return resultObject;
                }else{
                    if(!contract.getTransporterId().equals(sellingDevice.getTransporterId())){
                        resultObject.setObject(null);
                        resultObject.setMessage("This bus does belong neither is it attached to any current contract with to the owner of this device");
                        return resultObject;
                    }
                }
            }
            sellingDevice.setCurrentBusNumberPlate(numberPlate);
            em.merge(sellingDevice);
            
            resultObject=getSellingDevice(sellingDevice.getId());
            resultObject.setMessage("Device well attached to the bus with number plate "+numberPlate);
            return resultObject;
        }catch(Exception ex){
            resultObject.setMessage("system error:"+ ex.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(SellingDevice.class);
            return resultObject;
        }
    }
    
    public ResultObject attachProfile2SellingDevice(Integer sellingProfileId,String sellingDeviceId){
        ResultObject resultObject =new ResultObject();
        if(em.find(SellingDevice.class, sellingDeviceId)==null){
            resultObject.setObject(null);
            resultObject.setMessage("A selling device can not be found");
            return resultObject;
        }
        
        if(em.find(SellingProfile.class, sellingProfileId)==null){
            resultObject.setObject(null);
            resultObject.setMessage("A selling Profile can not be found");
            return resultObject;
        }
        
        ProfileOnSellingDevicePK profileOnSellingDevicePK =new ProfileOnSellingDevicePK(sellingProfileId,sellingDeviceId);
        if(!(em.find(ProfileOnSellingDevice.class, profileOnSellingDevicePK)==null)){
            resultObject.setObject(null);
            resultObject.setObjectClass(ProfileOnSellingDevice.class);
            resultObject.setMessage("selling Profile already on this device");
            return resultObject;
        }
        
        ProfileOnSellingDevice profileOnSellingDevice=new ProfileOnSellingDevice();
        profileOnSellingDevice.setProfileOnSellingDevicePK(profileOnSellingDevicePK);
        em.persist(profileOnSellingDevice);
        
        resultObject.setObject(profileOnSellingDevice);
        resultObject.setObjectClass(ProfileOnSellingDevice.class);
        resultObject.setMessage("selling Profile well attached to device");
        return resultObject;
    }
    
    public ResultObject detachSellingDeviceFromCurrentBus(String id){
        SellingDevice sellingDevice;
        ResultObject resultObject=getSellingDevice(id);
        if(resultObject.getObject()==null)
            return resultObject;
        else
            sellingDevice=(SellingDevice)resultObject.getObject();
        
        if(sellingDevice.getCurrentBusNumberPlate().equals("") || sellingDevice.getCurrentBusNumberPlate()==null){
            resultObject.setMessage("This device is not attached to any bus!");
            return resultObject;
        }
        sellingDevice.setCurrentBusNumberPlate("");
        em.merge(sellingDevice);
        resultObject=getSellingDevice(id);
        resultObject.setMessage("Device detached from bus and not attached to any!");
        return resultObject;
    }
    
    public ResultObject getTransporterSellingDevicesList(Integer transporterId){
        ResultObject resultObject= new ResultObject();
        resultObject.setObject(transportersManagerEJB.getTransporter(transporterId));
        
        if(resultObject.getObject()==null)
            return resultObject;
        
        Query query;
        
        query=em.createQuery("select d from SellingDevice d where d.transporterId=:transporterId ");
        query.setParameter("transporterId", transporterId);
        
        List<SellingDevice> sellingDeviceslist=(List<SellingDevice>)query.getResultList();
        
        
        resultObject.setObject(sellingDeviceslist);
        resultObject.setObjectClass(ArrayList.class);
        
        if(sellingDeviceslist.isEmpty()){
            resultObject.setMessage("There are no Selling Device for this transporter");
        }
        else{
            resultObject.setMessage(sellingDeviceslist.size()+" Selling Devices for this transporter well found");
        }
        return resultObject;
    }
    
    public ResultObject getAllSellingDevicesList(){
        ResultObject resultObject= new ResultObject();
        Query query;
        
        query=em.createQuery("select d from SellingDevice d  ");
        
        List<SellingDevice> sellingDevicesList=(List<SellingDevice>)query.getResultList();
        
        resultObject.setObject(sellingDevicesList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(sellingDevicesList.isEmpty()){
            resultObject.setMessage("There are no selling Devices in the system");
        }
        else{
            resultObject.setMessage(sellingDevicesList.size()+" selling Devices  well found");
        }
        
        return resultObject;
        
    }
    
    public ResultObject deleteSellingDevice(SellingDevice sellingDevice2Delete){
        SellingDevice sellingDevice=em.find(SellingDevice.class, sellingDevice2Delete.getId());
        sellingDevice.setStatus(sellingDevice.getStatus()&6);
        
        em.merge(sellingDevice);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("SellingDevice successfully sent to dustbin");
        resultObject.setObject(sellingDevice);
        resultObject.setObjectClass(SellingDevice.class);
        return resultObject;
    }
    
    
}
