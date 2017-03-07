package com.oltranz.ignite.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T08:53:10")
@StaticMetamodel(Liquidation.class)
public class Liquidation_ { 

    public static volatile SingularAttribute<Liquidation, String> reference;
    public static volatile SingularAttribute<Liquidation, Double> amount;
    public static volatile SingularAttribute<Liquidation, Date> creationTime;
    public static volatile SingularAttribute<Liquidation, String> createdBy;
    public static volatile SingularAttribute<Liquidation, Date> liquidationTime;
    public static volatile SingularAttribute<Liquidation, String> telcoName;
    public static volatile SingularAttribute<Liquidation, String> referenceType;
    public static volatile SingularAttribute<Liquidation, String> id;

}