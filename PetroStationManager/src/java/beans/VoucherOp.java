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
@XmlRootElement(name="voucherop")
public class VoucherOp {
    
    private String barnumber;
    private String baramount;
    private String validation;

    public VoucherOp() {
    }

    public VoucherOp(String barnumber, String baramount, String validation) {
        this.barnumber = barnumber;
        this.baramount = baramount;
        this.validation = validation;
    }

    /**
     * @return the barnumber
     */
    public String getBarnumber() {
        return barnumber;
    }

    /**
     * @param barnumber the barnumber to set
     */
    public void setBarnumber(String barnumber) {
        this.barnumber = barnumber;
    }

    /**
     * @return the baramount
     */
    public String getBaramount() {
        return baramount;
    }

    /**
     * @param baramount the baramount to set
     */
    public void setBaramount(String baramount) {
        this.baramount = baramount;
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
    
    
    
}
