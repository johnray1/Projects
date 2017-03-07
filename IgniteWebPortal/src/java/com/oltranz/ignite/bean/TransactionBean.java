/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;


import com.oltranz.ignite.library.CommonLibrary;
import com.oltranz.ignite.model.PaymentTransaction;
import com.oltranz.ignite.model.PaymentsTransactionsList;
import com.oltranz.ignite.model.TransactionsFilter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@ManagedBean(name="TransactionBean")
@SessionScoped
public class TransactionBean implements Serializable{
    
    private Date currentDate;
    private String filterDate;
    private Integer subMerchantLength;
    private String paymentMode;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    private PaymentTransaction paymentTransaction;
    private PaymentsTransactionsList paymentsTransactionsList;
    private PaymentsTransactionsList overAllIgnitePaymentsTransactionsList;
    
    private HttpSession session = SessionBean.getSession();
    
    
    
    private String page;
    
    public void transactions(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setTransactionClassName("active");
        templateBean.setLiquidClassName("deactive");
        
        try {
            currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDate=dateFormat.format(getCurrentDate())+"  00:00";
            String endDate=dateFormat.format(getCurrentDate())+"  23:59";
            
            TransactionsFilter tf=new TransactionsFilter();
            tf.setPeriodFrom(startDate);
            tf.setPeriodTo(endDate);
            tf.setMerchantId("3150");
            transactionList(tf);
            
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("tra.xhtml");
            
            
        }
        catch (Exception ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void filterTransactions(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setTransactionClassName("active");
        
        
        try {
            String dateIp=filterDate;
            String[] output = dateIp.split("-");
            String start=output[0];
            String end=output[1];
            String startDate = start.replace('/', '-');
            String endDate = end.replace('/', '-');
            
            TransactionsFilter tf=new TransactionsFilter();
            tf.setPeriodFrom(startDate);
            tf.setPeriodTo(endDate);
            tf.setMerchantId("3150");
            tf.setPaymentSpId(paymentMode);
            transactionList(tf);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("tra.xhtml");
            
        }
        catch (Exception ex) {
            Logger.getLogger(TransactionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void transactionList(TransactionsFilter tf)throws IOException{
        
        ObjectMapper mapper=new ObjectMapper();
        String  jsonData = mapper.writeValueAsString(tf);
        
        String url="http://localhost:8080/RequestsDispatcher/RequestsHandler";
        MultivaluedMap<String,Object> headers=new MultivaluedHashMap();
        headers.add("CMD", "002");
        headers.add("Domain","paymentgw");
        
        Response response = CommonLibrary.sendRESTRequest(url, jsonData,headers, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse = response.readEntity(String.class);
        overAllIgnitePaymentsTransactionsList=(PaymentsTransactionsList)mapper.readValue(jsonResponse, PaymentsTransactionsList.class);
        
        if(subMerchantLength==null){
            paymentsTransactionsList=overAllIgnitePaymentsTransactionsList;
        }
        
        else{
            List<PaymentTransaction> ptList=new ArrayList<>();
            for(PaymentTransaction pt : overAllIgnitePaymentsTransactionsList.getTxList()){
                
                if(pt.getMerchantTransactionId().trim().length()==subMerchantLength){
                    ptList.add(pt);
                }
                paymentsTransactionsList.setTxList(ptList);
            }
            
        }
        
    }
    
    
    public void transactionForView(PaymentTransaction pt){
        
        paymentTransaction=pt;
        
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
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }
    
    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
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
     * @return the filterDate
     */
    public String getFilterDate() {
        return filterDate;
    }
    
    /**
     * @param filterDate the filterDate to set
     */
    public void setFilterDate(String filterDate) {
        this.filterDate = filterDate;
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
     * @return the page
     */
    public String getPage() {
        return page;
    }
    
    /**
     * @param page the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }
    
    
    
    /**
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }
    
    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    
    /**
     * @return the overAllIgnitePaymentsTransactionsList
     */
    public PaymentsTransactionsList getOverAllIgnitePaymentsTransactionsList() {
        return overAllIgnitePaymentsTransactionsList;
    }
    
    /**
     * @param overAllIgnitePaymentsTransactionsList the overAllIgnitePaymentsTransactionsList to set
     */
    public void setOverAllIgnitePaymentsTransactionsList(PaymentsTransactionsList overAllIgnitePaymentsTransactionsList) {
        this.overAllIgnitePaymentsTransactionsList = overAllIgnitePaymentsTransactionsList;
    }
    
    /**
     * @return the subMerchantLength
     */
    public Integer getSubMerchantLength() {
        return subMerchantLength;
    }
    
    /**
     * @param subMerchantLength the subMerchantLength to set
     */
    public void setSubMerchantLength(Integer subMerchantLength) {
        this.subMerchantLength = subMerchantLength;
    }
    
    
    
    
    
    
}
