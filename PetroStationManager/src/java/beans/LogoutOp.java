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
@XmlRootElement(name="logoutop")
public class LogoutOp {
    
    private String uId;
    private String uName;
    private String webStatus;

    public LogoutOp() {
    }

    public LogoutOp(String uId, String uName, String webStatus) {
        this.uId = uId;
        this.uName = uName;
        this.webStatus = webStatus;
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
     * @return the webStatus
     */
    public String getWebStatus() {
        return webStatus;
    }

    /**
     * @param webStatus the webStatus to set
     */
    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus;
    }
    
    
    
}
