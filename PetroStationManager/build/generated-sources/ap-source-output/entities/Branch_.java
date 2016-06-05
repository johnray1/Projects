package entities;

import entities.Company;
import entities.Customer;
import entities.Device;
import entities.Product;
import entities.Pump;
import entities.Transaction;
import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile CollectionAttribute<Branch, Transaction> transactionCollection;
    public static volatile SingularAttribute<Branch, String> bStatus;
    public static volatile SingularAttribute<Branch, String> bName;
    public static volatile CollectionAttribute<Branch, Product> productCollection;
    public static volatile CollectionAttribute<Branch, Pump> pumpCollection;
    public static volatile CollectionAttribute<Branch, User> userCollection;
    public static volatile CollectionAttribute<Branch, Device> deviceCollection;
    public static volatile SingularAttribute<Branch, String> bLongitude;
    public static volatile CollectionAttribute<Branch, Customer> customerCollection;
    public static volatile SingularAttribute<Branch, String> bLatitude;
    public static volatile SingularAttribute<Branch, String> bDescr;
    public static volatile SingularAttribute<Branch, Integer> bId;
    public static volatile SingularAttribute<Branch, Company> cId;

}