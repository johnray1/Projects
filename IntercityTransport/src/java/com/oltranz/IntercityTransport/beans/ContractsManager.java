/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.IntercityTransport.beans;


import com.oltranz.IntercityTransport.entities.Contract;
import com.oltranz.IntercityTransport.entities.ContractPaymentType;
import com.oltranz.IntercityTransport.entities.Service;
import com.oltranz.IntercityTransport.entities.ServiceProvider;
import com.oltranz.IntercityTransport.entities.Transporter;
import com.oltranz.IntercityTransport.models.ContractForDisplayModel;
import com.oltranz.IntercityTransport.models.ResultObject;
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
//import org.eclipse.persistence.config.HintValues;
//import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author manzi
 */
@Stateless
@LocalBean
public class ContractsManager {
    @PersistenceContext(unitName = "IntercityTransportPU")
    private  EntityManager em;
    
    @EJB
            InitialData initialDataEJB;
    
    public ResultObject getContractForDisplay(Integer contractId){
        
        ResultObject resultObject=new  ResultObject();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Contract contract=em.find(Contract.class, contractId);
        int count=0;
        ContractForDisplayModel contractForDisplayModel=new ContractForDisplayModel();
        String message="";
        Service service=null;
        Transporter transporter=null;
        ServiceProvider serviceProvider=null;
        ContractPaymentType contractPaymentType=null;
        
        if(contract!=null){
            if(contract.getServiceId()==null || contract.getTransporterId()==null || contract.getServiceProviderId()==null ||contract.getPaymentTypeId()==null){
                 if(contract.getServiceId()==null){
                    count++;
                    message+=" serviceId";
                }else{
                    service=em.find(Service.class, contract.getServiceId());
                }
                if(contract.getTransporterId()==null){
                    if(count>0)
                        message=",";
                    count++;
                    message+=" transportId";
                }else{
                    transporter=em.find(Transporter.class, contract.getTransporterId());
                }
                
                if(contract.getServiceProviderId()==null){
                    if(count>0)
                        message=",";
                    count++;
                    message+=" serviceProviderId";
                }else{
                    serviceProvider=em.find(ServiceProvider.class, contract.getServiceProviderId());
                }
                
                if(contract.getPaymentTypeId()==null){
                    if(count>0)
                        message=",";
                    count++;
                    message+=" PaymentTypeId";
                }else{
                    contractPaymentType=em.find(ContractPaymentType.class, contract.getPaymentTypeId());
                }
                
                if(count==1)
                    message+=" is missing. ";
                else
                    message+=" are missing. ";
                message=" Error: "+message;
            }
            String message2="";
            count=0;
            if(service==null){
                if(contract.getServiceId()!=null){
                    count++;
                    message2+=" service ,";
                }
                contractForDisplayModel.setServiceName("");
            }
            else{
                contractForDisplayModel.setServiceName(service.getName());
            }
            
            if(transporter==null){
                if(contract.getTransporterId()==null){
                    count++;
                    message2+=" transporter ,";
                }
                contractForDisplayModel.setTransporterName("");
            }else{
                contractForDisplayModel.setTransporterName(transporter.getName());
            }
            
            if(serviceProvider==null){
                if(contract.getServiceProviderId()!=null){
                    count++;
                    message2+=" service provider ,";
                }
                contractForDisplayModel.setServiceProviderName("");
            }else{
                contractForDisplayModel.setServiceProviderName(serviceProvider.getName());
            }
            
            if(contractPaymentType==null){
                if(contract.getPaymentTypeId()!=null){
                    count++;
                    message2+=" Payment Type ,";
                }
                contractForDisplayModel.setPaymentTypeName("");
            }else{
                contractForDisplayModel.setPaymentTypeName(contractPaymentType.getName());
            }
                        
            if(count>0){
                message2="Fatal error: "+ message2;
                if(count==1)
                    message2+=" is not found; Contract system Administrator urgently!!";
                else
                    message2+=" are not found; Contract system Administrator urgently!!";
                
            }
            contractForDisplayModel.setId(contract.getId());
            contractForDisplayModel.setAmount(""+contract.getAmount());
            contractForDisplayModel.setDescr(contract.getDescr());
            contractForDisplayModel.setName(contract.getName());
            contractForDisplayModel.setServiceId(contract.getServiceId());
            contractForDisplayModel.setPaymentTypeId(contract.getPaymentTypeId());
            contractForDisplayModel.setEndDate(sdf.format(contract.getEndDate()));
            contractForDisplayModel.setServiceProviderId(contract.getServiceProviderId());
            contractForDisplayModel.setStartDate(sdf.format(contract.getStartDate()));
            contractForDisplayModel.setTransporterId(contract.getTransporterId());
            
            if(message.isEmpty())
                resultObject.setMessage("Contract well found.");
            else
                resultObject.setMessage("Contract well found."+message+message2);
            
            resultObject.setObject(contractForDisplayModel);
            resultObject.setObjectClass(ContractForDisplayModel.class);
            return resultObject;
        }else{
            resultObject.setMessage("Contract is not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(ContractForDisplayModel.class);
            return resultObject;
        }
    }
    
    public ResultObject getContract(Integer contractId){
        ResultObject resultObject=new ResultObject();
        Contract contract=em.find(Contract.class,contractId);
        
        if(contract!=null){
            resultObject.setMessage("Contract Well found and returned!");
            resultObject.setObject(contract);
            resultObject.setObjectClass(Contract.class);
            return resultObject;
        }else{
            resultObject.setMessage("Contract with given Id not found!");
            resultObject.setObject(null);
            resultObject.setObjectClass(Contract.class);
            return resultObject;
        }
    }
    
    public ResultObject createContract(ContractForDisplayModel newContract){
        ResultObject resultObject=new  ResultObject();
        try{
            initialDataEJB.InitializeContractPaymentTypes();
            
            ContractPaymentType contractPaymentType= em.find(ContractPaymentType.class,newContract.getPaymentTypeId());
            if(contractPaymentType==null){
                resultObject.setMessage("Payment Type not found!");
                resultObject.setObject(null);
                resultObject.setObjectClass(ContractForDisplayModel.class);
                return resultObject;
            }
            
            if(em.find(Service.class, newContract.getServiceId())==null){
                resultObject.setMessage("The Service specified for this Contract cannot be found !");
                resultObject.setObject(null);
                resultObject.setObjectClass(ContractForDisplayModel.class);
                return resultObject;
            }
            
            
            Contract contract=new Contract();
            //confirm that is the new service provider does exist in the system database
            if(em.find(ServiceProvider.class, newContract.getServiceProviderId())==null){
                resultObject.setMessage("The specified service provider for this contract cannot be found !");
                resultObject.setObject(null);
                resultObject.setObjectClass(ContractForDisplayModel.class);
                return resultObject;
            }
            
            if(em.find(Transporter.class, newContract.getTransporterId())==null){
                resultObject.setMessage("The specified Contracting transporter for this contract cannot be found !");
                resultObject.setObject(null);
                resultObject.setObjectClass(ContractForDisplayModel.class);
                return resultObject;
            }
            
            String dateFormat="yyyy-MM-dd";
            
            contract.setAmount(Double.parseDouble(newContract.getAmount()));
            contract.setDescr(newContract.getDescr());
            contract.setPaymentTypeId(newContract.getPaymentTypeId());
            contract.setServiceId(newContract.getServiceId());
            contract.setName(newContract.getName());
            contract.setEndDate(newContract.getEndDate(),dateFormat);
            contract.setServiceProviderId(newContract.getServiceProviderId());
            contract.setStartDate(newContract.getStartDate(),dateFormat);
            contract.setTransporterId(newContract.getTransporterId());
            
            em.persist(contract);
             em.flush();
            
            return getContractForDisplay(contract.getId());
            
        }
        catch(Exception e){
            resultObject.setMessage("Exception duting creation successfully create");
            resultObject.setObject(e.getMessage());
            resultObject.setObjectClass(String.class);
            return resultObject;
        }
    }
    
    public ResultObject updateContract(ContractForDisplayModel editContract){
        ResultObject resultObject=new ResultObject();
        Contract contract=em.find(Contract.class, editContract.getId());
        
        if(em.find(Service.class, editContract.getServiceId())==null){
            resultObject.setMessage("The Service specified for this Contract cannot be found !");
            resultObject.setObject(null);
            resultObject.setObjectClass(ContractForDisplayModel.class);
            return resultObject;
        }
        
        if(!editContract.getPaymentTypeId().equals(contract.getPaymentTypeId())){
            ContractPaymentType contractPaymentType= em.find(ContractPaymentType.class,editContract.getPaymentTypeId());
            if(contractPaymentType==null){
                resultObject.setMessage("New Payment Type not found!");
                resultObject.setObject(null);
                resultObject.setObjectClass(ContractForDisplayModel.class);
                return resultObject;
            }
        }
        
        //confirm that is the new service is different from the existing, the new service does exists
        if(!contract.getServiceProviderId().equals(editContract.getServiceProviderId())){
            if(em.find(ServiceProvider.class, editContract.getServiceProviderId())!=null){
                resultObject.setMessage("The newly specified service provider for this contract cannot be found !");
                resultObject.setObject("FAILURE");
                resultObject.setObjectClass(String.class);
                return resultObject;
            }
        }
        
        if(!contract.getTransporterId().equals(editContract.getTransporterId())){
            if(em.find(Transporter.class, editContract.getTransporterId())!=null){
                resultObject.setMessage("The newly specified Contracting transporter for this contract cannot be found !");
                resultObject.setObject("FAILURE");
                resultObject.setObjectClass(String.class);
                return resultObject;
            }
        }
        
        String dateFormat="yyyy-MM-dd";
        
        contract.setAmount(Double.parseDouble(editContract.getAmount()));
        contract.setDescr(editContract.getDescr());
        contract.setName(editContract.getName());
        contract.setEndDate(editContract.getEndDate(),dateFormat);
        contract.setPaymentTypeId(editContract.getPaymentTypeId());
        contract.setServiceId(editContract.getServiceId());
        contract.setServiceProviderId(editContract.getServiceProviderId());
        contract.setStartDate(editContract.getStartDate(),dateFormat);
        contract.setTransporterId(editContract.getTransporterId());
        em.merge(contract);
        
        return getContractForDisplay(contract.getId());
        
    }
    
    public ResultObject getContractsList(){
        Query query;
        ResultObject resultObject=new ResultObject();
        
        query=em.createQuery("select t from Contract t ");
        
        List<Contract> contractsList=(List<Contract>) query.getResultList();
        List<ContractForDisplayModel> contractForDisplayModelsList=new ArrayList();
        for(Contract x: contractsList){
            contractForDisplayModelsList.add((ContractForDisplayModel)getContractForDisplay(x.getId()).getObject());
        }
        
        resultObject.setMessage("Contracts for Display List well returned");
        resultObject.setObject(contractForDisplayModelsList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject getTransporterContractsList(Integer transporterId){
        ResultObject resultObject=new ResultObject();
        Query query;
        
        query=em.createQuery("select c from Contract c where c.transporterId=:transporterId ");
        query.setParameter("transporterId", transporterId);
        
        List<Contract> contractsList=(List<Contract>)query.getResultList();
        if(contractsList.isEmpty())
            resultObject.setMessage("No Contract with service providers for this transporter");
        else{
            resultObject.setMessage("List of Contracts with Service Provider List well returned");
        }
        
        List<ContractForDisplayModel> contractForDisplayModelsList=new ArrayList();
        for(Contract x: contractsList){
            contractForDisplayModelsList.add((ContractForDisplayModel)getContractForDisplay(x.getId()).getObject());
        }
        
        
        resultObject.setMessage("Contracts of this transporter list well returned");
        resultObject.setObject(contractForDisplayModelsList);
        resultObject.setObjectClass(ArrayList.class);
        return resultObject;
    }
    
    public ResultObject deleteContract(Contract contract2Delete){
        Contract contract=em.find(Contract.class, contract2Delete.getId());
        contract.setStatus(contract.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        contract.setName(contract.getName()+ sdf.format(deletionTIme));
        contract.setStatus(contract.getStatus()&6);
        em.merge(contract);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("Contract successfully sent to dustbin");
        resultObject.setObject(contract);
        resultObject.setObjectClass(Contract.class);
        return resultObject;
    }
    
    public ResultObject deleteContract(Integer contract2DeleteId){
        Contract contract=em.find(Contract.class, contract2DeleteId);
        contract.setStatus(contract.getStatus()&6);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date deletionTIme=new Date();
        contract.setName(contract.getName()+ sdf.format(deletionTIme));
        contract.setStatus(contract.getStatus()&6);
        em.merge(contract);
        
        ResultObject resultObject=new ResultObject();
        resultObject.setMessage("Contract successfully sent to dustbin");
        resultObject.setObject(contract);
        resultObject.setObjectClass(Contract.class);
        return resultObject;
    }
    
}
