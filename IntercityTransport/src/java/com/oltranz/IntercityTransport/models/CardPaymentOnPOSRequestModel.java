/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.models;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author ismaelnzamutuma
 */
@JsonRootName("DeviceSaleModel")
public class CardPaymentOnPOSRequestModel{
    private String cardId;
    private String deviceId;
    private Integer transporterId;
    private Integer itemId;
    private Integer quantity;
    private Integer totalAmount;
    private Integer profileId;

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

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the totalAmount
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
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
    
    
}
