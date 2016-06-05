/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Owner
 */
@Stateless
@XmlRootElement(name = "trip")
@XmlAccessorType (XmlAccessType.FIELD)
public class RTrip {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private String depart;
    
    private String dest;
    
    private int price;
    
    private String route;

    /**
     * @return the depart
     */
    public String getDepart() {
        return depart;
    }

    /**
     * @param depart the depart to set
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * @return the dest
     */
    public String getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(String route) {
        this.route = route;
    }

    public RTrip(String depart, String dest, int price, String route) {
        this.depart = depart;
        this.dest = dest;
        this.price = price;
        this.route = route;
    }

    public RTrip() {
    }
    
    
    

    
    
}
