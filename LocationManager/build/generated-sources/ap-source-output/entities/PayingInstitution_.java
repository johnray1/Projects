package entities;

import entities.PaymentRequest;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(PayingInstitution.class)
public class PayingInstitution_ { 

    public static volatile SingularAttribute<PayingInstitution, String> descr;
    public static volatile SingularAttribute<PayingInstitution, String> code;
    public static volatile CollectionAttribute<PayingInstitution, PaymentRequest> paymentRequestCollection;

}