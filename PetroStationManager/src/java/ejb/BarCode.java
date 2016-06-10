/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 *
 * @author JOHN
 */

public class BarCode {
    
    
    private static String barCodePath = "/Users/JOHN/Desktop/";
    
//this function create barcode image
    public static String eanImage(String fileName) {
        
        try{
            EAN13Bean bean=new EAN13Bean();
            
            final int dpi = 220;
            
            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
            bean.doQuietZone(true);
            
            //Open output file
            File outputFile = new File(barCodePath + fileName + ".JPG");
            
            FileOutputStream out = new FileOutputStream(outputFile);
            
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            
            //Generate the barcode
            bean.generateBarcode(canvas, fileName);
   
            
            canvas.finish();
            
            
        }
        
        catch(IOException ex){
            ex.printStackTrace();
        }
        return "BarCodeCreated";
    }
    
    
    
//this both function create barcode number
    public static String eanNumber(String s) {
        
        int result;
        result = addIntegerPlaceValue(s);
        int mod = result%10;
        int checksum=10-mod;
        s = s + String.valueOf(checksum);
        
        return s;
    }
    
    public static int addIntegerPlaceValue(String barCode){
        
        int summ = 0;
        for (int i = 0; i < barCode.length(); i++){
            
            int value = Character.getNumericValue(barCode.charAt(i));
            if (i%2 == 0){
                
                summ += value;
            }
            else{
                
                summ += value * 3;
            }
            
            
        }
        
        return summ;
    }
    
    
    
}
