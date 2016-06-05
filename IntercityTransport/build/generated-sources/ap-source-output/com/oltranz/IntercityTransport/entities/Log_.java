package com.oltranz.IntercityTransport.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-19T02:02:00")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, Date> datetime;
    public static volatile SingularAttribute<Log, String> objectName;
    public static volatile SingularAttribute<Log, Integer> actionId;
    public static volatile SingularAttribute<Log, Integer> id;
    public static volatile SingularAttribute<Log, String> userName;
    public static volatile SingularAttribute<Log, Integer> userId;
    public static volatile SingularAttribute<Log, Integer> objectId;
    public static volatile SingularAttribute<Log, String> actionName;
    public static volatile SingularAttribute<Log, Integer> actionResult;

}