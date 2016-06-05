package entities;

import entities.Branch;
import entities.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile CollectionAttribute<Product, Transaction> transactionCollection;
    public static volatile SingularAttribute<Product, String> proMeasureUnity;
    public static volatile SingularAttribute<Product, String> proUnityPrice;
    public static volatile SingularAttribute<Product, Integer> proId;
    public static volatile SingularAttribute<Product, String> proName;
    public static volatile SingularAttribute<Product, Branch> bId;
    public static volatile SingularAttribute<Product, String> pStatus;

}