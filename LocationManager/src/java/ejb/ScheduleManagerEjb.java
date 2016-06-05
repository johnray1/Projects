/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Schedule;
import entitiesFacade.ScheduleFacade;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class ScheduleManagerEjb {
    
    @PersistenceContext
    private EntityManager em;
     
    @EJB
    ScheduleFacade scheduleFacade;
    
    
    
    
    
    public String createSchedule(Schedule s) {
        scheduleFacade.create(s);
        return "Schedule Persisted";
    }
    
    public String editSchedule(Schedule s) {
        scheduleFacade.edit(s);
        return "Schedule Edited";
    }

    public List<Schedule> getAllSchedule() {

        List<Schedule> list = scheduleFacade.findAll();
        
        return list;
    }
    
     
  


   public void getHours(int depId,int destId){
        
        Schedule s = new Schedule();
        List<Schedule> scheduleList=em.createQuery("SELECT s FROM Schedule s WHERE s.trip.location.id = :depId "
                                                    + "AND s.trip.location1.id = :destId ")
                                                        .setParameter("depId", depId).setParameter("destId", destId).getResultList();
       
       Iterator i=scheduleList.iterator();
       while(i.hasNext()){
           s=(Schedule) i.next();
           String depName=s.getTrip().getLocation().getName();
           String destName=s.getTrip().getLocation1().getName();
           
       }
       
       
      }
}