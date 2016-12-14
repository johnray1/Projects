/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.kvcs.library;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manzi
 */
public class Common {
    
    
    
    public static class shared{
    
        private static List<selectListItemint> pageSizeList;
        
        
        public  final String MatchEmailPattern =""
                +"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        
        public static byte setBit(byte[] data, int pos, int val) {
            int posByte = pos/8;
            int posBit = pos%8;
            byte oldByte = data[posByte];
            //oldByte = (byte) (((0xFF7F>>posBit) & oldByte) & 0x00FF);
            byte newByte = (byte) ((val<<(8-(posBit+1))) | oldByte);
            data[posByte] = newByte;
            
            return data[posByte] = newByte;
        }
        
        public static String byteArrayToHexString(byte[] bytes) {
            char[] hexArray = "0123456789ABCDEF".toCharArray();
            char[] hexChars = new char[bytes.length * 2];
            for ( int j = 0; j < bytes.length; j++ ) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        }
        
        public static String byteArrayToString(byte[] data){
            String s="";
            for(int i=0;i<data.length;i++){
                byte b1 = data[i];
                s += String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
            }
            return s;
        }
        
        public static byte[] hexStringToByteArray(String s) {
            int len = s.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i+1), 16));
            }
            return data;
        }
        
        public static int GetBit(byte[] data, int pos){
        
            int posByte = pos/8;
            byte thebyte = data[posByte];
            return ((thebyte >> 8-(pos+1)) & 1);
        }
        
        public static List<selectListItemint> getPageSizeList(){
            pageSizeList =  new ArrayList();
            
            pageSizeList.add(new selectListItemint ( 10, "10" ));
            pageSizeList.add(new selectListItemint ( 25, "25"));
            pageSizeList.add(new selectListItemint ( 50, "50"));
            pageSizeList.add(new selectListItemint ( 100, "100"));
            pageSizeList.add(new selectListItemint ( 500, "500"));
            return pageSizeList;
        }
        
        public static  String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
            String generatedPassword = null;
            try {
                
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(salt.getBytes("UTF-8"));
                byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return generatedPassword;
        }
    }
    
    
    
    
    
    public class selectListItemInteger{
    
        private Integer value ;
        private String text ;
        
        public selectListItemInteger(Integer value, String text){
            this.value=value;
            this.text=text;
        }
        /**
         * @return the value
         */
        public Integer getValue() {
            return value;
        }
        
        /**
         * @param value the value to set
         */
        public void setValue(Integer value) {
            this.value = value;
        }
        
        /**
         * @return the text
         */
        public String getText() {
            return text;
        }
        
        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text=text;
        }
        
    }
    
    
    
    
    
    public class selectListItemString{
    
        private String value ;
        private String text ;
        
        public selectListItemString(String value, String text){
            this.value=value;
            this.text=text;
        }
        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }
        
        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }
        
        /**
         * @return the text
         */
        public String getText() {
            return text;
        }
        
        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text=text;
        }
        
    }
    
    
    
    
    
    public class selectListItemBoolean{
  
        private Boolean value ;
        private String text ;
        
        
        public selectListItemBoolean(Boolean value, String text){
            this.value=value;
            this.text=text;
        }
        /**
         * @return the value
         */
        public Boolean getValue() {
            return value;
        }
        
        /**
         * @param value the value to set
         */
        public void setValue(Boolean value) {
            this.value = value;
        }
        
        /**
         * @return the text
         */
        public String getText() {
            return text;
        }
        
        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text=text;
        }
        
    }
    
    
    
    
    
    public static class selectListItemint{
    
        private int value ;
        private String text ;
        
        public selectListItemint(){
            
        }
        public selectListItemint(int value, String text){
            this.value=value;
            this.text=text;
        }
        
        /**
         * @return the value
         */
        public int getValue() {
            return value;
        }
        
        /**
         * @param value the value to set
         */
        public void setValue(int value) {
            this.value = value;
        }
        
        /**
         * @return the text
         */
        public String getText() {
            return text;
        }
        
        /**
         * @param text the text to set
         */
        public void setText(String text) {
            this.text=text;
        }
        
    }
    
    
    
}
