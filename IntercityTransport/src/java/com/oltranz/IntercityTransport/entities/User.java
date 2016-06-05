/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manzi
 */
@Entity
@SequenceGenerator(name="userSeq", initialValue=1, allocationSize=1)
@Table(name = "users")
@XmlRootElement
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="userSeq")
    private Integer id;
    
   
    @Column(name = "fname", length = 100)
    private String fname;
    
   
    @Column(name = "other_names", length = 150)
    private String otherNames;
    
    @Column(name = "email",unique = true, length = 100)
    private String email;
   
   
    @Column(name = "password", length = 255)
    private String password;
    
    
    @Column(name = "pin", length = 100)
    private String pin;
    
    @Column(name = "permissions")
    private String permissions="00000000000000000000000000000000";
    
    @Column(name = "status")
    private int status=7;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone_number",unique = true, length = 100)
    private String phoneNumber;    
     
    
    @Column(name = "gender", length = 50)
    private String gender;
    
    @Column(name = "details", length = 255)
    private String details;
    
    public User(){
        
    }
    public User(String fname,String otherNames,String email,String password,String pin,String PhoneNumber,String gender,String details){
        this.fname=fname;
        this.otherNames=otherNames;
        this.email=email;
        this.password=password;
        this.pin=pin;
        this.phoneNumber=PhoneNumber;
        this.gender=gender;
        this.details=details;        
    }
      public User(String fname,String otherNames,String email,String password,String pin,String PhoneNumber,String gender,String permissions,String details){
        this.fname=fname;
        this.otherNames=otherNames;
        this.email=email;
        this.password=password;
        this.pin=pin;
        this.phoneNumber=PhoneNumber;
        this.gender=gender;
        this.details=details;  
        this.permissions=permissions;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransport.Entities.user[ id=" + getId() + " ]";
    }
    
    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }
    
    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }
    
    /**
     * @return the otherNames
     */
    public String getOtherNames() {
        return otherNames;
    }
    
    /**
     * @param otherNames the otherNames to set
     */
    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * @param PhoneNumber the PhoneNumber to set
     */
    public void setPhoneNumber(String PhoneNumber) {
        this.phoneNumber = PhoneNumber;
    }
    
    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }
    
    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the gender
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the permissions
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
    
}
