/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@Entity
@Table(name = "ticket", catalog = "TransportManagerDB", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByTransporterId", query = "SELECT t FROM Ticket t WHERE t.ticketPK.transporterId = :transporterId"),
    @NamedQuery(name = "Ticket.findByTicketNumber", query = "SELECT t FROM Ticket t WHERE t.ticketPK.ticketNumber = :ticketNumber"),
    @NamedQuery(name = "Ticket.findByReservationDatetime", query = "SELECT t FROM Ticket t WHERE t.reservationDatetime = :reservationDatetime"),
    @NamedQuery(name = "Ticket.findByConfirmationDatetime", query = "SELECT t FROM Ticket t WHERE t.confirmationDatetime = :confirmationDatetime"),
    @NamedQuery(name = "Ticket.findByDepartureDatetime", query = "SELECT t FROM Ticket t WHERE t.departureDatetime = :departureDatetime"),
    @NamedQuery(name = "Ticket.findByPassengerNames", query = "SELECT t FROM Ticket t WHERE t.passengerNames = :passengerNames"),
    @NamedQuery(name = "Ticket.findByPurchasingMsisdn", query = "SELECT t FROM Ticket t WHERE t.purchasingMsisdn = :purchasingMsisdn"),
    @NamedQuery(name = "Ticket.findByCustomerCode", query = "SELECT t FROM Ticket t WHERE t.customerCode = :customerCode"),
    @NamedQuery(name = "Ticket.findByTicketAmount", query = "SELECT t FROM Ticket t WHERE t.ticketAmount = :ticketAmount"),
    @NamedQuery(name = "Ticket.findByLanguage", query = "SELECT t FROM Ticket t WHERE t.language = :language")})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TicketPK ticketPK;
    @Column(name = "reservation_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDatetime;
    @Column(name = "confirmation_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmationDatetime;
    @Column(name = "departure_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDatetime;
    @Size(max = 100)
    @Column(name = "passenger_names", length = 100)
    private String passengerNames;
    @Size(max = 50)
    @Column(name = "purchasing_msisdn", length = 50)
    private String purchasingMsisdn;
    @Size(max = 50)
    @Column(name = "customer_code", length = 50)
    private String customerCode;
    @Column(name = "ticketAmount")
    private Integer ticketAmount;
    @Size(max = 3)
    @Column(name = "language", length = 3)
    private String language;
    @JoinColumn(name = "transporter_id", referencedColumnName = "transporter_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Transporter transporter;
    @JoinColumn(name = "ticket_type_id", referencedColumnName = "type_id", nullable = false)
    @ManyToOne(optional = false)
    private TicketType ticketTypeId;
    @JoinColumn(name = "payment_request_id", referencedColumnName = "request_id")
    @ManyToOne
    private PaymentRequest paymentRequestId;
    @JoinColumn(name = "ticket_status_code", referencedColumnName = "status_code", nullable = false)
    @ManyToOne(optional = false)
    private TicketStatus ticketStatusCode;
    @JoinColumns({
        @JoinColumn(name = "tripdepart_loc_id", referencedColumnName = "depart_loc_id"),
        @JoinColumn(name = "tripdest_loc_id", referencedColumnName = "dest_loc_id"),
        @JoinColumn(name = "triproute_id", referencedColumnName = "route_id")})
    @ManyToOne
    private Trip trip;

    public Ticket() {
    }

    public Ticket(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public Ticket(int transporterId, int ticketNumber) {
        this.ticketPK = new TicketPK(transporterId, ticketNumber);
    }

    public TicketPK getTicketPK() {
        return ticketPK;
    }

    public void setTicketPK(TicketPK ticketPK) {
        this.ticketPK = ticketPK;
    }

    public Date getReservationDatetime() {
        return reservationDatetime;
    }

    public void setReservationDatetime(Date reservationDatetime) {
        this.reservationDatetime = reservationDatetime;
    }

    public Date getConfirmationDatetime() {
        return confirmationDatetime;
    }

    public void setConfirmationDatetime(Date confirmationDatetime) {
        this.confirmationDatetime = confirmationDatetime;
    }

    public Date getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(Date departureDatetime) {
        this.departureDatetime = departureDatetime;
    }

    public String getPassengerNames() {
        return passengerNames;
    }

    public void setPassengerNames(String passengerNames) {
        this.passengerNames = passengerNames;
    }

    public String getPurchasingMsisdn() {
        return purchasingMsisdn;
    }

    public void setPurchasingMsisdn(String purchasingMsisdn) {
        this.purchasingMsisdn = purchasingMsisdn;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(Integer ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public TicketType getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(TicketType ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public PaymentRequest getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(PaymentRequest paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public TicketStatus getTicketStatusCode() {
        return ticketStatusCode;
    }

    public void setTicketStatusCode(TicketStatus ticketStatusCode) {
        this.ticketStatusCode = ticketStatusCode;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketPK != null ? ticketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.ticketPK == null && other.ticketPK != null) || (this.ticketPK != null && !this.ticketPK.equals(other.ticketPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ticket[ ticketPK=" + ticketPK + " ]";
    }
    
}
