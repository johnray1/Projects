/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JOHN
 */
@XmlRootElement(name = "schedules")
@XmlAccessorType(XmlAccessType.FIELD)
public class RSchedules {
    
    @XmlElement(name = "schedule")
    private List<RSchedule> schedules = new ArrayList<>();

    /**
     * @return the schedules
     */
    public List<RSchedule> getSchedules() {
        return schedules;
    }

    /**
     * @param schedules the schedules to set
     */
    public void setSchedules(List<RSchedule> schedules) {
        this.schedules = schedules;
    }
    
}
