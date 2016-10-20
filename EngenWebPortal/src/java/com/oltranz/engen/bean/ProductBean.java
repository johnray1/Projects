/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.BranchList;
import com.oltranz.engen.model.BranchProductList;
import com.oltranz.engen.model.BranchProductSingle;
import com.oltranz.engen.model.ProductList;
import com.oltranz.engen.model.ProductSingle;
import com.oltranz.engen.model.ProductTypeList;
import com.oltranz.engen.model.ProductTypeSingle;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    
    private String productTypeId,productTypeName,descr;
    private String productId,productName,price,measureUnity;
    private String branchId;
    
    private String saveActionName="Save",popUpLabel;
    
    private Boolean actionRendered;
    
    private ProductTypeSingle productTypeSingle;private ProductTypeList productTypeList;
    
    private ProductSingle productSingle;private ProductList productList;
    
    private BranchProductList branchProductList;private BranchProductSingle branchProductSingle;
    
    private BranchList branchList;
    
    private HttpSession session = SessionBean.getSession();
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    public void products(){
        templateBean.setDashboardClassName("omenu");
        templateBean.setBranchClassName("omenu");
        templateBean.setProductsClassName("omenu_active");
        templateBean.setGoalClassName("omenu");
        templateBean.setTransactionClassName("omenu");
        templateBean.setSettingClassName("omenu");
        
        try{
            productTypeList();
            productList();
            branchProductList();
            branchList();
            
            if(((int) session.getAttribute("branchId"))==0){
                actionRendered=true;
            }
            else{
                actionRendered=false;
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect("innerpage_product.xhtml");
        }
        catch(Exception ex){
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void productTypeList() throws IOException{
        
        String getBranchUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/productTypes";
        Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper=new ObjectMapper();
        productTypeList=(ProductTypeList)mapper.readValue(jsonResponse, ProductTypeList.class);
    }
    
    public void productList() throws IOException{
        
        String getBranchUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/products";
        Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper1=new ObjectMapper();
        productList=(ProductList)mapper1.readValue(jsonResponse, ProductList.class);
    }
    
    public void branchProductList() throws IOException{
        
        int braId=(int) getSession().getAttribute("branchId");
        String getUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/branches/products/"+braId;
        Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper2=new ObjectMapper();
        branchProductList=(BranchProductList)mapper2.readValue(jsonResponse, BranchProductList.class);
    }
    
    public void branchList() throws IOException{
        
        int braId=(int) session.getAttribute("branchId");
        String getUrl="http://localhost:8080/EngenPayFuel/BranchManagementService/branches/"+braId;
        Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
        String jsonResponse = response.readEntity(String.class);
        ObjectMapper mapper3=new ObjectMapper();
        branchList=(BranchList)mapper3.readValue(jsonResponse, BranchList.class);
    }
    
    
    
    //------------------------------------------------------------------------------------------------------------------------------
    
    public void productTypeForView(int productTypeId){
        productTypeById(productTypeId);
    }
    
    public void productTypeForEdit(int productTypeId){
        productTypeById(productTypeId);
        saveActionName="EDIT";
        popUpLabel="EDIT TYPE";
    }
    
    public void productTypeForCreate(){
        this.productTypeId="-1";
        this.productTypeName="";
        this.descr="";
        saveActionName="ADD";
        popUpLabel="ADD TYPE";
    }
    
    public void saveProductType(){
        try{
            if(productTypeId.equals("-1")){
                createProductType();
            }else{
                updateProductType();
            }
        }
        catch(Exception ex){
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void productTypeById(int productTypeId){
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/productTypes/"+productTypeId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            productTypeSingle=(ProductTypeSingle)mapper.readValue(jsonResponse, ProductTypeSingle.class);
            
            this.productTypeId=productTypeSingle.getProductType().getProductTypeId().toString();
            this.productTypeName=productTypeSingle.getProductType().getName();
            this.descr=productTypeSingle.getProductType().getDescr();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void createProductType() throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/ProductManagementService/productType/create";
        String  jsonData = "{\n" +
                "\"name\":\""+productTypeName+"\",\n" +
                "\"descr\":\""+descr+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        
        productTypeList();
    }
    
    public void updateProductType() throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/ProductManagementService/productType/edit";
        String  jsonData = "{\n" +
                "\"productTypeId\":\""+productTypeId+"\",\n" +
                "\"name\":\""+productTypeName+"\",\n" +
                "\"descr\":\""+descr+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        productTypeList();
    }
    
    
    
    //------------------------------------------------------------------------------------------------------------------------------
    
    
    public void productForView(int productId){
        productById(productId);
    }
    
    
    public void productForEdit(int productId){
        productById(productId);
        saveActionName="EDIT";
        popUpLabel="EDIT PRODUCT";
    }
    
    public void productForCreate(){
        this.productId="-1";
        this.productName="";
        this.price="";
        this.measureUnity="";
        this.productTypeId="";
        
        saveActionName="ADD";
        popUpLabel="ADD PRODUCT";
    }
    
    public void saveProduct(){
        try{
            if(productId.equals("-1")){
                createProduct();
            }else{
                updateProduct();
            }
        }
        catch(Exception ex){
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void productById(int productId){
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/product/"+productId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
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
    
    public void createProduct() throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/ProductManagementService/product/create";
        String  jsonData = "{\n" +
                "\"name\":\""+productName+"\",\n" +
                "\"hqPrice\":\""+price+"\",\n" +
                "\"measureUnity\":\""+measureUnity+"\",\n" +
                "\"productTypeId\":\""+productTypeId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        productList();
    }
    
    public void updateProduct() throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/ProductManagementService/product/edit";
        String  jsonData = "{\n" +
                "\"productId\":\""+productId+"\",\n" +
                "\"name\":\""+productName+"\",\n" +
                "\"hqPrice\":\""+price+"\",\n" +
                "\"measureUnity\":\""+measureUnity+"\",\n" +
                "\"productTypeId\":\""+productTypeId+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        productList();
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------
    
    public void branchProductForView(Integer branchId,Integer productId){
        branchProductsById(branchId,productId);
    }
    
    public void branchProductForEdit(Integer branchId,Integer productId){
        branchProductsById(branchId,productId);
        saveActionName="Edit";
    }
    
    public void saveBranchProduct(){
        try{
            if(productId.equals("-1")){
                createBranchProduct();
            }else{
                updateBranchProduct();
            }
        }
        catch(Exception ex){
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void branchProductsById(Integer branchId,Integer productId){
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/branch/products/"+branchId+"/"+productId;
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
    
    
    public void createBranchProduct() throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/ProductManagementService/product/addProductPriceToBranch";
        String  jsonData ="{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"productId\":\""+productId+"\",\n" +
                "\"price\":\""+price+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        branchProductList();
    }
    
    public void updateBranchProduct() throws IOException{
        
        String url="http://localhost:8080/EngenPayFuel/ProductManagementService/product/editProductPriceToBranch";
        String  jsonData ="{\n" +
                "\"branchId\":\""+branchId+"\",\n" +
                "\"productId\":\""+productId+"\",\n" +
                "\"price\":\""+price+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        
        branchProductList();
    }
    
    
    
    
    /**
     * @return the productTypeSingle
     */
    public ProductTypeSingle getProductTypeSingle() {
        return productTypeSingle;
    }
    
    /**
     * @param productTypeSingle the productTypeSingle to set
     */
    public void setProductTypeSingle(ProductTypeSingle productTypeSingle) {
        this.productTypeSingle = productTypeSingle;
    }
    
    /**
     * @return the productTypeList
     */
    public ProductTypeList getProductTypeList() {
        return productTypeList;
    }
    
    /**
     * @param productTypeList the productTypeList to set
     */
    public void setProductTypeList(ProductTypeList productTypeList) {
        this.productTypeList = productTypeList;
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
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }
    
    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
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
     * @return the session
     */
    public HttpSession getSession() {
        return session;
    }
    
    /**
     * @param session the session to set
     */
    public void setSession(HttpSession session) {
        this.session = session;
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
     * @return the popUpLabel
     */
    public String getPopUpLabel() {
        return popUpLabel;
    }
    
    /**
     * @param popUpLabel the popUpLabel to set
     */
    public void setPopUpLabel(String popUpLabel) {
        this.popUpLabel = popUpLabel;
    }
    
    /**
     * @return the branchList
     */
    public BranchList getBranchList() {
        return branchList;
    }
    
    /**
     * @param branchList the branchList to set
     */
    public void setBranchList(BranchList branchList) {
        this.branchList = branchList;
    }
    
    /**
     * @return the actionRendered
     */
    public Boolean getActionRendered() {
        return actionRendered;
    }
    
    /**
     * @param actionRendered the actionRendered to set
     */
    public void setActionRendered(Boolean actionRendered) {
        this.actionRendered = actionRendered;
    }
    
    
    
    
    
    
    
    
}
