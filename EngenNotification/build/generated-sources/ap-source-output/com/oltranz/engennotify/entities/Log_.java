package com.oltranz.engennotify.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-15T09:22:15")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> notification;
    public static volatile SingularAttribute<Log, Date> notifyTime;
    public static volatile SingularAttribute<Log, String> destination;
    public static volatile SingularAttribute<Log, String> destinationType;
    public static volatile SingularAttribute<Log, Long> id;
    public static volatile SingularAttribute<Log, String> notificationType;

}