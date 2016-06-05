/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.payfuel.library;

import java.util.Random;

/**
 *
 * @author JOHN
 */

public  class RandomPin {
    
    public static String createPin(){
        
        int i=getRandomDigit();
        String integer=Integer.toString(i);
        String pin=integer;
        
        return pin;
        
    }
    
    public static int getRandomDigit(){
        return (int)(10.0 * Math.random()*365+14987);
    }
    
    public static String createRandom12Digit(){
        
        String SALTCHARS = "0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        return saltStr;
    }
    
}
