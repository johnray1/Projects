package entities;

import entities.Location;
import entities.Route;
import entities.Schedule;
import entities.Ticket;
import entities.TripPK;
import entities.TripTransporter;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Trip.class)
public class Trip_ { 

    public static volatile CollectionAttribute<Trip, Schedule> scheduleCollection;
    public static volatile SingularAttribute<Trip, Route> route;
    public static volatile CollectionAttribute<Trip, Ticket> ticketCollection;
    public static volatile CollectionAttribute<Trip, TripTransporter> tripTransporterCollection;
    public static volatile SingularAttribute<Trip, Location> location1;
    public static volatile SingularAttribute<Trip, String> description;
    public static volatile SingularAttribute<Trip, TripPK> tripPK;
    public static volatile SingularAttribute<Trip, Location> location;

}