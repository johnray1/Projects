/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.engen.bean;

import com.oltranz.engen.library.Common;
import com.oltranz.engen.library.CommonLibrary;
import java.io.Serializable;
import static java.lang.System.out;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author John
 */
@ManagedBean(name="PermissonBean")
@SessionScoped
public class PermissonBean implements Serializable{
    
    private int i;
    private String[] permisson;
    HttpSession session = SessionBean.getSession();
   
    @ManagedProperty(value="#{LoginBean}")
    private LoginBean loginBean;
    
    public void setUserPermisson(){
        
       
        
        int userId=(int) session.getAttribute("userId");
        String permissions=(String)session.getAttribute("permissions");
        
        byte[] permissionBytes =Common.shared.hexStringToByteArray(permissions);
        
        int length= permisson.length;
        Integer index=0;
        Integer bitValue;
        
        for(i=0;i<4;i++){
            bitValue=0;
            for(Integer j=0;j<length;j++){
                if(Integer.parseInt(permisson[j])==i){
                    bitValue=1;
                    break;
                }
            }
            
            Common.shared.setBit(permissionBytes, i,bitValue);
            
        }
        permissions=Common.shared.byteArrayToHexString(permissionBytes);
        String s=Common.shared.byteArrayToString(permissionBytes);
        
        out.print(permissions);
        
        String url="http://localhost:8080/EngenPayFuel/UserManagementService/user/permisson/"+userId+"/"+permissions;
        Response response=CommonLibrary.sendRESTRequest(url, "empty data",MediaType.APPLICATION_JSON, "POST");
        String jsonResponse=response.readEntity(String.class);
        
        out.println(jsonResponse);
    }
    
    /**
     * @return the i
     */
    public int getI() {
        return i;
    }
    
    /**
     * @param i the i to set
     */
    public void setI(int i) {
        this.i = i;
    }
    
    /**
     * @return the permisson
     */
    public String[] getPermisson() {
        return permisson;
    }
    
    /**
     * @param permisson the permisson to set
     */
    public void setPermisson(String[] permisson) {
        this.permisson = permisson;
    }
    
    /**
     * @return the loginBean
     */
    public LoginBean getLoginBean() {
        return loginBean;
    }
    
    /**
     * @param loginBean the loginBean to set
     */
    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    
    
    
    
}
