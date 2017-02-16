/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "actions_on_objects")

public class ActionOnObject implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "action_id")
    private Integer actionId;
    
    @Column(name = "object_id")
    private Integer objectId;
    
    @Column(name = "descr")
    private String descr;
    
    public ActionOnObject(){
        
    }
    
    public ActionOnObject(Integer id,Integer actionId, Integer objectId, String descr) {
        this.id = id;
        this.actionId = actionId;
        this.objectId = objectId;
        this.descr = descr;
    }
    
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionOnObject)) {
            return false;
        }
        ActionOnObject other = (ActionOnObject) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "entities.AAA.oltranz.com.actions_on_objects[ id=" + getId() + " ]";
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
     * @return the actionId
     */
    public Integer getActionId() {
        return actionId;
    }
    
    /**
     * @param actionId the actionId to set
     */
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }
    
    
    
    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    /**
     * @return the objectId
     */
    public Integer getObjectId() {
        return objectId;
    }
    
    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
    
    
}
