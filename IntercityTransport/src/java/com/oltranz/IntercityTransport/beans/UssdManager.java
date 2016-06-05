/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import org.glassfish.jersey.apache.connector.ApacheClientProperties;
//import org.glassfish.jersey.apache.connector.ApacheConnector;
//import org.glassfish.jersey.client.ClientConfig;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.oltranz.IntercityTransport.entities.Bus;
import com.oltranz.IntercityTransport.entities.Route;
import com.oltranz.IntercityTransport.models.UssdRequest;
import com.oltranz.IntercityTransport.models.UssdResponse;
import com.oltranz.IntercityTransporter.utils.CommonLibrary;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.System.out;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aimable
 */
public class UssdManager {
    
    private static final long serialVersionUID = 1L;
    
    
    private ConcurrentHashMap<Object, String> sessions = new ConcurrentHashMap<>();
    
    private String resumePreviousState;
    private Date todayDate;
    
    private Date referenceDate; //the date of first day in the list of days dispayed in USSD for the purchase of tickets
    
    private Calendar todayCal;
    private String previousUssdInput;
    private UssdResponse ussdResponse;
    private ResourceBundle resBundle;
    private Locale locale;
    private final Integer countryCode=250;
    
    private final Random random = new Random(System.currentTimeMillis());
    
    String ussdInput;
    String ussdSessionId;
    String ussdSessionIdsAsString;
    String MSISDN;
    String lang;
    
    LoadingCache<String, UssdManager> cache;
    
    public UssdManager() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .recordStats()
                .build(new CacheLoader<String, UssdManager>() {
                    @Override
                    public UssdManager load(String key) throws Exception {
                        return loadObject(key);
                    }
                    
                });
        
    }
    
    private UssdManager loadObject(String key) {
        
        List<String> menus;
        UssdManager ussdM = new UssdManager();
        ussdResponse = new UssdResponse();
        menus = new ArrayList<>();
        ussdResponse.setMsisdn(key);
        locale = new Locale("us", "US");
        resBundle = getCurrentLocale(locale);
        ussdResponse.setMessageTitle(resBundle.getString("app_title") + "^");
        menus.add("1. " + resBundle.getString("lan_a") + "^");
        menus.add("2. " + resBundle.getString("lan_b") + "^");
        menus.add("3. " + resBundle.getString("lan_c")+ "^");
        ussdResponse.setMenus(menus);
        ussdResponse.setFreeFlow("C");
        ussdResponse.setClientState("1");
        ussdM.setUssdResponse(ussdResponse);
        return ussdM;
        
    }
    
    /**
     * this method is used to handler the ussd request to be sent based on the
     * submitted request
     *
     * @param ussdRequest
     * @return
     */
    public UssdResponse getNextMessage(UssdRequest ussdRequest) {
        String input;
        try{
            if(ussdRequest.getResume()==null){
                ussdRequest.setResume(false);
            }
            if(ussdRequest.getFromMultiUSSD()==null){
                ussdRequest.setFromMultiUSSD(false);
            }
            out.print("USSD Request : "+ CommonLibrary.marchalling(ussdRequest, UssdRequest.class));
            ussdResponse= cache.get(ussdRequest.getMsisdn()).getUssdResponse();
            
            ussdSessionId=ussdRequest.getSessionId().trim();
            ussdResponse.addSessionId2SessionsIds(ussdSessionId);
            ussdResponse.setMsisdn(MSISDN);
            ussdResponse.setInput(ussdInput);
            ussdResponse.setSessionId(ussdRequest.getSessionId());
            
            ussdInput=ussdRequest.getInput().trim();
            MSISDN =ussdRequest.getMsisdn().trim();
            
            ussdSessionIdsAsString="";
            
            
            
            List<String> x=ussdResponse.getSessionIdsList();
            int v=1;
            for (String item:x) {
                if(v==0)
                    ussdSessionIdsAsString+= item;
                else
                    ussdSessionIdsAsString+= ","+item;
                
                v++;
            }
            
            cache.put(ussdRequest.getMsisdn(), this);
            
            
            Integer currentTime=LocalTime.now().getHour();
            Integer periodsCount=1;
            String busName;
            try
            {
                
                //out.print("SessionID:"+ REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn());
                /**
                 * check if the cache contains a null client state this means the
                 * first request of given msisdn
                 */
                List<String> menus;
                /**
                 *
                 */
                String currentClientState= cache.get(ussdRequest.getMsisdn()).getUssdResponse().getClientState();
                
                // check if language is at first request
                if(ussdRequest.getFromMultiUSSD()){
                    currentClientState="customerNames";
                    locale = new Locale(ussdRequest.getLanguage(), ussdRequest.getLanguage().toUpperCase());
                }
                
                if(ussdRequest.getNewRequest()==1 && !currentClientState.equals("1") )
                {
                    //ussdResponse=new UssdResponse();
                    //ussdResponse.setMsisdn(ussdRequest.getMsisdn());
                    ussdResponse.setSessionId(ussdRequest.getSessionId());
                    
                    menus=new ArrayList<>();
                    
                    resBundle=getCurrentLocale(locale);
                    //ussdResponse.setMessageTitle("title");
                    // menus.add("1.continue");
                    //menus.add("2.begin");
                    
                    ussdResponse.setMessageTitle(resBundle.getString("session_stopped_message")+"^^");
                    menus.add("1. "+resBundle.getString("return_resume")+"^");
                    menus.add("2. "+resBundle.getString("return_begin")+"^");
                    
                    ussdResponse.setMenus(menus);
                    ussdResponse.setClientState("0");
                    this.setUssdResponse(ussdResponse);
                    cache.put(ussdRequest.getMsisdn(), this);
                    //out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                    
                    return getUssdResponse();
                }
                
                boolean doExit=false;
                while(!doExit){
                    if(!currentClientState.equals("0")){
                        this.setResumePreviousState(currentClientState);
                        previousUssdInput=ussdInput;
                    }
                    resBundle = getCurrentLocale(locale);
                    switch (currentClientState)
                    {
                        case "0":
                            switch (ussdInput)
                            {
                                case "1":
                                    currentClientState=this.getResumePreviousState();
                                    // ussdResponse= cache.get(ussdRequest.getMsisdn()).getUssdResponse();
                                    ussdResponse.setClientState(currentClientState);
                                    ussdInput=previousUssdInput;
                                    this.setUssdResponse(ussdResponse);
                                    cache.put(ussdRequest.getMsisdn(), this);
                                    continue;
                                case "2":
                                    currentClientState="1";
                                    continue;
                                default :
                                    
                                    return getUssdResponse();
                                    
                            }
                        
                        
                        /* if the client state is at the beginning of the flow */
                        case "1":
                            init();
                            //initialize the chosen code to customer msisdn, this will remail the code unless a different
                            //promotion code is provided at later stage
                            sessions.put(ussdRequest.getMsisdn(), "init");
                            ussdResponse = new UssdResponse();
                            menus = new ArrayList<>();
                            ussdResponse.setMsisdn(ussdRequest.getMsisdn());
                            ussdResponse.setSessionId(ussdRequest.getSessionId());
                            locale = new Locale("us", "US");
                            resBundle = getCurrentLocale(locale);
                            ussdResponse.setMessageTitle(resBundle.getString("app_title") + "^^");
                            menus.add("1. " + resBundle.getString("lan_a") + "^");
                            menus.add("2. " + resBundle.getString("lan_b") + "^");
                            menus.add("3. " + resBundle.getString("lan_c") + "^");
                            ussdResponse.setMenus(menus);
                            ussdResponse.setFreeFlow("C");
                            // set the client state to the language selection
                            ussdResponse.setClientState("2");
                            ussdRequest.setNewRequest(0);
                            this.setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                            break;
                            /* if the client state is on language selection */
                        case "2":
                            switch (ussdInput)
                            {
                                // 1 for english language is chosen /
                                case "1":
                                    // set the language to be used to be english
                                    locale = new Locale("rw", "RW");
                                    currentClientState="mainMenu";
                                    continue;
                                    // 2 for the kinyarwanda language is chosen
                                case "2":
                                    locale = new Locale("us", "US");
                                    currentClientState="mainMenu";
                                    continue;
                                case "3":
                                    locale = new Locale("fr", "FR");
                                    currentClientState="mainMenu";
                                    continue;
                                    // if use submit wrong option or invalid input
                                default:
                                    
                                    ussdResponse.setMessageTitle(resBundle.getString("Wrong_choice") + "^");
                                    menus = new ArrayList<>();
                                    menus.add("1. " + resBundle.getString("lan_a") + "^");
                                    menus.add("2. " + resBundle.getString("lan_b") + "^");
                                    menus.add("3. " + resBundle.getString("lan_c") + "^");
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("C");
                                    out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                                    break;
                            } // end of switch language selection
                            break;
                            /**
                             * if the client state is the on departure input
                             */
                        case "mainMenu":
                            ussdResponse.setMessageTitle("");
                            menus = new ArrayList<>();
                            menus.add("1. " + resBundle.getString("check_balance") + "^");
                            menus.add("2. " + resBundle.getString("load_money_on_my_card") + "^");
                            menus.add("3. " + resBundle.getString("load_money_on_different_card") + "^");
                            menus.add("4. " + resBundle.getString("get_latest_transactions") + "^");
                            menus.add("5. Ururimi/language/langue ");
                            ussdResponse.setMenus(menus);
                            ussdResponse.setFreeFlow("C");
                            ussdResponse.setClientState("mainMenuChoice");
                            setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                            break;
                            
                        case "mainMenuChoice":
                            switch (ussdInput)
                            {
                                // 1 for english language is choosen /
                                case "1":
                                    // User chose to buy tickets
                                    currentClientState="check_balance";
                                    continue;
                                    
                                case "2":
                                    //User chose to view purchased tickets
                                    currentClientState="load_money_on_my_card";
                                    continue;
                                    
                                case "3":
                                    //User chose to view what he needs to know
                                    currentClientState="load_money_on_different_card";
                                    continue;
                                    
                                case "4":
                                    //User chose to view what he needs to know
                                    currentClientState="get_latest_transactions";
                                    continue;
                                default :
                                    
                                    //wrong option is chosen
                                    currentClientState="mainMenuChoice";
                                    continue;
                            }
                        
                        case "check_balance":
                            ussdResponse.setMessageTitle(resBundle.getString("check_balance_chosen"));
                            menus = new ArrayList<>();
                            menus.add("1. " + resBundle.getString("choice_back") + "^");
                            ussdResponse.setMenus(menus);
                            ussdResponse.setFreeFlow("C");
                            ussdResponse.setClientState("get_choice_from_check_balance");
                            setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                            
                            break;
                            
                        case "get_choice_from_check_balance":
                            
                            switch(ussdInput){
                                case "1":
                                    currentClientState="mainMenu";
                                    continue;
                                case "2":
                                    ussdResponse.setMessageTitle(resBundle.getString("thank_you"));
                                    menus = new ArrayList<>();
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("B");
                                    out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                                    break;
                                default:
                                    ussdResponse.setMessageTitle(resBundle.getString("Wrong_choice"));
                                    currentClientState="check_balance";
                                    continue;
                            }
                        
                        case "load_money_on_my_card":
                            ussdResponse.setMessageTitle(resBundle.getString("load_money_on_my_card_chosen"));
                            menus = new ArrayList<>();
                            menus.add("1. " + resBundle.getString("choice_back") + "^");
                            ussdResponse.setMenus(menus);
                            ussdResponse.setFreeFlow("C");
                            ussdResponse.setClientState("get_choice_from_load_money_on_my_card");
                            setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                            
                            break;
                            
                        case "get_choice_from_load_money_on_my_card":
                            
                            switch(ussdInput){
                                case "1":
                                    currentClientState="mainMenu";
                                    continue;
                                case "2":
                                    ussdResponse.setMessageTitle(resBundle.getString("thank_you"));
                                    menus = new ArrayList<>();
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("B");
                                    out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                                    break;
                                default:
                                    ussdResponse.setMessageTitle(resBundle.getString("Wrong_choice"));
                                    currentClientState="get_latest_transactions";
                                    continue;
                            }
                        
                        
                        case "load_money_on_different_card":
                            ussdResponse.setMessageTitle(resBundle.getString("load_money_on_different_card_chosen"));
                            menus = new ArrayList<>();
                            menus.add("1. " + resBundle.getString("choice_back") + "^");
                            ussdResponse.setMenus(menus);
                            ussdResponse.setFreeFlow("C");
                            ussdResponse.setClientState("get_choice_from_load_money_on_different_card");
                            setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                            
                            break;
                            
                        case "get_choice_from_load_money_on_different_card":
                            
                            switch(ussdInput){
                                case "1":
                                    currentClientState="mainMenu";
                                    continue;
                                case "2":
                                    ussdResponse.setMessageTitle(resBundle.getString("thank_you"));
                                    menus = new ArrayList<>();
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("B");
                                    out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                                    break;
                                default:
                                    ussdResponse.setMessageTitle(resBundle.getString("Wrong_choice"));
                                    currentClientState="get_latest_transactions";
                                    continue;
                            }
                        
                        
                        case "get_latest_transactions":
                            ussdResponse.setMessageTitle(resBundle.getString("get_latest_transactions_chosen"));
                            menus = new ArrayList<>();
                            menus.add("1. " + resBundle.getString("choice_back") + "^");
                            ussdResponse.setMenus(menus);
                            ussdResponse.setFreeFlow("C");
                            ussdResponse.setClientState("get_choice_from_get_latest_transactions");
                            setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                            
                            break;
                            
                        case "get_choice_from_get_latest_transactions":
                            
                            switch(ussdInput){
                                case "1":
                                    currentClientState="mainMenu";
                                    continue;
                                case "2":
                                    ussdResponse.setMessageTitle(resBundle.getString("thank_you"));
                                    menus = new ArrayList<>();
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("B");
                                    out.print("REQUEST:" + ussdInput + "|MSISDN:" + ussdRequest.getMsisdn() + "|RESPONSE:" + ussdResponse.getMessageTitle());
                                    break;
                                default:
                                    ussdResponse.setMessageTitle(resBundle.getString("Wrong_choice"));
                                    currentClientState="get_latest_transactions";
                                    continue;
                            }
                        
                        case "initiatePurchase":
                            //preapre reservation request
                            
                            switch(getPayingInstitution(ussdResponse.getMsisdn())){
                                
                                case 2484:
                                    
                                    Thread t = new Thread(new initiatePayment());
                                    t.start();
                                    
                                    menus = new ArrayList<>();
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("B");
                                    
                                    //        ussdResponse.setMessageTitle(resBundle.getString("successful_ticket_booked1") +initiatePurchaseRequest.getAmount()+ resBundle.getString("MTN_successful_ticket_booked2"));
                                    
                                    break;
                                    
                                case 3382:
                                    
                                    if(true){
                                        //ussdResponse.getMsisdn().equals("250722712609")|| ussdResponse.getMsisdn().equals("250722123270") || ussdResponse.getMsisdn().equals("250722123127")){
                                        
                                        Thread t2 = new Thread(new initiatePayment());
                                        t2.start();
                                        
                                        menus = new ArrayList<>();
                                        ussdResponse.setMenus(menus);
                                        ussdResponse.setFreeFlow("B");
                                        
                                        //  ussdResponse.setMessageTitle(resBundle.getString("successful_ticket_booked1") +initiatePurchaseRequest.getAmount()+resBundle.getString("TIGO_successful_ticket_booked2"));
                                        
                                        
                                        
                                    }else{
                                   //     ussdResponse.setMessageTitle("^" + resBundle.getString("Payment_with_tigo_coming_soon"));
                                        
                                        menus = new ArrayList<>();
                                        ussdResponse.setMenus(menus);
                                    }
                                    break;
//                                case 3382:
//                                    menus = new ArrayList<>();
//                                    ussdResponse.setMenus(menus);
//                                    ussdResponse.setFreeFlow("B");
//
//                                    ussdResponse.setMessageTitle("^" + resBundle.getString("Payment_with_TIGOCASH_problem"));
//
//                                    break;
                                case 5728:
                                    menus = new ArrayList<>();
                                    ussdResponse.setMenus(menus);
                                    ussdResponse.setFreeFlow("B");
                             //       ussdResponse.setMessageTitle("^" + resBundle.getString("Payment_with_Airtel_coming_soon"));
                                    ussdResponse.setFreeFlow("B");
                                    break;
                            }
                            
                            ussdResponse.setClientState("1");
                            this.setUssdResponse(ussdResponse);
                            cache.put(ussdRequest.getMsisdn(), this);
                            break;
                            
                    }
                    
                    out.print("MSISDN: " + ussdRequest.getMsisdn()+" | ALL SESSIONIDS: "+ussdSessionIdsAsString+" | LAST SESSIONID: "+ussdSessionId+"| USSDAPPSTATE: "+currentClientState+ " | USSDREQUEST: " + ussdInput + " | USSDRESPONSE: "+ussdResponse.getMessageTitle());
                    
                    doExit=true;
                }
                // } end of the client state not equal to FST ( Flow Start)
                // end the request is not new continue to the next menu
                return getUssdResponse();
            } catch (Exception ex)
            {
                input=ussdInput;
                String msisdn=ussdRequest.getMsisdn();
                ussdResponse=cache.get(msisdn).getUssdResponse();
                ussdResponse.setMessageTitle(resBundle.getString("systemException"));
                List<String> menus=new ArrayList<>();
                
                
                //    ussdResponse.setMessageTitle(ex+"^");
                menus.add("^1. "+resBundle.getString("return_resume"));
                menus.add("^2. "+resBundle.getString("return_begin"));
                ussdResponse.setMenus(menus);
                
                
                this.setResumePreviousState(ussdResponse.getClientState());
                ussdResponse.setClientState("0");
                this.setUssdResponse(ussdResponse);
                cache.put(msisdn, this);
                
                String message="ERROR ON RECEPTION OF USSD: MESSAGE: "+ex.getMessage()+" | TRACE :";
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                message+=errors.toString();
                
                out.print("LAST REQUEST INPUT:" +  input + "|MSISDN:" + msisdn + "|APP EXCEPTION TRACE: "+message);
                
                return ussdResponse;
            }
        }catch (Exception ex2){
            out.print(ex2.getMessage());
            
            return null;
        }
    }
    
    
    class initiatePayment implements Runnable {
        
        @Override
        public void run() {
            try{
                Thread.sleep(5000);
                //  initiatePurchaseResponse = initiatePurchase();
                
            }
            catch(Exception ex){
                
            }
        }
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
    
    private boolean validateNames(String names){
        String regex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(names);
        return matcher.matches() && (names.length()>2);
    }
    
    /**
     * get the current hour in form of Integer
     *
     * @return
     */
    public int getCurrentHour() {
        LocalTime localTime = LocalTime.now();
        return localTime.getHour();
    }
    
    /**
     * get a String representing date in form of yyyy-MM-dd
     *
     * @return
     */
    public String getStringDate() {
        LocalDate localTime = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localTime);
    }
    
    /**
     * this method is used to get the resources bundler bases on chosen local
     * language
     *
     * @param currentLocale
     * @return
     */
    public ResourceBundle getCurrentLocale(Locale currentLocale) {
        ResourceBundle resB = ResourceBundle.getBundle("resources.message", currentLocale);
        return resB;
    }
    
    /**
     * since the remote interface is using the JSon format in its API this
     * method will be used to generate booking request in that format
     *
     * @param ticket
     * @param notToday
     * @return
     */
    
    
    private boolean IsInt_ByRegex(String str) {
        return str.matches("^-?\\d+$");
    }
    
    private void init() {
        
//        availableLocations = new ConcurrentHashMap<>();
        todayCal= new GregorianCalendar();
        todayDate = todayCal.getTime();
    }
    
    private String getValidMsisdn(String msisdn) {
        return countryCode + msisdn.substring(msisdn.length() - 9);
    }
    
    
    public UssdResponse getUssdResponse() {
        return ussdResponse;
    }
    
    public void setUssdResponse(UssdResponse ussdResponse) {
        this.ussdResponse = ussdResponse;
    }
    
    public void setResumePreviousState(String resumePreviousState) {
        this.resumePreviousState = resumePreviousState;
    }
    
    public String getResumePreviousState() {
        return this.resumePreviousState;
    }
    
    public boolean isValidInteger(String input){
        try
        {
            int x= Integer.parseInt(input);
            return true;
        } catch (Exception ne)
        {
            
            return false;
        }
    }
    
    /**
     * method to send notification sms
     *
     * @param receiver
     * @param textMessage
     * @param sender
     * @return
     */
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.ussdResponse.getMsisdn());
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final UssdManager other = (UssdManager) obj;
        if (!Objects.equals(this.ussdResponse.getMsisdn(), other.ussdResponse.getMsisdn()))
        {
            return false;
        }
        return true;
    }
    
}
