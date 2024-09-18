package com.umg.edu.gt.progra2.HelloWorld.Controller;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.umg.edu.gt.progra2.HelloWorld.Dto.ModelTC;
import com.umg.edu.gt.progra2.HelloWorld.SoapConsumer.Consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class TipoCambioController {
    
    private final Consumer consumer;

    public TipoCambioController() {
        this.consumer = new Consumer();
    }

    @GetMapping("/tipoCambioDia")
    public ResponseEntity<Object> obtenerTipoCambioDia() {
        String tipoCambioResponse = consumer.obtenerTipoCambioDia();

        if (tipoCambioResponse.contains("Error")) {
            return ResponseEntity.status(500).body("Error: " + tipoCambioResponse);
        }

        try {
            JsonObject jsonObject = JsonParser.parseString(tipoCambioResponse).getAsJsonObject();
            JsonObject varDolar = jsonObject.getAsJsonObject("soap:Envelope")
                    .getAsJsonObject("soap:Body")
                    .getAsJsonObject("TipoCambioDiaResponse")
                    .getAsJsonObject("TipoCambioDiaResult")
                    .getAsJsonObject("CambioDolar")
                    .getAsJsonObject("VarDolar");

            String fecha = varDolar.get("fecha").getAsString();
            double referencia = varDolar.get("referencia").getAsDouble();

            ModelTC tipoCambioDTO = new ModelTC(fecha, referencia);

            return ResponseEntity.ok(tipoCambioDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el XML.");
        }
    }
}
