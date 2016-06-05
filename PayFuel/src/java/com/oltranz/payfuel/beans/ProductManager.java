/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.BranchProductPrice;
import com.oltranz.payfuel.entities.BranchProductPricePK;
import com.oltranz.payfuel.entities.Product;
import com.oltranz.payfuel.entities.ProductType;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleForBranch;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.models.BranchProduct;
import com.oltranz.payfuel.models.BranchProductPriceModel;
import com.oltranz.payfuel.models.ProductTypes;
import com.oltranz.payfuel.models.Products;
import com.oltranz.payfuel.models.ResultObject;
import com.oltranz.payfuel.models.UserDetailsModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class ProductManager {
    
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
            CommonFunctionEjb commonFunctionEjb;
    
    @EJB
            UserManager userManager;
    
    
    public ResultObject createProductType(ProductType newProductType) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(ProductType.class);
        try{
            ProductType productType=new ProductType();
            productType.setName(newProductType.getName());
            productType.setDescr(newProductType.getDescr());
            em.persist(productType);
            em.flush();
            //set the returntype resultset object
            
            resultObject.setMessage("New ProductType successfully created ");
            resultObject.setObject(productType);
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
    }
    
    public ResultObject editProductType(ProductType editProductType) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(ProductType.class);
        try{
            
            ProductType productType=em.find(ProductType.class, editProductType.getProductTypeId());
            if(productType==null){
                resultObject.setObject(null);
                resultObject.setMessage("ProductType is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            productType.setName(editProductType.getName());
            productType.setDescr(editProductType.getDescr());
            em.merge(productType);
            em.flush();
            //set the returntype resultset object
            
            resultObject.setMessage("New ProductType successfully Updated ");
            resultObject.setObject(productType);
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
    }
    
    
    public ResultObject getProductTypeList(){
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(ProductType.class);
        
        List<ProductTypes> productTypesList=new ArrayList<>();
        ProductType productType;
        List<ProductType> productTypeList=(List<ProductType>)em.createQuery("SELECT p FROM ProductType p").getResultList();
        if(productTypeList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no ProductType in the system");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=productTypeList.iterator();
            while(i.hasNext()){
                productType=(ProductType) i.next();
                
                ProductTypes productTypes=new ProductTypes();
                productTypes.setProductTypeId(productType.getProductTypeId());
                productTypes.setName(productType.getName());
                productTypes.setDescr(productType.getDescr());
                productTypes.setStatus(productType.getStatus());
                if(productType.getStatus()==7){
                    productTypes.setStatusName("ACTIVE");
                }
                else{
                    productTypes.setStatusName("DEACTIVE");
                }
                productTypesList.add(productTypes);
            }
            resultObject.setObject(productTypesList);
            resultObject.setMessage(productTypesList.size()+" ProductType were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    
    public ResultObject getProductTypeByItsId(Integer productTypeId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(ProductType.class);
        
        ProductType productType=em.find(ProductType.class,productTypeId);
        
        if(productType!=null){
            
            ProductTypes productTypes=new ProductTypes();
            productTypes.setProductTypeId(productType.getProductTypeId());
            productTypes.setName(productType.getName());
            productTypes.setDescr(productType.getDescr());
            productTypes.setStatus(productType.getStatus());
            if(productType.getStatus()==7){
                productTypes.setStatusName("ACTIVE");
            }
            else{
                productTypes.setStatusName("DEACTIVE");
            }
            
            resultObject.setObject(productTypes);
            resultObject.setMessage("ProductType Well found and returned!");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setObject(null);
            resultObject.setMessage("ProductType with given Id not found!");
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
//------------------------------------------------------------------------------------------------------------------------------------
    
    public ResultObject createProduct(Product newProduct) {
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Product.class);
        try{
            
            ProductType productType=em.find(ProductType.class, newProduct.getProductTypeId());
            if(productType==null){
                resultObject.setObject(null);
                resultObject.setMessage("ProductType is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            Product product=new Product();
            product.setName(newProduct.getName());
            product.setHqPrice(newProduct.getHqPrice());
            product.setMeasureUnity(newProduct.getMeasureUnity());
            product.setProductTypeId(newProduct.getProductTypeId());
            em.persist(product);
            em.flush();
            
            commonFunctionEjb.addDefaultProductPriceToProductManager(product);
            
            //set the returntype resultset object
            resultObject.setMessage("New Product successfully created ");
            resultObject.setObject(product);
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
    }
    
    
    
    public ResultObject editProduct(Product editProduct){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Product.class);
        
        try{
            Product product=em.find(Product.class, editProduct.getProductId());
            if(product==null){
                resultObject.setMessage("No Product with id of the given one is found!");
                resultObject.setObject(null);
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            ProductType productType=em.find(ProductType.class, editProduct.getProductTypeId());
            if(productType==null){
                resultObject.setObject(null);
                resultObject.setMessage("ProductType is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            product.setName(editProduct.getName());
            product.setHqPrice(editProduct.getHqPrice());
            product.setMeasureUnity(editProduct.getMeasureUnity());
            product.setProductTypeId(productType.getProductTypeId());
            em.merge(product);
            em.flush();
            
            resultObject.setMessage("Product successfully updated");
            resultObject.setObject(product);
            resultObject.setStatusCode(100);
            
            return resultObject;
        }
        catch(Exception e){
            String message=e.getMessage();
            resultObject.setObject(null);
            resultObject.setMessage(message);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    
    public ResultObject getProductByItsId(Integer productId){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(Product.class);
        
        Product product=em.find(Product.class,productId);
        if(product!=null){
            
            Products products=new Products();
            products.setProductId(product.getProductId());
            products.setName(product.getName());
            products.setHqPrice(product.getHqPrice());
            products.setMeasureUnity(product.getMeasureUnity());
            products.setProductTypeId(product.getProductTypeId());
            ProductType productType=commonFunctionEjb.getProductTypeName(product.getProductTypeId());
            products.setProductTypeName(productType.getName());
            products.setStatus(product.getStatus());
            if(product.getStatus()==7){
                products.setStatusName("ACTIVE");
            }
            else{
                products.setStatusName("DEACTIVE");
            }
            resultObject.setMessage("Product Well found and returned!");
            resultObject.setObject(products);
            resultObject.setStatusCode(100);
            return resultObject;
        }
        else{
            resultObject.setMessage("Product with given Id not found!");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
    }
    
    
    public ResultObject getProductList(){
        ResultObject resultObject= new ResultObject();
        resultObject.setObjectClass(Product.class);
        
        List<Products> productsList=new ArrayList<>();
        Product product;
        List<Product> productList=(List<Product>)em.createNamedQuery("Product.findAll").getResultList();
        
        if(productList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("There are no Product in the system");
            resultObject.setStatusCode(500);
        }
        else{
            Iterator i=productList.iterator();
            while(i.hasNext()){
                product=(Product) i.next();
                
                Products products=new Products();
                products.setProductId(product.getProductId());
                products.setName(product.getName());
                products.setHqPrice(product.getHqPrice());
                products.setMeasureUnity(product.getMeasureUnity());
                products.setProductTypeId(product.getProductTypeId());
                ProductType productType=commonFunctionEjb.getProductTypeName(product.getProductTypeId());
                products.setProductTypeName(productType.getName());
                products.setStatus(product.getStatus());
                if(product.getStatus()==7){
                    products.setStatusName("ACTIVE");
                }
                else{
                    products.setStatusName("DEACTIVE");
                }
                
                productsList.add(products);
            }
            resultObject.setObject(productsList);
            resultObject.setMessage(productsList.size()+" Product were found");
            resultObject.setStatusCode(100);
        }
        
        return resultObject;
    }
    
    
    
//-----------------------------------------------------------------------------------------------------------------------------
    public ResultObject addProductPriceToBranch(BranchProductPriceModel branchProductPriceModel){
        
        Integer branchId=branchProductPriceModel.getBranchId();
        Integer productId=branchProductPriceModel.getProductId();
        double price=branchProductPriceModel.getPrice();
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BranchProductPrice.class);
        try{
            Branch branch=em.find(Branch.class, branchId);
            if(branch==null){
                resultObject.setObject(null);
                resultObject.setMessage("BranchId and Branch is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            Product product=em.find(Product.class, productId);
            if(product==null){
                resultObject.setObject(null);
                resultObject.setMessage("ProductId and Product is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            BranchProductPricePK branchProductPricePK=new BranchProductPricePK();
            branchProductPricePK.setBranchId(branchId);
            branchProductPricePK.setProductId(productId);
            
            BranchProductPrice branchProductPrice=new BranchProductPrice();
            branchProductPrice.setBranchProductPricePK(branchProductPricePK);
            branchProductPrice.setBPrice(price);
            em.persist(branchProductPrice);
            em.flush();
            
            resultObject.setObject(branchProductPrice);
            resultObject.setMessage("Product Price is Set For This Branch");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
    }
    
    
    public ResultObject editProductPriceToBranch(BranchProductPriceModel branchProductPriceModel){
        
        Integer branchId=branchProductPriceModel.getBranchId();
        Integer productId=branchProductPriceModel.getProductId();
        double price=branchProductPriceModel.getPrice();
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BranchProductPrice.class);
        try{
            
            Branch branch=em.find(Branch.class, branchId);
            if(branch==null){
                resultObject.setObject(null);
                resultObject.setMessage("BranchId and Branch is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            Product product=em.find(Product.class, productId);
            if(product==null){
                resultObject.setObject(null);
                resultObject.setMessage("ProductId and Product is not available");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            
            BranchProductPricePK branchProductPricePK=new BranchProductPricePK();
            branchProductPricePK.setBranchId(branchId);
            branchProductPricePK.setProductId(productId);
            
            BranchProductPrice branchProductPrice=em.find(BranchProductPrice.class, branchProductPricePK);
            if(branchProductPrice==null){
                resultObject.setObject(null);
                resultObject.setMessage("Branch Product Price is not Assigned");
                resultObject.setStatusCode(500);
                return resultObject;
            }
            branchProductPrice.setBPrice(price);
            em.merge(branchProductPrice);
            em.flush();
            
            resultObject.setObject(branchProductPrice);
            resultObject.setMessage("Product Price is Set For This Branch");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setObject(null);
            resultObject.setMessage(e.getMessage());
            return resultObject;
        }
    }
    
    
    
    
    public ResultObject removeProduct(Integer productId){
        
        ResultObject resultObject=new ResultObject();
        Product product2Delete=em.find(Product.class, productId);
        
        if(product2Delete==null){
            resultObject.setMessage("Product with given Id not found!");
            resultObject.setObject("FAILURE");
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        Product product=em.find(Product.class, product2Delete.getProductId());
        product.setStatus(product.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("--yyyy-MM-dd/HH:mm");
        Date deletionTIme=new Date();
        product.setName(product.getName()+ sdf.format(deletionTIme));
        em.merge(product);
        
        
        resultObject.setMessage("Product successfully sent to dustbin");
        resultObject.setObject(product);
        resultObject.setObjectClass(Product.class);
        return resultObject;
    }
    
    public ResultObject getBranchProductList(){
        
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BranchProduct.class);
        
        List<BranchProduct> branchProductModelList=new ArrayList<>();
        
        BranchProductPrice branchProductPrice;
        List<BranchProductPrice> branchProductPriceList=(List<BranchProductPrice>)em.createQuery("SELECT b FROM BranchProductPrice b").getResultList();
        if(branchProductPriceList.isEmpty()){
            resultObject.setObject(null);
            resultObject.setMessage("No BranchProduct Found");
            resultObject.setStatusCode(500);
            
            return resultObject;
        }
        Iterator i=branchProductPriceList.iterator();
        while (i.hasNext()){
            branchProductPrice=(BranchProductPrice) i.next();
            
            BranchProduct branchProductModel=new BranchProduct();
            branchProductModel.setBranchId(branchProductPrice.getBranchProductPricePK().getBranchId());
            Branch branch=commonFunctionEjb.getBranchName(branchProductPrice.getBranchProductPricePK().getBranchId());
            branchProductModel.setBranchName(branch.getName());
            branchProductModel.setProductId(branchProductPrice.getBranchProductPricePK().getProductId());
            Product product=commonFunctionEjb.getProductName(branchProductPrice.getBranchProductPricePK().getProductId());
            branchProductModel.setProductName(product.getName());
            branchProductModel.setUnitPrice(branchProductPrice.getBPrice());
            
            branchProductModelList.add(branchProductModel);
        }
        resultObject.setObject(branchProductModelList);
        resultObject.setMessage(branchProductModelList.size()+" "+"BranchProduct Found");
        resultObject.setStatusCode(100);
        
        return resultObject;
    }
    
    public ResultObject getBranchProductList(Integer userId){
        ResultObject resultObject=new ResultObject();
        resultObject.setObjectClass(BranchProduct.class);
        
        //check if user is available
        User user=em.find(User.class,userId);
        if(user==null){
            resultObject.setMessage("User is not Created To Access The PRODUCT");
            resultObject.setObject(null);
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        List<BranchProduct> branchProductModelList=new ArrayList<>();
        BranchProductPrice branchProductPrice;
        List<BranchProductPrice> branchProductPriceList=(List<BranchProductPrice>)em.createQuery("SELECT b FROM BranchProductPrice b").getResultList();
        
        //if user id 1 bring all branch
        if(user.getUserId()==1){
            Iterator i=branchProductPriceList.iterator();
            while (i.hasNext()){
                branchProductPrice=(BranchProductPrice) i.next();
                
                BranchProduct branchProductModel=new BranchProduct();
                branchProductModel.setBranchId(branchProductPrice.getBranchProductPricePK().getBranchId());
                Branch branch=commonFunctionEjb.getBranchName(branchProductPrice.getBranchProductPricePK().getBranchId());
                branchProductModel.setBranchName(branch.getName());
                branchProductModel.setProductId(branchProductPrice.getBranchProductPricePK().getProductId());
                Product product=commonFunctionEjb.getProductName(branchProductPrice.getBranchProductPricePK().getProductId());
                branchProductModel.setProductName(product.getName());
                branchProductModel.setUnitPrice(branchProductPrice.getBPrice());
                
                branchProductModelList.add(branchProductModel);
            }
            resultObject.setObject(branchProductModelList);
            resultObject.setMessage(branchProductModelList.size()+" "+"BranchProduct Found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        //get the user details,roles and its branch
        UserDetailsModel userDetails= (UserDetailsModel) userManager.getUserDetails(user.getUserId()).getObject();
        List<Role> roles=userDetails.getRoles();
        Integer branchId=-1;
        for(Role r: roles){
            
            if(r.getTypeId()==1){
                Iterator i=branchProductPriceList.iterator();
                while (i.hasNext()){
                    branchProductPrice=(BranchProductPrice) i.next();
                    
                    BranchProduct branchProductModel=new BranchProduct();
                    branchProductModel.setBranchId(branchProductPrice.getBranchProductPricePK().getBranchId());
                    Branch branch=commonFunctionEjb.getBranchName(branchProductPrice.getBranchProductPricePK().getBranchId());
                    branchProductModel.setBranchName(branch.getName());
                    branchProductModel.setProductId(branchProductPrice.getBranchProductPricePK().getProductId());
                    Product product=commonFunctionEjb.getProductName(branchProductPrice.getBranchProductPricePK().getProductId());
                    branchProductModel.setProductName(product.getName());
                    branchProductModel.setUnitPrice(branchProductPrice.getBPrice());
                    
                    branchProductModelList.add(branchProductModel);
                }
                resultObject.setObject(branchProductModelList);
                resultObject.setMessage(branchProductModelList.size()+" "+"BranchProduct Found");
                resultObject.setStatusCode(100);
                return resultObject;
            }
            
            if(r.getTypeId()==2){
                
                List<RoleForBranch> list = (List<RoleForBranch>)em.createQuery("SELECT r FROM RoleForBranch r WHERE r.roleForBranchPK.roleId = :roleId").setParameter("roleId", r.getRoleId()).getResultList();
                if(list.size()>0)
                    
                    branchId=list.get(0).getRoleForBranchPK().getBranchId();
            }
        }
        
        if(branchId==-1){
            resultObject.setObject(null);
            resultObject.setMessage("There is no branch product");
            resultObject.setStatusCode(500);
            return resultObject;
        }
        
        //get the branch from userbranchid
        branchProductPriceList=(List<BranchProductPrice>)em.createQuery("SELECT b FROM BranchProductPrice b WHERE b.branchProductPricePK.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        if(branchProductPriceList.size()>0){
            Iterator i=branchProductPriceList.iterator();
            while (i.hasNext()){
                branchProductPrice=(BranchProductPrice) i.next();
                
                BranchProduct branchProductModel=new BranchProduct();
                branchProductModel.setBranchId(branchProductPrice.getBranchProductPricePK().getBranchId());
                Branch branch=commonFunctionEjb.getBranchName(branchProductPrice.getBranchProductPricePK().getBranchId());
                branchProductModel.setBranchName(branch.getName());
                branchProductModel.setProductId(branchProductPrice.getBranchProductPricePK().getProductId());
                Product product=commonFunctionEjb.getProductName(branchProductPrice.getBranchProductPricePK().getProductId());
                branchProductModel.setProductName(product.getName());
                branchProductModel.setUnitPrice(branchProductPrice.getBPrice());
                
                branchProductModelList.add(branchProductModel);
            }
            resultObject.setObject(branchProductModelList);
            resultObject.setMessage(branchProductModelList.size()+" "+"BranchProduct Found");
            resultObject.setStatusCode(100);
            return resultObject;
        }
        
        
        resultObject.setObject(null);
        resultObject.setMessage("There are no BranchProduct in the system");
        resultObject.setStatusCode(500);
        return resultObject;
        
    }
    
    
    
}
