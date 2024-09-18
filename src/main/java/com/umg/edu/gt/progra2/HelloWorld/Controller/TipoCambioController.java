package com.umg.edu.gt.progra2.HelloWorld.Controller;

import com.umg.edu.gt.progra2.HelloWorld.service.TipoCambioSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TipoCambioController {

    @Autowired
    private TipoCambioSoapService tipoCambioSoapService;

    @GetMapping("/tipoCambioDia")
    public ResponseEntity<Map<String, String>> obtenerTipoCambioDia() {
        String tipoCambio = tipoCambioSoapService.obtenerTipoCambioDia();
        Map<String, String> response = new HashMap<>();

        if (tipoCambio.contains("Error")) {
            response.put("status", "error");
            response.put("message", tipoCambio);
            return ResponseEntity.status(500).body(response);
        }

        response.put("status", "success");
        response.put("tipoCambio", tipoCambio);
        return ResponseEntity.ok(response);
    }
}