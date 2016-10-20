/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;


import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.Device;
import com.oltranz.engen.model.DeviceList;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="DeviceBean")
@SessionScoped
public class DeviceBean implements Serializable{
    
    //TO KEEP USER BRANCH DATA
    private int bId;
    
    private String branchId;
    private String deviceId;
    private String deviceNo;
    private String saveActionName="Save";
    
    private Device device;
    private DeviceList deviceList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    HttpSession session = SessionBean.getSession();
    
    public void init() {
        setSaveActionName("Save");
    }
    
    public void devices(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu_active");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu");
        
        int braId=(int) session.getAttribute("branchId");
        try{
            String getBranchUrl="http://localhost:8080/EngenPayFuel/DeviceManagementService/devices/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            deviceList=(DeviceList)mapper.readValue(jsonResponse, DeviceList.class);
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_device.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DeviceBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deviceView(Device d){
        device=d;
    }
    
    
   
    
    
    
    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }
    
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    
    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }
    
    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    /**
     * @return the deviceNo
     */
    public String getDeviceNo() {
        return deviceNo;
    }
    
    /**
     * @param deviceNo the deviceNo to set
     */
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    
    /**
     * @return the deviceList
     */
    public DeviceList getDeviceList() {
        return deviceList;
    }
    
    /**
     * @param deviceList the deviceList to set
     */
    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }
    
    
    
    
    /**
     * @return the templateBean
     */
    public TemplateBean getTemplateBean() {
        return templateBean;
    }
    
    /**
     * @param templateBean the templateBean to set
     */
    public void setTemplateBean(TemplateBean templateBean) {
        this.templateBean = templateBean;
    }
    
    
    
    /**
     * @return the saveActionName
     */
    public String getSaveActionName() {
        return saveActionName;
    }
    
    /**
     * @param saveActionName the saveActionName to set
     */
    public void setSaveActionName(String saveActionName) {
        this.saveActionName = saveActionName;
    }
    
    /**
     * @return the bId
     */
    public int getbId() {
        return bId;
    }
    
    /**
     * @param bId the bId to set
     */
    public void setbId(int bId) {
        this.bId = bId;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(Device device) {
        this.device = device;
    }
    
    
    
}
