package com.oltranz.engennotify.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-04T10:55:43")
@StaticMetamodel(NotificationDestination.class)
public class NotificationDestination_ { 

    public static volatile SingularAttribute<NotificationDestination, String> name;
    public static volatile SingularAttribute<NotificationDestination, String> notificationId;
    public static volatile SingularAttribute<NotificationDestination, Integer> notifyTypeId;
    public static volatile SingularAttribute<NotificationDestination, String> dest;
    public static volatile SingularAttribute<NotificationDestination, String> email;
    public static volatile SingularAttribute<NotificationDestination, Integer> destType;

}