package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-04T10:55:43")
@StaticMetamodel(BizzareTransactionNotification.class)
public class BizzareTransactionNotification_ { 

    public static volatile SingularAttribute<BizzareTransactionNotification, Integer> branchId;
    public static volatile SingularAttribute<BizzareTransactionNotification, Integer> notificationTypeId;
    public static volatile SingularAttribute<BizzareTransactionNotification, Date> creationTime;
    public static volatile SingularAttribute<BizzareTransactionNotification, Double> maxQuantity;
    public static volatile SingularAttribute<BizzareTransactionNotification, String> createdBy;
    public static volatile SingularAttribute<BizzareTransactionNotification, Integer> count;
    public static volatile SingularAttribute<BizzareTransactionNotification, Integer> checkCount;
    public static volatile SingularAttribute<BizzareTransactionNotification, String> id;
    public static volatile SingularAttribute<BizzareTransactionNotification, Date> lastNotifyTime;
    public static volatile SingularAttribute<BizzareTransactionNotification, Double> maxAmount;
    public static volatile SingularAttribute<BizzareTransactionNotification, Integer> checkingPeriod;

}