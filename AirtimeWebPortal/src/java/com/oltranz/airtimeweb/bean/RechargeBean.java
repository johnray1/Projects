/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author John
 */
@ManagedBean(name="RechargeBean")
@SessionScoped
public class RechargeBean {
    
    private Boolean faceMessage=false;
    
    
    
    private String phone1;
    private Double amount1;
    
    private String phone2;
    private Double amount2;
    
    private String phone3;
    private Double amount3;
    
    
//-------------------------------------------------SEND ONE---------------------------------------------------------------------------
    
    public void confirmViewOne(){
        
    }
    
    public void approveOne(){
        String msisdn=phone1;
        Double amt=amount1;
    }
    
    public void cancelOne(){
        this.phone1=null;
        this.amount1=null;
        
    }
    
    
    
//-----------------------------------------------SEND MANY-----------------------------------------------------------------------------
    
    public void confirmViewMany(){
        
    }
    
    public void approveMany(){
        
        
        
    }
    
    public void cancelMany(){
        
        this.phone1=null;
        this.amount1=null;
        
        this.phone2=null;
        this.amount2=null;
        
        this.phone3=null;
        this.amount3=null;
        
        
    }

    
//-----------------------------------------------SEND FAVOURITE-----------------------------------------------------------------------------    
    
    public void confirmViewFav(){
        
    }
    
    public void approveFav(){
        
    }
    
    public void cancelFav(){
        
    }
    
    
    
    /**
     * @return the faceMessage
     */
    public Boolean getFaceMessage() {
        return faceMessage;
    }
    
    /**
     * @param faceMessage the faceMessage to set
     */
    public void setFaceMessage(Boolean faceMessage) {
        this.faceMessage = faceMessage;
    }
    
    
    /**
     * @return the phone2
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * @return the amount2
     */
    public Double getAmount2() {
        return amount2;
    }

    /**
     * @param amount2 the amount2 to set
     */
    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    /**
     * @return the phone3
     */
    public String getPhone3() {
        return phone3;
    }

    /**
     * @param phone3 the phone3 to set
     */
    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    /**
     * @return the amount3
     */
    public Double getAmount3() {
        return amount3;
    }

    /**
     * @param amount3 the amount3 to set
     */
    public void setAmount3(Double amount3) {
        this.amount3 = amount3;
    }

    /**
     * @return the phone1
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * @param phone1 the phone1 to set
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * @return the amount1
     */
    public Double getAmount1() {
        return amount1;
    }

    /**
     * @param amount1 the amount1 to set
     */
    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }

    
    
}
