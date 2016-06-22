/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Action;
import com.oltranz.payfuel.entities.CustomerType;
import com.oltranz.payfuel.entities.Gender;
import com.oltranz.payfuel.entities.PaymentMode;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleForUser;
import com.oltranz.payfuel.entities.RoleType;
import com.oltranz.payfuel.entities.SystemObject;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.library.Common;
import com.oltranz.payfuel.models.ResultObject;
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
            
            
            //SystemObject(Integer id,String name, String descr)
            initializationLog+="\n\n System Objects Initialization begins...";
            List<SystemObject> systemObjectsList=new ArrayList();
            systemObjectsList.add(new SystemObject(1,"user","user of the system"));
            systemObjectsList.add(new SystemObject(2,"role","role"));
            systemObjectsList.add(new SystemObject(3,"branch","branch"));
            
            for(SystemObject x: systemObjectsList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //PaymentMode(Integer id,String name, String descr)
            initializationLog+="\n\n PaymentMode Initialization begins...";
            List<PaymentMode> paymentModesList=new ArrayList();
            paymentModesList.add(new PaymentMode(1,"CASH","Physical Money"));
            paymentModesList.add(new PaymentMode(2,"VOUCHER","Money In The Form Of Voucher/Bon"));
            paymentModesList.add(new PaymentMode(3,"MTN","Mobile Money"));
            paymentModesList.add(new PaymentMode(4,"TIGO","Tigo Cash"));
            paymentModesList.add(new PaymentMode(5,"AIRTEL","Airtel Money"));
            paymentModesList.add(new PaymentMode(6,"VISA","Visa prepaid/credit card"));
            paymentModesList.add(new PaymentMode(7,"MASTER","Master prepaid/credit card"));
            paymentModesList.add(new PaymentMode(8,"DEBT","DEPT FOR POSTPAYMENT"));
            paymentModesList.add(new PaymentMode(9,"SP CARD","SP FIDELITY card"));
            
            for(PaymentMode x: paymentModesList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //Action(Integer id, String name, String descr)
            initializationLog+="\n\n Actions Initialization begins...";
            List<Action> actionsList=new ArrayList();
            actionsList.add(new Action(1,"Create","To create a new instance of an object"));
            actionsList.add(new Action(2,"Read","To access and read few or detailed info of an object"));
            actionsList.add(new Action(3,"Update","To Edit and Update instance of an object"));
            actionsList.add(new Action(4,"Delete","To delete instance of an object"));
            actionsList.add(new Action(5,"List","To View list of object"));
            actionsList.add(new Action(6,"Change Status","To change the status of an instance of an object"));
            actionsList.add(new Action(7,"Pos Login","To User Pos Login"));
            actionsList.add(new Action(8,"Pos Logout","To User Pos Logout"));
            actionsList.add(new Action(9,"Web Login","To User Web Login"));
            actionsList.add(new Action(10,"Web Logout","To User Web Logout"));
            for(Action x: actionsList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
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
            
            //CustomerType(Integer id,String name,String descr)
            initializationLog+="\n\n CustomerType Initialization begins...";
            List<CustomerType> customerTypeList=new ArrayList();
            customerTypeList.add(new CustomerType(1,"Company Customer","Customer Associated To Company"));
            customerTypeList.add(new CustomerType(2,"Normal Customer","Normal Day To Day Customer"));
            
            for(CustomerType x: customerTypeList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            
            
            //RoleType(Integer id,String name, String descr)
            initializationLog+="\n Roles Types Initialization begins...";
            List<RoleType> roleTypeList= new ArrayList();
            roleTypeList.add(new RoleType(1,"Overall User","Over all management system users"));
            roleTypeList.add(new RoleType(2,"Branches Staff","Branches staff, roles here have their Ids in the userRoleForBranche "));
            
            for(RoleType x: roleTypeList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            
            
            
            //String name,int typeId,String descr
            initializationLog+="\n\n Roles Initialization begins...";
            em.persist(new Role("Overall Administrators",1,"Overall System Administrators"));
            em.flush();
            em.persist(new Role("Overall General users",1,"Overall General users"));
            em.flush();
            em.persist(new Role("Branch's Administrators",2,"Branch's Administrators"));
            em.flush();
            em.persist(new Role("Branch's General users",2,"Branch's General users"));
            em.flush();
            
            initializationLog+="\n "+" Added successfully";
        
           //String name,int type_id,String descr
//            initializationLog+="\n\n Users' roles Initialization begins...";
//            List<Role> roles=new ArrayList();
//            roles.add(new Role("Overall Administrators",1,"Overall System Administrators"));
//            roles.add(new Role("Overall General users",1,"Overall General users"));
//            roles.add(new Role("Branch's Administrators",2,"Branch's Administrators"));
//            roles.add(new Role("Branch's General users",2,"Branch's General users"));
//            
//            for(Role x: roles){
//                em.persist(x);
//                em.flush();
//                initializationLog+="\n "+x.getName() +" Added successfully";
//            }
        
        
        
        /*User(
        String fname,
        String otherNames,
        String email,
        String password,
        String pin,
        String permissions,
        String phoneNumber,
        String gender,
        String details)*/
        initializationLog+="\n\n Users Initialization begins...";
        List<User> usersList=new ArrayList();
        usersList.add(new User("System"," Administrator","admin@oltranz.com",Common.shared.get_SHA_512_SecurePassword("admin", "726"),"000","ffffffffffffffffffffffffffffffff","0726255084","MALE","Initial system administrator with all permissions"));
        for(User x: usersList){
            em.persist(x);
            initializationLog+="\n ID:"+ x.getUserId()+", Names:"+x.getFname() + x.getOtherNames() +" Added successfully";
            
            //UserInRole(Integer userId, Integer roleId)
            initializationLog+="\n\n Adding user ID:"+ x.getUserId()+", Names:"+x.getFname() + x.getOtherNames() +" to his roles  begins...";
            List<RoleForUser> roleForUserList=new ArrayList();
            roleForUserList.add(new RoleForUser(x.getUserId(),1));  // add to overall administrators
            roleForUserList.add(new RoleForUser(x.getUserId(),2));  // add to overall administrators
            roleForUserList.forEach(y->em.persist(x));
            for(RoleForUser y: roleForUserList){
                em.persist(y);
                
                initializationLog+="\n adding of user  ID:"+ x.getUserId()+", Names:"+x.getFname() + x.getOtherNames() +" to role"+ em.find(Role.class, y.getRoleForUserPK().getRoleId()).getName()+" completed successfully";
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
