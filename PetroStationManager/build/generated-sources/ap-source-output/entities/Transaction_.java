package entities;

import entities.Branch;
import entities.Customer;
import entities.Device;
import entities.Product;
import entities.Pump;
import entities.SpPaymentRes;
import entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Double> amount;
    public static volatile SingularAttribute<Transaction, Double> quantity;
    public static volatile SingularAttribute<Transaction, String> paymentMode;
    public static volatile SingularAttribute<Transaction, Date> resDatetime;
    public static volatile SingularAttribute<Transaction, Integer> traId;
    public static volatile SingularAttribute<Transaction, User> uId;
    public static volatile SingularAttribute<Transaction, Date> reqDatetime;
    public static volatile SingularAttribute<Transaction, Pump> puId;
    public static volatile SingularAttribute<Transaction, Product> proId;
    public static volatile CollectionAttribute<Transaction, SpPaymentRes> spPaymentResCollection;
    public static volatile SingularAttribute<Transaction, Customer> custId;
    public static volatile SingularAttribute<Transaction, Branch> bId;
    public static volatile SingularAttribute<Transaction, String> paymentStatus;
    public static volatile SingularAttribute<Transaction, Device> dId;

}