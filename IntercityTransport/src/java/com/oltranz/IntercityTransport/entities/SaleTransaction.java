/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manzi
 */
@Entity
@SequenceGenerator(name="saleTransactionSeq", initialValue=1, allocationSize=1)
@Table(name = "sales_transactions")
@XmlRootElement
public class SaleTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="saleTransactionSeq")
    
    @Column(name = "id")
    private Long id;
    
    @Column(name = "device_id")
    private String deviceId;
    
    @Column(name = "bus_number_plate")
    private String busNumberPlate;
    
    @Column(name = "bus_owner_id")
    private Integer busOwnerId;
    
     @Column(name = "bus_owner_type_id")
    private Integer busOwnerTypeId;
    
    @Column(name = "passenger_identifier")
    private String passenger_identifier;
    
    
    @Column(name = "bus_owner_name")
    private String busOwnerName;
    
    @Column(name = "bus_renting_cost")
    private Integer busRentingCost;
    
    @Column(name = "other_immediate_cost")
    private Integer otherImmediateCosts;
    
    @Column(name = "transporter_balance")
    private Integer transporter_balance;
    
    @Column(name = "card_id")
    private String cardId;
    
    @Column(name = "Selling_profile_Id")
    private Integer sellingProfileId;
    
    @Column(name = "sale_Item_id")
    private Integer saleItemId;
    
    @Column(name = "sale_item_name")
    private String saleItemName;
    
    @Column(name = "transporter_id")
    private Integer transporterId;
    
    @Column(name = "unit_price")
    private Integer unitPrice;
    
    @Column(name = "qty")
    private Integer qty;
    
    @Column(name = "total_price")
    private Integer totalPrice;
    
    @Column(name = "transporter_wallet_id")
    private Integer transporterWalletId;
    
    @Column(name = "transporter_wallet_prev_balance")
    private Double transporterWalletPrevBalance;
    
    @Column(name = "transporter_wallet_new_balance")
    private Double transporterWalletNewBalance;
    
    @Column(name = "passanger_wallet_id")
    private Integer passangerWalletId;
    
    @Column(name = "passanger_wallet_prev_balance")
    private Double passangerWalletPrevBalance;
    
    @Column(name = "passanger_wallet_new_balance")
    private Double passangerWalletNewBalance;
    
    @Column(name = "status_code")
    private Integer statusCode;
    
    @Column(name = "status_message")
    private String statusMessage;
    
    
    
    @Column(name = "date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleTransaction)) {
            return false;
        }
        SaleTransaction other = (SaleTransaction) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.oltranz.IntercityTransport.Entities.walletTransaction[ id=" + getId() + " ]";
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    /**
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }
    
    /**
     * @param cardId the cardId to set
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    /**
     * @return the sellingProfileId
     */
    public Integer getSellingProfileId() {
        return sellingProfileId;
    }
    
    /**
     * @param sellingProfileId the sellingProfileId to set
     */
    public void setSellingProfileId(Integer sellingProfileId) {
        this.sellingProfileId = sellingProfileId;
    }
    
    /**
     * @return the transporterId
     */
    public Integer getTransporterId() {
        return transporterId;
    }
    
    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(Integer transporterId) {
        this.transporterId = transporterId;
    }
    
    
    
    /**
     * @return the qty
     */
    public Integer getQty() {
        return qty;
    }
    
    /**
     * @param qty the qty to set
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    
    
    
    /**
     * @return the transporterWalletPrevBalance
     */
    public Double getTransporterWalletPrevBalance() {
        return transporterWalletPrevBalance;
    }
    
    /**
     * @param transporterWalletPrevBalance the transporterWalletPrevBalance to set
     */
    public void setTransporterWalletPrevBalance(Double transporterWalletPrevBalance) {
        this.transporterWalletPrevBalance = transporterWalletPrevBalance;
    }
    
    /**
     * @return the transporterWalletNewBalance
     */
    public Double getTransporterWalletNewBalance() {
        return transporterWalletNewBalance;
    }
    
    /**
     * @param transporterWalletNewBalance the transporterWalletNewBalance to set
     */
    public void setTransporterWalletNewBalance(Double transporterWalletNewBalance) {
        this.transporterWalletNewBalance = transporterWalletNewBalance;
    }
    
    /**
     * @return the passangerWalletId
     */
    public Integer getPassangerWalletId() {
        return passangerWalletId;
    }
    
    /**
     * @param passangerWalletId the passangerWalletId to set
     */
    public void setPassangerWalletId(Integer passangerWalletId) {
        this.passangerWalletId = passangerWalletId;
    }
    
    /**
     * @return the passangerWalletPrevBalance
     */
    public Double getPassangerWalletPrevBalance() {
        return passangerWalletPrevBalance;
    }
    
    /**
     * @param passangerWalletPrevBalance the passangerWalletPrevBalance to set
     */
    public void setPassangerWalletPrevBalance(Double passangerWalletPrevBalance) {
        this.passangerWalletPrevBalance = passangerWalletPrevBalance;
    }
    
    /**
     * @return the passangerWalletNewBalance
     */
    public Double getPassangerWalletNewBalance() {
        return passangerWalletNewBalance;
    }
    
    /**
     * @param passangerWalletNewBalance the passangerWalletNewBalance to set
     */
    public void setPassangerWalletNewBalance(Double passangerWalletNewBalance) {
        this.passangerWalletNewBalance = passangerWalletNewBalance;
    }
    
    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }
    
    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    /**
     * @return the transporterWalletId
     */
    public Integer getTransporterWalletId() {
        return transporterWalletId;
    }
    
    /**
     * @param transporterWalletId the transporterWalletId to set
     */
    public void setTransporterWalletId(Integer transporterWalletId) {
        this.transporterWalletId = transporterWalletId;
    }
    
    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }
    
    /**
     * @param deviceId the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    
    /**
     * @return the saleItemId
     */
    public Integer getSaleItemId() {
        return saleItemId;
    }
    
    /**
     * @param saleItemId the saleItemId to set
     */
    public void setSaleItemId(Integer saleItemId) {
        this.saleItemId = saleItemId;
    }
    
    /**
     * @return the totalPrice
     */
    public Integer getTotalPrice() {
        return totalPrice;
    }
    
    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    /**
     * @return the unitPrice
     */
    public Integer getUnitPrice() {
        return unitPrice;
    }
    
    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }
    
    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }
    
    /**
     * @param statusMessage the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    /**
     * @return the busNumberPlate
     */
    public String getBusNumberPlate() {
        return busNumberPlate;
    }
    
    /**
     * @param busNumberPlate the busNumberPlate to set
     */
    public void setBusNumberPlate(String busNumberPlate) {
        this.busNumberPlate = busNumberPlate;
    }
    
    /**
     * @return the busOwnerId
     */
    public Integer getBusOwnerId() {
        return busOwnerId;
    }
    
    /**
     * @param busOwnerId the busOwnerId to set
     */
    public void setBusOwnerId(Integer busOwnerId) {
        this.busOwnerId = busOwnerId;
    }
    
    /**
     * @return the passenger_identifier
     */
    public String getPassenger_identifier() {
        return passenger_identifier;
    }
    
    /**
     * @param passenger_identifier the passenger_identifier to set
     */
    public void setPassenger_identifier(String passenger_identifier) {
        this.passenger_identifier = passenger_identifier;
    }
    
    /**
     * @return the busOwnerName
     */
    public String getBusOwnerName() {
        return busOwnerName;
    }
    
    /**
     * @param busOwnerName the busOwnerName to set
     */
    public void setBusOwnerName(String busOwnerName) {
        this.busOwnerName = busOwnerName;
    }
    
    /**
     * @return the busRentingCost
     */
    public Integer getBusRentingCost() {
        return busRentingCost;
    }
    
    /**
     * @param busRentingCost the busOwnerPayment to set
     */
    public void setBusRentingCost(Integer busRentingCost) {
        this.busRentingCost = busRentingCost;
    }
    
   
    /**
     * @return the transporter_balance
     */
    public Integer getTransporter_balance() {
        return transporter_balance;
    }
    
    /**
     * @param transporter_balance the transporter_balance to set
     */
    public void setTransporter_balance(Integer transporter_balance) {
        this.transporter_balance = transporter_balance;
    }
    
    /**
     * @return the saleItemName
     */
    public String getSaleItemName() {
        return saleItemName;
    }
    
    /**
     * @param saleItemName the saleItemName to set
     */
    public void setSaleItemName(String saleItemName) {
        this.saleItemName = saleItemName;
    }

    /**
     * @return the otherImmediateCosts
     */
    public Integer getOtherImmediateCosts() {
        return otherImmediateCosts;
    }

    /**
     * @param otherImmediateCosts the otherImmediateCosts to set
     */
    public void setOtherImmediateCosts(Integer otherImmediateCosts) {
        this.otherImmediateCosts = otherImmediateCosts;
    }

    /**
     * @return the busOwnerTypeId
     */
    public Integer getBusOwnerTypeId() {
        return busOwnerTypeId;
    }

    /**
     * @param busOwnerTypeId the busOwnerTypeId to set
     */
    public void setBusOwnerTypeId(Integer busOwnerTypeId) {
        this.busOwnerTypeId = busOwnerTypeId;
    }
    
    
}
