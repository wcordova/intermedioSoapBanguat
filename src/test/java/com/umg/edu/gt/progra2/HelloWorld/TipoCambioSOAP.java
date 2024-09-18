/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.edu.gt.progra2.HelloWorld;

/**
 *
 * @author benja
 */
import java.io.*;
import java.net.HttpURLConnection; 
import java.net.URL; 
public class TipoCambioSOAP {
    public static void main(Strign[] args){ 
        try {
            // URL del servicio web SOAP
            URL url = new URL("http://www.banguat.gob.gt/variables/ws/TipoCambio.asmx");
            
            // Establecer la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("SOAPAction", "http://www.banguat.gob.gt/variables/ws/TipoCambioDia");
            connection.setDoOutput(true);

            // El contenido del mensaje SOAP
            String xmlInput =
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body>" +
                "<TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                "</soap:Body>" +
                "</soap:Envelope>";

            // Enviar la solicitud SOAP
            OutputStream os = connection.getOutputStream();
            os.write(xmlInput.getBytes());
            os.flush();
            os.close();

            // Comprobar la respuesta del servidor
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Leer la respuesta
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Mostrar la respuesta SOAP
                System.out.println("Response: " + response.toString());
            } else {
                System.out.println("Error en la solicitud SOAP");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


