/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtime.bean;

import com.oltranz.airtime.library.CommonLibrary;
import com.oltranz.airtime.model.CombinedSalesReport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="DashBean")
@SessionScoped
public class DashBean {
    
    private CombinedSalesReport combinedSalesReport;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public void dashBoard(){
        
        templateBean.setDashboardClassName("active");
        templateBean.setUserClassName("deactive");
        templateBean.setCustomerClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLogClassName("deactive");
        
        
        
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
        }
        catch (Exception ex) {
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void traPerFilter(int check){
        
        String startDate = null,endDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        
        if(check==1){
            templateBean.setToday("col-xs-3 shortdate active");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
            
            startDate=dateFormat.format(cal.getTime())+"  00:00";
            endDate=dateFormat.format(cal.getTime())+"  23:59";
        }
        if(check==2){
            templateBean.setYesterday("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
            cal.add(Calendar.DATE, -1);
            startDate=dateFormat.format(cal.getTime())+"  00:00";
            endDate=dateFormat.format(cal.getTime())+"  23:59";
        }
        if(check==3){
            templateBean.setWeek("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startDate=dateFormat.format(cal.getTime())+"  00:00";
            cal.add(Calendar.DATE, 6);
            endDate=dateFormat.format(cal.getTime())+"  23:59";
            
        }
        if(check==4){
            templateBean.setMonth("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");
            
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            Date curMonthStartDate = cal.getTime();
            
            cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date curMonthEndDate = cal.getTime();
            
            startDate=dateFormat.format(curMonthStartDate)+"  00:00";
            endDate=dateFormat.format(curMonthEndDate)+"  23:59";
        }
        
        report(startDate,endDate);
        
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void report(String startDate,String endDate){
        
        try{
            
            String url="http://localhost:8080/AirtimeRechargeSystemCore/reports/getreport";
            String  jsonData = "{\n" +
                    "\"startDate\":\""+startDate+"\",\n" +
                    "\"endDate\":\""+endDate+"\"\n" +
                    "}";
            Response response = CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            combinedSalesReport=(CombinedSalesReport)mapper.readValue(jsonResponse, CombinedSalesReport.class);
            String s=null;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    /**
     * @return the templateBean
     */
    public TemplateBean getTemplateBean() {
        return templateBean;
    }
    
    /**
     * @param templateBean the templateBean to set
     */
    public void setTemplateBean(TemplateBean templateBean) {
        this.templateBean = templateBean;
    }
    
    
    
    /**
     * @return the combinedSalesReport
     */
    public CombinedSalesReport getCombinedSalesReport() {
        return combinedSalesReport;
    }
    
    /**
     * @param combinedSalesReport the combinedSalesReport to set
     */
    public void setCombinedSalesReport(CombinedSalesReport combinedSalesReport) {
        this.combinedSalesReport = combinedSalesReport;
    }
    
    
    
}
