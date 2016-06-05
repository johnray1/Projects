/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitiesFacade.LocationAliaseFacade;
import entitiesFacade.LocationFacade;
import beans.RTrip;
import beans.RTrips;
import entities.Location;
import entities.LocationAliase;
import entities.Route;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JOHN
 */
@Stateless
public class CreateLocation {
    //Global position variable daclaration
    @PersistenceContext(unitName = "LocationManagerPU")
    private EntityManager em;

    @EJB
    private LocationFacade locationFacade;

    @EJB
    private LocationAliaseFacade locationAliaseFacade;
    
    @EJB
    private RTrips rTrips;
    
    @EJB
    private RTrip rTrip;
    
    //String depart="";
    String msg="Sart:\n";
    
    public String persistLocation(RTrips rTps)throws Exception{
        for (RTrip rt : rTps.getTrips()){
              
              String depart=rt.getDepart();
              String dest=rt.getDest();
              int price=rt.getPrice();
              String route=rt.getRoute();
              
              int checkLocDep=isLocationAvailable(depart);
              
              if(checkLocDep<=0){
                  msg+=persistLoc(depart)+"\n";
              }else{
              msg+=depart+": Available"+"\n";
              }
                          
              
              int checkLocDest=isLocationAvailable(dest);
              
              if(checkLocDest<=0){
                  msg+=persistLoc(dest)+"\n";
              }else{
              msg+=dest+": Available"+"\n";
              }
              
              int checkRoute=isRouteAvailable(route);
              
              if(checkRoute<=0){
                  msg+=persistRoute(route)+"\n";
              }else{
              msg+=route+": Available"+"\n";
              }
              
              
              
        }
       return msg;
    }
    
    public int isLocationAvailable(String locNm)throws Exception{
        Query q=em.createNamedQuery("Location.findByName");
        q.setParameter("name", locNm);
        List<Location> locList=(List<Location>) q.getResultList();
        Iterator i=locList.iterator();
        Location l=new Location();
        while(i.hasNext()){
            l=(Location) i.next();
            
        }
        if(l.getName()==null){
            return locList.size();
        }
        return locList.size();
    }
    
    
    public int isRouteAvailable(String routeNm)throws Exception{
        
        Query q=em.createNamedQuery("Route.findByName");
        q.setParameter("name", routeNm);
        List<Route> routeList=(List<Route>) q.getResultList();
        Iterator i=routeList.iterator();
        Route r=new Route();
        while(i.hasNext()){
            r=(Route) i.next();
            
        }
        if(r.getName()==null){
            return routeList.size();
        }
        return routeList.size();
    }
    
    
    
    public String persistLoc(String locNm)throws Exception{
        
        Location loc=new Location();
        loc.setName(locNm);
        loc.setDescr("");
        em.persist(loc);
        em.flush();
        int locId=loc.getId();
        String locName=loc.getName();
        LocationAliase locationAliase=new LocationAliase();
        locationAliase.setLocAliasName(locName);
        locationAliase.setLocId(loc);
        locationAliaseFacade.create(locationAliase);
        return locNm+": Persisted";
    }
    
    public String persistRoute(String routeNm)throws Exception{
        
        Route r=new Route();
        r.setName(routeNm);
        em.persist(r);
        return routeNm+": Persisted";
    }
}
