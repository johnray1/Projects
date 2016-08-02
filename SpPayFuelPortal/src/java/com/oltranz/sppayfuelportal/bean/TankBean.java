/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.NozzleDash;
import com.oltranz.sppayfuelportal.model.PumpDash;
import com.oltranz.sppayfuelportal.model.TankDash;
import com.oltranz.sppayfuelportal.model.TankList;
import com.oltranz.sppayfuelportal.model.TankSingle;
import java.util.List;
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
    private int bId;
    
    private TankDash tankDash1;
    private PumpDash pumpDash1;
    private NozzleDash nozzleDash1;
    private NozzleDash nozzleDash2;
    
    
    
    private TankDash tankDash2;
    private PumpDash pumpDash2;
    private NozzleDash nozzleDash3;
    private NozzleDash nozzleDash4;
    
    private TankDash tankDash3;
    private PumpDash pumpDash3;
    private PumpDash pumpDash4;
    private NozzleDash nozzleDash5;
    private NozzleDash nozzleDash6;
    private NozzleDash nozzleDash7;
    private NozzleDash nozzleDash8;
    
    
    private String saveActionName="Save";
    private TankSingle tankSingle;
    private TankList tankList;
    
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public void init() {
        saveActionName="Save";
    }
    
    public String tanks(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("deactive");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setSettingClassName("active");
        
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/TankManagementService/tanks/"+bId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            
            String jsonResponse = response.readEntity(String.class);
            
            
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
    
    public void tank1Dashboard(){
        try{
            String getUrl="http://localhost:8080/PayFuel/ChartManagementService/tankDashboard/1";
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            tankDash1=(TankDash)mapper.readValue(jsonResponse, TankDash.class);
            
            List<PumpDash> pumpDashList=tankDash1.getPumpDash();
            
            pumpDash1=pumpDashList.get(0);
            List<NozzleDash> nozzleDashList=pumpDash1.getNozzleDash();
            nozzleDash1=nozzleDashList.get(0);
            nozzleDash2=nozzleDashList.get(1);
            
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void tank2Dashboard(){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/ChartManagementService/tankDashboard/2";
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            tankDash2=(TankDash)mapper.readValue(jsonResponse, TankDash.class);
            
            List<PumpDash> pumpDashList=tankDash2.getPumpDash();
            
            pumpDash2=pumpDashList.get(0);
            List<NozzleDash> nozzleDashList=pumpDash2.getNozzleDash();
            nozzleDash3=nozzleDashList.get(0);
            nozzleDash4=nozzleDashList.get(1);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void tank3Dashboard(){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/ChartManagementService/tankDashboard/3";
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            tankDash3=(TankDash)mapper.readValue(jsonResponse, TankDash.class);
            
            List<PumpDash> pumpDashList=tankDash3.getPumpDash();
            
            pumpDash3=pumpDashList.get(0);
            List<NozzleDash> nozzleDashList=pumpDash3.getNozzleDash();
            nozzleDash5=nozzleDashList.get(0);
            nozzleDash6=nozzleDashList.get(1);
            
            pumpDash4=pumpDashList.get(1);
            List<NozzleDash> nozzleDashListt=pumpDash4.getNozzleDash();
            nozzleDash7=nozzleDashListt.get(0);
            nozzleDash8=nozzleDashListt.get(1);
            
            
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
    
    /**
     * @return the tankDash1
     */
    public TankDash getTankDash1() {
        return tankDash1;
    }
    
    /**
     * @param tankDash1 the tankDash1 to set
     */
    public void setTankDash1(TankDash tankDash1) {
        this.tankDash1 = tankDash1;
    }
    
    
    
    /**
     * @return the tankDash2
     */
    public TankDash getTankDash2() {
        return tankDash2;
    }
    
    /**
     * @param tankDash2 the tankDash2 to set
     */
    public void setTankDash2(TankDash tankDash2) {
        this.tankDash2 = tankDash2;
    }
    
    /**
     * @return the pumpDash2
     */
    
    
    /**
     * @return the tankDash3
     */
    public TankDash getTankDash3() {
        return tankDash3;
    }
    
    /**
     * @param tankDash3 the tankDash3 to set
     */
    public void setTankDash3(TankDash tankDash3) {
        this.tankDash3 = tankDash3;
    }
    
    /**
     * @return the pumpDash3
     */
    
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
     * @return the pumpDash1
     */
    public PumpDash getPumpDash1() {
        return pumpDash1;
    }

    /**
     * @param pumpDash1 the pumpDash1 to set
     */
    public void setPumpDash1(PumpDash pumpDash1) {
        this.pumpDash1 = pumpDash1;
    }

    

    /**
     * @return the nozzleDash1
     */
    public NozzleDash getNozzleDash1() {
        return nozzleDash1;
    }

    /**
     * @param nozzleDash1 the nozzleDash1 to set
     */
    public void setNozzleDash1(NozzleDash nozzleDash1) {
        this.nozzleDash1 = nozzleDash1;
    }

    /**
     * @return the nozzleDash2
     */
    public NozzleDash getNozzleDash2() {
        return nozzleDash2;
    }

    /**
     * @param nozzleDash2 the nozzleDash2 to set
     */
    public void setNozzleDash2(NozzleDash nozzleDash2) {
        this.nozzleDash2 = nozzleDash2;
    }

    /**
     * @return the pumpDash2
     */
    public PumpDash getPumpDash2() {
        return pumpDash2;
    }

    /**
     * @param pumpDash2 the pumpDash2 to set
     */
    public void setPumpDash2(PumpDash pumpDash2) {
        this.pumpDash2 = pumpDash2;
    }

    /**
     * @return the nozzleDash3
     */
    public NozzleDash getNozzleDash3() {
        return nozzleDash3;
    }

    /**
     * @param nozzleDash3 the nozzleDash3 to set
     */
    public void setNozzleDash3(NozzleDash nozzleDash3) {
        this.nozzleDash3 = nozzleDash3;
    }

    /**
     * @return the nozzleDash4
     */
    public NozzleDash getNozzleDash4() {
        return nozzleDash4;
    }

    /**
     * @param nozzleDash4 the nozzleDash4 to set
     */
    public void setNozzleDash4(NozzleDash nozzleDash4) {
        this.nozzleDash4 = nozzleDash4;
    }

    /**
     * @return the pumpDash3
     */
    public PumpDash getPumpDash3() {
        return pumpDash3;
    }

    /**
     * @param pumpDash3 the pumpDash3 to set
     */
    public void setPumpDash3(PumpDash pumpDash3) {
        this.pumpDash3 = pumpDash3;
    }

    /**
     * @return the pumpDash4
     */
    public PumpDash getPumpDash4() {
        return pumpDash4;
    }

    /**
     * @param pumpDash4 the pumpDash4 to set
     */
    public void setPumpDash4(PumpDash pumpDash4) {
        this.pumpDash4 = pumpDash4;
    }

    /**
     * @return the nozzleDash5
     */
    public NozzleDash getNozzleDash5() {
        return nozzleDash5;
    }

    /**
     * @param nozzleDash5 the nozzleDash5 to set
     */
    public void setNozzleDash5(NozzleDash nozzleDash5) {
        this.nozzleDash5 = nozzleDash5;
    }

    /**
     * @return the nozzleDash6
     */
    public NozzleDash getNozzleDash6() {
        return nozzleDash6;
    }

    /**
     * @param nozzleDash6 the nozzleDash6 to set
     */
    public void setNozzleDash6(NozzleDash nozzleDash6) {
        this.nozzleDash6 = nozzleDash6;
    }

    /**
     * @return the nozzleDash7
     */
    public NozzleDash getNozzleDash7() {
        return nozzleDash7;
    }

    /**
     * @param nozzleDash7 the nozzleDash7 to set
     */
    public void setNozzleDash7(NozzleDash nozzleDash7) {
        this.nozzleDash7 = nozzleDash7;
    }

    /**
     * @return the nozzleDash8
     */
    public NozzleDash getNozzleDash8() {
        return nozzleDash8;
    }

    /**
     * @param nozzleDash8 the nozzleDash8 to set
     */
    public void setNozzleDash8(NozzleDash nozzleDash8) {
        this.nozzleDash8 = nozzleDash8;
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

    
    
    
}
