/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.RSchedules;
import beans.RTrips;
import ejb.ScheduleHandlerEjb;
import ejb.TripHandlerEjb;
import java.io.StringReader;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author JOHN
 */
@Stateless
@Path("rintegratadata")
public class RintegrataService {
    
    @EJB
    TripHandlerEjb tripHandlerEjb;
    
    @EJB
    ScheduleHandlerEjb scheduleHandlerEjb;
    
    @GET
    @Path("getRintegrataTrips")
    @Produces(MediaType.APPLICATION_XML)
    public boolean getRintegrataTrips()throws Exception{
         
         String url="http://localhost:8080/RintegrataAgent/rintegratadata/rintegratatrips";
         String xml="<trip>"
                        + "<company>select</company>"
                        + "<id>123</id>"
                 + "</trip>";
         
         String xmldata=tripHandlerEjb.sendXML(url,xml);
         JAXBContext jaxbContext = JAXBContext.newInstance(RTrips.class);
         Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
         StringReader reader = new StringReader(xmldata);
         RTrips rtrips = (RTrips) unmarshaller.unmarshal(reader);
         boolean b=tripHandlerEjb.persistTripData(rtrips);
         return b;
    }
    
    
    @GET
    @Path("getRintegrataSchedules")
    @Produces(MediaType.APPLICATION_XML)
    public boolean getRSchedules()throws Exception{
         
         String url="http://localhost:8080/RintegrataAgent/rintegratadata/rintegrataschedules";
         String xml="<schedule>"
                           + "<company>select</company>"
                           + "<id>123</id>"
                           + "<depart>NYABUGOGO</depart>"
                           + "<dest>BATIMA</dest>"
                           + "<route>NYABUGOGO-BATIMA</route>"
                           + "<day>2015-11-12</day>"
                 + "</schedule>";
         
         String xmldata=scheduleHandlerEjb.sendXML(url,xml);
         JAXBContext jaxbContext = JAXBContext.newInstance(RSchedules.class);
         Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
         StringReader reader = new StringReader(xmldata);
         RSchedules rschedules = (RSchedules) unmarshaller.unmarshal(reader);
         boolean b=scheduleHandlerEjb.persistScheduleData(rschedules);
         return b;
    }
    
   
            
    
        
        
}
