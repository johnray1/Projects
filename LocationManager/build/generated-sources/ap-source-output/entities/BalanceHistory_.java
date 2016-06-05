package entities;

import entities.Account;
import entities.AccountMvmnt;
import entities.BalanceHistoryPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(BalanceHistory.class)
public class BalanceHistory_ { 

    public static volatile SingularAttribute<BalanceHistory, Account> accountId;
    public static volatile SingularAttribute<BalanceHistory, Integer> balance;
    public static volatile SingularAttribute<BalanceHistory, BalanceHistoryPK> balanceHistoryPK;
    public static volatile SingularAttribute<BalanceHistory, AccountMvmnt> accountMvmnt;

}