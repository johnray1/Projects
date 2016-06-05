package entities;

import entities.PaymentRequest;
import entities.TicketPK;
import entities.TicketStatus;
import entities.TicketType;
import entities.Transporter;
import entities.Trip;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, String> purchasingMsisdn;
    public static volatile SingularAttribute<Ticket, TicketStatus> ticketStatusCode;
    public static volatile SingularAttribute<Ticket, String> passengerNames;
    public static volatile SingularAttribute<Ticket, PaymentRequest> paymentRequestId;
    public static volatile SingularAttribute<Ticket, Date> reservationDatetime;
    public static volatile SingularAttribute<Ticket, String> customerCode;
    public static volatile SingularAttribute<Ticket, String> language;
    public static volatile SingularAttribute<Ticket, TicketType> ticketTypeId;
    public static volatile SingularAttribute<Ticket, Date> departureDatetime;
    public static volatile SingularAttribute<Ticket, Date> confirmationDatetime;
    public static volatile SingularAttribute<Ticket, Integer> ticketAmount;
    public static volatile SingularAttribute<Ticket, Trip> trip;
    public static volatile SingularAttribute<Ticket, Transporter> transporter;
    public static volatile SingularAttribute<Ticket, TicketPK> ticketPK;

}