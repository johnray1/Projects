/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.services;

import com.oltranz.usermanager.bizLogic.MembershipManager;
import com.oltranz.usermanager.entities.User;
import com.oltranz.usermanager.entities.UserRole;
import com.oltranz.usermanager.models.AuthenticationRequestModel;
import com.oltranz.usermanager.models.AuthenticationResponseModel;
import com.oltranz.usermanager.models.UserCreateResponseModel;
import com.oltranz.usermanager.models.UserEditModel;
import com.oltranz.usermanager.models.UserEditResponseModel;
import com.oltranz.usermanager.models.UsersWithDetails;
import java.util.List;
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
            MembershipManager membershipManager;
    
    @POST
    @Path("user/create")
    @Consumes({"application/xml", "application/json"})
    public UserCreateResponseModel newUser(UserEditModel newUser) {
        
        UserCreateResponseModel userCreateResponse= membershipManager.createUser(newUser);
        
        return userCreateResponse;
    }
    
    
    @GET
    @Path("user/edit/{userId}")
    @Consumes({"application/xml", "application/json"})
    public User getEditUser(@PathParam("userId")Integer userId) {
        
        User user= membershipManager.getUser(userId);
        
        return user;
    }
    
    @GET
    @Path("users")
    @Produces({"application/xml", "application/json"})
    public UsersWithDetails getUsers() {        
        UsersWithDetails usersDetailsList= new UsersWithDetails();
        
        usersDetailsList.setUsersList(membershipManager.getAllUsersWithDetails());
        
        return usersDetailsList;
    }
    
    @POST
    @Path("user/edit")
    @Consumes({"application/xml", "application/json"})
    public UserEditResponseModel editUser(UserEditModel user2Edit) {
        
        UserEditResponseModel userEditResponse= membershipManager.updateUser(user2Edit);
        
        
        return userEditResponse;
    }
    
    
    @POST
    @Path("user/Authenticate")
    @Consumes({"application/xml", "application/json"})
    public AuthenticationResponseModel authenticationWithPhoneNumberRequest(AuthenticationRequestModel  authenticationModel) {
        
        AuthenticationResponseModel authenticationResponse= membershipManager.authenticateUser(authenticationModel);
        
        return authenticationResponse;
    }
    
    
    @GET
    @Path("userRole/{userId}")
    @Produces({"application/xml", "application/json"})
    public List<UserRole> getUserRoles(@PathParam("userId")Integer userId) {
        List<UserRole> result= membershipManager.getUserRoles(userId);
        return result;
    }
    
    
}
