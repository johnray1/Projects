/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author JOHN
 */
public class PaymentIp {
    
    private String tId;
    private String pMode;
    private String request;

    public PaymentIp() {
    }

    public PaymentIp(String tId, String pMode, String request) {
        this.tId = tId;
        this.pMode = pMode;
        this.request = request;
    }

    /**
     * @return the tId
     */
    public String gettId() {
        return tId;
    }

    /**
     * @param tId the tId to set
     */
    public void settId(String tId) {
        this.tId = tId;
    }

    /**
     * @return the pMode
     */
    public String getpMode() {
        return pMode;
    }

    /**
     * @param pMode the pMode to set
     */
    public void setpMode(String pMode) {
        this.pMode = pMode;
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(String request) {
        this.request = request;
    }

    
    
}
