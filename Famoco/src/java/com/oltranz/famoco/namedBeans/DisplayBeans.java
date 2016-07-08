/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.namedBeans;

import com.oltranz.famoco.beans.Buses;
import com.oltranz.famoco.beans.Devices;
import com.oltranz.famoco.beans.FilterSubcontractors;
import com.oltranz.famoco.beans.FilteringParameter;
import com.oltranz.famoco.beans.TransactionsDisplay;
import com.oltranz.famoco.beans.TransporterMessage;
import com.oltranz.models.AccessResult;
import com.oltranz.models.AccessTokenWithMessage;
import java.io.Serializable;
import java.io.StringReader;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import utils.CommonLibrary;

/**
 *
 * @author ismaelnzamutuma
 */
@ManagedBean(name="authe")
@SessionScoped
public class DisplayBeans implements Serializable {
   
    public DisplayBeans()
    {
        
    }
    private AccessTokenWithMessage accessResult;
    
   private TransactionsDisplay transactions;
   private Buses buses ;
    private String from;
    private String to;
    private int activenumberBuses;
    private TransporterMessage transportermessage;
    private FilterSubcontractors filtersubc;
    private FilteringParameter filteringParameter;
  private Devices devicesmessage;
    
    @PostConstruct
    public void init() 
    {
        
    
    }
    
   
  public void   getTransporterTransactions(String transporterId)
    {
         try{
         
         
         
 String txurl ="http://41.74.172.132:8080/FomocoBusiness/transaction/oftoday";
  Response txdata = CommonLibrary.sendRESTRequest(txurl, "", MediaType.TEXT_PLAIN, "GET");
 String data = txdata.readEntity(String.class);
  JAXBContext jaxbContext = JAXBContext.newInstance(TransactionsDisplay.class);
Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
StringReader reader = new StringReader(data);
this.setTransactions((TransactionsDisplay) unmarshaller.unmarshal(reader));
     }
     catch(Exception e)
     {
         System.out.println(e.getMessage());
     }
        
    }
    
    
    

  
    /**
     * @return the transactions
     */
    public TransactionsDisplay getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(TransactionsDisplay transactions) {
        this.transactions = transactions;
    }

    /**
     * @return the buses
     */
    public Buses getBuses() {
        return buses;
    }

    /**
     * @param buses the buses to set
     */
    public void setBuses(Buses buses) {
        this.buses = buses;
    }

  
    
    public TransporterMessage getTransportermessage() {
        return transportermessage;
    }

    /**
     * @param transportermessage the transportermessage to set
     */
    public void setTransportermessage(TransporterMessage transportermessage) {
        this.transportermessage = transportermessage;
    }

    /**
     * @return the filtersubc
     */
    public FilterSubcontractors getFiltersubc() {
        return filtersubc;
    }

    /**
     * @param filtersubc the filtersubc to set
     */
    public void setFiltersubc(FilterSubcontractors filtersubc) {
        this.filtersubc = filtersubc;
    }

    /**
     * @return the filteringParameter
     */
    public FilteringParameter getFilteringParameter() {
        return filteringParameter;
    }

    /**
     * @param filteringParameter the filteringParameter to set
     */
    public void setFilteringParameter(FilteringParameter filteringParameter) {
        this.filteringParameter = filteringParameter;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the devicesmessage
     */
    public Devices getDevicesmessage() {
        return devicesmessage;
    }

    /**
     * @param devicesmessage the devicesmessage to set
     */
    public void setDevicesmessage(Devices devicesmessage) {
        this.devicesmessage = devicesmessage;
    }

    /**
     * @return the activenumberBuses
     */
    public int getActivenumberBuses() {
        return activenumberBuses;
    }

    /**
     * @param activenumberBuses the activenumberBuses to set
     */
    public void setActivenumberBuses(int activenumberBuses) {
        this.activenumberBuses = activenumberBuses;
    }

    /**
     * @return the accessResult
     */
    public AccessTokenWithMessage getAccessResult() {
        return accessResult;
    }

    /**
     * @param accessResult the accessResult to set
     */
    public void setAccessResult(AccessTokenWithMessage accessResult) {
        this.accessResult = accessResult;
    }

   

    
}
