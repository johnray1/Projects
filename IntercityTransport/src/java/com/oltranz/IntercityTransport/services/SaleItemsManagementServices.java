/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.beans.SaleItemsManager;
import com.oltranz.IntercityTransport.entities.SaleItem;
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
@Path("saleItemsManagementServices")
public class SaleItemsManagementServices {
    
    @EJB
            SaleItemsManager saleItemsManagerEJB;
    
    
    @POST
    @Path("saleItem/new")
    @Consumes({"application/xml", "application/json"})
    public String createSaleItem(SaleItem  newSaleItem) {
        ResultObject result= saleItemsManagerEJB.createSaleItem(newSaleItem);
        return result.getJsonFormat();
    }
    
    
    
    @POST
    @Path("saleItem/edit")
    @Consumes({"application/xml", "application/json"})
    public String editSaleItem(SaleItem  newSaleItem) {
        ResultObject result= saleItemsManagerEJB.updateSaleItem(newSaleItem);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("saleItemToSellingProfile/{itemId}/{profileId}")
    @Consumes({"application/xml", "application/json"})
    public String itemToSaleItem(@PathParam("itemId") Integer itemId,@PathParam("profileId") Integer profileId) {
        ResultObject result= saleItemsManagerEJB.addItemToProfile(itemId, profileId);
        return result.getJsonFormat();
    }
    
    @DELETE
    @Path("saleItemFromSellingProfile/{itemId}/{profileId}")
    @Consumes({"application/xml", "application/json"})
    public String itemFromSaleItem(@PathParam("itemId") Integer itemId,@PathParam("profileId") Integer profileId) {
        ResultObject result= saleItemsManagerEJB.removeItemFromProfile(itemId, profileId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("saleItem/{id}")
    @Produces({"application/xml", "application/json"})
    public String getSaleItem(@PathParam("id") Integer id) {
        ResultObject result= saleItemsManagerEJB.getSaleItem(id);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("saleItems")
    @Produces({"application/xml", "application/json"})
    public String getSaleItems() {
        ResultObject result= saleItemsManagerEJB.getAllSaleItemsList();
        
        return result.getJsonFormat();
    }
    
    @GET
    @Path("saleItems/transporter/{transporterId}")
    @Produces({"application/xml", "application/json"})
    public String getSaleItems(@PathParam("transporterId") Integer transporterId) {
        ResultObject result= saleItemsManagerEJB.getTransporterSaleItemsList(transporterId);
        
        return result.getJsonFormat();
    }
    
    
    
    
    
}
