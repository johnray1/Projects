/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@XmlRootElement(name = "VOUCHER")
@XmlAccessorType (XmlAccessType.FIELD)
public class VoucherGenerate {
    
    @XmlElement(name = "NO")
    private String no;
    
    @XmlElement(name = "AMOUNT")
    private String amount;
    
    public VoucherGenerate() {
    }
    
    public VoucherGenerate(String no, String amount) {
        this.no = no;
        this.amount = amount;
    }
    
    
    
    
    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }
    
    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
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
    
    
    
}
