package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(SchedulePK.class)
public class SchedulePK_ { 

    public static volatile SingularAttribute<SchedulePK, Date> departDatetime;
    public static volatile SingularAttribute<SchedulePK, Integer> transporterId;
    public static volatile SingularAttribute<SchedulePK, Integer> tripdepartLocId;
    public static volatile SingularAttribute<SchedulePK, Integer> reintegratorscheduleid;
    public static volatile SingularAttribute<SchedulePK, Integer> tripdestLocId;
    public static volatile SingularAttribute<SchedulePK, Integer> triprouteId;

}