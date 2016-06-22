package com.oltranz.paymentmanagerpayfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-22T10:41:28")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Date> requestDatetime;
    public static volatile SingularAttribute<Payment, String> descr;
    public static volatile SingularAttribute<Payment, Double> amount;
    public static volatile SingularAttribute<Payment, Long> requestId;
    public static volatile SingularAttribute<Payment, String> contact;
    public static volatile SingularAttribute<Payment, Date> responseDatetime;
    public static volatile SingularAttribute<Payment, Long> transactionId;

}