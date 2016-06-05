/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ejb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import static java.lang.System.out;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author john
 */
@Stateless
public class CommonLibrary {
    
    public Object unmarshalling(InputStream inputStream, Class className) {
        Object object=null;
        try{
            String inputXML=inputStream2String(inputStream);
            XMLInputFactory xmlFactory = XMLInputFactory.newFactory();
            StreamSource streamSource = new StreamSource(new StringReader(inputXML));
            XMLStreamReader streamReader= xmlFactory.createXMLStreamReader(streamSource);
            
            JAXBContext jc = JAXBContext.newInstance(className);
            Unmarshaller unMarshaller = jc.createUnmarshaller();
            object= unMarshaller.unmarshal(streamReader);
            
            inputStream.close();
            return object;
            
        }catch(Exception ex){
            return object;
        }
        finally{
            try{
                inputStream.close();
                
            }
            catch(IOException ex){
                String message="ERROR ON INPUT STREAM RECEPTION OF CENTER REQUEST: MESSAGE: "+ex.getMessage()+" | TRACE :";
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                message+=errors.toString();
                out.print(message);
            }
        }
    }
    
    public Object unmarshalling(String inputXML, Class className) {
        Object object=null;
        try{
            XMLInputFactory xmlFactory = XMLInputFactory.newFactory();
            StreamSource streamSource = new StreamSource(new StringReader(inputXML));
            XMLStreamReader streamReader= xmlFactory.createXMLStreamReader(streamSource);
            
            JAXBContext jc = JAXBContext.newInstance(className);
            Unmarshaller unMarshaller = jc.createUnmarshaller();
            object= unMarshaller.unmarshal(streamReader);
            
            
            return object;
            
        }catch(Exception ex){
            return object;
            
        }
    }
    
    public String marshalling(Object object, Class className){
        
        try{
            JAXBContext ctx = JAXBContext.newInstance(className);
            Marshaller msh = ctx.createMarshaller();
            msh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            msh.marshal(object, writer);
            return writer.toString();
            
        }catch(Exception ex){
            return null;
        }
        
    }
    
    public String inputStream2String(InputStream stream ){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
        
    }
    
    public Response sendRESTRequest(String url,String requestStr, String MediaType, String method){
        try
        {
            Response response=null;
            Client client =ClientBuilder.newClient();
            WebTarget target =client.target(url);
            switch (method){
                case "POST":
                    response = target.request().header("Content-type", "text/xml").header("Signature", "43AD232FD45FF").post(Entity.entity(requestStr, MediaType));
                    
                    break;
                    
                case "GET":
                    response = target.request().get();
                    break;
                    
            }
            
            return response;
        }
        catch(Exception e)
        {
            //System.out.println("Error send request: "+e.getMessage());
            return null;
        }
        
        
    }
    
}
