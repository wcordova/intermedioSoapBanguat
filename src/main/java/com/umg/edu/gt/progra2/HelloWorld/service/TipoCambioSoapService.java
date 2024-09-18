package com.umg.edu.gt.progra2.HelloWorld.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Service
public class TipoCambioSoapService {

    @Value("${proxy.host:localhost}")
    private String proxyHost;

    @Value("${proxy.port:8080}")
    private int proxyPort;

    public String obtenerTipoCambioDia() {
        String soapEndpoint = "http://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";
        String soapAction = "http://www.banguat.gob.gt/variables/ws/TipoCambioDia";

        String soapRequest =
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                        "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                        "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                        "<soap:Body>" +
                        "<TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />" +
                        "</soap:Body>" +
                        "</soap:Envelope>";

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        requestFactory.setProxy(proxy);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", soapAction);
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, como Gecko) Chrome/91.0.4472.124 Safari/537.36");

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);

        try {
            // Aquí se devuelve todo el cuerpo de la respuesta, no solo el tipo de cambio
            return restTemplate.exchange(soapEndpoint, HttpMethod.POST, request, String.class).getBody();
        } catch (Exception e) {
            return "Error al obtener el tipo de cambio: " + e.getMessage();
        }
    }
}