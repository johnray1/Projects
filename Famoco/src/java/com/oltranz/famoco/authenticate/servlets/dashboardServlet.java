/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.authenticate.servlets;

import com.oltranz.deserializejson.DeserializationofObjects;
import com.oltranz.famoco.beans.DashboardBuses;
import com.oltranz.famoco.beans.DashboardContractors;
import com.oltranz.famoco.beans.TransactionsDisplay;
import com.oltranz.famoco.beans.TransporterMessage;
import com.oltranz.famoco.namedBeans.BusesDisplay;
import com.oltranz.famoco.namedBeans.DisplayBeans;
import com.oltranz.models.AccessTokenWithMessage;
import com.oltranz.models.UserRole;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import utils.CommonLibrary;

/**
 *
 * @author ismaelnzamutuma
 */
@WebServlet(name = "dashboardServlet", urlPatterns = {"/dashboardServlet"})
public class dashboardServlet extends HttpServlet {
  private AccessTokenWithMessage acct ;
    @ManagedProperty(value = "#{authe}")
    private DisplayBeans displayBeans =new DisplayBeans();
    @ManagedProperty(value = "#{busesDisplay}")
    private BusesDisplay busesDisplay;

    private TransactionsDisplay transactions;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ObjectMapper mapper = new ObjectMapper();
       
     displayBeans =(DisplayBeans)request.getServletContext().getAttribute("authe");
     acct = displayBeans.getAccessResult();
     System.out.println(acct.getMessage());
         List<UserRole> roles = new ArrayList<>();
            roles = acct.getAccesstoken().getUserDetails().getRoles();
           // roles = udm.getRoles();
            UserRole ur = new UserRole();
            for (UserRole userRole : roles) {
                ur = userRole;
                break;
            }
        String urlTransporter = "http://41.74.172.132:8080/IntercityTransport/TransportersManagementServices/transporterByUserRole/" + ur.getId();

            String transporterjson = CommonLibrary.sendRESTRequest(urlTransporter, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
           

            TransporterMessage transportermessage = (TransporterMessage) mapper.readValue(transporterjson, TransporterMessage.class);

            getDisplayBeans().setTransportermessage(transportermessage);
          //  displayBeans.setAccessResult(acct);
            String activebusesUrl = "http://41.74.172.132:8080/IntercityTransport/BusesManagementServices/buses/transporter/";
            activebusesUrl = activebusesUrl + transportermessage.getTransporter().getId();
           
            String busesjson = CommonLibrary.sendRESTRequest(activebusesUrl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
           System.out.println(busesjson);
            DeserializationofObjects dsob = new DeserializationofObjects();
            com.oltranz.famoco.beans.Buses buses = dsob.deserializeBuses(busesjson);
            getDisplayBeans().setActivenumberBuses(buses.getBuses().size());

            getDisplayBeans().getTransporterTransactions("" + getDisplayBeans().getTransportermessage().getTransporter().getId());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
            getDisplayBeans().setFrom(sdf.format(new java.util.Date()));
            getDisplayBeans().setTo(sdf.format(new java.util.Date()));

       
///// dashboard buses 
     String  dashboardbusesurl="http://41.74.172.132:8080/IntercityTransport/salesServices/sales/getTodayBusesSalesReportPerTransporter/";
     dashboardbusesurl=dashboardbusesurl+getDisplayBeans().getTransportermessage().getTransporter().getId();
    
      
     String busesdashboardjson = CommonLibrary.sendRESTRequest(dashboardbusesurl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
     DashboardBuses  dashboardBuses=new DashboardBuses();
     dashboardBuses =(DashboardBuses)mapper.readValue(busesdashboardjson, DashboardBuses.class);
        setBusesDisplay(new BusesDisplay());
        getBusesDisplay().setDashboardBuses(dashboardBuses);
      
        
  //end dashboard buses 
          
  //dashboard contractors 
    String  dashboardcontractorsurl="http://41.74.172.132:8080/IntercityTransport/salesServices/sales/getBusesOwnersSalesReportPerTransporter/";
     dashboardcontractorsurl=dashboardcontractorsurl+getDisplayBeans().getTransportermessage().getTransporter().getId();
    
      
     String dashboardcontractorsjson = CommonLibrary.sendRESTRequest(dashboardcontractorsurl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
     DashboardContractors  dashboardcontractors = new DashboardContractors();
     dashboardcontractors =(DashboardContractors)mapper.readValue(dashboardcontractorsjson, DashboardContractors.class);
     // busesDisplay=new BusesDisplay();
        getBusesDisplay().setDashboardContractors(dashboardcontractors);
       
            
          
          
  // end dashoboard contractors
          
          request.getServletContext().setAttribute("busesDisplay", getBusesDisplay());
            request.getServletContext().setAttribute("authe", getDisplayBeans());
String address="";
            if (getAcct() != null) {
                address = "/faces/_dashboard.xhtml";
            } else {
///    displayBeans.setData("Your athentication was not successful. Try again.");
                address = "/faces/_login.xhtml";
            }
       
   
            
        
       
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
    dispatcher.forward(request,response); 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * @return the acct
     */
    public AccessTokenWithMessage getAcct() {
        return acct;
    }

    /**
     * @param acct the acct to set
     */
    public void setAcct(AccessTokenWithMessage acct) {
        this.acct = acct;
    }

    /**
     * @return the displayBeans
     */
    public DisplayBeans getDisplayBeans() {
        return displayBeans;
    }

    /**
     * @param displayBeans the displayBeans to set
     */
    public void setDisplayBeans(DisplayBeans displayBeans) {
        this.displayBeans = displayBeans;
    }

    /**
     * @return the busesDisplay
     */
    public BusesDisplay getBusesDisplay() {
        return busesDisplay;
    }

    /**
     * @param busesDisplay the busesDisplay to set
     */
    public void setBusesDisplay(BusesDisplay busesDisplay) {
        this.busesDisplay = busesDisplay;
    }

    /**
     * @return the transactions
     */
    public TransactionsDisplay getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(TransactionsDisplay transactions) {
        this.transactions = transactions;
    }

}
