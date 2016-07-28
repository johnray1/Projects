/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oltranz.payfuel.chartmodel.NozzleDash;
import com.oltranz.payfuel.chartmodel.ProductChartModel;
import com.oltranz.payfuel.chartmodel.PumpDash;
import com.oltranz.payfuel.chartmodel.TankChartModel;
import com.oltranz.payfuel.chartmodel.TankDash;
import com.oltranz.payfuel.entities.Nozzle;
import com.oltranz.payfuel.entities.Pump;
import com.oltranz.payfuel.entities.Tank;
import com.oltranz.payfuel.entities.Transaction;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class ChartManager {
    
    @PersistenceContext
    private  EntityManager em;
    
    @EJB
            PumpManager pumpManager;
    
    public String getDailyProductSaleChart(){
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthStartDate = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthEndDate = cal.getTime();
        
        
        
        
        
        
        return null;
    }
    
    public void superSalePerDay(int productId,Date monthStartDate,Date monthEndDate){
        
        String sqlString="SELECT DAY(t.date),SUM(t.quantity) FROM Transaction t WHERE t.productId = :productId GROUP BY t.date HAVING t.date BETWEEN :monthStartDate AND :monthEndDate";
        
        Query query = em.createQuery(sqlString);
        query.setParameter("productId", productId);
        query.setParameter("monthStartDate", monthStartDate);
        query.setParameter("monthEndDate", monthEndDate);
        
        
        
    }
    
    
//--------------------------------------------------------------------------------------------------------------------------------
    
    public String getMonthlyProductSaleChart(){
        
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            Date curMonthStartDate = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date curMonthEndDate = cal.getTime();
            
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            Date preMonthStartDate = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date preMonthEndDate = cal.getTime();
            
            DateFormat dateFormat=new SimpleDateFormat("MMM, yyyy");
            String curMonth=dateFormat.format(curMonthEndDate);
            String preMonth=dateFormat.format(preMonthEndDate);
            
            double preSuperQuantity=superSalePerMonth(1,preMonthStartDate,preMonthEndDate);
            double preGasoilQuantity=gasoilSalePerMonth(2,preMonthStartDate,preMonthEndDate);
            
            double curSuperQuantity=superSalePerMonth(1,curMonthStartDate,curMonthEndDate);
            double curGasoilQuantity=gasoilSalePerMonth(2,curMonthStartDate,curMonthEndDate);
            
            
            
            ProductChartModel preProductChartModel=new ProductChartModel();
            preProductChartModel.setName(preMonth);
            preProductChartModel.setSuperr(preSuperQuantity);
            preProductChartModel.setGasoil(preGasoilQuantity);
            
            ProductChartModel curProductChartModel=new ProductChartModel();
            curProductChartModel.setName(curMonth);
            curProductChartModel.setSuperr(curSuperQuantity);
            curProductChartModel.setGasoil(curGasoilQuantity);
            
            
            
            List<ProductChartModel> productChartModelList=new ArrayList<>();
            productChartModelList.add(preProductChartModel);
            productChartModelList.add(curProductChartModel);
            
            ObjectMapper mapper =new ObjectMapper();
            String jsonString=mapper.writeValueAsString(productChartModelList);
            
            return jsonString;
        }
        catch (JsonProcessingException ex) {
            Logger.getLogger(ChartManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public double superSalePerMonth(int productId,Date monthStartDate,Date monthEndDate){
        
        Query query=em.createQuery("SELECT t FROM Transaction t WHERE t.productId = :productId and t.date BETWEEN :monthStartDate AND :monthEndDate");
        query.setParameter("productId", productId);
        query.setParameter("monthStartDate", monthStartDate);
        query.setParameter("monthEndDate", monthEndDate);
        
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double quantity=0;
        
        for(Transaction transaction : transactionList){
            quantity+=transaction.getQuantity();
        }
        
        return quantity;
    }
    
    public double gasoilSalePerMonth(int productId,Date monthStartDate,Date monthEndDate){
        
        Query query=em.createQuery("SELECT t FROM Transaction t WHERE t.productId = :productId and t.date BETWEEN :monthStartDate AND :monthEndDate");
        query.setParameter("productId", productId);
        query.setParameter("monthStartDate", monthStartDate);
        query.setParameter("monthEndDate", monthEndDate);
        
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double quantity=0;
        
        for(Transaction transaction : transactionList){
            quantity+=transaction.getQuantity();
        }
        
        return quantity;
    }
    
//-------------------------------------------------------------------------------------------------------------------------------
    
    
    
    
    
    public String tankChart(){
        try{
            
            List<Tank> tankList=(List<Tank>)em.createQuery("SELECT t FROM Tank t").getResultList();
            List<TankChartModel> tankChartList=new ArrayList<>();
            
            for(Tank tank : tankList){
                TankChartModel tankChartModel=new TankChartModel();
                tankChartModel.setName(tank.getName());
                tankChartModel.setMax(tank.getMaxCapacity());
                tankChartModel.setCurrent(tank.getCurrentCapacity());
                tankChartList.add(tankChartModel);
            }
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(tankChartList);
            
            return jsonString;
        }
        catch(Exception e){
            return null;
        }
    }
    
    
    
//-------------------------------------------------------------------------------------------------------------------------------
    
    
    
    public String getTankDashboard(int tankId){
        
        
        try {
            Tank tank =em.find(Tank.class,tankId);
            
            TankDash tankDash=new TankDash();
            tankDash.setTankName(tank.getName());
            tankDash.setCurrentCapacity(tank.getCurrentCapacity());
            tankDash.setMaxCapacity(tank.getMaxCapacity());
            tankDash.setPumpDash(pumpList(tank.getTankId()));
            
            ObjectMapper om=new ObjectMapper();
            String jsonString = om.writeValueAsString(tankDash);
            
            return jsonString;
        }
        catch (IOException ex) {
            Logger.getLogger(ChartManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    
    public List<PumpDash> pumpList(int tankId){
        
        List<Pump> pumpList=(List<Pump>) em.createQuery("SELECT p FROM Pump p WHERE p.tankId = :tankId").setParameter("tankId", tankId).getResultList();
        List<PumpDash> pumpDashList=new ArrayList<>();
        
        for(Pump p:pumpList){
            
            List<NozzleDash> nozzleDashList=nozzleList(p.getPumpId());
            PumpDash pumpDash=new PumpDash();
            pumpDash.setPumpName(p.getName());
            pumpDash.setNozzleDash(nozzleDashList);
            pumpDashList.add(pumpDash);
        }
        
        return pumpDashList;
    }
    
    public List<NozzleDash> nozzleList(int pumpId){
        
        List<NozzleDash> nozzleDashList=new ArrayList<>();
        List<Nozzle> nozzleList=(List<Nozzle>)em.createQuery("SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId").setParameter("pumpId", pumpId).getResultList();
        for(Nozzle n : nozzleList){
            NozzleDash nd=new NozzleDash();
            nd.setNozzleName(n.getNozzleName());
            nd.setIndex(n.getNozzleIndex());
            nozzleDashList.add(nd);
        }
        
        return nozzleDashList;
    }
    
    
    public void test(){
        
        
        
        
        Calendar cal = Calendar.getInstance();
// Set the day of the month to the first day of the month
        
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        
// Extract the Date from the Calendar instance
        
        Date firstDayOfTheMonth = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s=dateFormat.format(firstDayOfTheMonth);
        
    }
    
    
}
