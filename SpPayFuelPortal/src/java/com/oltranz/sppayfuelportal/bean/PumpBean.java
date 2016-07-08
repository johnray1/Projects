/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.PumpNozzleProductModelList;
import com.oltranz.sppayfuelportal.model.PumpNozzleProductModelSingle;
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
@ManagedBean(name="PumpBean")
@SessionScoped
public class PumpBean {
    
    private String nozzleId;
    private String nozzleName;
    private String index;
    private String saveActionName="Save";
    private PumpNozzleProductModelList pumpNozzleProductModelList;
    
    private PumpNozzleProductModelSingle pumpNozzleProductModelSingle;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public void init() {
        saveActionName="Save";
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
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/PumpManagementService/getPumpNozzleProductList";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            pumpNozzleProductModelList=(PumpNozzleProductModelList)mapper.readValue(jsonResponse, PumpNozzleProductModelList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_pump.xhtml";
    }
    
    
    public void pumpForView(int nozzleId){
        pumpById(nozzleId);
    }
    
    
    
    public void pumpForEdit(int nozzleId){
        pumpById(nozzleId);
        saveActionName="Add";
    }
    
    public String savePump(){
        if(nozzleId.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    
    public void pumpById(int nozzleId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/PumpManagementService/getPumpNozzleProduct/"+nozzleId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            pumpNozzleProductModelSingle=(PumpNozzleProductModelSingle)mapper.readValue(jsonResponse, PumpNozzleProductModelSingle.class);
            
            this.nozzleId=pumpNozzleProductModelSingle.getPumpNozzleProductModel().getNozzleId().toString();
            this.nozzleName=pumpNozzleProductModelSingle.getPumpNozzleProductModel().getNozzleName();
            this.index=String.valueOf(pumpNozzleProductModelSingle.getPumpNozzleProductModel().getIndex());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String create(){
        
        String url="http://localhost:8080/PayFuel/NozzleManagementService/nozzle/edit";
        String  jsonData = "{\n" +
                "\"nozzleName\":\""+nozzleName+"\",\n" +
                "\"nozzleIndex\":\""+index+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        
        this.nozzleId=null;
        this.index=null;
        
        return "innerpage_pump.xhtml";
    }
    
    
    public String update(){
        
        String url="http://localhost:8080/PayFuel/NozzleManagementService/nozzle/edit";
        String  jsonData = "{\n" +
                "\"nozzleId\":\""+nozzleId+"\",\n" +
                "\"nozzleName\":\""+nozzleName+"\",\n" +
                "\"nozzleIndex\":\""+index+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        pumpById(Integer.parseInt(nozzleId));
        
        return pumps();
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
     * @return the pumpNozzleProductModelSingle
     */
    public PumpNozzleProductModelSingle getPumpNozzleProductModelSingle() {
        return pumpNozzleProductModelSingle;
    }

    /**
     * @param pumpNozzleProductModelSingle the pumpNozzleProductModelSingle to set
     */
    public void setPumpNozzleProductModelSingle(PumpNozzleProductModelSingle pumpNozzleProductModelSingle) {
        this.pumpNozzleProductModelSingle = pumpNozzleProductModelSingle;
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
    
    
    
}
