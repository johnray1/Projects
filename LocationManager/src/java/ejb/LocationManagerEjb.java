/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Location;
import entities.LocationAliase;
import entities.Trip;
import entitiesFacade.LocationAliaseFacade;
import entitiesFacade.LocationFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Owner
 */
@Stateless
public class LocationManagerEjb {

    //Global position variable daclaration
    @PersistenceContext
    private EntityManager em;

    @EJB
    private LocationFacade locationFacade;

    @EJB
    private LocationAliaseFacade locationAliaseFacade;

    @EJB
    TripManagerEjb tripManager;
    
    @EJB
    PatternSearchLogic psl;

    


    //Create a location
    public String createLocation(Location l) {
        locationFacade.create(l);
        em.flush();
        int locId=l.getId();
        String locationName = l.getName();
        LocationAliase locAli = new LocationAliase();
        locAli.setLocAliasName(locationName);
        locAli.setLocId(l);
        locationAliaseFacade.create(locAli);

        return "Location Persisted";
    }

    //map alias
    public String createLocationAliase(LocationAliase lA) {
        locationAliaseFacade.create(lA);
        return "Aliase Persisted";
    }

    //edit location
    public boolean updateLocation(Location l) {
        try{
             int locationId=l.getId();
             String locationName=l.getName();
             Location l1=new Location();
             l1.setId(locationId);
             l1.setName(locationName);
             locationFacade.edit(l);
        
              return true;
           }
        catch(Exception e){
            return false;
           }
        
       
    }

    //edit alias
    public boolean updateLocationAliase(LocationAliase la) {
        try{
             int locationAliaseId=la.getLocAliasId();
             String locationAliaseName=la.getLocAliasName();
             LocationAliase al1=new LocationAliase();
             al1.setLocAliasId(locationAliaseId);
             al1.setLocAliasName(locationAliaseName);
             locationAliaseFacade.edit(al1);
        
              return true;
           }
        catch(Exception e){
            return false;
           }
        
    }

    //get all location list only
    public List<Location> getLocationList() {
        List<Location> locationList = locationFacade.findAll();
        return locationList;
    }

    //get a list of all location alias
    public List<LocationAliase> getLocationAliaseList() {
        List<LocationAliase> listAliase = locationAliaseFacade.findAll();
        return listAliase;
    }

    //get a list of all aliases with same location ID
    public List<LocationAliase> getAliaseByLocationId(int id) {
        Location location = new Location();
        location.setId(id);
        List<LocationAliase> listAliase = (List<LocationAliase>) em.createQuery("SELECT l FROM LocationAliase l WHERE l.locId = :locId").setParameter("locId", location).getResultList();
        return listAliase;
    }

    //get locations list by id and its alias
    public Location getLocationById(int locId) {
        Location location = locationFacade.find(locId);
        return location;
    }

    //get location aliase by its id
    public LocationAliase getLocationAliaseById(int locAlId) {
        LocationAliase locationAliase = locationAliaseFacade.find(locAlId);
        return locationAliase;
    }

    //delete location by id and its alias
    public String deleteLocation(Location l) {
        locationFacade.remove(l);
        return "Location Deleted";
    }

    //delete alias
    public String deleteLocationAliase(LocationAliase lA) {
        locationAliaseFacade.remove(lA);
        return "Aliase Deleted";
    }

    //draft
    //get all location respectively with their aliases
    public List<LocationAliase> getAll() throws Exception {
        List<LocationAliase> locationList = locationAliaseFacade.findAll();

        return locationList;
    }

    //get Location list by name
    public Location getLocByName(String locName) {
        Location location = new Location();
        List<Location> locationList = (List<Location>) em.createQuery("SELECT l FROM Location l WHERE l.name = :locName").setParameter("locName", locName).getResultList();
        //validating empty list
        if (!locationList.isEmpty()) {
            Iterator i = locationList.iterator();
            while (i.hasNext()) {
                location = (Location) i.next();
            }
        } else {
            location.setName("Location not found");
        }
        return location;
    }

    //get Location list by name
    public LocationAliase getLocAliByName(String locAliaseName) {
        LocationAliase locationAliase = new LocationAliase();
        List<LocationAliase> locationAliaseList = (List<LocationAliase>) em.createQuery("SELECT l FROM LocationAliase l WHERE l.locAliasName = :locName").setParameter("locName", locAliaseName).getResultList();
        if (!locationAliaseList.isEmpty()) {
            Iterator i = locationAliaseList.iterator();
            while (i.hasNext()) {
                locationAliase = (LocationAliase) i.next();
            }
        }
        return locationAliase;
    }

    //get Location list by name
    public List<LocationAliase> getLocAliListByName(String locAliaseName) {
        LocationAliase locationAliase = new LocationAliase();
        List<LocationAliase> locationAliaseList = (List<LocationAliase>) em.createQuery("SELECT l FROM LocationAliase l WHERE l.locAliasName = :locName").setParameter("locName", locAliaseName).getResultList();
        if (!locationAliaseList.isEmpty()) {
            Iterator i = locationAliaseList.iterator();
            while (i.hasNext()) {
                locationAliase = (LocationAliase) i.next();
            }
        } else {
            locationAliase.setLocAliasName("Location not found");
        }
        Location location = new Location();
        location = locationAliase.getLocId();
        List<LocationAliase> locAliList = (List<LocationAliase>) em.createQuery("SELECT l FROM LocationAliase l WHERE l.locId = :locId").setParameter("locId", location).getResultList();
        return locAliList;
    }

    //Get Departure
    public List<LocationAliase> getDeparture(String depName) throws Exception {
        
        Location locat = new Location();
        LocationAliase locAli = new LocationAliase();
        LocationAliase locAliData = new LocationAliase();
        List<LocationAliase> aliaseList = new ArrayList<LocationAliase>();
        
        LocationAliase loc = getLocAliByName(depName);
        if (loc.getLocAliasId() != null) {
        //getting location Id from Location Aliase of type Location
            Location locId = loc.getLocId();
            int id = locId.getId();
            List<Trip> t = tripManager.getTripByDepId(id);
            if (!t.isEmpty()) {
                Iterator itr = t.iterator();
                while (itr.hasNext()) {
                    Trip tr = (Trip) itr.next();
                    locat = tr.getLocation();
                }
                //new idea
                //locAliData.setLocId(locat);
                int idLoc = loc.getLocAliasId();
                Location idLocation = new Location();
                idLocation.setId(idLoc);
                locAli.setLocAliasId(idLoc);
                locAli.setLocAliasName(depName);
                aliaseList.add(locAli);
            } else {

                //if no departure found, select all aliase with same location Id
                List<LocationAliase> listAliase = getLocAliListByName(depName);
                Iterator i = listAliase.iterator();
                LocationAliase locAliCheck = new LocationAliase();
                Location locCheck = new Location();

                while (i.hasNext()) {
                    locAliCheck = (LocationAliase) i.next();
                    locCheck = locAliCheck.getLocId();
                    int locDep = locCheck.getId();
                    Trip tripCheck = tripManager.getTripDepId(locDep);
                    if (tripCheck.getLocation() != null) {
                        LocationAliase l = new LocationAliase();
                        Location lo = new Location();
                        lo = tripCheck.getLocation();
                        l.setLocAliasId(locAliCheck.getLocAliasId());
                        l.setLocAliasName(locAliCheck.getLocAliasName());
                        locAli.setLocAliasName(locAliCheck.getLocAliasName());
                        aliaseList.add(l);
                    } else {
                        locAliData.setLocAliasName("Departure not Found");
                        locAli.setLocAliasName("Check msg");
                        aliaseList.add(locAliData);
                    }
                }
            }

        } 
        else {
            //search pattern
            List<LocationAliase> locAliList = getAll();
            LocationAliase aliase = new LocationAliase();
            int dis, index = 0;
            double perc;
            String decis;
            Trip tr = new Trip();
            Iterator i = locAliList.iterator();
            while (i.hasNext()) {
                aliase = (LocationAliase) i.next();

                //distance between input and others
                dis = psl.distance(depName, aliase.getLocAliasName());
                perc = psl.percentage(dis, depName, aliase.getLocAliasName());
                decis = psl.decision(perc, aliase.getLocAliasName());

                //check the decision made
                if (!decis.isEmpty()) {
                    LocationAliase locAliDecis = getLocAliByName(decis);
                    Location locDecis = locAliDecis.getLocId();
                    int locAliDecisId = locDecis.getId();
                    Trip tList = tripManager.getTripDepId(locAliDecisId);
                    if (tList != null) {
                        locat = tList.getLocation();
                        locAliData.setLocId(locat);
                        locAli.setLocAliasName(locAliDecis.getLocAliasName());

                        Location idLocation = new Location();
                        idLocation = locAliDecis.getLocId();

                        LocationAliase dataAliase = new LocationAliase();
                        dataAliase.setLocAliasId(locAliDecis.getLocAliasId());
                        dataAliase.setLocAliasName(locAliDecis.getLocAliasName());

                        aliaseList.add(dataAliase);
                    }
                }
            }
        }//matching algorthime end

        if (locAli.getLocAliasName() == null) {
            locAliData.setLocAliasName("Location not Found");
            aliaseList.add(locAliData);
        }

        return aliaseList;
    }

    //Get Destination
    public List<LocationAliase> getDestination(int depIdIn, String destName) throws Exception {
        
        //get Location Id 
        Location loc = new Location();
        loc = (Location) em.createQuery("SELECT l FROM Location l WHERE l.id = :lId").setParameter("lId", depIdIn).getSingleResult();
        int depId = loc.getId();
        
       //Locaion Id Found

        LocationAliase locationAliase = getLocAliByName(destName);
        List<LocationAliase> aliaseList = new ArrayList<>();
        LocationAliase locAliData = new LocationAliase();
        Location locDest = new Location();
        Location locDep = new Location();

        if (locationAliase.getLocAliasId() != null) {
            Location locId = locationAliase.getLocId();
            if (locId.getId() == null) {
                LocationAliase l = new LocationAliase();
                l.setLocAliasName("No such Trip Found");
                aliaseList.add(l);
            }
            List<LocationAliase> listOfAliase = (List<LocationAliase>) em.createQuery("SELECT l FROM LocationAliase l WHERE l.locId = :locId").setParameter("locId", locId).getResultList();
            Iterator i = listOfAliase.iterator();
            while (i.hasNext()) {

                locAliData = (LocationAliase) i.next();
                locDest = locAliData.getLocId();
                //String destNm = locAliData.getLocAliasName();
                locDep.setId(depId);

                //search for a valid dep and destination in trip
                Trip t = tripManager.getTripByDepDestId(locDep, locDest);
                if (t.getLocation().getId() != null) {
                    LocationAliase l = new LocationAliase();
                    l.setLocAliasId(locAliData.getLocAliasId());
                    l.setLocAliasName(locAliData.getLocAliasName());
                    aliaseList.add(l);
                } else {
                    LocationAliase l = new LocationAliase();
                    l.setLocAliasName("No such Trip Found");
                    aliaseList.add(l);
                }
            }
            if (aliaseList.isEmpty()) {
                LocationAliase l = new LocationAliase();
                l.setLocAliasName("No such Trip Found");
                aliaseList.add(l);
            }
        } 
        else {
            
            //Destination Is not found into location aliase
            //Select all aliase with same loc id and check the valid trip for them

            LocationAliase locAli = new LocationAliase();
            List<LocationAliase> listAliase = getLocAliListByName(destName);
            Iterator i = listAliase.iterator();
            LocationAliase locAliCheck = new LocationAliase();

            while (i.hasNext()) {
                locAliCheck = (LocationAliase) i.next();
                locDest = locAliCheck.getLocId();
                locDep.setId(depId);

                Trip tripCheck = tripManager.getTripByDepDestId(locDep, locDest);
                if (tripCheck.getLocation().getId() != null) {
                    LocationAliase l = new LocationAliase();
                    Location lo = new Location();
                    lo = tripCheck.getLocation();
                    l.setLocAliasId(locAliCheck.getLocAliasId());
                    l.setLocAliasName(locAliCheck.getLocAliasName());
                    locAli.setLocAliasName(locAliCheck.getLocAliasName());
                    aliaseList.add(l);
                }
            } //idea end
        }

        if (aliaseList.isEmpty()) {
            //Search Pattern 

            List<LocationAliase> locAliList = getAll();
            LocationAliase aliase = new LocationAliase();
            Location locat = new Location();
            int dis, index = 0;
            double perc;
            String decis;
            Trip tr = new Trip();
            Iterator i = locAliList.iterator();
            while (i.hasNext()) {
                aliase = (LocationAliase) i.next();

                //distance between input and others
                dis = psl.distance(destName, aliase.getLocAliasName());
                perc = psl.percentage(dis, destName, aliase.getLocAliasName());
                decis = psl.decision(perc, aliase.getLocAliasName());

                //check the decision made
                if (!decis.isEmpty()) {
                    LocationAliase locAliDecis = getLocAliByName(decis);
                    Location locDecis = locAliDecis.getLocId();
                    int locAliDecisId = locDecis.getId();
                    locDep.setId(depId);
                    locDest.setId(locAliDecisId);
                    Trip tList = tripManager.getTripByDepDestId(locDep, locDest);
                    if (tList.getLocation().getId() != null) {
                        locat = tList.getLocation();
                        locAliData.setLocId(locat);
                        LocationAliase locAli = new LocationAliase();
                        locAli.setLocAliasId(locAliDecis.getLocAliasId());
                        locAli.setLocAliasName(locAliDecis.getLocAliasName());

                        aliaseList.add(locAli);
                    }
                }
            } //Search End  
        }
        if (aliaseList.isEmpty()) {
            LocationAliase locAli = new LocationAliase();
            locAli.setLocAliasName("No Destination Found");
            aliaseList.add(locAli);
        }
        return aliaseList;
    }
 
    //Get Siblings
    public List<LocationAliase> getLocationSibling(String input) throws Exception{
       
       List<LocationAliase> aliaseList = new ArrayList<LocationAliase>(); 
       
       LocationAliase la=(LocationAliase) em.createNamedQuery("LocationAliase.findByLocAliasName").setParameter("locAliasName", input).getSingleResult();
       int locId=la.getLocId().getId();
       
       
       List<LocationAliase> lal=em.createQuery("SELECT l FROM LocationAliase l WHERE l.locId.id = :locId").setParameter("locId",locId).getResultList();
        
         Iterator i=lal.iterator();
          while(i.hasNext()){
              LocationAliase la2=new LocationAliase();
              la2=(LocationAliase) i.next();
              String s=la2.getLocAliasName();
              LocationAliase locAli = new LocationAliase();
              locAli.setLocAliasName(s);
              aliaseList.add(locAli);
        }
           
           return aliaseList;

    }

    //Get CombinationList
    public List<LocationAliase> getCombinationList(int depId,int destId) throws Exception{
        
        LocationAliase depla=(LocationAliase) em.createNamedQuery("LocationAliase.findByLocAliasId").setParameter("locAliasId",depId).getSingleResult();
        String depName=depla.getLocAliasName();
        List<LocationAliase> depSiblingList = getLocationSibling(depName);
        
        
        LocationAliase destla=(LocationAliase) em.createNamedQuery("LocationAliase.findByLocAliasId").setParameter("locAliasId",depId).getSingleResult();
        String destName=destla.getLocAliasName();
        List<LocationAliase> destSiblingList = getLocationSibling(destName);
        
        
        List<LocationAliase> combinations = new ArrayList<LocationAliase>();
        return null;

        

    }
    


}
