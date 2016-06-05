/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ws;

import beans.BranchIp;
import beans.LoginIp;
import beans.LogoutIp;
import beans.PaymentIp;
import beans.PumpDataIp;
import beans.SaleIp;
import beans.SaleIpCmp;
import beans.SpReq;
import beans.TraIp;
import beans.VoucherIp;
import ejb.AndroidDataManager;
import ejb.CommonLibrary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author JOHN
 */

@Stateless
@Path("androiddata")
public class AndroWebService {
    
    @EJB
    private AndroidDataManager androidDataManager;
    
    @EJB
    private CommonLibrary commonLibrary;
    
//posting the android json login pindata as inputstream and return login opdata
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkLogin(InputStream is) throws Exception{
        
        String androjson="";
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(LoginIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.createLogin(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return androjson;
        
    }

    
    
//posting the android json login pindata as inputstream and return login opdata
    @POST
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkLogout(InputStream is) throws Exception{
        
        String androjson="";
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(LogoutIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.createLogout(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return androjson;
        
    }


//posting the android json pumpipdata as inputstream and return pumplist as pump data op
    @POST
    @Path("listPump")
    @Produces(MediaType.APPLICATION_JSON)
    public String listPump(InputStream is) throws Exception{
        
        String androjson="";
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(PumpDataIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.listPump(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return androjson;
        
    }
    


//posting the android json branchiddata as inputstream and return productlist
    @POST
    @Path("listProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public String listProduct(InputStream is) throws Exception{
        
        String androjson="";
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(BranchIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.listProduct(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return androjson;
        
    }
    
  //posting the android json branchiddata as inputstream and return productlist
    @POST
    @Path("barCodeMoney")
    @Produces(MediaType.APPLICATION_JSON)
    public String barCodeMoney(InputStream is) throws Exception{
        
        String androjson="";
        
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(VoucherIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.barCodeMoney(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        return androjson;
        
    }  
    


//posting the android json sale data as inputstream and return transaction id
    @POST
    @Path("getTraData")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTraId(InputStream is) throws Exception{
        
        String androjson="";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(SaleIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.getSaleData(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return androjson;
        
    }
    
//make payment
    @POST
    @Path("getPaymentData")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPaymentData(InputStream is) throws Exception{
        
        String androjson="";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(PaymentIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.getPaymentData(jsondata);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return androjson;
        
    }






//posting the android json sale data as inputstream and return transaction id
    @POST
    @Path("getCmpnyTraData")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCmpTraId(InputStream is) throws Exception{
        
        String androjson="";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(SaleIpCmp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.getCmpSaleData(jsondata);
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return androjson;
        
    }
    
    


//get completed transaction details by its transaction id for android machine which requesting transaction id details in regular interval
    @POST
    @Path("getTraDataById")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTraDataById(InputStream is) throws Exception{
        
        String androjson="";
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try{
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String jsondata = sb.toString();
            JAXBContext jc = JAXBContext.newInstance(TraIp.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            androjson=androidDataManager.getTraDataById(jsondata);
        }
        
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return androjson;
        
    }
    
    


//service provison conformation link, after  mobile money payment the payment gateway post payment status on this link
    @POST
    @Path("spc")
    public void serviceProvisonConfirmation(InputStream is) throws Exception{
        
            SpReq sr=(SpReq) commonLibrary.unmarshalling(is, SpReq.class);
            androidDataManager.serviceProvisonConfirmation(sr);
    }
    
}
