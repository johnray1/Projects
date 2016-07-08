/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author ismaelnzamutuma
 */
@JsonRootName("transactions")

public class TransactionDesplay implements Serializable {
    private String id;
  private String busNumberPlate;
  private String busOwnerId;
  private String passenger_identifier;
  private String busOwnerName;
    private int busRentingCost;
    private int otherImmediateCosts;
    private int transporterBalance;
    private int sellingProfileId;
    private int saleItemId;
    private int transporterId;
    private int unitPrice;
    private int qty;
    private int totalPrice;
  private String cardId;
  private String saleItemName;
  private int transporterWalletId,transporterWalletPrevBalance,transporterWalletNewBalance;
  private int passangerWalletId,passangerWalletPrevBalance,passangerWalletNewBalance,statusCode;
    private String statusMessage;
    private String dateTime;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
    public String getBusOwnerId() {
        return busOwnerId;
    }

    /**
     * @param busOwnerId the busOwnerId to set
     */
    public void setBusOwnerId(String busOwnerId) {
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
    public int getBusRentingCost() {
        return busRentingCost;
    }

    /**
     * @param busRentingCost the busRentingCost to set
     */
    public void setBusRentingCost(int busRentingCost) {
        this.busRentingCost = busRentingCost;
    }

    /**
     * @return the otherImmediateCosts
     */
    public int getOtherImmediateCosts() {
        return otherImmediateCosts;
    }

    /**
     * @param otherImmediateCosts the otherImmediateCosts to set
     */
    public void setOtherImmediateCosts(int otherImmediateCosts) {
        this.otherImmediateCosts = otherImmediateCosts;
    }

    /**
     * @return the transporterBalance
     */
    public int getTransporterBalance() {
        return transporterBalance;
    }

    /**
     * @param transporterBalance the transporterBalance to set
     */
    public void setTransporterBalance(int transporterBalance) {
        this.transporterBalance = transporterBalance;
    }

    /**
     * @return the sellingProfileId
     */
    public int getSellingProfileId() {
        return sellingProfileId;
    }

    /**
     * @param sellingProfileId the sellingProfileId to set
     */
    public void setSellingProfileId(int sellingProfileId) {
        this.sellingProfileId = sellingProfileId;
    }

    /**
     * @return the saleItemId
     */
    public int getSaleItemId() {
        return saleItemId;
    }

    /**
     * @param saleItemId the saleItemId to set
     */
    public void setSaleItemId(int saleItemId) {
        this.saleItemId = saleItemId;
    }

    /**
     * @return the transporterId
     */
    public int getTransporterId() {
        return transporterId;
    }

    /**
     * @param transporterId the transporterId to set
     */
    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    /**
     * @return the unitPrice
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the totalPrice
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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
     * @return the transporterWalletId
     */
    public int getTransporterWalletId() {
        return transporterWalletId;
    }

    /**
     * @param transporterWalletId the transporterWalletId to set
     */
    public void setTransporterWalletId(int transporterWalletId) {
        this.transporterWalletId = transporterWalletId;
    }

    /**
     * @return the transporterWalletPrevBalance
     */
    public int getTransporterWalletPrevBalance() {
        return transporterWalletPrevBalance;
    }

    /**
     * @param transporterWalletPrevBalance the transporterWalletPrevBalance to set
     */
    public void setTransporterWalletPrevBalance(int transporterWalletPrevBalance) {
        this.transporterWalletPrevBalance = transporterWalletPrevBalance;
    }

    /**
     * @return the transporterWalletNewBalance
     */
    public int getTransporterWalletNewBalance() {
        return transporterWalletNewBalance;
    }

    /**
     * @param transporterWalletNewBalance the transporterWalletNewBalance to set
     */
    public void setTransporterWalletNewBalance(int transporterWalletNewBalance) {
        this.transporterWalletNewBalance = transporterWalletNewBalance;
    }

    /**
     * @return the passangerWalletId
     */
    public int getPassangerWalletId() {
        return passangerWalletId;
    }

    /**
     * @param passangerWalletId the passangerWalletId to set
     */
    public void setPassangerWalletId(int passangerWalletId) {
        this.passangerWalletId = passangerWalletId;
    }

    /**
     * @return the passangerWalletPrevBalance
     */
    public int getPassangerWalletPrevBalance() {
        return passangerWalletPrevBalance;
    }

    /**
     * @param passangerWalletPrevBalance the passangerWalletPrevBalance to set
     */
    public void setPassangerWalletPrevBalance(int passangerWalletPrevBalance) {
        this.passangerWalletPrevBalance = passangerWalletPrevBalance;
    }

    /**
     * @return the passangerWalletNewBalance
     */
    public int getPassangerWalletNewBalance() {
        return passangerWalletNewBalance;
    }

    /**
     * @param passangerWalletNewBalance the passangerWalletNewBalance to set
     */
    public void setPassangerWalletNewBalance(int passangerWalletNewBalance) {
        this.passangerWalletNewBalance = passangerWalletNewBalance;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
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
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
  
  
    
}
