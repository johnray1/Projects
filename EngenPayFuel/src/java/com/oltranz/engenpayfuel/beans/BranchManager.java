/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleForBranch;
import com.oltranz.engenpayfuel.entities.RoleForBranchPK;
import com.oltranz.engenpayfuel.models.ResultObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class BranchManager {
    
    @PersistenceContext
    private EntityManager em;
    @EJB
            UserManager userManager;
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    public ResultObject createBranch(Branch newBranch) {
        
        
        Branch branch =new Branch();
        branch.setName(newBranch.getName());
        branch.setDescr(newBranch.getDescr());
        branch.setCompanyId(1);
        em.persist(branch);
        em.flush();
        
        commonFunctionEjb.addDefaultProductPriceToBranchManager(branch);
        
        //after creating the branch, a role thet represent this branch
        //must be also created in userRoleForBranch
        
        //So we create first an new user role
        Role role= new Role();
        role.setDescr("A role that Branch ['"+branch.getName()+"'] Staff are considered to have");
        role.setName("Branch ['"+ branch.getName()+"'] Staff");
        role.setTypeId(2); //as per system initialized roleType 2 is for Branch staff
        em.persist(role);
        em.flush();
        
        //after persist userrole we set both primary keys in user_role_for_branch table
        RoleForBranchPK roleForBranchPK=new RoleForBranchPK(role.getRoleId(),branch.getBranchId());
        RoleForBranch userRoleForBranch=new RoleForBranch(roleForBranchPK);
        em.persist(userRoleForBranch);
        em.flush();
        
        //set the returntype resultset object
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("New branch successfully created and its staff role created");
        resultObject.setObject(branch);
        resultObject.setObjectClass(Branch.class);
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject editBranch(Branch editBranch){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Branch.class);
        Branch branch=em.find(Branch.class, editBranch.getBranchId());
        
        
        if(branch==null){
            
            resultObject.setObject(null);
            resultObject.setMessage("No Branch with id of the given one is found!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        branch.setName(editBranch.getName());
        branch.setDescr(editBranch.getDescr());
        em.merge(branch);
        em.flush();
        
        
        RoleForBranch roleForBranch=(RoleForBranch) em.createQuery("SELECT u FROM RoleForBranch u WHERE u.roleForBranchPK.branchId = :branchId").setParameter("branchId", branch.getBranchId()).getSingleResult();
        
        if(roleForBranch==null){
            resultObject.setObject(null);
            resultObject.setMessage("No UserRoleForBranch Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Role userRole=em.find(Role.class, roleForBranch.getRoleForBranchPK().getRoleId());
        if(userRole==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Role Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        userRole.setDescr("A role that Branch ['"+branch.getName()+"'] Staff are considered to have");
        userRole.setName("Branch ['"+ branch.getName()+"'] Staff");
        em.merge(userRole);
        
        resultObject.setMessage("Branch successfully updated");
        resultObject.setObject(branch);
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    public ResultObject getBranchList(){
        
        ResultObject resultObject= new ResultObject();
        List<Branch> branchesList=(List<Branch>)em.createQuery("SELECT b FROM Branch b").getResultList();
        
        resultObject.setObject(branchesList);
        resultObject.setObjectClass(Branch.class);
        
        if(branchesList.isEmpty()){
            resultObject.setMessage("There are no Branch in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(branchesList.size()+" Branches were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
        
    }
    
    public ResultObject getBranchListById(Integer branchId){
        
        ResultObject resultObject= new ResultObject();
        List<Branch> branchesList=(List<Branch>)em.createQuery("SELECT b FROM Branch b WHERE b.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        
        resultObject.setObject(branchesList);
        resultObject.setObjectClass(Branch.class);
        
        if(branchesList.isEmpty()){
            resultObject.setMessage("There are no Branch in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(branchesList.size()+" Branches were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
        
    }
    
    public ResultObject getBranchByItsId(Integer branchId){
        
        ResultObject resultObject=new ResultObject();
        Branch branch=em.find(Branch.class,branchId);
        
        if(branch!=null){
            resultObject.setMessage("Branch Well found and returned!");
            resultObject.setObject(branch);
            resultObject.setObjectClass(Branch.class);
            resultObject.setStatusCode(100);
            return resultObject;
        }else{
            resultObject.setMessage("Branch with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Branch.class);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    public ResultObject deleteBranch(Integer branchId){
        
        ResultObject resultObject=new ResultObject();
        Branch branch2Delete=em.find(Branch.class, branchId);
        
        if(branch2Delete==null){
            resultObject.setMessage("Branch with given Id not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        Branch branch=em.find(Branch.class, branch2Delete.getBranchId());
        branch.setStatus(branch.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("--yyyy-MM-dd/HH:mm");
        Date deletionTIme=new Date();
        branch.setName(branch.getName()+ sdf.format(deletionTIme));
        em.merge(branch);
        
        
        resultObject.setMessage("Branch successfully sent to dustbin");
        resultObject.setObject(branch);
        resultObject.setObjectClass(Branch.class);
        return resultObject;
    }
    
    public ResultObject deleteBranch(Branch branch2Delete){
        ResultObject resultObject=new ResultObject();
        Branch branch=em.find(Branch.class, branch2Delete.getBranchId());
        
        if(branch==null){
            resultObject.setMessage("Branch with given Id not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        branch.setStatus(branch.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("--yyyy-MM-dd/HH:mm");
        Date deletionTIme=new Date();
        branch.setName(branch.getName()+ sdf.format(deletionTIme));
        em.merge(branch);
        
        
        resultObject.setMessage("Branch successfully sent to dustbin");
        resultObject.setObject(branch);
        resultObject.setObjectClass(Branch.class);
        return resultObject;
    }
    
    

//------------------------------------------------web------------------------------------------------------------------
    
    
    public ResultObject getBranchList(Integer branchId){
        
        ResultObject resultObject;
        if(branchId==0){
            resultObject=getBranchList();
        }
        else{
            resultObject=getBranchListById(branchId);
        }
        return resultObject;
    }
    
    
    
    
    
}
