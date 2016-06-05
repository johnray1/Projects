package entities;

import entities.TicketingServiceProvider;
import entities.Transporter;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Contact.class)
public class Contact_ { 

    public static volatile CollectionAttribute<Contact, TicketingServiceProvider> ticketingServiceProviderCollection;
    public static volatile SingularAttribute<Contact, Integer> contactId;
    public static volatile SingularAttribute<Contact, String> contactName;
    public static volatile SingularAttribute<Contact, String> phone2;
    public static volatile SingularAttribute<Contact, String> phone3;
    public static volatile SingularAttribute<Contact, String> position;
    public static volatile CollectionAttribute<Contact, Transporter> transporterCollection;
    public static volatile SingularAttribute<Contact, String> email;
    public static volatile SingularAttribute<Contact, String> phone1;

}