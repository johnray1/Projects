/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author John
 */
public class Product {
    
    @JsonProperty("productId")
    private Integer productId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("hqPrice")
    private double hqPrice;
    
    @JsonProperty("measureUnity")
    private String measureUnity;
    
    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("statusName")
    private String statusName;
    
    @JsonProperty("productTypeId")
    private Integer productTypeId;
    
    @JsonProperty("productTypeName")
    private String productTypeName;
    
    

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the measureUnity
     */
    public String getMeasureUnity() {
        return measureUnity;
    }

    /**
     * @param measureUnity the measureUnity to set
     */
    public void setMeasureUnity(String measureUnity) {
        this.measureUnity = measureUnity;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the hqPrice
     */
    public double getHqPrice() {
        return hqPrice;
    }

    /**
     * @param hqPrice the hqPrice to set
     */
    public void setHqPrice(double hqPrice) {
        this.hqPrice = hqPrice;
    }

    /**
     * @return the productTypeId
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId the productTypeId to set
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * @param productTypeName the productTypeName to set
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * @return the statusName
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName the statusName to set
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    
    
}
