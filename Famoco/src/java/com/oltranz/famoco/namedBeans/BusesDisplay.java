/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.namedBeans;

import com.oltranz.deserializejson.DeserializationofObjects;
import com.oltranz.famoco.beans.Bus;
import com.oltranz.famoco.beans.Buses;
import com.oltranz.famoco.beans.DashboardBuses;
import com.oltranz.famoco.beans.DashboardContractors;
import com.oltranz.famoco.beans.FilteringBuses;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import utils.CommonLibrary;

/**
 *
 * @author ismaelnzamutuma
 */
@ManagedBean(name ="busesDisplay")
public class BusesDisplay {
    private Buses buses = new Buses();
    private FilteringBuses filteringBuses;
    private Bus bus ;
    private DashboardBuses dashboardBuses;
    private DashboardContractors dashboardContractors;
    public void Authenticate(String authenticteData)
    {
        
    }
    @PostConstruct
    public void init()
    {
      
    }

    public void listBuses(String transporterId)
    {
          try{
        String url ="http://41.74.172.132:8080/IntercityTransport/BusesManagementServices/buses/transporter/"+transporterId;
        Response response = CommonLibrary.sendRESTRequest(url, "", MediaType.TEXT_PLAIN, "GET");
 String data = response.readEntity(String.class);
DeserializationofObjects dsob = new DeserializationofObjects();

this.setBuses(dsob.deserializeBuses(data));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

 
    }
            
   public void busesSubcontractor(ValueChangeEvent event)
    {
        if(event.getNewValue()!=null)
        {
        try
        {
        System.out.println(event.getNewValue());
        String urlbuses = "http://41.74.172.132:8080/IntercityTransport/BusesManagementServices/buses/transporter/"+event.getNewValue();
      String  busesjson = CommonLibrary.sendRESTRequest(urlbuses, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
       ObjectMapper mapper = new ObjectMapper();
      this.setBuses(new DeserializationofObjects().deserializeBuses(busesjson));
      System.out.println(busesjson);
     List list = new ArrayList();
      for(Bus bus:this.getBuses().getBuses())
      {
         list.add(bus);
          
      }
      FilteringBuses filb = new FilteringBuses();
      filb.setBuses(list);
      this.setFilteringBuses(filb);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        }
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

    /**
     * @return the filteringBuses
     */
    public FilteringBuses getFilteringBuses() {
        return filteringBuses;
    }

    /**
     * @param filteringBuses the filteringBuses to set
     */
    public void setFilteringBuses(FilteringBuses filteringBuses) {
        this.filteringBuses = filteringBuses;
    }

    /**
     * @return the bus
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * @param bus the bus to set
     */
    public void setBus(Bus bus) {
        this.bus = bus;
    }

    /**
     * @return the dashboardBuses
     */
    public DashboardBuses getDashboardBuses() {
        return dashboardBuses;
    }

    /**
     * @param dashboardBuses the dashboardBuses to set
     */
    public void setDashboardBuses(DashboardBuses dashboardBuses) {
        this.dashboardBuses = dashboardBuses;
    }

    /**
     * @return the dashboardContractors
     */
    public DashboardContractors getDashboardContractors() {
        return dashboardContractors;
    }

    /**
     * @param dashboardContractors the dashboardContractors to set
     */
    public void setDashboardContractors(DashboardContractors dashboardContractors) {
        this.dashboardContractors = dashboardContractors;
    }
    
}
