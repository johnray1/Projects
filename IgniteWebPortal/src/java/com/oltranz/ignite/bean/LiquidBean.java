/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.ignite.bean;


import com.oltranz.ignite.library.CommonLibrary;
import com.oltranz.ignite.model.Liquidation;
import com.oltranz.ignite.model.LiquidationEditModel;
import com.oltranz.ignite.model.LiquidationList;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="LiquidBean")
@SessionScoped
public class LiquidBean implements Serializable{
    
    private String id;
    private double amount;
    private String telcoName;
    private String refNo;
    private String refType;
    private Double latestLiquidAmount;
    private Double totalLiquidAmount;
    private HttpSession session = SessionBean.getSession();
    private String saveActionName="Save";
    private String popUpLabel;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    private LiquidationList liquidationList;
    
    public void liquids(){
        
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setUserClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setLiquidClassName("active");
        
        try {
            
            liquidList();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("liq.xhtml");
            
            
        }
        catch (Exception ex) {
            Logger.getLogger(LiquidBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void liquidList()throws IOException{
        
        ObjectMapper mapper=new ObjectMapper();
        String url="http://localhost:8080/IgniteCore/LiquidationManagementService/liquidations";
        
        
        Response response = CommonLibrary.sendRESTRequest(url, null,null, MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        liquidationList=(LiquidationList)mapper.readValue(jsonResponse, LiquidationList.class);
        
        latestLiquidAmount=liquidationList.getLiquidationList().get(liquidationList.getLiquidationList().size()-1).getAmount();
        totalLiquidAmount=0.0;
        for(Liquidation l:liquidationList.getLiquidationList()){
            totalLiquidAmount+=l.getAmount();
        }
        
    }
    
    public void liquidForCreate(){
        this.id=null;
        this.amount=0;
        this.telcoName="";
        this.refType=null;
        this.refNo=null;
        popUpLabel="ADD LIQUIDATION";
        saveActionName="CREATE";
    }
    
    
    public void liquidForEdit(Liquidation le){
        this.id=le.getId();
        this.amount=le.getAmount().longValue();
        this.telcoName=le.getTelcoName();
        this.refType=le.getReferenceType();
        this.refNo=le.getReference();
        popUpLabel="EDIT LIQUIDATION";
        saveActionName="UPDATE";
    }
    
    
    public void saveLiquidation(){
        try {
            this.setLatestLiquidAmount((Double) amount);
            
            LiquidationEditModel lqne=new LiquidationEditModel();
            lqne.setId(id);
            lqne.setAmount(amount);
            lqne.setTelcoName(telcoName.toUpperCase());
            lqne.setCreatedBy(((String)session.getAttribute("username")).toUpperCase());
            lqne.setReference(refNo);
            lqne.setReferenceType(refType);
            
            ObjectMapper mapper=new ObjectMapper();
            String url="http://localhost:8080/IgniteCore/LiquidationManagementService/liquidation/create";
            
            String jsonData = mapper.writeValueAsString(lqne);
            
            
            Response response = CommonLibrary.sendRESTRequest(url, jsonData, null, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse = response.readEntity(String.class);
            
            liquids();
        }
        catch (IOException ex) {
            Logger.getLogger(LiquidBean.class.getName()).log(Level.SEVERE, null, ex);
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
     * @return the liquidationList
     */
    public LiquidationList getLiquidationList() {
        return liquidationList;
    }
    
    /**
     * @param liquidationList the liquidationList to set
     */
    public void setLiquidationList(LiquidationList liquidationList) {
        this.liquidationList = liquidationList;
    }
    
    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    /**
     * @return the telcoName
     */
    public String getTelcoName() {
        return telcoName;
    }
    
    /**
     * @param telcoName the telcoName to set
     */
    public void setTelcoName(String telcoName) {
        this.telcoName = telcoName;
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
     * @return the saveActionName
     */
    public String getSaveActionName() {
        return saveActionName;
    }
    
    /**
     * @param saveActionName the saveActionName to set
     */
    public void setSaveActionName(String saveActionName) {
        this.saveActionName = saveActionName;
    }
    
    /**
     * @return the popUpLabel
     */
    public String getPopUpLabel() {
        return popUpLabel;
    }
    
    /**
     * @param popUpLabel the popUpLabel to set
     */
    public void setPopUpLabel(String popUpLabel) {
        this.popUpLabel = popUpLabel;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * @param refNo the refNo to set
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     * @return the refType
     */
    public String getRefType() {
        return refType;
    }

    /**
     * @param refType the refType to set
     */
    public void setRefType(String refType) {
        this.refType = refType;
    }

    /**
     * @return the latestLiquidAmount
     */
    public Double getLatestLiquidAmount() {
        return latestLiquidAmount;
    }

    /**
     * @param latestLiquidAmount the latestLiquidAmount to set
     */
    public void setLatestLiquidAmount(Double latestLiquidAmount) {
        this.latestLiquidAmount = latestLiquidAmount;
    }

    /**
     * @return the totalLiquidAmount
     */
    public Double getTotalLiquidAmount() {
        return totalLiquidAmount;
    }

    /**
     * @param totalLiquidAmount the totalLiquidAmount to set
     */
    public void setTotalLiquidAmount(Double totalLiquidAmount) {
        this.totalLiquidAmount = totalLiquidAmount;
    }

    
    
    
    
    
    
    
}
