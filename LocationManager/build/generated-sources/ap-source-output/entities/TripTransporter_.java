package entities;

import entities.Transporter;
import entities.Trip;
import entities.TripTransporterPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(TripTransporter.class)
public class TripTransporter_ { 

    public static volatile SingularAttribute<TripTransporter, Trip> trip;
    public static volatile SingularAttribute<TripTransporter, Integer> price;
    public static volatile SingularAttribute<TripTransporter, Transporter> transporter;
    public static volatile SingularAttribute<TripTransporter, TripTransporterPK> tripTransporterPK;

}