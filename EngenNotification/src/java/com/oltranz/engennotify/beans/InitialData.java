/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engennotify.beans;



import com.oltranz.engennotify.entities.NotificationDestinationType;
import com.oltranz.engennotify.entities.NotificationType;
import com.oltranz.engennotify.models.ResultObject;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class InitialData {
    
    @PersistenceContext
    private  EntityManager em;
    
   
    
    public ResultObject Initialise(){
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(String.class);
        resultObject.setMessage("Well completed");
        
        
        
        
        String initializationLog="Data Initialization Begins at "+ ((new Date()).toString());
        try{
            
            
            //NotificationType(Integer id,String name, String descr)
            initializationLog+="\n\n Notification Type Initialization begins...";
            List<NotificationType> notificationTypeList=new ArrayList();
            notificationTypeList.add(new NotificationType(1,"DIP","notification at late dipping"));
            notificationTypeList.add(new NotificationType(2,"TANK","notification at low tank quantity"));
            notificationTypeList.add(new NotificationType(3,"IDLE","notification at idle state"));
            notificationTypeList.add(new NotificationType(4,"TRANSACTION","notification at bizzare transaction"));
            
            for(NotificationType x: notificationTypeList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            //NotificationDestinationType(Integer id,String name, String descr)
            initializationLog+="\n\n Notification Destination Type  Initialization begins...";
            List<NotificationDestinationType> notificationDestinationTypeList=new ArrayList();
            notificationDestinationTypeList.add(new NotificationDestinationType(1,"SMS","Phone number destination to receive notification SMS"));
            notificationDestinationTypeList.add(new NotificationDestinationType(2,"EMAIL","Email destination to receive notification EMAIL"));
            
            for(NotificationDestinationType x: notificationDestinationTypeList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            
            initializationLog+="\n\n Data Initialization completed successfully at "+ ((new Date()).toString());
            resultObject.setObject(initializationLog);
            return resultObject;
            
        }
        catch(Exception ex){
            
            String message="AAA: Find All: "+ex.getMessage()+" | TRACE :";
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            message+=errors.toString();
            out.print(message);
            
            initializationLog+="\n\n An error Occured: "+message;
            resultObject.setObject(initializationLog);
            return resultObject;
        }
        
    }
    
}
