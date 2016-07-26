/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.bean;

import com.oltranz.airtimeweb.model.TopUpBean;
import com.oltranz.airtimeweb.model.TopUpRequest;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author John
 */
@ManagedBean(name="RechargeBean")
@SessionScoped
public class RechargeBean {
    
    private Boolean faceMessage=false;
    
    
    private TopUpRequest topUpRequest;
    
    @ManagedProperty(value="#{TopUpBean}")
    private TopUpBean topUpBean;
    
    @ManagedProperty(value="#{TopUpBean}")
    private TopUpBean topUpBean2;
    
    @ManagedProperty(value="#{TopUpBean}")
    private TopUpBean topUpBean3;
    
    
    
    public void confirm(){
        
    }
    
    public void approve(){
        String s=topUpBean.getMsisdn();
        String z="";
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
     * @return the topUpRequest
     */
    public TopUpRequest getTopUpRequest() {
        return topUpRequest;
    }

    /**
     * @param topUpRequest the topUpRequest to set
     */
    public void setTopUpRequest(TopUpRequest topUpRequest) {
        this.topUpRequest = topUpRequest;
    }

    /**
     * @return the topUpBean
     */
    public TopUpBean getTopUpBean() {
        return topUpBean;
    }

    /**
     * @param topUpBean the topUpBean to set
     */
    public void setTopUpBean(TopUpBean topUpBean) {
        this.topUpBean = topUpBean;
    }

    /**
     * @return the topUpBean2
     */
    public TopUpBean getTopUpBean2() {
        return topUpBean2;
    }

    /**
     * @param topUpBean2 the topUpBean2 to set
     */
    public void setTopUpBean2(TopUpBean topUpBean2) {
        this.topUpBean2 = topUpBean2;
    }

    /**
     * @return the topUpBean3
     */
    public TopUpBean getTopUpBean3() {
        return topUpBean3;
    }

    /**
     * @param topUpBean3 the topUpBean3 to set
     */
    public void setTopUpBean3(TopUpBean topUpBean3) {
        this.topUpBean3 = topUpBean3;
    }
    
    
}
