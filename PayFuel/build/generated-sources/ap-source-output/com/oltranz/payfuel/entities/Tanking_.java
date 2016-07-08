package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-07-08T13:05:40")
@StaticMetamodel(Tanking.class)
public class Tanking_ { 

    public static volatile SingularAttribute<Tanking, Date> datetime;
    public static volatile SingularAttribute<Tanking, Double> quantity;
    public static volatile SingularAttribute<Tanking, Integer> tankId;
    public static volatile SingularAttribute<Tanking, Integer> id;
    public static volatile SingularAttribute<Tanking, Integer> userId;

}