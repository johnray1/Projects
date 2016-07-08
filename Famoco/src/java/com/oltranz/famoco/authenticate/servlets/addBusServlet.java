/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.authenticate.servlets;

import com.oltranz.famoco.beans.Bus;
import com.oltranz.famoco.namedBeans.DisplayBeans;
import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.bean.ManagedProperty;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import utils.CommonLibrary;

/**
 *
 * @author ismaelnzamutuma
 */
@WebServlet(name = "addBusServlet", urlPatterns = {"/addBusServlet"})
public class addBusServlet extends HttpServlet {
@ManagedProperty(value = "#{authe}")
    private DisplayBeans displayBeans = new DisplayBeans();
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
        response.setContentType("text/html;charset=UTF-8");
      displayBeans = (DisplayBeans)request.getServletContext().getAttribute("authe");
      if(displayBeans==null)
      {
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Famoco/_login.xhtml");
   
          
          
          
          
          
  dispatcher.forward(request, response); 
      }
     
        String plateNumber = request.getParameter("plate");
        String description = request.getParameter("description");
        String ownership = request.getParameter("ownership");
        Bus bus = new Bus();
        bus.setDescr(description);
        bus.setNumberPlate(plateNumber);
        if(ownership.equals("1"))
        {
        bus.setTransporterOwned("true");
        bus.setOwnerId(""+displayBeans.getTransportermessage().getTransporter().getId());
        }
        else 
        {
            bus.setTransporterOwned("false");}
                 
        
        ObjectMapper mapper = new ObjectMapper();
     String busjson=   mapper.writeValueAsString(bus);
     System.out.println(busjson);
     String url ="http://41.74.172.132:8080/IntercityTransport/BusesManagementServices/bus/new";
     CommonLibrary.sendRESTRequest(url, busjson, "application/json", "POST");

      String address = "/busesServlet";
     
 //response.sendRedirect(address);
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
   
  dispatcher.forward(request, response);
        
        
        
        
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

}
