/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.payfuel.services;

import com.oltranz.payfuel.beans.ProductManager;
import com.oltranz.payfuel.entities.Product;
import com.oltranz.payfuel.entities.ProductType;
import com.oltranz.payfuel.models.BranchProductPriceModel;
import com.oltranz.payfuel.models.ResultObject;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author John
 */
@Path("ProductManagementService")
@Stateless
public class ProductManagementService {
    
    @EJB
    ProductManager productManager;
    
    @POST
    @Path("productType/create")
    @Consumes({"application/xml", "application/json"})
    public String createProductType(ProductType newProductType) {
        
        ResultObject result=productManager.createProductType(newProductType);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("productType/edit")
    @Consumes({"application/xml", "application/json"})
    public String editProductType(ProductType editProductType) {
        
        ResultObject result=productManager.editProductType(editProductType);
        return result.getJsonFormat();
        
    }
    
    
    @POST
    @Path("product/create")
    @Consumes({"application/xml", "application/json"})
    public String createProduct(Product newProduct) {
        
        ResultObject result=productManager.createProduct(newProduct);
        return result.getJsonFormat();
        
    }
    
    @POST
    @Path("product/edit")
    @Consumes({"application/xml", "application/json"})
    public String editProduct(Product editProduct) {
       
        ResultObject result=productManager.editProduct(editProduct);
        return result.getJsonFormat();
        
    }
    

    @POST
    @Path("product/addProductPriceToBranch")
    @Produces({"application/xml", "application/json"})
    public String addProductPriceToBranch(BranchProductPriceModel branchProductPriceModel) {
        
        ResultObject result= productManager.addProductPriceToBranch(branchProductPriceModel);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @POST
    @Path("product/editProductPriceToBranch")
    @Produces({"application/xml", "application/json"})
    public String editProductPriceToBranch(BranchProductPriceModel branchProductPriceModel) {
        
        ResultObject result= productManager.editProductPriceToBranch(branchProductPriceModel);
        String jsonResult=result.getJsonFormat();
        
        return jsonResult;
    }
    
    @GET
    @Path("products")
    @Produces({"application/xml", "application/json"})
    public String getProductList() {
        
        ResultObject result= productManager.getProductList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("productTypes")
    @Produces({"application/xml", "application/json"})
    public String getProductTypeList() {
        
        ResultObject result= productManager.getProductTypeList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("productTypes/{id}")
    @Produces({"application/xml", "application/json"})
    public String getproductTypesByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= productManager.getProductTypeByItsId(id);
        return result.getJsonFormat();
    }
    
    
    @GET
    @Path("branch/products")
    @Produces({"application/xml", "application/json"})
    public String getBranchProductList() {
        
        ResultObject result= productManager.getBranchProductList();
        return result.getJsonFormat();
    }
    
    @GET
    @Path("branch/products/{userId}")
    @Produces({"application/xml", "application/json"})
    public String getBranchProductList(@PathParam("userId") Integer userId) {
        
        ResultObject result= productManager.getBranchProductList(userId);
        return result.getJsonFormat();
    }
    
    @GET
    @Path("product/{id}")
    @Produces({"application/xml", "application/json"})
    public String getProductByItsId(@PathParam("id") Integer id) {
        
        ResultObject result= productManager.getProductByItsId(id);
        return result.getJsonFormat();
    }
    
    
    @POST
    @Path("product/delete/{id}")
    @Produces({"application/xml", "application/json"})
    public String removeProduct(@PathParam("id") Integer id) {
        ResultObject result= productManager.removeProduct(id);
        return result.getJsonFormat();
    }
    

}
