/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JohnRay
 */
@Entity
@Table(name = "action_on_object", catalog = "EngenPayFuelDB", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"action_id"}),
    @UniqueConstraint(columnNames = {"object_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionOnObject.findAll", query = "SELECT a FROM ActionOnObject a"),
    @NamedQuery(name = "ActionOnObject.findById", query = "SELECT a FROM ActionOnObject a WHERE a.id = :id"),
    @NamedQuery(name = "ActionOnObject.findByActionId", query = "SELECT a FROM ActionOnObject a WHERE a.actionId = :actionId"),
    @NamedQuery(name = "ActionOnObject.findByObjectId", query = "SELECT a FROM ActionOnObject a WHERE a.objectId = :objectId")})
public class ActionOnObject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "action_id")
    private Integer actionId;
    @Column(name = "object_id")
    private Integer objectId;

    public ActionOnObject() {
    }

    public ActionOnObject(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionOnObject)) {
            return false;
        }
        ActionOnObject other = (ActionOnObject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.ActionOnObject[ id=" + id + " ]";
    }
    
}
