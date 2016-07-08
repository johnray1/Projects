/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.authenticate.servlets;

import com.oltranz.deserializejson.DeserializationofObjects;
import com.oltranz.famoco.beans.Devices;
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
import javax.ws.rs.core.MediaType;
import utils.CommonLibrary;

/**
 *
 * @author ismaelnzamutuma
 */
@WebServlet(name = "devicesServlet", urlPatterns = {"/devicesServlet"})
public class devicesServlet extends HttpServlet {

@ManagedProperty(value="authe")
private DisplayBeans displayBeans ;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String devicesUrl ="http://41.74.172.132:8080/IntercityTransport/sellingDevicesManagementServices/transporterSellingDevices";
        displayBeans = (DisplayBeans)   request.getServletContext().getAttribute("authe");
        //System.out.println(getDisplayBeans().getTransportermessage().getMessage());
        devicesUrl =devicesUrl+"/"+getDisplayBeans().getTransportermessage().getTransporter().getId();
     System.out.println(devicesUrl);
        
        String devicesmessage =     CommonLibrary.sendRESTRequest(devicesUrl, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
        DeserializationofObjects doo = new DeserializationofObjects();
       System.out.println(devicesmessage);
      Devices   devices = doo.deserializeDevices(devicesmessage);
        displayBeans.setDevicesmessage(devices);
       request.getServletContext().setAttribute("authe", displayBeans);
       
        String address="/faces/devices.xhtml";
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
     * @return the devices
     */
   

}
