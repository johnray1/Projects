/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portalbeans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@XmlRootElement
public class LoggerPortal {
    
    private String loggerId;
    private String loggerName;
    private String loggerType;
    private String loginTime;
    private String logoutTime;
    private String fastname;
    private String lastname;
    private String email;
    private String phno;

    /**
     * @return the loggerId
     */
    public String getLoggerId() {
        return loggerId;
    }

    /**
     * @param loggerId the loggerId to set
     */
    public void setLoggerId(String loggerId) {
        this.loggerId = loggerId;
    }

    /**
     * @return the loggerName
     */
    public String getLoggerName() {
        return loggerName;
    }

    /**
     * @param loggerName the loggerName to set
     */
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    /**
     * @return the loggerType
     */
    public String getLoggerType() {
        return loggerType;
    }

    /**
     * @param loggerType the loggerType to set
     */
    public void setLoggerType(String loggerType) {
        this.loggerType = loggerType;
    }

    /**
     * @return the loginTime
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime the loginTime to set
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return the logoutTime
     */
    public String getLogoutTime() {
        return logoutTime;
    }

    /**
     * @param logoutTime the logoutTime to set
     */
    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    /**
     * @return the fastname
     */
    public String getFastname() {
        return fastname;
    }

    /**
     * @param fastname the fastname to set
     */
    public void setFastname(String fastname) {
        this.fastname = fastname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phno
     */
    public String getPhno() {
        return phno;
    }

    /**
     * @param phno the phno to set
     */
    public void setPhno(String phno) {
        this.phno = phno;
    }
    
    
}
