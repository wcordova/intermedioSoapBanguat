package com.umg.edu.gt.progra2.HelloWorld.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BanguatService {

    @Autowired
    private RestTemplate restTemplate;

    public void consumirApi() {
        String url = "https://banguat.gob.gt/variables/ws/TipoCambio.asmx?wsdl"; // Coloca tu URL aquí
        String resultado = restTemplate.getForObject(url, String.class);
        System.out.println(resultado);
    }
}
