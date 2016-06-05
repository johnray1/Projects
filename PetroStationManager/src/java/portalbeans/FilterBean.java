/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package portalbeans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@XmlRootElement(name = "FILTER")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilterBean {
    
    @XmlElement(name = "BNAME")
    private String branchName;
    
    @XmlElement(name = "PTYPE")
    private String paymentType;
    
    @XmlElement(name = "PSTATUS")
    private String paymentStatus;
     
    @XmlElement(name = "UNAME")
    private String userName;
    
    @XmlElement(name = "PNAME")
    private String productName;
    
    @XmlElement(name = "CTEL")
    private String custTel;

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
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

    
    
    
    
}
