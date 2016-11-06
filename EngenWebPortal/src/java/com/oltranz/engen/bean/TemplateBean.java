/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.CommonLibrary;
import com.oltranz.engen.model.ActionList;
import com.oltranz.engen.model.BranchList;
import com.oltranz.engen.model.DeviceList;
import com.oltranz.engen.model.PaymentModel;
import com.oltranz.engen.model.PaymentModeList;
import com.oltranz.engen.model.ProductList;
import com.oltranz.engen.model.UserList;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author John
 */
@ManagedBean(name="TemplateBean")
@SessionScoped
public class TemplateBean implements Serializable{
    
    
    //Menu variables
    private String dashboardClassName;
    private String branchClassName;
    
    private String productsClassName;
    private String typeClassName;
    private String productClassName;
    private String branchproductClassName;
    
    private String goalClassName;
    private String transactionClassName;
    
    private String settingClassName;
    private String deviceClassName;
    private String tankClassName;
    private String pumpClassName;
    private String userClassName;
    private String roleClassName;
    private String logClassName;
    
    
    private String hideClassName;
    private String hideDash;
    private String hideDashHq;
    
    
    private PaymentModel paymentChartModel;
    
    private PaymentModeList paymentModeList;
    private ActionList actionList;
    private BranchList braList;
    private ProductList productList;
    private DeviceList deviceList;
    private UserList userList;
    
    private HttpSession session = SessionBean.getSession();
    
    public void paymentModes(){
        
        try{
            String url="http://localhost:8080/EngenPayFuel/PaymentModeManagementService/paymentmodes";
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            //System.out.println(response.getHeaders());
            String jsonResponse = response.readEntity(String.class);
            //System.out.println(jsonResponse);
            
            ObjectMapper mapper=new ObjectMapper();
            paymentModeList=(PaymentModeList)mapper.readValue(jsonResponse, PaymentModeList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void paymentModeChart(){
        
        try{
            int braId=(int) session.getAttribute("branchId");
            String url="http://localhost:8080/EngenPayFuel/ChartManagementService/paymentChart/"+braId;
            Response response = CommonLibrary.sendRESTRequest(url, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            paymentChartModel=(PaymentModel)mapper.readValue(jsonResponse, PaymentModel.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void actions(){
        
        try{
            String getUrl="http://localhost:8080/EngenPayFuel/ActionManagementService/actions";
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
            String jsonResponse = response.readEntity(String.class);
            
            ObjectMapper mapper=new ObjectMapper();
            actionList=(ActionList)mapper.readValue(jsonResponse, ActionList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void branchList(){
        try{
            int braId=(int) session.getAttribute("branchId");
            String getUrl="http://localhost:8080/EngenPayFuel/BranchManagementService/branches/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.TEXT_PLAIN, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            braList=(BranchList)mapper.readValue(jsonResponse, BranchList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void productList(){
        try{
            String getBranchUrl="http://localhost:8080/EngenPayFuel/ProductManagementService/products";
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            productList=(ProductList)mapper.readValue(jsonResponse, ProductList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void deviceList(){
        try{
            int braId=(int) session.getAttribute("branchId");
            String getUrl="http://localhost:8080/EngenPayFuel/DeviceManagementService/devices/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            deviceList=(DeviceList)mapper.readValue(jsonResponse, DeviceList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void userList(){
        try{
            int braId=(int) session.getAttribute("branchId");
            String getBranchUrl="http://localhost:8080/EngenPayFuel/UserManagementService/users/"+braId;
            Response response = CommonLibrary.sendRESTRequest(getBranchUrl, "empty data", MediaType.APPLICATION_JSON, "GET");
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper mapper=new ObjectMapper();
            userList=(UserList)mapper.readValue(jsonResponse, UserList.class);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    /**
     * @return the dashboardClassName
     */
    public String getDashboardClassName() {
        return dashboardClassName;
    }
    
    /**
     * @param dashboardClassName the dashboardClassName to set
     */
    public void setDashboardClassName(String dashboardClassName) {
        this.dashboardClassName = dashboardClassName;
    }
    
    /**
     * @return the branchClassName
     */
    public String getBranchClassName() {
        return branchClassName;
    }
    
    /**
     * @param branchClassName the branchClassName to set
     */
    public void setBranchClassName(String branchClassName) {
        this.branchClassName = branchClassName;
    }
    
    
    
    /**
     * @return the goalClassName
     */
    public String getGoalClassName() {
        return goalClassName;
    }
    
    /**
     * @param goalClassName the goalClassName to set
     */
    public void setGoalClassName(String goalClassName) {
        this.goalClassName = goalClassName;
    }
    
    /**
     * @return the transactionClassName
     */
    public String getTransactionClassName() {
        return transactionClassName;
    }
    
    /**
     * @param transactionClassName the transactionClassName to set
     */
    public void setTransactionClassName(String transactionClassName) {
        this.transactionClassName = transactionClassName;
    }
    
    /**
     * @return the settingClassName
     */
    public String getSettingClassName() {
        return settingClassName;
    }
    
    /**
     * @param settingClassName the settingClassName to set
     */
    public void setSettingClassName(String settingClassName) {
        this.settingClassName = settingClassName;
    }
    
    /**
     * @return the hideClassName
     */
    public String getHideClassName() {
        return hideClassName;
    }
    
    /**
     * @param hideClassName the hideClassName to set
     */
    public void setHideClassName(String hideClassName) {
        this.hideClassName = hideClassName;
    }
    
    /**
     * @return the hideDash
     */
    public String getHideDash() {
        return hideDash;
    }
    
    /**
     * @param hideDash the hideDash to set
     */
    public void setHideDash(String hideDash) {
        this.hideDash = hideDash;
    }
    
    /**
     * @return the hideDashHq
     */
    public String getHideDashHq() {
        return hideDashHq;
    }
    
    /**
     * @param hideDashHq the hideDashHq to set
     */
    public void setHideDashHq(String hideDashHq) {
        this.hideDashHq = hideDashHq;
    }
    
    /**
     * @return the deviceClassName
     */
    public String getDeviceClassName() {
        return deviceClassName;
    }
    
    /**
     * @param deviceClassName the deviceClassName to set
     */
    public void setDeviceClassName(String deviceClassName) {
        this.deviceClassName = deviceClassName;
    }
    
    /**
     * @return the tankClassName
     */
    public String getTankClassName() {
        return tankClassName;
    }
    
    /**
     * @param tankClassName the tankClassName to set
     */
    public void setTankClassName(String tankClassName) {
        this.tankClassName = tankClassName;
    }
    
    /**
     * @return the pumpClassName
     */
    public String getPumpClassName() {
        return pumpClassName;
    }
    
    /**
     * @param pumpClassName the pumpClassName to set
     */
    public void setPumpClassName(String pumpClassName) {
        this.pumpClassName = pumpClassName;
    }
    
    /**
     * @return the userClassName
     */
    public String getUserClassName() {
        return userClassName;
    }
    
    /**
     * @param userClassName the userClassName to set
     */
    public void setUserClassName(String userClassName) {
        this.userClassName = userClassName;
    }
    
    /**
     * @return the roleClassName
     */
    public String getRoleClassName() {
        return roleClassName;
    }
    
    /**
     * @param roleClassName the roleClassName to set
     */
    public void setRoleClassName(String roleClassName) {
        this.roleClassName = roleClassName;
    }
    
    /**
     * @return the logClassName
     */
    public String getLogClassName() {
        return logClassName;
    }
    
    /**
     * @param logClassName the logClassName to set
     */
    public void setLogClassName(String logClassName) {
        this.logClassName = logClassName;
    }
    
    /**
     * @return the productsClassName
     */
    public String getProductsClassName() {
        return productsClassName;
    }
    
    /**
     * @param productsClassName the productsClassName to set
     */
    public void setProductsClassName(String productsClassName) {
        this.productsClassName = productsClassName;
    }
    
    /**
     * @return the typeClassName
     */
    public String getTypeClassName() {
        return typeClassName;
    }
    
    /**
     * @param typeClassName the typeClassName to set
     */
    public void setTypeClassName(String typeClassName) {
        this.typeClassName = typeClassName;
    }
    
    /**
     * @return the productClassName
     */
    public String getProductClassName() {
        return productClassName;
    }
    
    /**
     * @param productClassName the productClassName to set
     */
    public void setProductClassName(String productClassName) {
        this.productClassName = productClassName;
    }
    
    /**
     * @return the branchproductClassName
     */
    public String getBranchproductClassName() {
        return branchproductClassName;
    }
    
    /**
     * @param branchproductClassName the branchproductClassName to set
     */
    public void setBranchproductClassName(String branchproductClassName) {
        this.branchproductClassName = branchproductClassName;
    }
    
    /**
     * @return the paymentModeList
     */
    public PaymentModeList getPaymentModeList() {
        return paymentModeList;
    }
    
    /**
     * @param paymentModeList the paymentModeList to set
     */
    public void setPaymentModeList(PaymentModeList paymentModeList) {
        this.paymentModeList = paymentModeList;
    }
    
    /**
     * @return the paymentChartModel
     */
    public PaymentModel getPaymentChartModel() {
        return paymentChartModel;
    }
    
    /**
     * @param paymentChartModel the paymentChartModel to set
     */
    public void setPaymentChartModel(PaymentModel paymentChartModel) {
        this.paymentChartModel = paymentChartModel;
    }
    
    /**
     * @return the actionList
     */
    public ActionList getActionList() {
        return actionList;
    }
    
    /**
     * @param actionList the actionList to set
     */
    public void setActionList(ActionList actionList) {
        this.actionList = actionList;
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
     * @return the deviceList
     */
    public DeviceList getDeviceList() {
        return deviceList;
    }
    
    /**
     * @param deviceList the deviceList to set
     */
    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }
    
    /**
     * @return the userList
     */
    public UserList getUserList() {
        return userList;
    }
    
    /**
     * @param userList the userList to set
     */
    public void setUserList(UserList userList) {
        this.userList = userList;
    }
    
    /**
     * @return the braList
     */
    public BranchList getBraList() {
        return braList;
    }
    
    /**
     * @param braList the braList to set
     */
    public void setBraList(BranchList braList) {
        this.braList = braList;
    }
    
    
    
}