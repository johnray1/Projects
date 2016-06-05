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
@XmlRootElement(name="loginop")
public class LoginOp {
    
    private String validation;
    private String uId;
    private String uName;
    private String uType;
    private String uStatus;
    private String bId;
    private String bName;
//  private List<PumpDataOp> pumplist = new ArrayList<PumpDataOp>();

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
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the uType
     */
    public String getuType() {
        return uType;
    }

    /**
     * @param uType the uType to set
     */
    public void setuType(String uType) {
        this.uType = uType;
    }

    /**
     * @return the uStatus
     */
    public String getuStatus() {
        return uStatus;
    }

    /**
     * @param uStatus the uStatus to set
     */
    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
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
     * @return the bName
     */
    public String getbName() {
        return bName;
    }

    /**
     * @param bName the bName to set
     */
    public void setbName(String bName) {
        this.bName = bName;
    }

   
    
    
}
