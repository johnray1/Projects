/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

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

/**
 *
 * @author John
 */
@ManagedBean(name="DashBean")
@SessionScoped
public class DashBean implements Serializable{
    private String date;
    private String startDate,endDate;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{TpnBean}")
    private TpnBean tpnBean;
    
    private HttpSession session = SessionBean.getSession();
    
    
    public void dashBoard(){
        try{
            templateBean.setDashboardClassName("omenu_active");
            templateBean.setBranchClassName("omenu");
            templateBean.setProductsClassName("omenu");
            templateBean.setGoalClassName("omenu");
            templateBean.setTransactionClassName("omenu");
            templateBean.setSettingClassName("omenu");
            
            report(1);
            tpnBean.tankDashboard();
            
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dashBoardHq(){
        try{
            templateBean.setDashboardClassName("omenu_active");
            templateBean.setBranchClassName("omenu");
            templateBean.setProductsClassName("omenu");
            templateBean.setGoalClassName("omenu");
            templateBean.setTransactionClassName("omenu");
            templateBean.setSettingClassName("omenu");
            
            report(1);
            tpnBean.tankDashboard();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard_hq.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(DashBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void report(int check){
        
        int userId=(int) session.getAttribute("userId");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        if(check==0){
            templateBean.setToday("col-xs-3 shortdate");templateBean.setYesterday("col-xs-3 shortdate");templateBean.setWeek("col-xs-3 shortdate");templateBean.setMonth("col-xs-3 shortdate");
            
            String[] output = date.split("-");
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
        templateBean.paymentModeChart(startDate,endDate);
        
        try{
            if(userId!=1){
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");
            }
            else{
                FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard_hq.xhtml");
            }
        }
        catch(Exception ex){
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the tpnBean
     */
    public TpnBean getTpnBean() {
        return tpnBean;
    }
    
    /**
     * @param tpnBean the tpnBean to set
     */
    public void setTpnBean(TpnBean tpnBean) {
        this.tpnBean = tpnBean;
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
    
    
    
    
}
