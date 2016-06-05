/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ws;

import ejb.CommonLibrary;
import ejb.WebPortalEjb;
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
@Path("portalmanager")
@Stateless
public class WebPortalService {
    
    
    @EJB
    private WebPortalEjb webPortalEjb;
    
    
    @EJB
    private CommonLibrary commonLibrary;
    
    
    
    
    @GET
    @Path("validatelogin/{key1}/{key2}")
    @Produces(MediaType.APPLICATION_XML)
    public String validateLogin(@PathParam("key1") String username, @PathParam("key2")String password) throws Exception {
        
        String s = webPortalEjb.validateLogin(username, password);
        
        return s;
    }
    
    
    
    @GET
    @Path("sendmail/{key}")
    @Produces(MediaType.APPLICATION_XML)
    public String sendEmail(@PathParam("key") String email) throws Exception {
        
        String s = webPortalEjb.sendEmail(email);
        
        return s;
    }
    
    
    
    
    @GET
    @Path("getAllBranches/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<BranchPortal> getBranches(@PathParam("key1") int cid){
        
        List<BranchPortal> bl=webPortalEjb.getBranches(cid);
        
        return bl;
    }
    
    @GET
    @Path("getAllBranchName/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<BranchName> getAllBranchName(@PathParam("key1") int cid){
        
        List<BranchName> bl=webPortalEjb.getAllBrancheName(cid);
        
        return bl;
    }
    
    @GET
    @Path("getOneBranch/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<BranchPortal> getOneBranch(@PathParam("key1") String bId){
        
        List<BranchPortal> bl=webPortalEjb.getOneBranch(bId);
        
        return bl;
    }
    
    
    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_XML)
    public List<UserPortal> getAllUser(){
        
        List<UserPortal> upl=webPortalEjb.getAllUser();
        return upl;
    }
    
    @GET
    @Path("getAllUserName")
    @Produces(MediaType.APPLICATION_XML)
    public List<UserName> getAllUserName(){
        
        List<UserName> upl=webPortalEjb.getAllUserName();
        return upl;
    }
    
    @GET
    @Path("getOneUser/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<UserPortal> getOneUser(@PathParam("key1") String uId){
        
        List<UserPortal> upl=webPortalEjb.getOneUser(uId);
        
        return upl;
    }
    
    
    @GET
    @Path("getAllDevices")
    @Produces(MediaType.APPLICATION_XML)
    public List<DevicePortal> getAllDevice(){
        
        List<DevicePortal> dpl=webPortalEjb.getAllDevice();
        return dpl;
    }
    
    @GET
    @Path("getAllDeviceName")
    @Produces(MediaType.APPLICATION_XML)
    public List<DeviceName> getAllDeviceName(){
        
        List<DeviceName> dpl=webPortalEjb.getAllDeviceName();
        
        return dpl;
    }
    
    @GET
    @Path("getOneDevice/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<DevicePortal> getOneDevice(@PathParam("key1") String dId){
        
        List<DevicePortal> dpl=webPortalEjb.getOneDevice(dId);
        
        return dpl;
    }
    
    
    @GET
    @Path("getAllProducts")
    @Produces(MediaType.APPLICATION_XML)
    public List<ProductPortal> getAllProduct(){
        
        List<ProductPortal> ppl=webPortalEjb.getAllProduct();
        return ppl;
    }
    
    @GET
    @Path("getAllProductName")
    @Produces(MediaType.APPLICATION_XML)
    public List<ProductName> getAllProductName(){
        
        List<ProductName> ppl=webPortalEjb.getAllProductName();
        return ppl;
    }
    
    @GET
    @Path("getOneProduct/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ProductPortal> getOneProduct(@PathParam("key1") String pId){
        
        List<ProductPortal> productPortal=webPortalEjb.getOneProduct(pId);
        
        return productPortal;
    }
    
    
    @GET
    @Path("getAllTransactions")
    @Produces(MediaType.APPLICATION_XML)
    public List<TransactionPortal> getAllTransaction(){
        
        List<TransactionPortal> tpl=webPortalEjb.getAllTransaction();
        return tpl;
    }
    
    
    
    @POST
    @Path("getFilterTransactions")
    @Produces(MediaType.APPLICATION_XML)
    public List<TransactionFilterPortal> getFilterTransactions(InputStream is) {
        
        FilterBean fb=(FilterBean) commonLibrary.unmarshalling(is, FilterBean.class);
        List<TransactionFilterPortal> tpl=webPortalEjb.getFilterTransaction(fb);
        
        return tpl;
    }
    
    
    @GET
    @Path("getOneTransaction/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<TransactionPortal> getOneTransaction(@PathParam("key1") String tId){
        
        List<TransactionPortal> transactionPortal=webPortalEjb.getOneTransaction(tId);
        
        return transactionPortal;
    }
    
    @GET
    @Path("getAllLogs/{key1}")
    @Produces(MediaType.APPLICATION_XML)
    public List<LoggerPortal> getAllWebLogger(@PathParam("key1") String logMachineType){
        
        List<LoggerPortal> loggerPortal=webPortalEjb.getAllLogger(logMachineType);
        
        return loggerPortal;
    }
    
    
//***************************************************************** CREATE **********************************************************
    
    
    
    
    @POST
    @Path("createbranch")
    @Produces(MediaType.APPLICATION_XML)
    public void createBranch(InputStream is) {
        
        BranchCreate bc=(BranchCreate) commonLibrary.unmarshalling(is, BranchCreate.class);
        webPortalEjb.createBranch(bc);
        
        
    }
    
    @POST
    @Path("createdevice")
    @Produces(MediaType.APPLICATION_XML)
    public void createDevice(InputStream is) {
        
        DeviceCreate dc=(DeviceCreate) commonLibrary.unmarshalling(is, DeviceCreate.class);
        webPortalEjb.createDevice(dc);
        
        
    }
    
    @POST
    @Path("createuser")
    @Produces(MediaType.APPLICATION_XML)
    public void createUser(InputStream is) {
        
        UserCreate uc=(UserCreate) commonLibrary.unmarshalling(is, UserCreate.class);
        webPortalEjb.createUser(uc);
        
        
    }
    
    @POST
    @Path("createproduct")
    @Produces(MediaType.APPLICATION_XML)
    public void createProduct(InputStream is) {
        
        ProductCreate pc=(ProductCreate) commonLibrary.unmarshalling(is, ProductCreate.class);
        webPortalEjb.createProduct(pc);
        
        
    }
    
    
    
    
    
//***************************************************************** EDIT **********************************************************
    
    
    @POST
    @Path("editbranch")
    @Produces(MediaType.APPLICATION_XML)
    public void editBranch(InputStream is) {
        
        BranchEdit be=(BranchEdit) commonLibrary.unmarshalling(is, BranchEdit.class);
        webPortalEjb.editBranch(be);
        
        
    }
    
    @POST
    @Path("editdevice")
    @Produces(MediaType.APPLICATION_XML)
    public void editDevice(InputStream is) {
        
        DeviceEdit de=(DeviceEdit) commonLibrary.unmarshalling(is, DeviceEdit.class);
        webPortalEjb.editDevice(de);
        
    }
    
    @POST
    @Path("edituser")
    @Produces(MediaType.APPLICATION_XML)
    public void editUser(InputStream is) {
        
        UserEdit ue=(UserEdit) commonLibrary.unmarshalling(is, UserEdit.class);
        webPortalEjb.editUser(ue);
        
        
    }
    
    @POST
    @Path("editproduct")
    @Produces(MediaType.APPLICATION_XML)
    public void editProduct(InputStream is) {
        
        ProductEdit pe=(ProductEdit) commonLibrary.unmarshalling(is, ProductEdit.class);
        webPortalEjb.editProduct(pe);
        
        
    }
    
    
    
    
//***************************************************************** DELETE **********************************************************
    
    
    
    @POST
    @Path("deactivebranch")
    @Produces(MediaType.APPLICATION_XML)
    public void deactiveBranch(InputStream is){
        
        BranchDeactive bd=(BranchDeactive) commonLibrary.unmarshalling(is, BranchDeactive.class);
        webPortalEjb.deactiveBranch(bd);
        
    }
    
    @POST
    @Path("deactivedevice")
    @Produces(MediaType.APPLICATION_XML)
    public void deactiveDevice(InputStream is){
        
        DeviceDeactive dd=(DeviceDeactive) commonLibrary.unmarshalling(is, DeviceDeactive.class);
        webPortalEjb.deactiveDevice(dd);
        
    }
    
    @POST
    @Path("deactiveuser")
    @Produces(MediaType.APPLICATION_XML)
    public void deactiveUser(InputStream is){
        
        UserDeactive ud=(UserDeactive) commonLibrary.unmarshalling(is, UserDeactive.class);
        webPortalEjb.deactiveUser(ud);
        
    }
    
    @POST
    @Path("deactiveproduct")
    @Produces(MediaType.APPLICATION_XML)
    public void deactiveProduct(InputStream is){
        
        ProductDeactive pd=(ProductDeactive) commonLibrary.unmarshalling(is, ProductDeactive.class);
        webPortalEjb.deactiveProduct(pd);
        
    }
    
    
    
    
    //************************************************************ ACTIVE ****************************************************************
    
    @POST
    @Path("activebranch")
    @Produces(MediaType.APPLICATION_XML)
    public void activeBranch(InputStream is){
        
        BranchDeactive bd=(BranchDeactive) commonLibrary.unmarshalling(is, BranchDeactive.class);
        webPortalEjb.activeBranch(bd);
        
    }
    
    @POST
    @Path("activedevice")
    @Produces(MediaType.APPLICATION_XML)
    public void activeDevice(InputStream is){
        
        DeviceDeactive dd=(DeviceDeactive) commonLibrary.unmarshalling(is, DeviceDeactive.class);
        webPortalEjb.activeDevice(dd);
        
    }
    
    @POST
    @Path("activeuser")
    @Produces(MediaType.APPLICATION_XML)
    public void activeUser(InputStream is){
        
        UserDeactive ud=(UserDeactive) commonLibrary.unmarshalling(is, UserDeactive.class);
        webPortalEjb.activeUser(ud);
        
    }
    
    @POST
    @Path("activeproduct")
    @Produces(MediaType.APPLICATION_XML)
    public void activeProduct(InputStream is){
        
        ProductDeactive pd=(ProductDeactive) commonLibrary.unmarshalling(is, ProductDeactive.class);
        webPortalEjb.activeProduct(pd);
        
    }
    
    
}
