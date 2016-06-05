/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.beans.MembershipManager;
import com.oltranz.IntercityTransport.models.AuthenticationModel;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.UserEditModel;
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
 * @author manzi
 */
@Stateless
@Path("MembershipServices")
public class MembershipServices {
    
    @EJB
            MembershipManager userManagementProviderEJB;
    
    @POST
    @Path("user/new")
    @Consumes({"application/xml", "application/json"})
    public String newUser(UserEditModel newUser) {
        
        ResultObject result= userManagementProviderEJB.createUser(newUser);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("user/edit")
    @Consumes({"application/xml", "application/json"})
    public String userEdit(UserEditModel userEdit) {
        
        ResultObject result= userManagementProviderEJB.updateUser(userEdit);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("userToTransporter/{userId}/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String addUserToTransporter(@PathParam("userId") Integer userId,@PathParam("transporterId") Integer transporterId) {
        
        ResultObject result= userManagementProviderEJB.addUserToTransporter(userId, transporterId);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("userToPassengers/{userId}")
    @Produces({"application/xml", "application/json"})
    public String addUserToPassengers(@PathParam("userId") Integer userId) {
        
        ResultObject result= userManagementProviderEJB.addPassenger(userId);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }    
    
    @POST
    @Path("userToServiceProvider/{userId}/{serviceProviderId}")
    @Produces({"application/xml", "application/json"})
    public String addUserToServiceProvider(@PathParam("userId") Integer userId,@PathParam("serviceProviderId") Integer serviceProviderId) {
        
        ResultObject result= userManagementProviderEJB.addUserToServiceProvider(userId, serviceProviderId);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("user/authenticate")
    @Consumes({"application/xml", "application/json"})
    public String authenticationRequest(AuthenticationModel  authenticationModel) {
        
        ResultObject result= userManagementProviderEJB.authenticateUser(authenticationModel.getPhoneNumber(), authenticationModel.getPassword());
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("users")
    @Produces({"application/xml", "application/json"})
    public String getAllUsers() {
        ResultObject result= userManagementProviderEJB.getAllUsers();
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
     @GET
    @Path("userRoles/user/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getAllRoleOfUser(@PathParam("userId") Integer userId) {
        ResultObject result= userManagementProviderEJB.getUserRoles(userId);
        String jsonResult=result.getJsonFormat();
        return jsonResult;
    }
    
    @GET
    @Path("user/{id}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("id") Integer id) {
        ResultObject result= userManagementProviderEJB.getUserDetails(id);
        return result.getJsonFormat();
    }
    
//    @GET
//    @Path("roleUsers")
//    @PathParam("roleId")
//    @Produces({"application/xml", "application/json"})
//    public String getAllRoleUsers(@PathParam("roleId") Integer roleId) {
//        ResultObject result= userManagementProviderEJB.getRoleUsers(roleId);
//        String jsonResult=result.getJsonFormat();
//
//        return jsonResult;
//    }
    
    
    
}
