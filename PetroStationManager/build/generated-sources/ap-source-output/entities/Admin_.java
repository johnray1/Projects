package entities;

import entities.Company;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-09T10:48:08")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SingularAttribute<Admin, String> firstName;
    public static volatile SingularAttribute<Admin, String> lastName;
    public static volatile SingularAttribute<Admin, String> password;
    public static volatile SingularAttribute<Admin, Integer> id;
    public static volatile SingularAttribute<Admin, String> type;
    public static volatile SingularAttribute<Admin, String> email;
    public static volatile SingularAttribute<Admin, String> phoneNo;
    public static volatile SingularAttribute<Admin, String> username;
    public static volatile SingularAttribute<Admin, Company> cId;

}