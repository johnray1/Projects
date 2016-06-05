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
@Table(name = "users_roles_for_services_providers")

@XmlRootElement
public class UserRoleForServiceProvider implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }  
    
    @EmbeddedId
    private UserRoleForServiceProviderPK userRoleForServiceProviderPK;
    
     public UserRoleForServiceProvider(){
        
    }
    
    public UserRoleForServiceProvider(UserRoleForServiceProviderPK userRoleForServiceProviderPK){
        this.userRoleForServiceProviderPK=userRoleForServiceProviderPK;
    }
    
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUserRoleForServiceProviderPK() != null ? getUserRoleForServiceProviderPK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoleForServiceProviderPK)) {
            return false;
        }
        UserRoleForServiceProvider other = (UserRoleForServiceProvider) object;
        if ((this.getUserRoleForServiceProviderPK() == null && other.getUserRoleForServiceProviderPK() != null) || (this.getUserRoleForServiceProviderPK() != null && !this.userRoleForServiceProviderPK.equals(other.userRoleForServiceProviderPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.UserRoleForServiceProvider[ UserRoleForServiceProviderPK=" + getUserRoleForServiceProviderPK() + " ]";
    }

    /**
     * @return the userRoleForServiceProviderPK
     */
    public UserRoleForServiceProviderPK getUserRoleForServiceProviderPK() {
        return userRoleForServiceProviderPK;
    }

    /**
     * @param userRoleForServiceProviderPK the userRoleForServiceProviderPK to set
     */
    public void setUserRoleForServiceProviderPK(UserRoleForServiceProviderPK userRoleForServiceProviderPK) {
        this.userRoleForServiceProviderPK = userRoleForServiceProviderPK;
    }


    

    
}
