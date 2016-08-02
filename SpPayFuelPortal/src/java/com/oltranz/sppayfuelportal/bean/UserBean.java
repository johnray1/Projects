/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.RoleList;
import com.oltranz.sppayfuelportal.model.RoleSingle;
import com.oltranz.sppayfuelportal.model.RoleUserList;
import com.oltranz.sppayfuelportal.model.UserList;
import com.oltranz.sppayfuelportal.model.UserSingle;
import java.io.Serializable;
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
@ManagedBean(name="UserBean")
@SessionScoped
public class UserBean implements Serializable{
    
    private String userId;
    private String fname;
    private String otherNames;
    private String password;
    private String repeatPassword;
    private String email;
    private String gender;
    private String phoneNumber;
    private String details;
    private String saveActionName="Save";
    
    private UserSingle userSingle;
    private UserList userList;
    
    private RoleSingle roleSingle;
    private RoleUserList roleUserList;
    private RoleList roleList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    public void init() {
        saveActionName="Save";
    }
    
    public String users(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("deactive");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setSettingClassName("active");
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/UserManagementService/users";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            userList=(UserList)mapper.readValue(jsonResponse, UserList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_user.xhtml?faces-redirect=true";
        
    }
    
    public void userForView(int userId){
        userById(userId);
    }
    
    
    
    public void userForEdit(int userId){
        userById(userId);
        saveActionName="Add";
    }
    
    
    
    public String saveUser(){
        if(userId.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    public void userById(int userId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/UserManagementService/user/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            userSingle=(UserSingle)mapper.readValue(jsonResponse, UserSingle.class);
            roleList=(RoleList) userSingle.getUserDetailsModel().getRoles();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String create(){
        
        String url="http://localhost:8080/PayFuel/UserManagementService/user/create";
        String  jsonData ="{\n" +
                "\"actionUserId\":\""+1+"\",\n" +
                "\"fname\":\""+fname+"\",\n" +
                "\"otherNames\":\""+otherNames+"\",\n" +
                "\"password\":\""+password+"\",\n" +
                "\"repeatPassword\":\""+repeatPassword+"\",\n" +
                "\"email\":\""+email+"\",\n" +
                "\"gender\":\""+gender+"\",\n" +
                "\"phoneNumber\":\""+phoneNumber+"\",\n" +
                "\"details\":\""+details+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        //System.out.println(jsonResponse);
        
        return "innerpage_user.xhtml?faces-redirect=true";
    }
    
    public String update(){
        
        String url="http://localhost:8080/PayFuel/UserManagementService/user/edit";
        String  jsonData ="{\n" +
                "\"actionUserId\":\""+1+"\",\n" +
                "\"userId\":\""+userId+"\",\n" +
                "\"fname\":\""+fname+"\",\n" +
                "\"otherNames\":\""+otherNames+"\",\n" +
                "\"password\":\""+password+"\",\n" +
                "\"repeatPassword\":\""+repeatPassword+"\",\n" +
                "\"email\":\""+email+"\",\n" +
                "\"gender\":\""+gender+"\",\n" +
                "\"phoneNumber\":\""+phoneNumber+"\",\n" +
                "\"details\":\""+details+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        userById(Integer.parseInt(userId));
        
        return users();
    }
    
    
    
    
    
    public String roles(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("deactive");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setSettingClassName("active");
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/UserManagementService/getAllRoles";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            roleList=(RoleList)mapper.readValue(jsonResponse, RoleList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return "innerpage_role.xhtml";
        
    }
    
    public void roleById(int roleId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/UserManagementService/getRoleDetails/"+roleId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            roleSingle=(RoleSingle)mapper.readValue(jsonResponse, RoleSingle.class);
            roleUserList=(RoleUserList) roleSingle.getRoleDetailsModel().getUsers();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        //return "view_branch_popup.xhtml";
    }
    
    
    public String usersOfRole(int roleId){
        
        try{
            String url="http://localhost:8080/PayFuel/UserManagementService/getAllUsersOfARole/role/"+roleId;
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            userList=(UserList)mapper.readValue(jsonResponse, UserList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "view_role_member_popup.xhtml";
        
    }
    public String rolesOfUser(int userId){
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/UserManagementService/getAllRolesOfAUser/user/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            roleList=(RoleList)mapper.readValue(jsonResponse, RoleList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "view_role_popup.xhtml";
        
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
     * @return the userSingle
     */
    public UserSingle getUserSingle() {
        return userSingle;
    }
    
    /**
     * @param userSingle the userSingle to set
     */
    public void setUserSingle(UserSingle userSingle) {
        this.userSingle = userSingle;
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
     * @return the roleUserList
     */
    public RoleUserList getRoleUserList() {
        return roleUserList;
    }

    /**
     * @param roleUserList the roleUserList to set
     */
    public void setRoleUserList(RoleUserList roleUserList) {
        this.roleUserList = roleUserList;
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
