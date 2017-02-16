package com.oltranz.usermanager.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-01-26T10:28:53")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Date> datetime;
    public static volatile SingularAttribute<Log, String> ip;
    public static volatile SingularAttribute<Log, String> objectName;
    public static volatile SingularAttribute<Log, Integer> actionId;
    public static volatile SingularAttribute<Log, Long> id;
    public static volatile SingularAttribute<Log, String> source;
    public static volatile SingularAttribute<Log, String> userName;
    public static volatile SingularAttribute<Log, Integer> userId;
    public static volatile SingularAttribute<Log, String> objectId;
    public static volatile SingularAttribute<Log, String> actionName;
    public static volatile SingularAttribute<Log, Integer> actionResult;

}