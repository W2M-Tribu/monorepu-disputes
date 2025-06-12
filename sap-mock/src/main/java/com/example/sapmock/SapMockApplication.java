package com.example.sapmock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * Mock del servicio SAP para disputas.
 *
 * Este controlador cumple con el requisito del PMV de simular el estado
 * de una disputa según lo tendría SAP. Devuelve un estado fijo ("paused").
 *
 * Instrucciones cumplidas:
 * - El microservicio de disputas debe consultar a un mock de SAP.
 * - Este endpoint proporciona un estado simulado para la disputa.
 */
@RestController
public class StatusController {

    /**
     * Endpoint que simula el estado actual de una disputa en SAP.
     *
     * @return Un JSON con el estado fijo simulado
     */
    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Map.of("status", "paused");
    }
}
