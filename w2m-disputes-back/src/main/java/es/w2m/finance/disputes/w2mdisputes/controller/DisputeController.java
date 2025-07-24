package es.w2m.finance.disputes.w2mdisputes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Controlador principal del microservicio de disputas.
 * Este controlador se encarga de recuperar y combinar información
 * de tres servicios externos mockeados: edge-service (Snowflake),
 * sap-mock (estado SAP) e ia-mock (clasificación por IA).
 */
@RestController
@RequestMapping("/dispute")
public class DisputeController {

    // Cliente HTTP para realizar llamadas REST a servicios externos
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Endpoint principal que devuelve información agregada de una disputa.
     *
     * Instrucciones cumplidas:
     * - Al recibir una solicitud a /dispute/{id}, este método llama a tres servicios mock:
     *   1. edge-service → Recupera los datos básicos de la disputa desde Snowflake
     *   2. sap-mock → Simula el estado de la disputa en SAP
     *   3. ia-mock → Simula una clasificación automática de la disputa por IA
     * - Junta la información de los tres en una única respuesta JSON
     *
     * Ejemplo de respuesta esperada:
     * {
     *   "dispute": { "id": "123", "amount": "1000€", ... },
     *   "sap_status": "paused",
     *   "ai_category": "incorrect number of vacation days"
     * }
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getDispute(@PathVariable String id) {
        // Llamada al servicio edge (simula Snowflake)
        //Map<?, ?> dispute = restTemplate.getForObject("http://localhost:8081/dispute/" + id, Map.class);
        Map<?, ?> dispute = restTemplate.getForObject("http://localhost:8081/test-snowflake", Map.class);

        // Llamada al mock de SAP para obtener el estado de la disputa
        Map<?, ?> sapStatus = restTemplate.getForObject("http://localhost:3001/status", Map.class);

        // Llamada al mock de IA para obtener la categoría de la disputa
        Map<?, ?> aiCategory = restTemplate.getForObject("http://localhost:3002/category", Map.class);

        // Composición final de la respuesta con los datos agregados
        return ResponseEntity.ok(Map.of(
                "dispute", dispute,
                "sap_status", sapStatus.get("status"),
                "ai_category", aiCategory.get("category")
        ));
    }
}
