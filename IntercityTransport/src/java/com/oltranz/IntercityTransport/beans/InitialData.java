/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.Action;
import com.oltranz.IntercityTransport.entities.CardType;
import com.oltranz.IntercityTransport.entities.ContractPaymentType;
import com.oltranz.IntercityTransport.entities.Gender;
import com.oltranz.IntercityTransport.entities.Service;
import com.oltranz.IntercityTransport.entities.SystemObject;
import com.oltranz.IntercityTransport.entities.TransactionType;
import com.oltranz.IntercityTransport.entities.UserRole;
import com.oltranz.IntercityTransport.entities.User;
import com.oltranz.IntercityTransport.entities.UserInRole;
import com.oltranz.IntercityTransport.entities.UserRoleType;
import com.oltranz.IntercityTransport.entities.WalletTransactionType;
import com.oltranz.IntercityTransport.entities.WalletType;
import com.oltranz.IntercityTransport.library.common;
import com.oltranz.IntercityTransport.models.ResultObject;
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
    @PersistenceContext(unitName = "IntercityTransportPU")
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
    
    public void InitializeTranactionsTypes(){
        try{
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionType.class));
            
            if((em.createQuery(cq).getResultList()).isEmpty()){
                //WalletType(Integer id,String name, String descr)
                List<TransactionType> transactionTypesList= new ArrayList();
                transactionTypesList.add(new TransactionType(1,"Wallet Transaction","Wallet Transaction"));
                transactionTypesList.add(new TransactionType(2,"Device ticket selling Transaction","Device ticket selling Tranaction"));
                
                for(TransactionType x: transactionTypesList){
                    em.persist(x);
                }
            }
        }
        catch(Exception ex){
            String message="AAA: Find All: "+ex.getMessage()+" | TRACE :";
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            message+=errors.toString();
            out.print(message);
            
        }
    }
    
    public void InitializeWalletTranactionsTypes(){
        try{
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionType.class));
            
            if((em.createQuery(cq).getResultList()).isEmpty()){
                //WalletType(Integer id,String name, String descr)
                List<WalletTransactionType> walletTransactionTypesList= new ArrayList();
                walletTransactionTypesList.add(new WalletTransactionType(1,"MTN mobile money loading","only for passengers' wallets, MTN mobile money loading"));
                walletTransactionTypesList.add(new WalletTransactionType(2,"TIGO cash Money Loading","only for passengers' wallets, TIGO cash Money Loading"));
                walletTransactionTypesList.add(new WalletTransactionType(3,"AirTel Money Loading","only for passengers' wallets, AirTel Money Loading"));
                walletTransactionTypesList.add(new WalletTransactionType(4,"Cash Payment Load","only for passengers' wallets, Cash Payment on reload"));
                walletTransactionTypesList.add(new WalletTransactionType(5,"Ticket payment","Ticket Payment"));
                walletTransactionTypesList.add(new WalletTransactionType(6,"contractual","Based on a contract between transporter and service provider"));
                
                for(WalletTransactionType x: walletTransactionTypesList){
                    em.persist(x);
                }
            }
        }
        catch(Exception ex){
            String message="AAA: Find All: "+ex.getMessage()+" | TRACE :";
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            message+=errors.toString();
            out.print(message);
            
        }
    }
    
    public  void InitializeContractPaymentTypes(){
        try{
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContractPaymentType.class));
            
            if(em.createQuery(cq).getResultList().isEmpty()){
                List<ContractPaymentType> paymentTypesList=new ArrayList();
                paymentTypesList.add(new ContractPaymentType(1,"Percentage/transactional","Percentage transactional"));
                paymentTypesList.add(new ContractPaymentType(2,"Fixed/transactional","Fixed transactional"));
                paymentTypesList.add(new ContractPaymentType(3,"Percentage/daily","Percentage daily"));
                paymentTypesList.add(new ContractPaymentType(4,"Fixed/daily","Fixed daily"));
                paymentTypesList.add(new ContractPaymentType(5,"Percentage/weekly","Percentage weekly"));
                paymentTypesList.add(new ContractPaymentType(6,"Fixed/weekly","Fixed weekly"));
                paymentTypesList.add(new ContractPaymentType(7,"Percentage/monthly","Percentage weekly"));
                paymentTypesList.add(new ContractPaymentType(8,"Fixed/monthly","Fixed monthly"));
                paymentTypesList.add(new ContractPaymentType(9,"Fixed/contractual","Fixed contractual"));
                for(ContractPaymentType x: paymentTypesList){
                    em.persist(x);
                }
            }
        }
        catch(Exception ex){
            String message="AAA: Find All: "+ex.getMessage()+" | TRACE :";
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            message+=errors.toString();
            out.print(message);
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
            userRoleTypeList.add(new UserRoleType(2,"Passengers","Customer to transporters"));
            userRoleTypeList.add(new UserRoleType(3,"Transporters Staff","Transporters staff, roles here have their Ids in the userRoleForTransporter "));
            userRoleTypeList.add(new UserRoleType(4,"Service Providers Staff","Service Providers staff, roles here have their Ids in the userRoleForServiceProviders"));
            
            for(UserRoleType x: userRoleTypeList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //WalletType(Integer id,String name, String descr)
            initializationLog+="\n\n wallets Tranactions Types Initialization begins...";
            List<WalletTransactionType> walletTransactionTypesList= new ArrayList();
            walletTransactionTypesList.add(new WalletTransactionType(1,"MTN mobile money loading","only for passengers' wallets, MTN mobile money loading"));
            walletTransactionTypesList.add(new WalletTransactionType(2,"TIGO cash Money Loading","only for passengers' wallets, TIGO cash Money Loading"));
            walletTransactionTypesList.add(new WalletTransactionType(3,"AirTel Money Loading","only for passengers' wallets, AirTel Money Loading"));
            walletTransactionTypesList.add(new WalletTransactionType(4,"Cash Payment Load","only for passengers' wallets, Cash Payment on reload"));
            walletTransactionTypesList.add(new WalletTransactionType(5,"Ticket payment","Ticket Payment"));
            walletTransactionTypesList.add(new WalletTransactionType(6,"contractual","Based on a contract between transporter and service provider"));
            
            for(WalletTransactionType x: walletTransactionTypesList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //WalletType(Integer id,String name, String descr)
            initializationLog+="\n\n Transactions Types Initialization begins...";
            List<TransactionType> transactionTypesList= new ArrayList();
            transactionTypesList.add(new TransactionType(1,"Wallet Transaction","Wallet Transaction"));
            walletTransactionTypesList.add(new WalletTransactionType(2,"Device ticket selling Transaction","Device ticket selling Tranaction"));
            
            for(TransactionType x: transactionTypesList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            //WalletType(Integer id,String name, String descr)
            initializationLog+="\n\n wallets Types Initialization begins...";
            List<WalletType> walletsTypesList= new ArrayList();
            walletsTypesList.add(new WalletType(1,"Passengers","Passengers Wallet"));
            walletsTypesList.add(new WalletType(2,"Transporter Wallet","Transporters wallet "));
            walletsTypesList.add(new WalletType(3,"Service Providers wallet","Service Provider's wallet"));
            
            for(WalletType x: walletsTypesList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //cardsType(Integer id,String name, String descr)
            initializationLog+="\n\n card types Initialization begins...";
            List<CardType> cardsTypesList= new ArrayList();
            cardsTypesList.add(new CardType(1,"Passenger card","Passengers Card"));
            cardsTypesList.add(new CardType(2,"Transporter cards","Transporters Staff Selling Device manamgement card"));
            
            for(CardType x: cardsTypesList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //String name,int type_id,String descr
            initializationLog+="\n\n Users' roles Initialization begins...";
            List<UserRole>usersRoles=new ArrayList();
            usersRoles.add(new UserRole("Overall Administrators",1,"Overall System Administrators"));
            usersRoles.add(new UserRole("Overall General users",1,"Overall General users"));
            usersRoles.add(new UserRole("Passengers",2,"Passengers"));
            usersRoles.add(new UserRole("Transporter's Administrators",3,"Transporter's Administrators"));
            usersRoles.add(new UserRole("Transporter's General users",3,"Transporter's General users"));
            usersRoles.add(new UserRole("Service Providers' Administrators",4,"Service Providers' Administrators"));
            usersRoles.add(new UserRole("Service Providers' General users",4,"Service Providers' General users"));
            
            for(UserRole x: usersRoles){
                em.persist(x);
                em.flush();
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
            for(Action x: actionsList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            
            //SystemObject(Integer id,String name, String descr)
            initializationLog+="\n\n System Objects Initialization begins...";
            List<SystemObject> systemObjectsList=new ArrayList();
            systemObjectsList.add(new SystemObject(1,"user","user of the system"));
            systemObjectsList.add(new SystemObject(2,"user role","user role"));
            systemObjectsList.add(new SystemObject(3,"transporter","transporter"));
            systemObjectsList.add(new SystemObject(4,"Service Provider","Service Provider"));
            systemObjectsList.add(new SystemObject(5,"Contract","Contract"));
            systemObjectsList.add(new SystemObject(6,"Bus","Bus"));
            systemObjectsList.add(new SystemObject(7,"Selling Device","Selling Device"));
            systemObjectsList.add(new SystemObject(8,"Selling Device","Selling Device"));
            for(SystemObject x: systemObjectsList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            
            //Service(Integer id,String name, String descr)
            initializationLog+="\n\n [providers] Services Initialization begins...";
            List<Service> servicesList=new ArrayList();
            servicesList.add(new Service(1,"Other","Other"));
            servicesList.add(new Service(2,"Bus Provider","Bus provider"));
            servicesList.add(new Service(3,"Cleaning","Bus Cleaning"));
            servicesList.add(new Service(4,"system support","system support"));
            for(Service x: servicesList){
                em.persist(x);
                initializationLog+="\n "+x.getName() +" Added successfully";
            }
            
            //Service(Integer id,String name, String descr)
            initializationLog+="\n\n [providers] Services Initialization begins...";
            List<ContractPaymentType> paymentTypesList=new ArrayList();
            paymentTypesList.add(new ContractPaymentType(1,"Percentage/transactional","Percentage transactional"));
            paymentTypesList.add(new ContractPaymentType(2,"Fixed/transactional","Fixed transactional"));
            paymentTypesList.add(new ContractPaymentType(3,"Percentage/daily","Percentage daily"));
            paymentTypesList.add(new ContractPaymentType(4,"Fixed/daily","Fixed daily"));
            paymentTypesList.add(new ContractPaymentType(5,"Percentage/weekly","Percentage weekly"));
            paymentTypesList.add(new ContractPaymentType(6,"Fixed/weekly","Fixed weekly"));
            paymentTypesList.add(new ContractPaymentType(7,"Percentage/monthly","Percentage weekly"));
            paymentTypesList.add(new ContractPaymentType(8,"Fixed/monthly","Fixed monthly"));
            paymentTypesList.add(new ContractPaymentType(9,"Fixed/contractual","Fixed contractual"));
            for(ContractPaymentType x: paymentTypesList){
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
            
            // User(String fname,String otherNames,String email,String password,String pin,String PhoneNumber,String gender,String details)
            initializationLog+="\n\n Users Initialization begins...";
            List<User> usersList=new ArrayList();
            usersList.add(new User("System"," Administrator","0711111111@intercity.rw",common.shared.get_SHA_512_SecurePassword("admin", "726"),"000","0711111111","1","ffffffffffffffffffffffffffffffff","Initial system administrator with all permissions"));
            for(User x: usersList){
                em.persist(x);
                em.flush();
                initializationLog+="\n ID:"+ x.getId()+", Names:"+x.getFname() + x.getOtherNames() +" Added successfully";
                
                //UserInRole(Integer userId, Integer roleId)
                initializationLog+="\n\n Adding user ID:"+ x.getId()+", Names:"+x.getFname() + x.getOtherNames() +" to his roles  begins...";
                List<UserInRole> usersInRolesList=new ArrayList();
                usersInRolesList.add(new UserInRole(x.getId(),1));  // add to overall administrators
                usersInRolesList.add(new UserInRole(x.getId(),2));  // add to overall administrators
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
