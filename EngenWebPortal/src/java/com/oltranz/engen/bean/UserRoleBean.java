/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.RoleList;
import com.oltranz.engen.model.RoleSingle;
import com.oltranz.engen.model.UserDetailsModel;
import com.oltranz.engen.model.UserList;
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
 * @author JohnRay
 */
@ManagedBean(name="UserRoleBean")
@SessionScoped
public class UserRoleBean implements Serializable{
    
    private String userId;
    private String fname;
    private String otherNames;
    private String password;
    private String repeatPassword;
    private String email;
    private String gender;
    private String phoneNumber;
    private String details;
    private String branchRoleId;
    
    private String saveActionName="Save";
    private String popUpLabel;
    
    
    
    private UserDetailsModel userDetailsModel;
    private UserList userList;
    
    private RoleSingle roleSingle;
    private RoleList roleList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    private HttpSession session = SessionBean.getSession();
    
    
    
    
    public void userrole(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu_active");
        templateBean.branchList();
        
        try {
            users();
            roles();
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_userrole.xhtml");
        }
        catch (IOException ex) {
            Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void users(){
        int braId=(int) session.getAttribute("branchId");
        String getUrl="http://localhost:8080/EngenPayFuel/UserManagementService/users/"+braId;
        Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        try {
            userList=(UserList)mapper.readValue(jsonResponse, UserList.class);
        } catch (IOException ex) {
            Logger.getLogger(UserRoleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void userView(UserDetailsModel udm){
        userDetailsModel=udm;
        this.userId=userDetailsModel.getUserId().toString();
        this.fname=userDetailsModel.getFname();
        this.email=userDetailsModel.getEmail();
        this.phoneNumber=userDetailsModel.getPhoneNumber();
        this.branchRoleId=userDetailsModel.getBranchId().toString();
        this.password=null;
        this.repeatPassword=null;
        
    }
    
    
    public void userEdit(UserDetailsModel udm){
        userView(udm);
        
        popUpLabel="EDIT USER";
        saveActionName="UPDATE";
    }
    
    
    public void userAdd(){
        
        this.userId="-1";
        this.fname="";
        this.email="";
        this.phoneNumber="";
        this.branchRoleId="0";
        
        
        
        popUpLabel="ADD USER";
        saveActionName="CREATE";
    }
    
    
    public void saveUser(){
        if(userId.equals("-1")){
            userCreate();
        }else{
            userUpdate();
        }
    }
    
    
    
    public void userCreate(){
        
        if((fname.equalsIgnoreCase(""))||(email.equalsIgnoreCase(""))||(phoneNumber.equalsIgnoreCase(""))||(password.equalsIgnoreCase(""))||(repeatPassword.equalsIgnoreCase(""))){
            
        }
        
        else{
            String url="http://localhost:8080/EngenPayFuel/UserManagementService/webUser/create";
            String  jsonData ="{\n" +
                    "\"actionUserId\":\""+1+"\",\n" +
                    "\"fname\":\""+fname+"\",\n" +
                    "\"otherNames\":\""+fname+"\",\n" +
                    "\"password\":\""+password+"\",\n" +
                    "\"repeatPassword\":\""+repeatPassword+"\",\n" +
                    "\"email\":\""+email+"\",\n" +
                    "\"gender\":\""+"MALE/FEMALE"+"\",\n" +
                    "\"phoneNumber\":\""+phoneNumber+"\",\n" +
                    "\"details\":\""+"Engen Staff"+"\",\n" +
                    "\"branchRoleId\":\""+branchRoleId+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String json=response.toString();
        }
        
        users();
        
    }
    
    public void userUpdate(){
        
        String url="http://localhost:8080/EngenPayFuel/UserManagementService/webUser/edit";
        
        String  jsonData ="{\n" +
                    "\"actionUserId\":\""+1+"\",\n" +
                "\"userId\":\""+userId+"\",\n" +
                    "\"fname\":\""+fname+"\",\n" +
                    "\"otherNames\":\""+fname+"\",\n" +
                    "\"password\":\""+password+"\",\n" +
                    "\"repeatPassword\":\""+repeatPassword+"\",\n" +
                    "\"email\":\""+email+"\",\n" +
                    "\"gender\":\""+"MALE/FEMALE"+"\",\n" +
                    "\"phoneNumber\":\""+phoneNumber+"\",\n" +
                    "\"details\":\""+"Engen Staff"+"\",\n" +
                    "\"branchRoleId\":\""+branchRoleId+"\"\n" +
                    "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        users();
        
    }
    
    
    //----------------------------------------------Role------------------------------------------------------------------
    
    
    public void roles() throws IOException{
        
        String getBranchUrl="http://localhost:8080/EngenPayFuel/UserManagementService/getAllRoles";
        Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        roleList=(RoleList)mapper.readValue(jsonResponse, RoleList.class);
    }
    
    
    public void roleById(int roleId){
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/UserManagementService/getRoleDetails/"+roleId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            roleSingle=(RoleSingle)mapper.readValue(jsonResponse, RoleSingle.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    /**
     * @return the userList
     */
    public UserList getUserList() {
        return userList;
    }
    
    /**
     * @param userList the userList to set
     */
    public void setUserList(UserList userList) {
        this.userList = userList;
    }
    
    /**
     * @return the roleSingle
     */
    public RoleSingle getRoleSingle() {
        return roleSingle;
    }
    
    /**
     * @param roleSingle the roleSingle to set
     */
    public void setRoleSingle(RoleSingle roleSingle) {
        this.roleSingle = roleSingle;
    }
    
    /**
     * @return the roleList
     */
    public RoleList getRoleList() {
        return roleList;
    }
    
    /**
     * @param roleList the roleList to set
     */
    public void setRoleList(RoleList roleList) {
        this.roleList = roleList;
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
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }
    
    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }
    
    /**
     * @return the otherNames
     */
    public String getOtherNames() {
        return otherNames;
    }
    
    /**
     * @param otherNames the otherNames to set
     */
    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the repeatPassword
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }
    
    /**
     * @param repeatPassword the repeatPassword to set
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }
    
    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }
    
    /**
     * @return the userDetailsModel
     */
    public UserDetailsModel getUserDetailsModel() {
        return userDetailsModel;
    }
    
    /**
     * @param userDetailsModel the userDetailsModel to set
     */
    public void setUserDetailsModel(UserDetailsModel userDetailsModel) {
        this.userDetailsModel = userDetailsModel;
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
     * @return the branchRoleId
     */
    public String getBranchRoleId() {
        return branchRoleId;
    }

    /**
     * @param branchRoleId the branchRoleId to set
     */
    public void setBranchRoleId(String branchRoleId) {
        this.branchRoleId = branchRoleId;
    }
    
    
    
    
    
}
