/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.beans;

import com.oltranz.engennotify.library.CommonLibrary;
import com.oltranz.engennotify.models.Branch;
import com.oltranz.engennotify.models.Diping;
import com.oltranz.engennotify.models.DipingList;
import com.oltranz.engennotify.models.Tank;
import com.oltranz.engennotify.models.TankModel;
import com.oltranz.engennotify.models.TankList;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@Stateless
public class EngenPayFuelCommunicateManager {
    
    
    public List<TankModel> tankList(int branchId){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/tanks/"+branchId;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        List<TankModel> tankList = null;
        try {
            TankList  tList=(TankList)mapper.readValue(jsonResponse, TankList.class);
            tankList=tList.getTankList();
        }
        catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tankList;
    }
    
    public TankModel tank(int tankId){
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/tank/"+tankId;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        
        String jsonResponse = response.readEntity(String.class);
        Tank tank=new Tank();
        try {
            tank=(Tank)mapper.readValue(jsonResponse, Tank.class);
            
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        TankModel tankModel=tank.getTank();
        return tankModel;
    }
    
    public List<Diping> dipingList(int tankId,String dipingTime){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/diping/notify/"+tankId+"/"+dipingTime;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        List<Diping> dipingList = null;
        try {
            DipingList  dList=(DipingList)mapper.readValue(jsonResponse, DipingList.class);
            dipingList=dList.getDipingList();
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dipingList;
    }
    
    public Branch branch(int braId){
        
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/EngenPayFuel/BranchManagementService/branch/"+braId;
        Response response = CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        Branch  branch = null;
        try {
            branch = (Branch)mapper.readValue(jsonResponse, Branch.class);
        } catch (IOException ex) {
            Logger.getLogger(NotificationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return branch;
        
    }
    
    
    
}
