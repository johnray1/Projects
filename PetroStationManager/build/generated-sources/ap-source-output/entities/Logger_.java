package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-09T10:48:08")
@StaticMetamodel(Logger.class)
public class Logger_ { 

    public static volatile SingularAttribute<Logger, String> loggerType;
    public static volatile SingularAttribute<Logger, Date> logoutTime;
    public static volatile SingularAttribute<Logger, String> firstName;
    public static volatile SingularAttribute<Logger, String> lastName;
    public static volatile SingularAttribute<Logger, Date> loginTime;
    public static volatile SingularAttribute<Logger, Integer> loggerId;
    public static volatile SingularAttribute<Logger, String> loggerName;
    public static volatile SingularAttribute<Logger, String> email;
    public static volatile SingularAttribute<Logger, String> phoneNo;
    public static volatile SingularAttribute<Logger, String> logMachineType;

}