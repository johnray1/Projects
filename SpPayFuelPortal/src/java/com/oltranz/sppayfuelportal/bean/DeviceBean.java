/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;


import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.DeviceList;
import com.oltranz.sppayfuelportal.model.DeviceSingle;
import com.oltranz.sppayfuelportal.model.PumpNozzleProductModelList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="DeviceBean")
@SessionScoped
public class DeviceBean {
    
    private String branchId;
    private String deviceId;
    private String deviceNo;
    private String pumpName;
    private String nozzleName;
    private String productId;
    private String saveActionName="Save";
    
    private DeviceSingle deviceSingle;
    private DeviceList deviceList;
    
    private PumpNozzleProductModelList pumpNozzleProductModelList;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public void init() {
        setSaveActionName("Save");
    }
    
    public String devices(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu_active");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        int userId=loginBean.getUserId();
        System.out.println(loginBean.getUserId());
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/DeviceManagementService/devices/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            deviceList=(DeviceList)mapper.readValue(jsonResponse, DeviceList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_device.xhtml";
    }
    
    public void deviceForView(int deviceId){
        deviceById(deviceId);
    }
    
    
     public void deviceForEdit(int deviceId){
        deviceById(deviceId);
        saveActionName="Add";
    }
     
     public String saveDevice(){
        if(deviceId.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    public void deviceById(int deviceId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/DeviceManagementService/device/"+deviceId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            deviceSingle=(DeviceSingle)mapper.readValue(jsonResponse, DeviceSingle.class);
            
            this.deviceId=deviceSingle.getDevice().getDeviceId().toString();
            this.deviceNo=deviceSingle.getDevice().getDeviceNo();
            this.branchId=deviceSingle.getDevice().getBranchId().toString();
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String create(){
        
        String url="http://localhost:8080/PayFuel/DeviceManagementService/device/create";
        String  jsonData = "{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"deviceNo\":\""+deviceNo+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        //System.out.println(jsonResponse);
        
        this.branchId=null;
        this.deviceNo=(null);
        
        return "innerpage_device.xhtml";
    }
    
    public String update(){
        
        String url="http://localhost:8080/PayFuel/DeviceManagementService/device/edit";
        String  jsonData = "{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"deviceId\":\""+deviceId+"\",\n" +
                "\"deviceNo\":\""+deviceNo+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        deviceById(Integer.parseInt(deviceId));
        
        return devices();
    }
    
    
    public String pumps(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu_active");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        int userId=loginBean.getUserId();
        System.out.println(loginBean.getUserId());
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/PumpManagementService/getPumpNozzleProductList";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            pumpNozzleProductModelList=(PumpNozzleProductModelList)mapper.readValue(jsonResponse, PumpNozzleProductModelList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_pump.xhtml";
    }
    
    public String createPump(){
        return null;
        
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
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }
    
    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    /**
     * @return the deviceSingle
     */
    public DeviceSingle getDeviceSingle() {
        return deviceSingle;
    }
    
    /**
     * @param deviceSingle the deviceSingle to set
     */
    public void setDeviceSingle(DeviceSingle deviceSingle) {
        this.deviceSingle = deviceSingle;
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
     * @return the pumpNozzleProductModelList
     */
    public PumpNozzleProductModelList getPumpNozzleProductModelList() {
        return pumpNozzleProductModelList;
    }
    
    /**
     * @param pumpNozzleProductModelList the pumpNozzleProductModelList to set
     */
    public void setPumpNozzleProductModelList(PumpNozzleProductModelList pumpNozzleProductModelList) {
        this.pumpNozzleProductModelList = pumpNozzleProductModelList;
    }
    
    /**
     * @return the pumpName
     */
    public String getPumpName() {
        return pumpName;
    }
    
    /**
     * @param pumpName the pumpName to set
     */
    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
    }
    
    /**
     * @return the nozzleName
     */
    public String getNozzleName() {
        return nozzleName;
    }
    
    /**
     * @param nozzleName the nozzleName to set
     */
    public void setNozzleName(String nozzleName) {
        this.nozzleName = nozzleName;
    }
    
    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }
    
    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
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

    
    
}
