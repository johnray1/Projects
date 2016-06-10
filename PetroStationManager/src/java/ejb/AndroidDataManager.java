/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;

import beans.BranchIp;
import beans.LoginIp;
import beans.LoginOp;
import beans.LogoutIp;
import beans.LogoutOp;
import beans.PaymentInvalidOp;
import beans.PaymentIp;
import beans.PaymentOp;
import beans.ProductDataOp;
import beans.PumpDataIp;
import beans.PumpDataOp;
import beans.SaleInvalidOp;
import beans.SaleInvalidOpCmp;
import beans.SaleIp;
import beans.SaleIpCmp;
import beans.SaleOp;
import beans.SaleOpCmp;
import beans.SpReq;
import beans.TraIp;
import beans.VoucherIp;
import beans.VoucherOp;
import entities.User;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Branch;
import entities.Customer;
import entities.Device;
import entities.Logger;
import entities.Product;
import entities.Pump;
import entities.PumpTransaction;
import entities.SpPaymentRes;
import entities.Transaction;
import entities.Voucher;
import entitiesFacade.CustomerFacade;
import entitiesFacade.DeviceFacade;
import entitiesFacade.PumpFacade;
import entitiesFacade.PumpTransactionFacade;
import entitiesFacade.SpPaymentResFacade;
import entitiesFacade.TransactionFacade;
import entitiesFacade.UserFacade;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




/**
 *
 * @author JOHN
 */
@Stateless
public class AndroidDataManager {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @EJB
    private DeviceFacade deviceFacade;
    @EJB
    private CustomerFacade customerFacade;
    @EJB
    private TransactionFacade transactionFacade;
    @EJB
    private SpPaymentResFacade spPaymentResFacade;
    @EJB
    private PumpFacade pumpFacade;
    @EJB
    private PumpTransactionFacade pumpTransactionFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private CommonLibrary commonLibrary;
    
    
//*************************************************** LOGIN ********************************************************************************************
    
    //Get the login pin and return the valid user data
    
    public String createLogin(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            LoginIp login= mapper.readValue(ipjson, LoginIp.class);
            String json=isPinImeiSameBranchId(login.getPin(),login.getImei());
            return json;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String isPinImeiSameBranchId(String ippin,String ipimei)throws Exception{
        
        int x=isPinAvailable(ippin);//user branch id
        int y=isImeiAvailable(ipimei);//device branch id
        
        if(x==y && (x!=0&&y!=0)){
            
            User u=new User();
            List<User> ul=(List<User>)entityManager.createQuery("SELECT u FROM User u WHERE u.uPin = :uPin and u.bId.bId = :bId").setParameter("uPin", ippin).setParameter("bId", x).setParameter("bId", y).getResultList();
            Iterator i=ul.iterator();
            while(i.hasNext())
            {
                u=(User) i.next();
                
            }
            String m=u.getWebStatus();
            String n=null;
            if((m==n)||(!u.getWebStatus().equalsIgnoreCase("LOGIN"))||(u.getWebStatus().equalsIgnoreCase("LOGIN"))){
                
                u.setWebStatus("LOGIN");
                userFacade.edit(u);
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dt=dateFormat.format(date);
                Date dtt=dateFormat.parse(dt);
                
                Logger lgr=new Logger();
                lgr.setEmail(u.getEmail());
                lgr.setFirstName(u.getFname());
                lgr.setLastName(u.getLname());
                lgr.setLoggerName(u.getUName());
                lgr.setLoggerType(u.getUType());
                lgr.setLoginTime(dtt);
                lgr.setPhoneNo(u.getUNo());
                lgr.setLogMachineType("pos");
                entityManager.persist(lgr);
                
                
                
                LoginOp lo=new LoginOp();
                lo.setValidation("valid");
                lo.setuId(u.getUId().toString());
                lo.setuName(u.getUName());
                lo.setuType(u.getUType());
                lo.setuStatus(u.getUStatus());
                lo.setbId(u.getBId().getBId().toString());
                lo.setbName(u.getBId().getBName());
//          lo.setPumplist(pdo);
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(lo);
                
                return jsonInString;
            }
            else{
                String s="not found";
                LoginOp lo=new LoginOp();
                lo.setValidation("invalid");
                lo.setuId(s);
                lo.setuName(s);
                lo.setuType(s);
                lo.setuStatus(s);
                lo.setbId(s);
                lo.setbName(s);
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(lo);
                
                return jsonInString;
                
            }
        }
        else{
            String s="not found";
            LoginOp lo=new LoginOp();
            lo.setValidation("invalid");
            lo.setuId(s);
            lo.setuName(s);
            lo.setuType(s);
            lo.setuStatus(s);
            lo.setbId(s);
            lo.setbName(s);
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(lo);
            
            return jsonInString;
        }
    }
    
    public int isPinAvailable(String ippin)throws Exception{
        String status="ACTIVE";
        User u=new User();
        List<User> ul=(List<User>)entityManager.createQuery("SELECT u FROM User u WHERE u.uPin = :uPin and u.uStatus = :uStatus").setParameter("uPin", ippin).setParameter("uStatus", status).getResultList();
        if(!ul.isEmpty())
        {
            Iterator i=ul.iterator();
            while(i.hasNext()){
                u=(User) i.next();
            }
            if((u.getUPin().equals(ippin))&& ((u.getUPin()!= null))||(!u.getUPin().isEmpty())){
                return u.getBId().getBId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public int isImeiAvailable(String ipimei)throws Exception{
        String status="ACTIVE";
        Device d=new Device();
        List<Device> dl=(List<Device>)entityManager.createQuery("SELECT d FROM Device d WHERE d.imeiNo = :imeiNo and d.dStatus = :dStatus").setParameter("imeiNo", ipimei).setParameter("dStatus", status).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getImeiNo().equals(ipimei))&& ((d.getImeiNo()!= null))||(!d.getImeiNo().isEmpty())){
                return d.getBId().getBId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    
//*************************************************** LOGOUT ***************************************************************************
    
    
    public String createLogout(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            LogoutIp logout= mapper.readValue(ipjson, LogoutIp.class);
            String json=isUserIdAvailable(logout.getuId(),logout.getAction());
            return json;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String isUserIdAvailable(String userId,String action)throws Exception{
        
        int uId=Integer.parseInt(userId);
        User u=new User();
        List<User> ul=(List<User>)entityManager.createQuery("SELECT u FROM User u WHERE u.uId = :uId").setParameter("uId", uId).getResultList();
        if(!ul.isEmpty()){
            Iterator i=ul.iterator();
            while(i.hasNext())
            {
                u=(User) i.next();
                
            }
            if(action.equalsIgnoreCase("LOGOUT")){
                
                u.setWebStatus("LOGOUT");
                userFacade.edit(u);
                entityManager.flush();
                String webStatus=u.getWebStatus();
                
                LogoutOp lo=new LogoutOp();
                lo.setuId(u.getUId().toString());
                lo.setuName(u.getUName());
                lo.setWebStatus("LOGOUT_SUCCESS");
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(lo);
                
                return jsonInString;
            }
            else{
                LogoutOp lo=new LogoutOp();
                lo.setuId(u.getUId().toString());
                lo.setuName(u.getUName());
                lo.setWebStatus("LOGOUT_FAILED");
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(lo);
                
                return jsonInString;
            }
        }
        else{
            LogoutOp lo=new LogoutOp();
            lo.setuId(userId+"_"+"invalid");
            lo.setuName("invalid");
            lo.setWebStatus("LOGOUT_FAILED");
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(lo);
            
            return jsonInString;
        }
    }
    
    
    
//*************************************************** PUMP LIST AND INDEX VALIDATION ********************************************************************************************
    
//Get the user id and imei  and return the valid pump list
    
    public String listPump(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            PumpDataIp pdi= mapper.readValue(ipjson, PumpDataIp.class);
            String json=getValidPumpList(pdi.getImei());
            return json;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String getValidPumpList(String ipimei)throws Exception{
        
        String status="ACTIVE";
        String jsonInString="";
        
        int bId=getDeviceBranchId(ipimei);//device branch id
        
        List<PumpDataOp> pdo=new ArrayList<PumpDataOp>();
        
        Pump p=new Pump();
        List<Pump> pl=(List<Pump>)entityManager.createQuery("SELECT p FROM Pump p WHERE p.bId.bId = :bId and p.bId.bStatus = :bStatus").setParameter("bId", bId).setParameter("bStatus", status).getResultList();
        if(!pl.isEmpty()){
            Iterator i=pl.iterator();
            while(i.hasNext())
            {
                PumpDataOp pd=new PumpDataOp();
                p=(Pump) i.next();
                if( (p.getBId().getBId() == bId) && (p.getBId().getBId()!= null) )
                {
                    pd.setPumpId(p.getPuId().toString());
                    pd.setPumpName(p.getPuName());
                    pd.setIndex(p.getPuIndex());
                    pdo.add(pd);
                    
                    
                    ObjectMapper mapper = new ObjectMapper();
                    jsonInString=mapper.writeValueAsString(pdo);
                }
            }
            return jsonInString;
        }
        else{
            String s="not found";
            PumpDataOp pd=new PumpDataOp();
            pd.setPumpId(s);
            pd.setPumpName(s);
            pd.setIndex(s);
            pdo.add(pd);
            
            
            ObjectMapper mapper = new ObjectMapper();
            jsonInString=mapper.writeValueAsString(pdo);
            
            return jsonInString;
        }
    }
    
    public int getDeviceBranchId(String ipimei)throws Exception{
        
        String status="ACTIVE";
        Device d=new Device();
        List<Device> dl=(List<Device>)entityManager.createQuery("SELECT d FROM Device d WHERE d.imeiNo = :imeiNo and d.dStatus = :dStatus").setParameter("imeiNo", ipimei).setParameter("dStatus", status).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getImeiNo().equals(ipimei))&& ((d.getImeiNo()!= null))||(!d.getImeiNo().isEmpty())){
                return d.getBId().getBId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    
    
//*************************************************** PRODUCT LIST  ********************************************************************************************
    
    //Get the branchId and return valid product list
    
    public String listProduct(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            BranchIp bp= mapper.readValue(ipjson, BranchIp.class);
            String json=isBidAvailable(bp.getbId());
            return json;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String isBidAvailable(String ipbId)throws Exception{
        
        String status="ACTIVE";
        String jsonInString="";
        
        int bId=Integer.parseInt(ipbId);
        
        List<ProductDataOp> pla=new ArrayList<ProductDataOp>();
        
        Product p=new Product();
        List<Product> pl=(List<Product>)entityManager.createQuery("SELECT p FROM Product p WHERE p.bId.bId = :bId and p.pStatus = :pStatus and p.bId.bStatus = :bStatus").setParameter("bId", bId).setParameter("pStatus", status).setParameter("bStatus", status).getResultList();
        if(!pl.isEmpty()){
            Iterator i=pl.iterator();
            while(i.hasNext())
            {
                ProductDataOp pd=new ProductDataOp();
                p=(Product) i.next();
                if( (p.getBId().getBId() == bId) && (p.getBId().getBId()!= null) )
                {
                    
                    pd.setpId(p.getProId().toString());
                    pd.setpName(p.getProName());
                    pd.setuPrice(p.getProUnityPrice());
                    pd.setUnity(p.getProMeasureUnity());
                    pla.add(pd);
                    
                    ObjectMapper mapper = new ObjectMapper();
                    jsonInString=mapper.writeValueAsString(pla);
                }
            }
            
            return jsonInString;
        }
        else{
            String s="not found";
            ProductDataOp pd=new ProductDataOp();
            
            pd.setpId(s);
            pd.setpName(s);
            pd.setuPrice(s);
            pd.setUnity(s);
            pla.add(pd);
            
            ObjectMapper mapper = new ObjectMapper();
            jsonInString=mapper.writeValueAsString(pla);
            
            return jsonInString;
        }
    }
    
//******************************************************* GET BARCODE TO RETURN MOBILE MONEY******************************************************************************
    
    public String barCodeMoney(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            VoucherIp vp= mapper.readValue(ipjson, VoucherIp.class);
            String json=getValidBarCodeMoney(vp.getuId(),vp.getImei(),vp.getBarnumber(),vp.getBarformat());
            return json;
        }
        catch(Exception e){
            return null;
        }
    }
    
    public String getValidBarCodeMoney(String uId,String imei,String barNumber,String barFormat)throws Exception{
        
        int x=getUserBranchId(uId);//user branch id
        int y=getPosBranchId(imei);//device branch id
        
        int j=getBarNoVoucherId(barNumber);//voucher id
        int k=getBarFormatVoucherId(barFormat);//voucher id
        
        if(x==y && (x!=0&&y!=0)){
            Voucher v=new Voucher();
            List<Voucher> vl=(List<Voucher>)entityManager.createQuery("SELECT v FROM Voucher v WHERE v.vNo = :vNo and v.vFormat = :vFormat").setParameter("vNo", barNumber).setParameter("vFormat", barFormat).getResultList();
            Iterator i=vl.iterator();
            while(i.hasNext())
            {
                v=(Voucher) i.next();
            }
            
            VoucherOp vo=new VoucherOp();
            vo.setBarnumber(v.getVNo());
            vo.setBaramount(v.getVAmount().toString());
            vo.setValidation("valid");
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(vo);
            
            return jsonInString;
            
        }
        
        else{
//            VoucherOp vo=new VoucherOp();
//            vo.setBarnumber(barNumber);
//            vo.setBaramount("invalid");
//            vo.setValidation("invalid");
            
            Voucher v=new Voucher();
            List<Voucher> vl=(List<Voucher>)entityManager.createQuery("SELECT v FROM Voucher v WHERE v.vNo = :vNo and v.vFormat = :vFormat").setParameter("vNo", barNumber).setParameter("vFormat", barFormat).getResultList();
            Iterator i=vl.iterator();
            while(i.hasNext())
            {
                v=(Voucher) i.next();
            }
            
            VoucherOp vo=new VoucherOp();
            vo.setBarnumber(v.getVNo());
            vo.setBaramount(v.getVAmount().toString());
            vo.setValidation("valid");
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(vo);
            
            return jsonInString;
        }
        
    }
    
    public int getUserBranchId(String ipuid)throws Exception{
        
        int uId=Integer.parseInt(ipuid);
        User u=new User();
        List<User> ul=(List<User>)entityManager.createNamedQuery("User.findByUId").setParameter("uId", uId).getResultList();
        if(!ul.isEmpty())
        {
            Iterator i=ul.iterator();
            while(i.hasNext()){
                u=(User) i.next();
            }
            if( (u.getUId()==uId) && (u.getUId()!= 0) ){
                return u.getBId().getBId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public int getPosBranchId(String ipimei)throws Exception{
        
        Device d=new Device();
        List<Device> dl=(List<Device>)entityManager.createNamedQuery("Device.findByImeiNo").setParameter("imeiNo", ipimei).getResultList();
        if(!dl.isEmpty())
        {
            Iterator i=dl.iterator();
            while(i.hasNext()){
                d=(Device) i.next();
            }
            if((d.getImeiNo().equals(ipimei))&& ((d.getImeiNo()!= null))||(!d.getImeiNo().isEmpty())){
                return d.getBId().getBId();
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    public int getBarNoVoucherId(String barNumber){
        
        
        return 0;
    }
    
    public int getBarFormatVoucherId(String barFormat){
        
        
        return 0;
    }
    
    
    
//******************************************************** SALE PROCESS START**********************************************************************************************
    
    
//Get Personal sale data
    
    public String getSaleData(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            SaleIp si= mapper.readValue(ipjson, SaleIp.class);
            
            int pumpId=Integer.parseInt(si.getPumpId());
            
            int proId=Integer.parseInt(si.getpId());
            
            double amt=Double.parseDouble(si.getAmount());
            
            double quantity=Double.parseDouble(si.getQuantity());
            
            
            String pMode=si.getpMode();
            
            String custTel=si.getCustTel();
            
            int userId=Integer.parseInt(si.getuId());
            
            int bId=Integer.parseInt(si.getbId());
            
            String imei=si.getImei();
            
            String pStatus="pending";
            
            String valid="valid";
            String invalid="invalid";
            
            //check if branchId is available
            int branchId=isBranchAvailable(bId);
            
            if(branchId != 0){
                String json= getSaleDataOp(branchId,pumpId,proId,userId,amt,quantity,pMode,custTel,imei,valid,pStatus);
                return json;
            }
            else{
                String json=getSaleDataInvalidOp(bId,userId,amt,pMode,custTel,imei,invalid);
                return json;
            }
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public String getSaleDataOp(int bId,int pumpId,int pId,int uId,double amt,double quantity,String pMode,String custTel,String imei,String valid,String pStatus)throws Exception{
        
        String pumpIndex=getPumpIndex(pumpId);
        
        String pumpName=getPumpName(pumpId);
        
        String proName=getProductName(pId);
        
        String userName=getUserName(uId);
        
        int deviceId=isDevicePersistable(bId,imei);
        
        int custId=isCustomerPersistable(bId,custTel);
        
        int traId=isTransactionPersistable(bId,uId,pumpId,pId,deviceId,custId,quantity,amt,pMode,pStatus,pumpName,pumpIndex,proName,userName);
        
        SaleOp so=new SaleOp();
        so.setValidation(valid);
        so.setTraId(Integer.toString(traId));
        so.setpStatus(pStatus);
        so.setPumpName(pumpName);
        so.setpName(proName);
        so.setAmount(Double.toString(amt));
        so.setQuantity(Double.toString(quantity));
        so.setpMode(pMode);
        so.setCustTel(custTel);
        so.setuId(Integer.toString(uId));
        so.setbId(Integer.toString(bId));
        so.setImei(imei);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString=mapper.writeValueAsString(so);
        
        return jsonInString;
    }
    
    public String getSaleDataInvalidOp(int bId,int uId,double amt,String pMode,String custTel,String imei,String invalid)throws Exception{
        
        SaleInvalidOp so=new SaleInvalidOp();
        so.setValidation(invalid);
        so.setTraId("notFound");
        so.setpStatus("failure");
        so.setPumpName("notFound");
        so.setpName("notFound");
        so.setAmount(Double.toString(amt));
        so.setQuantity("notFound");
        so.setpMode(pMode);
        so.setCustTel(custTel);
        so.setuId(Integer.toString(uId));
        so.setbId(Integer.toString(bId));
        so.setImei(imei);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString=mapper.writeValueAsString(so);
        
        return jsonInString;
    }
    
    public int isCustomerPersistable(int bId, String tel)throws Exception{
        
        Branch b2=new Branch();
        b2.setBId(bId);
        Customer c=new Customer();
        c.setCustTel(tel);
        c.setBId(b2);
        customerFacade.create(c);
        entityManager.flush();
        int cId=c.getCustId();
        
        return cId;
    }
    
    
    
//Commom Function Used By Personal Sale And Company Sale
    
    public int isBranchAvailable(int bId)throws Exception{
        Branch b=new Branch();
        List<Branch> bl=(List<Branch>)entityManager.createNamedQuery("Branch.findByBId").setParameter("bId", bId).getResultList();
        if(!bl.isEmpty()){
            Iterator i=bl.iterator();
            while(i.hasNext()){
                b=(Branch) i.next();
            }
            if(b.getBId().equals(bId)){
                
                int braId=b.getBId();
                return braId;
            }
        }
        return 0;
        
    }
    
    public String getPumpName(int puId)throws Exception{
        
        Pump pump=(Pump) entityManager.createNamedQuery("Pump.findByPuId").setParameter("puId", puId).getSingleResult();
        
        return pump.getPuName();
        
    }
    
    public String getPumpIndex(int puId)throws Exception{
        
        Pump pump=(Pump) entityManager.createNamedQuery("Pump.findByPuId").setParameter("puId", puId).getSingleResult();
        
        return pump.getPuIndex();
        
    }
    
    public String setPumpCurrentIndex(int puId,String currIndex)throws Exception{
        
        Pump pump=(Pump) entityManager.createNamedQuery("Pump.findByPuId").setParameter("puId", puId).getSingleResult();
        pump.setPuIndex(currIndex);
        pumpFacade.edit(pump);
        entityManager.flush();
        
        return pump.getPuIndex();
        
    }
    
    public String getProductName(int proId)throws Exception{
        
        Product p=(Product) entityManager.createNamedQuery("Product.findByProId").setParameter("proId", proId).getSingleResult();
        
        return p.getProName();
        
    }
    
    public String getUserName(int uId)throws Exception{
        
        User u=(User) entityManager.createNamedQuery("User.findByUId").setParameter("uId", uId).getSingleResult();
        
        return u.getUName();
        
    }
    
    public int isDevicePersistable(int bId, String imeiNo)throws Exception{
        
        List<Device> dl=(List<Device>)entityManager.createNamedQuery("Device.findByImeiNo").setParameter("imeiNo", imeiNo).getResultList();
        Iterator i=dl.iterator();
        Device d=new Device();
        while(i.hasNext()){
            d=(Device) i.next();
        }
        if(d.getImeiNo()==null ){
            Branch b1=new Branch();
            b1.setBId(bId);
            d.setImeiNo(imeiNo);
            d.setBId(b1);
            deviceFacade.create(d);
            entityManager.flush();
            int dId=d.getDId();
            
            return dId;
        }
        else{
            return d.getDId();
        }
        
    }
    
    public int isTransactionPersistable(int branchId,int userId,int puId,int proId,int deviceId,int custId,double quantity,double amt,String pMode,String pStatus,String pumpName,String pumpIndex,String proName,String userName)throws Exception{
        
        Pump pump=new Pump();
        pump.setPuId(puId);
        
        Product p=new Product();
        p.setProId(proId);
        
        User u1=new User();
        u1.setUId(userId);
        
        Branch b=new Branch();
        b.setBId(branchId);
        
        Device d=new Device();
        d.setDId(deviceId);
        
        Customer c=new Customer();
        c.setCustId(custId);
        
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dt=dateFormat.format(date);
        Date dtt=dateFormat.parse(dt);
        
        Transaction t=new Transaction();
        t.setBId(b);
        t.setUId(u1);
        t.setPuId(pump);
        t.setProId(p);
        t.setDId(d);
        t.setCustId(c);
        t.setQuantity(quantity);
        t.setAmount(amt);
        t.setPaymentMode(pMode);
        t.setPaymentStatus("pending");
        t.setReqDatetime(dtt);
        transactionFacade.create(t);
        entityManager.flush();
        int traId=t.getTraId();
        
        return traId;
        
    }
    
    
    
    //get company Sale data
    
    public String getCmpSaleData(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            SaleIpCmp sic= mapper.readValue(ipjson, SaleIpCmp.class);
            
            int pumpId=Integer.parseInt(sic.getPumpId());
            
            int proId=Integer.parseInt(sic.getpId());
            
            double amt=Double.parseDouble(sic.getAmount());
            
            double quantity=Double.parseDouble(sic.getQuantity());
            
            String pMode=sic.getpMode();
            
            String custTel=sic.getCustTel();
            
            String cmpName=sic.getCmpName();
            
            String tin=sic.getTin();
            
            int userId=Integer.parseInt(sic.getuId());
            
            int bId=Integer.parseInt(sic.getbId());
            
            String imei=sic.getImei();
            
            String pStatus="pending";
            
            String valid="valid";
            String invalid="invalid";
            
            //check if branchId is available
            int branchId=isBranchAvailable(bId);
            
            if(branchId != 0){
                String json= getCmpSaleDataOp(branchId,pumpId,proId,userId,amt,quantity,pMode,custTel,cmpName,tin,imei,valid,pStatus);
                return json;
            }
            else{
                String json=getCmpSaleDataInvalidOp(bId,userId,amt,pMode,custTel,cmpName,tin,imei,invalid);
                return json;
            }
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public String getCmpSaleDataOp(int bId,int pumpId,int pId,int uId,double amt,double quantity,String pMode,String custTel,String cmpName,String tin,String imei,String valid,String pStatus)throws Exception{
        
        String pumpIndex=getPumpIndex(pumpId);
        
        String pumpName=getPumpName(pumpId);
        
        String proName=getProductName(pId);
        
        String userName=getUserName(uId);
        
        int deviceId=isDevicePersistable(bId,imei);
        
        int custId=isCmpCustomerPersistable(bId,custTel,cmpName,tin);
        
        int traId=isTransactionPersistable(bId,uId,pumpId,pId,deviceId,custId,quantity,amt,pMode,pStatus,pumpName,pumpIndex,proName,userName);
        
        SaleOpCmp soc=new SaleOpCmp();
        soc.setValidation(valid);
        soc.setTraId(Integer.toString(traId));
        soc.setpStatus(pStatus);
        soc.setPumpName(pumpName);
        soc.setpName(proName);
        soc.setAmount(Double.toString(amt));
        soc.setQuantity(Double.toString(quantity));
        soc.setpMode(pMode);
        soc.setCustTel(custTel);
        soc.setCmpName(cmpName);
        soc.setTin(tin);
        soc.setuId(Integer.toString(uId));
        soc.setbId(Integer.toString(bId));
        soc.setImei(imei);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString=mapper.writeValueAsString(soc);
        
        return jsonInString;
    }
    
    public String getCmpSaleDataInvalidOp(int bId,int uId,double amt,String pMode,String custTel,String cmpName,String tin,String imei,String invalid)throws Exception{
        
        SaleInvalidOpCmp soc=new SaleInvalidOpCmp();
        soc.setValidation(invalid);
        soc.setTraId("notFound");
        soc.setpStatus("failure");
        soc.setPumpName("notFound");
        soc.setpName("notFound");
        soc.setAmount(Double.toString(amt));
        soc.setQuantity("notFound");
        soc.setpMode(pMode);
        soc.setCustTel(custTel);
        soc.setCmpName(cmpName);
        soc.setTin(tin);
        soc.setuId(Integer.toString(uId));
        soc.setbId(Integer.toString(bId));
        soc.setImei(imei);
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString=mapper.writeValueAsString(soc);
        
        return jsonInString;
    }
    
    public int isCmpCustomerPersistable(int bId, String tel,String cmpName,String tin)throws Exception{
        
        Branch b2=new Branch();
        b2.setBId(bId);
        Customer c=new Customer();
        c.setCustName(cmpName);
        c.setCustTin(tin);
        c.setCustTel(tel);
        c.setBId(b2);
        customerFacade.create(c);
        entityManager.flush();
        int cId=c.getCustId();
        
        return cId;
    }
    
    
    
    
    
//************************************************** PAYMENT PROCESS START ******************************************************************************************************
    
    //payment data process
    
    public String getPaymentData(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            PaymentIp pi= mapper.readValue(ipjson, PaymentIp.class);
            
            String tId=pi.gettId();
            String pMode=pi.getpMode();
            String request=pi.getRequest();
            
            String json=proceedPaymentData(tId,pMode,request);
            
            return json;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public String proceedPaymentData(String traId,String pMode,String request)throws Exception{
//format the date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dt=dateFormat.format(date);
        Date dtt=dateFormat.parse(dt);
        
        int tId=Integer.parseInt(traId);
        Transaction t=new Transaction();
        List<Transaction> tl=(List<Transaction>)entityManager.createNamedQuery("Transaction.findByTraId").setParameter("traId", tId).getResultList();
//if condition start
        if(!tl.isEmpty()){
            Iterator i=tl.iterator();
            while(i.hasNext()){
                t=(Transaction) i.next();
            }
            
            //******************************************* PROCEED CASH PAYMENT ****************************************************************
            
            if( (pMode.equalsIgnoreCase("cash"))&&(request.equalsIgnoreCase("proceed"))&&(!request.equalsIgnoreCase("cancel"))&&(t.getPaymentMode().equals(pMode)) ){
                
                //updating transaction table
                t.setPaymentStatus("success");
                t.setResDatetime(dtt);
                transactionFacade.edit(t);
                entityManager.flush();
                
                //persisting pump transaction
                PumpTransaction pt=new PumpTransaction();
                pt.setTraId(t.getTraId());
                pt.setUName(t.getUId().getUName());
                pt.setProName(t.getProId().getProName());
                pt.setPuName(t.getPuId().getPuName());
                pt.setDatetime(dtt);
                pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                double currentIndex=puIndex+(t.getQuantity());
                String curIndex=String.valueOf(currentIndex);
                pt.setIndexafter(curIndex);//set pump after index
                pumpTransactionFacade.create(pt);
                entityManager.flush();
                
                String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                
                String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                
                
                
                //Setting android payment op data
                PaymentOp po=new PaymentOp();
                po.setAmount(t.getAmount().toString());
                po.setCustTel(t.getCustId().getCustTel());
                po.setImei(t.getDId().getImeiNo());
                po.setQuantity(t.getQuantity().toString());
                po.setTraId(t.getTraId().toString());
                po.setValidation("valid");
                po.setbId(t.getBId().getBId().toString());
                po.setpMode(t.getPaymentMode());
                po.setPumpName(t.getPuId().getPuName());
                po.setpName(t.getProId().getProName());
                po.setpStatus(t.getPaymentStatus());
                po.setuId(t.getUId().getUId().toString());
                po.setbName(t.getBId().getBName());
                //get updated datetime from database and persist
                SimpleDateFormat dformat = new SimpleDateFormat("MMM dd,yyyy HH:mm a");
                String opdate=dformat.format(t.getResDatetime());
                po.setpTime(opdate);
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(po);
                
                return jsonInString;
            }
            
            
            
            //****************************************** PROCEED VOUCHER PAYMENT ***********************************************************
            
            else if((pMode.equalsIgnoreCase("voucher"))&&(!pMode.equalsIgnoreCase("cash"))&&(!pMode.equalsIgnoreCase("mtn"))&&(!pMode.equalsIgnoreCase("tigo"))&&(!pMode.equalsIgnoreCase("airtel")) && (request.equalsIgnoreCase("proceed"))&&(!request.equalsIgnoreCase("cancel"))&&(t.getPaymentMode().equals(pMode))){
                
                t.setPaymentStatus("success");
                t.setResDatetime(dtt);
                transactionFacade.edit(t);
                entityManager.flush();
                
                //persisting pump transaction
                PumpTransaction pt=new PumpTransaction();
                pt.setTraId(t.getTraId());
                pt.setUName(t.getUId().getUName());
                pt.setProName(t.getProId().getProName());
                pt.setPuName(t.getPuId().getPuName());
                pt.setDatetime(dtt);
                pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                double currentIndex=puIndex+(t.getQuantity());
                String curIndex=String.valueOf(currentIndex);
                pt.setIndexafter(curIndex);//set pump after index
                pumpTransactionFacade.create(pt);
                entityManager.flush();
                
                String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                
                String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                
                
                
                //Setting android payment op data
                PaymentOp po=new PaymentOp();
                po.setAmount(t.getAmount().toString());
                po.setCustTel(t.getCustId().getCustTel());
                po.setImei(t.getDId().getImeiNo());
                po.setQuantity(t.getQuantity().toString());
                po.setTraId(t.getTraId().toString());
                po.setValidation("valid");
                po.setbId(t.getBId().getBId().toString());
                po.setpMode(t.getPaymentMode());
                po.setPumpName(t.getPuId().getPuName());
                po.setpName(t.getProId().getProName());
                po.setpStatus(t.getPaymentStatus());
                po.setuId(t.getUId().getUId().toString());
                po.setbName(t.getBId().getBName());
                //get updated datetime from database and persist
                SimpleDateFormat dformat = new SimpleDateFormat("MMM dd,yyyy HH:mm a");
                String opdate=dformat.format(t.getResDatetime());
                po.setpTime(opdate);
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(po);
                
                return jsonInString;
                
                
            }
            
            //****************************************** PROCEED OTHER PAYMENT ***********************************************************
            
            else if((!pMode.equalsIgnoreCase("cash"))&&(!pMode.equalsIgnoreCase("mtn"))&&(!pMode.equalsIgnoreCase("tigo"))&&(!pMode.equalsIgnoreCase("airtel")) && (request.equalsIgnoreCase("proceed"))&&(!request.equalsIgnoreCase("cancel"))&&(t.getPaymentMode().equals(pMode))){
                
                t.setPaymentStatus("success");
                t.setResDatetime(dtt);
                transactionFacade.edit(t);
                entityManager.flush();
                
                //persisting pump transaction
                PumpTransaction pt=new PumpTransaction();
                pt.setTraId(t.getTraId());
                pt.setUName(t.getUId().getUName());
                pt.setProName(t.getProId().getProName());
                pt.setPuName(t.getPuId().getPuName());
                pt.setDatetime(dtt);
                pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                double currentIndex=puIndex+(t.getQuantity());
                String curIndex=String.valueOf(currentIndex);
                pt.setIndexafter(curIndex);//set pump after index
                pumpTransactionFacade.create(pt);
                entityManager.flush();
                
                String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                
                String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                
                
                
                //Setting android payment op data
                PaymentOp po=new PaymentOp();
                po.setAmount(t.getAmount().toString());
                po.setCustTel(t.getCustId().getCustTel());
                po.setImei(t.getDId().getImeiNo());
                po.setQuantity(t.getQuantity().toString());
                po.setTraId(t.getTraId().toString());
                po.setValidation("valid");
                po.setbId(t.getBId().getBId().toString());
                po.setpMode(t.getPaymentMode());
                po.setPumpName(t.getPuId().getPuName());
                po.setpName(t.getProId().getProName());
                po.setpStatus(t.getPaymentStatus());
                po.setuId(t.getUId().getUId().toString());
                po.setbName(t.getBId().getBName());
                //get updated datetime from database and persist
                SimpleDateFormat dformat = new SimpleDateFormat("MMM dd,yyyy HH:mm a");
                String opdate=dformat.format(t.getResDatetime());
                po.setpTime(opdate);
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(po);
                
                return jsonInString;
                
                
            }
            
            
            //************************************* PROCEED MOBILE MONEY PAYMENT ************************************************************
            
            else if( (!pMode.equalsIgnoreCase("cash"))&&(!pMode.equalsIgnoreCase("voucher"))&&(request.equalsIgnoreCase("proceed"))&&(!request.equalsIgnoreCase("cancel"))&&(t.getPaymentMode().equals(pMode)) ){
                
                String ps="";
                
                if(pMode.equalsIgnoreCase("mtn")){
                    ps="2484";
                }
                else if(pMode.equalsIgnoreCase("tigo")){
                    ps="3382";
                }
                else if(pMode.equalsIgnoreCase("airtel")){
                    ps="5728";
                }
                
                int tranId=t.getTraId();
                String transId=t.getTraId().toString();
                String tel=t.getCustId().getCustTel();
                String amount=t.getAmount().toString();
                String payXmlData=sendPaymentXML(ps,transId,tel,amount);//this function is for posting data for mobile money request
                return null;
            }
            
            //******************************************* Cancel the transaction ***********************************************************
            
            else if(request.equalsIgnoreCase("cancel")){
                
                t.setPaymentStatus("cancel");
                t.setQuantity(0.0);
                t.setAmount(0.0);
                t.setResDatetime(dtt);
                transactionFacade.edit(t);
                entityManager.flush();
                
                //persisting pump transaction
                PumpTransaction pt=new PumpTransaction();
                pt.setTraId(t.getTraId());
                pt.setUName(t.getUId().getUName());
                pt.setProName(t.getProId().getProName());
                pt.setPuName(t.getPuId().getPuName());
                pt.setDatetime(dtt);
                pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                double currentIndex=puIndex+(t.getQuantity());
                String curIndex=String.valueOf(currentIndex);
                pt.setIndexafter(curIndex);//set pump after index
                pumpTransactionFacade.create(pt);
                entityManager.flush();
                
                String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                
                String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                
                
                
                //Setting android payment op data
                PaymentOp po=new PaymentOp();
                po.setAmount(t.getAmount().toString());
                po.setCustTel(t.getCustId().getCustTel());
                po.setImei(t.getDId().getImeiNo());
                po.setQuantity(t.getQuantity().toString());
                po.setTraId(t.getTraId().toString());
                po.setValidation("valid");
                po.setbId(t.getBId().getBId().toString());
                po.setpMode(t.getPaymentMode());
                po.setPumpName(t.getPuId().getPuName());
                po.setpName(t.getProId().getProName());
                po.setpStatus(t.getPaymentStatus());
                po.setuId(t.getUId().getUId().toString());
                po.setbName(t.getBId().getBName());
                SimpleDateFormat dformat = new SimpleDateFormat("MMM dd,yyyy HH:mm a");
                String opdate=dformat.format(t.getResDatetime());
                po.setpTime(opdate);
                
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(po);
                // out.print("ANDROID  SALE START: Transaction response Header:"+" | BODY:"+jsonInString);
                return jsonInString;
                
            }
            
        }
        //if condition end
        
        //*************************************************** ELSE WRONG PAYMENT REQUEST ****************************************************
        
        PaymentInvalidOp pio=new PaymentInvalidOp();
        pio.setValidation("invalid");
        pio.setTraId("notFound");
        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(pio);
        
        return jsonInString;
        
    }
    
    public String sendPaymentXML(String ps,String tId,String tel,String amount){
        
        String url="http://10.171.1.53/PaymentGateway/payments/paymentRequest";
        String xmlData= "<COMMAND>"
                + "<CONTRACTID>441001</CONTRACTID>"
                + "<PAYINGACCOUNTIDATSP>"+tel+"</PAYINGACCOUNTIDATSP>"
                +"<PAYMENTSPID>"+ps+"</PAYMENTSPID>"
                +"<DESCR>"+amount+" PAYMENT FOR ENGEN"+"</DESCR>"
                +"<TRANSID>"+tId+"</TRANSID>"
                +"<AMOUNT>"+amount+"</AMOUNT>"
                + "</COMMAND>";
        
        out.print("ANDROID  PAYMENT:"+xmlData);
        Response response=commonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        String xmldata=response.readEntity(String.class);
        out.print("ANDROID  PAYMENT: Payment response Header:"+response+" | BODY:"+xmldata);
        
        return xmldata;
        
    }
    
    
    
    //after you receive a payment completed post request from manzi your application will be required to send a service provision confirmation
    
    public void serviceProvisonConfirmation(SpReq sr)throws Exception{
        //format the date
        try{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String dt=dateFormat.format(date);
            Date dtt=dateFormat.parse(dt);
            
            //declared a global variable to get the transaction id and keep it to use in whole function
            int transId = 0;
            String transacId = "";
            String statusdesc="";
            String statuscode="";
            String contractid="";
            
            
            //Get the data here which manzi posted on my link after payment request completed and Persist data
            String contractId=sr.getCONTRACTID();
            String spTransId=sr.getSPTRANSID();
            String statusCode=sr.getSTATUSCODE();
            String statusDesc=sr.getSTATUSDESC();
            String traId=sr.getTRANSID();
            int tId=Integer.parseInt(traId);//transaction id a foreign key in Sp PaymentRes table
            Transaction tt=new Transaction();
            tt.setTraId(tId);
            
            
            //first check sppaymentresponse table
            SpPaymentRes spr=new SpPaymentRes();
            List<SpPaymentRes> sprl=entityManager.createNamedQuery("SpPaymentRes.findBySpTransactionId").setParameter("spTransactionId", spTransId).getResultList();
            if(!sprl.isEmpty()){
                Iterator i=sprl.iterator();
                while(i.hasNext()){
                    spr=(SpPaymentRes) i.next();
                    
                    spr.setContractId(contractId);
                    spr.setSpTransactionId(spTransId);
                    spr.setStatusCode(statusCode);
                    spr.setStatusDesc(statusDesc);
                    spr.setTransactionId(tt);
                    spPaymentResFacade.edit(spr);
                    entityManager.flush();
                    
                    transId=spr.getTransactionId().getTraId();
                    transacId=spr.getTransactionId().getTraId().toString();
                    statusdesc=spr.getStatusDesc();
                    statuscode=spr.getStatusCode();
                    contractid=spr.getContractId();
                }
            }
            else{
                spr.setContractId(contractId);
                spr.setStatusDesc(statusDesc);
                spr.setStatusCode(statusCode);
                spr.setSpTransactionId(spTransId);
                spr.setTransactionId(tt);
                spPaymentResFacade.create(spr);
                entityManager.flush();
                
                transId=spr.getTransactionId().getTraId();
                transacId=spr.getTransactionId().getTraId().toString();
                statusdesc=spr.getStatusDesc();
                statuscode=spr.getStatusCode();
                contractid=spr.getContractId();
            }
            
            //After Persist We Update Our Transaction Table
            
            Transaction t=new Transaction();
            List<Transaction> tl=(List<Transaction>)entityManager.createNamedQuery("Transaction.findByTraId").setParameter("traId", transId).getResultList();
            if(!tl.isEmpty()){
                Iterator i=tl.iterator();
                while(i.hasNext()){
                    t=(Transaction) i.next();
                }
                if(spr.getStatusCode().equalsIgnoreCase("100")){
                    
                    t.setPaymentStatus("success");
                    t.setResDatetime(dtt);
                    transactionFacade.edit(t);
                    entityManager.flush();
                    
                    
                    //persisting pump transaction
                    PumpTransaction pt=new PumpTransaction();
                    pt.setTraId(t.getTraId());
                    pt.setUName(t.getUId().getUName());
                    pt.setProName(t.getProId().getProName());
                    pt.setPuName(t.getPuId().getPuName());
                    pt.setDatetime(dtt);
                    pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                    double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                    double currentIndex=puIndex+(t.getQuantity());
                    String curIndex=String.valueOf(currentIndex);
                    pt.setIndexafter(curIndex);//set pump after index
                    pumpTransactionFacade.create(pt);
                    entityManager.flush();
                    
                    String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                    
                    String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                }
                else{
                    if(spr.getStatusCode().equalsIgnoreCase("301")){
                        
                        t.setPaymentStatus("pending");
                        t.setQuantity(0.0);
                        t.setAmount(0.0);
                        t.setResDatetime(dtt);
                        transactionFacade.edit(t);
                        entityManager.flush();
                        
                        //persisting pump transaction
                        PumpTransaction pt=new PumpTransaction();
                        pt.setTraId(t.getTraId());
                        pt.setUName(t.getUId().getUName());
                        pt.setProName(t.getProId().getProName());
                        pt.setPuName(t.getPuId().getPuName());
                        pt.setDatetime(dtt);
                        pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                        double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                        double currentIndex=puIndex+(t.getQuantity());
                        String curIndex=String.valueOf(currentIndex);
                        pt.setIndexafter(curIndex);//set pump after index
                        pumpTransactionFacade.create(pt);
                        entityManager.flush();
                        
                        String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                        
                        String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                    }
                    else{
                        t.setPaymentStatus("failure");
                        t.setQuantity(0.0);
                        t.setAmount(0.0);
                        t.setResDatetime(dtt);
                        transactionFacade.edit(t);
                        entityManager.flush();
                        
                        //persisting pump transaction
                        PumpTransaction pt=new PumpTransaction();
                        pt.setTraId(t.getTraId());
                        pt.setUName(t.getUId().getUName());
                        pt.setProName(t.getProId().getProName());
                        pt.setPuName(t.getPuId().getPuName());
                        pt.setDatetime(dtt);
                        pt.setIndexbefore(t.getPuId().getPuIndex());//set pump before index and this index is from pump table
                        double puIndex=Double.parseDouble(t.getPuId().getPuIndex());
                        double currentIndex=puIndex+(t.getQuantity());
                        String curIndex=String.valueOf(currentIndex);
                        pt.setIndexafter(curIndex);//set pump after index
                        pumpTransactionFacade.create(pt);
                        entityManager.flush();
                        
                        String currIndex=pt.getIndexafter();//get the pumptransaction current index after serving
                        
                        String cuIndex=setPumpCurrentIndex(t.getPuId().getPuId(),currIndex);//update pump index in pump table and this function is in line 742
                    }
                }
            }
            
            //POST PAYMENT CONFORMATION MASSAGE On This Function To Manzi For Payment Conformation
            serviceAcknowledgementXML(contractid,transacId,statusdesc,statuscode);
            
        }//try block end
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    //This function is to post conformation message to manzi
    public String serviceAcknowledgementXML(String conId,String trId,String descr,String scode){
        
        String url="http://10.171.1.53/PaymentGateway/payments/paymentResponseConfirmation";
        String xmlData= "<COMMAND>"
                +"<TRANSID>"+trId+"</TRANSID>"
                +"<CONTRACTID>"+conId+"</CONTRACTID>"
                +"<STATUSCODE>"+scode+"</STATUSCODE>"
                +"<DESCR>"+descr+"</DESCR>"
                +"</COMMAND>";
        out.print("ANDROID SERVICE CONFORMATION:"+xmlData);
        Response response=commonLibrary.sendRESTRequest(url, xmlData, MediaType.APPLICATION_XML, "POST");
        String xmldata=response.readEntity(String.class);
        out.print("ANDROID SERVICE CONFORMATION: Payment conformation response Header:"+response+" | BODY:"+xmldata);
        
        return xmldata;
    }
    
    
    
    
    
//************************************************** ASYNC REQUEST FROM AUBAIN TO KNOW TRANSACTION ID STATUS ****************************************************************
    
    //This Function Get completed transaction data from transaction table Bcz Aubain Will Make Async request To Know Transaction Id Status
    
    public String getTraDataById(String ipjson){
        
        try{
            ObjectMapper mapper=new ObjectMapper();
            TraIp ti= mapper.readValue(ipjson, TraIp.class);
            String tId=ti.gettId();
            String json=proceedTraDataById(tId);
            
            return json;
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    public String proceedTraDataById(String traId)throws Exception{
        
        int tId=Integer.parseInt(traId);
        Transaction t=new Transaction();
        List<Transaction> tl=(List<Transaction>)entityManager.createNamedQuery("Transaction.findByTraId").setParameter("traId", tId).getResultList();
        if(!tl.isEmpty()){
            Iterator i=tl.iterator();
            while(i.hasNext()){
                t=(Transaction) i.next();
            }
            PaymentOp po=new PaymentOp();
            po.setAmount(t.getAmount().toString());
            po.setCustTel(t.getCustId().getCustTel());
            po.setImei(t.getDId().getImeiNo());
            po.setQuantity(t.getQuantity().toString());
            po.setTraId(t.getTraId().toString());
            po.setValidation("valid");
            po.setbId(t.getBId().getBId().toString());
            po.setpMode(t.getPaymentMode());
            po.setPumpName(t.getPuId().getPuName());
            po.setpName(t.getProId().getProName());
            po.setpStatus(t.getPaymentStatus());
            po.setuId(t.getUId().getUId().toString());
            po.setbName(t.getBId().getBName());
            SimpleDateFormat dformat = new SimpleDateFormat("MMM dd,yyyy HH:mm a");
            String opdate=dformat.format(t.getReqDatetime());
            po.setpTime(opdate);
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(po);
            
            return jsonInString;
        }
        
        PaymentInvalidOp pio=new PaymentInvalidOp();
        pio.setValidation("invalid");
        pio.setTraId("notFound");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(pio);
        
        return jsonInString;
        
    }
    
    
    
    
    
    
    
    
}

