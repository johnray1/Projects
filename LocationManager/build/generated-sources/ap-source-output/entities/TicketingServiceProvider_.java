package entities;

import entities.Contact;
import entities.Transporter;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(TicketingServiceProvider.class)
public class TicketingServiceProvider_ { 

    public static volatile SingularAttribute<TicketingServiceProvider, String> apiBaseUrl;
    public static volatile CollectionAttribute<TicketingServiceProvider, Contact> contactCollection;
    public static volatile SingularAttribute<TicketingServiceProvider, String> name;
    public static volatile SingularAttribute<TicketingServiceProvider, String> details;
    public static volatile SingularAttribute<TicketingServiceProvider, Integer> id;
    public static volatile CollectionAttribute<TicketingServiceProvider, Transporter> transporterCollection;

}