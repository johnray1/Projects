package com.oltranz.airtime.model;



/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


public class CombinedSalesReport {
    private VtuSalesReport vtuSalesReport;
    private ItemTotals paymentTotals;
    private ItemTotals paymentProcessedTotal;
    private ItemTotals referersRewardingTotal;
    private ItemTotals InitialLoadingRewardingTotal;
    private ItemTotals manualFundingTotal;
    private ItemTotals WalletsLoadingTotal;
    private ItemTotals successfulAirtimePurchasesTotals;
    private ItemTotals failedAirtimePurchasesTotals;
    private ItemTotals airtimePurchasesTotals;
    private ItemTotals walletsBalancesTotals;
    
    /**
     * @return the vtuSalesReport
     */
    public VtuSalesReport getVtuSalesReport() {
        return vtuSalesReport;
    }
    
    /**
     * @param vtuSalesReport the vtuSalesReport to set
     */
    public void setVtuSalesReport(VtuSalesReport vtuSalesReport) {
        this.vtuSalesReport = vtuSalesReport;
    }
    
    /**
     * @return the paymentTotals
     */
    public ItemTotals getPaymentTotals() {
        return paymentTotals;
    }
    
    /**
     * @param paymentTotals the paymentTotals to set
     */
    public void setPaymentTotals(ItemTotals paymentTotals) {
        this.paymentTotals = paymentTotals;
    }
    
    /**
     * @return the paymentProcessedTotal
     */
    public ItemTotals getPaymentProcessedTotal() {
        return paymentProcessedTotal;
    }
    
    /**
     * @param paymentProcessedTotal the paymentProcessedTotal to set
     */
    public void setPaymentProcessedTotal(ItemTotals paymentProcessedTotal) {
        this.paymentProcessedTotal = paymentProcessedTotal;
    }
    
    /**
     * @return the referersRewardingTotal
     */
    public ItemTotals getReferersRewardingTotal() {
        return referersRewardingTotal;
    }
    
    /**
     * @param referersRewardingTotal the referersRewardingTotal to set
     */
    public void setReferersRewardingTotal(ItemTotals referersRewardingTotal) {
        this.referersRewardingTotal = referersRewardingTotal;
    }
    
    /**
     * @return the InitialLoadingRewardingTotal
     */
    public ItemTotals getInitialLoadingRewardingTotal() {
        return InitialLoadingRewardingTotal;
    }
    
    /**
     * @param InitialLoadingRewardingTotal the InitialLoadingRewardingTotal to set
     */
    public void setInitialLoadingRewardingTotal(ItemTotals InitialLoadingRewardingTotal) {
        this.InitialLoadingRewardingTotal = InitialLoadingRewardingTotal;
    }
    
    /**
     * @return the manualFundingTotal
     */
    public ItemTotals getManualFundingTotal() {
        return manualFundingTotal;
    }
    
    /**
     * @param manualFundingTotal the manualFundingTotal to set
     */
    public void setManualFundingTotal(ItemTotals manualFundingTotal) {
        this.manualFundingTotal = manualFundingTotal;
    }
    
    /**
     * @return the WalletsLoadingTotal
     */
    public ItemTotals getWalletsLoadingTotal() {
        return WalletsLoadingTotal;
    }
    
    /**
     * @param WalletsLoadingTotal the WalletsLoadingTotal to set
     */
    public void setWalletsLoadingTotal(ItemTotals WalletsLoadingTotal) {
        this.WalletsLoadingTotal = WalletsLoadingTotal;
    }
    
    /**
     * @return the successfulAirtimePurchasesTotals
     */
    public ItemTotals getSuccessfulAirtimePurchasesTotals() {
        return successfulAirtimePurchasesTotals;
    }
    
    /**
     * @param successfulAirtimePurchasesTotals the successfulAirtimePurchasesTotals to set
     */
    public void setSuccessfulAirtimePurchasesTotals(ItemTotals successfulAirtimePurchasesTotals) {
        this.successfulAirtimePurchasesTotals = successfulAirtimePurchasesTotals;
    }
    
    /**
     * @return the failedAirtimePurchasesTotals
     */
    public ItemTotals getFailedAirtimePurchasesTotals() {
        return failedAirtimePurchasesTotals;
    }
    
    /**
     * @param failedAirtimePurchasesTotals the failedAirtimePurchasesTotals to set
     */
    public void setFailedAirtimePurchasesTotals(ItemTotals failedAirtimePurchasesTotals) {
        this.failedAirtimePurchasesTotals = failedAirtimePurchasesTotals;
    }
    
    /**
     * @return the airtimePurchasesTotals
     */
    public ItemTotals getAirtimePurchasesTotals() {
        return airtimePurchasesTotals;
    }
    
    /**
     * @param airtimePurchasesTotals the airtimePurchasesTotals to set
     */
    public void setAirtimePurchasesTotals(ItemTotals airtimePurchasesTotals) {
        this.airtimePurchasesTotals = airtimePurchasesTotals;
    }
    
    /**
     * @return the walletsBalancesTotals
     */
    public ItemTotals getWalletsBalancesTotals() {
        return walletsBalancesTotals;
    }
    
    /**
     * @param walletsBalancesTotals the walletsBalancesTotals to set
     */
    public void setWalletsBalancesTotals(ItemTotals walletsBalancesTotals) {
        this.walletsBalancesTotals = walletsBalancesTotals;
    }
    
}
