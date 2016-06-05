/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "users_in_roles")

@XmlRootElement
public class UserInRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }  
    
    @EmbeddedId
    private UserInRolePK userInRolePK;
    
    public UserInRole(){
        
    }
    
    public UserInRole(Integer userId, Integer roleId){
        userInRolePK=new UserInRolePK();
        this.userInRolePK.setRoleId(roleId);
        this.userInRolePK.setUserId(userId);
    }
      public UserInRole(UserInRolePK userInRolePK){
        this.userInRolePK=userInRolePK;
    }
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUserInRolePK() != null ? getUserInRolePK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInRolePK)) {
            return false;
        }
        UserInRole other = (UserInRole) object;
        if ((this.getUserInRolePK() == null && other.getUserInRolePK() != null) || (this.getUserInRolePK() != null && !this.userInRolePK.equals(other.userInRolePK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransport.Entities.UserInRole[ userInRolePK=" + getUserInRolePK() + " ]";
    }

    /**
     * @return the userInRolePK
     */
    public UserInRolePK getUserInRolePK() {
        return userInRolePK;
    }

    /**
     * @param userInRolePK the userInRolePK to set
     */
    public void setUserRolePK(UserInRolePK userInRolePK) {
        this.userInRolePK = userInRolePK;
    }

    

    
}
