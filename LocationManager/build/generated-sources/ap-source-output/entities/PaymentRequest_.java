package entities;

import entities.PayingInstitution;
import entities.Ticket;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(PaymentRequest.class)
public class PaymentRequest_ { 

    public static volatile SingularAttribute<PaymentRequest, Date> requestDatetime;
    public static volatile SingularAttribute<PaymentRequest, Double> amount;
    public static volatile SingularAttribute<PaymentRequest, String> spTransactionStatusCode;
    public static volatile SingularAttribute<PaymentRequest, String> spTransactionId;
    public static volatile SingularAttribute<PaymentRequest, String> requestStatusCode;
    public static volatile CollectionAttribute<PaymentRequest, Ticket> ticketCollection;
    public static volatile SingularAttribute<PaymentRequest, String> requestId;
    public static volatile SingularAttribute<PaymentRequest, PayingInstitution> payingInstitutionCode;
    public static volatile SingularAttribute<PaymentRequest, String> requestDescr;
    public static volatile SingularAttribute<PaymentRequest, Date> responseDatetime;
    public static volatile SingularAttribute<PaymentRequest, String> payingAccountCode;

}