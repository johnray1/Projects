/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oltranz.engenpayfuel.chartmodel.BranchBar;
import com.oltranz.engenpayfuel.chartmodel.DailyChartProductModel;
import com.oltranz.engenpayfuel.chartmodel.NozzleDash;
import com.oltranz.engenpayfuel.chartmodel.PaymentChartModel;
import com.oltranz.engenpayfuel.chartmodel.ProductChartModel;
import com.oltranz.engenpayfuel.chartmodel.ProductPie;
import com.oltranz.engenpayfuel.chartmodel.PumpDash;
import com.oltranz.engenpayfuel.chartmodel.TankChartModel;
import com.oltranz.engenpayfuel.chartmodel.TankChartModelHq;
import com.oltranz.engenpayfuel.chartmodel.TankDash;
import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.Nozzle;
import com.oltranz.engenpayfuel.entities.PaymentMode;
import com.oltranz.engenpayfuel.entities.Product;
import com.oltranz.engenpayfuel.entities.Tank;
import com.oltranz.engenpayfuel.entities.Transaction;
import com.oltranz.engenpayfuel.models.ResultObject;
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
    
    public String getDailyProductSaleChart(int branchId){
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date monthStartDate = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date monthEndDate = cal.getTime();
        
        
        String [] dayOfMonth=commonFunctionEjb.dayOfMonth();
        double [] superOfDay=productSalePerDay(1,branchId);
        double [] gasoilOfDay=productSalePerDay(2,branchId);
        
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
    
    
    
    public double [] productSalePerDay(int productId,int branchId){
        
        
        Calendar cal = Calendar.getInstance();
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        double [] productOfMonth=new double[maxDay];
        
        for (int i = 0; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            Date date=cal.getTime();
            String sqlString;
            Query query;
            if(branchId==0){
                sqlString="SELECT SUM(t.quantity) FROM Transaction t WHERE t.productId = :productId AND t.date = :date";
                query = em.createQuery(sqlString);
                query.setParameter("productId", productId);
                query.setParameter("date", date);
            }
            else{
                sqlString="SELECT SUM(t.quantity) FROM Transaction t WHERE t.productId = :productId AND t.date = :date AND t.branchId = :branchId";
                query = em.createQuery(sqlString);
                query.setParameter("productId", productId);
                query.setParameter("date", date);
                query.setParameter("branchId", branchId);
            }
            
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
    
    
    
    
    public String getMonthlyProductSaleChart(int branchId){
        
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
            
            double preSuperQuantity=productSalePerMonth(1,preMonthStartDate,preMonthEndDate,branchId);
            double preGasoilQuantity=productSalePerMonth(2,preMonthStartDate,preMonthEndDate,branchId);
            
            double curSuperQuantity=productSalePerMonth(1,curMonthStartDate,curMonthEndDate,branchId);
            double curGasoilQuantity=productSalePerMonth(2,curMonthStartDate,curMonthEndDate,branchId);
            
            
            
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
    
    public double productSalePerMonth(int productId,Date monthStartDate,Date monthEndDate,int branchId){
        Query query;
        if(branchId==0){
            query=em.createQuery("SELECT t FROM Transaction t WHERE t.productId = :productId and t.date BETWEEN :monthStartDate AND :monthEndDate");
            query.setParameter("productId", productId);
            query.setParameter("monthStartDate", monthStartDate);
            query.setParameter("monthEndDate", monthEndDate);
        }
        else{
            query=em.createQuery("SELECT t FROM Transaction t WHERE t.productId = :productId and t.branchId = :branchId and t.date BETWEEN :monthStartDate AND :monthEndDate");
            query.setParameter("productId", productId);
            query.setParameter("monthStartDate", monthStartDate);
            query.setParameter("monthEndDate", monthEndDate);
            query.setParameter("branchId", branchId);
        }
        
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double quantity=0;
        
        for(Transaction transaction : transactionList){
            quantity+=transaction.getQuantity();
        }
        
        return quantity;
    }
    
    
    /*tank start*/
    public String allTankQuantityCommulativeChart(){
        
        try{
            List<TankChartModelHq> thqList=new ArrayList<>();
            TankChartModelHq thq;
            List<Product> proList=em.createQuery("SELECT p FROM Product p").getResultList();
            for(Product p:proList){
                thq=findTankIdByProduct(p.getProductId());
                thqList.add(thq);
            }
            
            
            ObjectMapper om=new ObjectMapper();
            String jsonString = om.writeValueAsString(thqList);
            
            return jsonString;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public TankChartModelHq findTankIdByProduct(int productId){
        
        
        Product product=em.find(Product.class, productId);
        
        List<Tank> tankIdList=em.createQuery("SELECT  t FROM Tank t WHERE t.productId = :productId").setParameter("productId", productId).getResultList();
        double quantity=0;
        
        for(Tank t : tankIdList){
            quantity+=t.getCurrentCapacity();
        }
        
        TankChartModelHq thq=new TankChartModelHq();
        thq.setName(product.getName());
        thq.setQuantity(quantity);
        
        return thq;
    }
    
    
    public String tankChart(int branchId){
        try{
            
            List<Tank> tankList=(List<Tank>)em.createQuery("SELECT t FROM Tank t where t.branchId = :branchId").setParameter("branchId", branchId).getResultList();
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
    
    
    
    
    
//payment
    
    public String allPaymentChart(int branchId){
        try{
            PaymentChartModel pcm=new PaymentChartModel();
            List<PaymentMode> pmList=em.createQuery("SELECT p FROM PaymentMode p").getResultList();
            
            for(PaymentMode p:pmList){
                double amount=salePerPayment(p.getPaymentModeId(),branchId);
                int pId=p.getPaymentModeId();
                
                if(pId==1){
                    pcm.setCash(amount);
                }
                
                if(pId==2){
                    pcm.setVoucher(amount);
                }
                
                if(pId==3){
                    pcm.setMtn(amount);
                }
                
                if(pId==4){
                    pcm.setTigo(amount);
                }
                
                if(pId==5){
                    pcm.setAirtel(amount);
                }
                
                if(pId==6){
                    pcm.setVisa(amount);
                }
                
                if(pId==7){
                    pcm.setMaster(amount);
                }
                
                if(pId==8){
                    pcm.setDebt(amount);
                }
                
                if(pId==9){
                    pcm.setEngenCard(amount);
                }
            }
            
            
            ObjectMapper om=new ObjectMapper();
            String jsonString = om.writeValueAsString(pcm);
            return jsonString;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public double salePerPayment(int paymentModeId,int branchId){
        Date currentDate = new Date();
        Query query;
        if(branchId==0){
            query=em.createQuery("SELECT t FROM Transaction t WHERE t.paymentModeId = :paymentModeId and t.date = :date");
            query.setParameter("paymentModeId", paymentModeId);
            query.setParameter("date", currentDate);
        }
        else{
            query=em.createQuery("SELECT t FROM Transaction t WHERE t.paymentModeId = :paymentModeId and t.branchId = :branchId and t.date = :date");
            query.setParameter("paymentModeId", paymentModeId);
            query.setParameter("branchId", branchId);
            query.setParameter("date", currentDate);
        }
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double amount=0;
        if(transactionList.isEmpty()){
            return amount;
        }
        else{
            for(Transaction transaction : transactionList){
                amount+=transaction.getAmount();
            }
            return amount;
        }
        
    }
    
    
    
    public String paymentChartSaleProduct(){
        try{
            PaymentChartModel pcmsup=new PaymentChartModel();
            PaymentChartModel pcmgas=new PaymentChartModel();
            List<PaymentChartModel> pclist=new ArrayList<>();
            List<PaymentMode> pmList=em.createQuery("SELECT p FROM PaymentMode p").getResultList();
            
            for(PaymentMode p:pmList){
                
                double amountsup=salePaymentPerProduct(p.getPaymentModeId(),1);
                double amountgas=salePaymentPerProduct(p.getPaymentModeId(),2);
                int pId=p.getPaymentModeId();
                
                if(pId==1){
                    pcmsup.setCash(amountsup);
                    pcmgas.setCash(amountgas);
                }
                
                if(pId==2){
                    pcmsup.setVoucher(amountsup);
                    pcmgas.setVoucher(amountgas);
                }
                
                if(pId==3){
                    pcmsup.setMtn(amountsup);
                    pcmgas.setMtn(amountgas);
                }
                
                if(pId==4){
                    pcmsup.setTigo(amountsup);
                    pcmgas.setTigo(amountgas);
                }
                
                if(pId==5){
                    pcmsup.setAirtel(amountsup);
                    pcmgas.setAirtel(amountgas);
                }
                
                if(pId==6){
                    pcmsup.setVisa(amountsup);
                    pcmgas.setVisa(amountgas);
                }
                
                if(pId==7){
                    pcmsup.setMaster(amountsup);
                    pcmgas.setMaster(amountgas);
                }
                
                if(pId==8){
                    pcmsup.setDebt(amountsup);
                    pcmgas.setDebt(amountgas);
                }
                
                if(pId==9){
                    pcmsup.setEngenCard(amountsup);
                    pcmgas.setEngenCard(amountgas);
                }
                
            }
            pclist.add(pcmsup);
            pclist.add(pcmgas);
            
            ObjectMapper om=new ObjectMapper();
            String jsonString = om.writeValueAsString(pclist);
            return jsonString;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public double salePaymentPerProduct(int paymentModeId,int productId){
        
        Query query=em.createQuery("SELECT t FROM Transaction t WHERE t.paymentModeId = :paymentModeId and t.productId = :productId");
        query.setParameter("paymentModeId", paymentModeId);
        query.setParameter("productId", productId);
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double amount=0;
        if(transactionList.isEmpty()){
            return amount;
        }
        else{
            for(Transaction transaction : transactionList){
                amount+=transaction.getAmount();
            }
            return amount;
        }
        
    }
    
    
    public String productPie(){
        try{
            double amountsup=productPieData(1);
            double amountgas=productPieData(2);
            ProductPie productPie=new ProductPie();
            productPie.setSuperAmount(amountsup);
            productPie.setGasoilAmount(amountgas);
            
            ObjectMapper om=new ObjectMapper();
            String jsonString = om.writeValueAsString(productPie);
            return jsonString;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public double productPieData(int productId){
        
        Query query=em.createQuery("SELECT t FROM Transaction t WHERE t.productId = :productId");
        query.setParameter("productId", productId);
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double amount=0;
        if(transactionList.isEmpty()){
            return amount;
        }
        else{
            for(Transaction transaction : transactionList){
                amount+=transaction.getAmount();
            }
            return amount;
        }
        
    }
    
    
    public String allBranchChart(){
        try{
            List<BranchBar> bbl=new ArrayList<>();
            List<Branch> branchList=em.createQuery("SELECT b FROM Branch b").getResultList();
            
            for(Branch b:branchList){
                BranchBar bb=new BranchBar();
                double amount=branchIncome(b.getBranchId());
                String branchName=b.getName();
                bb.setBranchName(branchName);
                bb.setIncome(amount);
                bbl.add(bb);
            }
            ObjectMapper om=new ObjectMapper();
            String jsonString = om.writeValueAsString(bbl);
            return jsonString;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public double branchIncome(int branchId){
        Query query=em.createQuery("SELECT t FROM Transaction t WHERE t.branchId = :branchId");
        query.setParameter("branchId", branchId);
        List<Transaction> transactionList=(List<Transaction>)query.getResultList();
        double amount=0;
        if(transactionList.isEmpty()){
            return amount;
        }
        else{
            for(Transaction transaction : transactionList){
                amount+=transaction.getAmount();
            }
            return amount;
        }
        
    }
    
    
    
    
    
    //-------------------------------------------------------------------------------------------------------------------------------
    
    
    
//    public String getTankDashboard(int tankId){
//
//
//        try {
//            Tank tank =em.find(Tank.class,tankId);
//
//            TankDash tankDash=new TankDash();
//            tankDash.setTankName(tank.getName());
//            tankDash.setCurrentCapacity(tank.getCurrentCapacity());
//            tankDash.setMaxCapacity(tank.getMaxCapacity());
//            tankDash.setPumpDash(pumpList(tank.getTankId()));
//
//            ObjectMapper om=new ObjectMapper();
//            String jsonString = om.writeValueAsString(tankDash);
//
//            return jsonString;
//        }
//        catch (IOException ex) {
//            Logger.getLogger(ChartManager.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//
//    }
//
//    public List<PumpDash> pumpList(int tankId){
//
//        List<Pump> pumpList=(List<Pump>) em.createQuery("SELECT p FROM Pump p WHERE p.tankId = :tankId").setParameter("tankId", tankId).getResultList();
//        List<PumpDash> pumpDashList=new ArrayList<>();
//
//        for(Pump p:pumpList){
//
//            List<NozzleDash> nozzleDashList=nozzleList(p.getPumpId());
//            PumpDash pumpDash=new PumpDash();
//            pumpDash.setPumpName(p.getName());
//            pumpDash.setNozzleDash(nozzleDashList);
//            pumpDashList.add(pumpDash);
//        }
//
//        return pumpDashList;
//    }
//
//    public List<NozzleDash> nozzleList(int pumpId){
//
//        List<NozzleDash> nozzleDashList=new ArrayList<>();
//        List<Nozzle> nozzleList=(List<Nozzle>)em.createQuery("SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId").setParameter("pumpId", pumpId).getResultList();
//        for(Nozzle n : nozzleList){
//            NozzleDash nd=new NozzleDash();
//            nd.setNozzleName(n.getNozzleName());
//            nd.setIndex(n.getNozzleIndex());
//            nozzleDashList.add(nd);
//        }
//
//        return nozzleDashList;
//    }
    
    
    //--------------------------------------------------------------------------------------------------------
    
    
    
    public ResultObject getTankDashboard(int branchId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(TankDash.class);
        
        List<TankDash> tankDashList=new ArrayList<>();
        List<Tank> tankList =em.createQuery("SELECT t FROM Tank t WHERE t.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        for(Tank tank:tankList){
            TankDash tankDash=new TankDash();
            tankDash.setTankId(tank.getTankId());
            tankDash.setTankName(tank.getName());
            tankDash.setProductName(commonFunctionEjb.getProductName(tank.getProductId()).getName().toLowerCase());
            tankDash.setCurrentCapacity(tank.getCurrentCapacity());
            tankDash.setMaxCapacity(tank.getMaxCapacity());
            tankDash.setDippedCapacity(tank.getDippedCapacity());
            tankDash.setDippedTime(tank.getDippedTime());
            tankDash.setPumpDash(pumpList(tank.getTankId()));
            tankDashList.add(tankDash);
        }
        
        resultObject.setObject(tankDashList);
        resultObject.setMessage("Success");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    
    
    public List<PumpDash> pumpList(int tankId){
        
        List<PumpDash> pumpDashList=new ArrayList<>();
        List<Integer> pumpIdList=em.createQuery("SELECT DISTINCT n.pumpId FROM Nozzle n WHERE n.tankId = :tankId").setParameter("tankId", tankId).getResultList();
        for(int i:pumpIdList){
            int j= i;
            PumpDash pumpDash=new PumpDash();
            pumpDash.setPumpId(i);
            pumpDash.setPumpName(commonFunctionEjb.getPumpName(i).getName());
            pumpDash.setNozzleDash(nozzleList(i,tankId));
            pumpDashList.add(pumpDash);
        }
        
        return pumpDashList;
    }
    
    public List<NozzleDash> nozzleList(int pumpId,int tankId){
        
        List<NozzleDash> nozzleDashList=new ArrayList<>();
        List<Nozzle> nozzleList=em.createQuery("SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId and n.tankId = :tankId").setParameter("pumpId", pumpId).setParameter("tankId", tankId).getResultList();
        for(Nozzle n:nozzleList){
            
            NozzleDash nozzleDash=new NozzleDash();
            nozzleDash.setNozzleId(n.getNozzleId());
            nozzleDash.setNozzleName(n.getNozzleName());
            nozzleDash.setIndex(n.getNozzleIndex());
            
            nozzleDashList.add(nozzleDash);
        }
        
        return nozzleDashList;
    }
    
    
}
