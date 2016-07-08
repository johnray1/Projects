/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.authenticate.servlets;

import com.oltranz.deserializejson.DeserializationofObjects;
import com.oltranz.famoco.beans.DashboardBuses;
import com.oltranz.famoco.beans.DashboardContractors;
import com.oltranz.famoco.namedBeans.DisplayBeans;
import com.oltranz.famoco.beans.TransactionsDisplay;
import com.oltranz.famoco.beans.TransporterMessage;
import com.oltranz.famoco.beans.UserBean;
import com.oltranz.famoco.namedBeans.BusesDisplay;
import com.oltranz.famoco.namedBeans.MessagesDisplay;
import com.oltranz.models.AccessTokenWithMessage;

import com.oltranz.models.UserRole;
import java.io.IOException;
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
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import utils.CommonLibrary;

@WebServlet(name = "UserAuthentication", urlPatterns = {"/userAuthentication"})
public class UserAuthentication extends HttpServlet {

    AccessTokenWithMessage acct ;
    @ManagedProperty(value = "#{authe}")
    private DisplayBeans displayBeans =new DisplayBeans();
    @ManagedProperty(value = "#{busesDisplay}")
    private BusesDisplay busesDisplay;

    private TransactionsDisplay transactions;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  {
 String address = "";
        MessagesDisplay messageDisplay = new MessagesDisplay();
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserBean userBean = new UserBean();
            userBean.setPhoneNumber(username);
            userBean.setPassword(password);
            ObjectMapper mapper = new ObjectMapper();
            String authData = mapper.writeValueAsString(userBean);
           
            
            String authenticationUrl = "http://41.74.172.132:8080/IntercityTransport/MembershipServices/user/authenticate";

            Response dataResponse = CommonLibrary.sendRESTRequest(authenticationUrl, authData, MediaType.APPLICATION_JSON_TYPE.toString(), "POST");
            String data = dataResponse.readEntity(String.class);
          
            
            acct = new AccessTokenWithMessage();
            acct = (AccessTokenWithMessage) mapper.readValue(data, AccessTokenWithMessage.class);
       
            if(acct.getAccesstoken()==null)
            {
                
            messageDisplay.setLoginFailure("Your access credentials do not match. Please try again");
            request.getServletContext().setAttribute("requestMessages", messageDisplay);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/faces/_login.xhtml");
            dispatcher.forward(request, response);
            
            }
            
            
            List<UserRole> roles = new ArrayList<>();
            roles = acct.getAccesstoken().getUserDetails().getRoles();
           // roles = udm.getRoles();
            UserRole ur = new UserRole();
            for (UserRole userRole : roles) {
                ur = userRole;
                break;
            }
            displayBeans.setAccessResult(acct);
                String urlTransporter = "http://41.74.172.132:8080/IntercityTransport/TransportersManagementServices/transporterByUserRole/" + ur.getId();

            String transporterjson = CommonLibrary.sendRESTRequest(urlTransporter, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
           

            TransporterMessage transportermessage = (TransporterMessage) mapper.readValue(transporterjson, TransporterMessage.class);

            displayBeans.setTransportermessage(transportermessage);
          //  displayBeans.setAccessResult(acct);
            String activebusesUrl = "http://41.74.172.132:8080/IntercityTransport/BusesManagementServices/buses/transporter/";
            activebusesUrl = activebusesUrl + transportermessage.getTransporter().getId();
           
            String busesjson = CommonLibrary.sendRESTRequest(activebusesUrl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
           System.out.println(busesjson);
            DeserializationofObjects dsob = new DeserializationofObjects();
            com.oltranz.famoco.beans.Buses buses = dsob.deserializeBuses(busesjson);
            displayBeans.setActivenumberBuses(buses.getBuses().size());

            displayBeans.getTransporterTransactions("" + displayBeans.getTransportermessage().getTransporter().getId());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
            displayBeans.setFrom(sdf.format(new java.util.Date()));
            displayBeans.setTo(sdf.format(new java.util.Date()));

       
///// dashboard buses 
     String  dashboardbusesurl="http://41.74.172.132:8080/IntercityTransport/salesServices/sales/getTodayBusesSalesReportPerTransporter/";
     dashboardbusesurl=dashboardbusesurl+displayBeans.getTransportermessage().getTransporter().getId();
    
      
     String busesdashboardjson = CommonLibrary.sendRESTRequest(dashboardbusesurl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
     DashboardBuses  dashboardBuses=new DashboardBuses();
     dashboardBuses =(DashboardBuses)mapper.readValue(busesdashboardjson, DashboardBuses.class);
      busesDisplay=new BusesDisplay();
      busesDisplay.setDashboardBuses(dashboardBuses);
      
        
  //end dashboard buses 
          
  //dashboard contractors 
    String  dashboardcontractorsurl="http://41.74.172.132:8080/IntercityTransport/salesServices/sales/getBusesOwnersSalesReportPerTransporter/";
     dashboardcontractorsurl=dashboardcontractorsurl+displayBeans.getTransportermessage().getTransporter().getId();
    
      
     String dashboardcontractorsjson = CommonLibrary.sendRESTRequest(dashboardcontractorsurl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
     DashboardContractors  dashboardcontractors = new DashboardContractors();
     dashboardcontractors =(DashboardContractors)mapper.readValue(dashboardcontractorsjson, DashboardContractors.class);
     // busesDisplay=new BusesDisplay();
      busesDisplay.setDashboardContractors(dashboardcontractors);
       
            
          
          
  // end dashoboard contractors
          
          request.getServletContext().setAttribute("busesDisplay", busesDisplay);
            request.getServletContext().setAttribute("authe", displayBeans);

            if (acct != null) {
                address = "/faces/_dashboard.xhtml";
            } else {
///    displayBeans.setData("Your athentication was not successful. Try again.");
                address = "/faces/_login.xhtml";
            }
       
   
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
            dispatcher.forward(request, response);

       } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

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

}
