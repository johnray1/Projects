/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.entities.Contract;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.beans.ContractsManager;
import com.oltranz.IntercityTransport.models.ContractForDisplayModel;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("contractsManagementServices")
public class ContractsManagementServices {
    
    @EJB
            ContractsManager ContractsManagerEJB;
    
    @POST
    @Path("contract/new")
    @Consumes({"application/xml", "application/json"})
    public String createContract(ContractForDisplayModel contract) {
        ResultObject result= ContractsManagerEJB.createContract(contract);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("contract/edit")
    @Consumes({"application/xml", "application/json"})
    public String editContract(ContractForDisplayModel contract) {
        ResultObject result= ContractsManagerEJB.updateContract(contract);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("contract/{id}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("id") Integer id) {
        ResultObject result= ContractsManagerEJB.getContractForDisplay(id);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("contracts")
    @Produces({"application/xml", "application/json"})
    public String getContracts() {
        ResultObject result= ContractsManagerEJB.getContractsList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("contracts/transporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getContractsOfTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= ContractsManagerEJB.getTransporterContractsList(transporterId);
        return result.getJsonFormat();
    }
    
        
    @DELETE
    @Path("contract/{id}")
    public String remove(@PathParam("id") Integer id) {
        ResultObject result= ContractsManagerEJB.deleteContract(id);
        return result.getJsonFormat();
    }
    
}
