/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John
 */
@Entity
@Table(name = "user", catalog = "PetroStationManagerDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"u_pin"})})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUId", query = "SELECT u FROM User u WHERE u.uId = :uId"),
    @NamedQuery(name = "User.findByUName", query = "SELECT u FROM User u WHERE u.uName = :uName"),
    @NamedQuery(name = "User.findByUType", query = "SELECT u FROM User u WHERE u.uType = :uType"),
    @NamedQuery(name = "User.findByUIdcard", query = "SELECT u FROM User u WHERE u.uIdcard = :uIdcard"),
    @NamedQuery(name = "User.findByUPin", query = "SELECT u FROM User u WHERE u.uPin = :uPin"),
    @NamedQuery(name = "User.findByUNo", query = "SELECT u FROM User u WHERE u.uNo = :uNo"),
    @NamedQuery(name = "User.findByFname", query = "SELECT u FROM User u WHERE u.fname = :fname"),
    @NamedQuery(name = "User.findByLname", query = "SELECT u FROM User u WHERE u.lname = :lname"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUStatus", query = "SELECT u FROM User u WHERE u.uStatus = :uStatus"),
    @NamedQuery(name = "User.findByWebStatus", query = "SELECT u FROM User u WHERE u.webStatus = :webStatus")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "u_id", nullable = false)
    private Integer uId;
    @Size(max = 50)
    @Column(name = "u_name", length = 50)
    private String uName;
    @Size(max = 50)
    @Column(name = "u_type", length = 50)
    private String uType;
    @Size(max = 50)
    @Column(name = "u_idcard", length = 50)
    private String uIdcard;
    @Size(max = 255)
    @Column(name = "u_pin", length = 255)
    private String uPin;
    @Size(max = 50)
    @Column(name = "u_no", length = 50)
    private String uNo;
    @Size(max = 55)
    @Column(name = "fname", length = 55)
    private String fname;
    @Size(max = 55)
    @Column(name = "lname", length = 55)
    private String lname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 55)
    @Column(name = "email", length = 55)
    private String email;
    @Size(max = 50)
    @Column(name = "u_status", length = 50)
    private String uStatus;
    @Size(max = 20)
    @Column(name = "web_status", length = 20)
    private String webStatus;
    @JoinColumn(name = "b_id", referencedColumnName = "b_id", nullable = false)
    @ManyToOne(optional = false)
    private Branch bId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uId")
    private Collection<Transaction> transactionCollection;

    public User() {
    }

    public User(Integer uId) {
        this.uId = uId;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUType() {
        return uType;
    }

    public void setUType(String uType) {
        this.uType = uType;
    }

    public String getUIdcard() {
        return uIdcard;
    }

    public void setUIdcard(String uIdcard) {
        this.uIdcard = uIdcard;
    }

    public String getUPin() {
        return uPin;
    }

    public void setUPin(String uPin) {
        this.uPin = uPin;
    }

    public String getUNo() {
        return uNo;
    }

    public void setUNo(String uNo) {
        this.uNo = uNo;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUStatus() {
        return uStatus;
    }

    public void setUStatus(String uStatus) {
        this.uStatus = uStatus;
    }

    public String getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(String webStatus) {
        this.webStatus = webStatus;
    }

    public Branch getBId() {
        return bId;
    }

    public void setBId(Branch bId) {
        this.bId = bId;
    }

    @XmlTransient
    public Collection<Transaction> getTransactionCollection() {
        return transactionCollection;
    }

    public void setTransactionCollection(Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uId != null ? uId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.uId == null && other.uId != null) || (this.uId != null && !this.uId.equals(other.uId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ uId=" + uId + " ]";
    }
    
}
