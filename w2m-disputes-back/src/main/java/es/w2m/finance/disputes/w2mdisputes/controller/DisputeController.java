package es.w2m.finance.disputes.w2mdisputes.controller;

import es.w2m.finance.disputes.libsnowflake.client.SnowflakeApiClient;
import es.w2m.finance.disputes.w2mdisputes.client.IaClient;
import es.w2m.finance.disputes.w2mdisputes.client.SapClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * Controlador principal del microservicio de disputas.
 * Este controlador se encarga de recuperar y combinar información
 * de tres servicios externos mockeados: edge-service (Snowflake),
 * sap-mock (estado SAP) e ia-mock (clasificación por IA).
 */
@RestController
@RequestMapping("/dispute")
@RequiredArgsConstructor
public class DisputeController {

    private final SnowflakeApiClient snowflakeApiClient;
    private final SapClient sapClient;
    private final IaClient iaClient;

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
        try{
            Map<String, Object> dispute   = snowflakeApiClient.getDispute();
            Map<String, Object> sapStatus = sapClient.getStatus();
            Map<String, Object> aiCategory= iaClient.getCategory();

            return ResponseEntity.ok(Map.of(
                    "dispute", dispute,
                    "sap_status", sapStatus.get("status"),
                    "ai_category", aiCategory.get("category")
            ));
        }
        catch (HttpClientErrorException.Unauthorized e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Upstream 401 from B");
        } catch (HttpClientErrorException.Forbidden e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Upstream 403 from B");
        }
    }
}
