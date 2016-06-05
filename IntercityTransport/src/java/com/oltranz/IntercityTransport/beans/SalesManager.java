/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;



import com.oltranz.IntercityTransport.entities.Bus;
import com.oltranz.IntercityTransport.entities.Card;
import com.oltranz.IntercityTransport.entities.Contract;
import com.oltranz.IntercityTransport.entities.ProfileOnSellingDevice;
import com.oltranz.IntercityTransport.entities.SaleItem;
import com.oltranz.IntercityTransport.entities.SaleTransaction;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.entities.SellingDevice;
import com.oltranz.IntercityTransport.entities.SellingProfile;
import com.oltranz.IntercityTransport.entities.ServiceProvider;
import com.oltranz.IntercityTransport.entities.User;
import com.oltranz.IntercityTransport.entities.Wallet;
import com.oltranz.IntercityTransport.entities.WalletPK;
import com.oltranz.IntercityTransport.models.BusOwnersTodayIncomeModel;
import com.oltranz.IntercityTransport.models.BusesIncomeReportModel;
import com.oltranz.IntercityTransport.models.CardPaymentOnPOSRequestModel;
import com.oltranz.IntercityTransport.models.OverallTodaySalesModel;
import com.oltranz.IntercityTransport.models.ResultObject;
import com.oltranz.IntercityTransport.models.SaleTransactionsSearchModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
//import org.eclipse.persistence.config.HintValues;
//import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class SalesManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            WalletsManager walletsManagerEJB;
    
    public ResultObject getSalesTransactions(SaleTransactionsSearchModel saleSearch){
        Date fromTime;
        Date toTime;
        ResultObject resultObject=new ResultObject();
        try{
            
            Query query;
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); // only for display right bellow
            try{
                fromTime=sdf.parse(saleSearch.getFrom());
                toTime=sdf.parse(saleSearch.getTo());
            }catch(Exception ex){
                resultObject.setMessage("Wrong time format, use  yyyyMMddHHmm");
                resultObject.setObject(null);
                resultObject.setObjectClass(ArrayList.class);
                return resultObject;
            }
            
            String q="select s from SaleTransaction s where s.dateTime >=:fromTime and s.dateTime<=:toTime";
            if(saleSearch.getBusNumberPlate()!=null)
                if(!saleSearch.getBusNumberPlate().trim().isEmpty())
                q+=" and s.busNumberPlate=:busNumberPlate";
            if(saleSearch.getBusOwnerId()!=null)
                if(saleSearch.getBusOwnerId()!=0)
                q+=" and s.busOwnerId=:busOwnerId";
            if(saleSearch.getBusOwnerTypeId()!=null)
                if(saleSearch.getBusOwnerTypeId()!=0)
                q+=" and s.busOwnerTypeId=:busOwnerTypeId";
            if(saleSearch.getTransporterId()!=null)
                if(saleSearch.getTransporterId()!=0)
                q+=" and s.transporterId=:transporterId";
            //
            //   s.transporterId=:transporterId and
            query=em.createQuery(q);
            query.setParameter("toTime", toTime,TemporalType.TIMESTAMP);
            query.setParameter("fromTime", fromTime,TemporalType.TIMESTAMP);
            
            if(saleSearch.getBusNumberPlate()!=null)
                if(!saleSearch.getBusNumberPlate().trim().isEmpty())
                query.setParameter("busNumberPlate",saleSearch.getBusNumberPlate());
            if(saleSearch.getBusOwnerId()!=null)
                if(saleSearch.getBusOwnerId()!=0)
                query.setParameter("busOwnerId",saleSearch.getBusOwnerId());
            if(saleSearch.getBusOwnerTypeId()!=null)
                if(saleSearch.getBusOwnerTypeId()!=0)
                query.setParameter("busOwnerTypeId",saleSearch.getBusOwnerTypeId());
            if(saleSearch.getTransporterId()!=null)
                if(saleSearch.getTransporterId()!=0)
                query.setParameter("transporterId",saleSearch.getTransporterId());
            
            
            
            List<SaleTransaction> spList=(List<SaleTransaction>)query.getResultList();
            
            if(spList.isEmpty())
                resultObject.setMessage("No Transaction yet");
            else
                resultObject.setMessage(spList.size()+" Sales transactions returned ");
            resultObject.setObject(spList);
            resultObject.setObjectClass(ArrayList.class);
            return resultObject;
        }catch(Exception ex){
            resultObject.setMessage("Wrong time format, use  yyyyMMddHHmm");
            resultObject.setObject(null);
            resultObject.setObjectClass(ArrayList.class);
            return resultObject;
        }
    }
    
    
    public ResultObject processCardPaymentOnPOSRequest(CardPaymentOnPOSRequestModel payment){
        ResultObject resultObject= new ResultObject();
        
        ServiceProvider serviceProvider;
        Transporter transporter;
        Wallet transporterWallet;
        Contract busOwnerContract;
        SaleTransaction saleTransaction=new SaleTransaction();
        
        saleTransaction.setCardId(payment.getCardId());
        saleTransaction.setDateTime(new Date());
        saleTransaction.setDeviceId(payment.getDeviceId());
        
        //saleTransaction.setPassangerWalletId(walletTypeId);
        //saleTransaction.setPassangerWalletNewBalance(Double.NaN);
        //saleTransaction.setPassangerWalletPrevBalance(Double.NaN);
        saleTransaction.setQty(payment.getQuantity());
        saleTransaction.setSaleItemId(payment.getItemId());
        saleTransaction.setSellingProfileId(payment.getProfileId());
        saleTransaction.setTotalPrice(payment.getTotalAmount());
        saleTransaction.setTransporterId(payment.getTransporterId());
        //saleTransaction.setTransporterWalletId(walletTypeId);
//        saleTransaction.setTransporterWalletNewBalance(Double.NaN);
//        saleTransaction.setTransporterWalletPrevBalance(Double.NaN);
//        saleTransaction.setUnitPrice(payment.getTotalAmount()/payment.getQuantity());
        saleTransaction.setStatusCode(11);
        saleTransaction.setStatusMessage("Initiated");
        em.persist(saleTransaction);
        em.flush();
        
        
        //on receiving payment request
        //1. check validity of (profileId, deviceId) validity
        Query query = em.createQuery("select p from ProfileOnSellingDevice p where p.profileOnSellingDevicePK.deviceId=:deviceId and p.profileOnSellingDevicePK.profileId=:profileId");
        query.setParameter("deviceId",payment.getDeviceId());
        query.setParameter("profileId",payment.getProfileId());
        List<ProfileOnSellingDevice> device_profileList=query.getResultList();
        if(device_profileList.isEmpty()){
            saleTransaction.setStatusCode(12);
            saleTransaction.setStatusMessage("Wrong Selling profile");
            em.merge(saleTransaction);
            resultObject.setStatusCode("102");
            
            resultObject.setObject(null);
            resultObject.setMessage("Current Profile on device is no longer assigned to it on the server");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            
            return resultObject;
        }
        
        SellingDevice sellingDevice=em.find(SellingDevice.class,payment.getDeviceId());
        if(sellingDevice==null){
            saleTransaction.setStatusCode(13);
            saleTransaction.setStatusMessage("Unknown selling device");
            em.merge(saleTransaction);
            resultObject.setStatusCode("103");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Device can not be found on system");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        SellingProfile sellingProfile=em.find(SellingProfile.class, payment.getProfileId());
        if(sellingProfile==null){
            saleTransaction.setStatusCode(14);
            saleTransaction.setStatusMessage("Unknown Selling Profile");
            em.merge(saleTransaction);
            resultObject.setStatusCode("104");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Selling profile can not be found on the system");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        //confirm that item payed for remains a member of the profile and its prices remains the same
        SaleItem saleItem=em.find(SaleItem.class, payment.getItemId());
        if(saleItem==null){
            saleTransaction.setStatusCode(15);
            saleTransaction.setStatusMessage("Sold Item not in system");
            em.merge(saleTransaction);
            resultObject.setStatusCode("105");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Paid for Item no longer exists on system");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        saleTransaction.setSaleItemName(saleItem.getName());
        saleTransaction.setUnitPrice(payment.getTotalAmount()/payment.getQuantity());
        em.merge(saleTransaction);
        
        query=em.createQuery("select I from ItemOnSellingProfile I where I.itemId=:itemId and I.profileId=:profileId");
        query.setParameter("itemId", saleItem.getId());
        query.setParameter("profileId",sellingProfile.getId());
        
        if(query.getResultList().isEmpty()){
            saleTransaction.setStatusCode(16);
            saleTransaction.setStatusMessage("Sold Item not on Selling profile");
            em.merge(saleTransaction);
            resultObject.setStatusCode("106");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Paid for Item no longer on the selling profile for this device");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        Integer paymentUnitPrice=payment.getTotalAmount()/payment.getQuantity();
        if(saleItem.getPrice().intValue()!=paymentUnitPrice){
            saleTransaction.setStatusCode(17);
            saleTransaction.setStatusMessage("Sold Item price wrong price");
            em.merge(saleTransaction);
            resultObject.setStatusCode("107");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Item price has changed in the system");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        //get Passenger wallet, but first get the card
        Card passengerCard=em.find(Card.class, payment.getCardId());
        if(passengerCard==null){
            saleTransaction.setStatusCode(18);
            saleTransaction.setStatusMessage("Unrecognized card");
            em.merge(saleTransaction);
            resultObject.setStatusCode("108");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: No recognized card");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        saleTransaction.setPassenger_identifier(em.find(User.class, passengerCard.getOwnerId()).getPhoneNumber());
        em.merge(saleTransaction);
        query=em.createQuery("select w from Wallet w where w.walletPK.ownerId=:ownerId and w.walletPK.typeId=:typeId");
        query.setParameter("ownerId", passengerCard.getOwnerId());
        query.setParameter("typeId", passengerCard.getTypeId());
        
        List<Wallet>passengerWalletsList=(List<Wallet>)query.getResultList();
        if(passengerWalletsList.isEmpty()){
            saleTransaction.setStatusCode(19);
            saleTransaction.setStatusMessage("Passenger Wallet missing");
            em.merge(saleTransaction);
            resultObject.setStatusCode("109");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error:Passenger Wallet could not be found");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        Wallet passengerWallet=passengerWalletsList.get(0);
        if(passengerWallet.getBalance()<payment.getTotalAmount()){
            saleTransaction.setStatusCode(110);
            saleTransaction.setStatusMessage("Passenger Wallet insufficient amount");
            em.merge(saleTransaction);
            resultObject.setStatusCode("110");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error:Passenger Wallet insufficient amount");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        
        //2. get bus on which device is located
        if(sellingDevice.getCurrentBusNumberPlate()==null || sellingDevice.getCurrentBusNumberPlate().isEmpty())
        {
            saleTransaction.setStatusCode(111);
            saleTransaction.setStatusMessage("Selling device not attached to any bus");
            em.merge(saleTransaction);
            resultObject.setStatusCode("111");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Device is currently not attached to any bus in the system");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        //get profile and confirm that the profile and the device are well located on the same transporter
        
        transporter=em.find(Transporter.class, sellingProfile.getTransporterId());
        if(transporter==null){
            saleTransaction.setStatusCode(112);
            saleTransaction.setStatusMessage("Selling Profile indicating unrecognizable tranporter's id");
            em.merge(saleTransaction);
            resultObject.setStatusCode("112");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: wrong Profile's Transporter id");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        saleTransaction.setTransporterId(transporter.getId());
        
        if((transporter.getStatus()&2)==0){
            saleTransaction.setStatusCode(113);
            saleTransaction.setStatusMessage("Transporter disabled in the system");
            em.merge(saleTransaction);
            resultObject.setStatusCode("113");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Transporter is disabled; please contact administrator");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        
        //now that everything is okay with transporter let's get his wallet
        query =em.createQuery("select w from Wallet w where w.walletPK.ownerId=:transporterId and w.walletPK.typeId=2");
        query.setParameter("transporterId", transporter.getId());
        
        
        List<Wallet> transportersWalletsList=(List<Wallet>) query.getResultList();
        if(transportersWalletsList.isEmpty()){
            saleTransaction.setStatusCode(114);
            saleTransaction.setStatusMessage("Transporter's wallet not found");
            em.merge(saleTransaction);
            resultObject.setStatusCode("114");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Transporter wallet can not be found");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        transporterWallet=transportersWalletsList.get(0);
        
        if(!sellingProfile.getTransporterId().equals(sellingDevice.getTransporterId())){
            saleTransaction.setStatusCode(115);
            saleTransaction.setStatusMessage("Selling Device with profile from different transporters");
            em.merge(saleTransaction);
            resultObject.setStatusCode("115");
            
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: Selling profile and Device do not bellong to the same transporter");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        
        //finally get the bus
        Bus bus=em.find(Bus.class, sellingDevice.getCurrentBusNumberPlate());
        if(bus==null){
            saleTransaction.setStatusCode(116);
            saleTransaction.setStatusMessage("Bus indicated by selling device can not be found");
            em.merge(saleTransaction);
            resultObject.setStatusCode("116");
            
            resultObject.setObject(null);
            resultObject.setMessage("Fatal Error: the number plate of the bus on which this Selling device is currently attached to cannot be found");
            resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
            return resultObject;
        }
        
        saleTransaction.setBusNumberPlate(bus.getNumberPlate());
        em.merge(saleTransaction);
        //3. get owner of the bus (bus_owner)
        // -start by preparing Service provider variabl, in case bus is not transporter owned
        
        
        if(bus.isTransporterOwned()){
            // the owner's id should be equal to the profile's transporterId,else Fatal error
            saleTransaction.setBusOwnerName(transporter.getName());
            saleTransaction.setBusOwnerId(transporter.getId());
            saleTransaction.setBusOwnerTypeId(1);
            saleTransaction.setBusRentingCost(0);
            em.merge(saleTransaction);
            
            if(!bus.getOwnerId().equals(sellingProfile.getTransporterId())){
                saleTransaction.setStatusCode(117);
                saleTransaction.setStatusMessage("Bus and selling device owned by different transporters");
                em.merge(saleTransaction);
                resultObject.setStatusCode("117");
                
                resultObject.setObject(null);
                resultObject.setMessage("Fatal Error: The Bus indicated as transporter owned, has an owner different from the owner of the device and profile");
                resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
                return resultObject;
            }
            
            //process now the payments
            Integer walletTransactionType=5; //cTicket payment by passenger
            Integer referenceTransactionType=2; //evice ticket selling Tranaction
            
            saleTransaction.setPassangerWalletId(passengerWallet.getWalletPK().getOwnerId());
            saleTransaction.setPassangerWalletPrevBalance(passengerWallet.getBalance());
            saleTransaction.setTransporterWalletPrevBalance(transporterWallet.getBalance());
            
            walletsManagerEJB.processWalletTransaction(passengerWallet.getWalletPK(), transporterWallet.getWalletPK(),payment.getTotalAmount(),walletTransactionType,referenceTransactionType,saleTransaction.getId());
            
            saleTransaction.setPassangerWalletNewBalance(passengerWallet.getBalance());
            saleTransaction.setTransporterWalletNewBalance(transporterWallet.getBalance());
            em.merge(saleTransaction);
        }else{
            saleTransaction.setBusOwnerTypeId(2);
            em.merge(saleTransaction);
            em.flush();
            // owner id must be a valid service provider
            serviceProvider=em.find(ServiceProvider.class, bus.getOwnerId());
            if(serviceProvider==null){
                saleTransaction.setStatusCode(118);
                saleTransaction.setStatusMessage("Bus Owner can not be found");
                em.merge(saleTransaction);
                resultObject.setStatusCode("118");
                
                resultObject.setObject(null);
                resultObject.setMessage("Fatal Error: The Bus indicated as service provider owned, but the owner cannot be found");
                resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
                return resultObject;
            }
            saleTransaction.setBusOwnerName(serviceProvider.getName());
            saleTransaction.setBusOwnerId(serviceProvider.getId());
            em.merge(saleTransaction);
            
            // get confirm validity of bus owner's service provider contract
            
            query = em.createQuery("select c from BusOnContract bc join Contract c on bc.BusOnContractPK.contractId=c.id where bc.BusOnContractPK.numberPlate=:numberPlate");
            query.setParameter("numberPlate", bus.getNumberPlate());
            
            List<Contract> busOwnerContractsList=query.getResultList();
            
            if(busOwnerContractsList.isEmpty()){
                saleTransaction.setStatusCode(119);
                saleTransaction.setStatusMessage("Bus Owner contract missing");
                em.merge(saleTransaction);
                resultObject.setStatusCode("119");
                
                resultObject.setObject(null);
                resultObject.setMessage("Fatal Error: Bus Owner contract missing");
                resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
                return resultObject;
            }
            busOwnerContract=busOwnerContractsList.get(0);
            if(busOwnerContract.getEndDate().before(new Date())){
                saleTransaction.setStatusCode(120);
                saleTransaction.setStatusMessage("Bus Owner contract expired");
                em.merge(saleTransaction);
                resultObject.setStatusCode("120");
                
                resultObject.setObject(null);
                resultObject.setMessage("Fatal Error: Bus Owner contract Expired");
                resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
                return resultObject;
            }
            
            //process now the payments
            Integer walletTransactionType=5; //cTicket payment by passenger
            Integer referenceTransactionType=2; //evice ticket selling Tranaction
            
            saleTransaction.setPassangerWalletId(passengerWallet.getWalletPK().getOwnerId());
            saleTransaction.setPassangerWalletPrevBalance(passengerWallet.getBalance());
            saleTransaction.setTransporterWalletPrevBalance(transporterWallet.getBalance());
            
            walletsManagerEJB.processWalletTransaction(passengerWallet.getWalletPK(), transporterWallet.getWalletPK(),payment.getTotalAmount(),walletTransactionType,referenceTransactionType,saleTransaction.getId());
            
            saleTransaction.setPassangerWalletNewBalance(passengerWallet.getBalance());
            saleTransaction.setTransporterWalletNewBalance(transporterWallet.getBalance());
            em.merge(saleTransaction);
            WalletPK busOwnerWalletPK= new WalletPK();
            busOwnerWalletPK.setOwnerId(busOwnerContract.getServiceProviderId());
            busOwnerWalletPK.setTypeId(3);
            Wallet busOwnerWallet=em.find(Wallet.class,busOwnerWalletPK);
            //get Bus owner wallet
            walletTransactionType=6;
            saleTransaction.setBusRentingCost(payment.getTotalAmount()*busOwnerContract.getAmount().intValue()/100);
            em.merge(saleTransaction);
            walletsManagerEJB.processWalletTransaction(transporterWallet.getWalletPK(),busOwnerWallet.getWalletPK(),saleTransaction.getBusRentingCost(),walletTransactionType,referenceTransactionType,saleTransaction.getId());
            
            saleTransaction.setTransporterWalletNewBalance(transporterWallet.getBalance());
            em.merge(saleTransaction);
        }
        
        //at this level basic payment have been processed. let
        //get list of all service providers with transactional () whether percentage or fixed and pay them as well
        
        query=em.createQuery("select c from Contract c where (c.transporterId=:transporterId and c.paymentTypeId=1 or c.paymentTypeId=2) and c.serviceId!=2");
        query.setParameter("transporterId", transporter.getId());
        
        List<Contract> serviceProvidersContractsList=(List<Contract>)query.getResultList();
        
        WalletPK spWalletPK;
        Integer walletTransactionType=6; //contractual
        Integer referenceTransactionType=2;
        
        Integer OtherCost=0;
        for(Contract c : serviceProvidersContractsList){
            spWalletPK= new WalletPK();
            spWalletPK.setOwnerId(c.getServiceProviderId());
            spWalletPK.setTypeId(3);
            
            if(c.getPaymentTypeId()==1){
                OtherCost+=payment.getTotalAmount()*c.getAmount().intValue()/100;
                walletsManagerEJB.processWalletTransaction(transporterWallet.getWalletPK(),spWalletPK,payment.getTotalAmount()*c.getAmount().intValue()/100,walletTransactionType,referenceTransactionType,saleTransaction.getId());
            }
            else{
                OtherCost+=c.getAmount().intValue();
                walletsManagerEJB.processWalletTransaction(transporterWallet.getWalletPK(),spWalletPK,c.getAmount().intValue(),walletTransactionType,referenceTransactionType,saleTransaction.getId());
            }
        }
        saleTransaction.setOtherImmediateCosts(OtherCost);
        saleTransaction.setTransporterWalletNewBalance(transporterWallet.getBalance());
        
        saleTransaction.setStatusCode(100);
        saleTransaction.setStatusMessage("Completed successfully");
        em.merge(saleTransaction);
        em.flush();
        resultObject.setStatusCode("100");
        
        resultObject.setObject(payment);
        resultObject.setMessage("Success");
        resultObject.setObjectClass(CardPaymentOnPOSRequestModel.class);
        return resultObject;
//3. get transporter owner of the profile (profile_device_owner) and device used
        //4. get transporter owner of the of the bus wallet (bus_owner_wallet)
        //
        //5. check card validity
        //6. get customer owner of the card used
        //7. get customer's wallet
        //8. get contracts on which current bus is attached and for which [profile_device_owner] is the contracting_transporter
        //9. Out of the contracts get those with transactional payment
        //10. check if the balance of customer wallet is greater than the total request payment
        //
        //11. proceed with creating sale_transaction, setting each variable with data from above variables, save it and get its new id in [Sale_transaction_id]
        //
        //12 remove amount needed from customer wallet
        //  -create wallet tranaction for it setting [refTrans_id]=[Sale_transaction_id],[refTransType_id]=[bus_ticket_purchase_type]
        //13 `
        //13 get list of all the (contractor,walletId, percentage)
        //   set remainingMount=AmountPaid
        //  Add the percentage of the paid amount the balance of each wallet
        //  create wallet tranaction for it setting [refTrans_id]=[Sale_transaction_id],[refTransType_id]=[bus_ticket_purchase_type]
        //   remainingMount+=AmountAddedToTheabove
        //14.
        
    }
    
    public ResultObject getTodayBusesSalesReport(){
        ResultObject resultObject=new ResultObject();
        
//         private String numberPlate;
//    private Integer ownerId;
//    private String OwnerName;
//    private Integer yesterdayIncome;
//    private Integer todayIncome;
        
        Query query=em.createQuery("select t.busNumberPlate,t.busOwnerId,t.busOwnerName,SUM(t.totalPrice) from SaleTransaction t where t.dateTime>=:Today and t.statusCode=100  group by t.busNumberPlate  ");
        query.setParameter("Today", getStartOfDayDateTime(new Date()), TemporalType.TIMESTAMP);
        
        List<Object[]> objectsList= query.getResultList();
        
        List<BusesIncomeReportModel> busAndIncomeList= new ArrayList();
        objectsList.forEach(x->{
            BusesIncomeReportModel model= new BusesIncomeReportModel();
            model.setNumberPlate((String)x[0]);
            model.setOwnerId((Integer)x[1]);
            model.setOwnerName((String)x[2]);
            model.setTodayIncome(((Long)x[3]).intValue());
            model.setYesterdayIncome(0);
            busAndIncomeList.add(model);
        });
        
        resultObject.setObject(busAndIncomeList);
        
        if(busAndIncomeList.isEmpty())
            resultObject.setMessage("No transaction yet for today");
        else
            resultObject.setMessage(busAndIncomeList.size() + " Buses made money today");
        
        resultObject.setObjectClass(ArrayList.class);
        
        return resultObject;
    }
    
    public ResultObject getTodayBusesSalesReportPerTransporter(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        
//         private String numberPlate;
//    private Integer ownerId;
//    private String OwnerName;
//    private Integer yesterdayIncome;
//    private Integer todayIncome;
        
        Query query=em.createQuery("select t.busNumberPlate,t.busOwnerId,t.busOwnerName,SUM(t.totalPrice) from SaleTransaction t where t.dateTime>=:Today and t.statusCode=100 and t.transporterId=:transporterId group by t.busNumberPlate  ");
        query.setParameter("Today", getStartOfDayDateTime(new Date()), TemporalType.TIMESTAMP);
        query.setParameter("transporterId", transporterId);
        
        List<Object[]> objectsList= query.getResultList();
        
        List<BusesIncomeReportModel> busAndIncomeList= new ArrayList();
        objectsList.forEach(x->{
            BusesIncomeReportModel model= new BusesIncomeReportModel();
            model.setNumberPlate((String)x[0]);
            model.setOwnerId((Integer)x[1]);
            model.setOwnerName((String)x[2]);
            model.setTodayIncome(((Long)x[3]).intValue());
            model.setYesterdayIncome(0);
            busAndIncomeList.add(model);
        });
        
        resultObject.setObject(busAndIncomeList);
        
        
        if(busAndIncomeList.isEmpty())
            resultObject.setMessage("No transaction yet for today");
        else
            resultObject.setMessage(busAndIncomeList.size() + " Buses made money today");
        
        resultObject.setObjectClass(ArrayList.class);
        
        return resultObject;
    }
    
    public ResultObject getTodayBusOwnersSalesReport(){
        ResultObject resultObject=new ResultObject();
        
//        class BusOwnersTodayIncomeModel
//    private Integer busOwnerId;
//    private String busOwnerName;
//    private Long todayIncome;
        
        Query query=em.createQuery("select t.busOwnerId,t.busOwnerName,SUM(t.totalPrice), t.busRentingCost from SaleTransaction t where t.statusCode=100  group by t.busOwnerName  ");
        query.setParameter("Today", getStartOfDayDateTime(new Date()), TemporalType.TIMESTAMP);
        
        List<Object[]> objectsList= query.getResultList();
        
        List<BusOwnersTodayIncomeModel> busOwnersAndIncomeList= new ArrayList();
        objectsList.forEach(x->{
            BusOwnersTodayIncomeModel model= new BusOwnersTodayIncomeModel();
            model.setBusOwnerId((Integer)x[0]);
            model.setBusOwnerName((String)x[1]);
            model.setTodaySales((Long)x[2]);
            model.setTodayDue((Long)x[2]);
            busOwnersAndIncomeList.add(model);
        });
        
        resultObject.setObject(busOwnersAndIncomeList);
        
        if(busOwnersAndIncomeList.isEmpty())
            resultObject.setMessage("No transaction yet for today");
        else
            resultObject.setMessage(busOwnersAndIncomeList.size() + " Buses made money today");
        
        resultObject.setObjectClass(ArrayList.class);
        
        return resultObject;
    }
    
    public ResultObject getTodayBusOwnersSalesReportPerTransporter(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        
//        class BusOwnersTodayIncomeModel
//    private Integer busOwnerId;
//    private String busOwnerName;
//    private Long todayIncome;
        
        Query query=em.createQuery("select t.busOwnerId,t.busOwnerName,SUM(t.totalPrice), COUNT(DISTINCT(t.busNumberPlate)), t.busRentingCost from SaleTransaction t where t.dateTime>=:Today and  t.statusCode=100 and t.transporterId=:transporterId  group by t.busOwnerName  ");
        query.setParameter("Today", getStartOfDayDateTime(new Date()), TemporalType.TIMESTAMP);
        query.setParameter("transporterId", transporterId);
        
        List<Object[]> objectsList= query.getResultList();
        
        List<BusOwnersTodayIncomeModel> busOwnersAndIncomeList= new ArrayList();
        objectsList.forEach(x->{
            BusOwnersTodayIncomeModel model= new BusOwnersTodayIncomeModel();
            model.setBusOwnerId((Integer)x[0]);
            model.setBusOwnerName((String)x[1]);
            model.setTodaySales((Long)x[2]);
            model.setTodayDue((Long)x[2]);
            model.setBusesCount((Long)x[3]);
            busOwnersAndIncomeList.add(model);
        });
        
        resultObject.setObject(busOwnersAndIncomeList);
        
        if(busOwnersAndIncomeList.isEmpty())
            resultObject.setMessage("No transaction yet for today");
        else
            resultObject.setMessage(busOwnersAndIncomeList.size() + " Buses made money today");
        
        resultObject.setObjectClass(ArrayList.class);
        
        return resultObject;
    }
    
    public ResultObject getTransporterTotalRevenues(Integer transporterId){
        ResultObject resultObject= new ResultObject();
        try{
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String todayDateStr=sdf.format(getStartOfDayDateTime(new Date()));
            
            OverallTodaySalesModel model= null;
            Query query=em.createNativeQuery("select cast(count(distinct(bus_number_plate)) as signed), cast(sum(qty) as signed), cast(sum(total_price) as signed) from sales_transactions where status_code=100 and date_time >= '"+todayDateStr+"'");
            // Query query=em.createQuery("select COUNT( DISTINCT(t.busNumberPlate)),  SUM(t.qty), SUM(t.totalPrice)  from SaleTransaction t where t.statusCode=100 and t.transporterId=:transporterId and t.dateTime>=:Today");
//        query.setParameter("Today", getStartOfDayDateTime(new Date()), TemporalType.TIMESTAMP);
//        query.setParameter("transporterId", transporterId);
            List<Object[]> objectsList= query.getResultList();
            
            if(!objectsList.isEmpty()){
                model=new OverallTodaySalesModel();
                Object[] x= objectsList.get(0);
                
                if(x[0]!=null)
                    model.setTodayBusesCount(((Long)x[0]).intValue());
                else
                    model.setTodayBusesCount(0);
                
                if(x[1]!=null)
                    model.setTodayPassengersCount(((Long)x[1]).intValue());
                else
                    model.setTodayPassengersCount(0);
                
                if(x[2]!=null)
                    model.setTodayRevenue(((Long)x[2]).intValue());
                else
                    model.setTodayRevenue(0);
                
                query=em.createNativeQuery("select cast(count(distinct(bus_number_plate)) as signed), cast(sum(qty) as signed), cast(sum(total_price) as signed) from sales_transactions where status_code=100 and date_time < '"+todayDateStr+"'");
                
                objectsList= query.getResultList();
                
                if(!objectsList.isEmpty()){
                    x= objectsList.get(0);
                    
                    if(x[0]!=null)
                        model.setUp2YesterdayBusesCount(((Long)x[0]).intValue());
                    else
                        model.setUp2YesterdayBusesCount(0);
                    
                    if(x[1]!=null)
                        model.setUp2YesterdayPassengersCount(((Long)x[1]).intValue());
                    else
                        model.setUp2YesterdayPassengersCount(0);
                    
                    if(x[2]!=null)
                        model.setUp2YesterdayRevenue(((Long)x[2]).intValue());
                    else
                        model.setUp2YesterdayRevenue(0);
                }
                
            }
            
            resultObject.setObject(model);
            
            if(model==null)
                resultObject.setMessage("total couldn't be made");
            else
                resultObject.setMessage("Total well calculated");
            
            resultObject.setObjectClass(OverallTodaySalesModel.class);
            
            return resultObject;
        }catch(Exception ex){
            resultObject.setMessage("error: "+ex.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(ArrayList.class);
            return resultObject;
        }
    }
    
    private Date getStartOfDayDateTime(Date theDate){
        try{
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            return sdf2.parse(sdf1.format(theDate)+" 00:00");
        }catch(Exception ex){
            return theDate;
        }
        
    }
    
    
    
    
}
