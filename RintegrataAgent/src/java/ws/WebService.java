/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.Purchase;
import beans.Reserve;
import beans.Schedule;
import beans.Trips;
import ejb.RintegrataData;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
@Path("rintegratadata")
@Stateless
public class WebService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    RintegrataData rt;
    
    //Trips from Service provider search
    @POST
    @Path("rintegratatrips")
    @Produces(MediaType.APPLICATION_XML)
    public String getTripsFromRentegrata(InputStream is) throws Exception {
        
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
            JAXBContext context = JAXBContext.newInstance(Trips.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Trips> je = um.unmarshal(sr, Trips.class);
            sr.close();
            Trips trips = je.getValue();
            String msg=rt.rintegrataTrips(trips);
            return msg;

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    
    //Schedules from Service provider search
    @POST
    @Path("rintegrataschedules")
    @Produces(MediaType.APPLICATION_XML)
    public String getSchedulesFromRentegrata(InputStream is) throws Exception {
        
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
            JAXBContext context = JAXBContext.newInstance(Schedule.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Schedule> je = um.unmarshal(sr, Schedule.class);
            sr.close();
            Schedule schedule = je.getValue();
            String msg=rt.getRintegrataSchedules(schedule);
            return msg;

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    
    //Reserve a ticket of rintegrata
    @POST
    @Path("rintegratareserve")
    @Produces(MediaType.APPLICATION_XML)
    public String reserveTicketFromRentegrata(InputStream is) throws Exception {
        //InputStream is = new ByteArrayInputStream(reserveData.getBytes(StandardCharsets.UTF_8));
        //forming an trip object from url data
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
            JAXBContext context = JAXBContext.newInstance(Reserve.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Reserve> je = um.unmarshal(sr, Reserve.class);
            sr.close();
            Reserve reserve = je.getValue();
            String msg=rt.reserveRintegrataTicket(reserve);
            return msg;

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    
    //Purchase Reserved ticket of rintegrata
    @POST
    @Path("purchaserintegratareserved")
    @Produces(MediaType.APPLICATION_XML)
    public String purchaseReservedTicketFromRentegrata(InputStream is) throws Exception {
       //InputStream is = new ByteArrayInputStream(purchaseData.getBytes(StandardCharsets.UTF_8));
        //forming an trip object from url data
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
            JAXBContext context = JAXBContext.newInstance(Purchase.class);
            Unmarshaller um = context.createUnmarshaller();
            JAXBElement<Purchase> je = um.unmarshal(sr, Purchase.class);
            sr.close();
            Purchase purchase = je.getValue();
            String msg=rt.purchaseReservedRintegrataTicket(purchase);
            return msg;

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
}
