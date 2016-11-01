/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Log;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleForBranch;
import com.oltranz.engenpayfuel.entities.RoleForUser;
import com.oltranz.engenpayfuel.entities.RoleForUserPK;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.library.Common;
import com.oltranz.engenpayfuel.library.RandomPin;
import com.oltranz.engenpayfuel.models.ResultObject;
import com.oltranz.engenpayfuel.models.RoleDetailsModel;
import com.oltranz.engenpayfuel.models.RoleForBranchModel;
import com.oltranz.engenpayfuel.models.RoleForUserModel;
import com.oltranz.engenpayfuel.models.UserCreateModel;
import com.oltranz.engenpayfuel.models.UserDetailsModel;
import com.oltranz.engenpayfuel.models.UserEditModel;
import com.oltranz.engenpayfuel.models.UserWebCreateModel;
import com.oltranz.engenpayfuel.models.UserWebEditModel;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class UserManager {
    
    @PersistenceContext
    private  EntityManager em;
    
    
    
    @EJB
            InitialData InitialDataEJB;
    
    @EJB
            BranchManager branchManager;
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    
    public ResultObject createUser(UserCreateModel newUser){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(UserDetailsModel.class);
        
        //action user id means the id which is available in user table having all acccess to create user
        Integer actionBy=newUser.getActionUserId();
        
        try{
            Boolean isvalid = true;
            
            
            if(!newUser.getPassword().equals(newUser.getRepeatPassword())){
                resultObject.setObject(null);
                resultObject.setMessage("Password not repeated correctly");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            User actionUser=em.find(User.class, actionBy);
            if(actionUser==null){
                resultObject.setObject(null);
                resultObject.setMessage("Action user not found");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            if(Common.shared.GetBit(Common.shared.hexStringToByteArray(actionUser.getPermissions()), 1)==0){
                resultObject.setObject(null);
                resultObject.setMessage("User doesn't have the right to create new user");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            
            //check if user with same first name and other name is exist in database
            Query query = em.createQuery("select u from User u where UPPER(u.fname)=:fname and UPPER(u.otherNames)=:otherNames");
            query.setParameter("fname",newUser.getFname().toUpperCase());
            query.setParameter("otherNames", newUser.getOtherNames().toUpperCase());
            
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setObject(null);
                resultObject.setMessage("- User account with same names exists already! -");
                resultObject.setStatusCode(500);
            }
            
            //check if user with same email is exist in database
            query = em.createQuery("select u from User u where UPPER(u.email)=:email");
            query.setParameter("email",newUser.getEmail().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setObject(null);
                resultObject.setMessage("- user account with same email exists already! -");
                resultObject.setStatusCode(500);
            }
            
            
            if (!isvalid)
                return resultObject;
            //-----------if available checking finish-----------------
            
            User u = new User();
            u.setDetails(newUser.getDetails());
            u.setEmail(newUser.getEmail().trim());
            u.setFname(newUser.getFname());
            u.setOtherNames(newUser.getOtherNames());
            u.setPassword(Common.shared.get_SHA_512_SecurePassword(newUser.getPassword(), "726"));
            u.setPin(RandomPin.createPin());
            u.setStatus(7);
            u.setGender(newUser.getGender());
            u.setPhoneNumber(newUser.getPhoneNumber());
            
            
            byte[] permissions=Common.shared.hexStringToByteArray(u.getPermissions());
            Common.shared.setBit(permissions, 1, 0); // set to not deleted
            Common.shared.setBit(permissions, 2, 0); // set to not disabled
            u.setPermissions(Common.shared.byteArrayToHexString(permissions));
            
            em.persist(u);
            em.flush();
            
            //call the function getUserDetails and set user object
            resultObject=getUserDetails(u);
            
            //set logs
            Log log=new Log();
            log.setActionId(1);
            log.setActionName("Create New User");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setObjectId(u.getUserId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(actionBy);
            User actionByUser=em.find(User.class,actionBy);
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            InetAddress IP=InetAddress.getLocalHost();
            log.setIp(IP.toString());
            log.setSource("WEB");
            em.persist(log);
            
            resultObject.setMessage("User well created");
            
            return resultObject;
            
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            return resultObject;
        }
        
    }
    
    public ResultObject updateUser(UserEditModel editUser){
        
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
            
            //we check the permisson if it have access to edit
            if(Common.shared.GetBit(Common.shared.hexStringToByteArray(actionUser.getPermissions()), 2)==0){
                resultObject.setMessage("User doesn't have the right to update a user");
                return resultObject;
            }
            
            //we check if names are already given to a another user id
            Query query = em.createQuery("select u from User u where UPPER(u.fname)=:fname and UPPER(u.otherNames)=:otherNames and u.userId!=:userId");
            query.setParameter("fname",editUser.getFname().toUpperCase());
            query.setParameter("otherNames", editUser.getOtherNames().toUpperCase());
            query.setParameter("userId",editUser.getUserId());
            
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setMessage("- User account with same names exists already! -");
            }
            
            query = em.createQuery("select u from User u where UPPER(u.email)=:email and u.userId!=:userId");
            query.setParameter("userId",editUser.getUserId());
            query.setParameter("email",editUser.getEmail().toUpperCase());
            
            if (query.getResultList().size()>0)
            {
                isvalid = false;
                resultObject.setMessage("- user account with same email exists already! -");
            }
            
            if (!isvalid)
                return resultObject;
            
            User u=em.find(User.class, editUser.getUserId());
            u.setDetails(editUser.getDetails());
            u.setEmail(editUser.getEmail().trim());
            u.setFname(editUser.getFname());
            u.setOtherNames(editUser.getOtherNames());
            u.setGender(editUser.getGender());
            u.setPhoneNumber(editUser.getPhoneNumber());
            
            em.merge(u);
            em.flush();
            
            //call the function getUserDetails and set user object
            resultObject=getUserDetails(u);
            
            
            
            
            Log log=new Log();
            log.setActionName("Update user");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setActionId(2);
            log.setObjectId(u.getUserId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(actionBy);
            User actionByUser=em.find(User.class,actionBy);
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            log.setSource("WEB");
            InetAddress IP=InetAddress.getLocalHost();
            log.setIp(IP.toString());
            em.persist(log);
            
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
    
    public ResultObject authenticateWebUser(String email, String password){
        
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObject(null);
        resultObject.setObjectClass(UserDetailsModel.class);
        try
        {
            User user;
            try{
                password =Common.shared.get_SHA_512_SecurePassword(password, "726");
                Query query= em.createQuery("select u from User u where UPPER(u.email)=:email and UPPER(u.password)=:password");
                query.setParameter("email", email.toUpperCase());
                query.setParameter("password", password.toUpperCase());
                
                user=(User) query.getSingleResult();
            }
            catch(NoResultException ex){
                resultObject.setObject(null);
                resultObject.setObjectClass(UserDetailsModel.class);
                resultObject.setMessage("Unsuccessful Authentication");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            
            UserDetailsModel userDetails=(UserDetailsModel)getUserDetails(user.getUserId()).getObject();
            
            //set logs
            Log log=new Log();
            log.setActionId(9);
            log.setActionName("Login As Web User");
            log.setActionResult(0);
            log.setDatetime(new Date());
            log.setObjectId(userDetails.getUserId()); //user object id =1
            log.setObjectName("user");
            log.setUserId(userDetails.getUserId());
            User actionByUser=em.find(User.class,userDetails.getUserId());
            log.setUserName(actionByUser.getFname()+" "+actionByUser.getOtherNames());
            log.setSource("WEB");
            InetAddress IP=InetAddress.getLocalHost();
            log.setIp(IP.toString());
            em.persist(log);
            
            resultObject.setObject(userDetails);
            resultObject.setMessage("Successfull Authentification");
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        
        catch (Exception e)
        {
            
            resultObject.setMessage(e.getMessage());
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        
    }
    
    public ResultObject getAllUsers(){
        
        ResultObject resultObject=new ResultObject();
        
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        
        List<User> usersList=em.createQuery(cq).getResultList();
        List<UserDetailsModel> usersWithDetailsList=new ArrayList();
        
        for(User user: usersList){
            UserDetailsModel u=(UserDetailsModel) getUserDetails(user.getUserId()).getObject();
            usersWithDetailsList.add(u);
        }
        
        resultObject.setObject(usersWithDetailsList);
        resultObject.setObjectClass(User.class);
        
        if(usersWithDetailsList.size()>0){
            resultObject.setMessage("User list well found");
            resultObject.setStatusCode(100);
        }
        else{
            resultObject.setMessage("The are no users in the databases");
            resultObject.setStatusCode(500);
        }
        
        
        return resultObject;
    }
    
    public ResultObject getAllUsersByBranchId(int branchId){
        
        ResultObject resultObject=new ResultObject();
        
        Branch branch=em.find(Branch.class,branchId);
        if(branch==null){
            resultObject.setObjectClass(User.class);
            resultObject.setObject(null);
            resultObject.setMessage("No Branch with id of the given one is found!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //get Branch Role
        RoleForBranch roleForBranch=(RoleForBranch) em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.branchId = :branchId").setParameter("branchId", branchId).getSingleResult();
        
        //get user assign to that role
        List<RoleForUser> usersInRoleList=em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.roleId = :roleId").setParameter("roleId", roleForBranch.getRoleForBranchPK().getRoleId()).getResultList();
        
        List<UserDetailsModel> usersWithDetailsList=new ArrayList();
        
        for(RoleForUser user: usersInRoleList){
            UserDetailsModel u=(UserDetailsModel) getUserDetails(user.getRoleForUserPK().getUserId()).getObject();
            usersWithDetailsList.add(u);
        }
        
        resultObject.setObject(usersWithDetailsList);
        resultObject.setObjectClass(User.class);
        
        if(usersWithDetailsList.size()>0){
            resultObject.setMessage(usersWithDetailsList.size()+"  Users  found");
            resultObject.setStatusCode(100);
        }
        else{
            resultObject.setMessage("The are no users in the databases");
            resultObject.setStatusCode(500);
        }
        
        
        return resultObject;
    }
    
    public ResultObject getAllUsers(int userId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(User.class);
        
        //check if user is available
        User us=em.find(User.class,userId);
        if(us==null){
            resultObject.setMessage("User is not Created To Access The Branch");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        List<User> usersList=em.createQuery(cq).getResultList();
        List<UserDetailsModel> usersWithDetailsList=new ArrayList();
        for(User user: usersList){
            UserDetailsModel u=(UserDetailsModel) getUserDetails(user.getUserId()).getObject();
            usersWithDetailsList.add(u);
        }
        
        //if user id 1 bring all branch
        if(us.getUserId()==1){
            resultObject.setObject(usersWithDetailsList);
            resultObject.setMessage(usersWithDetailsList.size()+" Users were found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        
        //get the user details,roles and its branch
        UserDetailsModel userDetails= (UserDetailsModel)getUserDetails(us.getUserId()).getObject();
        List<Role> roles=userDetails.getRoles();
        Integer branchId=-1;
        for(Role r: roles){
            
            if(r.getTypeId()==1){
                resultObject.setObject(usersWithDetailsList);
                resultObject.setMessage(usersWithDetailsList.size()+" Users were found");
                resultObject.setStatusCode(100);
                return resultObject;
            }
            
            if(r.getTypeId()==2){
                
                List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                if(list.size()>0)
                    
                    branchId=list.get(0).getRoleForBranchPK().getBranchId();
            }
        }
        
        if(branchId==-1){
            resultObject.setObject(null);
            resultObject.setMessage("You are not a staff of any branch");
            resultObject.setObjectClass(Branch.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
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
    
    public ResultObject getUser(Integer userId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(User.class);
        
        User user=em.find(User.class,userId);
        if(user!=null){
            resultObject.setMessage("User Well found and returned!");
            resultObject.setObject(user);
            return resultObject;
        }else{
            resultObject.setMessage("User with given Id not found!");
            resultObject.setObject(null);
            return resultObject;
        }
    }
    
    public ResultObject getUserDetails(User user){
        
        ResultObject resultObject=new ResultObject();
        
        User u =em.find(User.class, user.getUserId());
        if(u==null){
            resultObject.setMessage("User with given userId not found");
            resultObject.setObject(null);
            resultObject.setObjectClass(UserDetailsModel.class);
            return resultObject;
        }
        
        //find user branchId
        Integer branchId=-1;
        String branchName="No Branch";
        
        if(user.getUserId()==1){
            branchId=0;
            branchName="HQ";
        }
        else{
            List<RoleForUser> ulist = (List<RoleForUser>)em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.userId = :userId").setParameter("userId", u.getUserId()).getResultList();
            
            if(ulist.size()>0){
                
                Integer roleId=ulist.get(0).getRoleForUserPK().getRoleId();
                
                List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", roleId).getResultList();
                if(list.size()>0){
                    
                    branchId=list.get(0).getRoleForBranchPK().getBranchId();
                    branchName=commonFunctionEjb.getBranchName(branchId).getName();
                }
            }
            
        }
        
        UserDetailsModel userDetails=new UserDetailsModel();
        userDetails.setDetails(u.getDetails());
        userDetails.setEmail(u.getEmail());
        userDetails.setFname(u.getFname());
        userDetails.setGender(u.getGender());
        userDetails.setOtherNames(u.getOtherNames());
        userDetails.setPhoneNumber(u.getPhoneNumber());
        userDetails.setPermissions(u.getPermissions());
        userDetails.setUserId(u.getUserId());
        userDetails.setBranchId(branchId);
        userDetails.setBranchName(branchName);
        
        //get list of roles this is user has
        Object object =getUserRoles(userDetails.getUserId()).getObject();
        
        userDetails.setRoles((List<Role>)object);
        
        resultObject.setMessage("User well found!");
        resultObject.setObject(userDetails);
        resultObject.setObjectClass(UserDetailsModel.class);
        return resultObject;
    }
    
    public ResultObject getUserRoles(Integer userId){
        
        ResultObject resultObject;
        
        resultObject=getUser(userId);
        
        if(resultObject.getObject()==null){
            return resultObject;
        }
        
        Query query=em.createQuery("select r from Role r join RoleForUser ru on r.roleId=ru.roleForUserPK.roleId where ru.roleForUserPK.userId=:userId");
        query.setParameter("userId", userId);
        Object object=query.getResultList();
        
        List<Role> rolesList=(List<Role>)object;
        resultObject.setObject(rolesList);
        resultObject.setObjectClass(Role.class);
        
        if(rolesList.isEmpty()){
            resultObject.setMessage("This user is not in any Role");
        }
        else{
            resultObject.setMessage(rolesList.size()+"Role found for this users!");
        }
        
        return resultObject;
    }
    
    public ResultObject addUserToABranchRole(Integer userId,Integer branchId){
        
        //first check if user id is available
        ResultObject resultObject=getUser(userId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        //first check if branch id is available
        resultObject=branchManager.getBranchByItsId(branchId);
        if(resultObject.getObject()==null)
            return resultObject;
        
        //CHECK IF A ROLE FOR BRANCH IS CREATED
        RoleForBranch roleForBranch=(RoleForBranch)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.branchId = :branchId").setParameter("branchId", branchId).getSingleResult();
        if(roleForBranch==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: Role for this branch's staff is missing in RolesForBranch");
            resultObject.setObjectClass(RoleForUser.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        List<RoleForUser> roleForUserList=em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.userId = :userId").setParameter("userId", userId).getResultList();
        if(roleForUserList.size()>0){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: User is already assigned to a role,To Add A new Role Remove The Current Assigned Role");
            resultObject.setObjectClass(RoleForUser.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        
        //IF A ROLE FOR BRANCH IS CREATED THEN GET ITS ROLE ID AND SET USER TO THAT ROLE ID
        Role role=em.find(Role.class, roleForBranch.getRoleForBranchPK().getRoleId());
        if(role==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: Role for this branch's staff exist in RolesForBranch but  is missing in Roles");
            resultObject.setObjectClass(RoleForUser.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        RoleForUserPK roleForUserPK=new RoleForUserPK(roleForBranch.getRoleForBranchPK().getRoleId(),userId);
        RoleForUser roleForUser=new RoleForUser();
        roleForUser.setRoleForUserPK(roleForUserPK);
        em.persist(roleForUser);
        
        resultObject.setObject(getUserDetails(userId).getObject());
        resultObject.setMessage("User well added in "+role.getName());
        resultObject.setStatusCode(100);
        resultObject.setObjectClass(RoleForUser.class);
        return resultObject;
    }
    
    public ResultObject addUser2ARole(Integer userId,Integer roleId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(RoleForUser.class);
        
        //check if its duplicate entry
        RoleForUserPK checkRoleForUserPK=new RoleForUserPK();
        checkRoleForUserPK.setUserId(userId);
        checkRoleForUserPK.setRoleId(roleId);
        RoleForUser checkRoleForUser=em.find(RoleForUser.class, checkRoleForUserPK);
        if(checkRoleForUser!=null){
            resultObject.setObject(null);
            resultObject.setMessage("Duplicate Entry");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //check if user is available
        User user=em.find(User.class, userId);
        if(user==null){
            resultObject.setObject(null);
            resultObject.setMessage("user is not created");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //check if role is available
        Role role=em.find(Role.class, roleId);
        if(role==null){
            resultObject.setObject(null);
            resultObject.setMessage("role is not created");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        int newTypeId=getRoleTypeId(roleId);
        int typeId=getUserTypeId(userId);
        
        if(typeId==newTypeId){
            
            resultObject.setObject(null);
            resultObject.setMessage("User Is already Added To Same Role Type");
            resultObject.setStatusCode(500);
            resultObject.setObjectClass(RoleForUser.class);
            return resultObject;
        }
        
        RoleForUserPK roleForUserPK=new RoleForUserPK(roleId,userId);
        RoleForUser roleForUser=new RoleForUser();
        roleForUser.setRoleForUserPK(roleForUserPK);
        em.persist(roleForUser);
        
        resultObject.setObject(getUserDetails(userId).getObject());
        resultObject.setMessage("User well added in "+role.getName());
        resultObject.setStatusCode(100);
        resultObject.setObjectClass(RoleForUser.class);
        
        return resultObject;
        
    }
    
    public ResultObject removeUser4rmRole(int userId, int roleId){
        
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(RoleForUser.class);
        try
        {
            if (roleId == 1) //no user can be removed from the main group
            {
                resultObject.setMessage("The general member role can not be taken from any user!");
                resultObject.setObject(null);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            if (roleId == 2 && userId == 1)// the group with ID =1 is a built and cannot be removed from the list of administrators
            {
                resultObject.setMessage("The built in administrator cannot built removed from overall administrators!");
                resultObject.setObject(null);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            //get user name and role name using user id and role id
            User u=em.find(User.class, userId);
            Role role=em.find(Role.class,roleId);
            String userNames = u.getFname() + " " + u.getOtherNames();
            String roleName = role.getName();
            
            
            RoleForUserPK rupk= new RoleForUserPK();
            rupk.setRoleId(roleId);
            rupk.setUserId(userId);
            RoleForUser roleForUser = em.find(RoleForUser.class, rupk);
            if (roleForUser == null)
            {
                resultObject.setMessage("The user is not (no longer) a member of this group!");
                resultObject.setObject(null);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            em.remove(roleForUser);
            
            
            resultObject.setMessage("the role " + roleName + " of the user '" + userNames + "' is successfully removed");
            resultObject.setObject(roleForUser);
            resultObject.setStatusCode(100);
            return resultObject;
            
        }
        catch (Exception e)
        {
            resultObject.setMessage(e.getMessage());
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        
        
    }
    
    public ResultObject getAllRoles(){
        
        ResultObject resultObject=new ResultObject();;
        resultObject.setObjectClass(Role.class);
        
        
        
        List<Role> roleList=(List<Role>)em.createQuery("SELECT r FROM Role r").getResultList();
        if(roleList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("This user is not in any users Role");
            resultObject.setStatusCode(500);
        }
        resultObject.setObject(roleList);
        resultObject.setMessage(roleList.size()+" Role found for this users!");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getAllRolesForUser(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(RoleForUser.class);
        
        
        List<RoleForUserModel> roleForUserModelList=new ArrayList<>();
        
        RoleForUser roleForUser;
        List<RoleForUser> roleForUserList=(List<RoleForUser>)em.createQuery("SELECT u FROM RoleForUser u").getResultList();
        
        if(roleForUserList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There is no user with role");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=roleForUserList.iterator();
            while(i.hasNext()){
                roleForUser=(RoleForUser) i.next();
                
                RoleForUserModel roleForUserModel=new RoleForUserModel();
                roleForUserModel.setRoleId(roleForUser.getRoleForUserPK().getRoleId());
                roleForUserModel.setUserId(roleForUser.getRoleForUserPK().getUserId());
                Role role=commonFunctionEjb.getRoleName(roleForUser.getRoleForUserPK().getRoleId());
                roleForUserModel.setRoleName(role.getName());
                User user=commonFunctionEjb.getUserName(roleForUser.getRoleForUserPK().getUserId());
                roleForUserModel.setUserName(user.getFname());
                
                roleForUserModelList.add(roleForUserModel);
            }
            
            resultObject.setObject(roleForUserModelList);
            resultObject.setMessage(roleForUserModelList.size()+"role found for this users!");
            resultObject.setStatusCode(100);
        }
        return resultObject;
    }
    
    public ResultObject getAllRolesForBranch(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(RoleForBranch.class);
        
        List<RoleForBranchModel> roleForBranchModelList=new ArrayList<>();
        
        RoleForBranch roleForBranch;
        List<RoleForBranch> roleForBranchList=(List<RoleForBranch>)em.createQuery("SELECT u FROM RoleForBranch u").getResultList();
        
        if(roleForBranchList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There is no branch with role");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=roleForBranchList.iterator();
            while(i.hasNext()){
                roleForBranch=(RoleForBranch) i.next();
                
                RoleForBranchModel roleForBranchModel=new RoleForBranchModel();
                roleForBranchModel.setRoleId(roleForBranch.getRoleForBranchPK().getRoleId());
                roleForBranchModel.setBranchId(roleForBranch.getRoleForBranchPK().getBranchId());
                Role role=commonFunctionEjb.getRoleName(roleForBranch.getRoleForBranchPK().getRoleId());
                roleForBranchModel.setRoleName(role.getName());
                Branch branch=commonFunctionEjb.getBranchName(roleForBranch.getRoleForBranchPK().getBranchId());
                roleForBranchModel.setBranchName(branch.getName());
                
                roleForBranchModelList.add(roleForBranchModel);
            }
            resultObject.setObject(roleForBranchModelList);
            resultObject.setMessage(roleForBranchModelList.size()+"role found for this branch!");
            resultObject.setStatusCode(100);
        }
        return resultObject;
    }
    
    public ResultObject getAllUserOfARole(int roleId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(User.class);
        
        //IF A ROLE  IS CREATED THEN GET ITS ROLE ID AND SET USER TO THAT ROLE ID
        Role role=em.find(Role.class,roleId);
        if(role==null){
            resultObject.setObject(null);
            resultObject.setMessage("Fatal error: Role Are Missing");
            resultObject.setObjectClass(RoleForUser.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        List<UserDetailsModel> userDetailsModelList=new ArrayList<>();
        RoleForUser roleForUser;
        List<RoleForUser> roleForUserList=(List<RoleForUser>)em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.roleId = :roleId").setParameter("roleId", role.getRoleId()).getResultList();
        if(roleForUserList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("This role is not available in RoleForUserUser");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=roleForUserList.iterator();
            while(i.hasNext()){
                roleForUser=(RoleForUser) i.next();
                int userId=roleForUser.getRoleForUserPK().getUserId();
                
                UserDetailsModel userDetailsModel;
                userDetailsModel=(UserDetailsModel) getUserDetails(userId).getObject();
                
                userDetailsModelList.add(userDetailsModel);
            }
            
            resultObject.setObject(userDetailsModelList);
            resultObject.setMessage(userDetailsModelList.size()+"role found for this users!");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject getAllUserData(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(User.class);
        
        List<User> userList=(List<User>)em.createQuery("SELECT u FROM User u").getResultList();
        
        if(userList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There is no user");
            resultObject.setStatusCode(500);
        }
        
        resultObject.setObject(userList);
        resultObject.setMessage(userList.size()+"user found for this branch!");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public int getUserTypeId(int userId){
        
        RoleForUser roleForUser=new RoleForUser();
        List<RoleForUser> roleForUserList=(List<RoleForUser>)em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.userId = :userId").setParameter("userId", userId).getResultList();
        if(roleForUserList.isEmpty()){
            return 0;
        }
        else{
            Iterator i=roleForUserList.iterator();
            while(i.hasNext()){
                roleForUser=(RoleForUser) i.next();
            }
            int typeId=getRoleTypeId(roleForUser.getRoleForUserPK().getRoleId());
            return typeId;
        }
    }
    
    public int getRoleTypeId(int roleId){
        
        Role role=new Role();
        List<Role> roleList=(List<Role>)em.createQuery("SELECT r FROM Role r WHERE r.roleId = :roleId").setParameter("roleId", roleId).getResultList();
        if(roleList.isEmpty()){
            return 0;
        }
        else{
            Iterator i=roleList.iterator();
            while(i.hasNext()){
                role=(Role) i.next();
            }
            return role.getTypeId();
        }
    }
    
    public ResultObject setUserPermisson(int userId,String permissons){
        
        ResultObject resultObject=new ResultObject();
        
        resultObject.setObjectClass(UserDetailsModel.class);
        
        User user=em.find(User.class, userId);
        if (user==null){
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            resultObject.setMessage("user not found");
            return resultObject;
        }
        else{
            user.setPermissions(permissons);
            em.merge(user);
            em.flush();
            resultObject.setObject(getUserDetails(user).getObject());
            resultObject.setStatusCode(100);
            resultObject.setMessage("user permisson updated");
            return resultObject;
        }
        
        
    }
    
    public ResultObject getRoleDetailsById(int roleId){
        
        ResultObject resultObject=new ResultObject();
        
        
        Role r =em.find(Role.class, roleId);
        if(r==null){
            resultObject.setMessage("Role with given roleId not found");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        RoleDetailsModel roleDetails=new RoleDetailsModel();
        roleDetails.setRoleId(r.getRoleId());
        roleDetails.setName(r.getName());
        roleDetails.setPermissions(r.getPermissions());
        roleDetails.setStatus(r.getStatus());
        roleDetails.setTypeId(r.getTypeId());
        roleDetails.setDescr(r.getDescr());
        resultObject=getRoleUsers(r.getRoleId());
        roleDetails.setUsers((List<User>) resultObject.getObject());
        
        
        resultObject.setObject(roleDetails);
        resultObject.setObjectClass(RoleDetailsModel.class);
        
        return resultObject;
    }
    
    public ResultObject getRoleUsers(Integer roleId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(User.class);
        
        List<User> userList=new ArrayList<>();
        RoleForUser roleForUser;
        List<RoleForUser>  roleForUserList=(List<RoleForUser>)em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.roleId = :roleId").setParameter("roleId", roleId).getResultList();
        Iterator i=roleForUserList.iterator();
        while(i.hasNext()){
            
            roleForUser=(RoleForUser) i.next();
            resultObject=getUser(roleForUser.getRoleForUserPK().getUserId());
            
            userList.add((User) resultObject.getObject());
        }
        
        resultObject.setObject(userList);
        return resultObject;
    }
    
    //------------------------------------------------web------------------------------------------------------------------
    
    
    public ResultObject getUserList(Integer branchId){
        
        ResultObject resultObject;
        if(branchId==0){
            resultObject=getAllUsers();
        }
        else{
            resultObject=getAllUsersByBranchId(branchId);
        }
        return resultObject;
    }
    
    public ResultObject createUserFromWeb(UserWebCreateModel ubc){
        
        ResultObject resultObject;
        
        UserCreateModel newUser=new UserCreateModel();
        newUser.setActionUserId(ubc.getActionUserId());
        newUser.setFname(ubc.getFname());
        newUser.setOtherNames(ubc.getOtherNames());
        newUser.setPassword(ubc.getPassword());
        newUser.setRepeatPassword(ubc.getRepeatPassword());
        newUser.setEmail(ubc.getEmail());
        newUser.setGender(ubc.getGender());
        newUser.setPhoneNumber(ubc.getPhoneNumber());
        newUser.setDetails(ubc.getDetails());
        
        resultObject=createUser(newUser);
        
        UserDetailsModel udm=(UserDetailsModel) resultObject.getObject();
        int userId=udm.getUserId();
        int branchRoleId=ubc.getBranchRoleId();
        
        if(branchRoleId!=0){
            resultObject=addUserToABranchRole(userId,branchRoleId);
            resultObject.setObjectClass(UserDetailsModel.class);
            return resultObject;
        }
        return resultObject;
    }
    
    
    public ResultObject editUserFromWeb(UserWebEditModel ube){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(UserDetailsModel.class);
        
        UserEditModel editUser =new UserEditModel();
        editUser.setActionUserId(ube.getActionUserId());
        editUser.setUserId(ube.getUserId());
        editUser.setFname(ube.getFname());
        editUser.setOtherNames(ube.getOtherNames());
        editUser.setPassword(ube.getPassword());
        editUser.setRepeatPassword(ube.getRepeatPassword());
        editUser.setEmail(ube.getEmail());
        editUser.setGender(ube.getGender());
        editUser.setPhoneNumber(ube.getPhoneNumber());
        editUser.setDetails(ube.getDetails());
        resultObject=updateUser(editUser);
        
        UserDetailsModel udm=(UserDetailsModel) resultObject.getObject();
        
        int userId=udm.getUserId();
        int branchId=ube.getBranchRoleId();
        
        List<RoleForUser> roleForUserList=em.createQuery("SELECT r FROM RoleForUser r WHERE r.roleForUserPK.userId = :userId").setParameter("userId", userId).getResultList();
        if(roleForUserList.isEmpty()){
            resultObject=addUserToABranchRole(userId,branchId);
            resultObject.setObjectClass(UserDetailsModel.class);
            return resultObject;
        }
        for(RoleForUser roleForUser:roleForUserList){
            em.remove(roleForUser);
        }
        
        if(branchId!=0){
            resultObject=addUserToABranchRole(userId,branchId);
            resultObject.setObjectClass(UserDetailsModel.class);
            return resultObject;
        }
        
        return getUserDetails(userId);
    }
    
    
}
