package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-15T09:22:15")
@StaticMetamodel(LowQuantityNotification.class)
public class LowQuantityNotification_ { 

    public static volatile SingularAttribute<LowQuantityNotification, Integer> branchId;
    public static volatile SingularAttribute<LowQuantityNotification, Date> creationTime;
    public static volatile SingularAttribute<LowQuantityNotification, String> createdBy;
    public static volatile SingularAttribute<LowQuantityNotification, String> count;
    public static volatile SingularAttribute<LowQuantityNotification, Integer> tankId;
    public static volatile SingularAttribute<LowQuantityNotification, Double> minCapacity;
    public static volatile SingularAttribute<LowQuantityNotification, String> id;
    public static volatile SingularAttribute<LowQuantityNotification, String> checkingPeriod;

}