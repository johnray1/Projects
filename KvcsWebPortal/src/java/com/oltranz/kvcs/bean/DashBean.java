/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcs.bean;

import com.oltranz.kvcs.library.CommonLibrary;
import com.oltranz.kvcs.model.DashBoardNumbersRequest;
import com.oltranz.kvcs.model.DashboardNumbersResponse;
import com.oltranz.kvcs.model.PaymentReportInfoModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    private String date;
    private Double mtn;
    private Double tigo;
    
    private DashboardNumbersResponse dashboardNumbersResponse;
    
    
    public void dashBoard(){
        
        getTemplateBean().setDashboardClassName("active");
        getTemplateBean().setTransactionClassName("deactive");
        
        
        
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("dash.xhtml");
        }
        catch (Exception ex) {
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dashFilter(int check){
        
        String startDate = null,endDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        
        if(check==0){
            templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
            String[] output = getDate().split("-");
            String from=output[0];
            String to=output[1];
            
            startDate= from.replace('/', '-');
            endDate= to.replace('/', '-');
        }
        
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("dash.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void report(String startDate,String endDate){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            
            //String url="http://41.74.172.132:8080/RequestsDispatcher/RequestsHandler";
            
            String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
            
            DashBoardNumbersRequest dashBoardNumbersRequest=new DashBoardNumbersRequest();
            dashBoardNumbersRequest.setStartDateTime(startDate);
            dashBoardNumbersRequest.setEndDateTime(endDate);
            String jsonData=mapper.writeValueAsString(dashBoardNumbersRequest);
            
            Response response = CommonLibrary.sendRESTRequest("001","kvcsAccounts",url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            dashboardNumbersResponse=(DashboardNumbersResponse)mapper.readValue(jsonResponse, DashboardNumbersResponse.class);
            List<PaymentReportInfoModel> paymentReportInfoModelList=dashboardNumbersResponse.getPayments();
            mtn=paymentReportInfoModelList.get(0).getAmount();
            tigo=paymentReportInfoModelList.get(1).getAmount();
            String s=getMtn()+"/"+getTigo();
            
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
     * @return the date
     */
    public String getDate() {
        return date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * @return the dashboardNumbersResponse
     */
    public DashboardNumbersResponse getDashboardNumbersResponse() {
        return dashboardNumbersResponse;
    }
    
    /**
     * @param dashboardNumbersResponse the dashboardNumbersResponse to set
     */
    public void setDashboardNumbersResponse(DashboardNumbersResponse dashboardNumbersResponse) {
        this.dashboardNumbersResponse = dashboardNumbersResponse;
    }

    /**
     * @return the mtn
     */
    public Double getMtn() {
        return mtn;
    }

    /**
     * @param mtn the mtn to set
     */
    public void setMtn(Double mtn) {
        this.mtn = mtn;
    }

    /**
     * @return the tigo
     */
    public Double getTigo() {
        return tigo;
    }

    /**
     * @param tigo the tigo to set
     */
    public void setTigo(Double tigo) {
        this.tigo = tigo;
    }
    
    
    
    
    
    
}
