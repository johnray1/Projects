/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oltranz.payfuel.chartmodel.DailyChartProductModel;
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
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    public String getDailyProductSaleChart(){
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthStartDate = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthEndDate = cal.getTime();
        
        
        String [] dayOfMonth=commonFunctionEjb.dayOfMonth();
        double [] superOfDay=productSalePerDay(1);
        double [] gasoilOfDay=productSalePerDay(2);
        
        DailyChartProductModel dcp=new DailyChartProductModel();
        dcp.setDay(dayOfMonth);
        dcp.setSuperList(superOfDay);
        dcp.setGasoilList(gasoilOfDay);
        
        ObjectMapper mapper=new ObjectMapper();
        
        try {
            String json = mapper.writeValueAsString(dcp);
            return json;
        }
        catch (JsonProcessingException ex) {
            Logger.getLogger(ChartManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    
    public double [] productSalePerDay(int productId){
        
        
        Calendar cal = Calendar.getInstance();
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        double [] productOfMonth=new double[maxDay];
        
        for (int i = 0; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            Date date=cal.getTime();
            
            String sqlString="SELECT SUM(t.quantity) FROM Transaction t WHERE t.productId = :productId AND t.date = :date";
            Query query = em.createQuery(sqlString);
            query.setParameter("productId", productId);
            query.setParameter("date", date);
            List<Double> quantityList=(List<Double>)query.getResultList();
            
            if(quantityList.get(0)==null){
                productOfMonth[i]=0;
            }
            else{
                productOfMonth[i]=quantityList.get(0);
            }
        }
        
        return productOfMonth;
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
            
            double preSuperQuantity=productSalePerMonth(1,preMonthStartDate,preMonthEndDate);
            double preGasoilQuantity=productSalePerMonth(2,preMonthStartDate,preMonthEndDate);
            
            double curSuperQuantity=productSalePerMonth(1,curMonthStartDate,curMonthEndDate);
            double curGasoilQuantity=productSalePerMonth(2,curMonthStartDate,curMonthEndDate);
            
            
            
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
    
    public double productSalePerMonth(int productId,Date monthStartDate,Date monthEndDate){
        
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
    
    
    
    
    
}
