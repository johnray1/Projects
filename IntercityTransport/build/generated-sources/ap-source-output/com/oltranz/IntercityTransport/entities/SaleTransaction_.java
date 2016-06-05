package com.oltranz.IntercityTransport.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-19T02:02:00")
@StaticMetamodel(SaleTransaction.class)
public class SaleTransaction_ { 

    public static volatile SingularAttribute<SaleTransaction, Date> dateTime;
    public static volatile SingularAttribute<SaleTransaction, Integer> passangerWalletId;
    public static volatile SingularAttribute<SaleTransaction, Integer> totalPrice;
    public static volatile SingularAttribute<SaleTransaction, Double> transporterWalletNewBalance;
    public static volatile SingularAttribute<SaleTransaction, Integer> otherImmediateCosts;
    public static volatile SingularAttribute<SaleTransaction, String> deviceId;
    public static volatile SingularAttribute<SaleTransaction, Double> transporterWalletPrevBalance;
    public static volatile SingularAttribute<SaleTransaction, String> busNumberPlate;
    public static volatile SingularAttribute<SaleTransaction, Double> passangerWalletNewBalance;
    public static volatile SingularAttribute<SaleTransaction, Integer> saleItemId;
    public static volatile SingularAttribute<SaleTransaction, String> saleItemName;
    public static volatile SingularAttribute<SaleTransaction, String> passenger_identifier;
    public static volatile SingularAttribute<SaleTransaction, Integer> transporter_balance;
    public static volatile SingularAttribute<SaleTransaction, Integer> sellingProfileId;
    public static volatile SingularAttribute<SaleTransaction, Integer> transporterWalletId;
    public static volatile SingularAttribute<SaleTransaction, Long> id;
    public static volatile SingularAttribute<SaleTransaction, Integer> unitPrice;
    public static volatile SingularAttribute<SaleTransaction, Integer> busRentingCost;
    public static volatile SingularAttribute<SaleTransaction, Integer> busOwnerId;
    public static volatile SingularAttribute<SaleTransaction, Double> passangerWalletPrevBalance;
    public static volatile SingularAttribute<SaleTransaction, Integer> busOwnerTypeId;
    public static volatile SingularAttribute<SaleTransaction, String> statusMessage;
    public static volatile SingularAttribute<SaleTransaction, Integer> transporterId;
    public static volatile SingularAttribute<SaleTransaction, String> cardId;
    public static volatile SingularAttribute<SaleTransaction, Integer> qty;
    public static volatile SingularAttribute<SaleTransaction, String> busOwnerName;
    public static volatile SingularAttribute<SaleTransaction, Integer> statusCode;

}