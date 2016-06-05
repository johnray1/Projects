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
@XmlRootElement(name="bId")
public class BranchIp {
    
    private String bId;

    public BranchIp() {
    }

    public BranchIp(String bId) {
        this.bId = bId;
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
}
