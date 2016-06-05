/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.RSchedule;
import beans.RSchedules;
import entities.Location;
import entities.LocationAliase;
import entities.Route;
import entities.Schedule;
import entities.SchedulePK;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class ScheduleHandlerEjb {
    
    @PersistenceContext
    private EntityManager em;
    
    int departId;
    int destId;
    int routeId;
    int scheduleId;
    
    public String sendXML(String url,String xmlData){
        try
        {
            URL oURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
              con.setRequestMethod("POST");
              con.setRequestProperty("Content-type", "Application/xml; charset=utf-8");
              con.setDoOutput(true);
              con.setDoInput(true);
            
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
              wr.writeBytes(xmlData);
	      wr.flush();
	      wr.close();
            System.out.println("Data to post :"+xmlData);
            
            BufferedReader in1= new BufferedReader(new InputStreamReader(con.getInputStream()));
	    
            String inputLine;
	    StringBuffer response = new StringBuffer();
 
            while ((inputLine = in1.readLine()) != null) 
                {
			response.append(inputLine);
		}
            in1.close();
            con.disconnect(); 
            String xmldata=response.toString();
                
            return xmldata; 
        }
        
        catch(Exception e)
        {
            System.out.println("Error in the posting: "+e.getMessage());
            return null;
        }
     
    
}
    
    public boolean persistScheduleData(RSchedules rss) throws Exception{
        for (RSchedule rs : rss.getSchedules()){
            
            SimpleDateFormat sdf=new SimpleDateFormat();
            String d=rs.getDate()+ rs.getTime();
            
            Date datetime=sdf.parse(d);
            int id=rs.getId();
            String depart=rs.getDepart();
            String dest=rs.getDest();
            String route=rs.getRoute();
            
            
            departId=isLocationAvailable(depart);
            destId=isLocationAvailable(dest);
            routeId=isRouteAvailable(route);
            scheduleId=isScheduleIdAvailable(id,datetime,departId,destId,routeId);
        }
        if(departId<=0 && destId<=0 && routeId<=0 && scheduleId<=0){
            return false;
        }
        else{
            return true;
        }
        
    }
    
    public int isLocationAvailable(String locName)throws Exception{
        List<Location> ll=(List<Location>)em.createNamedQuery("Location.findByName").setParameter("name", locName).getResultList();
        Iterator i=ll.iterator();
        Location l=new Location();
        while(i.hasNext()){
            l=(Location) i.next();
        }
        if(l.getName()==null){
            l.setName(locName);
            em.persist(l);
            em.flush();
            int locId=l.getId();
            String lName=l.getName();
            LocationAliase la=new LocationAliase();
            la.setLocId(l);
            la.setLocAliasName(locName);
            em.persist(la);
            return locId;
        }
        else{
            return 0; 
        }
    }
    
    public int isRouteAvailable(String routeName)throws Exception{
        List<Route> rl=(List<Route>)em.createNamedQuery("Route.findByName").setParameter("name", routeName).getResultList();
        Iterator i=rl.iterator();
        Route r=new Route();
        while(i.hasNext()){
            r=(Route) i.next();
        }
        if(r.getName()==null){
            r.setName(routeName);
            em.persist(r);
            em.flush();
            int routeId=r.getId();
            return routeId;
        }
        else{
            return 0; 
        }
    
    }
    
    public int isScheduleIdAvailable(int scheduleId,Date datetime,int departId,int destId,int routeId)throws Exception{
        List<Schedule> sl=(List<Schedule>)em.createNamedQuery("Schedule.findByReintegratorscheduleid").setParameter("reintegratorscheduleid", scheduleId).getResultList();
        Iterator i=sl.iterator();
        Schedule s=new Schedule();
        while(i.hasNext()){
            s=(Schedule) i.next();
        }
        if(s.getSchedulePK().getReintegratorscheduleid()==0){
            SchedulePK spk=new SchedulePK();
            spk.setDepartDatetime(datetime);
            spk.setReintegratorscheduleid(scheduleId);
            spk.setTripdepartLocId(departId);
            spk.setTripdestLocId(destId);
            spk.setTriprouteId(routeId);
            s.setSchedulePK(spk);
            em.persist(s);
            em.flush();
            int sId=s.getSchedulePK().getReintegratorscheduleid();
            return sId;
        }
        else{
            return 0; 
        }
        
    }
}
