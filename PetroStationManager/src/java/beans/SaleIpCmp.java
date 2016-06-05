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
@XmlRootElement(name="saleipcmp")
public class SaleIpCmp {
    
    private String pumpId;
    private String pId;
    private String amount;
    private String quantity;
    private String pMode;
    private String custTel;
    private String cmpName;
    private String tin;
    private String uId;
    private String bId;
    private String imei;

    public SaleIpCmp() {
    }

    public SaleIpCmp(String pumpId, String pId, String amount, String quantity, String pMode, String custTel, String cmpName, String tin, String uId, String bId, String imei) {
        this.pumpId = pumpId;
        this.pId = pId;
        this.amount = amount;
        this.quantity = quantity;
        this.pMode = pMode;
        this.custTel = custTel;
        this.cmpName = cmpName;
        this.tin = tin;
        this.uId = uId;
        this.bId = bId;
        this.imei = imei;
    }

    /**
     * @return the pumpId
     */
    public String getPumpId() {
        return pumpId;
    }

    /**
     * @param pumpId the pumpId to set
     */
    public void setPumpId(String pumpId) {
        this.pumpId = pumpId;
    }

    /**
     * @return the pId
     */
    public String getpId() {
        return pId;
    }

    /**
     * @param pId the pId to set
     */
    public void setpId(String pId) {
        this.pId = pId;
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
     * @return the cmpName
     */
    public String getCmpName() {
        return cmpName;
    }

    /**
     * @param cmpName the cmpName to set
     */
    public void setCmpName(String cmpName) {
        this.cmpName = cmpName;
    }

    /**
     * @return the tin
     */
    public String getTin() {
        return tin;
    }

    /**
     * @param tin the tin to set
     */
    public void setTin(String tin) {
        this.tin = tin;
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
