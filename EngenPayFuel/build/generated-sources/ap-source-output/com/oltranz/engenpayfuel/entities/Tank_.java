package com.oltranz.engenpayfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-31T10:31:57")
@StaticMetamodel(Tank.class)
public class Tank_ { 

    public static volatile SingularAttribute<Tank, Date> dippedTime;
    public static volatile SingularAttribute<Tank, Date> nextCalibrationDate;
    public static volatile SingularAttribute<Tank, Integer> branchId;
    public static volatile SingularAttribute<Tank, Integer> productId;
    public static volatile SingularAttribute<Tank, Double> currentCapacity;
    public static volatile SingularAttribute<Tank, Double> dippedCapacity;
    public static volatile SingularAttribute<Tank, String> name;
    public static volatile SingularAttribute<Tank, Integer> tankId;
    public static volatile SingularAttribute<Tank, Double> maxCapacity;
    public static volatile SingularAttribute<Tank, Date> preCalibrationDate;
    public static volatile SingularAttribute<Tank, Integer> status;

}