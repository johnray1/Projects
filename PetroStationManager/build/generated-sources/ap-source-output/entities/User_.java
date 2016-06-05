package entities;

import entities.Branch;
import entities.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, Transaction> transactionCollection;
    public static volatile SingularAttribute<User, String> fname;
    public static volatile SingularAttribute<User, String> uStatus;
    public static volatile SingularAttribute<User, Integer> uId;
    public static volatile SingularAttribute<User, String> lname;
    public static volatile SingularAttribute<User, String> uName;
    public static volatile SingularAttribute<User, String> uPin;
    public static volatile SingularAttribute<User, String> uType;
    public static volatile SingularAttribute<User, String> uNo;
    public static volatile SingularAttribute<User, String> uIdcard;
    public static volatile SingularAttribute<User, Branch> bId;
    public static volatile SingularAttribute<User, String> webStatus;
    public static volatile SingularAttribute<User, String> email;

}