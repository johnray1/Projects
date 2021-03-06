/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@XmlRootElement(name = "COMMAND")
@XmlAccessorType (XmlAccessType.FIELD)
public class SpPaymentResp {
    
   
    private String CONTRACTID;
    
    private String TRANSID;
    
    private String SPTRANSID;
    
    private String STATUSCODE;
    
    private String STATUSDESC;

    public SpPaymentResp() {
    }

    public SpPaymentResp(String CONTRACTID, String TRANSID, String SPTRANSID, String STATUSCODE, String STATUSDESC) {
        this.CONTRACTID = CONTRACTID;
        this.TRANSID = TRANSID;
        this.SPTRANSID = SPTRANSID;
        this.STATUSCODE = STATUSCODE;
        this.STATUSDESC = STATUSDESC;
    }

    /**
     * @return the CONTRACTID
     */
    public String getCONTRACTID() {
        return CONTRACTID;
    }

    /**
     * @param CONTRACTID the CONTRACTID to set
     */
    public void setCONTRACTID(String CONTRACTID) {
        this.CONTRACTID = CONTRACTID;
    }

    /**
     * @return the TRANSID
     */
    public String getTRANSID() {
        return TRANSID;
    }

    /**
     * @param TRANSID the TRANSID to set
     */
    public void setTRANSID(String TRANSID) {
        this.TRANSID = TRANSID;
    }

    /**
     * @return the SPTRANSID
     */
    public String getSPTRANSID() {
        return SPTRANSID;
    }

    /**
     * @param SPTRANSID the SPTRANSID to set
     */
    public void setSPTRANSID(String SPTRANSID) {
        this.SPTRANSID = SPTRANSID;
    }

    /**
     * @return the STATUSCODE
     */
    public String getSTATUSCODE() {
        return STATUSCODE;
    }

    /**
     * @param STATUSCODE the STATUSCODE to set
     */
    public void setSTATUSCODE(String STATUSCODE) {
        this.STATUSCODE = STATUSCODE;
    }

    /**
     * @return the STATUSDESC
     */
    public String getSTATUSDESC() {
        return STATUSDESC;
    }

    /**
     * @param STATUSDESC the STATUSDESC to set
     */
    public void setSTATUSDESC(String STATUSDESC) {
        this.STATUSDESC = STATUSDESC;
    }
    

    
    
}
