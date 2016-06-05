package com.oltranz.IntercityTransport.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-19T02:01:59")
@StaticMetamodel(Bus.class)
public class Bus_ { 

    public static volatile SingularAttribute<Bus, String> descr;
    public static volatile SingularAttribute<Bus, Boolean> transporterOwned;
    public static volatile SingularAttribute<Bus, Integer> ownerId;
    public static volatile SingularAttribute<Bus, String> numberPlate;
    public static volatile SingularAttribute<Bus, Integer> status;

}