/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.User;
import com.oltranz.airtime.model.UserList;
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
    
    private String id;
    private String msisdn;
    private String firstName;
    private String pass;
    private String email;
    private String otherName;
    private String createdBy;
    private String modifiedBy;
    private String saveActionName="Save";
    private User user;
    private UserList userList;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public void init() {
        setSaveActionName("Save");
    }
    
    //--------------------------------------------------------------List User---------------------------------------------------------------------------------------
    
    public String users(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setUserClassName("omenu_active");
        templateBean.setCustomerClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setLogClassName("omenu");
        
        try{
            
            String url="http://41.74.172.132:8080/AirtimeRechargeSystem/users/list";
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            userList=(UserList)mapper.readValue(jsonResponse, UserList.class);
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "user.xhtml?faces-redirect=true";
        
    }
    
    
    
    //--------------------------------------------------------------View User---------------------------------------------------------------------------------------
    
    public void userForView(User usr){
        userById(usr);
    }
    
    public void userById(User usr){
        
        user=usr;
        this.id=user.getId().toString();
        this.email=user.getEmail();
        this.msisdn=user.getMsisdn();
        this.firstName=user.getfName();
        this.otherName=user.getOtherName();
        this.pass=user.getPassword();
        this.createdBy=user.getCreatedBy();
        
    }
    
    public String saveUser(){
        if(id.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    //--------------------------------------------------------------create Users-----------------------------------------------------------------------------------
    
    public void userForCreate(){
        this.id="-1";
        this.email="";
        this.msisdn="";
        this.firstName="";
        this.otherName="";
        this.pass="";
        saveActionName="Add";
    }
    
    public String create(){
        
        String url="http://41.74.172.132:8080/AirtimeRechargeSystem/users/newuser";
        String  jsonData ="{\n" +
                "\"createdBy\":\""+loginBean.getUsername()+"\",\n" +
                "\"email\":\""+email+"\",\n" +
                "\"fName\":\""+firstName+"\",\n" +
                "\"modifiedBy\":\""+loginBean.getUsername()+"\",\n" +
                "\"msisdn\":\""+msisdn+"\",\n" +
                "\"otherName\":\""+otherName+"\",\n" +
                "\"password\":\""+pass+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        return users();
    }
    
    //--------------------------------------------------------------update Users-----------------------------------------------------------------------------------
    
    public void userForEdit(User user){
        userById(user);
        saveActionName="Add";
    }
    
    public String update(){
        
        String url="http://41.74.172.132:8080/AirtimeRechargeSystem/users/edit";
        String  jsonData ="{\n" +
                "\"createdBy\":\""+createdBy+"\",\n" +
                "\"email\":\""+email+"\",\n" +
                "\"fName\":\""+firstName+"\",\n" +
                "\"id\":\""+id+"\",\n" +
                "\"modifiedBy\":\""+loginBean.getUsername()+"\",\n" +
                "\"msisdn\":\""+msisdn+"\",\n" +
                "\"otherName\":\""+otherName+"\",\n" +
                "\"password\":\""+pass+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        return users();
    }
    
    
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------   
    
    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }
    
    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
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
     * @return the otherName
     */
    public String getOtherName() {
        return otherName;
    }
    
    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }
    
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }
    
    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
    
    
    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
    
}
