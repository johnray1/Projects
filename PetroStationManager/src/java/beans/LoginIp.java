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
@XmlRootElement(name="loginip")
public class LoginIp {
    
    private String pin;
    private String imei;

    public LoginIp() {
    }

    public LoginIp(String pin, String imei) {
        this.pin = pin;
        this.imei = imei;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
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

    
    
}
