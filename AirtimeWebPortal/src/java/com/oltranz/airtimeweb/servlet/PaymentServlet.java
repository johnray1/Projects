/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author John
 */
@WebServlet("/paymentServlet")
public class PaymentServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        // read form fields
        String resp_code = request.getParameter("resp_code");
        String resp_desc = request.getParameter("resp_desc");
        String tx_ref = request.getParameter("tx_ref");
        String recharge_pin = request.getParameter("recharge_pin");
        String signature = request.getParameter("signature");
        
        
        // get response writer
        PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2>Your resp_code is: " + resp_code + "<br/>";      
        htmlRespone += "Your resp_desc is: " + resp_desc + "</h2>";   
        htmlRespone += "<h2>Your tx_ref is: " + tx_ref + "<br/>";      
        htmlRespone += "Your recharge_pin is: " + recharge_pin + "</h2>";   
        htmlRespone += "<h2>Your signature is: " + signature + "<br/>"; 
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
    }
    
}
