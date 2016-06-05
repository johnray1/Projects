package entities;

import entities.Account;
import entities.AccountMvmntReason;
import entities.BalanceHistory;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(AccountMvmnt.class)
public class AccountMvmnt_ { 

    public static volatile SingularAttribute<AccountMvmnt, Double> recordId;
    public static volatile SingularAttribute<AccountMvmnt, String> mvmntTransctionDetails;
    public static volatile CollectionAttribute<AccountMvmnt, BalanceHistory> balanceHistoryCollection;
    public static volatile SingularAttribute<AccountMvmnt, Date> mvmntDatetime;
    public static volatile SingularAttribute<AccountMvmnt, Account> debitedAccountId;
    public static volatile SingularAttribute<AccountMvmnt, AccountMvmntReason> mvmntReasonCode;
    public static volatile SingularAttribute<AccountMvmnt, BigDecimal> amountNumeric;
    public static volatile SingularAttribute<AccountMvmnt, Account> creditedAccountId;

}