/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.ignite.bean;

import com.oltranz.ignite.entities.Liquidation;
import com.oltranz.ignite.library.Common;
import com.oltranz.ignite.model.LiquidationEditModel;
import com.oltranz.ignite.model.ResultObject;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JohnRay
 */
@Stateless
public class LiquidationManager {
    
    @PersistenceContext(unitName = "IgnitePU")
    private EntityManager em;
    
    public ResultObject createLiquidation(LiquidationEditModel lqe){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Liquidation.class);
        
        
        Liquidation liquidation=new Liquidation();
        liquidation.setId(Long.toString(Common.shared.generateId()));
        liquidation.setTelcoName(lqe.getTelcoName());
        liquidation.setAmount(lqe.getAmount());
        liquidation.setLiquidationTime(null);
        liquidation.setCreationTime(new Date());
        liquidation.setCreatedBy(lqe.getCreatedBy());
        liquidation.setReference(lqe.getReference());
        liquidation.setReferenceType(lqe.getReferenceType());
        em.persist(liquidation);
        em.flush();
        
        resultObject.setObject(liquidation);
        resultObject.setMessage("Liquidation well created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editLiquidation(LiquidationEditModel lqe){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Liquidation.class);
        
        Liquidation liquidation=em.find(Liquidation.class,lqe.getId());
        if(liquidation==null){
            resultObject.setObject(null);
            resultObject.setMessage("Liquidation  Is Not Available");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        liquidation.setTelcoName(lqe.getTelcoName());
        liquidation.setAmount(lqe.getAmount());
        liquidation.setLiquidationTime(null);
        liquidation.setCreationTime(new Date());
        liquidation.setCreatedBy(lqe.getCreatedBy());
        liquidation.setReference(lqe.getReference());
        liquidation.setReferenceType(lqe.getReferenceType());
        em.merge(liquidation);
        
        resultObject.setObject(liquidation);
        resultObject.setMessage("Liquidation well Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject getLiquidation(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Liquidation.class);
        
        List<Liquidation> liquidationList=(List<Liquidation>)em.createQuery("select l from Liquidation l").getResultList();
        
        
        resultObject.setObject(liquidationList);
        resultObject.setMessage(liquidationList.size()+" Liquidation found");
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    
    
}
