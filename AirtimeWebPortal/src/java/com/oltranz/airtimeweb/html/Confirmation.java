/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.oltranz.airtimeweb.html;

/**
 *
 * @author JohnRay
 */
public class Confirmation {
    
    public static String confirmationPage(String statusCode,int amt){
        
        String htmlRespone="<html lang=\"en\">\n" +
                "    \n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\"/>\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"/>\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\n" +
                "        <title>MobileA - Confirmation Box</title>\n" +
                "        \n" +
                "        <link href=\"http://fontawesome.io/assets/font-awesome/css/font-awesome.css\" rel=\"stylesheet\" />\n" +
                "        <link rel=\"stylesheet\" type=\"text/css\" href=\"http://fonts.googleapis.com/css?family=Ubuntu:regular,bold&subset=Latin\"/>\n" +
                "        \n" +
                "    </head>\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    <body style=\"margin:0px;padding:0px;font-family: Ubuntu, sans-serif;\">\n" +
                "        \n" +
                "        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "            <tr>\n" +
                "                <td  style=\"margin:0px;padding:0px;height:20px;background-color:#ff5700;\"></td>\n" +
                "            </tr>\n" +
                "            \n" +
                "            <tr>\n" +
                "                <td style=\"text-align:center;padding:27px;font-size:17px;\">\n" +
                "                    <h3 style=\"font-size:30px;color:#808080;\">";
        
        
        if(statusCode.equalsIgnoreCase("400")){
            
            
            htmlRespone +="</h3><br />\n" +
                    "                    of amount : <h3 style=\"font-size:50px;color:#ff5700;margin: 0px;padding: 17px;\">&#8358;";
            htmlRespone +=amt;
            htmlRespone +="</h3>\n" +
                    "              has been successfully processed<br /><br />\n" +
                    "              <i class=\"fa fa-check-circle\" aria-hidden=\"true\" style=\"font-size:117px;color:green;\"></i>		\n" +
                    "                  \n" +
                    "              <h2>Thank You!</h2>\n" +
                    "          </td>\n" +
                    "      </tr>";
            
            htmlRespone +="<tr>\n" +
                    "          <td></td>\n" +
                    "      </tr>";
            htmlRespone +="</table>";
            htmlRespone +="</body >";
            htmlRespone += "</html>";
        }
        else{
            
            
            htmlRespone +="</h3><br />\n" +
                    "		of amount : <h3 style=\"font-size:50px;color:#ff5700;margin: 0px;padding: 17px;\">&#8358; ";
            htmlRespone +=amt;
            htmlRespone +="</h3>\n" +
                    "		has been failed to be processed<br /><br />\n" +
                    "		<i class=\"fa fa-times-circle\" aria-hidden=\"true\" style=\"font-size:117px;color:red;\"></i>		\n" +
                    "\n" +
                    "		<h2>Please try again!</h2>\n" +
                    "		</td>\n" +
                    "	</tr>\n" +
                    "\n" +
                    "	<tr>\n" +
                    "		<td></td>\n" +
                    "	</tr>\n" +
                    "  </table>\n" +
                    "\n" +
                    "\n" +
                    " </body>\n" +
                    "</html>";
        }
        return htmlRespone;
    }
    
}
