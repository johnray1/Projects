/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Company;
import com.oltranz.engenpayfuel.models.ResultObject;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class CompanyManager {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public ResultObject createCompany(Company newCompany){
        //set the returntype resultset object
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Company.class);
        
        Company company=new Company();
        company.setName(newCompany.getName());
        company.setDescr(newCompany.getDescr());
        em.persist(company);
        em.flush();
        
        resultObject.setObject(company);
        resultObject.setMessage("New Company successfully created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getCompanyList(){
        
        List<Company> companyList=(List<Company>)em.createNamedQuery("Company.findAll").getResultList();
        
        ResultObject resultObject= new ResultObject();
        
        resultObject.setObject(companyList);
        resultObject.setObjectClass(Company.class);
        if(companyList.isEmpty()){
            resultObject.setMessage("There are no Company in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setMessage(companyList.size()+" Company were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    public ResultObject getCompanyByItsId(int companyId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Company.class);
        resultObject.setObject(null);
        Company company=em.find(Company.class,companyId);
        
        if(company!=null){
            resultObject.setMessage("Company Well found and returned!");
            resultObject.setObject(company);
            resultObject.setStatusCode(100);
            return resultObject;
        }else{
            resultObject.setMessage("Company with given Id not found!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
    }
    
}
