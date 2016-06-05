/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ws;

import ejb.CommonLibrary;
import ejb.PetroStationManagerEjb;
import entities.Admin;
import entities.Branch;
import entities.Company;
import entities.Customer;
import entities.Device;
import entities.Product;
import entities.Pump;
import entities.User;
import entities.Voucher;
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JOHN
 */
@Path("stationmanager")
@Stateless
public class PetroWebService {
    
    @EJB
    private PetroStationManagerEjb petroStationManagerEjb;
    
    @EJB
    private CommonLibrary commonLibrary;
    
//COMPANY CREATION AND GET COMPANY DATA
    @POST
    @Path("createcompany")
    @Produces(MediaType.APPLICATION_XML)
    public String createCompany(InputStream is) {
        
        
        Company c=(Company) commonLibrary.unmarshalling(is, Company.class);
        String s=petroStationManagerEjb.createCompany(c);
        return s;
        
    }
    
    @GET
    @Path("getAllC")
    @Produces(MediaType.APPLICATION_XML)
    public List<Company> getAllC(){
        
        List<Company> cl=petroStationManagerEjb.getAllC();
        return cl;
    }
    




//ADMIN CREATION AND GET ADMIN DATA
    
    @POST
    @Path("createadmin")
    @Produces(MediaType.APPLICATION_XML)
    public String createAdmin(InputStream is) {
        
        
        Admin a=(Admin) commonLibrary.unmarshalling(is, Admin.class);
        String s=petroStationManagerEjb.createAdmin(a);
        return s;
        
    }
    
    @GET
    @Path("getAllA")
    @Produces(MediaType.APPLICATION_XML)
    public List<Admin> getAllA(){
        
        List<Admin> al=petroStationManagerEjb.getAllA();
        return al;
    }    
    
    



//BRANCH CREATION AND GET BRANCH DATA
    @POST
    @Path("createbranch")
    @Produces(MediaType.APPLICATION_XML)
    public String createBranch(InputStream is) {
        
        Branch b=(Branch) commonLibrary.unmarshalling(is, Branch.class);
        String s=petroStationManagerEjb.createBranch(b);
        return s;
        
    }
    
    @GET
    @Path("getAllB")
    @Produces(MediaType.APPLICATION_XML)
    public List<Branch> getAllB(){
        
        List<Branch> bl=petroStationManagerEjb.getAllB();
        return bl;
    }
 
  //BRANCH CREATION AND GET BRANCH DATA
    @POST
    @Path("createpump")
    @Produces(MediaType.APPLICATION_XML)
    public String createPump(InputStream is) {
        
        Pump p=(Pump) commonLibrary.unmarshalling(is, Pump.class);
        String s=petroStationManagerEjb.createPump(p);
        return s;
        
    }
    
    @GET
    @Path("getAllPump")
    @Produces(MediaType.APPLICATION_XML)
    public List<Branch> getAllPump(){
        
        List<Branch> bl=petroStationManagerEjb.getAllB();
        return bl;
    }  


//PRODUCT CREATION AND GET PRODUCT DATA
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("createproduct")
    public String createProduct(InputStream is) {
        
        Product p=(Product) commonLibrary.unmarshalling(is, Product.class);
        String s=petroStationManagerEjb.createProduct(p);
        return s;
    }
    
    @GET
    @Path("getAllP")
    @Produces(MediaType.APPLICATION_XML)
    public List<Product> getAllP(){
        
        List<Product> pl=petroStationManagerEjb.getAllP();
        return pl;
    }
    


//DEVICE CREATION AND GET DEVICE DATA
    @POST
    @Path("createdevice")
    @Produces(MediaType.APPLICATION_XML)
    public String createDevice(InputStream is) {
        
        Device d=(Device) commonLibrary.unmarshalling(is, Device.class);
        String s=petroStationManagerEjb.createDevice(d);
        return s;
    }
    
    @GET
    @Path("getAllD")
    @Produces(MediaType.APPLICATION_XML)
    public List<Device> getAllD(){
        
        List<Device> dl=petroStationManagerEjb.getAllD();
        return dl;
    }
    

//CUSTOMER CREATION AND GET CUSTOMER DATA
    
    @POST
    @Path("createcustomer")
    @Produces(MediaType.APPLICATION_XML)
    public String createCustomer(InputStream is) {
        
        Customer c=(Customer) commonLibrary.unmarshalling(is, Customer.class);
        String s=petroStationManagerEjb.createCustomer(c);
        return s;
        
    }
    
    @GET
    @Path("getAllCust")
    @Produces(MediaType.APPLICATION_XML)
    public List<Customer> getAllCust(){
        
        List<Customer> cul=petroStationManagerEjb.getAllCust();
        return cul;
    }
    
//USER CREATION AND GET USER DATA
    @POST
    @Path("createuser")
    @Produces(MediaType.APPLICATION_XML)
    public String createUser(InputStream is) {
        
        User u=(User) commonLibrary.unmarshalling(is, User.class);
        String s=petroStationManagerEjb.createUser(u);
        return s;
        
    }
    
    @GET
    @Path("getAllU")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getAllU(){
        
        List<User> ul=petroStationManagerEjb.getAllU();
        return ul;
    }
    
    //VOUCHER CREATION AND GET VOUCHER DATA
    @POST
    @Path("createvoucher")
    @Produces(MediaType.APPLICATION_XML)
    public String createVoucher(InputStream is) {
        
        
        Voucher v=(Voucher) commonLibrary.unmarshalling(is, Voucher.class);
        String s=petroStationManagerEjb.createVoucher(v);
        return s;
        
    }
    
    @GET
    @Path("getAllV")
    @Produces(MediaType.APPLICATION_XML)
    public List<Voucher> getAllV(){
        
        List<Voucher> vl=petroStationManagerEjb.getAllV();
        return vl;
    }
    
    
    
    
    
    
//**************************************************CURRENTLY NOT NEEDED****************************************************************
    
    
    @GET
    @Path("getCompanyName")
    @Produces(MediaType.APPLICATION_XML)
    public List<Company> getCompanyName() throws Exception {
        
        List<Company> cl = petroStationManagerEjb.getCompanyName();
        return cl;
    }
    
    @GET
    @Path("getBranchList/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Branch> getBranchList(@PathParam("key") int input) throws Exception {
        
        List<Branch> bl = petroStationManagerEjb.getBranchList(input);
        
        return bl;
    }
    
    @GET
    @Path("getProductList/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Product> getProductList(@PathParam("key") int input) throws Exception {
        
        List<Product> pl = petroStationManagerEjb.getProductList(input);
        
        return pl;
    }
    
    @GET
    @Path("getDeviceList/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Device> getDeviceList(@PathParam("key") int input) throws Exception {
        
        List<Device> dl = petroStationManagerEjb.getDeviceList(input);
        
        return dl;
    }
    
    @GET
    @Path("getCustomerList/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Customer> getCustomerList(@PathParam("key") int input) throws Exception {
        
        List<Customer> cl = petroStationManagerEjb.getCustomerList(input);
        
        return cl;
    }
    
    @GET
    @Path("getUserList/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUserList(@PathParam("key") int input) throws Exception {
        
        List<User> ul = petroStationManagerEjb.getUserList(input);
        
        return ul;
    }
    
    
}
