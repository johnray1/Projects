package entities;

import entities.Transaction;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-27T13:20:35")
@StaticMetamodel(SpPaymentRes.class)
public class SpPaymentRes_ { 

    public static volatile SingularAttribute<SpPaymentRes, String> spTransactionId;
    public static volatile SingularAttribute<SpPaymentRes, String> statusDesc;
    public static volatile SingularAttribute<SpPaymentRes, Integer> requestId;
    public static volatile SingularAttribute<SpPaymentRes, String> contractId;
    public static volatile SingularAttribute<SpPaymentRes, Transaction> transactionId;
    public static volatile SingularAttribute<SpPaymentRes, String> statusCode;

}