/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.bizLogic;


import com.oltranz.usermanager.entities.Action;
import com.oltranz.usermanager.entities.ActionOnObject;
import com.oltranz.usermanager.entities.Gender;
import com.oltranz.usermanager.entities.SystemObject;
import com.oltranz.usermanager.entities.User;
import com.oltranz.usermanager.entities.UserInRole;
import com.oltranz.usermanager.entities.UserRole;
import com.oltranz.usermanager.entities.UserRoleType;
import com.oltranz.usermanager.library.Common;
import com.oltranz.usermanager.models.ResultObject;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.eclipse.persistence.config.HintValues;
//import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class InitialData {
    @PersistenceContext(unitName = "kvcsUserManagerPU")
    private  EntityManager em;
    //getEntityManager().persist(entity);
    public  List<User> findAll(){
        try{
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            return em.createQuery(cq).getResultList();
        }
        catch(Exception ex){
            String message="AAA: Find All: "+ex.getMessage()+" | TRACE :";
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            message+=errors.toString();
            out.print(message);
            return new ArrayList();
            
        }
        
    }
    
    public ResultObject Initialise(){
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(String.class);
        resultObject.setMessage("Well completed");
        
        
        if(!findAll().isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("Contains data already");
            return resultObject;
        }
        
        
        String initializationLog="Data Initialization Begins at "+ ((new Date()).toString());
        try{
            //UserRoleType(Integer id,String name, String descr)
            initializationLog+="\nUser Roles Types Initialization begins...";
            List<UserRoleType> userRoleTypeList= new ArrayList();
            userRoleTypeList.add(new UserRoleType(1,"Overall User","Over all management system users"));
            
            for(UserRoleType x: userRoleTypeList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            //String name,int type_id,String descr
            initializationLog+="\n\n Users' roles Initialization begins...";
            List<UserRole>usersRoles=new ArrayList();
            usersRoles.add(new UserRole("Administrators",1,"System Administrators"));
            usersRoles.add(new UserRole("General users",1,"General users"));
            
            for(UserRole x: usersRoles){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            //Action(Integer id, String name, String descr)
            initializationLog+="\n\n Actions Initialization begins...";
            List<Action> actionsList=new ArrayList();
            actionsList.add(new Action(1,"create","To create a new instance of an object"));
            actionsList.add(new Action(2,"Edit","To Edit and update instance of an object"));
            actionsList.add(new Action(3,"Read","To access and read few or detailed info of an object"));
            actionsList.add(new Action(4,"List","To View list of object"));
            actionsList.add(new Action(5,"delete","To delete instance of an object"));
            actionsList.add(new Action(6,"change status","To change the status of an instance of an object"));
            actionsList.add(new Action(7,"Other action","Other action very specific to the a given system object"));
            for(Action x: actionsList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            
            //SystemObject(Integer id,String name, String descr)
            initializationLog+="\n\n System Objects Initialization begins...";
            List<SystemObject> systemObjectsList=new ArrayList();
            systemObjectsList.add(new SystemObject(1,"user","user of the system"));
            systemObjectsList.add(new SystemObject(2,"user role","user role"));
            systemObjectsList.add(new SystemObject(3,"Ticket","Ticket"));
            systemObjectsList.add(new SystemObject(4,"Penalty","Penalty"));
            systemObjectsList.add(new SystemObject(5,"Contact","Contract"));
            systemObjectsList.add(new SystemObject(6,"Vehicule Profile","Vehicule Profile"));
            for(SystemObject x: systemObjectsList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //ActionOnObject(Integer id,String name, String descr)
            initializationLog+="\n\n Action on objects Initialization begins...";
            List<ActionOnObject> ActionOnObjectsList=new ArrayList();
            ActionOnObjectsList.add(new ActionOnObject(51,1,1,"create new user account"));
            ActionOnObjectsList.add(new ActionOnObject(52,1,2,"Edit user account details"));
            ActionOnObjectsList.add(new ActionOnObject(53,1,3,"Read/display user account details"));
            ActionOnObjectsList.add(new ActionOnObject(54,1,4,"View list of system users accounts"));
            ActionOnObjectsList.add(new ActionOnObject(55,1,5,"Delete user account"));
            ActionOnObjectsList.add(new ActionOnObject(56,1,6,"Disable user account"));
            ActionOnObjectsList.add(new ActionOnObject(57,1,7,"View user access permissions"));
            ActionOnObjectsList.add(new ActionOnObject(58,1,7,"Change user access permissions"));
            ActionOnObjectsList.add(new ActionOnObject(59,1,7,"add user account to user role"));
            ActionOnObjectsList.add(new ActionOnObject(60,1,7,"remove user account from user role"));
            ActionOnObjectsList.add(new ActionOnObject(61,1,1,"create new user role"));
            ActionOnObjectsList.add(new ActionOnObject(62,1,2,"Edit user role details"));
            ActionOnObjectsList.add(new ActionOnObject(63,1,3,"Read/display user role details"));
            ActionOnObjectsList.add(new ActionOnObject(64,1,4,"View list of system roles"));
            ActionOnObjectsList.add(new ActionOnObject(65,1,5,"Delete user role"));
            ActionOnObjectsList.add(new ActionOnObject(66,1,6,"Disable user role"));
            ActionOnObjectsList.add(new ActionOnObject(67,1,7,"View user role access permissions"));
            ActionOnObjectsList.add(new ActionOnObject(68,1,7,"Change user role access permissions"));
            
            
            for(ActionOnObject x: ActionOnObjectsList){
                em.persist(x);
                initializationLog+="\n "+x.getDescr() +" Added successfully";
            }
            
            
            
            //Gender(Integer id,String name,String descr)
            initializationLog+="\n\n Genders Initialization begins...";
            List<Gender> gendersList=new ArrayList();
            gendersList.add(new Gender(1,"Male","Human Male"));
            gendersList.add(new Gender(2,"Female","Human Female"));
            gendersList.add(new Gender(3,"None","System object non-human"));
            for(Gender x: gendersList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            // User(String fname,String otherNames,String email,String password,String pin,String PhoneNumber,String gender,String details)
            initializationLog+="\n\n Users Initialization begins...";
            List<User> usersList=new ArrayList();
            usersList.add(new User("System"," Administrator","admin@oltranz.com",Common.shared.get_SHA_512_SecurePassword("admin", "726"),"000","","1","ffffffffffffffffffffffffffffffff","Initial system administrator with all permissions"));
            for(User x: usersList){
                em.persist(x);
                initializationLog+="\n ID:"+ x.getEmail()+", Names:"+x.getFname() + x.getOtherNames() +" Added successfully";
                
                //UserInRole(Integer userId, Integer roleId)
                initializationLog+="\n\n Adding user ID:"+ x.getId()+", Names:"+x.getFname() + x.getOtherNames() +" to his roles  begins...";
                List<UserInRole> usersInRolesList=new ArrayList();
                usersInRolesList.add(new UserInRole(x.getId(),1));  // add to overall administrators
                usersInRolesList.add(new UserInRole(x.getId(),2));  // add to overall administrators
                usersInRolesList.forEach(y->em.persist(x));
                for(UserInRole y: usersInRolesList){
                    em.persist(y);
                    
                    initializationLog+="\n adding of user  ID:"+ x.getId()+", Names:"+x.getFname() + x.getOtherNames() +" to role"+ em.find(UserRole.class, y.getUserInRolePK().getRoleId()).getName()+" completed successfully";
                }
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
