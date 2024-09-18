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
    public ResponseEntity<Map<String, Object>> obtenerTipoCambioDia() {
        String tipoCambio = tipoCambioSoapService.obtenerTipoCambioDia();
        Map<String, Object> response = new HashMap<>();

        if (tipoCambio.contains("Error")) {
            response.put("status", "error");
            response.put("message", tipoCambio);
            return ResponseEntity.status(500).body(response);
        }

        // Aquí devolvemos el XML completo en el campo 'data'
        response.put("status", "success");
        response.put("data", tipoCambio); // Se devuelve el XML completo
        return ResponseEntity.ok(response);
    }
}
