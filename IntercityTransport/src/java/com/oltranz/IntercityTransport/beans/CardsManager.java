/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.User;
import com.oltranz.IntercityTransport.entities.Card;
import com.oltranz.IntercityTransport.entities.Passenger;
import com.oltranz.IntercityTransport.entities.PassengerPK;
import com.oltranz.IntercityTransport.models.ResultObject;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import org.eclipse.persistence.config.HintValues;
//import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class CardsManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    public ResultObject getCard(String cardId){
        ResultObject resultObject=new ResultObject();
        Card card=em.find(Card.class,cardId);
        
        if(card==null){
            resultObject.setMessage("Card with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Card.class);
            return resultObject;
        }else{
            resultObject.setMessage("Card well found!");
            resultObject.setObject(card);
            resultObject.setObjectClass(Card.class);
            return resultObject;
        }
    }
    
    public ResultObject createCard(Card newCard){
        ResultObject resultObject=new ResultObject();
        String ownerTypeName="";
        try{
            
            //Owner's Id and owner type id can never null;
            if(newCard.getOwnerId()==null || newCard.getTypeId()==null){
                resultObject.setMessage("Neither OwnerId nor typeId can be null");
                resultObject.setObject(null);
                resultObject.setObjectClass(Card.class);
                return resultObject;
            }
            
            switch(newCard.getTypeId()){
                case 1:
                    if(em.find(User.class, newCard.getOwnerId())==null){
                        resultObject.setMessage("[type 1: passenger],Owner Passenger user (with id:"+newCard.getOwnerId()+")  cannot be found");
                        resultObject.setObject(null);
                        resultObject.setObjectClass(Card.class);
                        return resultObject;
                    }
                    ownerTypeName="Passenger";
                    break;
                case 2:
                    if(em.find(User.class, newCard.getOwnerId())==null){
                        resultObject.setMessage("[type 2: transporter],Owner Transporter (with id:"+newCard.getOwnerId()+")  cannot be found");
                        resultObject.setObject(null);
                        resultObject.setObjectClass(Card.class);
                        return resultObject;
                    }
                    ownerTypeName="Transporter";
                    break;
                    
                default:
                    ownerTypeName="Unknown type";
                    resultObject.setMessage("[type "+newCard.getTypeId()+": Invalid card type");
                    resultObject.setObject(null);
                    resultObject.setObjectClass(Card.class);
                    return resultObject;
            }
            
            //check to see if no other card is attached to this owner
            Query query=em.createQuery("select c from Card c where c.ownerId=:ownerId and c.typeId=:typeId");
            query.setParameter("ownerId", newCard.getOwnerId());
            query.setParameter("typeId", newCard.getTypeId());
            try{
                if(!query.getResultList().isEmpty()){
                    resultObject.setMessage("[type"+newCard.getTypeId()+":"+ownerTypeName+"; This "+ownerTypeName+" has alreadygot another card");
                    resultObject.setObject(null);
                    resultObject.setObjectClass(Card.class);
                    return resultObject;
                }
            }catch(NoResultException ex){
                
            }
            
            em.persist(newCard);
             em.flush();
            
       
            
            resultObject.setMessage("Card for this "+ownerTypeName+" well created");
            resultObject.setObject(newCard);
            resultObject.setObjectClass(Card.class);
            return resultObject;
        }
        catch(Exception e){
            resultObject.setMessage("Exception duting creation of a "+ownerTypeName+" card. Error message:"+e.getMessage());
            resultObject.setObject(null);
            resultObject.setObjectClass(Card.class);
            return resultObject;
        }
    }
    
    
    
    
}
