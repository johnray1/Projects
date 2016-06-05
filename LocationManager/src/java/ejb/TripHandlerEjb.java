/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.RTrip;
import beans.RTrips;
import entities.Location;
import entities.LocationAliase;
import entities.Route;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class TripHandlerEjb {
    
    @PersistenceContext
    private EntityManager em;
    
    int departId;
    int destId;
    int routeId;
    int ticketPrice;
    
    public String sendXML(String url,String xmlData){
        try
        {
            URL oURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
              con.setRequestMethod("POST");
              con.setRequestProperty("Content-type", "Application/xml; charset=utf-8");
              con.setDoOutput(true);
              con.setDoInput(true);
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
              wr.writeBytes(xmlData);
	      wr.flush();
	      wr.close();
            System.out.println("Data to post :"+xmlData);
            
            BufferedReader in1= new BufferedReader(new InputStreamReader(con.getInputStream()));
	    
            String inputLine;
	    StringBuffer response = new StringBuffer();
 
            while ((inputLine = in1.readLine()) != null) 
                {
			response.append(inputLine);
		}
            in1.close();
            con.disconnect(); 
            String xmldata=response.toString();
                
            return xmldata; 
        }
        
        catch(Exception e)
        {
            System.out.println("Error in the posting: "+e.getMessage());
            return null;
        }
     
    
}
    
    public boolean persistTripData(RTrips rts)throws Exception{
        for (RTrip rt : rts.getTrips()){
            
            String depart=rt.getDepart();
            String dest=rt.getDest();
            String route=rt.getRoute();
            int price=rt.getPrice();
          
            departId=isLocationAvailable(depart);
            destId=isLocationAvailable(dest);
            routeId=isRouteAvailable(route);
//            ticketPrice=isPriceAvailable(price,departId,destId,routeId);
            
         }
        if(departId<=0 && destId<=0 && routeId<=0){
            return false;
        }
        else{
            return true;
        }
        
    }
    
    public int isLocationAvailable(String locName)throws Exception{
        List<Location> ll=(List<Location>)em.createNamedQuery("Location.findByName").setParameter("name", locName).getResultList();
        Iterator i=ll.iterator();
        Location l=new Location();
        while(i.hasNext()){
            l=(Location) i.next();
        }
        if(l.getName()==null){
            l.setName(locName);
            em.persist(l);
            em.flush();
            int locId=l.getId();
            String lName=l.getName();
            LocationAliase la=new LocationAliase();
            la.setLocId(l);
            la.setLocAliasName(locName);
            em.persist(la);
            return locId;
        }
        else{
            return 0; 
        }
    }
    
    public int isRouteAvailable(String routeName)throws Exception{
        List<Route> rl=(List<Route>)em.createNamedQuery("Route.findByName").setParameter("name", routeName).getResultList();
        Iterator i=rl.iterator();
        Route r=new Route();
        while(i.hasNext()){
            r=(Route) i.next();
        }
        if(r.getName()==null){
            r.setName(routeName);
            em.persist(r);
            em.flush();
            int routeId=r.getId();
            return routeId;
        }
        else{
            return 0; 
        }
    
    }
    
//    public int isPriceAvailable(int price,int departId,int destId,int routeId)throws Exception{
//        List<TripTransporter> ttl=(List<TripTransporter>)em.createNamedQuery("TripTransporter.findByPrice").setParameter("price", price).getResultList();
//        Iterator i=ttl.iterator();
//        TripTransporter tt=new TripTransporter();
//        while(i.hasNext()){
//            tt=(TripTransporter) i.next();
//        }
//        if(tt.getPrice()==null){
//            TripTransporterPK tpk=new TripTransporterPK();
//            tpk.setTripdepartLocId(departId);
//            tpk.setTripdestLocId(destId);
//            tpk.setTriprouteId(routeId);
//            tt.setPrice(price);
//            tt.setTripTransporterPK(tpk);
//            em.persist(tt);
//            return 1;
//        }
//        else{
//            return 0; 
//        }
//    
//    }
    
    
    
}
