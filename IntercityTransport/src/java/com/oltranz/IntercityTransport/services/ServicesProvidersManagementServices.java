/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.entities.ServiceProvider;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.beans.ServicesProvidersManager;
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
@Path("ServicesProvidersManagementServices")
public class ServicesProvidersManagementServices {
    
    @EJB
            ServicesProvidersManager ServicesProvidersManagerEJB;
    
    @POST
    @Path("serviceProvider/new")
    @Consumes({"application/xml", "application/json"})
    public String createServiceProvider(ServiceProvider serviceProvider) {
        ResultObject result= ServicesProvidersManagerEJB.createServiceProvider(serviceProvider);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("serviceProvider/edit")
    @Consumes({"application/xml", "application/json"})
    public String editServiceProvider(ServiceProvider serviceProvider) {
        ResultObject result= ServicesProvidersManagerEJB.updateServiceProvider(serviceProvider);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("serviceProvider/{id}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("id") Integer id) {
        ResultObject result= ServicesProvidersManagerEJB.getServiceProvider(id);
        return result.getJsonFormat();
    }
    
      @GET
    @Path("serviceProviderByUserRole/{userRoleId}")
    @Produces({"application/xml", "application/json"})
    public String findTransporterByRoleId(@PathParam("userRoleId") Integer userRoleId) {
        ResultObject result= ServicesProvidersManagerEJB.getServiceProviderByUserRole(userRoleId);
        return result.getJsonFormat();
    }
    
     
    @GET
    @Path("serviceProviders")
    @Produces({"application/xml", "application/json"})
    public String getServicesProviders() {
        ResultObject result= ServicesProvidersManagerEJB.getServicesProvidersList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("serviceProviders/transporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getServicesProvidersOfTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= ServicesProvidersManagerEJB.getServicesProvidersList(transporterId);
        return result.getJsonFormat();
    }
    
     @GET
    @Path("serviceProviders/transporter/service/{transporterId}/{serviceId}")
    @Produces({"application/xml", "application/json"})
    public String getServicesProvidersOfTransporterByService(@PathParam("transporterId") Integer transporterId,@PathParam("serviceId") Integer serviceId) {
        ResultObject result= ServicesProvidersManagerEJB.getServicesProvidersList(transporterId,serviceId);
        return result.getJsonFormat();
    }
    
    @DELETE
    @Path("serviceProvider/{id}")
    public String remove(@PathParam("id") Integer id) {
        ResultObject result= ServicesProvidersManagerEJB.deleteServiceProvider(id);
        return result.getJsonFormat();
    }
    
}
