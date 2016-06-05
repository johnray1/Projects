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
@XmlRootElement(name="traip")
public class TraIp {
    
    private String tId;

    public TraIp() {
    }

    public TraIp(String tId) {
        this.tId = tId;
    }

    /**
     * @return the tId
     */
    public String gettId() {
        return tId;
    }

    /**
     * @param tId the tId to set
     */
    public void settId(String tId) {
        this.tId = tId;
    }
    
    
    
}
