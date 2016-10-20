/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.services;

import com.oltranz.engenpayfuel.beans.CompanyManager;
import com.oltranz.engenpayfuel.entities.Company;
import com.oltranz.engenpayfuel.models.ResultObject;
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
@Path("CompanyManagementService")
@Stateless
public class CompanyManagementService {
    
    @EJB
    private CompanyManager companyManager;
    
    
    
    @POST
    @Path("company/create")
    @Consumes({"application/xml", "application/json"})
    public String createCompany(Company newCompany) {
        
        ResultObject result=companyManager.createCompany(newCompany);
        return result.getJsonFormat();
    }
   
    @GET
    @Path("companies")
    @Produces({"application/xml", "application/json"})
    public String getCompanies() {
        
        ResultObject result= companyManager.getCompanyList();
        return result.getJsonFormat();
    }

    @GET
    @Path("company/{id}")
    @Produces({"application/xml", "application/json"})
    public String getBranchByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= companyManager.getCompanyByItsId(id);
        return result.getJsonFormat();
    }
}
