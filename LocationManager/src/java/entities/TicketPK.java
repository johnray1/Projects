/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JOHN
 */
@Embeddable
public class TicketPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "transporter_id", nullable = false)
    private int transporterId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ticketNumber", nullable = false)
    private int ticketNumber;

    public TicketPK() {
    }

    public TicketPK(int transporterId, int ticketNumber) {
        this.transporterId = transporterId;
        this.ticketNumber = ticketNumber;
    }

    public int getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) transporterId;
        hash += (int) ticketNumber;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketPK)) {
            return false;
        }
        TicketPK other = (TicketPK) object;
        if (this.transporterId != other.transporterId) {
            return false;
        }
        if (this.ticketNumber != other.ticketNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TicketPK[ transporterId=" + transporterId + ", ticketNumber=" + ticketNumber + " ]";
    }
    
}
