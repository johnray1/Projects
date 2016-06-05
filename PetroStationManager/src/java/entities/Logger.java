/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "logger", catalog = "PetroStationManagerDB", schema = "")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Logger.findAll", query = "SELECT l FROM Logger l"),
    @NamedQuery(name = "Logger.findByLoggerId", query = "SELECT l FROM Logger l WHERE l.loggerId = :loggerId"),
    @NamedQuery(name = "Logger.findByLoggerName", query = "SELECT l FROM Logger l WHERE l.loggerName = :loggerName"),
    @NamedQuery(name = "Logger.findByLoggerType", query = "SELECT l FROM Logger l WHERE l.loggerType = :loggerType"),
    @NamedQuery(name = "Logger.findByLoginTime", query = "SELECT l FROM Logger l WHERE l.loginTime = :loginTime"),
    @NamedQuery(name = "Logger.findByLogoutTime", query = "SELECT l FROM Logger l WHERE l.logoutTime = :logoutTime"),
    @NamedQuery(name = "Logger.findByFirstName", query = "SELECT l FROM Logger l WHERE l.firstName = :firstName"),
    @NamedQuery(name = "Logger.findByLastName", query = "SELECT l FROM Logger l WHERE l.lastName = :lastName"),
    @NamedQuery(name = "Logger.findByEmail", query = "SELECT l FROM Logger l WHERE l.email = :email"),
    @NamedQuery(name = "Logger.findByPhoneNo", query = "SELECT l FROM Logger l WHERE l.phoneNo = :phoneNo"),
    @NamedQuery(name = "Logger.findByLogMachineType", query = "SELECT l FROM Logger l WHERE l.logMachineType = :logMachineType")})
public class Logger implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "logger_id", nullable = false)
    private Integer loggerId;
    @Size(max = 55)
    @Column(name = "logger_name", length = 55)
    private String loggerName;
    @Size(max = 25)
    @Column(name = "logger_type", length = 25)
    private String loggerType;
    @Column(name = "login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;
    @Column(name = "logout_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTime;
    @Size(max = 55)
    @Column(name = "first_name", length = 55)
    private String firstName;
    @Size(max = 55)
    @Column(name = "last_name", length = 55)
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 55)
    @Column(name = "email", length = 55)
    private String email;
    @Size(max = 15)
    @Column(name = "phone_no", length = 15)
    private String phoneNo;
    @Size(max = 15)
    @Column(name = "log_machine_type", length = 15)
    private String logMachineType;

    public Logger() {
    }

    public Logger(Integer loggerId) {
        this.loggerId = loggerId;
    }

    public Integer getLoggerId() {
        return loggerId;
    }

    public void setLoggerId(Integer loggerId) {
        this.loggerId = loggerId;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerType() {
        return loggerType;
    }

    public void setLoggerType(String loggerType) {
        this.loggerType = loggerType;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLogMachineType() {
        return logMachineType;
    }

    public void setLogMachineType(String logMachineType) {
        this.logMachineType = logMachineType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loggerId != null ? loggerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logger)) {
            return false;
        }
        Logger other = (Logger) object;
        if ((this.loggerId == null && other.loggerId != null) || (this.loggerId != null && !this.loggerId.equals(other.loggerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Logger[ loggerId=" + loggerId + " ]";
    }
    
}
