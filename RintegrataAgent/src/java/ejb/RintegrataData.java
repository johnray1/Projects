/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.Purchase;
import beans.Reserve;
import beans.Schedule;
import beans.Trips;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ejb.Stateless;

/**
 *
 * @author Owner
 */
@Stateless
public class RintegrataData {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    String url = "http://rintegrata.com/prs/services/oltranz/";

    public String rintegrataTrips(Trips trips) throws MalformedURLException, IOException {
           
        String cNm=trips.getCompany();
        int cId=trips.getId();
        //URL Data
        URL xmlUrl = new URL(url+"get_trips.php?" + "company=" + cNm + "&id=" + cId);
        InputStream is = xmlUrl.openStream();

        //forming an trip object from url data
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = sb.toString();
            
            return xmldata ;

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public String getRintegrataSchedules(Schedule schedule) throws IOException, IOException {
        //URL Data
        String cNm=schedule.getCompany();
        int cId=schedule.getId();
        String trip=schedule.getDepart()+"-"+schedule.getDest();
        String route=schedule.getRoute();
        String day=schedule.getDay();
        
        URL xmlUrl = new URL(url+"get_schedules.php?" + "company=" + cNm + "&id=" + cId + "&trip=" + trip +"&route=" + route + "&day=" + day);
        InputStream is = xmlUrl.openStream();

        //forming an trip object from url data
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = "";
            return xmldata = sb.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    public String reserveRintegrataTicket(Reserve reserve) throws IOException, IOException {
        
        String cNm=reserve.getCompany();
        int cId=reserve.getId();
        String trip=reserve.getDepart()+"-"+reserve.getDest();
        String route=reserve.getRoute();
        String date=reserve.getDate();
        String time=reserve.getTime();
        String name=reserve.getName();
        int code=reserve.getCode();
        String reseller=reserve.getReseller();
        String telecom=reserve.getTelecom();
        
        //URL Data
        URL xmlUrl = new URL(url+"reserve.php?" + "company=" + cNm + "&id=" + cId + "&trip=" + trip +"&route=" + route + "&date=" + date + "&time=" + time + "&name=" + name + "&code=" + code + "&reseller=" + reseller + "&telecom=" + telecom);
        InputStream is = xmlUrl.openStream();

        //forming an trip object from url data
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = "";
            return xmldata = sb.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return null;
    }
    
    public String purchaseReservedRintegrataTicket(Purchase purchase) throws IOException, IOException {
        
        String cNm=purchase.getCompany();
        int cId=purchase.getId();
        int resCode=purchase.getCode();
        String reseller=purchase.getReseller();
        String telecom=purchase.getTelecom();
        //URL Data
        URL xmlUrl = new URL(url+"purchase.php?" +"company="+cNm+"&id="+cId+"&reservation_code="+resCode+"&reseller="+reseller+"&telecom="+telecom);
        InputStream is = xmlUrl.openStream();

        //forming an trip object from url data
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
            String xmldata = "";
            return xmldata = sb.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return null;
    }
}
