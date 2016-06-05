/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author manzi
 */
@Entity
@SequenceGenerator(name="itemOnSeliingProfileSeq", initialValue=1, allocationSize=1)
@Table(name = "items_on_selling_profiles",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"item_id","profile_id"})})
public class ItemOnSellingProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="itemOnSeliingProfileSeq")
    private Integer id;
    
    @Column(name = "item_id")
    private Integer itemId;
    
    @Column(name = "profile_id")
    private Integer profileId;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SellingProfile)) {
            return false;
        }
        SellingProfile other = (SellingProfile) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransport.ItemOnSellingProfile[ id=" + getId() + " ]";
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
     * @return the profileId
     */
    public Integer getProfileId() {
        return profileId;
    }

    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    /**
     * @return the itemId
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    
    
}
