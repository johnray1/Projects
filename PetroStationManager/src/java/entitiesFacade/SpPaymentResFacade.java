/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiesFacade;

import entities.SpPaymentRes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class SpPaymentResFacade extends AbstractFacade<SpPaymentRes> {
    @PersistenceContext(unitName = "PetroStationManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpPaymentResFacade() {
        super(SpPaymentRes.class);
    }
    
}
