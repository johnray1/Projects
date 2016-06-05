/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.beans.TransportersManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author manzi
 */
@Stateless
@Path("TransportersManagementServices")
public class TransportersManagementServices {
    
    @EJB
            TransportersManager TransportersManagerEJB;
    
    @POST
    @Path("transporter/new")
    @Consumes({"application/xml", "application/json"})
    public String createTransporter(Transporter transporter) {
        ResultObject result= TransportersManagerEJB.createTransporter(transporter);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("transporter/edit")
    @Consumes({"application/xml", "application/json"})
    public String editTransporter(Transporter transporter) {
        ResultObject result= TransportersManagerEJB.updateTransporter(transporter);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("transporter/{id}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("id") Integer id) {
        ResultObject result= TransportersManagerEJB.getTransporter(id);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("transporterByUserRole/{userRoleId}")
    @Produces({"application/xml", "application/json"})
    public String findTransporterByRoleId(@PathParam("userRoleId") Integer userRoleId) {
        ResultObject result= TransportersManagerEJB.getTransporterByUserRole(userRoleId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("transporters")
    @Produces({"application/xml", "application/json"})
    public String getTransporters() {
        ResultObject result= TransportersManagerEJB.getTransportersList();
        return result.getJsonFormat();
    }
    
    @POST
    @Path("transporter/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String remove(@PathParam("id") Integer id) {
        ResultObject result= TransportersManagerEJB.deleteTransporter(id);
        return result.getJsonFormat();
    }
    
}
