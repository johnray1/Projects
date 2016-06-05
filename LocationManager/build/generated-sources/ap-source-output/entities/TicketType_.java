package entities;

import entities.Ticket;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-06T09:57:07")
@StaticMetamodel(TicketType.class)
public class TicketType_ { 

    public static volatile SingularAttribute<TicketType, String> descr;
    public static volatile CollectionAttribute<TicketType, Ticket> ticketCollection;
    public static volatile SingularAttribute<TicketType, String> typeName;
    public static volatile SingularAttribute<TicketType, Integer> typeId;

}