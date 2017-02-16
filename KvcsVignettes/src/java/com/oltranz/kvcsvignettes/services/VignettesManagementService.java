/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcsvignettes.services;

import com.oltranz.kvcsvignettes.beans.VignettesManager;
import com.oltranz.kvcsvignettes.models.ResultObject;
import com.oltranz.kvcsvignettes.models.VignetteEditModel;
import com.oltranz.kvcsvignettes.models.VignetteFilter;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author JohnRay
 */
@Path("VignettesManagementService")
@Stateless
public class VignettesManagementService {
    
    @EJB
            VignettesManager vignettesManager;
    
    @POST
    @Path("vignette/create")
    @Consumes({"application/xml", "application/json"})
    public String newUser(VignetteEditModel vignetteEditModel) {
        ResultObject result;
        
        if(vignetteEditModel.getId()==null){
            result= vignettesManager.createVignette(vignetteEditModel);
        }
        else{
            result= vignettesManager.editVignette(vignetteEditModel);
        }
        
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    
    @GET
    @Path("vignette/list")
    @Produces({"application/xml", "application/json"})
    public String getVignette() {
        
        ResultObject result= vignettesManager.getVignette();
        return result.getJsonFormat();
    }
    
    
    @POST
    @Path("vignette/filterList")
    @Consumes({"application/xml", "application/json"})
    public String getVignetteFilteredList(VignetteFilter filter) {
        
        ResultObject result= vignettesManager.getVignetteFilteredList(filter);
        return result.getJsonFormat();
    }
    
    @POST
    @Path("vignette/total")
    @Consumes({"application/xml", "application/json"})
    public String getVignetteTotal(VignetteFilter filter) {
        
        ResultObject result= vignettesManager.getVignetteTotal(filter);
        return result.getJsonFormat();
    }
    
    
}
