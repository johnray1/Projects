package entities;

import entities.AccountMvmnt;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(AccountMvmntReason.class)
public class AccountMvmntReason_ { 

    public static volatile SingularAttribute<AccountMvmntReason, String> descr;
    public static volatile SingularAttribute<AccountMvmntReason, Integer> code;
    public static volatile CollectionAttribute<AccountMvmntReason, AccountMvmnt> accountMvmntCollection;

}