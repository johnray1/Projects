package entities;

import entities.Branch;
import entities.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-09T10:48:08")
@StaticMetamodel(Device.class)
public class Device_ { 

    public static volatile CollectionAttribute<Device, Transaction> transactionCollection;
    public static volatile SingularAttribute<Device, String> imeiNo;
    public static volatile SingularAttribute<Device, String> dStatus;
    public static volatile SingularAttribute<Device, Branch> bId;
    public static volatile SingularAttribute<Device, Integer> dId;

}