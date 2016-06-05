package com.oltranz.IntercityTransport.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-19T02:01:59")
@StaticMetamodel(Contract.class)
public class Contract_ { 

    public static volatile SingularAttribute<Contract, Integer> transporterId;
    public static volatile SingularAttribute<Contract, String> descr;
    public static volatile SingularAttribute<Contract, Double> amount;
    public static volatile SingularAttribute<Contract, Integer> serviceProviderId;
    public static volatile SingularAttribute<Contract, Integer> paymentTypeId;
    public static volatile SingularAttribute<Contract, Date> endDate;
    public static volatile SingularAttribute<Contract, String> name;
    public static volatile SingularAttribute<Contract, Integer> id;
    public static volatile SingularAttribute<Contract, Integer> serviceId;
    public static volatile SingularAttribute<Contract, Date> startDate;
    public static volatile SingularAttribute<Contract, Integer> status;

}