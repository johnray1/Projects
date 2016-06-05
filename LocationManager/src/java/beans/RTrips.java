/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Stateless
@XmlRootElement(name = "trips")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTrips {

    @XmlElement(name = "trip")
    private List<RTrip> trips = new ArrayList<>();

    /**
     * @return the trips
     */
    public List<RTrip> getTrips() {
        return trips;
    }

    /**
     * @param trips the trips to set
     */
    public void setTrips(List<RTrip> trips) {
        this.trips = trips;
    }
    
    
}
