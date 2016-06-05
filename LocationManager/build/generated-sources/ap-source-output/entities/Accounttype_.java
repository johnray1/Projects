package entities;

import entities.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Accounttype.class)
public class Accounttype_ { 

    public static volatile CollectionAttribute<Accounttype, Account> accountCollection;
    public static volatile SingularAttribute<Accounttype, Integer> accountTypeId;
    public static volatile SingularAttribute<Accounttype, String> accountTypeDesc;

}