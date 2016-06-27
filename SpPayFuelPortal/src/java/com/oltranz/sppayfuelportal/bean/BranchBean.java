/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.Common;
import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.BranchList;
import com.oltranz.sppayfuelportal.model.BranchSingle;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="BranchBean")
@SessionScoped
public class BranchBean implements Serializable{
    
    private String branchId;
    private String branchName;
    private String address;
    private Boolean branchMenuItemRendered;
    private String saveActionName="Save";
    
    
    private BranchSingle branchSingle;
    private BranchList branchList;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public BranchBean(){
        
        
        HttpSession session = SessionBean.getSession();
        String permissions=(String)session.getAttribute("permissions");
        
        byte[] permissionBytes =Common.shared.hexStringToByteArray(permissions);
        
        String s=Common.shared.byteArrayToString(permissionBytes);
        
        int bitValue=Common.shared.GetBit(permissionBytes, 1);//1 means 2nd Position bit in permisson
        
        
        branchMenuItemRendered=bitValue==1;
        
        
    }
    
    public void init() {
        saveActionName="Save";
    }
    
    public String branches(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu_active");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        
        int userId=loginBean.getUserId();
        System.out.println(loginBean.getUserId());
        try{
            String getUrl="http://localhost:8080/PayFuel/BranchManagementService/branches/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            branchList=(BranchList)mapper.readValue(jsonResponse, BranchList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_branch.xhtml";
    }
    
    
    
    public void branchForView(int branchId){
        branchById(branchId);
    }
    
    
    
    public void branchForEdit(int branchId){
        branchById(branchId);
        saveActionName="Add";
    }
    
    public String saveBranch(){
        if(branchId.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    public void branchForCreate(){
        this.branchId="-1";
        this.branchName="";
        this.address="";
        saveActionName="Add";
    }
    
    
    public void branchById(int branchId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/BranchManagementService/branch/"+branchId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            branchSingle=(BranchSingle)mapper.readValue(jsonResponse, BranchSingle.class);
            
            this.branchId=branchSingle.getBranch().getBranchId().toString();
            this.branchName=branchSingle.getBranch().getName();
            this.address=branchSingle.getBranch().getDescr();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String create(){
        
        String url="http://localhost:8080/PayFuel/BranchManagementService/branch/create";
        String  jsonData = "{\n" +
                "\"name\":\""+branchName+"\",\n" +
                "\"descr\":\""+address+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        System.out.println(jsonResponse);
        
        ObjectMapper mapper=new ObjectMapper();
        try{
            branchSingle=(BranchSingle)mapper.readValue(jsonResponse, BranchSingle.class);
            
            this.branchId=branchSingle.getBranch().getBranchId().toString();
            this.branchName=branchSingle.getBranch().getName();
            this.address=branchSingle.getBranch().getDescr();
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            
        }
        return branches();
    }
    
    
    
    public String update(){
        
        
        String url="http://localhost:8080/PayFuel/BranchManagementService/branch/edit";
        String  jsonData ="{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"name\":\""+branchName+"\",\n" +
                "\"descr\":\""+address+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        branchById(Integer.parseInt(branchId));
        
        return branches();
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
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }
    
    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * @return the branchSingle
     */
    public BranchSingle getBranchSingle() {
        return branchSingle;
    }
    
    /**
     * @param branchSingle the branchSingle to set
     */
    public void setBranchSingle(BranchSingle branchSingle) {
        this.branchSingle = branchSingle;
    }
    
    /**
     * @return the branchList
     */
    public BranchList getBranchList() {
        return branchList;
    }
    
    /**
     * @param branchList the branchList to set
     */
    public void setBranchList(BranchList branchList) {
        this.branchList = branchList;
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
     * @return the branchMenuItemRendered
     */
    public Boolean getBranchMenuItemRendered() {
        return branchMenuItemRendered;
    }
    
    /**
     * @param branchMenuItemRendered the branchMenuItemRendered to set
     */
    public void setBranchMenuItemRendered(Boolean branchMenuItemRendered) {
        this.branchMenuItemRendered = branchMenuItemRendered;
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
    
    
    
}
