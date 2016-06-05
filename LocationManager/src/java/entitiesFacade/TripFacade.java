/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesFacade;

import entities.Trip;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class TripFacade extends AbstractFacade<Trip> {
    @PersistenceContext(unitName = "LocationManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TripFacade() {
        super(Trip.class);
    }
    
}
