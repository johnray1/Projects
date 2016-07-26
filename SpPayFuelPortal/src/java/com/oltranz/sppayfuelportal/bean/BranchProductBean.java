/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.BranchProductList;
import com.oltranz.sppayfuelportal.model.BranchProductSingle;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="BranchProductBean")
@SessionScoped
public class BranchProductBean implements Serializable{
    
    private String branchId;
    private String productId;
    private String price;
    private String saveActionName="Save";
    private BranchProductList branchProductList;
    private BranchProductSingle branchProductSingle;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;

    
    public void init() {
        setSaveActionName("Save");
    }
    
    public String branchProducts(){
        
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setDevicesClassName("omenu");
        templateBean.setProductsClassName("omenu_active");
        templateBean.setUsersClassName("omenu");
        templateBean.setRolesClassName("omenu");
        templateBean.setTransactionsClassName("omenu");
        templateBean.setLogsClassName("omenu");
        
        int userId=loginBean.getUserId();
        System.out.println(loginBean.getUserId());
        try{
            String getUrl="http://localhost:8080/PayFuel/ProductManagementService/branch/products/"+userId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            branchProductList=(BranchProductList)mapper.readValue(jsonResponse, BranchProductList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return "innerpage_branch_product.xhtml?faces-redirect=true";
    }
    
    
    public void branchProductForView(Integer branchId,Integer productId){
        branchProductsById(branchId,productId);
    }
    
    
    public void branchProductForEdit(Integer branchId,Integer productId){
        branchProductsById(branchId,productId);
        saveActionName="Add";
    }
    
    public String saveBranchProduct(){
        if(productId.equals("-1")){
            return create();
        }else{
            return update();
        }
    }
    
    public void branchProductsById(Integer branchId,Integer productId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/ProductManagementService/branch/products/"+branchId+"/"+productId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            branchProductSingle=(BranchProductSingle)mapper.readValue(jsonResponse, BranchProductSingle.class);
            
            this.branchId=branchProductSingle.getBranchProduct().getBranchId().toString();
            this.productId=branchProductSingle.getBranchProduct().getProductId().toString();
            this.price=String.valueOf(branchProductSingle.getBranchProduct().getUnitPrice());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String create(){
        
        String url="http://localhost:8080/PayFuel/ProductManagementService/product/addProductPriceToBranch";
        String  jsonData ="{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"productId\":\""+productId+"\",\n" +
                "\"price\":\""+price+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        //System.out.println(jsonResponse);
        
        
        
        return "innerpage_branch_product.xhtml?faces-redirect=true";
    }
    
    public String update(){
        
        String url="http://localhost:8080/PayFuel/ProductManagementService/product/editProductPriceToBranch";
        String  jsonData ="{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"productId\":\""+productId+"\",\n" +
                "\"price\":\""+price+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        branchProductsById(Integer.parseInt(branchId),Integer.parseInt(productId));
        
        return branchProducts();
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

    /**
     * @return the branchProductList
     */
    public BranchProductList getBranchProductList() {
        return branchProductList;
    }

    /**
     * @param branchProductList the branchProductList to set
     */
    public void setBranchProductList(BranchProductList branchProductList) {
        this.branchProductList = branchProductList;
    }

    /**
     * @return the branchProductSingle
     */
    public BranchProductSingle getBranchProductSingle() {
        return branchProductSingle;
    }

    /**
     * @param branchProductSingle the branchProductSingle to set
     */
    public void setBranchProductSingle(BranchProductSingle branchProductSingle) {
        this.branchProductSingle = branchProductSingle;
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
    
}
