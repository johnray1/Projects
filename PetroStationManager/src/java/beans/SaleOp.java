/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@Stateless
@XmlRootElement(name="saleop")
public class SaleOp {
    
    private String validation;
    private String traId;
    private String pStatus;
    private String pumpName;
    private String pName;
    private String amount;
    private String quantity;
    private String pMode;
    private String custTel;
    private String uId;
    private String bId;
    private String imei;

    public SaleOp() {
    }

    public SaleOp(String validation, String traId, String pStatus, String pumpName, String pName, String amount, String quantity, String pMode, String custTel, String uId, String bId, String imei) {
        this.validation = validation;
        this.traId = traId;
        this.pStatus = pStatus;
        this.pumpName = pumpName;
        this.pName = pName;
        this.amount = amount;
        this.quantity = quantity;
        this.pMode = pMode;
        this.custTel = custTel;
        this.uId = uId;
        this.bId = bId;
        this.imei = imei;
    }

    /**
     * @return the validation
     */
    public String getValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(String validation) {
        this.validation = validation;
    }

    /**
     * @return the traId
     */
    public String getTraId() {
        return traId;
    }

    /**
     * @param traId the traId to set
     */
    public void setTraId(String traId) {
        this.traId = traId;
    }

    /**
     * @return the pStatus
     */
    public String getpStatus() {
        return pStatus;
    }

    /**
     * @param pStatus the pStatus to set
     */
    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    /**
     * @return the pumpName
     */
    public String getPumpName() {
        return pumpName;
    }

    /**
     * @param pumpName the pumpName to set
     */
    public void setPumpName(String pumpName) {
        this.pumpName = pumpName;
    }

    /**
     * @return the pName
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName the pName to set
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the pMode
     */
    public String getpMode() {
        return pMode;
    }

    /**
     * @param pMode the pMode to set
     */
    public void setpMode(String pMode) {
        this.pMode = pMode;
    }

    /**
     * @return the custTel
     */
    public String getCustTel() {
        return custTel;
    }

    /**
     * @param custTel the custTel to set
     */
    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }

    /**
     * @return the uId
     */
    public String getuId() {
        return uId;
    }

    /**
     * @param uId the uId to set
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * @return the bId
     */
    public String getbId() {
        return bId;
    }

    /**
     * @param bId the bId to set
     */
    public void setbId(String bId) {
        this.bId = bId;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

   

   
}
