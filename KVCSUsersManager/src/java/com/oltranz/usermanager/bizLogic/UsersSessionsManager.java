/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.usermanager.bizLogic;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.oltranz.usermanager.models.UserSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.ejb.Singleton;

/**
 *
 * @author manzi
 */
@Singleton

public class UsersSessionsManager {
    private LoadingCache<String, UserSession> userSessionsCashe;
    private ConcurrentHashMap<String, UserSession> userSessions;
    private LoadingCache<String, String> tokensCashe;
    private ConcurrentHashMap<String, String> tokens;
    UserSession userSession;
    
    public String createNewSession(String email){
        try{
            String newSessionToken=UUID.randomUUID().toString().replace("-", "");
            
            if(tokensCashe==null){
                tokens=new ConcurrentHashMap();
                tokensCashe = CacheBuilder.newBuilder()
                        .initialCapacity(50)
                        .maximumSize(20000)
                        .refreshAfterWrite(5, TimeUnit.MINUTES)
                        .expireAfterAccess(5, TimeUnit.MINUTES)
                        .recordStats()
                        .build(new CacheLoader<String, String>() {
                            @Override
                            public String load(String msisdn) throws Exception {
                                return tokens.get(msisdn);
                            }
                            
                        });
                tokens.put(email, newSessionToken);
            }
            else{
                //if token exists for this customer msisdn, it means that there is a open session
                //We will need to kick out the previous session
                String token=tokens.get(email);
                if(token==null)
                    tokens.put(email, newSessionToken);
                else{
                    userSessionsCashe.invalidate(token);
                }
            }
            
            // create the new Session to saved for this
            LocalDateTime ldt=LocalDateTime.now();
            Date now= Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            
            userSession= new UserSession();
            userSession.setLastAccessTime(now);
            userSession.setUserId(email);
            userSession.setSessionId(newSessionToken);
            
            if(userSessionsCashe==null){
                userSessions=new ConcurrentHashMap();
                userSessionsCashe = CacheBuilder.newBuilder()
                        .initialCapacity(50)
                        .maximumSize(20000)
                        .refreshAfterWrite(5, TimeUnit.MINUTES)
                        .expireAfterAccess(5, TimeUnit.MINUTES)
                        .recordStats()
                        .build(new CacheLoader<String, UserSession>() {
                            @Override
                            public UserSession load(String sessionToken) throws Exception {                                
                                return userSessions.get(sessionToken);
                            }
                            
                        });
                userSessions.put(newSessionToken, userSession);
            }else{
                userSessions.put(newSessionToken, userSession);
            }
            return newSessionToken;
        }catch(Exception ex){
            return null;
        }
    }
    
    public UserSession getUserSession(String token){
        try{
            UserSession session =userSessionsCashe.get(token);
            return session;
        }catch(Exception ex){
            return null;
        }
    }
    
    public Boolean logoutSession(String token){
        userSessionsCashe.invalidate(token);
        return (getUserSession(token)==null);
    }
    
    
    
}
