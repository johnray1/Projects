/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.models;

import com.oltranz.engenpayfuel.library.CommonLibrary;

/**
 *
 * @author manzi
 */
public class ResultObject {
    
    private Object object;
    private Class objectClass;
    private String message;
    private int statusCode;
    private String jsonFormat;
    private String xmlFormat;

    /**
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(Object object) {
        this.object = object;
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

    /**
     * @return the objectClass
     */
    public Class getObjectClass() {
        return objectClass;
    }

    /**
     * @param objectClass the objectClass to set
     */
    public void setObjectClass(Class objectClass) {
        this.objectClass = objectClass;
    }

    /**
     * @return the jsonFormat
     */
    public String getJsonFormat() {
        String objectJson=CommonLibrary.marshalling(object, objectClass, "json");
        String[] typeComponents =objectClass.getName().split("\\.", -1);
        jsonFormat="{\""+typeComponents[typeComponents.length-1]+"\":"+objectJson+",\n";
        jsonFormat+="\"message\":\""+message+"\",\n";
        jsonFormat+="\"statusCode\":"+getStatusCode()+"\n}";
        return jsonFormat;
    }

    /**
     * @return the xmlFormat
     */
    public String getXmlFormat() {
        String objectXML=CommonLibrary.marshalling(object, objectClass, "xml");
        xmlFormat="<resultMessage>";
        xmlFormat+=objectXML;
        xmlFormat+="<message>"+message+"</message>";
        xmlFormat+="</resultMessage>";
        return xmlFormat;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    

   
}
