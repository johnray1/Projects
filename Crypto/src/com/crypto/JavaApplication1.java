/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.crypto;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author John
 */
public class JavaApplication1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String username = "bob@google.org";
        String password = "Password1";
        String dataToEncrypt="itsmydata";
        
        Test t=new Test();
        
        String encryptedData = t.encrypt(dataToEncrypt, username, password);
        String decryptedData =t.decrypt(encryptedData, username, password);
       
        System.out.println(encryptedData);
        System.out.println(decryptedData);
        
    }
    
}
