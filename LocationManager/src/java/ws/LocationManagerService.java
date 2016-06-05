/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import ejb.LocationManagerEjb;
import ejb.TripManagerEjb;
import entities.Location;
import entities.LocationAliase;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;



/**
 *
 * @author Owner
 */
@Path("locationmanager")
@Stateless
public class LocationManagerService {

    
    @EJB
    private LocationManagerEjb locationManager;

    @EJB
    private TripManagerEjb tripManager;

    
    @POST
    @Path("createlocation")
    public void createLocation(InputStream is) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = sb.toString();
            XMLInputFactory fc = XMLInputFactory.newFactory();
            StreamSource xml = new StreamSource(new StringReader(xmldata));
            XMLStreamReader sr = fc.createXMLStreamReader(xml);
            JAXBContext context = JAXBContext.newInstance(Location.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Location> je = um.unmarshal(sr, Location.class);
            sr.close();
            Location l;
            l = je.getValue();
            locationManager.createLocation(l);

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            System.out.println(errorMessage);

        }

    }

    
    @POST
    @Path("createlocationaliase")
    public void createLocationAliase(InputStream is) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = sb.toString();
            XMLInputFactory fc = XMLInputFactory.newFactory();
            StreamSource xml = new StreamSource(new StringReader(xmldata));
            XMLStreamReader sr = fc.createXMLStreamReader(xml);
            JAXBContext context = JAXBContext.newInstance(LocationAliase.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<LocationAliase> je = um.unmarshal(sr, LocationAliase.class);
            sr.close();
            LocationAliase sl = new LocationAliase();
            sl = je.getValue();
            locationManager.createLocationAliase(sl);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    
    @POST
    @Path("edit.location")
    public void updateLocation(InputStream is) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = sb.toString();
            XMLInputFactory fc = XMLInputFactory.newFactory();
            StreamSource xml = new StreamSource(new StringReader(xmldata));
            XMLStreamReader sr = fc.createXMLStreamReader(xml);
            JAXBContext context = JAXBContext.newInstance(Location.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Location> je = um.unmarshal(sr, Location.class);
            sr.close();
            Location l = new Location();
            l = je.getValue();
            locationManager.updateLocation(l);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    
    @POST
    @Path("edit.locationaliase")
    public void updateLocationAliase(InputStream is) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = sb.toString();
            XMLInputFactory fc = XMLInputFactory.newFactory();
            StreamSource xml = new StreamSource(new StringReader(xmldata));
            XMLStreamReader sr = fc.createXMLStreamReader(xml);
            JAXBContext context = JAXBContext.newInstance(LocationAliase.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<LocationAliase> je = um.unmarshal(sr, LocationAliase.class);
            sr.close();
            LocationAliase sl = new LocationAliase();
            sl = je.getValue();
            locationManager.updateLocationAliase(sl);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    
    
    @GET
    @Path("getDeparture/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<LocationAliase> getDeparture(@PathParam("key") String depName) throws Exception {
        
        List<LocationAliase> location = locationManager.getDeparture(depName);
        return location;
    }
    
    
   
    @GET
    @Path("getDestination/{key1}/{key2}")
    @Produces(MediaType.APPLICATION_XML)
    public List<LocationAliase> getDestination(@PathParam("key1") int depId, @PathParam("key2")String destination) throws Exception {
        
        List<LocationAliase> locationList = locationManager.getDestination(depId, destination);
        return locationList;
    }
    
    
    @GET
    @Path("getsibling/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<LocationAliase> getLocationSibling(@PathParam("key") String input) throws Exception {
        
        List<LocationAliase> locationList = locationManager.getLocationSibling(input);
        
        return locationList;
    }
    
    
    @GET
    @Path("getcombination/{key}/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<LocationAliase> getCombinationList(@PathParam("key") int depId, @PathParam("key") int destId ) throws Exception {
        
        List<LocationAliase> locationList = locationManager.getCombinationList(depId, destId);
        
        return locationList;
    }
    
}
