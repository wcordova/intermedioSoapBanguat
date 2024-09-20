package com.umg.edu.gt.progra2.HelloWorld.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HelloWorldController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public String mostrarPagina() {
        return "index"; // Retorna el archivo index.html
    }

    @GetMapping("/tipoCambio")
    public ResponseEntity<String> obtenerTipoCambio() {
        String url = "https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";

        String requestBody = 
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
            "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
            "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <soap:Body>\n" +
            "    <TipoCambioDia xmlns=\"http://www.banguat.gob.gt/variables/ws/\" />\n" +
            "  </soap:Body>\n" +
            "</soap:Envelope>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", "http://www.banguat.gob.gt/variables/ws/TipoCambioDia");

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        
        // Devolver la respuesta sin modificar
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_XML)
                .body(response.getBody());
    }
}
