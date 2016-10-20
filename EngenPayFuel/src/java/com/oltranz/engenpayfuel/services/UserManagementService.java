/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.UserManager;
import com.oltranz.engenpayfuel.models.AuthenticationModel;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.UserCreateModel;
import com.oltranz.engenpayfuel.models.UserEditModel;
import com.oltranz.engenpayfuel.models.UserWebCreateModel;
import com.oltranz.engenpayfuel.models.UserWebEditModel;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author John
 */
@Path("UserManagementService")
@Stateless
public class UserManagementService {
    
    @EJB
            UserManager userManager;
    
    @POST
    @Path("user/create")
    @Consumes({"application/xml", "application/json"})
    public String newUser(UserCreateModel newUser) {
        
        ResultObject result= userManager.createUser(newUser);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("user/edit")
    @Consumes({"application/xml", "application/json"})
    public String userEdit(UserEditModel userEdit) {
        
        ResultObject result= userManager.updateUser(userEdit);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    
    @POST
    @Path("user/authenticate")
    @Consumes({"application/xml", "application/json"})
    public String authenticationRequest(AuthenticationModel  authenticationModel) {
        
        ResultObject result= userManager.authenticateWebUser(authenticationModel.getEmail(), authenticationModel.getPassword());
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @POST
    @Path("user/permisson/{userId}/{permissions}")
    @Produces({"application/xml", "application/json"})
    public String setUserPermisson(@PathParam("userId") Integer userId,@PathParam("permissions") String permissions) {
        
        ResultObject result= userManager.setUserPermisson(userId,permissions);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("users")
    @Produces({"application/xml", "application/json"})
    public String getAllUsers() {
        ResultObject result= userManager.getAllUsers();
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    
    @GET
    @Path("users/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getAllUsersByBranch(@PathParam("branchId") Integer branchId) {
        ResultObject result= userManager.getUserList(branchId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("getAllUserData")
    @Produces({"application/xml", "application/json"})
    public String getAllUserData() {
        ResultObject result= userManager.getAllUserData();
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    @GET
    @Path("user/{id}")
    @Produces({"application/xml", "application/json"})
    public String findUser(@PathParam("id") Integer id) {
        ResultObject result= userManager.getUserDetails(id);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("getAllRolesOfAUser/user/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getAllRolesOfAUser(@PathParam("userId") Integer userId) {
        ResultObject result= userManager.getUserRoles(userId);
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    @GET
    @Path("getAllUsersOfARole/role/{roleId}")
    @Produces({"application/xml", "application/json"})
    public String getAllUsersOfARole(@PathParam("roleId") Integer roleId) {
        ResultObject result= userManager.getAllUserOfARole(roleId);
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    
    @POST
    @Path("addUserToABranchRole/{userId}/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String addUserToABranchRole(@PathParam("userId") Integer userId,@PathParam("branchId") Integer branchId) {
        
        ResultObject result= userManager.addUserToABranchRole(userId, branchId);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @POST
    @Path("addUser2ARole/{userId}/{roleId}")
    @Produces({"application/xml", "application/json"})
    public String addUser2ARole(@PathParam("userId") Integer userId,@PathParam("roleId") Integer roleId) {
        
        ResultObject result= userManager.addUser2ARole(userId, roleId);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("removeUser4rmRole/{userId}/{roleId}")
    @Produces({"application/xml", "application/json"})
    public String removeUser4rmRole(@PathParam("userId") Integer userId,@PathParam("roleId") Integer roleId) {
        
        ResultObject result= userManager.removeUser4rmRole(userId, roleId);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("getAllRoles")
    @Produces({"application/xml", "application/json"})
    public String getAllRoles() {
        
        ResultObject result= userManager.getAllRoles();
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    @GET
    @Path("getRoleDetails/{roleId}")
    @Produces({"application/xml", "application/json"})
    public String getAllRoles(@PathParam("roleId") Integer roleId) {
        
        ResultObject result= userManager.getRoleDetailsById(roleId);
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    @GET
    @Path("getAllRolesForUser")
    @Produces({"application/xml", "application/json"})
    public String getAllRolesForUser() {
        
        ResultObject result= userManager.getAllRolesForUser();
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    @GET
    @Path("getAllRolesForBranch")
    @Produces({"application/xml", "application/json"})
    public String getAllRolesForBranch() {
        
        ResultObject result= userManager.getAllRolesForBranch();
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
   //-------------------------------------------web-------------------------------------------------------- 
    
    @POST
    @Path("webUser/create")
    @Consumes({"application/xml", "application/json"})
    public String newWebUser(UserWebCreateModel userWebCreateModel) {
        
        ResultObject result= userManager.createUserFromWeb(userWebCreateModel);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("webUser/edit")
    @Consumes({"application/xml", "application/json"})
    public String editWebUser(UserWebEditModel userWebEditModel) {
        
        ResultObject result= userManager.editUserFromWeb(userWebEditModel);
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
}
