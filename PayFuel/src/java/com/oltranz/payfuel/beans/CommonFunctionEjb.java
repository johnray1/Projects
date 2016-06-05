/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.beans;

import com.oltranz.payfuel.entities.Branch;
import com.oltranz.payfuel.entities.BranchProductPrice;
import com.oltranz.payfuel.entities.BranchProductPricePK;
import com.oltranz.payfuel.entities.Customer;
import com.oltranz.payfuel.entities.Device;
import com.oltranz.payfuel.entities.PaymentMode;
import com.oltranz.payfuel.entities.Product;
import com.oltranz.payfuel.entities.ProductType;
import com.oltranz.payfuel.entities.Pump;
import com.oltranz.payfuel.entities.Role;
import com.oltranz.payfuel.entities.RoleType;
import com.oltranz.payfuel.entities.Transaction;
import com.oltranz.payfuel.entities.User;
import com.oltranz.payfuel.entities.Voucher;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import nl.bitwalker.useragentutils.*;

/**
 *
 * @author John
 */
@Stateless
public class CommonFunctionEjb {
    
    @PersistenceContext
    private  EntityManager em;
    
    private HttpServletRequest request=null;
    
    public Branch getBranchName(int branchId){
        Branch branch=em.find(Branch.class, branchId);
        if(branch==null){
            return null;
        }
        return branch;
    }
    
    public Pump getPumpName(int pumpId){
        
        Pump pump=em.find(Pump.class, pumpId);
        if(pump==null){
            return null;
        }
        return pump;
    }
    
    public Device getDeviceName(int deviceId){
        Device device=em.find(Device.class, deviceId);
        if(device==null){
            return null;
        }
        return device;
    }
    
    public PaymentMode getPaymentModeName(int paymentModeId){
        PaymentMode paymentMode=em.find(PaymentMode.class, paymentModeId);
        if(paymentMode==null){
            return null;
        }
        return paymentMode;
    }
    
    public Customer getCustomer(long customerId){
        Customer customer=em.find(Customer.class, customerId);
        if(customer==null){
            return null;
        }
        return customer;
    }
    
    public ProductType getProductTypeName(int productTypeId){
        ProductType productType=em.find(ProductType.class, productTypeId);
        if(productType==null){
            return null;
        }
        return productType;
    }
    
    public Product getProductName(int productId){
        Product product=em.find(Product.class, productId);
        if(product==null){
            return null;
        }
        return product;
    }
    
    
    public User getUserName(int userId){
        User user=em.find(User.class, userId);
        if(user==null){
            return null;
        }
        return user;
    }
    
    
    public Role getRoleName(int roleId){
        Role role=(Role) em.createQuery("SELECT r FROM Role r WHERE r.roleId = :roleId").setParameter("roleId", roleId).getSingleResult();
        if(role==null){
            return null;
        }
        return role;
    }
    
    public RoleType getRoleTypeName(int roleTypeId){
        RoleType roleType=em.find(RoleType.class, roleTypeId);
        if(roleType==null){
            return null;
        }
        return roleType;
    }
    
    
    
    public int isBranchAvailable(int deviceBranchId,int userBranchId){
        
        Branch branch =new Branch();
        List<Branch> branchList=(List<Branch>) em.createQuery("SELECT b FROM Branch b WHERE b.branchId = :branchId").setParameter("branchId", deviceBranchId).setParameter("branchId", userBranchId).getResultList();
        if(!branchList.isEmpty()){
            Iterator i=branchList.iterator();
            while(i.hasNext()){
                branch=(Branch) i.next();
            }
            if((branch.getBranchId().equals(deviceBranchId))&&(branch.getBranchId().equals(userBranchId)) && ((branch.getBranchId()!= null))||(branch.getBranchId()!=0)){
                return branch.getBranchId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    
    
    public int isUserIdAvailable(String pin){
        
        User u=new User();
        List<User> ul=(List<User>)em.createQuery("SELECT u FROM User u WHERE u.pin = :pin").setParameter("pin", pin).getResultList();
        if(!ul.isEmpty())
        {
            Iterator i=ul.iterator();
            while(i.hasNext()){
                u=(User) i.next();
            }
            if((u.getPin().equals(pin))&& ((u.getPin()!= null))||(!u.getPin().isEmpty())){
                return u.getUserId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public Device getDeviceNoId(String deviceNo){
        
        Device device=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.deviceNo = :deviceNo").setParameter("deviceNo", deviceNo).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                device=(Device) i.next();
            }
            if((device.getDeviceNo().equals(deviceNo))&& ((device.getDeviceNo()!= null))||(!device.getDeviceNo().isEmpty())){
                return device;
            }
            else{
                return null;
            }
        }
        return null;
    }
    
    public Transaction getDeviceTransactionId(long deviceTransactionId){
        
        Transaction transaction=new Transaction();
        List<Transaction> tl=(List<Transaction>)em.createQuery("SELECT t FROM Transaction t WHERE t.deviceTransactionId = :deviceTransactionId").setParameter("deviceTransactionId", deviceTransactionId).getResultList();
        if(!tl.isEmpty())
        {
            Iterator i=tl.iterator();
            while(i.hasNext()){
                transaction=(Transaction) i.next();
            }
            return transaction;
        }
        return null;
    }
    
    public int getDeviceNoBranchId(String deviceNo){
        
        Device d=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.deviceNo = :deviceNo").setParameter("deviceNo", deviceNo).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getDeviceNo().equals(deviceNo))&& ((d.getDeviceNo()!= null))||(!d.getDeviceNo().isEmpty())){
                return d.getBranchId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    
    
    public Double getProductPrice(Integer branchId,Integer productId){
        
        BranchProductPrice branchProductPrice=(BranchProductPrice) em.createQuery("SELECT b FROM BranchProductPrice b WHERE b.branchProductPricePK.branchId = :branchId and b.branchProductPricePK.productId = :productId").setParameter("branchId", branchId).setParameter("productId", productId).getSingleResult();
        if(branchProductPrice==null){
            return null;
        }
        else{
            return branchProductPrice.getBPrice();
        }
        
    }
    
    
    
    public void addDefaultProductPriceToProductManager(Product product){
        
        Branch branch;
        List<Branch> branchesList=(List<Branch>)em.createQuery("SELECT b FROM Branch b").getResultList();
        if(!branchesList.isEmpty()){
            Iterator i=branchesList.iterator();
            while(i.hasNext()){
                branch=(Branch) i.next();
                
                BranchProductPricePK branchProductPricePK=new BranchProductPricePK();
                branchProductPricePK.setBranchId(branch.getBranchId());
                branchProductPricePK.setProductId(product.getProductId());
                
                BranchProductPrice branchProductPrice=new BranchProductPrice();
                branchProductPrice.setBranchProductPricePK(branchProductPricePK);
                branchProductPrice.setBPrice(product.getHqPrice());
                em.persist(branchProductPrice);
                em.flush();
            }
        }
    }
    
    public void addDefaultProductPriceToBranchManager(Branch branch){
        
        Product product;
        List<Product> productList=(List<Product>)em.createQuery("SELECT p FROM Product p").getResultList();
        if(!productList.isEmpty()){
            Iterator i=productList.iterator();
            while(i.hasNext()){
                product=(Product) i.next();
                
                BranchProductPricePK branchProductPricePK=new BranchProductPricePK();
                branchProductPricePK.setBranchId(branch.getBranchId());
                branchProductPricePK.setProductId(product.getProductId());
                
                BranchProductPrice branchProductPrice=new BranchProductPrice();
                branchProductPrice.setBranchProductPricePK(branchProductPricePK);
                branchProductPrice.setBPrice(product.getHqPrice());
                em.persist(branchProductPrice);
                em.flush();
            }
        }
    }
    
    public String getBrowserName(){
        
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        String browserName = browser.getName();
        
        return browserName;
        
    }
    
    public int isDeviceAvailable(String deviceNo){
        
        Device d=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.deviceNo = :deviceNo").setParameter("deviceNo", deviceNo).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getDeviceNo().equals(deviceNo))&& ((d.getDeviceNo()!= null))||(!d.getDeviceNo().isEmpty())){
                return d.getDeviceId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public long isVoucherAvailable(String voucherNo){
        
        Voucher voucher=new Voucher();
        List<Voucher> voucherList=(List<Voucher>)em.createQuery("SELECT v FROM Voucher v WHERE v.voucherNo = :voucherNo").setParameter("voucherNo", voucherNo).getResultList();
        if(!voucherList.isEmpty())
        {
            Iterator i=voucherList.iterator();
            while(i.hasNext()){
                voucher=(Voucher) i.next();
            }
            if((voucher.getVoucherNo().equals(voucherNo))&& ((voucher.getVoucherNo()!= null))||(!voucher.getVoucherNo().isEmpty())){
                return voucher.getVoucherId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public Voucher voucherDetails(String voucherNo){
        
        Voucher voucher=new Voucher();
        List<Voucher> voucherList=(List<Voucher>)em.createQuery("SELECT v FROM Voucher v WHERE v.voucherNo = :voucherNo").setParameter("voucherNo", voucherNo).getResultList();
        if(!voucherList.isEmpty())
        {
            Iterator i=voucherList.iterator();
            while(i.hasNext()){
                voucher=(Voucher) i.next();
            }
            
            return voucher;
        }
        return null;
    }
    
    
}