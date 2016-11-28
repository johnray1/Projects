/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.models;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class UserShiftModel {
    
    @JsonProperty("userId")
    private int userId;
    
    @JsonProperty("paymentReport")
    private List<UserPaymentModel> paymentReport;

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the paymentReport
     */
    public List<UserPaymentModel> getPaymentReport() {
        return paymentReport;
    }

    /**
     * @param paymentReport the paymentReport to set
     */
    public void setPaymentReport(List<UserPaymentModel> paymentReport) {
        this.paymentReport = paymentReport;
    }

    
    
    
    
}
