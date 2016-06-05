/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;

import com.oltranz.IntercityTransport.library.common;
import com.oltranz.IntercityTransport.entities.Log;
import com.oltranz.IntercityTransport.entities.UserRole;
import com.oltranz.IntercityTransport.entities.User;
import com.oltranz.IntercityTransport.entities.UserInRole;
import com.oltranz.IntercityTransport.entities.UserInRolePK;
import com.oltranz.IntercityTransport.entities.UserRoleForServiceProvider;
import com.oltranz.IntercityTransport.entities.UserRoleForTransporter;
import com.oltranz.IntercityTransport.library.common.selectListItemint;
import static com.oltranz.IntercityTransport.library.common.shared.byteArrayToHexString;
import static com.oltranz.IntercityTransport.library.common.shared.hexStringToByteArray;
import com.oltranz.IntercityTransport.models.AccessToken;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.UserDetailsModel;
import com.oltranz.IntercityTransport.models.UserEditModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class MembershipManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            InitialData initialDataEJB;
    @EJB
            TransportersManager transportersManagerEJB;
    @EJB
            WalletsManager walletsManagerEJB;
    
    public ResultObject getUser(Integer userId){
        ResultObject resultObject=new ResultObject();
        User user=em.find(User.class,userId);
        
        if(user!=null){
            resultObject.setMessage("User Well found and returned!");
            resultObject.setObject(user);
            resultObject.setObjectClass(User.class);
            return resultObject;
        }else{
            resultObject.setMessage("User with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(User.class);
            return resultObject;
        }
    }
    
    public  List<selectListItemint> getUsersRolesSelectList()
    {
        Query query = em.createQuery("select new com.oltranz.IntercityTransport.library.common.selectListItemint(r.id, r.name) from UserRole r");
        
        List<selectListItemint>list=query.getResultList();
        return list;
        //return (from i in db.usersRoles select new selectListItemint { value = i.id, text = i.name }).ToList();
    }
    
    public   List<selectListItemint> getUsers2Add2userRole(int roleId, int requestorId, String searchString)
    {
        
        //now find all the users that are eligible by the searchString
        List<selectListItemint> users;
        Query query=em.createNativeQuery("select u.id,u.name from users u join users_roles ur  on u.id=ur.userId and u.permissio");
        users=query.getResultList();
        return users;
        
    }
    
    public  Boolean addRole2UsersList(List<selectListItemint> usersIds, int roleId)
    {
        try
        {
            usersIds.forEach(x->{
                UserInRole ur = new UserInRole();
                UserInRolePK pk=new UserInRolePK();
                pk.setRoleId(roleId);
                pk.setUserId(x.getValue());
                ur.setUserRolePK(pk);
                em.persist(ur);
            });
            
            
            // message = "all selected users are successfully added to the " + roleName + " group";
            return true;
        }
        catch (Exception e)
        {
            // message = e.Message;
            return false;
        }
    }
    
    public  ResultObject removeUser4rmRole(int userId, int roleId)
    {
        ResultObject returnObject= new ResultObject();
        returnObject.setObjectClass(Boolean.class);
        try
        {
            if (roleId == 1) //no user can be removed from the main group
            {
                returnObject.setMessage("The general member role can not be taken from any user!");
                returnObject.setObject(true);
                return returnObject;
            }
            
            if (roleId == 2 && userId == 1)// the group with ID =1 is a built and cannot be removed from the list of administrators
            {
                returnObject.setMessage("The built in administrator cannot built removed from overall administrators!");
                returnObject.setObject(false);
                return returnObject;
            }
            
            UserInRolePK pk= new UserInRolePK();
            pk.setRoleId(roleId);
            pk.setUserId(userId);
            UserInRole userRole = em.find(UserInRole.class, pk);
            User u=em.find(User.class, userId);
            
            UserRole role=em.find(UserRole.class,roleId);
            
            String userNames = u.getFname() + " " + u.getOtherNames();
            String roleName = role.getName();
            if (userRole == null)
            {
                returnObject.setMessage("The user is not (no longer) a member of this group!");
                returnObject.setObject(false);
                return returnObject;
            }
            em.remove(userRole);
            
            returnObject.setMessage("the role " + roleName + " of the user '" + userNames + "' is successfully revoked");
            returnObject.setObject(false);
            return returnObject;
        }
        catch (Exception e)
        {
            returnObject.setMessage(e.getMessage());
            returnObject.setObject(false);
            return returnObject;
        }
        
        
        
    }
    
    public  ResultObject setPassword(Integer userId, String newPassword, Integer actionBy)
    {
        ResultObject resultObject=new ResultObject();
        try
        {
            User _u = em.find(User.class, userId);
            if (_u != null)
            {
                _u.setPassword(common.shared.get_SHA_512_SecurePassword(newPassword, "726"));
                em.merge(_u);
                Log log=new Log();
                log.setActionName("set Password");
                log.setActionResult(0);
                log.setDatetime(new Date());
                log.setActionId(1);
                log.setObjectId(userId); //user object id =1
                log.setObjectName("user");
                log.setUserId(actionBy);
                User actionByUser=em.find(User.class,actionBy);
                log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
                em.persist(log);
                em.flush();
                
                
                resultObject.setMessage("New Password well saved");
                resultObject.setObject(true);
                resultObject.setObjectClass(Boolean.class);
            }
            else
            {
                resultObject.setMessage("User could not be found");
                resultObject.setObject(false);
                resultObject.setObjectClass(Boolean.class);
            }
            return resultObject;
        }
        catch (Exception e)
        {
            resultObject.setMessage(e.getMessage());
            resultObject.setObject(false);
            resultObject.setObjectClass(Boolean.class);
            return resultObject;
        }
        
        
    }
    
    public  ResultObject ChangePassword(Integer userId, String oldPassword, String newPassword, Integer actionBy)
    {
        ResultObject resultObject=new ResultObject();
        try
        {
            
            oldPassword =common.shared.get_SHA_512_SecurePassword(oldPassword, "726");
            User _u = em.find(User.class,userId);
            
            if (_u != null)
            {
                
                if (_u.getPassword().equals(oldPassword))
                {
                    
                    _u.setPassword(common.shared.get_SHA_512_SecurePassword(newPassword, "726"));
                    em.merge(_u);
                    
                    //log action
                    Log log=new Log();
                    log.setActionName("change Password");
                    log.setActionResult(0);
                    log.setDatetime(new Date());
                    log.setActionId(2);
                    log.setObjectId(userId); //user object id =1
                    log.setObjectName("user");
                    log.setUserId(actionBy);
                    User actionByUser=em.find(User.class,actionBy);
                    log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
                    em.persist(log);
                    em.flush();
                    resultObject.setMessage("New password successfully saved");
                    resultObject.setObject(true);
                    resultObject.setObjectClass(Boolean.class);
                    return resultObject;
                }
                
                else
                {
                    resultObject.setMessage("password change failed because the current password you gave is wrong!");
                    resultObject.setObject(false);
                    resultObject.setObjectClass(Boolean.class);
                    return resultObject;
                    
                }
            }
            else
            {
                resultObject.setMessage("password change failed because the profile of the provided users account could not be found!");
                resultObject.setObject(false);
                resultObject.setObjectClass(Boolean.class);
                return resultObject;
                
            }
        }
        catch (Exception e)
        {
            
            resultObject.setMessage(e.getMessage());
            resultObject.setObject(false);
            resultObject.setObjectClass(Boolean.class);
            return resultObject;
        }
        
        
    }
    
    public  ResultObject createUser(UserEditModel newUser)
    {
        ResultObject resultObject=new ResultObject();
        resultObject.setObject(null);
        resultObject.setObjectClass(UserDetailsModel.class);
        
        Integer actionBy=newUser.getActionUserId();
        try
        {
            Boolean isvalid = true;
            
            // check to see if action user is a valid user with right to create a new user
            
            
            if(!newUser.getPassword().equals(newUser.getRepeatPassword())){
                resultObject.setMessage("Password not repeated correctly");
                return resultObject;
            }
            
            if(newUser.getPhoneNumber()==null){
                resultObject.setObject(null);
                resultObject.setMessage("Phone number is not mendatory");
                return resultObject;
            }
            if(newUser.getPhoneNumber().trim().isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("Phone number is not mendatory");
                return resultObject;
            }
            
            Query query=em.createQuery("select u from User u where u.phoneNumber=:phoneNumber");
            query.setParameter("phoneNumber", newUser.getPhoneNumber());
            if(!query.getResultList().isEmpty()){
                resultObject.setObject(null);
                resultObject.setMessage("User with this phone number exists already");
                return resultObject;
            }
            
            User actionUser=em.find(User.class, actionBy);
            if(actionUser==null){
                resultObject.setMessage("Action user not found");
                return resultObject;
            }
            
            if(common.shared.GetBit(common.shared.hexStringToByteArray(actionUser.getPermissions()), 1)==0){
                resultObject.setMessage("User doesn't have the right to create new user");
                return resultObject;
            }
            
            query = em.createQuery("select u from User u where UPPER(u.fname)=:fname and UPPER(u.otherNames)=:otherNames");
            query.setParameter("fname",newUser.getFname().toUpperCase());
            query.setParameter("otherNames", newUser.getOtherNames().toUpperCase());
            
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setMessage("- User account with same names exists already! -");
            }
            
            query = em.createQuery("select u from User u where UPPER(u.email)=:email");
            query.setParameter("email",newUser.getEmail().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setMessage("- user account with same email exists already! -");
            }
            
            if (!isvalid)
                return resultObject;
            
            User u = new User();
            u.setDetails(newUser.getDetails());
            if(newUser.getEmail()!=null){
                if(newUser.getEmail().trim().isEmpty()){
                    u.setEmail(newUser.getPhoneNumber()+"@intercity.rw");
                }
            }else{
                u.setEmail(newUser.getPhoneNumber()+"@intercity.rw");
            }
            u.setFname(newUser.getFname());
            u.setOtherNames(newUser.getOtherNames());
            u.setPassword(common.shared.get_SHA_512_SecurePassword(newUser.getPassword(), "726"));
            u.setStatus(7);
            u.setGender(newUser.getGender());
            u.setPhoneNumber(newUser.getPhoneNumber());
            
            byte[] permissions=common.shared.hexStringToByteArray(u.getPermissions());
            common.shared.setBit(permissions, 1, 0); // set to not deleted
            common.shared.setBit(permissions, 2, 0); // set to not disabled
            u.setPermissions(common.shared.byteArrayToHexString(permissions));
            em.persist(u);
            em.flush();
            
            resultObject=getUserDetails(u);
            
            Log log=new Log();
            log.setActionName("create new user");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setActionId(3);
            log.setObjectId(u.getId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(actionBy);
            User actionByUser=em.find(User.class,actionBy);
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            em.persist(log);
            
            
            resultObject.setMessage("User well created");
            
            return resultObject;
        }
        catch (Exception e)
        {
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            return resultObject;
        }
    }
    
    
    public  ResultObject updateUser(UserEditModel editUser)
    {
        ResultObject resultObject=new ResultObject();
        resultObject.setObject(null);
        resultObject.setObjectClass(UserDetailsModel.class);
        
        Integer actionBy=editUser.getActionUserId();
        try
        {
            Boolean isvalid = true;
            
            User actionUser=em.find(User.class, actionBy);
            if(actionUser==null){
                resultObject.setMessage("Action user not found");
                return resultObject;
            }
            
            if(common.shared.GetBit(common.shared.hexStringToByteArray(actionUser.getPermissions()), 4)==0){
                resultObject.setMessage("User doesn't have the right to create new user");
                return resultObject;
            }
            
            Query query = em.createQuery("select u from User u where UPPER(u.fname)=:fname and UPPER(u.otherNames)=:otherNames and u.id!=:userId");
            query.setParameter("userId",editUser.getUserId());
            query.setParameter("fname",editUser.getFname().toUpperCase());
            query.setParameter("otherNames", editUser.getOtherNames().toUpperCase());
            
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setMessage("- User account with same names exists already! -");
            }
            
            query = em.createQuery("select u from User u where UPPER(u.email)=:email and u.id!=:userId");
            query.setParameter("userId",editUser.getUserId());
            query.setParameter("email",editUser.getEmail().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setMessage("- user account with same email exists already! -");
            }
            
            if (!isvalid)
                return resultObject;
            
            User u = em.find(User.class, editUser.getUserId());
            u.setDetails(editUser.getDetails());
            u.setEmail(editUser.getEmail().trim());
            u.setFname(editUser.getFname());
            u.setOtherNames(editUser.getOtherNames());
            u.setGender(editUser.getGender());
            u.setPhoneNumber(editUser.getPhoneNumber());
            
            
            em.merge(u);
            em.flush();
            
            
            resultObject.setObject(getUserDetails(u));
            
            
            Log log=new Log();
            log.setActionName("Update user");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setActionId(3);
            log.setObjectId(u.getId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(actionBy);
            User actionByUser=em.find(User.class,actionBy);
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            em.persist(log);
            
            resultObject.setObject(u);
            resultObject.setMessage("User details well updated");
            
            return resultObject;
        }
        catch (Exception e)
        {
            String message=e.getMessage();
            resultObject.setMessage(message);
            return resultObject;
            
        }
        
        
    }
    
    public ResultObject getUserDetails(User user){
        
        ResultObject resultObject=new ResultObject();
        
        User u =em.find(User.class, user.getId());
        if(u==null){
            resultObject.setMessage("User with given userId not found");
            resultObject.setObject(null);
            resultObject.setObjectClass(UserDetailsModel.class);
            return resultObject;
        }
        
        UserDetailsModel userDetails=new UserDetailsModel();
        userDetails.setDetails(u.getDetails());
        userDetails.setEmail(u.getEmail());
        userDetails.setFname(u.getFname());
        userDetails.setGender(u.getGender());
        userDetails.setOtherNames(u.getOtherNames());
        userDetails.setPhoneNumber(u.getPhoneNumber());
        userDetails.setPermissions(u.getPermissions());
        userDetails.setUserId(u.getId());
        
        //get list of roles this is user has
        Object object =getUserRoles(userDetails.getUserId()).getObject();
        
        userDetails.setRoles((List<UserRole>)object);
        
        resultObject.setMessage("User well found!");
        resultObject.setObject(userDetails);
        resultObject.setObjectClass(UserDetailsModel.class);
        return resultObject;
    }
    
    public ResultObject getUserDetails(Integer  userId){
        ResultObject resultObject;
        
        resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        resultObject= getUserDetails((User)resultObject.getObject());
        return resultObject;
    }
    
    //UserDetailsModel
    
    public  ResultObject  getAllUsers(){
        ResultObject resultObject=new ResultObject();
        
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        List<User> usersList=em.createQuery(cq).getResultList();
        List<UserDetailsModel> usersWithDetailsList=new ArrayList();
        for(User user: usersList){
            UserDetailsModel u=(UserDetailsModel) getUserDetails(user.getId()).getObject();
            usersWithDetailsList.add(u);
        }
        resultObject.setObject(usersWithDetailsList);
        resultObject.setObjectClass(ArrayList.class);
        if(usersWithDetailsList.size()>0)
            resultObject.setMessage("User list well found");
        else
            resultObject.setMessage("The are no users in the databases");
        
        return resultObject;
    }
    
    public ResultObject  getUsersInRole(Integer roleId){
        ResultObject resultObject=new ResultObject();
        Query query=em.createQuery("select u from User u join UserInRole ur  on u.id=ur.userId");
        query.setParameter("roleId", roleId);
        
        List<User> usersList=query.getResultList();
        List<UserDetailsModel> usersWithDetailsList=new ArrayList();
        for(User user: usersList){
            UserDetailsModel u= new UserDetailsModel();
            u.setDetails(user.getDetails());
            u.setEmail(user.getEmail());
            u.setFname(user.getFname());
            u.setGender(user.getGender());
            u.setOtherNames(user.getOtherNames());
            u.setPhoneNumber(user.getPhoneNumber());
            u.setUserId(user.getId());
            usersWithDetailsList.add(u);
        }
        resultObject.setObject(usersWithDetailsList);
        if(usersWithDetailsList.size()>0)
            resultObject.setMessage("User list well found");
        else
            resultObject.setMessage("The are no users in the databases");
        
        return resultObject;
    }
    
    public ResultObject getUserRoles(Integer userId){
        ResultObject resultObject;
        resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        
        Query query=em.createQuery("select r from UserRole r join UserInRole ur on r.id=ur.userInRolePK.roleId where ur.userInRolePK.userId=:userId");
        query.setParameter("userId", userId);
        
        Object object=query.getResultList();
        List<UserRole> userRolesList=(List<UserRole>)object;
        resultObject.setObject(userRolesList);
        resultObject.setObjectClass(ArrayList.class);
        
        if(userRolesList.isEmpty())
            resultObject.setMessage("This user is not in any users Role");
        else
            resultObject.setMessage(userRolesList.size()+" users role found for this users!");
        
        return resultObject;
    }
    
    public ResultObject getOverallUserPermission(Integer userId){
        ResultObject resultObject;
        resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        User u=(User)resultObject.getObject();
        
        byte[] userPermissions=common.shared.hexStringToByteArray(u.getPermissions());
        if ((u.getStatus()& 1)==0) //is deleted (not undeleted)
        {
            resultObject.setMessage("User is in deleted state");
            return resultObject;
        }
        if ((u.getStatus()& 3)==0) //is disabled (not enabled)
        {
            resultObject.setMessage("User is disabled");
            return resultObject;
        }
        
        
        List<UserRole> rolesList=(List<UserRole>)getUserRoles(u.getId()).getObject();
        
        // combine all roles permissions with this user's
        byte[] permissions=userPermissions;
        byte[] rolePermissions;
        
        for(UserRole r: rolesList){
            rolePermissions=hexStringToByteArray(r.getPermissions());
            if(( r.getStatus()&3 )==3){  //role not delete nor disabled
                for(int i=0;i<permissions.length;i++)
                    permissions[i]&=rolePermissions[i];
            }
        }
        
        resultObject.setObject(byteArrayToHexString(permissions));
        resultObject.setMessage("Permission for this user combined with the permission of the his/her role well found");
        resultObject.setObjectClass(String.class);
        return resultObject;
    }
    
    public  ResultObject authenticateUser(String phoneNumber, String password)
    {
        AccessToken token;
        ResultObject resultObject=new ResultObject();
        resultObject.setObject(null);
        resultObject.setObjectClass(AccessToken.class);
        try
        {
            User user;
            try{
                password =common.shared.get_SHA_512_SecurePassword(password, "726");
                Query query= em.createQuery("select u from User u where u.phoneNumber=:phoneNumber and UPPER(u.password)=:password");
                query.setParameter("phoneNumber",phoneNumber);
                query.setParameter("password", password.toUpperCase());
                
                user=(User) query.getSingleResult();
            }
            catch(NoResultException ex){
                resultObject.setObject(null);
                resultObject.setObjectClass(AccessToken.class);
                resultObject.setMessage("Unsuccessful Authentication");
                return resultObject;
            }
            
            
            UserDetailsModel userDetails=(UserDetailsModel)getUserDetails(user.getId()).getObject();
             SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            token= new AccessToken();
            token.setCreationTime(sdf.format(new Date()));
            token.setUserDetails(userDetails);
            resultObject.setObject(token);
            resultObject.setMessage("Successfull Authentification");
            
            return resultObject;
        }
        
        catch (Exception e)
        {
            
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
        
        
    }
    
    public ResultObject addUserToTransporter(Integer userId,Integer transporterId){
        ResultObject resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        resultObject=transportersManagerEJB.getTransporter(transporterId);
        
        if(resultObject.getObject()==null)
            return resultObject;
        
        //a user to be added as a transporter staff can not be a service provider staff, but can be a passanger
        
        Query query=em.createQuery("select ur from UserRoleForTransporter ur where ur.userRoleForTransporterPK.transporterId=:transporterId");
        query.setParameter("transporterId", transporterId);
        
        UserRoleForTransporter userRoleForTransporter=(UserRoleForTransporter)query.getSingleResult();
        
        if(userRoleForTransporter==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: User role for this transporter's staff is missing in users Roles for transporters");
            resultObject.setObjectClass(UserRoleForTransporter.class);
            return resultObject;
        }
        
        UserRole userRole=em.find(UserRole.class, userRoleForTransporter.getUserRoleForTransporterPK().getRoleId());
        if(userRole==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: User role for this transporter's staff exist in users roles for transporters but  is missing in users Roles");
            resultObject.setObjectClass(UserRoleForTransporter.class);
            return resultObject;
        }
        
        UserInRolePK userInRolePK=new UserInRolePK(userId,userRoleForTransporter.getUserRoleForTransporterPK().getRoleId());
        UserInRole userInRole=new UserInRole();
        userInRole.setUserRolePK(userInRolePK);
        em.persist(userInRole);
        em.flush();
        
        resultObject.setObject(getUserDetails(userId).getObject());
        resultObject.setMessage("User well added in "+userRole.getName());
        resultObject.setObjectClass(UserDetailsModel.class);
        return resultObject;
    }
    
    public ResultObject addUserToServiceProvider(Integer userId,Integer serviceProviderId){
        ResultObject resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        resultObject=transportersManagerEJB.getTransporter(serviceProviderId);
        
        if(resultObject.getObject()==null)
            return resultObject;
        
        //a user to be added as a transporter staff can not be a service provider staff, but can be a passanger
        
        Query query=em.createQuery("select ur from UserRoleForServiceProvider ur where ur.userRoleForServiceProviderPK.serviceProviderId=:serviceProviderId");
        query.setParameter("serviceProviderId", serviceProviderId);
        UserRoleForServiceProvider userRoleForServiceProvider=(UserRoleForServiceProvider)query.getSingleResult();
        
        if(userRoleForServiceProvider==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: User role for this Service Provider's staff is missing in users Roles for service Provider");
            resultObject.setObjectClass(UserRoleForServiceProvider.class);
            return resultObject;
        }
        
        UserRole userRole=em.find(UserRole.class, userRoleForServiceProvider.getUserRoleForServiceProviderPK().getRoleId());
        if(userRole==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: User role for this service Provider's staff exist in users roles for service Provider but  is missing in users Roles");
            resultObject.setObjectClass(UserRoleForServiceProvider.class);
            return resultObject;
        }
        
        UserInRolePK userInRolePK=new UserInRolePK(userId,userRoleForServiceProvider.getUserRoleForServiceProviderPK().getRoleId());
        UserInRole userInRole=new UserInRole();
        userInRole.setUserRolePK(userInRolePK);
        em.persist(userInRole);
        em.flush();
        
        resultObject.setObject(getUserDetails(userId).getObject());
        resultObject.setMessage("User well added in "+userRole.getName());
        resultObject.setObjectClass(UserDetailsModel.class);
        return resultObject;
    }
    
    public ResultObject addPassenger(Integer userId){
        ResultObject resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        //add user to Membership role
        
        UserInRole userInRole=new UserInRole();
        UserInRolePK userInRolePK=new UserInRolePK();
        userInRolePK.setRoleId(3);
        userInRolePK.setUserId(userId);
        userInRole.setUserRolePK(userInRolePK);
        em.persist(userInRole);
        
        //create passenger wallet and assign it to this user
        resultObject=walletsManagerEJB.createPassengerWallet(userId);
        if(resultObject.getObject()==null){
            resultObject.setObject((UserDetailsModel)getUserDetails(userId).getObject());
            resultObject.setObjectClass(UserDetailsModel.class);
            resultObject.setMessage("User Well made passenger but. wallet creation message:"+resultObject.getMessage());
            return resultObject;
        }
        
        UserDetailsModel userDetails=(UserDetailsModel)getUserDetails(userId).getObject();
        resultObject.setObject(userDetails);
        resultObject.setMessage("User Well made passenger and wallet successfully created");
        return resultObject;
        
    }
}
