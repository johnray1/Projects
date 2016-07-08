/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.deserializejson;

import com.oltranz.famoco.beans.Buses;
import com.oltranz.famoco.beans.Devices;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ismaelnzamutuma
 */
public class DeserializationofObjects {
    ObjectMapper mapper ;
    public Buses deserializeBuses(String busesJson) 
    {
        try
        {
       
       mapper = new ObjectMapper();
      return (Buses)mapper.readValue(busesJson, Buses.class);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
    }   
    
    public Devices deserializeDevices(String devicesJson)
    {
         try
        {
        
       mapper = new ObjectMapper();
      return (Devices)mapper.readValue(devicesJson, Devices.class);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
