/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import beans.SpReq;
import beans.VoucherGenerate;
import ejb.CommonLibrary;
import ejb.VoucherManagerEjb;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author JOHN
 */
@Stateless
@Path("vouchermanager")
public class VoucherWebService {
    
    @EJB
    private VoucherManagerEjb voucherManagerEjb;
    
    @EJB
    private CommonLibrary commonLibrary;
    
    
    @POST
    @Path("barCode")
    public String barCode(InputStream is) throws Exception{
           
            VoucherGenerate vg=(VoucherGenerate) commonLibrary.unmarshalling(is, VoucherGenerate.class);
            String s=voucherManagerEjb.barCodeGenerator(vg);
            return s;
    }
    
}
