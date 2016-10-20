/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.entities;

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
 * @author JohnRay
 */
@Entity
@Table(name = "tanking", catalog = "EngenPayFuelDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tanking.findAll", query = "SELECT t FROM Tanking t"),
    @NamedQuery(name = "Tanking.findById", query = "SELECT t FROM Tanking t WHERE t.id = :id"),
    @NamedQuery(name = "Tanking.findByUserId", query = "SELECT t FROM Tanking t WHERE t.userId = :userId"),
    @NamedQuery(name = "Tanking.findByTankId", query = "SELECT t FROM Tanking t WHERE t.tankId = :tankId"),
    @NamedQuery(name = "Tanking.findByPreTankingMeasuredDip", query = "SELECT t FROM Tanking t WHERE t.preTankingMeasuredDip = :preTankingMeasuredDip"),
    @NamedQuery(name = "Tanking.findByPreTankingCalculatedDip", query = "SELECT t FROM Tanking t WHERE t.preTankingCalculatedDip = :preTankingCalculatedDip"),
    @NamedQuery(name = "Tanking.findByDeliveredBy", query = "SELECT t FROM Tanking t WHERE t.deliveredBy = :deliveredBy"),
    @NamedQuery(name = "Tanking.findByTheoriticalTanked", query = "SELECT t FROM Tanking t WHERE t.theoriticalTanked = :theoriticalTanked"),
    @NamedQuery(name = "Tanking.findByPlateNumber", query = "SELECT t FROM Tanking t WHERE t.plateNumber = :plateNumber"),
    @NamedQuery(name = "Tanking.findByPostTankingMeasuredDip", query = "SELECT t FROM Tanking t WHERE t.postTankingMeasuredDip = :postTankingMeasuredDip"),
    @NamedQuery(name = "Tanking.findByPostTankingCalculatedDip", query = "SELECT t FROM Tanking t WHERE t.postTankingCalculatedDip = :postTankingCalculatedDip"),
    @NamedQuery(name = "Tanking.findByDatetime", query = "SELECT t FROM Tanking t WHERE t.datetime = :datetime"),
    @NamedQuery(name = "Tanking.findByWaterValue", query = "SELECT t FROM Tanking t WHERE t.waterValue = :waterValue")})
public class Tanking implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "tank_id")
    private Integer tankId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pre_tanking_measured_dip", precision = 22)
    private Double preTankingMeasuredDip;
    @Column(name = "pre_tanking_calculated_dip", precision = 22)
    private Double preTankingCalculatedDip;
    @Size(max = 100)
    @Column(name = "delivered_by", length = 100)
    private String deliveredBy;
    @Column(name = "theoritical_tanked", precision = 22)
    private Double theoriticalTanked;
    @Size(max = 100)
    @Column(name = "plate_number", length = 100)
    private String plateNumber;
    @Column(name = "post_tanking_measured_dip", precision = 22)
    private Double postTankingMeasuredDip;
    @Column(name = "post_tanking_calculated_dip", precision = 22)
    private Double postTankingCalculatedDip;
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @Column(name = "water_value", precision = 22)
    private Double waterValue;

    public Tanking() {
    }

    public Tanking(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public Double getPreTankingMeasuredDip() {
        return preTankingMeasuredDip;
    }

    public void setPreTankingMeasuredDip(Double preTankingMeasuredDip) {
        this.preTankingMeasuredDip = preTankingMeasuredDip;
    }

    public Double getPreTankingCalculatedDip() {
        return preTankingCalculatedDip;
    }

    public void setPreTankingCalculatedDip(Double preTankingCalculatedDip) {
        this.preTankingCalculatedDip = preTankingCalculatedDip;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public Double getTheoriticalTanked() {
        return theoriticalTanked;
    }

    public void setTheoriticalTanked(Double theoriticalTanked) {
        this.theoriticalTanked = theoriticalTanked;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Double getPostTankingMeasuredDip() {
        return postTankingMeasuredDip;
    }

    public void setPostTankingMeasuredDip(Double postTankingMeasuredDip) {
        this.postTankingMeasuredDip = postTankingMeasuredDip;
    }

    public Double getPostTankingCalculatedDip() {
        return postTankingCalculatedDip;
    }

    public void setPostTankingCalculatedDip(Double postTankingCalculatedDip) {
        this.postTankingCalculatedDip = postTankingCalculatedDip;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getWaterValue() {
        return waterValue;
    }

    public void setWaterValue(Double waterValue) {
        this.waterValue = waterValue;
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
        if (!(object instanceof Tanking)) {
            return false;
        }
        Tanking other = (Tanking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.oltranz.engenpayfuel.entities.Tanking[ id=" + id + " ]";
    }
    
}
