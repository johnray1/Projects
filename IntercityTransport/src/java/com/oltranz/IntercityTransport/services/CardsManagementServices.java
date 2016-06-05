/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.services;

import com.oltranz.IntercityTransport.entities.Card;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.beans.CardsManager;
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
@Path("cardsServices")
public class CardsManagementServices {
    
    @EJB
            CardsManager CardsManagerEJB;
    
    @POST
    @Path("card/new")
    @Consumes({"application/xml", "application/json"})
    public String createCard(Card card) {
        ResultObject result= CardsManagerEJB.createCard(card);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("card/{id}")
    @Produces({"application/xml", "application/json"})
    public String find(@PathParam("id") String id) {
        ResultObject result= CardsManagerEJB.getCard(id);
        return result.getJsonFormat();
    }
    
}
