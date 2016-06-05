package entities;

import entities.AccountMvmnt;
import entities.Accounttype;
import entities.BalanceHistory;
import entities.Transporter;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Integer> accountId;
    public static volatile SingularAttribute<Account, Date> lastUpdateDatetime;
    public static volatile CollectionAttribute<Account, BalanceHistory> balanceHistoryCollection;
    public static volatile SingularAttribute<Account, Accounttype> accountTypeId;
    public static volatile CollectionAttribute<Account, AccountMvmnt> accountMvmntCollection1;
    public static volatile SingularAttribute<Account, String> accountName;
    public static volatile CollectionAttribute<Account, AccountMvmnt> accountMvmntCollection;
    public static volatile SingularAttribute<Account, Integer> accountNumber;
    public static volatile CollectionAttribute<Account, Transporter> transporterCollection;

}