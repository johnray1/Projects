package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-15T09:22:15")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, String> notificationTypeId;
    public static volatile SingularAttribute<Notification, Date> notifyTime;
    public static volatile SingularAttribute<Notification, String> id;
    public static volatile SingularAttribute<Notification, String> notificationDefId;

}