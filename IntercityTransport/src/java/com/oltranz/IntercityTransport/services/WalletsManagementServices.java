/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.beans.WalletsManager;
import com.oltranz.IntercityTransport.entities.Card;
import com.oltranz.IntercityTransport.models.ResultObject;
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
@Path("walletsManagementServices")
public class WalletsManagementServices {
    
    @EJB
            WalletsManager walletsManagementEJB;
    
    @POST
    @Path("loadFunds/{ownerId}/{typeId}/{amount}/{transactionTypeId}")
    @Consumes({"application/xml", "application/json"})
    public String createCard(@PathParam("ownerId") Integer ownerId,@PathParam("typeId") Integer typeId,@PathParam("amount") Integer amount,@PathParam("transactionTypeId") Integer transactionTypeId) {
        ResultObject result= walletsManagementEJB.loadFundOnWallet(ownerId,typeId, amount, transactionTypeId);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("walletBalance/{ownerId}/{typeId}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("ownerId") Integer ownerId,@PathParam("typeId") Integer typeId) {
        ResultObject result= walletsManagementEJB.getWalletBalance(ownerId, typeId);
        return result.getJsonFormat();
    }
    
}
