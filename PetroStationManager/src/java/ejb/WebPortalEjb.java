/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;


import entities.Admin;
import entities.Branch;
import entities.Company;
import entities.Device;
import entities.Logger;
import entities.Product;
import entities.Transaction;
import entities.User;
import entitiesFacade.BranchFacade;
import entitiesFacade.DeviceFacade;
import entitiesFacade.ProductFacade;
import entitiesFacade.PumpFacade;
import entitiesFacade.UserFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import portalbeans.BranchCreate;
import portalbeans.BranchDeactive;
import portalbeans.BranchEdit;
import portalbeans.BranchName;
import portalbeans.BranchPortal;
import portalbeans.DeviceCreate;
import portalbeans.DeviceDeactive;
import portalbeans.DeviceEdit;
import portalbeans.DeviceName;
import portalbeans.DevicePortal;
import portalbeans.FilterBean;
import portalbeans.LoggerPortal;
import portalbeans.ProductCreate;
import portalbeans.ProductDeactive;
import portalbeans.ProductEdit;
import portalbeans.ProductName;
import portalbeans.ProductPortal;
import portalbeans.TransactionFilterPortal;
import portalbeans.TransactionPortal;
import portalbeans.UserCreate;
import portalbeans.UserDeactive;
import portalbeans.UserEdit;
import portalbeans.UserName;
import portalbeans.UserPortal;

/**
 *
 * @author JOHN
 */
@Stateless
public class WebPortalEjb {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private RandomPin rp;
    
    @EJB
    private BranchFacade branchFacade;
    
    @EJB
    private DeviceFacade deviceFacade;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private PumpFacade pumpFacade;
    
    @EJB
    private ProductFacade productFacade;
    
    @EJB
    private Email emailEjb;
    
    
    
    
    public String validateLogin(String user,String pass)throws Exception{
        
        Admin a=new Admin();
        List<Admin> al=(List<Admin>) em.createQuery("SELECT a FROM Admin a WHERE a.username = :username and a.password = :password").setParameter("username", user).setParameter("password", pass).getResultList();
        if(!al.isEmpty()){
            Iterator i=al.iterator();
            while(i.hasNext()){
                a=(Admin) i.next();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dt=dateFormat.format(date);
                Date dtt=dateFormat.parse(dt);
                
                Logger lgr=new Logger();
                lgr.setEmail(a.getEmail());
                lgr.setFirstName(a.getFirstName());
                lgr.setLastName(a.getLastName());
                lgr.setLoggerName(a.getUsername());
                lgr.setLoggerType(a.getType());
                lgr.setLoginTime(dtt);
                lgr.setPhoneNo(a.getPhoneNo());
                lgr.setLogMachineType("web");
                em.persist(lgr);
            }
            if((a.getUsername().equals(user)) && (a.getPassword().equals(pass))){
                
                return a.getUsername();
                
            }
            else{
                return "invalid";
            }
            
        }
        else{
            return "invalid";
        }
        
    }
    
    public String sendEmail(String email){
        
        Admin a=new Admin();
        List<Admin> al=(List<Admin>) em.createQuery("SELECT a FROM Admin a WHERE a.email = :email").setParameter("email", email).getResultList();
        if(!al.isEmpty()){
            Iterator i=al.iterator();
            while(i.hasNext()){
                a=(Admin) i.next();
            }
            String msg=emailEjb.sendPasswordByMail(a.getEmail(), a.getUsername(), a.getPassword());
            
            return msg;
        }
        else{
            return "Wrong Email";
        }
        
    }
    
    public List<BranchPortal> getBranches(int cid){
        
        List<BranchPortal> branchList = new ArrayList<BranchPortal>();
        Branch b=new Branch();
        List<Branch> bl=(List<Branch>) em.createQuery("SELECT b FROM Branch b WHERE b.cId.cId = :cId").setParameter("cId", cid).getResultList();
        Iterator i = bl.iterator();
        while (i.hasNext()) {
            b = (Branch) i.next();
            
            BranchPortal branchPortal=new BranchPortal();
            
            branchPortal.setBranchId(b.getBId().toString());
            branchPortal.setBranchName(b.getBName());
            branchPortal.setBranchDescr(b.getBDescr());
            branchPortal.setCompanyId(b.getCId().getCId().toString());
            branchPortal.setCompanyName(b.getCId().getCName());
            branchPortal.setStatus(b.getBStatus());
            
            branchList.add(branchPortal);
        }
        
        
        return branchList;
    }
    
    public List<BranchPortal> getOneBranch(String braId){
        
        int bId=Integer.parseInt(braId);
        List<BranchPortal> branchList = new ArrayList<BranchPortal>();
        Branch b=new Branch();
        List<Branch> bl=(List<Branch>) em.createQuery("SELECT b FROM Branch b WHERE b.bId = :bId").setParameter("bId", bId).getResultList();
        Iterator i = bl.iterator();
        while (i.hasNext()) {
            b = (Branch) i.next();
            
            BranchPortal branchPortal=new BranchPortal();
            
            branchPortal.setBranchId(b.getBId().toString());
            branchPortal.setBranchName(b.getBName());
            branchPortal.setBranchDescr(b.getBDescr());
            branchPortal.setCompanyId(b.getCId().getCId().toString());
            branchPortal.setCompanyName(b.getCId().getCName());
            
            branchList.add(branchPortal);
        }
        
        
        return branchList;
    }
    
    public List<BranchName> getAllBrancheName(int cid){
        
        List<BranchName> branchList = new ArrayList<BranchName>();
        Branch b=new Branch();
        List<Branch> bl=(List<Branch>) em.createQuery("SELECT b FROM Branch b WHERE b.cId.cId = :cId").setParameter("cId", cid).getResultList();
        Iterator i = bl.iterator();
        while (i.hasNext()) {
            b = (Branch) i.next();
            
            BranchName branchPortal=new BranchName();
            
            
            branchPortal.setBranchname(b.getBName());
            
            
            branchList.add(branchPortal);
        }
        
        
        return branchList;
    }
    
    
    public List<UserPortal> getAllUser(){
        
        List<UserPortal> userPortalList = new ArrayList<UserPortal>();
        User u=new User();
        List<User> ul=(List<User>) em.createQuery("SELECT u FROM User u").getResultList();
        Iterator i = ul.iterator();
        while (i.hasNext()) {
            u = (User) i.next();
            
            UserPortal up=new UserPortal();
            up.setUserId(u.getUId().toString());
            up.setUserName(u.getUName());
            up.setUserType(u.getUType());
            up.setUserIdCard(u.getUIdcard());
            up.setUserPin(u.getUPin());
            up.setStatus(u.getUStatus());
            up.setUserNo(u.getUNo());
            up.setBranchId(u.getBId().getBId().toString());
            up.setBranchName(u.getBId().getBName());
            
            userPortalList.add(up);
        }
        
        
        return userPortalList;
        
    }
    
    public List<UserPortal> getOneUser(String userId){
        
        int uId=Integer.parseInt(userId);
        List<UserPortal> userPortalList = new ArrayList<UserPortal>();
        User u=new User();
        List<User> ul=(List<User>) em.createQuery("SELECT u FROM User u WHERE u.uId = :uId").setParameter("uId", uId).getResultList();
        Iterator i = ul.iterator();
        while (i.hasNext()) {
            u = (User) i.next();
            
            UserPortal up=new UserPortal();
            up.setUserId(u.getUId().toString());
            up.setUserName(u.getUName());
            up.setUserType(u.getUType());
            up.setUserIdCard(u.getUIdcard());
            up.setUserPin(u.getUPin());
            up.setStatus(u.getUStatus());
            up.setUserNo(u.getUNo());
            up.setBranchId(u.getBId().getBId().toString());
            up.setBranchName(u.getBId().getBName());
            
            userPortalList.add(up);
        }
        
        
        return userPortalList;
    }
    
    public List<UserName> getAllUserName(){
        
        List<UserName> userNameList = new ArrayList<UserName>();
        User u=new User();
        List<User> ul=(List<User>) em.createQuery("SELECT u FROM User u").getResultList();
        Iterator i = ul.iterator();
        while (i.hasNext()) {
            u = (User) i.next();
            
            UserName un=new UserName();
            un.setUserName(u.getUName());
            userNameList.add(un);
        }
        return userNameList;
        
    }
    
    
    
    
    
    public List<DevicePortal> getAllDevice(){
        
        List<DevicePortal> devicePortalList = new ArrayList<DevicePortal>();
        Device d=new Device();
        List<Device> ul=(List<Device>) em.createQuery("SELECT d FROM Device d").getResultList();
        Iterator i = ul.iterator();
        while (i.hasNext()) {
            d = (Device) i.next();
            
            DevicePortal dp=new DevicePortal();
            dp.setDeviceId(d.getDId().toString());
            dp.setImei(d.getImeiNo());
            dp.setBranchId(d.getBId().getBId().toString());
            dp.setBranchName(d.getBId().getBName());
            dp.setStatus(d.getDStatus());
            devicePortalList.add(dp);
        }
        
        
        return devicePortalList;
        
    }
    
    public List<DevicePortal> getOneDevice(String deviceId){
        
        int dId=Integer.parseInt(deviceId);
        List<DevicePortal> devicePortalList = new ArrayList<DevicePortal>();
        Device d=new Device();
        List<Device> ul=(List<Device>) em.createQuery("SELECT d FROM Device d WHERE d.dId = :dId").setParameter("dId", dId).getResultList();
        
        Iterator i = ul.iterator();
        while (i.hasNext()) {
            d = (Device) i.next();
            
            DevicePortal dp=new DevicePortal();
            dp.setDeviceId(d.getDId().toString());
            dp.setImei(d.getImeiNo());
            dp.setBranchId(d.getBId().getBId().toString());
            dp.setBranchName(d.getBId().getBName());
            
            devicePortalList.add(dp);
        }
        
        
        return devicePortalList;
    }
    
    public List<DeviceName> getAllDeviceName(){
        
        List<DeviceName> devicePortalList = new ArrayList<DeviceName>();
        Device d=new Device();
        List<Device> ul=(List<Device>) em.createQuery("SELECT d FROM Device d").getResultList();
        Iterator i = ul.iterator();
        while (i.hasNext()) {
            d = (Device) i.next();
            
            DeviceName dp=new DeviceName();
            
            dp.setImei(d.getImeiNo());
            
            devicePortalList.add(dp);
        }
        
        
        return devicePortalList;
        
    }
    
    public List<ProductPortal> getAllProduct(){
        
        List<ProductPortal> productPortalList = new ArrayList<ProductPortal>();
        Product p=new Product();
        List<Product> pl=(List<Product>) em.createQuery("SELECT p FROM Product p").getResultList();
        Iterator i = pl.iterator();
        while (i.hasNext()) {
            p = (Product) i.next();
            
            ProductPortal pp=new ProductPortal();
            pp.setProductId(p.getProId().toString());
            pp.setProductName(p.getProName());
            pp.setPrice(p.getProUnityPrice());
            pp.setUnity(p.getProMeasureUnity());
            pp.setBranchId(p.getBId().getBId().toString());
            pp.setBranchName(p.getBId().getBName());
            pp.setStatus(p.getPStatus());
            productPortalList.add(pp);
        }
        
        
        return productPortalList;
        
    }
    
    public List<ProductName> getAllProductName(){
        
        List<ProductName> productPortalList = new ArrayList<ProductName>();
        Product p=new Product();
        List<Product> pl=(List<Product>) em.createQuery("SELECT p FROM Product p").getResultList();
        Iterator i = pl.iterator();
        while (i.hasNext()) {
            p = (Product) i.next();
            
            ProductName pp=new ProductName();
            
            pp.setProductName(p.getProName());
            
            productPortalList.add(pp);
        }
        
        
        return productPortalList;
        
    }
    
    public List<ProductPortal> getOneProduct(String productId){
        
        int proId=Integer.parseInt(productId);
        List<ProductPortal> productPortalList = new ArrayList<ProductPortal>();
        Product p=new Product();
        List<Product> pl=(List<Product>) em.createQuery("SELECT p FROM Product p WHERE p.proId = :proId").setParameter("proId", proId).getResultList();
        
        Iterator i = pl.iterator();
        while (i.hasNext()) {
            p = (Product) i.next();
            
            ProductPortal pp=new ProductPortal();
            pp.setProductId(p.getProId().toString());
            pp.setProductName(p.getProName());
            pp.setPrice(p.getProUnityPrice());
            pp.setUnity(p.getProMeasureUnity());
            pp.setBranchId(p.getBId().getBId().toString());
            pp.setBranchName(p.getBId().getBName());
            
            productPortalList.add(pp);
        }
        
        
        return productPortalList;
    }
    
    
    
    public List<TransactionPortal> getAllTransaction(){
        
        List<TransactionPortal> transactionPortalList = new ArrayList<TransactionPortal>();
        Transaction t=new Transaction();
        List<Transaction> tl=(List<Transaction>) em.createQuery("SELECT t FROM Transaction t ORDER BY t.traId DESC").getResultList();
        Iterator i = tl.iterator();
        while (i.hasNext()) {
            t = (Transaction) i.next();
            
            TransactionPortal tp=new TransactionPortal();
            tp.setTraId(t.getTraId().toString());
            tp.setAmount(t.getAmount().toString());
            tp.setPaymentMode(t.getPaymentMode());
            tp.setPaymentStatus(t.getPaymentStatus());
            tp.setQuantity(t.getQuantity().toString());
            tp.setBranchName(t.getBId().getBName());
            tp.setUserName(t.getUId().getUName());
            tp.setProName(t.getProId().getProName());
            tp.setImei(t.getDId().getImeiNo());
            tp.setCustName(t.getCustId().getCustTel());
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date=df.format(t.getReqDatetime());
            tp.setDatetime(date);
            
            transactionPortalList.add(tp);
        }
        
        
        return transactionPortalList;
    }
    
    public List<TransactionFilterPortal> getFilterTransaction(FilterBean fb){
        
        
        
        String branchName=fb.getBranchName();
        String paymentMode=fb.getPaymentType();
        String paymentStatus=fb.getPaymentStatus();
        String userName=fb.getUserName();
        String productName=fb.getProductName();
        String custTel=fb.getCustTel();
        
        
        List<TransactionFilterPortal> transactionPortalList = new ArrayList<TransactionFilterPortal>();
        Transaction t=new Transaction();
        
        List<Transaction> tl=(List<Transaction>) em.createQuery("SELECT t FROM Transaction t WHERE t.paymentStatus = :paymentStatus or t.paymentMode = :paymentMode or t.uId.uName = :userName or t.bId.bName = :branchName or t.proId.proName = :productName or t.custId.custTel = :custTel ").setParameter("paymentStatus", paymentStatus).setParameter("paymentMode", paymentMode).setParameter("userName", userName).setParameter("branchName", branchName).setParameter("productName", productName).setParameter("custTel", custTel).setParameter("paymentStatus", paymentStatus).setParameter("paymentMode", paymentMode).getResultList();
        
        Iterator i = tl.iterator();
        while (i.hasNext()) {
            t = (Transaction) i.next();
            
            TransactionFilterPortal tp=new TransactionFilterPortal();
            tp.setTraId(t.getTraId().toString());
            tp.setAmount(t.getAmount().toString());
            tp.setPaymentMode(t.getPaymentMode());
            tp.setPaymentStatus(t.getPaymentStatus());
            tp.setQuantity(t.getQuantity().toString());
            tp.setBranchName(t.getBId().getBName());
            tp.setUserName(t.getUId().getUName());
            tp.setProName(t.getProId().getProName());
            tp.setImei(t.getDId().getImeiNo());
            tp.setCustName(t.getCustId().getCustTel());
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date=df.format(t.getReqDatetime());
            tp.setDatetime(date);
            
            transactionPortalList.add(tp);
        }
        
        
        
        return transactionPortalList;
        
    }
    
    public List<TransactionPortal> getOneTransaction(String transactionId){
        
        int traId=Integer.parseInt(transactionId);
        List<TransactionPortal> transactionPortalList = new ArrayList<TransactionPortal>();
        Transaction t=new Transaction();
        List<Transaction> tl=(List<Transaction>) em.createQuery("SELECT t FROM Transaction t WHERE t.traId = :traId").setParameter("traId", traId).getResultList();
        
        Iterator i = tl.iterator();
        while (i.hasNext()) {
            t = (Transaction) i.next();
            
            TransactionPortal tp=new TransactionPortal();
            tp.setTraId(t.getTraId().toString());
            tp.setAmount(t.getAmount().toString());
            tp.setPaymentMode(t.getPaymentMode());
            tp.setPaymentStatus(t.getPaymentStatus());
            tp.setQuantity(t.getQuantity().toString());
            tp.setBranchName(t.getBId().getBName());
            tp.setUserName(t.getUId().getUName());
            tp.setProName(t.getProId().getProName());
            tp.setImei(t.getDId().getImeiNo());
            tp.setCustName(t.getCustId().getCustTel());
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date=df.format(t.getReqDatetime());
            tp.setDatetime(date);
            
            transactionPortalList.add(tp);
        }
        
        
        return transactionPortalList;
    }
    
    public List<LoggerPortal> getAllLogger(String logMachineType){
        
        List<LoggerPortal> loggerPortalList = new ArrayList<LoggerPortal>();
        Logger l=new Logger();
        List<Logger> ll=(List<Logger>) em.createQuery("SELECT l FROM Logger l WHERE l.logMachineType = :logMachineType ORDER BY l.loggerId DESC").setParameter("logMachineType", logMachineType).getResultList();
        Iterator i = ll.iterator();
        while (i.hasNext()) {
            l = (Logger) i.next();
            
            LoggerPortal lp=new LoggerPortal();
            lp.setLoggerId(l.getLoggerId().toString());
            lp.setLoggerName(l.getLoggerName());
            lp.setLoggerType(l.getLoggerType()+":"+l.getLogMachineType());
            
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String date=df.format(l.getLoginTime());
            
            lp.setLoginTime(date);
            lp.setLogoutTime(date);
            lp.setFastname(l.getFirstName());
            lp.setLastname(l.getLastName());
            lp.setEmail(l.getEmail());
            lp.setPhno(l.getPhoneNo());
            
            loggerPortalList.add(lp);
        }
        
        
        return loggerPortalList;
    }
    
    
    
    
    
    
    
//***************************************************************** CREATE **********************************************************
    
    
    
    
    public void createBranch(BranchCreate bc) {
        
        Branch b =new Branch();
        Company c=new Company();
        c.setCId(1);
        b.setCId(c);
        b.setBName(bc.getBranchName());
        b.setBDescr(bc.getAddress());
        b.setBStatus("ACTIVE");
        em.persist(b);
    }
    
    
    public void createDevice(DeviceCreate dc) {
        
        Device d =new Device();
        Branch b=new Branch();
        b.setBId(Integer.parseInt(dc.getBranchId()));
        d.setBId(b);
        d.setImeiNo(dc.getImei());
        
        em.persist(d);
    }
    
    
    public void createUser(UserCreate uc) {
        
        User u=new User();
        Branch b=new Branch();
        b.setBId(Integer.parseInt(uc.getBranchId()));
        u.setBId(b);
        u.setFname(uc.getFirstName());
        u.setLname(uc.getLastName());
        u.setUName(uc.getUserName());
        u.setUType(uc.getUserType());
        u.setUIdcard(uc.getIdcard());
        u.setUStatus(uc.getUserStatus());
        u.setUNo(uc.getPhoneNo());
        u.setEmail(uc.getEmail());
        String pin=rp.createPin();
        u.setUPin(pin);
        em.persist(u);
        
        em.flush();
        
        String msg=sendEmailUser(u.getEmail());
    }
    
    public String sendEmailUser(String email){
        
        User u=new User();
        List<User> ul=(List<User>) em.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).getResultList();
        if(!ul.isEmpty()){
            Iterator i=ul.iterator();
            while(i.hasNext()){
                u=(User) i.next();
            }
            String name=u.getFname()+" "+u.getLname();
            String msg=emailEjb.sendPasswordByMail(u.getEmail(), name, u.getUPin());
            
            return msg;
        }
        else{
            return "Wrong Email";
        }
        
    }
    
    
    public void createProduct(ProductCreate pc) {
        
        Product p=new Product();
        Branch b=new Branch();
        b.setBId(Integer.parseInt(pc.getBranchId()));
        p.setBId(b);
        p.setProName(pc.getProductName());
        p.setProUnityPrice(pc.getPrice());
        p.setProMeasureUnity(pc.getUnity());
        
        em.persist(p);
        
    }
    
    
    
    
    
    
    
    
//***************************************************************** EDIT **********************************************************
    
    public void editBranch(BranchEdit be) {
        
        String braId=be.getBranchId();
        int bId=Integer.parseInt(braId);
        
        Branch branch=(Branch) em.createQuery("SELECT b FROM Branch b WHERE b.bId = :bId").setParameter("bId", bId).getSingleResult();
        branch.setBName(be.getBranchName());
        branch.setBDescr(be.getAddress());
        
        branchFacade.edit(branch);
        
        
        
    }
    
    
    public void editDevice(DeviceEdit de) {
        
        String devId=de.getDeviceId();
        int dId=Integer.parseInt(devId);
        
        Device device=(Device) em.createQuery("SELECT d FROM Device d WHERE d.dId = :dId").setParameter("dId", dId).getSingleResult();
        device.setImeiNo(de.getImei());
        
        deviceFacade.edit(device);
        
    }
    
    
    public void editUser(UserEdit ue) {
        
        String userId=ue.getUserId();
        int uId=Integer.parseInt(userId);
        
        User user=(User) em.createQuery("SELECT u FROM User u WHERE u.uId = :uId").setParameter("uId", uId).getSingleResult();
        user.setFname(ue.getFirstName());
        user.setLname(ue.getLastName());
        user.setUName(ue.getUserName());
        user.setUType(ue.getUserType());
        user.setUIdcard(ue.getUserId());
        user.setUStatus(ue.getUserStatus());
        user.setEmail(ue.getEmail());
        user.setUNo(ue.getPhoneNo());
        
        userFacade.edit(user);
        
        
    }
    
    
    public void editProduct(ProductEdit pe) {
        
        String pId=pe.getProductId();
        int proId=Integer.parseInt(pId);
        
        Product product=(Product) em.createQuery("SELECT p FROM Product p WHERE p.proId = :proId").setParameter("proId", proId).getSingleResult();
        product.setProName(pe.getProductName());
        product.setProUnityPrice(pe.getPrice());
        product.setProMeasureUnity(pe.getUnity());
        
        productFacade.edit(product);
        
    }
    
    
    
    
    
    
    
//***************************************************************** DEACTIVE/ACTIVE **********************************************************
    
    
    
    public void activeBranch(BranchDeactive bd){
        
        String braId=bd.getBranchId();
        int bId=Integer.parseInt(braId);
        
        Branch branch=(Branch) em.createQuery("SELECT b FROM Branch b WHERE b.bId = :bId").setParameter("bId", bId).getSingleResult();
        
        String status=branch.getBStatus();
        
        branch.setBStatus("ACTIVE");
        branchFacade.edit(branch);
        
    }
    
    
//    public void activeBranch(BranchDeactive bd){
//
//        String braId=bd.getBranchId();
//        int bId=Integer.parseInt(braId);
//
//        int x=deactiveBranchDevice(bId);
//        int y=deactiveBranchProduct(bId);
//        int z=deactiveBranchUser(bId);
//
//        Branch b=new Branch();
//        List<Branch> bl=(List<Branch>)em.createQuery("SELECT b FROM Branch b WHERE b.bId = :bId").setParameter("bId", bId).getResultList();
//        Iterator i=bl.iterator();
//        while(i.hasNext()){
//            b=(Branch) i.next();
//            b.setBStatus("ACTIVE");
//            branchFacade.edit(b);
//        }
//    }
    
    public int activeBranchDevice(int bId){
        
        Device d=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.bId.bId = :bId").setParameter("bId", bId).getResultList();
        if(!dl.isEmpty()){
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
                d.setDStatus("ACTIVE");
                deviceFacade.edit(d);
            }
            return d.getBId().getBId();
        }
        else{
            return 0;
        }
    }
    
    public int activeBranchProduct(int bId){
        
        Product p=new Product();
        List<Product> pl=(List<Product>)em.createQuery("SELECT p FROM Product p WHERE p.bId.bId = :bId").setParameter("bId", bId).getResultList();
        if(!pl.isEmpty()){
            Iterator i=pl.iterator();
            while(i.hasNext()){
                p=(Product) i.next();
                p.setPStatus("ACTIVE");
                productFacade.edit(p);
            }
            return p.getBId().getBId();
        }
        else{
            return 0;
        }
    }
    
    public int activeBranchUser(int bId){
        
        User u=new User();
        List<User> ul=(List<User>)em.createQuery("SELECT u FROM User u WHERE u.bId.bId = :bId").setParameter("bId", bId).getResultList();
        if(!ul.isEmpty()){
            Iterator i=ul.iterator();
            while(i.hasNext()){
                u=(User) i.next();
                u.setUStatus("ACTIVE");
                userFacade.edit(u);
            }
            return u.getBId().getBId();
        }
        else{
            return 0;
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------
    
    //    public void deactiveBranch(BranchDeactive bd){
//
//        String braId=bd.getBranchId();
//        int bId=Integer.parseInt(braId);
//
//        Branch branch=(Branch) em.createQuery("SELECT b FROM Branch b WHERE b.bId = :bId").setParameter("bId", bId).getSingleResult();
//        int branchId=branch.getBId();
//
//        branch.setBStatus("DEACTIVE");
//        branchFacade.edit(branch);
//
//
//    }
    
    
    
    
    public void deactiveBranch(BranchDeactive bd){
        
        String braId=bd.getBranchId();
        int bId=Integer.parseInt(braId);
        
        int x=deactiveBranchDevice(bId);
        int y=deactiveBranchProduct(bId);
        int z=deactiveBranchUser(bId);
        
        Branch b=new Branch();
        List<Branch> bl=(List<Branch>)em.createQuery("SELECT b FROM Branch b WHERE b.bId = :bId").setParameter("bId", bId).getResultList();
        Iterator i=bl.iterator();
        while(i.hasNext()){
            b=(Branch) i.next();
            b.setBStatus("DEACTIVE");
            branchFacade.edit(b);
        }
    }
    
    public int deactiveBranchDevice(int bId){
        
        Device d=new Device();
        List<Device> dl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.bId.bId = :bId").setParameter("bId", bId).getResultList();
        if(!dl.isEmpty()){
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
                d.setDStatus("DEACTIVE");
                deviceFacade.edit(d);
            }
            return d.getBId().getBId();
        }
        else{
            return 0;
        }
    }
    
    public int deactiveBranchProduct(int bId){
        
        Product p=new Product();
        List<Product> pl=(List<Product>)em.createQuery("SELECT p FROM Product p WHERE p.bId.bId = :bId").setParameter("bId", bId).getResultList();
        if(!pl.isEmpty()){
            Iterator i=pl.iterator();
            while(i.hasNext()){
                p=(Product) i.next();
                p.setPStatus("DEACTIVE");
                productFacade.edit(p);
            }
            return p.getBId().getBId();
        }
        else{
            return 0;
        }
    }
    
    public int deactiveBranchUser(int bId){
        
        User u=new User();
        List<User> ul=(List<User>)em.createQuery("SELECT u FROM User u WHERE u.bId.bId = :bId").setParameter("bId", bId).getResultList();
        if(!ul.isEmpty()){
            Iterator i=ul.iterator();
            while(i.hasNext()){
                u=(User) i.next();
                u.setUStatus("DEACTIVE");
                userFacade.edit(u);
            }
            return u.getBId().getBId();
        }
        else{
            return 0;
        }
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------
    
    
    public void activeDevice(DeviceDeactive dd){
        
        String devId=dd.getDeviceId();
        int dId=Integer.parseInt(devId);
        
        Device device=(Device) em.createQuery("SELECT d FROM Device d WHERE d.dId = :dId").setParameter("dId", dId).getSingleResult();
        
        device.setDStatus("ACTIVE");
        deviceFacade.edit(device);
        
    }
    
    public void deactiveDevice(DeviceDeactive dd){
        
        String devId=dd.getDeviceId();
        int dId=Integer.parseInt(devId);
        
        Device device=(Device) em.createQuery("SELECT d FROM Device d WHERE d.dId = :dId").setParameter("dId", dId).getSingleResult();
        
        device.setDStatus("DEACTIVE");
        deviceFacade.edit(device);
        
    }
    
    public void activeUser(UserDeactive ud){
        
        String userId=ud.getUserId();
        int uId=Integer.parseInt(userId);
        
        User user=(User) em.createQuery("SELECT u FROM User u WHERE u.uId = :uId").setParameter("uId", uId).getSingleResult();
        
        user.setUStatus("ACTIVE");
        userFacade.edit(user);
        
    }
    
    
    public void deactiveUser(UserDeactive ud){
        
        String userId=ud.getUserId();
        int uId=Integer.parseInt(userId);
        
        User user=(User) em.createQuery("SELECT u FROM User u WHERE u.uId = :uId").setParameter("uId", uId).getSingleResult();
        
        user.setUStatus("DEACTIVE");
        userFacade.edit(user);
        
    }
    
    
    public void activeProduct(ProductDeactive pd){
        
        String pId=pd.getProductId();
        int proId=Integer.parseInt(pId);
        
        Product product=(Product) em.createQuery("SELECT p FROM Product p WHERE p.proId = :proId").setParameter("proId", proId).getSingleResult();
        
        product.setPStatus("ACTIVE");
        productFacade.edit(product);
        
    }
    
    public void deactiveProduct(ProductDeactive pd){
        
        String pId=pd.getProductId();
        int proId=Integer.parseInt(pId);
        
        Product product=(Product) em.createQuery("SELECT p FROM Product p WHERE p.proId = :proId").setParameter("proId", proId).getSingleResult();
        
        product.setPStatus("DEACTIVE");
        productFacade.edit(product);
    }
    
}
