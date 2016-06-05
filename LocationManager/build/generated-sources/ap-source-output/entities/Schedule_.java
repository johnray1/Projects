package entities;

import entities.SchedulePK;
import entities.Transporter;
import entities.Trip;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Schedule.class)
public class Schedule_ { 

    public static volatile SingularAttribute<Schedule, Trip> trip;
    public static volatile SingularAttribute<Schedule, Transporter> transporter;
    public static volatile SingularAttribute<Schedule, String> description;
    public static volatile SingularAttribute<Schedule, SchedulePK> schedulePK;

}