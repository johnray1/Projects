package com.oltranz.payfuel.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-02T11:25:33")
@StaticMetamodel(TankTracking.class)
public class TankTracking_ { 

    public static volatile SingularAttribute<TankTracking, Date> dateTime;
    public static volatile SingularAttribute<TankTracking, Double> quantitybefore;
    public static volatile SingularAttribute<TankTracking, Integer> transactionTypeId;
    public static volatile SingularAttribute<TankTracking, Integer> adminId;
    public static volatile SingularAttribute<TankTracking, Integer> tankId;
    public static volatile SingularAttribute<TankTracking, Long> id;
    public static volatile SingularAttribute<TankTracking, Integer> userId;
    public static volatile SingularAttribute<TankTracking, Long> transactionId;
    public static volatile SingularAttribute<TankTracking, Double> quantityafter;

}