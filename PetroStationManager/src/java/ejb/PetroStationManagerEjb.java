/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;

import entities.Admin;
import entities.Branch;
import entities.Company;
import entities.Customer;
import entities.Device;
import entities.Product;
import entities.Pump;
import entities.User;
import entities.Voucher;
import entitiesFacade.AdminFacade;
import entitiesFacade.BranchFacade;
import entitiesFacade.CompanyFacade;
import entitiesFacade.CustomerFacade;
import entitiesFacade.DeviceFacade;
import entitiesFacade.ProductFacade;
import entitiesFacade.PumpFacade;
import entitiesFacade.TransactionFacade;
import entitiesFacade.UserFacade;
import entitiesFacade.VoucherFacade;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class PetroStationManagerEjb {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private RandomPin rp;
    
    @EJB
    private CompanyFacade companyFacade;
    
    @EJB
    private AdminFacade adminFacade;
    
    @EJB
    private BranchFacade branchFacade;
    
    @EJB
    private PumpFacade pumpFacade;
    
    @EJB
    private ProductFacade productFacade;
    
    @EJB
    private DeviceFacade deviceFacade;
    
    @EJB
    private CustomerFacade customerFacade;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private TransactionFacade transactionFacade;
    
    @EJB
    private VoucherFacade voucherFacade;
    
//COMPANY CREATION AND GET COMPANY DATA
    
    public String createCompany(Company c){
        
        companyFacade.create(c);
        
        return "PERSIST" ;
    }
    
    public List<Company> getAllC(){
        List<Company> cl=companyFacade.findAll();
        return cl;
    }
    
    
//ADMIN CREATION AND GET ADMIN DATA
    
    public String createAdmin(Admin a){
        
        adminFacade.create(a);
        
        return "PERSIST" ;
    }
    
    public List<Admin> getAllA(){
        List<Admin> al=adminFacade.findAll();
        return al;
    }
    
    
//BRANCH CREATION AND GET BRANCH DATA
    
    public String createBranch(Branch b){
        
        branchFacade.create(b);
        return "PERSIST" ;
    }
    
    public List<Branch> getAllB(){
        List<Branch> bl=branchFacade.findAll();
        return bl;
    }
    
//BRANCH CREATION AND GET BRANCH DATA
    
    public String createPump(Pump p){
        
        pumpFacade.create(p);
        return "PERSIST" ;
    }
    
    public List<Pump> getAllPump(){
        List<Pump> pl=pumpFacade.findAll();
        return pl;
    }
    
//PRODUCT CREATION AND GET PRODUCT DATA
    
    public String createProduct(Product p){
        
        productFacade.create(p);
        return "PERSIST" ;
        
    }
    
    public List<Product> getAllP(){
        List<Product> pl=productFacade.findAll();
        return pl;
    }
    
    
//DEVICE CREATION AND GET DEVICE DATA ----------------------------------------------------------------------------------------------------
    
    public String createDevice(Device d){
        
        deviceFacade.create(d);
        return "PERSIST" ;
    }
    
    public List<Device> getAllD(){
        List<Device> dl=deviceFacade.findAll();
        return dl;
    }
    
    
//CUSTOMER CREATION AND GET CUSTOMER DATA ----------------------------------------------------------------------------------------------------
    
    public String createCustomer(Customer c){
        
        customerFacade.create(c);
        return "PERSIST" ;
    }
    
    public List<Customer> getAllCust(){
        List<Customer> cul=customerFacade.findAll();
        return cul;
    }
    
    
//USER CREATION AND GET USER DATA ----------------------------------------------------------------------------------------------------
    
    public String createUser(User u){
        
        String pin=rp.createPin();
        u.setUPin(pin);
        userFacade.create(u);
        return "PERSIST" ;
    }
    
    public List<User> getAllU(){
        List<User> ul=userFacade.findAll();
        return ul;
    }
    
    
    
    
    //VOUCHER CREATION AND GET VOUCHER DATA
    
    public String createVoucher(Voucher v){
        
        voucherFacade.create(v);
        
        return "PERSIST" ;
    }
    
    public List<Voucher> getAllV(){
        List<Voucher> vl=voucherFacade.findAll();
        return vl;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//**************************************************CURRENTLY NOT NEEDED****************************************************************
    
    
    
    public List<Company> getCompanyName(){
        String name="";int id=0;
        Company c=new Company();
        List<Company> cla=new ArrayList<Company>();
        List<Company> cl=(List<Company>)em.createNamedQuery("Company.findAll").getResultList();
        Iterator i=cl.iterator();
        while(i.hasNext()){
            Company c1=new Company();
            c=(Company) i.next();
            id=c.getCId();
            name=c.getCName();
            c1.setCId(id);
            c1.setCName(name);
            cla.add(c1);
        }
        return cla;
    }
    
    public List<Branch> getBranchList(int input){
        String name="";int id=0;
        Branch b=new Branch();
        List<Branch> bla=new ArrayList<Branch>();
        List<Branch> bl=(List<Branch>)em.createQuery("SELECT b FROM Branch b WHERE b.cId.cId = :cId").setParameter("cId", input).getResultList();
        Iterator i=bl.iterator();
        while(i.hasNext()){
            Branch b1=new Branch();
            b=(Branch) i.next();
            id=b.getBId();
            name=b.getBName();
            b1.setBId(id);
            b1.setBName(name);
            bla.add(b1);
        }
        return bla;
        
    }
    
    public List<Product> getProductList(int input){
        String name="";int id=0;
        Product p=new Product();
        List<Product> pla=new ArrayList<Product>();
        List<Product> pl=(List<Product>)em.createQuery("SELECT p FROM Product p WHERE p.bId.bId = :bId").setParameter("bId", input).getResultList();
        Iterator i=pl.iterator();
        while(i.hasNext()){
            Product p1=new Product();
            p=(Product) i.next();
            id=p.getProId();
            name=p.getProName();
            p1.setProId(id);
            p1.setProName(name);
            pla.add(p1);
        }
        return pla;
    }
    
    public List<Device> getDeviceList(int input){
        String no="";int id=0;int bid=0;
        Device d=new Device();
        List<Device> dla=new ArrayList<Device>();
        List<Device> pl=(List<Device>)em.createQuery("SELECT d FROM Device d WHERE d.bId.bId = :bId").setParameter("bId", input).getResultList();
        Iterator i=pl.iterator();
        while(i.hasNext()){
            Device d1=new Device();
            d=(Device) i.next();
            bid=d.getBId().getBId();
            Branch b=new Branch();
            b.setBId(bid);
            id=d.getDId();
            no=d.getImeiNo();
            
            d1.setBId(b);
            d1.setDId(id);
            d1.setImeiNo(no);
            dla.add(d1);
        }
        return dla;
    }
    
    public List<Customer> getCustomerList(int input){
        String no="";String name="";int id=0;
        Customer c=new Customer();
        List<Customer> cla=new ArrayList<Customer>();
        List<Customer> cl=(List<Customer>)em.createQuery("SELECT c FROM Customer c WHERE c.bId.bId = :bId").setParameter("bId", input).getResultList();
        Iterator i=cl.iterator();
        while(i.hasNext()){
            Customer c1=new Customer();
            c=(Customer) i.next();
            id=c.getCustId();
            name=c.getCustName();
            no=c.getCustTel();
            c1.setCustId(id);
            c1.setCustName(name);
            c1.setCustTel(no);
            cla.add(c1);
        }
        return cla;
    }
    
    public List<User> getUserList(int input){
        
        String name="";String idCard="";String type="";String status="";String pin="";
        User u=new User();
        List<User> ula=new ArrayList<User>();
        List<User> cl=(List<User>)em.createQuery("SELECT u FROM User u WHERE u.bId.bId = :bId").setParameter("bId", input).getResultList();
        Iterator i=cl.iterator();
        while(i.hasNext()){
            User u1=new User();
            u=(User) i.next();
            
            name=u.getUName();
            idCard=u.getUIdcard();
            type=u.getUType();
            status=u.getUStatus();
            pin=u.getUPin();
            
            u1.setUName(name);
            u1.setUIdcard(idCard);
            u1.setUType(type);
            u1.setUStatus(status);
            u1.setUPin(pin);
            ula.add(u1);
        }
        return ula;
    }
}
