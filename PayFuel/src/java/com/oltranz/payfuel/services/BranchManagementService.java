/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.BranchManager;
import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.models.ResultObject;
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
@Path("BranchManagementService")
@Stateless
public class BranchManagementService {
    
    @EJB
            BranchManager branchManager;
    
    
    @POST
    @Path("branch/create")
    @Consumes({"application/xml", "application/json"})
    public String createBranch(Branch newBranch) {
        
        ResultObject result=branchManager.createBranch(newBranch);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("branch/edit")
    @Consumes({"application/xml", "application/json"})
    public String editBranch(Branch editBranch) {
        
        ResultObject result=branchManager.editBranch(editBranch);
        return result.getJsonFormat();
        
    }
    
    
    @GET
    @Path("branches")
    @Produces({"application/xml", "application/json"})
    public String getBranches() {
        
        ResultObject result= branchManager.getBranchList();
        return result.getJsonFormat();
    }
    
    
    
    
    
    @GET
    @Path("branch/{id}")
    @Produces({"application/xml", "application/json"})
    public String getBranchByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= branchManager.getBranchByItsId(id);
        return result.getJsonFormat();
    }
    
    
    @POST
    @Path("branch/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removeBranch(@PathParam("id") Integer id) {
        ResultObject result= branchManager.deleteBranch(id);
        return result.getJsonFormat();
    }
    
    //----------------------------------------web----------------------------------------------------------------------
    
    @GET
    @Path("branches/{branchId}")
    @Produces({"application/xml", "application/json"})
    public String getBranchesByUserId(@PathParam("branchId") Integer branchId) {
        
        ResultObject result= branchManager.getBranchList(branchId);
        return result.getJsonFormat();
    }
}
