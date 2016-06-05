/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

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
@Table(name = "actions_on_objects", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"action_id","object_id"})})

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
    private Integer object_id;
    
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
     * @return the object_id
     */
    public Integer getObject_id() {
        return object_id;
    }

    /**
     * @param object_id the object_id to set
     */
    public void setObject_id(Integer object_id) {
        this.object_id = object_id;
    }

    
}
