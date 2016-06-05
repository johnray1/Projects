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
@XmlRootElement(name="pumpdataop")
public class PumpDataOp {
    
    private String pumpId;
    private String pumpName;
    private String index;

    public PumpDataOp() {
    }

    public PumpDataOp(String pumpId, String pumpName, String index) {
        this.pumpId = pumpId;
        this.pumpName = pumpName;
        this.index = index;
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
     * @return the index
     */
    public String getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(String index) {
        this.index = index;
    }
    
    
    
}
