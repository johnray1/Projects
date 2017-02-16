/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcsvignettes.beans;

import com.oltranz.kvcsvignettes.entities.Vignette;
import com.oltranz.kvcsvignettes.models.DashboardItemTotals;
import com.oltranz.kvcsvignettes.models.ResultObject;
import com.oltranz.kvcsvignettes.models.VignetteEditModel;
import com.oltranz.kvcsvignettes.models.VignetteFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author JohnRay
 */
@Stateless
public class VignettesManager {
    
    @PersistenceContext(unitName = "KvcsVignettesPU")
    private EntityManager em;
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public ResultObject createVignette(VignetteEditModel newVignette){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Vignette.class);
        
        Vignette checkVignette=em.find(Vignette.class,newVignette.getSerialNo()+newVignette.getPlateNo());
        if(checkVignette!=null){
            resultObject.setObject(checkVignette);
            resultObject.setMessage("Vignette  Is Already Created With Same Serial No");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        Vignette vignette=new Vignette();
        vignette.setId(newVignette.getSerialNo()+newVignette.getPlateNo());
        vignette.setfName(newVignette.getfName());
        vignette.setOtherName(newVignette.getOtherName());
        vignette.setPhoneNumber(newVignette.getPhoneNumber());
        vignette.setSerialNo(newVignette.getSerialNo());
        vignette.setPlateNo(newVignette.getPlateNo());
        vignette.setAmount(newVignette.getAmount());
        vignette.setCategory(newVignette.getCategory());
        try{
            vignette.setIssueTime(sdf.parse(newVignette.getIssueTime()));
            vignette.setExpireTime(sdf.parse(newVignette.getExpireTime()));
        }
        catch(Exception ex){
            vignette.setIssueTime(new Date());
            vignette.setExpireTime(new Date());
        }
        vignette.setCreatedBy(newVignette.getCreatedBy());
        em.persist(vignette);
        em.flush();
        
        resultObject.setObject(vignette);
        resultObject.setMessage("Vignette well created");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject editVignette(VignetteEditModel editVignette){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Vignette.class);
        
        Vignette vignette=em.find(Vignette.class,editVignette.getId());
        if(vignette==null){
            
            resultObject.setMessage("Vignette  Not Found");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        vignette.setfName(editVignette.getfName());
        vignette.setOtherName(editVignette.getOtherName());
        vignette.setPhoneNumber(editVignette.getPhoneNumber());
        vignette.setSerialNo(editVignette.getSerialNo());
        vignette.setPlateNo(editVignette.getPlateNo());
        vignette.setAmount(editVignette.getAmount());
        vignette.setCategory(editVignette.getCategory());
        try{
            vignette.setIssueTime(sdf.parse(editVignette.getIssueTime()));
            vignette.setExpireTime(sdf.parse(editVignette.getExpireTime()));
        }
        catch(Exception ex){
            vignette.setIssueTime(new Date());
            vignette.setExpireTime(new Date());
        }
        vignette.setCreatedBy(editVignette.getCreatedBy());
        em.merge(vignette);
        
        resultObject.setObject(vignette);
        resultObject.setMessage("Vignette Well Updated");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    public ResultObject getVignette(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Vignette.class);
        
        List<Vignette> vignetteList=(List<Vignette>)em.createQuery("SELECT v FROM Vignette v").getResultList();
        
        if(vignetteList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Vignette in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setObject(vignetteList);
            resultObject.setMessage(vignetteList.size()+" Vignette were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
        
    }
    
    
    public ResultObject getVignetteFilteredList(VignetteFilter filter){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Vignette.class);
        
        String Qstr="SELECT v FROM Vignette v WHERE v.id IS NOT NULL";
        
        if(filter.getPeriodFrom()!=null)
            Qstr+=" and v.issueTime>=:issueTime";
        
        if(filter.getPeriodTo()!=null)
            Qstr+=" and v.expireTime<=:expireTime ";
        
        if(filter.getPlateNo()!=null)
            Qstr+=" and v.plateNo=:plateNo ";
        
        if(filter.getCreatedBy()!=null)
            Qstr+=" and v.createdBy=:createdBy ";
        
        Query q= em.createQuery(Qstr);
        
        if(filter.getPeriodFrom()!=null){
            try{
                q.setParameter("issueTime",sdf.parse(filter.getPeriodFrom()),TemporalType.TIMESTAMP);
            }catch(Exception ex){
                q.setParameter("issueTime",new Date(),TemporalType.TIMESTAMP);
            }
        }
        
        if(filter.getPeriodTo()!=null){
            try{
                q.setParameter("expireTime",sdf.parse(filter.getPeriodTo()),TemporalType.TIMESTAMP);
            }catch(Exception ex){
                q.setParameter("expireTime",new Date(),TemporalType.TIMESTAMP);
            }
        }
        
        if(filter.getPlateNo()!=null)
            q.setParameter("plateNo", filter.getPlateNo());
        
        if(filter.getCreatedBy()!=null)
            q.setParameter("createdBy", filter.getCreatedBy());
        
        
        List<Vignette> vignetteList=(List<Vignette>)q.getResultList();
        
        if(vignetteList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Vignette in the system");
            resultObject.setStatusCode(500);
        }
        else{
            resultObject.setObject(vignetteList);
            resultObject.setMessage(vignetteList.size()+" Vignette were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
        
    }
    
    
    public ResultObject getVignetteTotal(VignetteFilter filter){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(DashboardItemTotals.class);
        
        String Qstr="SELECT count(v),sum(v.amount) FROM Vignette v WHERE v.id IS NOT NULL";
        
        if(filter.getPeriodFrom()!=null)
            Qstr+=" and v.issueTime>=:issueTime";
        
        if(filter.getPeriodTo()!=null)
            Qstr+=" and v.expireTime<=:expireTime ";
        
        if(filter.getPlateNo()!=null)
            Qstr+=" and v.plateNo=:plateNo ";
        
        if(filter.getCreatedBy()!=null)
            Qstr+=" and v.createdBy=:createdBy ";
        
        Query q= em.createQuery(Qstr);
        
        if(filter.getPeriodFrom()!=null){
            try{
                q.setParameter("issueTime",sdf.parse(filter.getPeriodFrom()),TemporalType.TIMESTAMP);
            }catch(Exception ex){
                q.setParameter("issueTime",new Date(),TemporalType.TIMESTAMP);
            }
        }
        
        if(filter.getPeriodTo()!=null){
            try{
                q.setParameter("expireTime",sdf.parse(filter.getPeriodTo()),TemporalType.TIMESTAMP);
            }catch(Exception ex){
                q.setParameter("expireTime",new Date(),TemporalType.TIMESTAMP);
            }
        }
        
        if(filter.getPlateNo()!=null)
            q.setParameter("plateNo", filter.getPlateNo());
        
        if(filter.getCreatedBy()!=null)
            q.setParameter("createdBy", filter.getCreatedBy());
        
        
        List<Object[]>oList=(List<Object[]>)q.getResultList();
        DashboardItemTotals totals=new DashboardItemTotals();
        totals.setItemName("Vignettes");
        totals.setCount((Long)oList.get(0)[0]);
        totals.setSum((Double)oList.get(0)[1]);
        
        resultObject.setObject(totals);
        resultObject.setMessage("Vignette Count and Amount ");
        resultObject.setStatusCode(100);
        
        return resultObject;
        
    }
    
    
    
    
}
