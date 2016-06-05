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
@XmlRootElement(name="voucherip")
public class VoucherIp {
    
    private String uId;
    private String imei;
    private String barnumber;
    private String barformat;

    public VoucherIp() {
    }

    public VoucherIp(String uId, String imei, String barnumber, String barformat) {
        this.uId = uId;
        this.imei = imei;
        this.barnumber = barnumber;
        this.barformat = barformat;
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
     * @return the barformat
     */
    public String getBarformat() {
        return barformat;
    }

    /**
     * @param barformat the barformat to set
     */
    public void setBarformat(String barformat) {
        this.barformat = barformat;
    }

    
    
}
