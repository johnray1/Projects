/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.entities.Bus;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.beans.BusesManager;
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
@Path("BusesManagementServices")
public class BusesManagementServices {
    
    @EJB
            BusesManager BusesManagerEJB;
    
    @POST
    @Path("bus/new")
    @Consumes({"application/xml", "application/json"})
    public String createBus(Bus bus) {
        ResultObject result= BusesManagerEJB.createBus(bus);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("bus/edit")
    @Consumes({"application/xml", "application/json"})
    public String editBus(Bus bus) {
        ResultObject result= BusesManagerEJB.updateBus(bus);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("busToContract/{numberPlate}/{contractId}")
    @Consumes({"application/xml", "application/json"})
    public String busToContract(@PathParam("numberPlate") String numberPlate,@PathParam("contractId") Integer contractId) {
        ResultObject result= BusesManagerEJB.addbusTocontract(numberPlate,contractId);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("busFromContract/{numberPlate}/{contractId}")
    @Consumes({"application/xml", "application/json"})
    public String busFromContract(@PathParam("numberPlate") String numberPlate,@PathParam("contractId") Integer contractId) {
        ResultObject result= BusesManagerEJB.removeBusfromcontract(numberPlate,contractId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("bus/{numberPlate}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("numberPlate") String numberPlate) {
        ResultObject result= BusesManagerEJB.getBus(numberPlate);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("buses")
    @Produces({"application/xml", "application/json"})
    public String getBuses() {
        ResultObject result= BusesManagerEJB.getBusesList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("buses/usedByTransporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getBusesUsedByTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= BusesManagerEJB.getBusesListUsedByTransporter(transporterId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("buses/transporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getBusesByTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= BusesManagerEJB.getBusesListUsedByTransporter(transporterId);
        return result.getJsonFormat();
    }
    
     @GET
    @Path("buses/ownedByTransporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getBusesByOwnedByTransporter(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= BusesManagerEJB.getBusesListOwnedByTransporter(transporterId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("buses/serviceProvider/{serviceProviderId}")
    @Produces({"application/xml", "application/json"})
    public String getBusesByServiceProvider(@PathParam("serviceProviderId") Integer serviceProviderId) {
        ResultObject result= BusesManagerEJB.getBusesListByServiceProvider(serviceProviderId);
        return result.getJsonFormat();
    }
    
      @GET
    @Path("buses/transporter/serviceProvider/{transporterId}/{serviceProviderId}")
    @Produces({"application/xml", "application/json"})
    public String getBusesPerTransporterByServiceProvider(@PathParam("serviceProviderId") Integer serviceProviderId,@PathParam("transporterId") Integer transporterId) {
        ResultObject result= BusesManagerEJB.getBusesListByTransporterByServiceProvider(transporterId,serviceProviderId);
        return result.getJsonFormat();
    }
    
    @DELETE
    @Path("bus/{numberPlate}")
    public String remove(@PathParam("numberPlate") String numberPlate) {
        ResultObject result= BusesManagerEJB.deleteBus(numberPlate);
        return result.getJsonFormat();
    }
    
}
