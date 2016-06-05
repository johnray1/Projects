package entities;

import entities.Branch;
import entities.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(Pump.class)
public class Pump_ { 

    public static volatile CollectionAttribute<Pump, Transaction> transactionCollection;
    public static volatile SingularAttribute<Pump, Integer> puId;
    public static volatile SingularAttribute<Pump, String> puName;
    public static volatile SingularAttribute<Pump, String> puIndex;
    public static volatile SingularAttribute<Pump, Branch> bId;

}