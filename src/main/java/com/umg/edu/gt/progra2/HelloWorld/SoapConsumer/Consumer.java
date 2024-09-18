package com.umg.edu.gt.progra2.HelloWorld.SoapConsumer;

import org.springframework.stereotype.Service;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import java.io.ByteArrayOutputStream;

@Service
public class Consumer {
    String soapEndpoint = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx";
    String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";


     public String obtenerTipoCambioDia() {
       try {
            // Crear el mensaje SOAP
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Enviar la solicitud SOAP y obtener la respuesta
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpoint);

            // Procesar la respuesta SOAP
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapResponse.writeTo(out);
            String responseString = new String(out.toByteArray());

            soapConnection.close();

            return responseString;

        } catch (Exception e) {
            // Manejar la excepción
            return "Error al obtener el tipo de cambio: " + e.getMessage();
        }
    }

    private SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }

    private void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "ws";
        String myNamespaceURI = "http://www.banguat.gob.gt/variables/ws/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("TipoCambioDia", myNamespace);
    }
    
}
