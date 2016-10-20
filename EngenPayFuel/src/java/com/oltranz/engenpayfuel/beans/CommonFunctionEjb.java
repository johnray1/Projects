/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engenpayfuel.beans;

import com.oltranz.engenpayfuel.entities.Branch;
import com.oltranz.engenpayfuel.entities.BranchProductPrice;
import com.oltranz.engenpayfuel.entities.BranchProductPricePK;
import com.oltranz.engenpayfuel.entities.Customer;
import com.oltranz.engenpayfuel.entities.Device;
import com.oltranz.engenpayfuel.entities.ErroneousTransaction;
import com.oltranz.engenpayfuel.entities.IndexTracking;
import com.oltranz.engenpayfuel.entities.Nozzle;
import com.oltranz.engenpayfuel.entities.PaymentMode;
import com.oltranz.engenpayfuel.entities.Product;
import com.oltranz.engenpayfuel.entities.ProductType;
import com.oltranz.engenpayfuel.entities.Pump;
import com.oltranz.engenpayfuel.entities.Role;
import com.oltranz.engenpayfuel.entities.RoleType;
import com.oltranz.engenpayfuel.entities.Tank;
import com.oltranz.engenpayfuel.entities.TankTracking;
import com.oltranz.engenpayfuel.entities.Transaction;
import com.oltranz.engenpayfuel.entities.User;
import com.oltranz.engenpayfuel.entities.Voucher;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    
    public Tank getTank(int tankId){
        
        Tank tank=em.find(Tank.class, tankId);
        if(tank==null){
            return null;
        }
        return tank;
    }
    
    public Nozzle getNozzle(int nozzleId){
        
        Nozzle Nozzle=em.find(Nozzle.class, nozzleId);
        if(Nozzle==null){
            return null;
        }
        return Nozzle;
    }
    
    public int getNozzleNo(int pumpId){
        
        List<Nozzle> nozzleList=(List<Nozzle>) em.createQuery("SELECT n FROM Nozzle n WHERE n.pumpId = :pumpId").setParameter("pumpId", pumpId).getResultList();
        return nozzleList.size();
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
    //-------------------------------------------------DEVICE----------------------------------------------------------
    
    public Device getDeviceNoId(String deviceName){
        
        Device device=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.deviceName = :deviceName").setParameter("deviceName", deviceName).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                device=(Device) i.next();
            }
            if((device.getDeviceName().equals(deviceName))&& ((device.getDeviceName()!= null))||(!device.getDeviceName().isEmpty())){
                return device;
            }
            else{
                return null;
            }
        }
        return null;
    }
    
    public int isDeviceAvailable(String deviceName){
        
        Device d=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.deviceName = :deviceName").setParameter("deviceName", deviceName).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getDeviceName().equals(deviceName))&& ((d.getDeviceName()!= null))||(!d.getDeviceName().isEmpty())){
                return d.getDeviceId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public int getDeviceNoBranchId(String deviceName){
        
        Device d=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.deviceName = :deviceName").setParameter("deviceName", deviceName).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getDeviceName().equals(deviceName))&& ((d.getDeviceName()!= null))||(!d.getDeviceName().isEmpty())){
                return d.getBranchId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public Device getSerialNo(String serialNo){
        
        Device device=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.serialNo = :serialNo").setParameter("serialNo", serialNo).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                device=(Device) i.next();
            }
            if((device.getSerialNo().equals(serialNo))&& ((device.getSerialNo()!= null))||(!device.getSerialNo().isEmpty())){
                return device;
            }
            else{
                return null;
            }
        }
        return null;
    }
    
    public Device getDevice(int branchId){
        
        Device device=null;
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.branchId = :branchId").setParameter("branchId", branchId).getResultList();
        if(dl.size()>0){
            device=dl.get(0);
            return device;
        }else{
            return device;
        }
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
    
    
    
    
    
    //----------------------------------------------------------------Transaction------------------------------------------------------------------------
    
    
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
    
    
    
    public IndexTracking getIndexTracking(long transactionId){
        IndexTracking indexTracking=(IndexTracking) em.createQuery("SELECT i FROM IndexTracking i WHERE i.transactionId = :transactionId").setParameter("transactionId", transactionId).getSingleResult();
        if(indexTracking==null){
            return null;
        }
        return indexTracking;
    }
    
    
    
    public TankTracking getTankTracking(long transactionId){
        TankTracking tankTracking=(TankTracking) em.createQuery("SELECT t FROM TankTracking t WHERE t.transactionId = :transactionId").setParameter("transactionId", transactionId).getSingleResult();
        if(tankTracking==null){
            return null;
        }
        return tankTracking;
    }
    
    
    
    public ErroneousTransaction createErroneousTransaction(Transaction transaction){
        
        
        //check the list size for set check sum
        List<ErroneousTransaction> erroneousTransactionList=(List<ErroneousTransaction>)em.createQuery("SELECT e FROM ErroneousTransaction e WHERE e.deviceTransactionId = :deviceTransactionId").setParameter("deviceTransactionId", transaction.getDeviceTransactionId()).getResultList();
        Integer checkSum = erroneousTransactionList.size()+1;
        
        
        //set erroneous transaction
        ErroneousTransaction erroneousTransaction=new ErroneousTransaction();
        erroneousTransaction.setChecksum(checkSum);
        erroneousTransaction.setTransactionId(transaction.getTransactionId());
        erroneousTransaction.setDeviceTransactionId(transaction.getDeviceTransactionId());
        erroneousTransaction.setDeviceTransactionTime(transaction.getDeviceTransactionTime());
        erroneousTransaction.setBranchId(transaction.getBranchId());
        erroneousTransaction.setUserId(transaction.getUserId());
        erroneousTransaction.setDeviceId(transaction.getDeviceId());
        erroneousTransaction.setPumpId(transaction.getTankId());
        erroneousTransaction.setPumpId(transaction.getPumpId());
        erroneousTransaction.setNozzleId(transaction.getNozzleId());
        erroneousTransaction.setProductId(transaction.getProductId());
        erroneousTransaction.setCustomerId(transaction.getCustomerId());
        erroneousTransaction.setPaymentModeId(transaction.getPaymentModeId());
        erroneousTransaction.setPaymentStatus(transaction.getPaymentStatus());
        erroneousTransaction.setAmount(transaction.getAmount());
        erroneousTransaction.setQuantity(transaction.getQuantity());
        erroneousTransaction.setPlatenumber(transaction.getPlatenumber());
        erroneousTransaction.setServerReqTime(transaction.getServerReqTime());
        erroneousTransaction.setServerResTime(transaction.getServerResTime());
        erroneousTransaction.setDate(transaction.getDate());
        em.persist(erroneousTransaction);
        em.flush();
        
        
        //After set corrected Nozzle Index
        Nozzle nozzle=em.find(Nozzle.class, transaction.getNozzleId());
        double correctedIndex=nozzle.getNozzleIndex()-transaction.getQuantity();
        nozzle.setNozzleIndex(correctedIndex);
        em.merge(nozzle);
        em.flush();
        
        //After set corrected Tank Quantity
        Pump pump=getPumpName(transaction.getPumpId());
        Tank tank=getTank(nozzle.getTankId());
        double correctQuantity=tank.getCurrentCapacity()+transaction.getQuantity();
        tank.setCurrentCapacity(correctQuantity);
        em.merge(tank);
        em.flush();
        
        return erroneousTransaction;
        
    }
    
    public String [] dayOfMonth(){
        
        
        Calendar cal = Calendar.getInstance();
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("d");
        String [] dayOfMonth=new String[maxDay];
        
        for (int i = 0; i < maxDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1);
            
            dayOfMonth[i]=df.format(cal.getTime());
            
        }
         return dayOfMonth;
    }
    
    public Date getDate(){
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            return dtt;
        }
        catch(ParseException pe){
            return null;
        }
    }
    
}
