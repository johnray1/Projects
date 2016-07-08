/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.famoco.beans;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 *
 * @author ismaelnzamutuma
 */
@JsonRootName("transporterMessage")
public class TransporterMessage {
    private Transporter transporter;
    private String message;

    /**
     * @return the transporter
     */
    public Transporter getTransporter() {
        return transporter;
    }

    /**
     * @param transporter the transporter to set
     */
    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
