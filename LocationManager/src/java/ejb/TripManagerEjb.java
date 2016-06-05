/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Location;
import entities.Trip;
import entitiesFacade.LocationAliaseFacade;
import entitiesFacade.LocationFacade;
import entitiesFacade.TripFacade;
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
public class TripManagerEjb {

    //Global position variable declaration
    @PersistenceContext
    private EntityManager em;

    @EJB
    TripFacade tripFacade;

    @EJB
    private LocationFacade locationFacade;

    @EJB
    private LocationAliaseFacade locationAliaseFacade;

    //create a trip
    public String createTrip(Trip t) {
        tripFacade.create(t);
        return "Trip Persisted";
    }

    //Edit Trip
    public String editTrip(Trip t) {
        tripFacade.edit(t);
        return "Trip Edited";
    }

    //get all trip
    public List<Trip> getAllTrip() {
        List<Trip> tripList = tripFacade.findAll();
        return tripList;
    }

    //Get trip by id
    public Trip getTripById(int tripId) {
        Trip t = tripFacade.find(tripId);
        return t;
    }

    //Get Trip by Departure Id
    public List<Trip> getTripByDepId(int depId) {
        Location location = new Location();
        location.setId(depId);
        List<Trip> tripList = (List<Trip>) em.createQuery("SELECT t FROM Trip t WHERE t.location = :depId").setParameter("depId", location).getResultList();
        return tripList;
    }

    //Get Trip by Destination Id
    public List<Trip> getTripByDestId(int destId) {
        Location location = new Location();
        location.setId(destId);
        List<Trip> tripList = (List<Trip>) em.createQuery("SELECT t FROM Trip t WHERE t.location1 = :destId").setParameter("destId", location).getResultList();
        return tripList;
    }

   
    //Get trip by depart and dest
    public List<Trip> getTripByDepDest(Location l, Location loc) {
        List<Trip> tripList = (List<Trip>) em.createQuery("SELECT t FROM Trip t WHERE t.location = :depId AND t.location1 = :destId").setParameter("depId", l).setParameter("destId", loc).getResultList();
        return tripList;
    }

    //Get Trip by Departure Id
    public Trip getTripDepId(int depId) {
        Location location = new Location();
        Trip trip = new Trip();
        location.setId(depId);
        List<Trip> tripList = (List<Trip>) em.createQuery("SELECT t FROM Trip t WHERE t.location = :depId").setParameter("depId", location).getResultList();
        Iterator i = tripList.iterator();
        while (i.hasNext()) {
            trip = (Trip) i.next();
        }
        return trip;
    }

    //Get trip by depart and dest
    public Trip getTripByDepDestId(Location l, Location loc) {
        Trip trip = new Trip();
        List<Trip> tripList = (List<Trip>) em.createQuery("SELECT t FROM Trip t WHERE t.location = :depId AND t.location1 = :destId").setParameter("depId", l).setParameter("destId", loc).getResultList();
     
            Iterator i = tripList.iterator();
            while (i.hasNext()) {
                trip = (Trip) i.next();
            }
       
        return trip;
    }

    

}
