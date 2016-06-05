package entities;

import entities.Admin;
import entities.Branch;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, String> descr;
    public static volatile CollectionAttribute<Company, Admin> adminCollection;
    public static volatile CollectionAttribute<Company, Branch> branchCollection;
    public static volatile SingularAttribute<Company, String> cName;
    public static volatile SingularAttribute<Company, Integer> cId;

}