package com.oltranz.quickteller.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-17T18:03:52")
@StaticMetamodel(PaymentInit.class)
public class PaymentInit_ { 

    public static volatile SingularAttribute<PaymentInit, String> initUid;
    public static volatile SingularAttribute<PaymentInit, Integer> paymentSpId;
    public static volatile SingularAttribute<PaymentInit, Double> amount;
    public static volatile SingularAttribute<PaymentInit, Integer> paymentTypeId;
    public static volatile SingularAttribute<PaymentInit, String> msisdn;

}