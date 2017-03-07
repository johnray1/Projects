package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-04T10:55:43")
@StaticMetamodel(BizzareTransaction.class)
public class BizzareTransaction_ { 

    public static volatile SingularAttribute<BizzareTransaction, Integer> paymentModeId;
    public static volatile SingularAttribute<BizzareTransaction, Date> date;
    public static volatile SingularAttribute<BizzareTransaction, Integer> branchId;
    public static volatile SingularAttribute<BizzareTransaction, Integer> pumpId;
    public static volatile SingularAttribute<BizzareTransaction, Double> amount;
    public static volatile SingularAttribute<BizzareTransaction, Double> quantity;
    public static volatile SingularAttribute<BizzareTransaction, Integer> productId;
    public static volatile SingularAttribute<BizzareTransaction, Date> deviceTransactionTime;
    public static volatile SingularAttribute<BizzareTransaction, Integer> count;
    public static volatile SingularAttribute<BizzareTransaction, Date> serverReqTime;
    public static volatile SingularAttribute<BizzareTransaction, Long> deviceTransactionId;
    public static volatile SingularAttribute<BizzareTransaction, Integer> userId;
    public static volatile SingularAttribute<BizzareTransaction, Integer> deviceId;
    public static volatile SingularAttribute<BizzareTransaction, Long> transactionId;
    public static volatile SingularAttribute<BizzareTransaction, String> platenumber;
    public static volatile SingularAttribute<BizzareTransaction, Date> serverResTime;
    public static volatile SingularAttribute<BizzareTransaction, Long> customerId;
    public static volatile SingularAttribute<BizzareTransaction, Integer> tankId;
    public static volatile SingularAttribute<BizzareTransaction, Integer> nozzleId;
    public static volatile SingularAttribute<BizzareTransaction, String> paymentStatus;

}