package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-05T13:52:59")
@StaticMetamodel(ErroneousTransaction.class)
public class ErroneousTransaction_ { 

    public static volatile SingularAttribute<ErroneousTransaction, Integer> paymentModeId;
    public static volatile SingularAttribute<ErroneousTransaction, Date> date;
    public static volatile SingularAttribute<ErroneousTransaction, Integer> branchId;
    public static volatile SingularAttribute<ErroneousTransaction, Integer> pumpId;
    public static volatile SingularAttribute<ErroneousTransaction, Double> amount;
    public static volatile SingularAttribute<ErroneousTransaction, Double> quantity;
    public static volatile SingularAttribute<ErroneousTransaction, Integer> productId;
    public static volatile SingularAttribute<ErroneousTransaction, Date> deviceTransactionTime;
    public static volatile SingularAttribute<ErroneousTransaction, Date> serverReqTime;
    public static volatile SingularAttribute<ErroneousTransaction, Long> deviceTransactionId;
    public static volatile SingularAttribute<ErroneousTransaction, Integer> userId;
    public static volatile SingularAttribute<ErroneousTransaction, Integer> deviceId;
    public static volatile SingularAttribute<ErroneousTransaction, Long> transactionId;
    public static volatile SingularAttribute<ErroneousTransaction, String> platenumber;
    public static volatile SingularAttribute<ErroneousTransaction, Date> serverResTime;
    public static volatile SingularAttribute<ErroneousTransaction, Long> checksum;
    public static volatile SingularAttribute<ErroneousTransaction, Long> customerId;
    public static volatile SingularAttribute<ErroneousTransaction, Long> id;
    public static volatile SingularAttribute<ErroneousTransaction, Integer> nozzleId;
    public static volatile SingularAttribute<ErroneousTransaction, String> paymentStatus;

}