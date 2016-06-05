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
@XmlRootElement(name="product")
public class ProductDataOp {
    
    private String pId;
    private String pName;
    private String uPrice;
    private String unity;

    public ProductDataOp() {
    }
    
    public ProductDataOp(String pId, String pName, String uPrice, String unity) {
        this.pId = pId;
        this.pName = pName;
        this.uPrice = uPrice;
        this.unity = unity;
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
     * @return the uPrice
     */
    public String getuPrice() {
        return uPrice;
    }

    /**
     * @param uPrice the uPrice to set
     */
    public void setuPrice(String uPrice) {
        this.uPrice = uPrice;
    }

    /**
     * @return the unity
     */
    public String getUnity() {
        return unity;
    }

    /**
     * @param unity the unity to set
     */
    public void setUnity(String unity) {
        this.unity = unity;
    }

    

    
    
}
