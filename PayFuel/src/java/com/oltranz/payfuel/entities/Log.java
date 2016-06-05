/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "log", catalog = "PayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findById", query = "SELECT l FROM Log l WHERE l.id = :id"),
    @NamedQuery(name = "Log.findByDatetime", query = "SELECT l FROM Log l WHERE l.datetime = :datetime"),
    @NamedQuery(name = "Log.findByUserId", query = "SELECT l FROM Log l WHERE l.userId = :userId"),
    @NamedQuery(name = "Log.findByUserName", query = "SELECT l FROM Log l WHERE l.userName = :userName"),
    @NamedQuery(name = "Log.findByObjectId", query = "SELECT l FROM Log l WHERE l.objectId = :objectId"),
    @NamedQuery(name = "Log.findByObjectName", query = "SELECT l FROM Log l WHERE l.objectName = :objectName"),
    @NamedQuery(name = "Log.findByActionId", query = "SELECT l FROM Log l WHERE l.actionId = :actionId"),
    @NamedQuery(name = "Log.findByActionName", query = "SELECT l FROM Log l WHERE l.actionName = :actionName"),
    @NamedQuery(name = "Log.findByActionResult", query = "SELECT l FROM Log l WHERE l.actionResult = :actionResult"),
    @NamedQuery(name = "Log.findBySource", query = "SELECT l FROM Log l WHERE l.source = :source"),
    @NamedQuery(name = "Log.findByIp", query = "SELECT l FROM Log l WHERE l.ip = :ip")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 255)
    @Column(name = "user_name", length = 255)
    private String userName;
    @Column(name = "object_id")
    private Integer objectId;
    @Size(max = 255)
    @Column(name = "object_name", length = 255)
    private String objectName;
    @Column(name = "action_id")
    private Integer actionId;
    @Size(max = 255)
    @Column(name = "action_name", length = 255)
    private String actionName;
    @Column(name = "action_result")
    private Integer actionResult;
    @Size(max = 255)
    @Column(name = "source", length = 255)
    private String source;
    @Size(max = 255)
    @Column(name = "ip", length = 255)
    private String ip;

    public Log() {
    }

    public Log(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getActionResult() {
        return actionResult;
    }

    public void setActionResult(Integer actionResult) {
        this.actionResult = actionResult;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.payfuel.entities.Log[ id=" + id + " ]";
    }
    
}
