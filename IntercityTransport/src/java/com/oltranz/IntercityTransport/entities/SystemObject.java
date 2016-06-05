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
import javax.validation.constraints.Size;

/**
 *
 * @author manzi
 */
@Entity
@Table(name = "system_objects")
public class SystemObject implements Serializable {
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
    
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;
    
    @Size(max = 255)
    @Column(name = "descr", length = 255)
    private String descr;
    
    
    public SystemObject(){
        
    }
    
    public SystemObject(Integer id,String name, String descr){
        this.id=id;
        this.name=name;
        this.descr=descr;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemObject)) {
            return false;
        }
        SystemObject other = (SystemObject) object;
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
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
    
    
}
