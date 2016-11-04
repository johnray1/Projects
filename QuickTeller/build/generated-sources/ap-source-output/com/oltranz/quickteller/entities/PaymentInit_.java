package com.oltranz.quickteller.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-04T16:05:20")
@StaticMetamodel(PaymentInit.class)
public class PaymentInit_ { 

    public static volatile SingularAttribute<PaymentInit, String> initUid;
    public static volatile SingularAttribute<PaymentInit, Integer> paymentSpId;
    public static volatile SingularAttribute<PaymentInit, Double> amount;
    public static volatile SingularAttribute<PaymentInit, Integer> paymentTypeId;
    public static volatile SingularAttribute<PaymentInit, Date> serverTime;
    public static volatile SingularAttribute<PaymentInit, String> msisdn;

}