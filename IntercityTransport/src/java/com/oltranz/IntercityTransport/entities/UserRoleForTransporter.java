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
@Table(name = "users_roles_for_transporters")

@XmlRootElement
public class UserRoleForTransporter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }  
    
    @EmbeddedId
    private UserRoleForTransporterPK userRoleForTransporterPK;
    
    public UserRoleForTransporter(){
        
    }
    
    public UserRoleForTransporter(UserRoleForTransporterPK userRoleForTransporterPK){
        this.userRoleForTransporterPK=userRoleForTransporterPK;
    }
    
      @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUserRoleForTransporterPK() != null ? getUserRoleForTransporterPK().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoleForTransporterPK)) {
            return false;
        }
        UserRoleForTransporter other = (UserRoleForTransporter) object;
        if ((this.getUserRoleForTransporterPK() == null && other.getUserRoleForTransporterPK() != null) || (this.getUserRoleForTransporterPK() != null && !this.userRoleForTransporterPK.equals(other.userRoleForTransporterPK))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransporter.Entities.UserRoleForTransporter[ UserRoleForTransporterPK=" + getUserRoleForTransporterPK() + " ]";
    }

    /**
     * @return the userRoleForTransporterPK
     */
    public UserRoleForTransporterPK getUserRoleForTransporterPK() {
        return userRoleForTransporterPK;
    }

    /**
     * @param userRoleForTransporterPK the userRoleForTransporterPK to set
     */
    public void setUserRoleForTransporterPK(UserRoleForTransporterPK userRoleForTransporterPK) {
        this.userRoleForTransporterPK = userRoleForTransporterPK;
    }


    

    
}
