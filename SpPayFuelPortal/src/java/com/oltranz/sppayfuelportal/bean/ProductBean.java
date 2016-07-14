/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.Common;
import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.BranchProductList;
import com.oltranz.sppayfuelportal.model.BranchProductSingle;
import com.oltranz.sppayfuelportal.model.ProductList;
import com.oltranz.sppayfuelportal.model.ProductSingle;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="ProductBean")
@SessionScoped
public class ProductBean implements Serializable{
    
    private String branchId;
    private String productId;
    private String price;
    private String productName;
    private String productTypeId;
    private String measureUnity;
    private String saveActionName="Save";
    
    private ProductSingle productSingle;
    private ProductList productList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    private Boolean productMenuItemRendered;
    
    public ProductBean() {
        
        HttpSession session = SessionBean.getSession();
        String permissions=(String)session.getAttribute("permissions");
        
        byte[] permissionBytes =Common.shared.hexStringToByteArray(permissions);
        
        String s=Common.shared.byteArrayToString(permissionBytes);
        
        int bitValue=Common.shared.GetBit(permissionBytes, 1);//1 means 2nd Position bit in permisson
        
        
        productMenuItemRendered=bitValue==1;
        
    }
    
    
    public void init() {
        setSaveActionName("Save");
    }

    
    
    public String products(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu_active");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/ProductManagementService/products";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            productList=(ProductList)mapper.readValue(jsonResponse, ProductList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_product.xhtml?faces-redirect=true";
        
    }
    
    
    
    public void productForView(int productId){
        productById(productId);
    }
    
    
    public void productForEdit(int productId){
        productById(productId);
        saveActionName="Add";
    }
    
    public String saveProduct(){
        if(productId.equals("-1")){
            return createProduct();
        }else{
            return updateProduct();
        }
    }
    
    public void productById(int productId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/ProductManagementService/product/"+productId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            productSingle=(ProductSingle)mapper.readValue(jsonResponse, ProductSingle.class);
            
            this.productId=productSingle.getProduct().getProductId().toString();
            this.productName=productSingle.getProduct().getName();
            this.price=String.valueOf(productSingle.getProduct().getHqPrice());
            this.measureUnity=productSingle.getProduct().getMeasureUnity();
            this.productTypeId=productSingle.getProduct().getProductTypeId().toString();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String createProduct(){
        
        String url="http://localhost:8080/PayFuel/ProductManagementService/product/create";
        String  jsonData = "{\n" +
                "\"name\":\""+productName+"\",\n" +
                "\"hqPrice\":\""+price+"\",\n" +
                "\"measureUnity\":\""+measureUnity+"\",\n" +
                "\"productTypeId\":\""+productTypeId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        //System.out.println(jsonResponse);
        
        this.productName=null;
        this.price=null;
        this.measureUnity=null;
        this.productTypeId=null;
        
        return "innerpage_product.xhtml";
    }
    
    public String updateProduct(){
        
        String url="http://localhost:8080/PayFuel/ProductManagementService/product/edit";
        String  jsonData = "{\n" +
                "\"productId\":\""+productId+"\",\n" +
                "\"name\":\""+productName+"\",\n" +
                "\"hqPrice\":\""+price+"\",\n" +
                "\"measureUnity\":\""+measureUnity+"\",\n" +
                "\"productTypeId\":\""+productTypeId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        //System.out.println(jsonResponse);
        
        productById(Integer.parseInt(productId));
        
        return products();
    }
    

    
    
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }
    
    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * @return the productList
     */
    public ProductList getProductList() {
        return productList;
    }
    
    /**
     * @param productList the productList to set
     */
    public void setProductList(ProductList productList) {
        this.productList = productList;
    }
    
    
    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }
    
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    
    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }
    
    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }
    
    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
    /**
     * @return the productSingle
     */
    public ProductSingle getProductSingle() {
        return productSingle;
    }
    
    /**
     * @param productSingle the productSingle to set
     */
    public void setProductSingle(ProductSingle productSingle) {
        this.productSingle = productSingle;
    }
    
    /**
     * @return the templateBean
     */
    public TemplateBean getTemplateBean() {
        return templateBean;
    }
    
    /**
     * @param templateBean the templateBean to set
     */
    public void setTemplateBean(TemplateBean templateBean) {
        this.templateBean = templateBean;
    }
    
    
    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }
    
    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    /**
     * @return the productMenuItemRendered
     */
    public Boolean getProductMenuItemRendered() {
        return productMenuItemRendered;
    }
    
    /**
     * @param productMenuItemRendered the productMenuItemRendered to set
     */
    public void setProductMenuItemRendered(Boolean productMenuItemRendered) {
        this.productMenuItemRendered = productMenuItemRendered;
    }
    
    
    
    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
    }
    
    /**
     * @param productTypeId the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
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
     * @return the saveActionName
     */
    public String getSaveActionName() {
        return saveActionName;
    }
    
    /**
     * @param saveActionName the saveActionName to set
     */
    public void setSaveActionName(String saveActionName) {
        this.saveActionName = saveActionName;
    }
    
   
    
}
