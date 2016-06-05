/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.IntercityTransport.services;


import com.oltranz.IntercityTransport.beans.UssdManager;
import com.oltranz.IntercityTransport.models.UssdRequest;
import com.oltranz.IntercityTransport.models.UssdResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author aimable
 */
@WebServlet(name = "UssdHandler", urlPatterns = {"/UssdHandler"})
public class UssdHandler extends HttpServlet {

    static final long serialVersionUID = 2111740233967932325L;

    private UssdManager ussdManager;

    ConcurrentHashMap<Object, Object> sessions = new ConcurrentHashMap<>();

    private static final Logger log = Logger.getLogger("UssdHandler");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml");
        try (PrintWriter out = response.getWriter()) {
            InputStream xmlrequest = request.getInputStream();
            String inputStreamString = new Scanner(xmlrequest, "UTF-8").useDelimiter("\\A").next();
            UssdRequest ussdReq = getUssdRequest(inputStreamString);
            if (sessions.get(ussdReq.getMsisdn()) == null) {
                ussdManager = new UssdManager();
                sessions.put(ussdReq.getMsisdn(), ussdManager);
            }
            else {
                ussdManager = (UssdManager) sessions.get(ussdReq.getMsisdn());
            }
            UssdResponse ussdResp = ussdManager.getNextMessage(ussdReq);
            out.print(getResponse(ussdResp));
        }

    }
    /**
     * this method is used create UssdRequest from xml string
     *
     * @param request
     * @return
     */
    public UssdRequest getUssdRequest(String request) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(UssdRequest.class);
            Unmarshaller unMarsh = ctx.createUnmarshaller();
            UssdRequest ussdRequest = (UssdRequest) unMarsh.unmarshal(new StringReader(request));
            return ussdRequest;
        }

        catch (JAXBException ex) {
            return null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String getResponse(UssdResponse ussdResponse) {

        try {
            JAXBContext ctx = JAXBContext.newInstance(UssdResponse.class);
            Marshaller marshaller = ctx.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(ussdResponse, writer);
            return writer.toString();
        }
        catch (JAXBException ex) {
            return null;
        }
    }

}
