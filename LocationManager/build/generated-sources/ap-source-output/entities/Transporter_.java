package entities;

import entities.Account;
import entities.Contact;
import entities.Schedule;
import entities.Ticket;
import entities.TicketingServiceProvider;
import entities.TripTransporter;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Transporter.class)
public class Transporter_ { 

    public static volatile CollectionAttribute<Transporter, Schedule> scheduleCollection;
    public static volatile SingularAttribute<Transporter, Integer> transporterId;
    public static volatile SingularAttribute<Transporter, String> transporterDetails;
    public static volatile SingularAttribute<Transporter, Account> accountId;
    public static volatile SingularAttribute<Transporter, String> transporterName;
    public static volatile SingularAttribute<Transporter, Integer> routeid;
    public static volatile CollectionAttribute<Transporter, Ticket> ticketCollection;
    public static volatile CollectionAttribute<Transporter, Contact> contactCollection;
    public static volatile CollectionAttribute<Transporter, TripTransporter> tripTransporterCollection;
    public static volatile SingularAttribute<Transporter, TicketingServiceProvider> ticketingServiceProviderId;

}