package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-02T11:25:33")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Integer> paymentModeId;
    public static volatile SingularAttribute<Transaction, Date> date;
    public static volatile SingularAttribute<Transaction, Integer> branchId;
    public static volatile SingularAttribute<Transaction, Integer> pumpId;
    public static volatile SingularAttribute<Transaction, Double> amount;
    public static volatile SingularAttribute<Transaction, Double> quantity;
    public static volatile SingularAttribute<Transaction, Integer> productId;
    public static volatile SingularAttribute<Transaction, Date> deviceTransactionTime;
    public static volatile SingularAttribute<Transaction, Date> serverReqTime;
    public static volatile SingularAttribute<Transaction, Long> deviceTransactionId;
    public static volatile SingularAttribute<Transaction, Integer> userId;
    public static volatile SingularAttribute<Transaction, Integer> deviceId;
    public static volatile SingularAttribute<Transaction, Long> transactionId;
    public static volatile SingularAttribute<Transaction, String> platenumber;
    public static volatile SingularAttribute<Transaction, Date> serverResTime;
    public static volatile SingularAttribute<Transaction, Long> customerId;
    public static volatile SingularAttribute<Transaction, Integer> tankId;
    public static volatile SingularAttribute<Transaction, Integer> nozzleId;
    public static volatile SingularAttribute<Transaction, String> paymentStatus;

}