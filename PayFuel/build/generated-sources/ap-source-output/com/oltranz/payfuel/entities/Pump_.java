package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-02T11:25:33")
@StaticMetamodel(Pump.class)
public class Pump_ { 

    public static volatile SingularAttribute<Pump, Integer> pumpId;
    public static volatile SingularAttribute<Pump, Date> nextCalibrationDate;
    public static volatile SingularAttribute<Pump, Integer> branchId;
    public static volatile SingularAttribute<Pump, String> name;
    public static volatile SingularAttribute<Pump, Date> preCalibrationDate;
    public static volatile SingularAttribute<Pump, Integer> tankId;
    public static volatile SingularAttribute<Pump, Integer> status;

}