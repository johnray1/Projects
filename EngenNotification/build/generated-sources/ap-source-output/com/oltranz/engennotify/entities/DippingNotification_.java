package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-04T10:55:43")
@StaticMetamodel(DippingNotification.class)
public class DippingNotification_ { 

    public static volatile SingularAttribute<DippingNotification, Integer> branchId;
    public static volatile SingularAttribute<DippingNotification, Integer> notificationTypeId;
    public static volatile SingularAttribute<DippingNotification, Date> creationTime;
    public static volatile SingularAttribute<DippingNotification, String> createdBy;
    public static volatile SingularAttribute<DippingNotification, Integer> count;
    public static volatile SingularAttribute<DippingNotification, Integer> checkCount;
    public static volatile SingularAttribute<DippingNotification, String> id;
    public static volatile SingularAttribute<DippingNotification, Date> lastNotifyTime;
    public static volatile SingularAttribute<DippingNotification, Integer> checkingPeriod;
    public static volatile SingularAttribute<DippingNotification, Date> dipingTime;

}