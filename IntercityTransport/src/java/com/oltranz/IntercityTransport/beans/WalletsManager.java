/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.SaleTransaction;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.entities.User;
import com.oltranz.IntercityTransport.entities.Wallet;
import com.oltranz.IntercityTransport.entities.WalletPK;
import com.oltranz.IntercityTransport.entities.WalletTransaction;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransporter.utils.CommonLibrary;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.eclipse.persistence.config.HintValues;
//import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class WalletsManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    @EJB
            InitialData initialDataEJB;
    
    public ResultObject getWallet(Integer walletId){
        ResultObject resultObject=new ResultObject();
        Wallet wallet=em.find(Wallet.class,walletId);
        
        if(wallet==null){
            resultObject.setMessage("Wallet with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }else{
            resultObject.setMessage("Wallet well found!");
            resultObject.setObject(wallet);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }
    }
    
    public ResultObject createWallet(Wallet newWallet){
        ResultObject resultObject=new ResultObject();
        String ownerTypeName="";
        try{
            
            //Owner's Id and owner type id can never null;
            if(newWallet.getWalletPK().getOwnerId()==null || newWallet.getWalletPK().getTypeId()==null){
                resultObject.setMessage("Neither OwnerId nor typeId can be null");
                resultObject.setObject(null);
                resultObject.setObjectClass(Wallet.class);
                return resultObject;
            }
            
            switch(newWallet.getWalletPK().getTypeId()){
                case 1:
                    if(em.find(User.class, newWallet.getWalletPK().getOwnerId())==null){
                        resultObject.setMessage("[type 1: passenger],Owner Passenger user (with id:"+newWallet.getWalletPK().getOwnerId()+")  cannot be found");
                        resultObject.setObject(null);
                        resultObject.setObjectClass(Wallet.class);
                        return resultObject;
                    }
                    ownerTypeName="Passenger";
                    break;
                case 2:
                    if(em.find(User.class, newWallet.getWalletPK().getOwnerId())==null){
                        resultObject.setMessage("[type 2: transporter],Owner Transporter (with id:"+newWallet.getWalletPK().getOwnerId()+")  cannot be found");
                        resultObject.setObject(null);
                        resultObject.setObjectClass(Wallet.class);
                        return resultObject;
                    }
                    ownerTypeName="Transporter";
                    break;
                    
                case 3:
                    if(em.find(User.class, newWallet.getWalletPK().getOwnerId())==null){
                        resultObject.setMessage("[type 3: Service Provider],Owner Service Provider (with id:"+newWallet.getWalletPK().getOwnerId()+")  cannot be found");
                        resultObject.setObject(null);
                        resultObject.setObjectClass(Wallet.class);
                        return resultObject;
                    }
                    ownerTypeName="Service Provider";
                    break;
                    
                default:
                    
                    resultObject.setMessage("[type "+newWallet.getWalletPK().getTypeId()+": Invalid wallet type");
                    resultObject.setObject(null);
                    resultObject.setObjectClass(Wallet.class);
                    return resultObject;
            }
            
            //check to see if no other wallet is attached to this owner
            Query query=em.createQuery("select w from Wallet w where w.walletPK.ownerId=:ownerId and w.walletPK.typeId=:typeId");
            query.setParameter("ownerId", newWallet.getWalletPK().getOwnerId());
            query.setParameter("typeId", newWallet.getWalletPK().getTypeId());
            
            if(!query.getResultList().isEmpty()){
                resultObject.setMessage("[type"+newWallet.getWalletPK().getTypeId()+":"+ownerTypeName+"; This "+ownerTypeName+" has alreadygot another wallet");
                resultObject.setObject(null);
                resultObject.setObjectClass(Wallet.class);
                return resultObject;
            }
            
            em.persist(newWallet);
            em.flush();
            resultObject.setMessage("Wallet for this "+ownerTypeName+" well created");
            resultObject.setObject(newWallet);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setMessage("Exception duting creation of a "+ownerTypeName+" wallet. Error message:"+e.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }
    }
    
    public ResultObject createPassengerWallet(Integer userId){
        ResultObject resultObject=new ResultObject();
        try{
            Wallet wallet=new Wallet();
            wallet.setBalance(0);
            wallet.setWalletPK(new WalletPK(userId,1));
            return createWallet(wallet);
        }
        catch(Exception e){
            resultObject.setMessage("Exception duting creation of a user wallet. Error message:"+e.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }
    }
    
    public ResultObject createTransporterWallet(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        try{
            Wallet wallet=new Wallet();
            wallet.setBalance(0);
            wallet.setWalletPK(new WalletPK(transporterId,2));
            return createWallet(wallet);
        }
        catch(Exception e){
            resultObject.setMessage("Exception duting creation of a transporter wallet. Error message:"+e.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }
    }
    
    public ResultObject createServiceProviderWallet(Integer serviceProviderId){
        ResultObject resultObject=new ResultObject();
        try{
            Wallet wallet=new Wallet();
            wallet.setBalance(0);
            wallet.setWalletPK(new WalletPK(serviceProviderId,3));
            return createWallet(wallet);
        }
        catch(Exception e){
            resultObject.setMessage("Exception duting creation of a service Provider wallet. Error message:"+e.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(Wallet.class);
            return resultObject;
        }
    }
    
    public ResultObject processWalletTransaction(WalletPK srcWalletPK, WalletPK destWalletPK, Integer amount, Integer wallatTransactionTypeId,Integer transactionTypeId, Long refTransId){
        ResultObject resultObject=new ResultObject();
        //initiatlize wallet transactions types and overall transaction type if not initialized yet
        initialDataEJB.InitializeTranactionsTypes();
        initialDataEJB.InitializeWalletTranactionsTypes();
        try{
            Wallet srcWallet=em.find(Wallet.class, srcWalletPK);
            Wallet destWallet=em.find(Wallet.class, destWalletPK);
            
            Double srcWalletPrevBalance=srcWallet.getBalance();
            Double srcWalletNewBalance= srcWalletPrevBalance-amount;
            srcWallet.setBalance(srcWalletNewBalance);
            
            if(srcWalletNewBalance<0){
                resultObject.setMessage("Insufficent amount on source Wallet");
                resultObject.setObject(null);
                resultObject.setObjectClass(WalletTransaction.class);
                return resultObject;
            }
            
            Double destWalletPrevBalance=destWallet.getBalance();
            Double destWalletNewBalance= destWalletPrevBalance+amount;
            destWallet.setBalance(destWalletNewBalance);            
            
            WalletTransaction walletTransaction=new WalletTransaction();            
            
            walletTransaction.setDateTime(new Date());
            walletTransaction.setDestWalletOwnerId(destWalletPK.getOwnerId());
            walletTransaction.setDestWalletTypeId(destWalletPK.getTypeId());
            walletTransaction.setDestWalletNewBalance(destWalletNewBalance);
            walletTransaction.setDestWalletPrevBalance(destWalletPrevBalance);
            walletTransaction.setRefTransId(refTransId);
            walletTransaction.setRefTransTypeId(wallatTransactionTypeId);
            walletTransaction.setSrcWalletOwnerId(srcWalletPK.getOwnerId());
            walletTransaction.setSrcWalletTypeId(srcWalletPK.getTypeId());
            walletTransaction.setSrcWalletNewBalance(srcWalletNewBalance);
            walletTransaction.setSrcWalletPrevBalance(srcWalletPrevBalance);
            walletTransaction.setWalletTransId(wallatTransactionTypeId);
            walletTransaction.setAmount(amount);
            
            em.merge(srcWallet);
            em.merge(destWallet);
            em.persist(walletTransaction);
            em.flush();
            
            //if transaction type is ticket payment send SMS
            if(wallatTransactionTypeId==5){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Transporter transporter=em.find(Transporter.class, destWallet.getWalletPK().getOwnerId());
                User user=em.find(User.class, srcWallet.getWalletPK().getOwnerId());
                SaleTransaction saleTransaction =em.find(SaleTransaction.class, refTransId);
                Integer SPID=getPayingInstitution(user.getPhoneNumber());
                String msisdn= getValidMsisdn(user.getPhoneNumber());
                
                String message="Payment Confirmation: "+amount +" Rwf\nTime:"+ sdf.format(saleTransaction.getDateTime())+"\n Trip: "+ saleTransaction.getSaleItemName()+ "\nPrev Balance" +srcWalletPrevBalance.intValue() + " Rwf\nNew balance="+srcWalletNewBalance.intValue() + " Rwf\nThank you !";
                ussdPush(msisdn, message,SPID);
                sendSMSNotificationSms(transporter.getName(),msisdn, message);
            }
            
            resultObject.setObject(walletTransaction);
            resultObject.setMessage("Transaction well processed");
            resultObject.setObjectClass(WalletTransaction.class);
            return resultObject;
        }catch(Exception ex){
            resultObject.setObject(null);
            resultObject.setObjectClass(WalletTransaction.class);
            resultObject.setMessage(ex.getMessage());
            return resultObject;
        }
        
    }
    
    public ResultObject loadFundOnWallet(Integer ownerId, Integer walletTypeId, Integer amount, Integer wallatTransactionTypeId){
        ResultObject resultObject=new ResultObject();
        //initiatlize wallet transactions types and overall transaction type if not initialized yet
        initialDataEJB.InitializeTranactionsTypes();
        initialDataEJB.InitializeWalletTranactionsTypes();
        try{
            
            WalletPK walletPK= new WalletPK(ownerId,walletTypeId);
            Wallet wallet=em.find(Wallet.class,walletPK);
            if(wallet==null){
                resultObject.setMessage("Wallet cannot be found");
                resultObject.setObject(null);
                resultObject.setObjectClass(String.class);
                return resultObject;
            }
            if(wallatTransactionTypeId<1){
                resultObject.setMessage("Invalid Wallet transaction Type type");
                resultObject.setObject(null);
                resultObject.setObjectClass(String.class);
                return resultObject;
            }
            if(walletTypeId!=1){
                resultObject.setMessage("Invalid Wallet type");
                resultObject.setObject(null);
                resultObject.setObjectClass(String.class);
                return resultObject;
            }
            
            
            if(wallatTransactionTypeId>4){
                resultObject.setMessage("Specified wallet transaction can not be performed for loading funds");
                resultObject.setObject(null);
                resultObject.setObjectClass(String.class);
                return resultObject;
            }
            
            
            
            Double walletPrevBalance=wallet.getBalance();
            Double walletNewBalance= walletPrevBalance+amount;
            wallet.setBalance(walletNewBalance);
            
            
            WalletTransaction walletTransaction=new WalletTransaction();
            
            
            walletTransaction.setDateTime(new Date());
            walletTransaction.setDestWalletOwnerId(walletPK.getOwnerId());
            walletTransaction.setDestWalletTypeId(walletPK.getTypeId());
            walletTransaction.setDestWalletNewBalance(walletNewBalance);
            walletTransaction.setDestWalletPrevBalance(walletPrevBalance);
            walletTransaction.setRefTransTypeId(wallatTransactionTypeId);
            walletTransaction.setSrcWalletOwnerId(0);
            walletTransaction.setSrcWalletTypeId(0);
            walletTransaction.setSrcWalletNewBalance(Double.parseDouble("0"));
            walletTransaction.setSrcWalletPrevBalance(Double.parseDouble("0"));
            walletTransaction.setWalletTransId(wallatTransactionTypeId);
            walletTransaction.setAmount(amount);
            em.merge(wallet);
            em.persist(walletTransaction);
            em.flush();
            resultObject.setObject(walletTransaction);
            resultObject.setMessage("Transaction well processed");
            resultObject.setObjectClass(WalletTransaction.class);
            return resultObject;
        }catch(Exception ex){
            resultObject.setObject(null);
            resultObject.setObjectClass(WalletTransaction.class);
            resultObject.setMessage(ex.getMessage());
            return resultObject;
        }
        
    }
    
    public ResultObject getWalletBalance(Integer ownerId, Integer walletTypeId){
        ResultObject resultObject=new ResultObject();
        //initiatlize wallet transactions types and overall transaction type if not initialized yet
        initialDataEJB.InitializeTranactionsTypes();
        initialDataEJB.InitializeWalletTranactionsTypes();
        WalletPK walletPK= new WalletPK(ownerId,walletTypeId);
        Wallet wallet=em.find(Wallet.class,walletPK);
        if(wallet==null){
            resultObject.setMessage("Wallet cannot be found");
            resultObject.setObject(null);
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
        
        Double walletBalance=wallet.getBalance();
        resultObject.setObject(walletBalance.intValue());
        resultObject.setMessage("Wallet balance well returned");
        resultObject.setObjectClass(Integer.class);
        return resultObject;
    }
    
    public   String sendSMSNotificationSms(String sender,String receiver, String textMessage) {
        try
        {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://10.171.1.51:8080/SmsEngine/SmsHandler");
            Response response = target
                    .queryParam("receiver", getValidMsisdn(receiver))
                    .queryParam("textMessage", textMessage)
                    .queryParam("from", sender).request().buildGet().invoke();
            out.print("SMS NOTIFICATION : To:" + receiver + "|sender:" + sender + "MESSAGE:"+textMessage );
            System.out.println("SMS is sent" + response.readEntity(String.class));
            return response.readEntity(String.class);
        } catch (Exception ex)
        {
            return "error";
        }
    }
    
    public   String ussdPush(String destMSISDN, String textMessage,Integer SPID) {
        try
        {
            String url="http://10.171.1.51/MTNRwUssdPushAgent/push/"+destMSISDN+"/"+textMessage;
            if(SPID==2484)
                url="http://10.171.1.51/MTNRwUssdPushAgent/push/"+destMSISDN+"/"+textMessage;
            if(SPID==3382)
                url="http://10.171.1.51/TIGORwUssdPushAgent/push/"+destMSISDN+"/"+textMessage;
            Response resp=CommonLibrary.sendRESTRequest(url,"", MediaType.APPLICATION_XHTML_XML, "GET");
            String Body=resp.readEntity(String.class);
            out.print("USSDPUSH: Response : HEADER"+resp +" | BODY: "+Body);
            return Body;
        } catch (Exception ex)
        {
            return "error";
        }
    }
    
    private   String getValidMsisdn(String msisdn) {
        return "250" + msisdn.substring(msisdn.length() - 9);
    }
    
    private Integer getPayingInstitution(String msisdn){
        
        
        msisdn=msisdn.trim();
        
        
        msisdn=msisdn.substring(msisdn.length()-10);
        
        String prefix=msisdn.substring(0,3);
        out.print("USSD 4 TRANSPORT : MSISDN Prefix"+prefix);
        
        
        switch(prefix){
            case "078":
                return 2484;
            case "072":
                return 3382;
            case "073":
                return 5728;
        }
        
        return -1;
        
    }
    
    
    
}
