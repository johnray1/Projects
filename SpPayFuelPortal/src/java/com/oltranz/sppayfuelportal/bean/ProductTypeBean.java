/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.sppayfuelportal.bean;

import com.oltranz.sppayfuelportal.library.Common;
import com.oltranz.sppayfuelportal.library.CommonLibrary;
import com.oltranz.sppayfuelportal.model.ProductTypeList;
import com.oltranz.sppayfuelportal.model.ProductTypeSingle;
import java.io.IOException;
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
@ManagedBean(name="ProductTypeBean")
@SessionScoped
public class ProductTypeBean {
    
    private String productTypeId;
    private String productTypeName;
    private String descr;
    
    private String saveActionName="Save";
    private String popUpLabel;
    
    
    private ProductTypeSingle productTypeSingle;
    private ProductTypeList productTypeList;
    
    @ManagedProperty(value="#{TemplateBean}")
    private TemplateBean templateBean;
    
    
    
    private Boolean productTypeMenuItemRendered;
    
    
    
    
    public ProductTypeBean() {
        
        HttpSession session = SessionBean.getSession();
        String permissions=(String)session.getAttribute("permissions");
        
        byte[] permissionBytes =Common.shared.hexStringToByteArray(permissions);
        
        String s=Common.shared.byteArrayToString(permissionBytes);
        
        int bitValue=Common.shared.GetBit(permissionBytes, 1);//1 means 2nd Position bit in permisson
        
        
        productTypeMenuItemRendered=bitValue==1;
        
    }
    
    public void init() {
        setSaveActionName("Save");
    }
    
    
    public String productTypes(){
        
        templateBean.setDashboardClassName("deactive");
        templateBean.setBranchClassName("deactive");
        templateBean.setProductClassName("active");
        templateBean.setGoalClassName("deactive");
        templateBean.setTransactionClassName("deactive");
        templateBean.setSettingClassName("deactive");
        
        try{
            String getBranchUrl="http://localhost:8080/PayFuel/ProductManagementService/productTypes";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            productTypeList=(ProductTypeList)mapper.readValue(jsonResponse, ProductTypeList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "innerpage_product_type.xhtml?faces-redirect=true";
        
    }
    
    
    
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
    
    public String saveProductType(){
        if(productTypeId.equals("-1")){
            return createProductType();
        }else{
            return updateProductType();
        }
    }
    
    public void productTypeById(int productTypeId){
        
        try{
            String getUrl="http://localhost:8080/PayFuel/ProductManagementService/productTypes/"+productTypeId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
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
    
    public String createProductType(){
        
        try{
            String url="http://localhost:8080/PayFuel/ProductManagementService/productType/create";
            String  jsonData = "{\n" +
                    "\"name\":\""+productTypeName+"\",\n" +
                    "\"descr\":\""+descr+"\"\n" +
                    "}";
            Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
            String jsonResponse=response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            productTypeSingle=(ProductTypeSingle)mapper.readValue(jsonResponse, ProductTypeSingle.class);
            
            productTypeById(productTypeSingle.getProductType().getProductTypeId());
            
        }
        catch(IOException | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
        
        return productTypes();
    }
    
    public String updateProductType(){
        
        String url="http://localhost:8080/PayFuel/ProductManagementService/productType/edit";
        String  jsonData = "{\n" +
                "\"productTypeId\":\""+productTypeId+"\",\n" +
                "\"name\":\""+productTypeName+"\",\n" +
                "\"descr\":\""+descr+"\"\n" +
                "}";
        Response response=CommonLibrary.sendRESTRequest(url, jsonData, MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        productTypeById(Integer.parseInt(productTypeId));
        
        return productTypes();
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
     * @return the productTypeMenuItemRendered
     */
    public Boolean getProductTypeMenuItemRendered() {
        return productTypeMenuItemRendered;
    }
    
    /**
     * @param productTypeMenuItemRendered the productTypeMenuItemRendered to set
     */
    public void setProductTypeMenuItemRendered(Boolean productTypeMenuItemRendered) {
        this.productTypeMenuItemRendered = productTypeMenuItemRendered;
    }

    /**
     * @return the popUpLabel
     */
    public String getPopUpLabel() {
        return popUpLabel;
    }

    /**
     * @param popUpLabel the popUpLevel to set
     */
    public void setPopUpLevel(String popUpLabel) {
        this.popUpLabel = popUpLabel;
    }
    
    
    
    
    
}
