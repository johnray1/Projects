/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author JOHN
 */
@Stateless
public class Email {
    
    public String sendPasswordByMail(String usermail,String name,String userpass){
        
        final String username = "engensupport@oltranz.com";
        final String password = "pass4test!";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance (props,new javax.mail.Authenticator()
        { protected PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(username, password);
        }
        }
        );
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("engensupport@oltranz.com"));//sender email id
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(usermail));//recipient email id
            message.setSubject("Check Password/Recover Password");
            message.setText("Dear,"+name                  
                    + "\n\n Your registered Username is :-"+"'"+name+"'"
                    + "\n\n Your registered Password/Pin is :-"+"'"+userpass+"'"
                    + "\n\n\n                                               Thank You");
            
            Transport.send(message);
            
        }
        catch (MessagingException e) {
            
            throw new RuntimeException(e);
        }
        
        return "Successfully Send To Email";
    }
    
}
