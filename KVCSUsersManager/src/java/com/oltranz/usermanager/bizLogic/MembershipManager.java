/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.bizLogic;

import com.oltranz.usermanager.entities.Log;
import com.oltranz.usermanager.entities.User;
import com.oltranz.usermanager.entities.UserInRole;
import com.oltranz.usermanager.entities.UserInRolePK;
import com.oltranz.usermanager.entities.UserRole;
import com.oltranz.usermanager.library.Common;
import static com.oltranz.usermanager.library.Common.shared.hexStringToByteArray;
import com.oltranz.usermanager.library.CommonLibrary.selectListItemint;
import com.oltranz.usermanager.models.AuthenticationRequestModel;
import com.oltranz.usermanager.models.AuthenticationResponseModel;
import com.oltranz.usermanager.models.ResultObject;
import com.oltranz.usermanager.models.SimpleStatus;
import com.oltranz.usermanager.models.UserCreateResponseModel;
import com.oltranz.usermanager.models.UserDetailsModel;
import com.oltranz.usermanager.models.UserEditModel;
import com.oltranz.usermanager.models.UserEditResponseModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
    @PersistenceContext(unitName = "kvcsUserManagerPU")
    private  EntityManager em;
    
    @EJB
            InitialData InitialDataEJB;
    
    
    public User getUser(Integer userId){
        User user=em.find(User.class,userId);
        return user;
    }
    
    public  List<selectListItemint> getUsersRolesSelectList()
    {
        Query query = em.createQuery("select NEW com.oltranz.usermanager.library.Common.selectListItemint(r.id, r.name) from UserRole r");
        
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
    
    public  ResultObject setPassword(Integer userId, String newPassword, Integer actionByUserId)
    {
        ResultObject resultObject=new ResultObject();
        try
        {
            User _u = em.find(User.class, userId);
            if (_u != null)
            {
                _u.setPassword(Common.shared.get_SHA_512_SecurePassword(newPassword, "726"));
                em.merge(_u);
                Log log=new Log();
                log.setActionName("set Password");
                log.setActionResult(0);
                log.setDatetime(new Date());
                log.setActionId(1);
                log.setObjectId(userId+""); //user object id =1
                log.setObjectName("user");
                log.setUserId(actionByUserId);
                User actionByUser=em.find(User.class,actionByUserId);
                log.setUserName(actionByUser.getUserName());
                em.persist(log);
                
                
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
    
    public  ResultObject ChangePassword(Integer userId, String oldPassword, String newPassword, Integer actionByUserId)
    {
        ResultObject resultObject=new ResultObject();
        try
        {
            
            oldPassword =Common.shared.get_SHA_512_SecurePassword(oldPassword, "726");
            User _u = em.find(User.class,userId);
            
            if (_u != null)
            {
                
                if (_u.getPassword().equals(oldPassword))
                {
                    
                    _u.setPassword(Common.shared.get_SHA_512_SecurePassword(newPassword, "726"));
                    em.merge(_u);
                    
                    //log action
                    Log log=new Log();
                    log.setActionName("change Password");
                    log.setActionResult(0);
                    log.setDatetime(new Date());
                    log.setActionId(2);
                    log.setObjectId(userId+""); //user object id =1
                    log.setObjectName("user");
                    log.setUserId(actionByUserId);
                    User actionByUser=em.find(User.class,actionByUserId);
                    log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
                    em.persist(log);
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
    
    public  UserCreateResponseModel createUser(UserEditModel newUser)
    {
        UserCreateResponseModel userCreateResponse=new UserCreateResponseModel();
        SimpleStatus status =new SimpleStatus();
        
        Integer actionByUserId=newUser.getActionUserId();
        try
        {
            Boolean isvalid = true;
            
            // check to see if action user is a valid user with right to create a new user
            
            
            if(!newUser.getPassword().equals(newUser.getRepeatPassword())){
                status.setDesc("Password not repeated correctly");
                status.setCode("500");
                userCreateResponse.setStatus(status);
                return userCreateResponse;
            }
            
            User actionUser=em.find(User.class, actionByUserId);
            if(actionUser==null){
                status.setDesc("Action user not found");
                status.setCode("500");
                userCreateResponse.setStatus(status);
                return userCreateResponse;
            }
            
            if(Common.shared.GetBit(Common.shared.hexStringToByteArray(actionUser.getPermissions()), 1)==0){
                
                status.setDesc("User doesn't have the right to create new user");
                status.setCode("500");
                userCreateResponse.setStatus(status);
                return userCreateResponse;
            }
            
            Query query = em.createQuery("select u from User u where UPPER(u.fname)=:fname and UPPER(u.otherNames)=:otherNames");
            query.setParameter("fname",newUser.getFname().toUpperCase());
            query.setParameter("otherNames", newUser.getOtherNames().toUpperCase());
            
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                status.setDesc("- User account with same names exists already! -");
                status.setCode("500");
                userCreateResponse.setStatus(status);
            }
            
            
            
            query = em.createQuery("select u from User u where UPPER(u.email)=:email");
            query.setParameter("email",newUser.getEmail().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                status.setDesc("- user account with same email exists already! -");
                
            }
            
             query = em.createQuery("select u from User u where u.PhoneNumber=:phoneNumber");
            query.setParameter("phoneNumber",newUser.getPhoneNumber().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                status.setDesc("- user account with same phone number exists already! -");
                
            }
            
            if (!isvalid){
                status.setCode("500");
                userCreateResponse.setStatus(status);
                return userCreateResponse;
            }
            
            User u = new User();
            u.setDetails(newUser.getDetails());
            u.setEmail(newUser.getEmail().trim());
            u.setFname(newUser.getFname());
            u.setOtherNames(newUser.getOtherNames());
            u.setPassword(Common.shared.get_SHA_512_SecurePassword(newUser.getPassword(), "726"));
            u.setStatus(7);
            u.setGender(newUser.getGender());
            u.setPhoneNumber(newUser.getPhoneNumber());
            
            byte[] permissions=Common.shared.hexStringToByteArray(u.getPermissions());
            Common.shared.setBit(permissions, 1, 0); // set to not deleted
            Common.shared.setBit(permissions, 2, 0); // set to not disabled
            u.setPermissions(Common.shared.byteArrayToHexString(permissions));
            em.persist(u);
            em.flush();
            
            userCreateResponse.setUserDetails(getUserDisplayDetails(u.getId()));
            status.setDesc("Success");
            status.setCode("100");
            userCreateResponse.setStatus(status);
            
            Log log=new Log();
            log.setActionName("create new user");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setActionId(3);
            log.setObjectId(u.getId()+""); //user object id =1
            log.setObjectName("user");
            log.setUserId(actionByUserId);
            User actionByUser=em.find(User.class,actionByUserId);
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            em.persist(log);
            
            
            return userCreateResponse;
        }
        catch (Exception e)
        {
            status.setDesc(e.getMessage());
            status.setCode("500");
            userCreateResponse.setStatus(status);
            return userCreateResponse;
        }
    }
    
    
    public  UserEditResponseModel updateUser(UserEditModel editUser)
    {
        UserEditResponseModel userEditResponse=new UserEditResponseModel();
        SimpleStatus status =new SimpleStatus();
        Integer actionBy=editUser.getActionUserId();
        try
        {
            Boolean isvalid = true;
            
            User actionUser=em.find(User.class, actionBy);
            if(actionUser==null){
                status.setDesc("Action user not found");
                status.setCode("500");
                userEditResponse.setStatus(status);
                return userEditResponse;
            }
            
            if(Common.shared.GetBit(Common.shared.hexStringToByteArray(actionUser.getPermissions()), 4)==0){
                status.setDesc("User doesn't have the right to create new user");
                status.setCode("500");
                userEditResponse.setStatus(status);
                return userEditResponse;
            }
            
            Query query = em.createQuery("select u from User u where UPPER(u.fname)=:fname and UPPER(u.otherNames)=:otherNames and u.id!=:userId");
            query.setParameter("userId",editUser.getUserId());
            query.setParameter("fname",editUser.getFname().toUpperCase());
            query.setParameter("otherNames", editUser.getOtherNames().toUpperCase());
            
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                status.setDesc("- User account with same names exists already! -");
                
            }
            
            query = em.createQuery("select u from User u where UPPER(u.email)=:email and u.id!=:userId");
            query.setParameter("userId",editUser.getUserId());
            query.setParameter("email",editUser.getEmail().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                status.setDesc("- user account with same email exists already! -");
            }
            
            if (!isvalid){
                
                status.setCode("500");
                userEditResponse.setStatus(status);
                return userEditResponse;
            }
            
            
            User u = new User();
            u.setDetails(editUser.getDetails());
            u.setEmail(editUser.getEmail().trim());
            u.setFname(editUser.getFname());
            u.setOtherNames(editUser.getOtherNames());
            u.setGender(editUser.getGender());
            u.setPhoneNumber(editUser.getPhoneNumber());
            
            em.merge(u);
            em.flush();
            
            status.setCode("100");
            status.setDesc("Success");
            userEditResponse.setStatus(status);
            userEditResponse.setUserDetails(getUserDisplayDetails(u.getId()));
            
            
            Log log=new Log();
            log.setActionName("Update user");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setActionId(3);
            log.setObjectId(u.getId()+""); //user object id =1
            log.setObjectName("user");
            log.setUserId(actionBy);
            User actionByUser=em.find(User.class,actionBy);
            log.setUserName(actionByUser.getUserName());
            em.persist(log);
            
            
            return userEditResponse;
        }
        catch (Exception e)
        {
            status.setDesc(e.getMessage());
            status.setCode("500");
            userEditResponse.setStatus(status);
            return userEditResponse;
        }
        
        
    }
    
    public UserDetailsModel getUserDisplayDetails(Integer userId){
        User u =em.find(User.class, userId);
        
        if(u!=null){
            UserDetailsModel userDetails=new UserDetailsModel();
            userDetails.setDetails(u.getDetails());
            userDetails.setEmail(u.getEmail());
            userDetails.setUserName(u.getUserName());
            userDetails.setFname(u.getFname());
            userDetails.setGender(u.getGender());
            userDetails.setOtherNames(u.getOtherNames());
            userDetails.setPhoneNumber(u.getPhoneNumber());
            userDetails.setUserId(u.getId());
            return userDetails;
        }
        else
            return null;
    }
    
    
    //UserDetailsModel
    
    public  List<UserDetailsModel>  getAllUsersWithDetails(){
        
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        List<User> usersList=em.createQuery(cq).getResultList();
        List<UserDetailsModel> usersWithDetailsList=new ArrayList();
        for(User user: usersList){
            UserDetailsModel u=(UserDetailsModel) getUserDisplayDetails(user.getId());
            usersWithDetailsList.add(u);
        }
        
        return usersWithDetailsList;
    }
    
    public List<UserDetailsModel>  getUsersInRole(Integer roleId){
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
        
        
        return usersWithDetailsList;
    }
    
    public List<UserRole> getUserRoles(Integer userId){
        
        User u =getUser(userId);
        if(u==null)
            return null;
        
        
        Query query=em.createQuery("select r from UserRole r join UserInRole ur on r.id=ur.userInRolePK.roleId where ur.userInRolePK.userId=:userId");
        query.setParameter("userId", userId);
        
        List<UserRole> userRolesList=(List<UserRole>)query.getResultList();
        return userRolesList;
    }
    
    public String getOverallUserPermission(Integer userId){
        
        User u =getUser(userId);
        if(u==null)
            return null;
        
        List<UserRole> rolesList=(List<UserRole>)getUserRoles(u.getId());
        byte[] userPermissions=Common.shared.hexStringToByteArray(u.getPermissions());
        // combine all roles permissions with this user's
        byte[] permissions=userPermissions;
        byte[] rolePermissions;
        
        for(UserRole r: rolesList){
            rolePermissions=hexStringToByteArray(r.getPermissions());
            if(( r.getStatus()&3 )==3){  //role not delete nor disabled
                for(int i=0;i<permissions.length;i++)
                    permissions[i]|=rolePermissions[i];
            }
        }
        
        return Common.shared.byteArrayToHexString(permissions);
    }
    
    public  AuthenticationResponseModel authenticateUser(AuthenticationRequestModel auth)
    {
        AuthenticationResponseModel authenticationResponse=new AuthenticationResponseModel();
        SimpleStatus status=new SimpleStatus();
        try
        {
            User user=null;
            try{
                String password =Common.shared.get_SHA_512_SecurePassword(auth.getPassword(), "726");
                
                String email=auth.getEmail();
                String phoneNumber=auth.getPhoneNumber();
                String userName=auth.getUserName();
                
                if(userName!=null){
                    Query query= em.createQuery("select u from User u where  UPPER(u.userName)=:userName and UPPER(u.password)=:password");
                    query.setParameter("userName", userName.toUpperCase());
                    query.setParameter("password", password.toUpperCase());
                    user=(User) query.getSingleResult();
                }else{
                    if(email!=null){
                        Query query= em.createQuery("select u from User u where UPPER(u.email)=:email and UPPER(u.password)=:password");
                        query.setParameter("email", email.toUpperCase());
                        query.setParameter("password", password.toUpperCase());
                        user=(User) query.getSingleResult();
                    }else{
                        if(phoneNumber!=null){
                            Query query= em.createQuery("select u from User u where  UPPER(u.PhoneNumber)=:phoneNumber and UPPER(u.password)=:password");
                            query.setParameter("phoneNumber", phoneNumber.toUpperCase());
                            query.setParameter("password", password.toUpperCase());
                            user=(User) query.getSingleResult();
                        }
                    }
                }
                if(user==null){
                    status.setCode("404");
                    status.setDesc("Wrong creadentials");
                    authenticationResponse.setStatus(status);
                }else{
                    status.setCode("100");
                    status.setDesc("Success");
                    authenticationResponse.setStatus(status);
                    authenticationResponse.setPermissions(getOverallUserPermission(user.getId()));
                    authenticationResponse.setUserId(user.getId()+"");
                    authenticationResponse.setEmail(user.getEmail());
                    authenticationResponse.setUserName(user.getUserName());
                    authenticationResponse.setPhoneMumber(user.getPhoneNumber());
                    authenticationResponse.setFname(user.getFname());
                    authenticationResponse.setOtherNames(user.getOtherNames());
                    
                }
            }
            catch(NoResultException ex){
                status.setCode("404");
                status.setDesc(ex.getMessage());
                status.setDesc("Wrong creadentials");
                authenticationResponse.setStatus(status);
            }
            return authenticationResponse;
        }
        catch(Exception ex){
            status.setCode("404");
            status.setDesc(ex.getMessage());
            status.setDesc("Wrong creadentials");
            authenticationResponse.setStatus(status);
            return authenticationResponse;
        }
    }
}