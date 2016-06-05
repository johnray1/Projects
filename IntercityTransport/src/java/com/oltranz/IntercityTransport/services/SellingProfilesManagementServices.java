/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.beans.SellingProfilesManager;
import com.oltranz.IntercityTransport.entities.SellingProfile;
import com.oltranz.IntercityTransport.models.ResultObject;
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
@Path("sellingProfilesManagementServices")
public class SellingProfilesManagementServices {

    @EJB
    SellingProfilesManager sellingProfilesManagerEJB;

    @POST
    @Path("sellingProfile/new")
    @Consumes({"application/xml", "application/json"})
    public String createSellingProfile(SellingProfile sellingProfile) {
        ResultObject result = sellingProfilesManagerEJB.createSellingProfile(sellingProfile);
        return result.getJsonFormat();
    }

    @POST
    @Path("sellingProfile/edit")
    @Consumes({"application/xml", "application/json"})
    public String editSellingProfile(SellingProfile sellingProfile) {
        ResultObject result = sellingProfilesManagerEJB.updateSellingProfile(sellingProfile);
        return result.getJsonFormat();
    }

    @POST
    @Path("itemToSellingProfile/{itemId}/{sellingProfileId}")
    @Consumes({"application/xml", "application/json"})
    public String itemToSellingProfile(@PathParam("itemId") Integer itemId, @PathParam("sellingProfileId") Integer sellingProfileId) {
        ResultObject result = sellingProfilesManagerEJB.addItemToProfile(itemId, sellingProfileId);
        return result.getJsonFormat();
    }

    @DELETE
    @Path("itemFromSellingProfile/{itemId}/{sellingProfileId}")
    @Consumes({"application/xml", "application/json"})
    public String itemFromSellingProfile(@PathParam("itemId") Integer itemId, @PathParam("sellingProfileId") Integer sellingProfileId) {
        ResultObject result = sellingProfilesManagerEJB.removeItemFromProfile(itemId, sellingProfileId);
        return result.getJsonFormat();
    }

    @GET
    @Path("sellingProfile/{id}")
    @Produces({"application/xml", "application/json"})
    public String getSellingProfile(@PathParam("id") Integer id) {
        ResultObject result = sellingProfilesManagerEJB.getSellingProfile(id);
        return result.getJsonFormat();
    }

    @GET
    @Path("sellingProfileWithItems/{id}")
    @Produces({"application/xml", "application/json"})
    public String getSellingProfileWithItems(@PathParam("id") Integer id) {
        ResultObject result = sellingProfilesManagerEJB.getSellingProfileWithItems(id);
        return result.getJsonFormat();
    }

    @GET
    @Path("sellingProfiles")
    @Produces({"application/xml", "application/json"})
    public String getSellingProfiles() {
        ResultObject result = sellingProfilesManagerEJB.getSellingProfilesList();

        return result.getJsonFormat();
    }

    @GET
    @Path("sellingProfiles/transporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getSellingProfiles(@PathParam("transporterId") Integer transporterId) {
        ResultObject result = sellingProfilesManagerEJB.getTransporterSellingProfilesList(transporterId);

        return result.getJsonFormat();
    }

}
