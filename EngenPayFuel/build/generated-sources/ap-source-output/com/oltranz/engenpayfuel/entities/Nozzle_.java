package com.oltranz.engenpayfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-14T09:06:00")
@StaticMetamodel(Nozzle.class)
public class Nozzle_ { 

    public static volatile SingularAttribute<Nozzle, Date> nextCalibrationDate;
    public static volatile SingularAttribute<Nozzle, Integer> pumpId;
    public static volatile SingularAttribute<Nozzle, Integer> branchId;
    public static volatile SingularAttribute<Nozzle, Double> nozzleIndex;
    public static volatile SingularAttribute<Nozzle, String> nozzleName;
    public static volatile SingularAttribute<Nozzle, Date> preCalibrationDate;
    public static volatile SingularAttribute<Nozzle, Integer> tankId;
    public static volatile SingularAttribute<Nozzle, Integer> nozzleId;
    public static volatile SingularAttribute<Nozzle, Integer> nozzleStatus;

}