/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcs.model;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author JohnRay
 */
public class DashboardNumbersResponse {
    
    @JsonProperty("chargesCount")
    private Long chargesCount;
    
    @JsonProperty("chargesValue")
    private Double chargesValue;
    
    @JsonProperty("paidChargesCount")
    private Long paidChargesCount;
    
    @JsonProperty("paidChargesValue")
    private Double paidChargesValue;
    
    
    
    
    @JsonProperty("paidTicketsCount")
    private Long paidTicketsCount;
    
    @JsonProperty("paidTicketsValue")
    private Double paidTicketsValue;
    
    @JsonProperty("unPaidTicketsCount")
    private Long unPaidTicketsCount;
    
    @JsonProperty("unPaidTicketsValue")
    private Double unPaidTicketsValue;
    
    
    
    
    @JsonProperty("paidPenaltiesCount")
    private Long paidPenaltiesCount;
    
    @JsonProperty("paidPenaltiesValue")
    private Double paidPenaltiesValue;
    
    @JsonProperty("unPaidPenaltieCount")
    private Long unPaidPenaltieCount;
    
    @JsonProperty("unPaidPenaltiesValue")
    private Double unPaidPenaltiesValue;
    
    
    
    @JsonProperty("resultCode")
    private String resultCode;
    
    @JsonProperty("resultDescr")
    private String resultDescr;
    
    
    
    @JsonProperty("payments")
    private List<PaymentReportInfoModel> payments;
    
    @JsonProperty("totalPayments")
    private String totalPayments;
    
    /**
     * @return the chargesCount
     */
    public Long getChargesCount() {
        return chargesCount;
    }
    
    /**
     * @param chargesCount the chargesCount to set
     */
    public void setChargesCount(Long chargesCount) {
        this.chargesCount = chargesCount;
    }
    
    /**
     * @return the chargesValue
     */
    public Double getChargesValue() {
        return chargesValue;
    }
    
    /**
     * @param chargesValue the chargesValue to set
     */
    public void setChargesValue(Double chargesValue) {
        this.chargesValue = chargesValue;
    }
    
    /**
     * @return the paidChargesCount
     */
    public Long getPaidChargesCount() {
        return paidChargesCount;
    }
    
    /**
     * @param paidChargesCount the paidChargesCount to set
     */
    public void setPaidChargesCount(Long paidChargesCount) {
        this.paidChargesCount = paidChargesCount;
    }
    
    /**
     * @return the paidChargesValue
     */
    public Double getPaidChargesValue() {
        return paidChargesValue;
    }
    
    /**
     * @param paidChargesValue the paidChargesValue to set
     */
    public void setPaidChargesValue(Double paidChargesValue) {
        this.paidChargesValue = paidChargesValue;
    }
    
    /**
     * @return the paidTicketsCount
     */
    public Long getPaidTicketsCount() {
        return paidTicketsCount;
    }
    
    /**
     * @param paidTicketsCount the paidTicketsCount to set
     */
    public void setPaidTicketsCount(Long paidTicketsCount) {
        this.paidTicketsCount = paidTicketsCount;
    }
    
    /**
     * @return the paidTicketsValue
     */
    public Double getPaidTicketsValue() {
        return paidTicketsValue;
    }
    
    /**
     * @param paidTicketsValue the paidTicketsValue to set
     */
    public void setPaidTicketsValue(Double paidTicketsValue) {
        this.paidTicketsValue = paidTicketsValue;
    }
    
    /**
     * @return the paidPenaltiesCount
     */
    public Long getPaidPenaltiesCount() {
        return paidPenaltiesCount;
    }
    
    /**
     * @param paidPenaltiesCount the paidPenaltiesCount to set
     */
    public void setPaidPenaltiesCount(Long paidPenaltiesCount) {
        this.paidPenaltiesCount = paidPenaltiesCount;
    }
    
    /**
     * @return the paidPenaltiesValue
     */
    public Double getPaidPenaltiesValue() {
        return paidPenaltiesValue;
    }
    
    /**
     * @param paidPenaltiesValue the paidPenaltiesValue to set
     */
    public void setPaidPenaltiesValue(Double paidPenaltiesValue) {
        this.paidPenaltiesValue = paidPenaltiesValue;
    }
    
    /**
     * @return the unPaidTicketsCount
     */
    public Long getUnPaidTicketsCount() {
        return unPaidTicketsCount;
    }
    
    /**
     * @param unPaidTicketsCount the unPaidTicketsCount to set
     */
    public void setUnPaidTicketsCount(Long unPaidTicketsCount) {
        this.unPaidTicketsCount = unPaidTicketsCount;
    }
    
    /**
     * @return the unPaidTicketsValue
     */
    public Double getUnPaidTicketsValue() {
        return unPaidTicketsValue;
    }
    
    /**
     * @param unPaidTicketsValue the unPaidTicketsValue to set
     */
    public void setUnPaidTicketsValue(Double unPaidTicketsValue) {
        this.unPaidTicketsValue = unPaidTicketsValue;
    }
    
    /**
     * @return the unPaidPenaltieCount
     */
    public Long getUnPaidPenaltieCount() {
        return unPaidPenaltieCount;
    }
    
    /**
     * @param unPaidPenaltieCount the unPaidPenaltieCount to set
     */
    public void setUnPaidPenaltieCount(Long unPaidPenaltieCount) {
        this.unPaidPenaltieCount = unPaidPenaltieCount;
    }
    
    /**
     * @return the unPaidPenaltiesValue
     */
    public Double getUnPaidPenaltiesValue() {
        return unPaidPenaltiesValue;
    }
    
    /**
     * @param unPaidPenaltiesValue the unPaidPenaltiesValue to set
     */
    public void setUnPaidPenaltiesValue(Double unPaidPenaltiesValue) {
        this.unPaidPenaltiesValue = unPaidPenaltiesValue;
    }
    
    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }
    
    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    
    /**
     * @return the resultDescr
     */
    public String getResultDescr() {
        return resultDescr;
    }
    
    /**
     * @param resultDescr the resultDescr to set
     */
    public void setResultDescr(String resultDescr) {
        this.resultDescr = resultDescr;
    }

    /**
     * @return the payments
     */
    public List<PaymentReportInfoModel> getPayments() {
        return payments;
    }

    /**
     * @param payments the payments to set
     */
    public void setPayments(List<PaymentReportInfoModel> payments) {
        this.payments = payments;
    }

    /**
     * @return the totalPayments
     */
    public String getTotalPayments() {
        return totalPayments;
    }

    /**
     * @param totalPayments the totalPayments to set
     */
    public void setTotalPayments(String totalPayments) {
        this.totalPayments = totalPayments;
    }
    
    
    
    
}
