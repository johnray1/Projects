package com.oltranz.IntercityTransport.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-19T02:01:59")
@StaticMetamodel(WalletTransaction.class)
public class WalletTransaction_ { 

    public static volatile SingularAttribute<WalletTransaction, Integer> srcWalletOwnerId;
    public static volatile SingularAttribute<WalletTransaction, Date> dateTime;
    public static volatile SingularAttribute<WalletTransaction, Integer> amount;
    public static volatile SingularAttribute<WalletTransaction, Integer> refTransTypeId;
    public static volatile SingularAttribute<WalletTransaction, Double> destWalletPrevBalance;
    public static volatile SingularAttribute<WalletTransaction, Double> srcWalletPrevBalance;
    public static volatile SingularAttribute<WalletTransaction, Integer> srcWalletTypeId;
    public static volatile SingularAttribute<WalletTransaction, Double> destWalletNewBalance;
    public static volatile SingularAttribute<WalletTransaction, Double> srcWalletNewBalance;
    public static volatile SingularAttribute<WalletTransaction, Long> refTransId;
    public static volatile SingularAttribute<WalletTransaction, Long> id;
    public static volatile SingularAttribute<WalletTransaction, Integer> destWalletOwnerId;
    public static volatile SingularAttribute<WalletTransaction, Integer> destWalletTypeId;
    public static volatile SingularAttribute<WalletTransaction, Integer> walletTransId;

}