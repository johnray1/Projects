/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;

import beans.VoucherGenerate;
import entities.Voucher;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOHN
 */
@Stateless
public class VoucherManagerEjb {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @EJB
    private RandomPin rp;
    
    @EJB
    private BarCode bc;
    
    
    public String barCodeGenerator(VoucherGenerate vg){
        
        String number=vg.getNo();//how many voucher customer need
        int no=Integer.parseInt(number);
        String amount=vg.getAmount();//how much amount
        
        int i;
        for (i = 0; i < no; i++){
            
            String ranNo=rp.createRandom12Digit();
            String vNo=bc.eanNumber(ranNo);
            
            Voucher v=new Voucher();
            v.setVNo(vNo);
            v.setVFormat("Ean_13");
            v.setVAmount(Double.parseDouble(amount));
            entityManager.persist(v);
            
            String vImage=bc.eanImage(ranNo);
            
            
        }
        return "persist";
    }
    
    
}
