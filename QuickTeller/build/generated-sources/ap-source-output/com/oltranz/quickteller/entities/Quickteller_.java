package com.oltranz.quickteller.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-17T18:03:52")
@StaticMetamodel(Quickteller.class)
public class Quickteller_ { 

    public static volatile SingularAttribute<Quickteller, Double> amount;
    public static volatile SingularAttribute<Quickteller, String> signature;
    public static volatile SingularAttribute<Quickteller, String> responseDescription;
    public static volatile SingularAttribute<Quickteller, String> paymentReference;
    public static volatile SingularAttribute<Quickteller, String> requestReference;
    public static volatile SingularAttribute<Quickteller, String> customerId;
    public static volatile SingularAttribute<Quickteller, String> transactionDate;
    public static volatile SingularAttribute<Quickteller, String> msisdn;
    public static volatile SingularAttribute<Quickteller, String> rechargePin;
    public static volatile SingularAttribute<Quickteller, String> responseCode;

}