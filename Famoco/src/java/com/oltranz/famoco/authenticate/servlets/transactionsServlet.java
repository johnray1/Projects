/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.authenticate.servlets;

import com.oltranz.famoco.beans.FilterSubcontractor;
import com.oltranz.famoco.beans.FilterSubcontractors;
import com.oltranz.famoco.beans.FilteringParameter;
import com.oltranz.famoco.beans.SubContractor;
import com.oltranz.famoco.beans.SubContractors;
import com.oltranz.famoco.namedBeans.DisplayBeans;
import java.io.IOException;
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
@WebServlet(name = "transactionsServlet", urlPatterns = {"/transactionsServlet"})
public class transactionsServlet extends HttpServlet {
@ManagedProperty("authe")
DisplayBeans displayBeans;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       displayBeans= (DisplayBeans)request.getServletContext().getAttribute("authe");
        displayBeans.getTransporterTransactions(""+displayBeans.getTransportermessage().getTransporter().getId());
       FilterSubcontractor fsc = new FilterSubcontractor();
       fsc.setId(displayBeans.getTransportermessage().getTransporter().getId());
       fsc.setName(displayBeans.getTransportermessage().getTransporter().getName());
       List<FilterSubcontractor> list = new ArrayList();
       list.add(fsc);
       FilterSubcontractors fscs = new FilterSubcontractors();
       fscs.setFilterSubs(list);
      
   String UrlSubcont=  "http://41.74.172.132:8080/IntercityTransport/ServicesProvidersManagementServices/serviceProviders/transporter/service/"+displayBeans.getTransportermessage().getTransporter().getId()+"/2";
       String subcontractorsjson= CommonLibrary.sendRESTRequest(UrlSubcont, "", MediaType.TEXT_PLAIN, "GET").readEntity(String.class);
    
       ObjectMapper mapper = new ObjectMapper();
       SubContractors subcontractors = (SubContractors)mapper.readValue(subcontractorsjson, SubContractors.class);
       List subcos=new ArrayList<>();
       System.out.println(subcontractorsjson);
       for(SubContractor subco:subcontractors.getSubcontractors())
       { 
           FilterSubcontractor fsc1 = new FilterSubcontractor();
           fsc1.setId(subco.getId());
           fsc1.setName(subco.getName());
           fscs.getFilterSubs().add(fsc1);
           
       }
        
       displayBeans.setFiltersubc(fscs);
       FilteringParameter fp = new FilteringParameter();
       displayBeans.setFilteringParameter(fp);
        request.getServletContext().setAttribute("authe", displayBeans);
        
        String address="/faces/_transactions.xhtml";
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

}
