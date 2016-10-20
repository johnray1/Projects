/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.Nozzle;
import com.oltranz.engen.model.NozzleList;
import com.oltranz.engen.model.Pump;
import com.oltranz.engen.model.PumpList;
import com.oltranz.engen.model.Tank;
import com.oltranz.engen.model.TankDashList;
import com.oltranz.engen.model.TankList;
import java.io.IOException;
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
@ManagedBean(name="TpnBean")
@SessionScoped
public class TpnBean implements Serializable{
    
    //TO KEEP USER BRANCH DATA
    private String saveActionName="Save",popUpLabel;
    
    private HttpSession session = SessionBean.getSession();
    
    
    /*TANKS */
    private String tankId,tankName,maxCapacity,currentCapacity,preCalibrationDate,nextCalibrationDate,tankBraId,tankProId;
    private Tank tank;
    private TankList tankList;
    
    /*PUMPS */
    private String pumpId,pumpName,pumpBraId;
    private Pump pump;
    private PumpList pumpList;
    
    /*NOZZLES*/
    private String nozzleId,nozzleName,index,nozzleTankId,nozzlePumpId,preCalDate,nextCalDate;
    private Nozzle nozzle;
    private NozzleList nozzleList;
    
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    /*TANK DASHBOARD*/
    
    private TankDashList tankDashList;
    
   
    
    
    public void init() {
        saveActionName="Save";
    }
    
    
    public void tpn(){
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu_active");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu");
        templateBean.branchList();
        templateBean.productList();
        try{
            tanks();
            pumps();
            nozzles();
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_tpn.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(TpnBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tanks() throws IOException{
        
        int braId=(int) getSession().getAttribute("branchId");
        String getBranchUrl="http://localhost:8080/EngenPayFuel/TankManagementService/tanks/"+braId;
        Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        tankList=(TankList)mapper.readValue(jsonResponse, TankList.class);
    }
    
    public void tankView(Tank t){
        tank=t;
        this.tankId=tank.getTankId().toString();
        this.tankName=tank.getName();
        this.maxCapacity=String.valueOf(tank.getMaxCapacity());
        this.currentCapacity=String.valueOf(tank.getCurrentCapacity());
        //this.nextCalibrationDate=tank.getNextCalibrationDate();
        //this.preCalibrationDate=tank.getPreCalibrationDate();
        this.tankBraId=tank.getBranchId().toString();
        this.tankProId=tank.getProductId().toString();
    }
    
    
    public void tankEdit(Tank t){
        tankView(t);
        saveActionName="UPDATE";
        popUpLabel="EDIT TANK";
    }
    
    public void tankAdd(){
        this.tankId="-1";
        this.tankName="";
        this.maxCapacity="";
        this.currentCapacity="";
        this.nextCalibrationDate="";
        this.preCalibrationDate="";
        this.tankBraId="";
        this.tankProId="";
        
        saveActionName="CREATE";
        popUpLabel="ADD TANK";
    }
    
    
    
    public void tankCreate() throws Exception{
        
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/tank/create";
        String  jsonData ="{\n" +
                "\"name\":\""+tankName+"\",\n" +
                "\"maxCapacity\":\""+maxCapacity+"\",\n" +
                "\"currentCapacity\":\""+currentCapacity+"\",\n" +
                "\"preCalibrationDate\":\""+preCalibrationDate+"\",\n" +
                "\"nextCalibrationDate\":\""+nextCalibrationDate+"\",\n" +
                "\"productId\":\""+tankProId+"\",\n" +
                "\"branchId\":\""+tankBraId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        tanks();
    }
    
    public void tankUpdate() throws Exception{
        
        
        String url="http://localhost:8080/EngenPayFuel/TankManagementService/tank/edit";
        String  jsonData ="{\n" +
                "\"tankId\":\""+tankId+"\",\n" +
                "\"name\":\""+tankName+"\",\n" +
                "\"maxCapacity\":\""+maxCapacity+"\",\n" +
                "\"currentCapacity\":\""+currentCapacity+"\",\n" +
                "\"preCalibrationDate\":\""+preCalibrationDate+"\",\n" +
                "\"nextCalibrationDate\":\""+nextCalibrationDate+"\",\n" +
                "\"productId\":\""+tankProId+"\",\n" +
                "\"branchId\":\""+tankBraId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        tanks();
    }
    
    
    public void saveTank(){
        try{
            if(tankId.equals("-1")){
                tankCreate();
            }else{
                tankUpdate();
            }
        }
        catch(Exception ex){
            Logger.getLogger(TpnBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
//--------------------------------------------------------------------------------------------------------------------
    public void pumps() throws IOException{
        
        int braId=(int) getSession().getAttribute("branchId");
        String getBranchUrl="http://localhost:8080/EngenPayFuel/PumpManagementService/pumps/"+braId;
        Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        pumpList=(PumpList)mapper.readValue(jsonResponse, PumpList.class);
    }
    
    public void pumpView(Pump p){
        pump=p;
        this.pumpId=pump.getPumpId().toString();
        this.pumpName=pump.getName();
        this.pumpBraId=pump.getBranchId().toString();
    }
    
    public void pumpEdit(Pump p){
        pumpView(p);
        saveActionName="UPDATE";
        popUpLabel="EDIT PUMP";
    }
    
    public void pumpAdd(){
        this.pumpId="-1";
        this.pumpName="";
        this.pumpBraId="";
        saveActionName="CREATE";
        popUpLabel="ADD PUMP";
    }
    
    public void pumpUpdate() throws Exception{
        
        String url="http://localhost:8080/EngenPayFuel/PumpManagementService/pump/edit";
        String  jsonData = "{\n" +
                "\"pumpId\":\""+pumpId+"\",\n" +
                "\"name\":\""+pumpName+"\",\n" +
                "\"branchId\":\""+pumpBraId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        pumps();
    }
    
    
    
    public void pumpCreate() throws Exception{
        
        if((pumpName.equalsIgnoreCase(""))||(pumpBraId.equalsIgnoreCase(""))){
            
        }
        else{
            
            String url="http://localhost:8080/EngenPayFuel/PumpManagementService/pump/create";
            String  jsonData = "{\n" +
                    "\"name\":\""+pumpName+"\",\n" +
                    "\"branchId\":\""+pumpBraId+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            
        }
        pumps();
    }
    
    
    public void savePump(){
        try{
            if(pumpId.equals("-1")){
                pumpCreate();
            }else{
                pumpUpdate();
            }
        }
        catch(Exception ex){
            Logger.getLogger(TpnBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
//-------------------------------------------------------------------------------------------------------------------------
    
    
    public void nozzles() throws IOException{
        
        int braId=(int) getSession().getAttribute("branchId");
        String getBranchUrl="http://localhost:8080/EngenPayFuel/NozzleManagementService/nozzles/"+braId;
        Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        nozzleList=(NozzleList)mapper.readValue(jsonResponse, NozzleList.class);
    }
    
    public void nozzleView(Nozzle n){
        nozzle=n;
        this.nozzleId=n.getNozzleId().toString();
        this.nozzleName=n.getNozzleName();
        this.index=String.valueOf(n.getIndex());
        //this.preCalDate;
        //this.nextCalDate;
        this.nozzleTankId=n.getTankId().toString();
        this.nozzlePumpId=n.getPumpId().toString();
    }
    
    
    public void nozzleEdit(Nozzle n){
        nozzleView(n);
        saveActionName="UPDATE";
        popUpLabel="EDIT NOZZLE";
    }
    
    public void nozzleAdd(){
        this.nozzleId="-1";
        this.nozzleName="";
        this.index="0";
        //this.preCalDate;
        //this.nextCalDate;
        this.nozzleTankId="";
        this.nozzlePumpId="";
        
        saveActionName="CREATE";
        popUpLabel="ADD NOZZLE";
    }
    
    
    
    public void nozzleUpdate() throws Exception{
        
        String url="http://localhost:8080/EngenPayFuel/NozzleManagementService/nozzle/edit";
        String  jsonData = "{\n" +
                "\"nozzleId\":\""+nozzleId+"\",\n" +
                "\"nozzleName\":\""+nozzleName+"\",\n" +
                "\"nozzleIndex\":\""+index+"\",\n" +
                "\"tankId\":\""+nozzleTankId+"\",\n" +
                "\"pumpId\":\""+nozzlePumpId+"\",\n" +
                "\"preCalibrationDate\":\""+preCalDate+"\",\n" +
                "\"nextCalibrationDate\":\""+nextCalDate+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        nozzles();
    }
    
    public void nozzleCreate() throws Exception{
        
        String url="http://localhost:8080/EngenPayFuel/NozzleManagementService/nozzle/create";
        String  jsonData = "{\n" +
                "\"nozzleName\":\""+nozzleName+"\",\n" +
                "\"nozzleIndex\":\""+index+"\",\n" +
                "\"tankId\":\""+nozzleTankId+"\",\n" +
                "\"pumpId\":\""+nozzlePumpId+"\",\n" +
                "\"preCalibrationDate\":\""+preCalDate+"\",\n" +
                "\"nextCalibrationDate\":\""+nextCalDate+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        nozzles();
    }
    
    
    public void saveNozzle(){
        try{
            if(nozzleId.equals("-1")){
                nozzleCreate();
            }else{
                nozzleUpdate();
            }
        }
        catch(Exception ex){
            Logger.getLogger(TpnBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
//----------------------------------------------------------------------------------------------------------------------------
    public void tankDashboard(){
        try{
            int braId=(int) getSession().getAttribute("branchId");
            String getUrl="http://localhost:8080/EngenPayFuel/ChartManagementService/tankDashboard/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            tankDashList=(TankDashList)mapper.readValue(jsonResponse, TankDashList.class);
            String s=null;
           
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
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
     * @return the popUpLabel
     */
    public String getPopUpLabel() {
        return popUpLabel;
    }
    
    /**
     * @param popUpLabel the popUpLabel to set
     */
    public void setPopUpLabel(String popUpLabel) {
        this.popUpLabel = popUpLabel;
    }
    
    /**
     * @return the nozzleId
     */
    public String getNozzleId() {
        return nozzleId;
    }
    
    /**
     * @param nozzleId the nozzleId to set
     */
    public void setNozzleId(String nozzleId) {
        this.nozzleId = nozzleId;
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
     * @return the index
     */
    public String getIndex() {
        return index;
    }
    
    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }
    
    
    /**
     * @return the pumpList
     */
    public PumpList getPumpList() {
        return pumpList;
    }
    
    /**
     * @param pumpList the pumpList to set
     */
    public void setPumpList(PumpList pumpList) {
        this.pumpList = pumpList;
    }
    
    /**
     * @return the nozzleList
     */
    public NozzleList getNozzleList() {
        return nozzleList;
    }
    
    /**
     * @param nozzleList the nozzleList to set
     */
    public void setNozzleList(NozzleList nozzleList) {
        this.nozzleList = nozzleList;
    }
    
    /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }
    
    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
    }
    
    /**
     * @return the pump
     */
    public Pump getPump() {
        return pump;
    }
    
    /**
     * @param pump the pump to set
     */
    public void setPump(Pump pump) {
        this.pump = pump;
    }
    
    /**
     * @return the nozzle
     */
    public Nozzle getNozzle() {
        return nozzle;
    }
    
    /**
     * @param nozzle the nozzle to set
     */
    public void setNozzle(Nozzle nozzle) {
        this.nozzle = nozzle;
    }
    
    /**
     * @return the tank
     */
    public Tank getTank() {
        return tank;
    }
    
    /**
     * @param tank the tank to set
     */
    public void setTank(Tank tank) {
        this.tank = tank;
    }
    
    /**
     * @return the pumpId
     */
    public String getPumpId() {
        return pumpId;
    }
    
    /**
     * @param pumpId the pumpId to set
     */
    public void setPumpId(String pumpId) {
        this.pumpId = pumpId;
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
     * @return the pumpBraId
     */
    public String getPumpBraId() {
        return pumpBraId;
    }
    
    /**
     * @param pumpBraId the pumpBraId to set
     */
    public void setPumpBraId(String pumpBraId) {
        this.pumpBraId = pumpBraId;
    }
    
    /**
     * @return the nozzleTankId
     */
    public String getNozzleTankId() {
        return nozzleTankId;
    }
    
    /**
     * @param nozzleTankId the nozzleTankId to set
     */
    public void setNozzleTankId(String nozzleTankId) {
        this.nozzleTankId = nozzleTankId;
    }
    
    /**
     * @return the nozzlePumpId
     */
    public String getNozzlePumpId() {
        return nozzlePumpId;
    }
    
    /**
     * @param nozzlePumpId the nozzlePumpId to set
     */
    public void setNozzlePumpId(String nozzlePumpId) {
        this.nozzlePumpId = nozzlePumpId;
    }
    
    /**
     * @return the preCalDate
     */
    public String getPreCalDate() {
        return preCalDate;
    }
    
    /**
     * @param preCalDate the preCalDate to set
     */
    public void setPreCalDate(String preCalDate) {
        this.preCalDate = preCalDate;
    }
    
    /**
     * @return the nextCalDate
     */
    public String getNextCalDate() {
        return nextCalDate;
    }
    
    /**
     * @param nextCalDate the nextCalDate to set
     */
    public void setNextCalDate(String nextCalDate) {
        this.nextCalDate = nextCalDate;
    }
    
    /**
     * @return the tankBraId
     */
    public String getTankBraId() {
        return tankBraId;
    }
    
    /**
     * @param tankBraId the tankBraId to set
     */
    public void setTankBraId(String tankBraId) {
        this.tankBraId = tankBraId;
    }
    
    /**
     * @return the tankProId
     */
    public String getTankProId() {
        return tankProId;
    }
    
    /**
     * @param tankProId the tankProId to set
     */
    public void setTankProId(String tankProId) {
        this.tankProId = tankProId;
    }

    

    /**
     * @return the tankDashList
     */
    public TankDashList getTankDashList() {
        return tankDashList;
    }

    /**
     * @param tankDashList the tankDashList to set
     */
    public void setTankDashList(TankDashList tankDashList) {
        this.tankDashList = tankDashList;
    }

   
    
    
    
    
}
