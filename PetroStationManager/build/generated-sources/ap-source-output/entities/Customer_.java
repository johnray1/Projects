package entities;

import entities.Branch;
import entities.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile CollectionAttribute<Customer, Transaction> transactionCollection;
    public static volatile SingularAttribute<Customer, String> custTel;
    public static volatile SingularAttribute<Customer, Integer> custId;
    public static volatile SingularAttribute<Customer, String> custName;
    public static volatile SingularAttribute<Customer, String> custTin;
    public static volatile SingularAttribute<Customer, Branch> bId;

}