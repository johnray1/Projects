package com.oltranz.quickteller.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-04T16:05:20")
@StaticMetamodel(Quickteller.class)
public class Quickteller_ { 

    public static volatile SingularAttribute<Quickteller, Double> amount;
    public static volatile SingularAttribute<Quickteller, String> signature;
    public static volatile SingularAttribute<Quickteller, String> responseDescription;
    public static volatile SingularAttribute<Quickteller, String> paymentReference;
    public static volatile SingularAttribute<Quickteller, String> requestReference;
    public static volatile SingularAttribute<Quickteller, String> customerId;
    public static volatile SingularAttribute<Quickteller, Date> serverTime;
    public static volatile SingularAttribute<Quickteller, String> transactionDate;
    public static volatile SingularAttribute<Quickteller, String> msisdn;
    public static volatile SingularAttribute<Quickteller, String> rechargePin;
    public static volatile SingularAttribute<Quickteller, String> responseCode;

}