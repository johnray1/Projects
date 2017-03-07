/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;


import com.oltranz.ignite.library.CommonLibrary;
import com.oltranz.ignite.model.DashboardItemTotals;
import com.oltranz.ignite.model.PaymentTransaction;
import com.oltranz.ignite.model.PaymentsTransactionsList;
import com.oltranz.ignite.model.TransactionsFilter;
import java.io.IOException;
import java.io.Serializable;
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
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="DashBean")
@SessionScoped
public class DashBean implements Serializable{
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{LiquidBean}")
    private LiquidBean liquidBean;
    
    private DashboardItemTotals dashboardItemTotals;
    private DashboardItemTotals successDashboardItemTotals;
    
    private DashboardItemTotals mtnSuccessDashboardItemTotals;
    private DashboardItemTotals tigoSuccessDashboardItemTotals;
    private DashboardItemTotals airtelSuccessDashboardItemTotals;
    
    private DashboardItemTotals chartMtnSuccessDashboardItemTotals;
    private DashboardItemTotals chartTigoSuccessDashboardItemTotals;
    private DashboardItemTotals chartAirtelSuccessDashboardItemTotals;
    
    private PaymentTransaction paymentTransaction;
    private PaymentsTransactionsList paymentsTransactionsList;
    
    private Double sumAngaza,sumAzur,sumDlight;
    
    private String date,startDate,endDate;
    
    
    private HttpSession session = SessionBean.getSession();
    
    public void dashBoard(){
        
        templateBean.setDashboardClassName("active");
        templateBean.setUserClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLiquidClassName("deactive");
        
        try {
            dashFilter(5);
            liquidBean.liquidList();
            FacesContext.getCurrentInstance().getExternalContext().redirect("dash.xhtml");
        }
        catch (Exception ex) {
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dashFilter(int check){
        
        
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            
            if(check==0){
                templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
                String[] output =date.split("-");
                String from=output[0];
                String to=output[1];
                
                startDate= from.replace('/', '-');
                endDate= to.replace('/', '-');
                
            }
            
            if(check==1){
                templateBean.setToday("col-xs-3 shortdate active");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");templateBean.setOverall("col-xs-3 shortdate");
                
                startDate=dateFormat.format(cal.getTime())+"  00:00";
                endDate=dateFormat.format(cal.getTime())+"  23:59";
                
            }
            if(check==2){
                templateBean.setYesterday("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");templateBean.setOverall("col-xs-3 shortdate");
                
                cal.add(Calendar.DATE, -1);
                startDate=dateFormat.format(cal.getTime())+"  00:00";
                endDate=dateFormat.format(cal.getTime())+"  23:59";
                
            }
            if(check==3){
                templateBean.setWeek("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");templateBean.setOverall("col-xs-3 shortdate");
                
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                startDate=dateFormat.format(cal.getTime())+"  00:00";
                cal.add(Calendar.DATE, 6);
                endDate=dateFormat.format(cal.getTime())+"  23:59";
                
                
            }
            if(check==4){
                templateBean.setMonth("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setOverall("col-xs-3 shortdate");
                
                cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                Date curMonthStartDate = cal.getTime();
                
                cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                Date curMonthEndDate = cal.getTime();
                
                startDate=dateFormat.format(curMonthStartDate)+"  00:00";
                endDate=dateFormat.format(curMonthEndDate)+"  23:59";
                
            }
            if(check==5){
                templateBean.setOverall("col-xs-3 shortdate active");templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
                
                startDate=null;
                endDate=null;
                
            }
            
            TransactionsFilter tf=new TransactionsFilter();
            tf.setPeriodFrom(startDate);
            tf.setPeriodTo(endDate);
            tf.setMerchantId("3150");
            
            dashReport(tf);
            dashReportSuccess(tf);
            dashTelecomChartReport(tf);
            dashSubCompanyChartReport(tf);
            dashTelecomReport();
            
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("dash.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void dashReport(TransactionsFilter tf)throws IOException{
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tf);
        
        String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
        MultivaluedMap<String,Object> headers=new MultivaluedHashMap();
        headers.add("CMD", "003");
        headers.add("Domain","paymentgw");
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,headers, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        dashboardItemTotals=(DashboardItemTotals)mapper.readValue(jsonResponse, DashboardItemTotals.class);
        
        
    }
    
    
    public void dashReportSuccess(TransactionsFilter tf)throws IOException{
        tf.setStatus(100);
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tf);
        
        String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
        MultivaluedMap<String,Object> headers=new MultivaluedHashMap();
        headers.add("CMD", "003");
        headers.add("Domain","paymentgw");
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,headers, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        successDashboardItemTotals=(DashboardItemTotals)mapper.readValue(jsonResponse, DashboardItemTotals.class);
        
        
    }
    
    public void dashTelecomReport()throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        
        mtnSuccessDashboardItemTotals=(DashboardItemTotals)mapper.readValue(dashTelecomReportSuccess("2484"), DashboardItemTotals.class);
        tigoSuccessDashboardItemTotals=(DashboardItemTotals)mapper.readValue(dashTelecomReportSuccess("3382"), DashboardItemTotals.class);
        airtelSuccessDashboardItemTotals=(DashboardItemTotals)mapper.readValue(dashTelecomReportSuccess("5728"), DashboardItemTotals.class);
    }
    
    public String dashTelecomReportSuccess(String paymentSpId)throws IOException{
        
        
        TransactionsFilter tf=new TransactionsFilter();
        tf.setStatus(100);
        tf.setPaymentSpId(paymentSpId);
        tf.setMerchantId("3150");
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tf);
        
        String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
        MultivaluedMap<String,Object> headers=new MultivaluedHashMap();
        headers.add("CMD", "003");
        headers.add("Domain","paymentgw");
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,headers, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        return jsonResponse;
        
    }
    
    
    
    public void dashTelecomChartReport(TransactionsFilter tf)throws IOException{
        ObjectMapper mapper=new ObjectMapper();
        
        tf.setPaymentSpId("2484");
        chartMtnSuccessDashboardItemTotals=(DashboardItemTotals)mapper.readValue(dashTelecomChartReportSuccess(tf), DashboardItemTotals.class);
        tf.setPaymentSpId("3382");
        chartTigoSuccessDashboardItemTotals=(DashboardItemTotals)mapper.readValue(dashTelecomChartReportSuccess(tf), DashboardItemTotals.class);
        tf.setPaymentSpId("5728");
        chartAirtelSuccessDashboardItemTotals=(DashboardItemTotals)mapper.readValue(dashTelecomChartReportSuccess(tf), DashboardItemTotals.class);
    }
    
    public String dashTelecomChartReportSuccess(TransactionsFilter tf)throws IOException{
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tf);
        
        String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
        MultivaluedMap<String,Object> headers=new MultivaluedHashMap();
        headers.add("CMD", "003");
        headers.add("Domain","paymentgw");
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,headers, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        
        return jsonResponse;
        
    }
    
    
    
    
    public String dashSubCompanyChartReport(TransactionsFilter tf)throws IOException{
        tf.setPaymentSpId(null);
        tf.setStatus(100);
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tf);
        
        String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
        MultivaluedMap<String,Object> headers=new MultivaluedHashMap();
        headers.add("CMD", "002");
        headers.add("Domain","paymentgw");
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,headers, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        paymentsTransactionsList=(PaymentsTransactionsList)mapper.readValue(jsonResponse, PaymentsTransactionsList.class);
        
        
        sumDlight=0.0;sumAzur=0.0;sumAngaza=0.0;
        
        for(PaymentTransaction pt : paymentsTransactionsList.getTxList()){
            
            if(pt.getMerchantTransactionId().trim().length()==9){
                sumDlight+=pt.getAmount();
            }
            
            if(pt.getMerchantTransactionId().trim().length()==8){
                sumAzur+=pt.getAmount();
            }
            
            if(pt.getMerchantTransactionId().trim().length()==7){
                sumAngaza+=pt.getAmount();
            }
            
        }
        
        return null;
        
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
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }
    
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }
    
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    /**
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }
    
    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
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
     * @return the dashboardItemTotals
     */
    public DashboardItemTotals getDashboardItemTotals() {
        return dashboardItemTotals;
    }
    
    /**
     * @param dashboardItemTotals the dashboardItemTotals to set
     */
    public void setDashboardItemTotals(DashboardItemTotals dashboardItemTotals) {
        this.dashboardItemTotals = dashboardItemTotals;
    }
    
    /**
     * @return the successDashboardItemTotals
     */
    public DashboardItemTotals getSuccessDashboardItemTotals() {
        return successDashboardItemTotals;
    }
    
    /**
     * @param successDashboardItemTotals the successDashboardItemTotals to set
     */
    public void setSuccessDashboardItemTotals(DashboardItemTotals successDashboardItemTotals) {
        this.successDashboardItemTotals = successDashboardItemTotals;
    }
    
    /**
     * @return the mtnSuccessDashboardItemTotals
     */
    public DashboardItemTotals getMtnSuccessDashboardItemTotals() {
        return mtnSuccessDashboardItemTotals;
    }
    
    /**
     * @param mtnSuccessDashboardItemTotals the mtnSuccessDashboardItemTotals to set
     */
    public void setMtnSuccessDashboardItemTotals(DashboardItemTotals mtnSuccessDashboardItemTotals) {
        this.mtnSuccessDashboardItemTotals = mtnSuccessDashboardItemTotals;
    }
    
    /**
     * @return the tigoSuccessDashboardItemTotals
     */
    public DashboardItemTotals getTigoSuccessDashboardItemTotals() {
        return tigoSuccessDashboardItemTotals;
    }
    
    /**
     * @param tigoSuccessDashboardItemTotals the tigoSuccessDashboardItemTotals to set
     */
    public void setTigoSuccessDashboardItemTotals(DashboardItemTotals tigoSuccessDashboardItemTotals) {
        this.tigoSuccessDashboardItemTotals = tigoSuccessDashboardItemTotals;
    }
    
    /**
     * @return the airtelSuccessDashboardItemTotals
     */
    public DashboardItemTotals getAirtelSuccessDashboardItemTotals() {
        return airtelSuccessDashboardItemTotals;
    }
    
    /**
     * @param airtelSuccessDashboardItemTotals the airtelSuccessDashboardItemTotals to set
     */
    public void setAirtelSuccessDashboardItemTotals(DashboardItemTotals airtelSuccessDashboardItemTotals) {
        this.airtelSuccessDashboardItemTotals = airtelSuccessDashboardItemTotals;
    }
    
    /**
     * @return the chartMtnSuccessDashboardItemTotals
     */
    public DashboardItemTotals getChartMtnSuccessDashboardItemTotals() {
        return chartMtnSuccessDashboardItemTotals;
    }
    
    /**
     * @param chartMtnSuccessDashboardItemTotals the chartMtnSuccessDashboardItemTotals to set
     */
    public void setChartMtnSuccessDashboardItemTotals(DashboardItemTotals chartMtnSuccessDashboardItemTotals) {
        this.chartMtnSuccessDashboardItemTotals = chartMtnSuccessDashboardItemTotals;
    }
    
    /**
     * @return the chartTigoSuccessDashboardItemTotals
     */
    public DashboardItemTotals getChartTigoSuccessDashboardItemTotals() {
        return chartTigoSuccessDashboardItemTotals;
    }
    
    /**
     * @param chartTigoSuccessDashboardItemTotals the chartTigoSuccessDashboardItemTotals to set
     */
    public void setChartTigoSuccessDashboardItemTotals(DashboardItemTotals chartTigoSuccessDashboardItemTotals) {
        this.chartTigoSuccessDashboardItemTotals = chartTigoSuccessDashboardItemTotals;
    }
    
    /**
     * @return the chartAirtelSuccessDashboardItemTotals
     */
    public DashboardItemTotals getChartAirtelSuccessDashboardItemTotals() {
        return chartAirtelSuccessDashboardItemTotals;
    }
    
    /**
     * @param chartAirtelSuccessDashboardItemTotals the chartAirtelSuccessDashboardItemTotals to set
     */
    public void setChartAirtelSuccessDashboardItemTotals(DashboardItemTotals chartAirtelSuccessDashboardItemTotals) {
        this.chartAirtelSuccessDashboardItemTotals = chartAirtelSuccessDashboardItemTotals;
    }
    
    /**
     * @return the paymentTransaction
     */
    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }
    
    /**
     * @param paymentTransaction the paymentTransaction to set
     */
    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }
    
    /**
     * @return the paymentsTransactionsList
     */
    public PaymentsTransactionsList getPaymentsTransactionsList() {
        return paymentsTransactionsList;
    }
    
    /**
     * @param paymentsTransactionsList the paymentsTransactionsList to set
     */
    public void setPaymentsTransactionsList(PaymentsTransactionsList paymentsTransactionsList) {
        this.paymentsTransactionsList = paymentsTransactionsList;
    }

    /**
     * @return the sumAngaza
     */
    public Double getSumAngaza() {
        return sumAngaza;
    }

    /**
     * @param sumAngaza the sumAngaza to set
     */
    public void setSumAngaza(Double sumAngaza) {
        this.sumAngaza = sumAngaza;
    }

    /**
     * @return the sumAzur
     */
    public Double getSumAzur() {
        return sumAzur;
    }

    /**
     * @param sumAzur the sumAzur to set
     */
    public void setSumAzur(Double sumAzur) {
        this.sumAzur = sumAzur;
    }

    /**
     * @return the sumDlight
     */
    public Double getSumDlight() {
        return sumDlight;
    }

    /**
     * @param sumDlight the sumDlight to set
     */
    public void setSumDlight(Double sumDlight) {
        this.sumDlight = sumDlight;
    }

    /**
     * @return the liquidBean
     */
    public LiquidBean getLiquidBean() {
        return liquidBean;
    }

    /**
     * @param liquidBean the liquidBean to set
     */
    public void setLiquidBean(LiquidBean liquidBean) {
        this.liquidBean = liquidBean;
    }
    
    

    
    
    
    
    
    
}
