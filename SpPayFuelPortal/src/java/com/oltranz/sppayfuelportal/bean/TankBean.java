/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.TankList;
import com.oltranz.sppayfuelportal.model.TankSingle;
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
@ManagedBean(name="TankBean")
@SessionScoped
public class TankBean {
    
    private String tankId;
    private String tankName;
    private String maxCapacity;
    private String currentCapacity;
    private String preCalibrationDate;
    private String nextCalibrationDate;
    private String branchId;
    
    
    private String saveActionName="Save";
    private TankSingle tankSingle;
    private TankList tankList;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public void init() {
        saveActionName="Save";
    }
    
    public String tanks(){
        
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
            String getBranchUrl="http://localhost:8080/PayFuel/TankManagementService/tanks/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            tankList=(TankList)mapper.readValue(jsonResponse, TankList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_tank.xhtml?faces-redirect=true";
    }
    
    public void tankForView(int branchId){
        tankById(branchId);
    }
    
    
    
    public void tankForEdit(int branchId){
        tankById(branchId);
        saveActionName="Add";
    }
    
    public String saveTank(){
        if(tankId.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    public void tankById(int tankId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/TankManagementService/tank/"+tankId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            tankSingle=(TankSingle)mapper.readValue(jsonResponse, TankSingle.class);
            this.tankId=tankSingle.getTank().getTankId().toString();
            this.tankName=tankSingle.getTank().getName();
            this.maxCapacity=tankSingle.getTank().getMaxCapacity().toString();
            this.currentCapacity=tankSingle.getTank().getCurrentCapacity().toString();
            this.branchId=tankSingle.getTank().getBranchId().toString();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String create(){
        return null;
        
//        String url="http://localhost:8080/PayFuel/BranchManagementService/branch/create";
//        String  jsonData = "{\n" +
//                "\"name\":\""+branchName+"\",\n" +
//                "\"descr\":\""+address+"\"\n" +
//                "}";
//        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
//        String jsonResponse=response.readEntity(String.class);
//        System.out.println(jsonResponse);
//        
//        ObjectMapper mapper=new ObjectMapper();
//        try{
//            branchSingle=(BranchSingle)mapper.readValue(jsonResponse, BranchSingle.class);
//            
//            this.branchId=branchSingle.getBranch().getBranchId().toString();
//            this.branchName=branchSingle.getBranch().getName();
//            this.address=branchSingle.getBranch().getDescr();
//            
//        }
//        catch(Exception ex){
//            System.out.println(ex.getMessage());
//            
//        }
//        return branches();
    }
    
    
    
    public String update(){
        
        
        String url="http://localhost:8080/PayFuel/TankManagementService/tank/edit";
        String  jsonData ="{\n" +
                "\"tankId\":\""+tankId+"\",\n" +
                "\"name\":\""+tankName+"\",\n" +
                "\"maxCapacity\":\""+maxCapacity+"\",\n" +
                "\"currentCapacity\":\""+currentCapacity+"\",\n" +
                "\"preCalibrationDate\":\""+preCalibrationDate+"\",\n" +
                "\"nextCalibrationDate\":\""+nextCalibrationDate+"\",\n" +
                "\"branchId\":\""+branchId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        tankById(Integer.parseInt(tankId));
        
        return tanks();
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
     * @return the tankList
     */
    public TankList getTankList() {
        return tankList;
    }

    /**
     * @param tankList the tankList to set
     */
    public void setTankList(TankList tankList) {
        this.tankList = tankList;
    }

    /**
     * @return the tankSingle
     */
    public TankSingle getTankSingle() {
        return tankSingle;
    }

    /**
     * @param tankSingle the tankSingle to set
     */
    public void setTankSingle(TankSingle tankSingle) {
        this.tankSingle = tankSingle;
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
     * @return the tankId
     */
    public String getTankId() {
        return tankId;
    }

    /**
     * @param tankId the tankId to set
     */
    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    /**
     * @return the tankName
     */
    public String getTankName() {
        return tankName;
    }

    /**
     * @param tankName the tankName to set
     */
    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    /**
     * @return the maxCapacity
     */
    public String getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * @return the currentCapacity
     */
    public String getCurrentCapacity() {
        return currentCapacity;
    }

    /**
     * @param currentCapacity the currentCapacity to set
     */
    public void setCurrentCapacity(String currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    /**
     * @return the preCalibrationDate
     */
    public String getPreCalibrationDate() {
        return preCalibrationDate;
    }

    /**
     * @param preCalibrationDate the preCalibrationDate to set
     */
    public void setPreCalibrationDate(String preCalibrationDate) {
        this.preCalibrationDate = preCalibrationDate;
    }

    /**
     * @return the nextCalibrationDate
     */
    public String getNextCalibrationDate() {
        return nextCalibrationDate;
    }

    /**
     * @param nextCalibrationDate the nextCalibrationDate to set
     */
    public void setNextCalibrationDate(String nextCalibrationDate) {
        this.nextCalibrationDate = nextCalibrationDate;
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

    
    
    
    
}
