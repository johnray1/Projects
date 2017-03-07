package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-04T10:55:43")
@StaticMetamodel(IdlingNotification.class)
public class IdlingNotification_ { 

    public static volatile SingularAttribute<IdlingNotification, Integer> branchId;
    public static volatile SingularAttribute<IdlingNotification, Integer> notificationTypeId;
    public static volatile SingularAttribute<IdlingNotification, Date> creationTime;
    public static volatile SingularAttribute<IdlingNotification, String> createdBy;
    public static volatile SingularAttribute<IdlingNotification, Integer> count;
    public static volatile SingularAttribute<IdlingNotification, Integer> checkCount;
    public static volatile SingularAttribute<IdlingNotification, Date> lastNotifyTime;
    public static volatile SingularAttribute<IdlingNotification, String> id;
    public static volatile SingularAttribute<IdlingNotification, Integer> nozzleId;
    public static volatile SingularAttribute<IdlingNotification, Date> lastUsedTime;
    public static volatile SingularAttribute<IdlingNotification, Integer> maxIdlingTime;
    public static volatile SingularAttribute<IdlingNotification, Integer> checkingPeriod;

}