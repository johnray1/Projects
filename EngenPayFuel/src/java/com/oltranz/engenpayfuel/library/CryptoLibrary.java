/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oltranz.engenpayfuel.library;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author John
 */
public class CryptoLibrary {
    
    public String encrypt(final String dataToEncrypt, final String username, final String password) {
        
        String encryptedData = null;
        
        try{
            byte[] key = (username + password).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            
            // Instantiate the cipher
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            
            byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
            
            java.util.Base64.Encoder encoder= java.util.Base64.getEncoder();
            encryptedData = encoder.encodeToString(encryptedByteArray);
            
        }
        catch(Exception e){
            
            System.err.println("Problem encrypting the data");
            e.printStackTrace();
        }
        return encryptedData;
    }
    
    
    public String decrypt(final String dataToDecrypt, final String username, final String password) {
        
        String decryptedData = null;
        
        
        try{
            byte[] key = (username + password).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            
            // Instantiate the cipher
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            
            // Decode using Base64
            java.util.Base64.Decoder decoder= java.util.Base64.getDecoder();
            final byte[] encryptedByteArray = decoder.decode(dataToDecrypt);
            // Decrypt the data
            final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
            decryptedData = new String(decryptedByteArray, "UTF8");
            
            
            
        }
        catch(Exception e){
            
            System.err.println("Problem encrypting the data");
            e.printStackTrace();
        }
        return decryptedData;
    }
    
}
