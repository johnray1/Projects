/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.models.ResultObject;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class TankManager {
    @PersistenceContext
    private EntityManager em;
    
    public ResultObject createTank(Tank newTank) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        Branch branch=em.find(Branch.class, newTank.getBranchId());
        if(branch==null){
            resultObject.setObject(null);
            resultObject.setMessage("BranchId is not created, to which we want to set our Tank");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Tank tank=new Tank();
        tank.setName(newTank.getName());
        tank.setBranchId(newTank.getBranchId());
        em.persist(tank);
        em.flush();
        
        resultObject.setObject(tank);
        resultObject.setMessage("New Tank successfully created ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject editTank(Tank editTank) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Tank.class);
        
        Tank tank=em.find(Tank.class, editTank.getTankId());
        if(tank==null){
            resultObject.setObject(null);
            resultObject.setMessage("No Tank with id of the given one is found!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        tank.setName(editTank.getName());
        tank.setBranchId(editTank.getBranchId());
        em.merge(tank);
        em.flush();
        
        resultObject.setObject(tank);
        resultObject.setMessage("New Tank successfully Updated ");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
}
