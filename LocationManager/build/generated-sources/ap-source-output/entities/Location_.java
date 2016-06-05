package entities;

import entities.LocationAliase;
import entities.Trip;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, String> descr;
    public static volatile CollectionAttribute<Location, Trip> tripCollection1;
    public static volatile SingularAttribute<Location, String> name;
    public static volatile CollectionAttribute<Location, Trip> tripCollection;
    public static volatile CollectionAttribute<Location, LocationAliase> locationAliaseCollection;
    public static volatile SingularAttribute<Location, Integer> id;

}