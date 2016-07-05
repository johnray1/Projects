package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-05T13:28:04")
@StaticMetamodel(IndexTracking.class)
public class IndexTracking_ { 

    public static volatile SingularAttribute<IndexTracking, Date> dateTime;
    public static volatile SingularAttribute<IndexTracking, Integer> transactionTypeId;
    public static volatile SingularAttribute<IndexTracking, Double> indexafter;
    public static volatile SingularAttribute<IndexTracking, Double> indexbefore;
    public static volatile SingularAttribute<IndexTracking, Long> id;
    public static volatile SingularAttribute<IndexTracking, Integer> userId;
    public static volatile SingularAttribute<IndexTracking, Long> transactionId;

}