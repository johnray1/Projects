/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityPortal.FaceletsBackBeans;

import com.oltranz.IntercityPortal.coreApiModels.BusDetailsResponseModel;
import com.oltranz.famoco.beans.ServiceProvider;
import com.oltranz.famoco.beans.ServiceProvidersList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import utils.CommonLibrary;
import utils.CommonWebUI;
import utils.CommonWebUI.selectListItemInteger;

/**
 *
 * @author manzi
 */
@ManagedBean
@SessionScoped
public class Bus {
    
    private String numberPlate;
    private Integer ownerId;
    private Boolean isTransporterOwned;
    private String descr;
    private List<selectListItemInteger> serviceProvidersSelectList;
    private String baseUrl="http://41.74.172.132:8080/IntercityTransport/";
    
    
    public Bus(){
        String url=baseUrl+"ServicesProvidersManagementServices/serviceProviders/transporter/1";
        Response response=CommonLibrary.sendRESTRequest(url, "", MediaType.APPLICATION_JSON, "GET");
        
         if(response.getStatus()==200){
            ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String responseBody=response.readEntity(String.class);
            
            try{
                ServiceProvidersList serviceProvidersList= mapper.readValue(responseBody,ServiceProvidersList.class);
                CommonWebUI commonWebUI=new CommonWebUI();
                 serviceProvidersSelectList=new ArrayList();
                serviceProvidersList.getArraylist().forEach(x-> serviceProvidersSelectList.add(commonWebUI.new selectListItemInteger(x.getId(),x.getName())));              
            }
            catch(IOException ex){
                
            }
        }else{
            
        }
       
    }
    
    private String getEditModelJson(){
        String Json="{"
                +"\"numberPlate\":\""+getNumberPlate()+"\""
                +"\"transporterOwned\":"+getIsTransporterOwned()
                +"\"ownerId\":"+ getOwnerId()
                +"\"descr\":\""+getDescr()+"\"";
        return Json;
    }
    
    
    public  void saveNewBus(){
        
        String url=getBaseUrl()+"BusesManagementServices/bus/new";
        Response response=CommonLibrary.sendRESTRequest(url, getEditModelJson(), MediaType.APPLICATION_JSON, "POST");
        
        if(response.getStatus()==200){
            ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String responseBody=response.readEntity(String.class);
            try{
                BusDetailsResponseModel busDetailsResponseModel= mapper.readValue(responseBody,BusDetailsResponseModel.class);
                //detailsModel=busDetailsResponseModel.getBusdetailsmodel();
            }
            catch(IOException ex){
                
            }
        }else{
            
        }
        
    }
    
    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }
    
    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
    
    /**
     * @return the ownerId
     */
    public Integer getOwnerId() {
        return ownerId;
    }
    
    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    
    /**
     * @return the isTransporterOwned
     */
    public Boolean getIsTransporterOwned() {        return isTransporterOwned;
    }
    
    /**
     * @param isTransporterOwned the isTransporterOwned to set
     */
    public void setIsTransporterOwned(Boolean isTransporterOwned) {
        this.isTransporterOwned = isTransporterOwned;
    }
    
    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    
  
    /**
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }
    
    /**
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * @return the serviceProvidersSelectList
     */
    public List<selectListItemInteger> getServiceProvidersSelectList() {
        return serviceProvidersSelectList;
    }

    /**
     * @param serviceProvidersSelectList the serviceProvidersSelectList to set
     */
    public void setServiceProvidersSelectList(List<selectListItemInteger> serviceProvidersSelectList) {
        this.serviceProvidersSelectList = serviceProvidersSelectList;
    }
    
    
    
}
