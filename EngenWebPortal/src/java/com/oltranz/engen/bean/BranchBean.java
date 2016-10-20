/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.Branch;
import com.oltranz.engen.model.BranchList;
import com.oltranz.engen.model.BranchSingle;
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
@ManagedBean(name="BranchBean")
@SessionScoped
public class BranchBean implements Serializable{
    
    //TO KEEP USER BRANCH DATA
    private int bId;
    
    private String branchId;
    private String branchName;
    private String address;
    private Boolean branchMenuItemRendered;
    private String branchDashRendered="display:none";
    
    private String saveActionName="Save";
    private String popUpLabel;
    
    private BranchSingle branchSingle;
    private Branch branch;
    private BranchList branchList;
    
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    HttpSession session = SessionBean.getSession();
    
    public BranchBean(){
        
//        HttpSession session = SessionBean.getSession();
//        String permissions=(String)session.getAttribute("permissions");
//        byte[] permissionBytes =Common.shared.hexStringToByteArray(permissions);
//        String s=Common.shared.byteArrayToString(permissionBytes);
//        int bitValue=Common.shared.GetBit(permissionBytes, 1);//Position bit in permisson is like [ARRAY]
//        branchMenuItemRendered=bitValue==1;
        
    }
    
    public void init() {
        saveActionName="Save";
    }
    
    public void branches(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu_active");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu");
        branchDashRendered="display:none";
        
        int braId=(int) session.getAttribute("branchId");
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/BranchManagementService/branches/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            branchList=(BranchList)mapper.readValue(jsonResponse, BranchList.class);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_branch.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(BranchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    public void branchView(Branch b){
        branch=b;
    }
    
    
    public void branchEdit(Branch b){
        branchView(b);
        
        popUpLabel="EDIT BRANCH";
        saveActionName="UPDATE";
    }
    
    public void branchAdd(){
        this.branchId="-1";
        this.branchName="";
        this.address="";
        
        popUpLabel="ADD BRANCH";
        saveActionName="CREATE";
    }
    
    public void saveBranch(){
        if(branchId.equals("-1")){
            create();
        }else{
            update();
        }
    }
    
    
    
    
    public void branchById(){
        branchDashRendered=null;
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/BranchManagementService/branch/"+branchId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            branchSingle=(BranchSingle)mapper.readValue(jsonResponse, BranchSingle.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    public void update(){
        
        
        String url="http://localhost:8080/EngenPayFuel/BranchManagementService/branch/edit";
        String  jsonData ="{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"name\":\""+branchName+"\",\n" +
                "\"descr\":\""+address+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        branches();
    }
    
    
    public void create(){
        
        if((branchName.equalsIgnoreCase(""))&&(address.equalsIgnoreCase(""))){
            branches();
        }
        else{
            String url="http://localhost:8080/EngenPayFuel/BranchManagementService/branch/create";
            String  jsonData = "{\n" +
                    "\"name\":\""+branchName+"\",\n" +
                    "\"descr\":\""+address+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            branches();
        }
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
     * @return the branchDashRendered
     */
    public String getBranchDashRendered() {
        return branchDashRendered;
    }
    
    /**
     * @param branchDashRendered the branchDashRendered to set
     */
    public void setBranchDashRendered(String branchDashRendered) {
        this.branchDashRendered = branchDashRendered;
    }
    
    /**
     * @return the branch
     */
    public Branch getBranch() {
        return branch;
    }
    
    /**
     * @param branch the branch to set
     */
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    
    
    
    
}
