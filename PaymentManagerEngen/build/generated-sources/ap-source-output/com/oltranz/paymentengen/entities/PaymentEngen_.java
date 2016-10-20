package com.oltranz.paymentengen.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-03T14:59:30")
@StaticMetamodel(PaymentEngen.class)
public class PaymentEngen_ { 

    public static volatile SingularAttribute<PaymentEngen, Date> requestDatetime;
    public static volatile SingularAttribute<PaymentEngen, String> descr;
    public static volatile SingularAttribute<PaymentEngen, Double> amount;
    public static volatile SingularAttribute<PaymentEngen, Long> requestId;
    public static volatile SingularAttribute<PaymentEngen, String> contact;
    public static volatile SingularAttribute<PaymentEngen, Date> responseDatetime;
    public static volatile SingularAttribute<PaymentEngen, Long> transactionId;

}