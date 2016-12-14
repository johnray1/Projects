package com.oltranz.engenpayfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-14T09:06:00")
@StaticMetamodel(Tanking.class)
public class Tanking_ { 

    public static volatile SingularAttribute<Tanking, Double> postTankingMeasuredDip;
    public static volatile SingularAttribute<Tanking, Date> datetime;
    public static volatile SingularAttribute<Tanking, String> deliveredBy;
    public static volatile SingularAttribute<Tanking, Double> postTankingCalculatedDip;
    public static volatile SingularAttribute<Tanking, Integer> tankId;
    public static volatile SingularAttribute<Tanking, Double> preTankingMeasuredDip;
    public static volatile SingularAttribute<Tanking, Double> theoriticalTanked;
    public static volatile SingularAttribute<Tanking, Integer> id;
    public static volatile SingularAttribute<Tanking, Double> waterValue;
    public static volatile SingularAttribute<Tanking, String> plateNumber;
    public static volatile SingularAttribute<Tanking, Integer> userId;
    public static volatile SingularAttribute<Tanking, Double> preTankingCalculatedDip;

}